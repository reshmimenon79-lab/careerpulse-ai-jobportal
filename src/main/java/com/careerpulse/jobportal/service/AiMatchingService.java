package com.careerpulse.jobportal.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiMatchingService {

    private final ChatClient chatClient;

    public AiMatchingService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String matchResume(String jobDescription, String candidateSkills) {
        try {
            String prompt = """
                You are an expert HR recruitment specialist.
                Analyze the match between this job description and the candidate's skills.
                Provide:
                1. Match Score (percentage out of 100)
                2. Key Matching Strengths
                3. Missing Skills / Areas for Growth
                4. 2 Tailored Technical Interview Questions
                
                Job Description:
                %s
                
                Candidate Profile / Resume Summary:
                %s
                """.formatted(jobDescription, candidateSkills);

            return chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            // Graceful fallback for mock/demo testing when using a dummy API key
            return "AI Analysis (Demo Mode):\n"
                    + "• Match Score: 85%\n"
                    + "• Strengths: Solid Java, Spring Boot, REST APIs, and SQL foundation.\n"
                    + "• Suggested Growth: Practice Docker containerization and cloud CI/CD.\n"
                    + "• Sample Interview Question: How does Spring Boot auto-configuration determine which beans to load?";
        }
    }
}