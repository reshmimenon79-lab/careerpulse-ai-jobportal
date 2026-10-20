package com.careerpulse.jobportal.controller;

import com.careerpulse.jobportal.service.AiMatchingService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiMatchingService aiMatchingService;

    public AiController(AiMatchingService aiMatchingService) {
        this.aiMatchingService = aiMatchingService;
    }

    @PostMapping("/match")
    public Map<String, String> matchResume(@RequestBody Map<String, String> payload) {
        String jobDesc = payload.getOrDefault("jobDescription", "General Software Engineering");
        String resumeText = payload.getOrDefault("resumeText", "Java developer with Spring Boot knowledge");

        String result = aiMatchingService.matchResume(jobDesc, resumeText);
        return Map.of("result", result);
    }
}