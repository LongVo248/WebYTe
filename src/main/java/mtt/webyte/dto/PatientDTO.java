package mtt.webyte.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PatientDTO {
    public int patientID;
    public int username;
    public String address;
    public String email;
    public String firstName;
    public String lastName;
    public String img;
    public String phone;
    public String birthday;

    public PatientDTO(Map<String, Object> obj) {
        this.email = (String) obj.get("email");
        this.phone = (String) obj.get("phone");
        this.firstName = (String) obj.get("firstName");
        this.lastName = (String) obj.get("lastName");
    }
}
