package com.kimbob.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimbob.multiplication.domain.Multiplication;
import com.kimbob.multiplication.domain.MultiplicationResultAttempt;
import com.kimbob.multiplication.domain.ResultResponse;
import com.kimbob.multiplication.domain.User;
import com.kimbob.multiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(MultiplicationResultAttemptController.class)
@Import(MultiplicationResultAttemptControllerTest.TestConfig.class)
class MultiplicationResultAttemptControllerTest {

    @Autowired
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnIncorrect() throws Exception {
        genericParameterizedTest(false);
    }

    void genericParameterizedTest(boolean correct) throws Exception {
        when(multiplicationService
                .checkAttempt(any(MultiplicationResultAttempt.class))
        ).thenReturn(correct);

        User user = new User("John_doe");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500);

        MockHttpServletResponse response = mvc.perform(
                post("/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonResult.write(attempt).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResponse.write(new ResultResponse(correct)).getJson());
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public MultiplicationService multiplicationService() {
            return Mockito.mock(MultiplicationService.class);  // Mock 객체 반환
        }
    }
}