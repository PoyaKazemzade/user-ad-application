package se.yrgo.userservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.userservice.domain.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
