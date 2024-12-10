package se.yrgo.listingservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.listingservice.domain.AdCopy;

import java.util.List;

@Repository
public interface AdCopyRepository extends JpaRepository<AdCopy, Integer> {
    List<AdCopy> findByCategoryName(String categoryName);
}
