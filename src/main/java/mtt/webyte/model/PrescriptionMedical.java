
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
@Table(name = "T_PRESCRIPTION_MEDICAL")
public class PrescriptionMedical extends AbstractAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRESCRIPTION_MEDICAL_ID", nullable = false)
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

    @ManyToOne
    @JoinColumn(name="MEDICINE_ID", nullable = false)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name="PRESCRIPTION_ID", nullable = false)
    private Prescription prescription;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prescription{");
        sb.append("prescriptionId=").append(prescriptionId);
        sb.append(", prescriptionDate='").append(prescriptionDate).append('\'');
        sb.append(", prescriptionTime='").append(prescriptionTime).append('\'');
        sb.append(", prescriptionAmount=").append(prescriptionAmount);
        sb.append(", prescriptionDosage='").append(prescriptionDosage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
