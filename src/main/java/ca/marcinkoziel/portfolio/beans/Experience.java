package ca.marcinkoziel.portfolio.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class Experience {

    String title;
    String companyName;
    String companyLink;
    String logoPath;
    LocalDate fromDate;
    LocalDate toDate;
    int dateDuration;
    ArrayList<String> bulletPoints;

}
