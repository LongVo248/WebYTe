package mtt.webyte.repository;

import mtt.webyte.model.Department;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Page<Department> findByDepartmentNameContaining(String departmentName, Pageable pageable);
	Set<Department> findByDepartmentIdIn(List<Long> departmentIdList);
}
