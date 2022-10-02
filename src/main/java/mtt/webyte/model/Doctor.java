package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_DOCTOR")
public class Doctor extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOCTOR_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "DOCTOR_NAME", nullable = false)
    private String doctorName;

    @Column(name ="ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private String accountId;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;
    @Column(name = "IMAGE", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<PriceOfMedical> priceOfMedicalList = new HashSet<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Schedule> schedules = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("doctorId=").append(doctorId);
        sb.append(", doctorName='").append(doctorName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accountId='").append(accountId).append('\'');
        sb.append(", birthDate='").append(birthDate).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }
}
