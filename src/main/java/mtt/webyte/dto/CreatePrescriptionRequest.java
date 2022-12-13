
package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePrescriptionRequest extends AbstractDTO implements Serializable {
	private List<PrescriptionDTO> prescriptionDTOs;
	private List<SicknessDTO> sicknessDTOs;
	private Long appointmentId ;
}
