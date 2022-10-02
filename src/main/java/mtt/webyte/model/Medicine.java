package mtt.webyte.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_MEDICINE")
public class Medicine extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MEDICINE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;

    @Column(name = "MEDICINE_NAME", nullable = false)
    private String medicineName;

    @Column(name = "MEDICINE_TYPE", nullable = false)
    private String medicineType;

    @Column(name = "MEDICINE_DESCRIPTION", nullable = false)
    private String medicineDescription;

    @Column(name = "MEDICINE_PRICE", nullable = false)
    private Long medicinePrice;

    @Column(name = "MEDICINE_QUANTITY", nullable = false)
    private Long medicineQuantity;

    @Column(name="MEDICINE_STATUS", nullable = false)
    private String medicineStatus;

    @Column(name = "MEDICINE_PACK", nullable = false)
    private String medicinePack;

    @ManyToMany
    @JoinTable(name = "T_PRESCRIPTION_MEDICAL",
            joinColumns = @JoinColumn(name = "MEDICINE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRESCRIPTION_ID"))
    private Set<Prescription> prescriptions = new HashSet<>();


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medicine{");
        sb.append("medicineId=").append(medicineId);
        sb.append(", medicineName='").append(medicineName).append('\'');
        sb.append(", medicineType='").append(medicineType).append('\'');
        sb.append(", medicineDescription='").append(medicineDescription).append('\'');
        sb.append(", medicinePrice=").append(medicinePrice);
        sb.append(", medicineQuantity=").append(medicineQuantity);
        sb.append(", medicineStatus='").append(medicineStatus).append('\'');
        sb.append(", medicinePack='").append(medicinePack).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
