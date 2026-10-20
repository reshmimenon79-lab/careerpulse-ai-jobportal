package com.careerpulse.jobportal.controller;

import com.careerpulse.jobportal.model.Job;
import com.careerpulse.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    // Get all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Add a new job
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }
}