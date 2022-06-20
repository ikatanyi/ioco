package com.ioco.apocalypse.robot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioco.apocalypse.exception.APIException;
import com.ioco.apocalypse.exception.ExceptionResponseDTO;
import com.ioco.apocalypse.robot.data.RobotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final WebClient webClient;

    @Value("${robot.api.url}")
    private String robotApiUrl;

    ObjectMapper mapper = new ObjectMapper();

    public List<RobotDto> fetchRobots() throws JsonProcessingException, APIException {
        String resp = webClient.get()
                .uri(robotApiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.error(new ExceptionResponseDTO(e.getMessage())))
                .block();
        return mapper.readValue(resp, new TypeReference<List<RobotDto>>(){});
    }
}
