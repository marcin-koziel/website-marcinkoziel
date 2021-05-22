package ca.marcinkoziel.portfolio.controllers;

import ca.marcinkoziel.portfolio.beans.ContactForm;
import ca.marcinkoziel.portfolio.beans.Experience;
import ca.marcinkoziel.portfolio.beans.Project;
import ca.marcinkoziel.portfolio.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private MailService mailService;

    @GetMapping({"/", "/home", "experience", "projects"})
    public String goIndex(Model model){

        List<Project> projects = new ArrayList<>();
        List<Experience> experiences = new ArrayList<>();

        projects.add(
                Project.builder()
                .title("Robert Koziel Website")
                .linkGithub("https://github.com/marcin-koziel/website-robertkoziel")
                .image("website-cover.jpg")
                .build()
        );
        projects.add(
                Project.builder()
                        .title("SmartDice")
                        .linkGithub("https://github.com/marcin-koziel/smartdice")
                        .image("smartdice-cover.jpg")
                        .build()
        );

        LocalDate fromDate = LocalDate.of(2021, 1, 5);
        LocalDate toDate = LocalDate.of(2021, 5, 7);
        Period monsDiff = Period.between(fromDate, toDate);

        experiences.add(
                Experience.builder()
                .title("Junior .NET Software Developer")
                .companyName("Inovex Inc.")
                .companyLink("https://www.linkedin.com/company/inovex-inc")
                .logoPath("https://media-exp1.licdn.com/dms/image/C560BAQGEa8-7In_pZg/company-logo_100_100/0/1561666515322?e=1628726400&v=beta&t=4hfwiRL4cRjUJRK4CuQ-RxVkqHn9FMpVneJfzVNfROU")
                .fromDate(fromDate)
                .toDate(toDate)
                .dateDuration(monsDiff.getMonths()+1)
                .bulletPoints(new ArrayList<>() {{
                    add("Built new features and modules for solutions aligned with application " +
                            "functionality and client requirements");
                    add("Provided QA support during the implementation of RFC processes to ensure " +
                            "the proposed change(s) meets regulatory requirements");
                    add("Compiled and assessed QA data/documentation, conducted analysis, and risk assessment for " +
                            "cross-functional teams that may include Project Manager, IT, QA, and Project Sponsor");
                              }}
                ).build()
        );

        model
            .addAttribute("projectList", projects)
            .addAttribute("experienceList", experiences);

        return "index";
    }

    @GetMapping("/contact")
    public String goToContact(ContactForm contactForm, Model model){

        model.addAttribute("contactFrom", contactForm);

        return "contact";
    };

    @PostMapping("/contact/send")
    public String send(@ModelAttribute ContactForm contactForm){

        System.out.println(contactForm.getName());
        System.out.println(contactForm.getEmail());
        System.out.println(contactForm.getSubject());
        System.out.println(contactForm.getReason());
        System.out.println(contactForm.getMessage());

        try {
            String content = "Name: " + contactForm.getName();
            content += "<br>From: " + contactForm.getEmail();
            content += "<br>Subject: " + contactForm.getSubject();
            content += "<br>Reason: " + contactForm.getReason();
            content += "<br>Message: " + contactForm.getMessage();
            mailService.send(contactForm.getEmail(), "marcinzkoziel+1@gmail.com", contactForm.getSubject(),
                    contactForm.getReason(), content);
        } catch (Exception e) {
            return "redirect:/";
        }

        return "redirect:/";
    };

    @RequestMapping(value = "/resume", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() throws IOException {
        ClassPathResource pdfFile = new ClassPathResource("downloads/Koziel_Marcin_Resume.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + "Koziel_Marcin_Resume.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.setContentLength(pdfFile.contentLength());
        return new ResponseEntity<InputStreamResource>(
                new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
    };

}
