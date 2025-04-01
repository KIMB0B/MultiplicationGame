package com.kimbob.multiplication.controller;

import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplications")
@RequiredArgsConstructor
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createMultiplication();
    }
}
