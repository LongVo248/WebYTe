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

    @ManyToMany(mappedBy = "prescription")
    private Set<PrescriptionMedical> prescriptionMedicals = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "prescription")
    private Set<HealthRecord> healthRecords = new java.util.LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prescription{");
        sb.append("prescriptionId=").append(prescriptionId);
        sb.append('}');
        return sb.toString();
    }
}
