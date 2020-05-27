package nz.govt.stats.mdt.dl.localrepo.models;

import javax.persistence.*;

@Entity
@Table(name = "workItem_meta")
public class WorkItemMeta {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) int id;
    private Long last_retrieved_work_item_id;
    private int no_records_last_retrieved;
    private String last_retrieved_date;
    private String country;
    private String state;

    public WorkItemMeta () {}

    public WorkItemMeta(Long last_retrieved_work_item_id, int no_records_last_retrieved, String country, String state) {
        this.last_retrieved_work_item_id = last_retrieved_work_item_id;
        this.no_records_last_retrieved = no_records_last_retrieved;
        this.country = country;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getLast_retrieved_work_item_id() {
        return last_retrieved_work_item_id;
    }

    public void setLast_retrieved_work_item_id(Long last_retrieved_work_item_id) {
        this.last_retrieved_work_item_id = last_retrieved_work_item_id;
    }

    public int getNo_records_last_retrieved() {
        return no_records_last_retrieved;
    }

    public void setNo_records_last_retrieved(int no_records_last_retrieved) {
        this.no_records_last_retrieved = no_records_last_retrieved;
    }

    public String getLast_retrieved_date() {
        return last_retrieved_date;
    }

    public void setLast_retrieved_date(String last_retrieved_date) {
        this.last_retrieved_date = last_retrieved_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
