package se.yrgo.listingservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class TrendingAdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 26)
    @Size(min = 2, max = 26)
    private String categoryName;
    @Column(nullable = false)
    private long adCount = 0;

    public TrendingAdCategory() {
    }

    public TrendingAdCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
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
