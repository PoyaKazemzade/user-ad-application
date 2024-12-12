package se.yrgo.adservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.adservice.domain.AdCategory;

@Repository
public interface AdCategoryRepository extends JpaRepository<AdCategory, Integer> {
}
