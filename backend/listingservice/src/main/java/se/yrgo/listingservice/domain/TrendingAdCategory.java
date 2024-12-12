package se.yrgo.listingservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrendingAdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categoryName;
    private long adCount;

    public TrendingAdCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getAdCount() {
        return adCount;
    }

    public void setAdCount(long adCount) {
        this.adCount = adCount;
    }

    @Override
    public String toString() {
        return String.format("Category %s has %d ads.", categoryName, adCount);
    }
}
