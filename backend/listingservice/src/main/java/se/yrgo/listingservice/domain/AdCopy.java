package se.yrgo.listingservice.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an ad. Copy of data from the Ad table of Ad Service.
 */
@Entity
public class AdCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String categoryName;
    private int price;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime created;

    public AdCopy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime createdDate) {
        this.created = createdDate;
    }

    @Override
    public String toString() {
        return "AdCopy{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", createdDate=" + created +
                '}';
    }
}
