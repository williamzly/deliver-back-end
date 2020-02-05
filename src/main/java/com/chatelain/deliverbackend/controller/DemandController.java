package com.chatelain.deliverbackend.controller;

import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demands")
public class DemandController {


    @GetMapping
    public ResponseDTO getDemands() {

        return new SingleResponseDTO("测试返回的DATA");
    }

}
