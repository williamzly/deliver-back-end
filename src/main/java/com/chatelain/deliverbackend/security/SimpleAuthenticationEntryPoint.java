package com.chatelain.deliverbackend.security;

import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {
     @Override
     public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         response.setCharacterEncoding(StandardCharsets.UTF_8.name());
         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         SingleResponseDTO responseDTO = new SingleResponseDTO(401, authException.getMessage());
         ObjectMapper objectMapper = new ObjectMapper();
         String resBody = objectMapper.writeValueAsString(responseDTO);
         PrintWriter printWriter = response.getWriter();
         printWriter.print(resBody);
         printWriter.flush();
         printWriter.close();
     }
 }