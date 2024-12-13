package se.yrgo.listingservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Represents an ad. Copy of data from the Ad table of Ad Service.
 */
@Entity
public class AdCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false, length = 26)
    @Size(min = 2, max = 26)
    private String categoryName;
    @Column(nullable = false)
    @Min(1)
    private int price;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
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
