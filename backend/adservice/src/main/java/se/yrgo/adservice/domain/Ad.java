package se.yrgo.adservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Ad")
public class Ad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 60)
    @Size(min = 2, max = 60)
    private String userName;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    @Min(1)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private AdCategory category;

    public Ad() {
    }

    public Ad(Integer id, String userName, AdCategory category, String title, String description, LocalDateTime created, Integer price) {
        this.id = id;
        this.userName = userName;
        this.category = category;
        this.title = title;
        this.description = description;
        this.created = created;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AdCategory getCategory() {
        return category;
    }

    public void setCategory(AdCategory category) {
        this.category = category;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}