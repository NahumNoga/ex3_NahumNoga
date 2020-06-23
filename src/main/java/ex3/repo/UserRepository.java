package ex3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findFirst10ByOrderByNumOfSearchesDesc();
}
