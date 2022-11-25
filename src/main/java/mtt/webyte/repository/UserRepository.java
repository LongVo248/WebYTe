package mtt.webyte.repository;

import mtt.webyte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(@Param("email") String email);


    @Query("select u.userId "
            + " from User u"
            + " where u.email = :email ")
    Long getUserIdByEmail(@Param("email") String email);

    User findByUserId(Long userId);

    User findUserByUserId(Long userId);
}
