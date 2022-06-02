package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.Project;
import lombok.Data;

import java.time.LocalDate;

// The Mapper implemented inside the DTO
// This method will return Project entity
@Data
public class ProjectDTO {
    private Long id;
    private String name;

    private String projectDescription;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private LocalDate startDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private LocalDate targetEndDate;

    private LocalDate actualEndDate;

    private LocalDate createdOn;
    private UserDTO createdBy;

    public Project toEntity() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setProjectDescription(projectDescription);
        project.setStartDate(startDate);
        project.setTargetEndDate(targetEndDate);
        project.setActualEndDate(actualEndDate);
        project.setCreatedOn(createdOn);
        if (createdBy != null) {
            project.setCreatedBy(createdBy.toEntity());
        }

        return project;
    }
}
