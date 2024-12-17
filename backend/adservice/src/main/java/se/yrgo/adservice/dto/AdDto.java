package se.yrgo.adservice.dto;

public class AdDto {
    private String userName;
    private String title;
    private String description;
    private Integer price;
    private Integer categoryId;

    public AdDto(String userName, String title, String description, Integer price, Integer categoryId) {
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
