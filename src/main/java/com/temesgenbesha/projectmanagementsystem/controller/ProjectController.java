package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/api/project")
public class ProjectController {


    private final ProjectService projectService;
    private final IssueService issueService;

    //Adding new project
    @PostMapping
    public void saveNewProject(@ModelAttribute("project") ProjectDTO projectDTO, HttpServletResponse response) throws IOException {
        projectService.addProject(projectDTO);
        response.sendRedirect("/overview?success");
    }
    @PostMapping("/{id}")
    public void updateProject(@PathVariable Long id, @ModelAttribute("project") ProjectDTO projectDTO, HttpServletResponse response) throws ProjectNotFoundException, IOException, ProjectNotFoundException {
        projectService.updateProject(id, projectDTO);
        response.sendRedirect("/overview?updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }


//
//    @RequestMapping(value =  "/api/issue")
//    public String displayEnums(Model model){
//        model.addAttribute("status", Status.values());
//
//        return "issue/new" ;
//
//    }
//
//    @PostMapping(value = "/api/issue")
//    public ResponseEntity<IssueDTO> createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException{
//        IssueDTO createdIssue = issueService.addIssue(issueDTO);
//        return ResponseEntity.created(new URI("/api/issue/" + createdIssue.getId())).build();
//    }


}
