package com.kimbob.multiplication.controller;

import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import com.kimbob.multiplication.domain.ResultResponse;
import com.kimbob.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    @PostMapping
    public ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        return ResponseEntity.ok(
                new ResultResponse(multiplicationService.checkAttempt(multiplicationResultAttempt))
        );
    }
}
