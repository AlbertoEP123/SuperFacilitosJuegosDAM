package model;

public class MyGames {
	private String user;
	private String title;
    private String releaseDate;
    private String description;
    private String imageUrl;
    private String platforms;

    public MyGames() {}
    
    // Constructor
    public MyGames(String title, String releaseDate, String description, String imageUrl, String platforms) {
    	this.title = title;
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

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    /**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	// ToString method
    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", platforms=" + platforms +
                '}';
    }
}

