
package mtt.webyte.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mtt.webyte.model.HealthRecord;
import mtt.webyte.model.Prescription;
import mtt.webyte.model.Question;
import mtt.webyte.model.Topic;
import mtt.webyte.model.TypeSick;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
