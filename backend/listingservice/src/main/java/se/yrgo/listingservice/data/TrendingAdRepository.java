package se.yrgo.listingservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrendingAdRepository extends JpaRepository<TrendingAdCategory, Integer> {
    List<TrendingAdCategory> findTop3ByOrderByAdCountDesc();
    Optional<TrendingAdCategory> findByCategoryName(String categoryName);
}
