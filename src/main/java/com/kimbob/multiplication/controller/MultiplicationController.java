package com.kimbob.multiplication.controller;

import com.kimbob.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MultiplicationController {

    private final MultiplicationService multiplicationService;
}
