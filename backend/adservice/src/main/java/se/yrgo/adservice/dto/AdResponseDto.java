package se.yrgo.adservice.dto;

import se.yrgo.adservice.domain.Ad;

import java.time.LocalDateTime;

public class AdResponseDto {
    private Integer id;
    private String userName;
    private String title;
    private String description;
    private LocalDateTime created;
    private Integer price;
    private String categoryName;

    public AdResponseDto(Ad ad) {
        this.id = ad.getId();
        this.userName = ad.getUserName();
        this.title = ad.getTitle();
        this.description = ad.getDescription();
        this.created = ad.getCreated();
        this.price = ad.getPrice();
        this.categoryName = ad.getCategory().getCategoryName();
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

