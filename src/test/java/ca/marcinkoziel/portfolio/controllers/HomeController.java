package ca.marcinkoziel.portfolio.controllers;

import ca.marcinkoziel.portfolio.beans.ContactForm;
import ca.marcinkoziel.portfolio.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private MailService mailService;

    @GetMapping("/")
    public String goHome(){

        return "index";
    }

    @GetMapping("/aboutme")
    public String goToAboutMe(){

        return "index";
    }

    @GetMapping("/projects")
    public String goToProjects(){

        return "index";
    }

    @GetMapping("/contact")
    public String goToContact(ContactForm contactForm, Model model){

        model.addAttribute("contactFrom", contactForm);

        return "contact";
    }

    @PostMapping("/contact/send")
    public String send(@ModelAttribute ContactForm contactForm){

        try{
            String content = "Name: " + contactForm.getName();
            content += "<br>From: " + contactForm.getEmail();
            content += "<br>Subject: " + contactForm.getSubject();
            content += "<br>Reason: " + contactForm.getReason();
            content += "<br>Message: " + contactForm.getMessage();
            mailService.send(contactForm.getEmail(), "marcinzkoziel+1@gmail.com", contactForm.getSubject(),
                    contactForm.getReason(), content);
        } catch (Exception e) {
            return "error/index";
        }

        return "redirect:/";
    }

    @GetMapping("/javascript")
    public String goToJavaScript(){
        return "redirect:http://koziel.dev.fast.sheridanc.on.ca";
    }

}

