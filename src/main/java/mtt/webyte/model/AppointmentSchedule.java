package mtt.webyte.model;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_APPOINTMENT_SCHEDULE")
public class AppointmentSchedule extends AbstractAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APPOINTMENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Nationalized
    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private Date appointmentDate;

    @Nationalized
    @Column(name = "APPOINTMENT_TIME", nullable = false)
    private String appointmentTime;

    @Nationalized
    @Column(name = "APPOINTMENT_STATUS", nullable = false)
    private String appointmentStatus;

    @Nationalized
    @Column(name = "APPOINTMENT_NUMBER", nullable = false)
    private String appointmentNumber;

    @Nationalized
    @Column(name = "APPOINTMENT_TYPE", nullable = false)
    private String appointmentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "appointmentSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalBill> medicalBills = new HashSet<>();

    @OneToMany(mappedBy = "appointmentSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HealthRecord> healthRecords = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppointmentSchedule{");
        sb.append("appointmentId=").append(appointmentId);
        sb.append(", appointmentDate='").append(appointmentDate).append('\'');
        sb.append(", appointmentTime='").append(appointmentTime).append('\'');
        sb.append(", appointmentStatus='").append(appointmentStatus).append('\'');
        sb.append(", appointmentNumber='").append(appointmentNumber).append('\'');
        sb.append(", appointmentType='").append(appointmentType).append('\'');
        sb.append(", user=").append(user);
        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }
}
