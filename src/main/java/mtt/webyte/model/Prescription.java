package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PRESCRIPTION")
public class Prescription extends AbstractAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRESCRIPTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @Column(name = "PRESCRIPTION_DATE", nullable = false)
    private String prescriptionDate;

    @Column(name = "PRESCRIPTION_TIME", nullable = false)
    private String prescriptionTime;

    @Column(name = "AMOUNT", nullable = false)
    private Long prescriptionAmount;

    @Column(name = "DOSAGE", nullable = false)
    private String prescriptionDosage;

    @Column(name = "DURATION", nullable = false)
    private String prescriptionDuration;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<Medicine> medicines = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "prescription")
    private Set<HealthRecord> healthRecords = new java.util.LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prescription{");
        sb.append("prescriptionId=").append(prescriptionId);
        sb.append(", prescriptionDate='").append(prescriptionDate).append('\'');
        sb.append(", prescriptionTime='").append(prescriptionTime).append('\'');
        sb.append(", prescriptionAmount=").append(prescriptionAmount);
        sb.append(", prescriptionDosage='").append(prescriptionDosage).append('\'');
        sb.append(", prescriptionDuration='").append(prescriptionDuration).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
