package mtt.webyte.repository;

import mtt.webyte.enums.RoleType;
import mtt.webyte.model.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);

    User findByEmail(String email);

    Long getUserIdByEmail(String email);
    User findByUserId(Long userId);

    User findUserByUserId(Long userId);

	Set<User> findByUserIdInAndRole(List<Long> userIdList, RoleType role);

	Page<User> findByUserFNameContainingOrUserLNameContainingAndRole(String userFName, String userLName, RoleType role, Pageable pageable);

	Set<User> findAllByRole(RoleType role);
}
