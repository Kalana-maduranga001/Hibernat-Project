package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentTm {

    private String id;
    private String patientId;
    private String programId;
    private String date;
    private String status;
    private double amount;

}
