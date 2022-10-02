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
@Table(name = "T_HEALTH_RECORD")
public class HealthRecord extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RECORD_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRESCRIPTION_ID", nullable = false)
    private Prescription prescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false)
    private AppointmentSchedule appointmentSchedule;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "T_HEALTH_RECORD_SICK_DETAIL",
            joinColumns = {
                @JoinColumn(name = "RECORD_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "SICKNESS_ID", nullable = false, updatable = false)})
    private Set<Sickness> sicknesses = new HashSet<>();


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HealthRecord{");
        sb.append("recordId='").append(recordId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
