package com.careerpulse.jobportal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class TestController {

        @GetMapping("/test")
        public String test() {
            return "CareerPulse server is working perfectly!";
        }
    }
