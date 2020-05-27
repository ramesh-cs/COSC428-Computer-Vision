package nz.govt.stats.mdt.dl.setstore.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

//@Entity
public class ImageDAO {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private Timestamp retrieved;
    private Date create;
    private String imageUrl;

    ImageDAO() {
    }

    public ImageDAO(String name, String url, Timestamp retrievedDate) {
        this.name = name;
        this.imageUrl = url;
        this.retrieved = retrievedDate;
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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getRetrieved() {
        return retrieved;
    }

    public void setRetrieved(Timestamp retrieved) {
        this.retrieved = retrieved;
    }
}
