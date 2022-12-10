package mtt.webyte.dto;

import java.io.Serializable;

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
public class MedicineDTO extends AbstractDTO implements Serializable {
    private Long medicineId;
    private String medicineName;
    private String medicineType;
    private String medicineDescription;
    private Long medicinePrice;
    private Long medicineQuantity;
    private String medicineStatus;
    private String medicinePack;
}
