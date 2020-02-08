package com.chatelain.deliverbackend.controller;

import com.chatelain.deliverbackend.dto.request.AuthorizationForm;
import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.security.auth.IdToken;
import com.chatelain.deliverbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("authorization")
    public ResponseDTO authorizeUser(@RequestBody @Valid AuthorizationForm authorizationForm, @ApiIgnore IdToken principal) {
        Account account = (Account) principal.getDetails();
        Account authorized = userService.authorize(account, authorizationForm);
        return new SingleResponseDTO(authorized.getRoleType());
    }


}
