
package mtt.webyte.repository;

import mtt.webyte.model.HealthRecord;
import mtt.webyte.model.Medicine;
import mtt.webyte.model.Prescription;
import mtt.webyte.model.PrescriptionMedical;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescriptionMedicalRepository extends JpaRepository<PrescriptionMedical, Long> {
}
