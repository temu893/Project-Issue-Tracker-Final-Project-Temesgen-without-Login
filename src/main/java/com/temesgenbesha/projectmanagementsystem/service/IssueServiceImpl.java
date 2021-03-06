package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.*;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.IssueRepository;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Slf4j
@Service
public class IssueServiceImpl implements IssueService {

   private final UserRepository userRepository;

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;


    @Override
    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll().stream().map(Issue::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<IssueDTO> getIssuesFromProject(Long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        return issueRepository.findAllByProject(project).stream().map(Issue::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<IssueDTO> getIssueById(long id) throws Exception {
        return issueRepository.findById(id).stream().map(Issue::toDTO).collect(Collectors.toList()) ;

    }




    @Override
    public IssueDTO addIssue(IssueDTO issueDTO) throws Exception {
        User admin = userRepository.findByUsername("temub").orElseThrow(() -> new UsernameNotFoundException("User with username temub not found!"));
        User user1 = userRepository.findByUsername("mm").orElseThrow(() -> new UsernameNotFoundException("User with username mm not found!"));
        Issue issue = issueDTO.toEntity();

//        issue.setProject(issue.getProject());
        issue.getId();
        issue.setSummary(issueDTO.getSummary());
        issue.setDescription(issueDTO.getDescription());
//        issue.setCreatedBy(issueDTO.getCreatedBy().toEntity());
        issue.setCreatedOn(LocalDateTime.now());
        issue.setAssignedTo(issueDTO.getAssignedTo());
        issue.setAssignedOn(LocalDateTime.now());
        issue.setStatus(issueDTO.getStatus());
        issue.setPriority(issueDTO.getPriority());
        issue.getTargetResolutionDate();
        issue.setResolutionSummary(issueDTO.getResolutionSummary());
//        issue.getProject().getId();
//        issue.setProject((Project) getIssuesFromProject(projectRepository.findById(id)));

        issue = issueRepository.save(issue);
        log.info("Created new issue ", issue);
        return issue.toDTO();

    }

    @Override
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);

    }


}
