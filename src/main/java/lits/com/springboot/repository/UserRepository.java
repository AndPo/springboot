package lits.com.springboot.repository;

import lits.com.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByEmail(String email);

    User findById(Long id);
}
