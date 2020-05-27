package nz.govt.stats.mdt.dl.setstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "work_item_fix", schema = "mi")
public class WorkItemFix {
    private @Id
    @GeneratedValue
    Long work_item_fix_id;
    private int collection_instance_id;
    private int seq_key;
    private String response_id;
    private String skill_type;
    private String intervention_type;
    private String fixed_by;
    private Date fixed_at;
    private String variable_name;
    private String original_value;
    private String final_value;
    private boolean is_escalated;
    private Integer work_item_id;
    private String alt_response_value;
    private String comment;
    private boolean is_suppressed;
    private int time_taken;
    private Integer replacement_id;
    private String operator_actions;
    private String related_data;
    private Integer fix_rationale_id;

    public Long getWork_item_fix_id() {
        return work_item_fix_id;
    }

    public void setWork_item_fix_id(Long work_item_fix_id) {
        this.work_item_fix_id = work_item_fix_id;
    }

    public int getCollection_instance_id() {
        return collection_instance_id;
    }

    public void setCollection_instance_id(int collection_instance_id) {
        this.collection_instance_id = collection_instance_id;
    }

    public int getSeq_key() {
        return seq_key;
    }

    public void setSeq_key(int seq_key) {
        this.seq_key = seq_key;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getSkill_type() {
        return skill_type;
    }

    public void setSkill_type(String skill_type) {
        this.skill_type = skill_type;
    }

    public String getIntervention_type() {
        return intervention_type;
    }

    public void setIntervention_type(String intervention_type) {
        this.intervention_type = intervention_type;
    }

    public String getFixed_by() {
        return fixed_by;
    }

    public void setFixed_by(String fixed_by) {
        this.fixed_by = fixed_by;
    }

    public Date getFixed_at() {
        return fixed_at;
    }

    public void setFixed_at(Date fixed_at) {
        this.fixed_at = fixed_at;
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public String getOriginal_value() {
        return original_value;
    }

    public void setOriginal_value(String original_value) {
        this.original_value = original_value;
    }

    public String getFinal_value() {
        return final_value;
    }

    public void setFinal_value(String final_value) {
        this.final_value = final_value;
    }

    public boolean isIs_escalated() {
        return is_escalated;
    }

    public void setIs_escalated(boolean is_escalated) {
        this.is_escalated = is_escalated;
    }

    public Integer getWork_item_id() {
        return work_item_id;
    }

    public void setWork_item_id(Integer work_item_id) {
        this.work_item_id = work_item_id;
    }

    public String getAlt_response_value() {
        return alt_response_value;
    }

    public void setAlt_response_value(String alt_response_value) {
        this.alt_response_value = alt_response_value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isIs_suppressed() {
        return is_suppressed;
    }

    public void setIs_suppressed(boolean is_suppressed) {
        this.is_suppressed = is_suppressed;
    }

    public int getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(int time_taken) {
        this.time_taken = time_taken;
    }

    public Integer getReplacement_id() {
        return replacement_id;
    }

    public void setReplacement_id(Integer replacement_id) {
        this.replacement_id = replacement_id;
    }

    public String getOperator_actions() {
        return operator_actions;
    }

    public void setOperator_actions(String operator_actions) {
        this.operator_actions = operator_actions;
    }

    public String getRelated_data() {
        return related_data;
    }

    public void setRelated_data(String related_data) {
        this.related_data = related_data;
    }

    public Integer getFix_rationale_id() {
        return fix_rationale_id;
    }

    public void setFix_rationale_id(Integer fix_rationale_id) {
        this.fix_rationale_id = fix_rationale_id;
    }

}
