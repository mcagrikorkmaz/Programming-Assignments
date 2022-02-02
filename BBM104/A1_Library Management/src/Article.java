public class Article {
    private String id;
    private String name;
    private String publisherName;
    private String publishYear;
    private String fullID;

    public Article(String id, String name, String publisherName, String publishYear, String fullID) {
        this.id = id;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
        this.fullID = fullID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getFullID() {
        return fullID;
    }

    public void setFullID(String fullID) {
        this.fullID = fullID;
    }
}
