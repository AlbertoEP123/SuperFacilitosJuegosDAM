package model;

import java.util.List;

public class Games {
    private String title;
    private double averageRating;
    private String releaseDate;
    private String description;
    private String imageUrl;
    private List<String> platforms;

    // Constructor
    public Games(String title, double averageRating, String releaseDate, String description, String imageUrl, List<String> platforms) {
        this.title = title;
        this.averageRating = averageRating;
        this.releaseDate = releaseDate;
        this.description = description;
        this.imageUrl = imageUrl;
        this.platforms = platforms;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    // ToString method
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", averageRating=" + averageRating +
                ", releaseDate='" + releaseDate + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", platforms=" + platforms +
                '}';
    }
}

