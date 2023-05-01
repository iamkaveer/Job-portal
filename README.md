# Job-portal
## Frameworks and language used
- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database

## Data flow 
- **Controller -** Handles incoming requests and returns responses.
- **Repository -** Provides various query methods to retrieve and manipulate data from the database.
- **Model -** Defines the Job class used to represent a job posting.
- **Enums -** Defines the JobType enum used in the Job class to represent the type of job.

## Endpoints
- **GET /jobs/{id} -** Get a specific job by ID
- **GET /jobs -** Get all jobs
- **POST /jobs -** Create a new job
- **PUT /jobs/{id}/salary -** Update the salary of a job by ID
- **DELETE /jobs -** Delete jobs by company name
- **GET /jobs/findByTitle -** Find jobs by title
- **GET /jobs/salary/{salary} -** Find jobs with salary greater than or equal to the specified amount
- **GET /jobs/findByJobType -** Find jobs by job type
- **GET /jobs/search -** Search jobs by a query string

## Screenshots
![Screenshot (170)](https://user-images.githubusercontent.com/41718548/235440316-aa90e411-a9d8-49e5-873e-f8f31396d317.png)


