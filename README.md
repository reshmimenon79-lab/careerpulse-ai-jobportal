# CareerPulse — AI-Powered Talent & Recruitment Platform

An enterprise recruitment platform engineered with **Spring Boot 3**, **Spring Data JPA**, **Spring AI**, and containerized via **Docker**.

---

##  Key Features

- **Candidate Portal:** Browse active job openings and submit applications with a single click.
- **Recruiter Dashboard:** Post new job openings and inspect real-time applicant submissions.
- **Spring AI Resume Matcher:** Evaluates candidate skills against job requirements to compute match percentages, key strengths, and generate customized interview questions.
- **RESTful Architecture:** Clear separation between backend REST APIs and lightweight frontend static views.
- **In-Memory & Production Ready:** Configured with H2 database for local development; easily swappable to cloud PostgreSQL.
- **Containerized Deployment:** Features a multi-stage Docker build for reproducible cross-platform deployment.

---

##  Tech Stack

- **Backend:** Java 17, Spring Boot 3.3.4, Spring Data JPA, Hibernate
- **AI Integration:** Spring AI (OpenAI API / Prompt Engineering)
- **Database:** H2 Database (Local In-Memory), PostgreSQL-compatible
- **Frontend:** HTML5, Modern CSS3, JavaScript (Fetch API)
- **DevOps & Cloud:** Docker, Git & GitHub, Render / Railway Cloud Hosting

---

##  REST API Endpoints

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/jobs` | Retrieve all active job postings |
| `POST` | `/api/jobs` | Create a new job listing |
| `GET` | `/api/users` | List registered candidates and recruiters |
| `POST` | `/api/users` | Register a new user |
| `POST` | `/api/applications/apply` | Submit a candidate application |
| `GET` | `/api/applications/job/{id}` | View all applicants for a specific job |
| `POST` | `/api/ai/match` | Analyze candidate skill match against job description |

---

##  Getting Started

### Prerequisites
- JDK 17 or higher
- Git

### Local Setup
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/reshmimenon79-lab/careerpulse-ai-jobportal.git](https://github.com/reshmimenon79-lab/careerpulse-ai-jobportal.git)
   cd careerpulse-ai-jobportal
