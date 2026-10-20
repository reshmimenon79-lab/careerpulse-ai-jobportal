package com.careerpulse.jobportal.controller;

import com.careerpulse.jobportal.model.Job;
import com.careerpulse.jobportal.model.JobApplication;
import com.careerpulse.jobportal.model.Role;
import com.careerpulse.jobportal.model.User;
import com.careerpulse.jobportal.repository.JobApplicationRepository;
import com.careerpulse.jobportal.repository.JobRepository;
import com.careerpulse.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    // Candidate applies for a job
    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(
            @RequestParam Long jobId,
            @RequestParam Long candidateId,
            @RequestParam(required = false) String resumeUrl) {

        User candidate = userRepository.findById(candidateId)
                .orElse(null);
        if (candidate == null || candidate.getRole() != Role.CANDIDATE) {
            return ResponseEntity.badRequest().body("Invalid candidate ID or user is not a candidate.");
        }

        Job job = jobRepository.findById(jobId).orElse(null);
        if (job == null) {
            return ResponseEntity.badRequest().body("Job not found.");
        }

        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setCandidate(candidate);
        application.setResumeUrl(resumeUrl != null ? resumeUrl : "default_resume.pdf");

        return ResponseEntity.ok(applicationRepository.save(application));
    }

    // Recruiter views all applicants for a specific job
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplicationsForJob(@PathVariable Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Candidate views their submitted applications
    @GetMapping("/candidate/{candidateId}")
    public List<JobApplication> getApplicationsByCandidate(@PathVariable Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }
}