package mtt.webyte.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mtt.webyte.model.Symptom;

@Repository
public interface SymptonRepository extends JpaRepository<Symptom, Long> {
	Set<Symptom> findBySymptomIdIn(List<Long> id);
}
