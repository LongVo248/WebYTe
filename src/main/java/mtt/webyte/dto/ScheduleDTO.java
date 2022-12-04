package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleDTO extends AbstractDTO implements Serializable {
    private Long scheduleId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Long userId;
}
