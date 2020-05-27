package nz.govt.stats.mdt.dl.setstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "collection", schema = "meta")
public class CollectionInstance {
    @Id
    @GeneratedValue
    int collection_instance_id;
    int collection_id;
    String collection_instance_code;
    String collection_instance_name;
    String metadata_reference;
    String start_date;
    String end_date;

    public int getCollection_instance_id() {
        return collection_instance_id;
    }

    public void setCollection_instance_id(int collection_instance_id) {
        this.collection_instance_id = collection_instance_id;
    }

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
    }

    public String getCollection_instance_code() {
        return collection_instance_code;
    }

    public void setCollection_instance_code(String collection_instance_code) {
        this.collection_instance_code = collection_instance_code;
    }

    public String getCollection_instance_name() {
        return collection_instance_name;
    }

    public void setCollection_instance_name(String collection_instance_name) {
        this.collection_instance_name = collection_instance_name;
    }

    public String getMetadata_reference() {
        return metadata_reference;
    }

    public void setMetadata_reference(String metadata_reference) {
        this.metadata_reference = metadata_reference;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
