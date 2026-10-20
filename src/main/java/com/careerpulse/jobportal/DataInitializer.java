package com.careerpulse.jobportal;

import com.careerpulse.jobportal.model.Job;
import com.careerpulse.jobportal.model.JobApplication;
import com.careerpulse.jobportal.model.Role;
import com.careerpulse.jobportal.model.User;
import com.careerpulse.jobportal.repository.JobApplicationRepository;
import com.careerpulse.jobportal.repository.JobRepository;
import com.careerpulse.jobportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository applicationRepository;

    public DataInitializer(JobRepository jobRepository,
                           UserRepository userRepository,
                           JobApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample Jobs
        Job job1 = jobRepository.save(new Job(null, "Java Backend Developer", "TechCorp", "Remote", "Develop REST APIs with Spring Boot", 600000.0));
        Job job2 = jobRepository.save(new Job(null, "Junior Full Stack Engineer", "Innovate Labs", "Bangalore", "Work on React and Spring microservices", 550000.0));

        // Sample Users
        User recruiter = userRepository.save(new User(null, "Alex HR", "alex.hr@techcorp.com", Role.RECRUITER));
        User candidate = userRepository.save(new User(null, "Reshmi Menon", "reshmi@example.com", Role.CANDIDATE));

        // Sample Job Application
        JobApplication application = new JobApplication();
        application.setJob(job1);
        application.setCandidate(candidate);
        application.setResumeUrl("https://careerpulse.storage/resumes/reshmi_resume.pdf");
        applicationRepository.save(application);

        System.out.println(">> Database Seeded: Users, Jobs, and Applications created!");
    }
}