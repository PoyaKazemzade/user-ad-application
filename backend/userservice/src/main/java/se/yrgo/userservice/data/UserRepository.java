package se.yrgo.userservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.userservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
