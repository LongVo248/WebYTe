
package mtt.webyte.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddDoctorRequest extends AbstractDTO implements Serializable {
    private Long departmentId;
    private List<Long> doctorIds;
}
