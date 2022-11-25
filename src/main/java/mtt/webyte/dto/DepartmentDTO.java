package mtt.webyte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO extends AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long departmentId;
    private String departmentName;
}
