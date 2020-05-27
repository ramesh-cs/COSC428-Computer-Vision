package nz.govt.stats.mdt.dl.localrepo.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private String retrievedDate;
    @Column(name = "is_retrieved")
    private Boolean isRetrieved = false;
    @Column(name = "image_url")
    private String imageUrl;
    private String state;
    private String country;
    private String collection_instance_code;

    Image() {
    }

    public Image(String name, String url, String retrievedDate, String country, String state, String collection_instance_code) {
        this.name = name;
        this.imageUrl = url;
        this.retrievedDate = retrievedDate;
        this.country = country;
        this.state = state;
        this.collection_instance_code = collection_instance_code;
    }

    public String getCollection_instance_code() {
        return collection_instance_code;
    }

    public void setCollection_instance_code(String collection_instance_code) {
        this.collection_instance_code = collection_instance_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getRetrieved() {
        return isRetrieved;
    }

    public void setRetrieved(Boolean retrieved) {
        isRetrieved = retrieved;
    }

    public String getRetrievedDate() {
        return retrievedDate;
    }

    public void setRetrievedDate(String retrievedDate) {
        this.retrievedDate = retrievedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
