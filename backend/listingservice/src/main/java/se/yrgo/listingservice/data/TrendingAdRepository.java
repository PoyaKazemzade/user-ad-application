package se.yrgo.listingservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.listingservice.domain.TrendingAdCategory;

import java.util.List;

@Repository
public interface TrendingAdRepository extends JpaRepository<TrendingAdCategory, Integer> {
    List<TrendingAdCategory> findByOrderByAdCountDesc();
}
