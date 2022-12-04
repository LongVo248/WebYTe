package mtt.webyte.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceOfMedicalDTO extends AbstractDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long priceId;
    private Long price;
    private Long userId;
}
