package mtt.webyte.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mtt.webyte.model.Sickness;


@Repository
public interface SicknessRepository extends JpaRepository<Sickness, Long> {		
	Page<Sickness> findBySickNameContaining(String sickName, Pageable pageable);
}
