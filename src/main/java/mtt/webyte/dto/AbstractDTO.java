package mtt.webyte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO extends AbstractNonAuditDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String createdBy;
    protected Date createdDate;
    protected String modifiedBy;
    protected Date modifiedDate;
}
