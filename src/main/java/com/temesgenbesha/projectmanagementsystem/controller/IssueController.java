
package com.temesgenbesha.projectmanagementsystem.controller;

        import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
        import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
        import com.temesgenbesha.projectmanagementsystem.entity.Status;
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

        import java.beans.PropertyEditorSupport;
        import java.net.URI;
        import java.net.URISyntaxException;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
@Controller
public class IssueController {

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( LocalDateTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText( String text ) throws IllegalArgumentException
            {
                //2022-06-03T00:57
                //"2019-09-20T12:36:39.359"
                System.out.println("Get time: "+text);
                LocalDateTime.parse( text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm") );
            }
        } );
    }

    private final IssueService issueService;


    @RequestMapping(value =  "/api/issue")
    public String displayEnums(Model model){
        model.addAttribute("status", Status.values());

        return "issue/new" ;

    }

    @PostMapping(value = "/api/issue")
    public ResponseEntity<IssueDTO> createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException{
        IssueDTO createdIssue = issueService.addIssue(issueDTO);
        return ResponseEntity.created(new URI("/api/issue/" + createdIssue.getId())).build();
    }


}