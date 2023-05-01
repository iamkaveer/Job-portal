package com.JobSearchPortal.JobSearch.controller;
import com.JobSearchPortal.JobSearch.model.Job;
import com.JobSearchPortal.JobSearch.model.JobType;
import com.JobSearchPortal.JobSearch.repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/jobs/find-by-id/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("get-all-jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jobs);
        }
    }
    @PostMapping("/add-new-jobs")
    public ResponseEntity<Job> createJob(@RequestBody @Valid Job job) {
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.created(URI.create("/jobs/" + savedJob.getId())).body(savedJob);
    }
    @GetMapping("/jobs/find-by-title")
    public ResponseEntity<List<Job>> findJobsByTitle(@RequestParam String title) {
        List<Job> jobs = jobRepository.findByTitleContaining(title);
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jobs);
        }
    }
    @GetMapping("/jobs/find-by-salary/{salary}")
    public ResponseEntity<List<Job>> getJobsBySalary(@PathVariable Double salary) {
        List<Job> jobs = jobRepository.findBySalaryGreaterThanEqual(salary);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    @GetMapping("/jobs/find-by-type")
    public ResponseEntity<List<Job>> findJobsByJobType(@RequestParam JobType jobType) {
        List<Job> jobs = jobRepository.findByJobType(jobType);
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jobs);
        }
    }

    @GetMapping("/jobs/search-by-title-or-decs")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String query) {
        List<Job> jobs = jobRepository.searchJobs(query);
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jobs);
        }
    }

    @PutMapping("/jobs/update-salary/{id}")
    public ResponseEntity<String> updateSalary(@PathVariable Long id, @RequestParam Double salary) {
        jobRepository.updateSalaryById(id, salary);
        return ResponseEntity.ok("Salary updated successfully");
    }

    @DeleteMapping("/jobs/delete-by-company-name")
    public ResponseEntity<String> deleteByCompanyName(@RequestParam String companyName) {
        jobRepository.deleteByCompanyName(companyName);
        return ResponseEntity.ok("Jobs deleted successfully");
    }

}
