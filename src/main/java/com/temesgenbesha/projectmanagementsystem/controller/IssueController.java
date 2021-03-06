package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import com.temesgenbesha.projectmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/issue")
public class IssueController {


        private final IssueService issueService;
        private final UserService userService;
        private final ProjectService projectService;




//@RequestMapping(value = {"/issue/new"},method = RequestMethod.GET)
//public String slelectOption(Model model){
//        Issue issue = new Issue();
//        model.addAttribute("issue", issue);
//        Map<Long,String> user = (Map<Long, String>) userService.getAllUsers();
//        model.addAttribute("user",user);
//        return "createNewIssue";
//}


        @PostMapping
        public void createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO, HttpServletResponse response) throws Exception {
                issueService.addIssue(issueDTO);

                response.sendRedirect("/project/{id}/issue");

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
                issueService.deleteIssue(id);
                return ResponseEntity.ok().build();
        }


}