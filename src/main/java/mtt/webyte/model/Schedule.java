package mtt.webyte.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_SCHEDULE")
public class Schedule extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SCHEDULE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name = "DAY_OF_WEEK", nullable = false)
    private String dayOfWeek;

    @Column(name = "START_TIME", nullable = false)
    private String startTime;

    @Column(name = "END_TIME", nullable = false)
    private String endTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule{");
        sb.append("scheduleId=").append(scheduleId);
        sb.append(", dayOfWeek='").append(dayOfWeek).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        //sb.append(", doctor=").append(doctor);
        sb.append('}');
        return sb.toString();
    }
}
