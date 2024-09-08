package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_id")
    private Long jobId;

    @Column(name="job_name")
    private String jobName;

    @ElementCollection
    @CollectionTable(name = "job_skills_required", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skills_required")
    private List<String> skillsRequired;


    @Column(name="job_description") // Updated column name
    private String jobDescription;

    @Column(name="job_salary") // Updated column name
    private double jobSalary;

    @Column(name="location")
    private String location;

    @Column(name="posted_date")
    private Date postedDate;

    @Column(name="employment_type")
    private String employmentType;

    @Column(name="job_category")
    private String jobCategory;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
