package mtt.webyte.repository;

import mtt.webyte.model.MedicalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalBillRepository extends JpaRepository<MedicalBill, Long> {
}

