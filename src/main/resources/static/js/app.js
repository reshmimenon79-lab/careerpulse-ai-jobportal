const API_BASE = "http://localhost:8080/api";

// 1. Fetch and render all jobs
async function loadJobs() {
    try {
        const res = await fetch(`${API_BASE}/jobs`);
        const jobs = await res.json();
        const container = document.getElementById("jobsContainer");

        if (jobs.length === 0) {
            container.innerHTML = "<p>No active jobs found.</p>";
            return;
        }

        container.innerHTML = jobs.map(job => `
            <div class="job-item">
                <div>
                    <div class="job-title">${job.title} <span style="font-size: 12px; color: #9ca3af;">#${job.id}</span></div>
                    <div class="job-details">${job.company} • ${job.location} • ${job.description}</div>
                    <div class="job-salary">₹${Number(job.salary).toLocaleString()} / year</div>
                </div>
                <div>
                    <button class="btn-apply" onclick="applyJob(${job.id})">Apply Now</button>
                </div>
            </div>
        `).join('');

        const filterSelect = document.getElementById("jobFilter");
        filterSelect.innerHTML = jobs.map(j => `<option value="${j.id}">Job #${j.id} (${j.title})</option>`).join('');
        if (jobs.length > 0) {
            loadApplications(jobs[0].id);
        }
    } catch (err) {
        document.getElementById("jobsContainer").innerHTML = `<p style="color:red">Failed to load jobs: ${err.message}</p>`;
    }
}

// 2. Apply for a job
async function applyJob(jobId) {
    const candidateId = 2; // Default seeded candidate ID
    try {
        const res = await fetch(`${API_BASE}/applications/apply?jobId=${jobId}&candidateId=${candidateId}`, {
            method: "POST"
        });
        if (res.ok) {
            alert(`Successfully submitted application for Job #${jobId}!`);
            const activeJobFilter = document.getElementById("jobFilter").value;
            loadApplications(activeJobFilter);
        } else {
            const error = await res.text();
            alert(`Application failed: ${error}`);
        }
    } catch (err) {
        alert(`Error: ${err.message}`);
    }
}

// 3. Post a new job
async function handleCreateJob(event) {
    event.preventDefault();
    const payload = {
        title: document.getElementById("jobTitle").value,
        company: document.getElementById("company").value,
        location: document.getElementById("location").value,
        salary: parseFloat(document.getElementById("salary").value),
        description: document.getElementById("description").value
    };

    try {
        const res = await fetch(`${API_BASE}/jobs`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (res.ok) {
            document.getElementById("jobForm").reset();
            loadJobs();
        } else {
            alert("Failed to save job.");
        }
    } catch (err) {
        alert(`Error: ${err.message}`);
    }
}

// 4. View applicants for a selected job
async function loadApplications(jobId) {
    if (!jobId) return;
    try {
        const res = await fetch(`${API_BASE}/applications/job/${jobId}`);
        const apps = await res.json();
        const tbody = document.getElementById("applicationsTableBody");

        if (!apps || apps.length === 0) {
            tbody.innerHTML = `<tr><td colspan="5" style="text-align: center; color: #9ca3af;">No applications submitted for this job yet.</td></tr>`;
            return;
        }

        tbody.innerHTML = apps.map(app => `
            <tr>
                <td>#${app.id}</td>
                <td><strong>${app.candidate ? app.candidate.name : 'Unknown'}</strong></td>
                <td>${app.candidate ? app.candidate.email : '-'}</td>
                <td>${app.appliedAt ? new Date(app.appliedAt).toLocaleString() : 'Just now'}</td>
                <td><span class="status-badge">${app.status || 'PENDING'}</span></td>
            </tr>
        `).join('');
    } catch (err) {
        console.error(err);
    }
}

// Initial load
loadJobs();
// 5. Call Spring AI Endpoint
async function runAiMatch() {
    const skills = document.getElementById("aiSkillsInput").value.trim();
    const outputDiv = document.getElementById("aiOutput");

    if (!skills) {
        alert("Please enter some skills or resume text first.");
        return;
    }

    outputDiv.style.display = "block";
    outputDiv.innerText = "Connecting to Spring AI... Analyzing profile fit...";

    try {
        const res = await fetch(`${API_BASE}/ai/match`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                jobDescription: "Java Backend Developer: Spring Boot, REST APIs, Microservices, and Relational Databases",
                resumeText: skills
            })
        });

        const data = await res.json();
        outputDiv.innerText = data.result;
    } catch (err) {
        outputDiv.innerText = "Error contacting AI service: " + err.message;
    }
}