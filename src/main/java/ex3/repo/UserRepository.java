package ex3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface of the UserRepository
 * hold the sql queries
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findFirst10ByOrderByNumOfSearchesDesc();
}
