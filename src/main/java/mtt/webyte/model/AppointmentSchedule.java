package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private String appointmentDate;

    @Column(name = "APPOINTMENT_TIME", nullable = false)
    private String appointmentTime;

    @Column(name = "APPOINTMENT_STATUS", nullable = false)
    private String appointmentStatus;

    @Column(name = "APPOINTMENT_NUMBER", nullable = false)
    private String appointmentNumber;

    @Column(name = "APPOINTMENT_TYPE", nullable = false)
    private String appointmentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DOCTOR_ID", nullable = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private Patient patient;

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
        sb.append(", doctor=").append(doctor);
        sb.append(", patient=").append(patient);
        sb.append(", department=").append(department);
        sb.append(", medicalBills=").append(medicalBills);
        sb.append('}');
        return sb.toString();
    }
}
