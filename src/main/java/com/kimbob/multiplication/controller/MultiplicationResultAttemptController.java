package com.kimbob.multiplication.controller;

import com.kimbob.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;


}
