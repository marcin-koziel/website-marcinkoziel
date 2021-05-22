package ca.marcinkoziel.portfolio.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class ContactForm {

    String name;
    String email;
    String subject;
    String message;
    String reason;
    String[] reasons = {
            "Just to say, hello!", "Business", "Software", "Other"
    };

}
