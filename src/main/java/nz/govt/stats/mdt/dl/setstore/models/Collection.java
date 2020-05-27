package nz.govt.stats.mdt.dl.setstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "collection_instance", schema = "meta")
public class Collection {
    @Id
    @GeneratedValue
    int collection_id;
    Long collection_code;
    String collection_name;
    String metadata_reference;

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
    }

    public Long getCollection_code() {
        return collection_code;
    }

    public void setCollection_code(Long collection_code) {
        this.collection_code = collection_code;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    public String getMetadata_reference() {
        return metadata_reference;
    }

    public void setMetadata_reference(String metadata_reference) {
        this.metadata_reference = metadata_reference;
    }
}
