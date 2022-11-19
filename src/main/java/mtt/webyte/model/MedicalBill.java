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
@Table(name = "T_MEDICAL_BILL")
public class MedicalBill extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BILL_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Long totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppointmentSchedule appointmentSchedule;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MedicalBill{");
        sb.append("billId='").append(billId).append('\'');
        sb.append(", totalPrice='").append(totalPrice).append('\'');
        sb.append('}');
        return sb.toString();
    }
}