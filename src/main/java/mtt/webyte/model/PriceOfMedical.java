package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PRICE_OF_MEDICAL")
public class PriceOfMedical extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRICE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
