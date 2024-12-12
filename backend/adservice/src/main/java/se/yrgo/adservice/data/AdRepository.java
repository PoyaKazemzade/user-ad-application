package se.yrgo.adservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.adservice.domain.Ad;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findByCategory_Id(Integer categoryId);
}