package com.chatelain.deliverbackend.controller;

import com.chatelain.deliverbackend.dto.request.AuthorizationForm;
import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("authorization")
    public ResponseDTO authorizeUser(@RequestBody @Valid AuthorizationForm authorizationForm) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Account authorized = userService.authorize(account, authorizationForm);
        return new SingleResponseDTO(authorized.getRoleType());
    }


}
