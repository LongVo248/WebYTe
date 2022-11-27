
package mtt.webyte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SicknessDTO extends AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long sickId;
    private String sickName;
    private String description;
    private Long typeSickId;
    private List<Long> symptomIds;
}
