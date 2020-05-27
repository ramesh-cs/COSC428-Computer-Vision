package nz.govt.stats.mdt.dl.setstore;

import nz.govt.stats.mdt.dl.setstore.models.WorkItemFix;
import org.springframework.data.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@org.springframework.stereotype.Repository
public class WorkItemFixRepositoryBase implements Repository<WorkItemFix, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<WorkItemFix> findWorkitemsByLimit(int limit) {
        return entityManager.createQuery("SELECT p FROM WorkItemFix p",
                WorkItemFix.class).setMaxResults(limit).getResultList();
    }

    private int collectionInstanceId = 45;
    private String variableName = "overseas_state";
    private String altResponseValue = null;
    private String finalValue = "{\"code\":\"AU007%";

    public List<WorkItemFix> findWorkitemsByParameters(int collectionInstanceId, String variableName, String finalValue, long workItemId, int limit) {
        TypedQuery<WorkItemFix> query = entityManager.createQuery("SELECT p FROM WorkItemFix p WHERE p.collection_instance_id = :collectionInstanceId AND work_item_fix_id > :workItemId AND p.variable_name = :variableName AND p.alt_response_value is null AND p.final_value LIKE :finalValue ORDER BY p.work_item_fix_id ASC",
                WorkItemFix.class);
        query.setParameter("variableName", variableName);
        query.setParameter("finalValue", finalValue);
        query.setParameter("workItemId", workItemId);
        List<WorkItemFix> res = query.setMaxResults(limit).getResultList();
        return res;
    }

    public List<WorkItemFix> findWorkitemsByParameters(String variableName, String finalValue, long workItemId, int limit) {
//        TypedQuery<WorkItemFix> query = entityManager.createQuery("SELECT p FROM Collection c inner join CollectionInstance ci on c.collection_id=ci.collection_id inner join WorkItemFix p on ci.collection_instance_id=p.collection_instance_id WHERE c.collection_code='ITM' AND ci.collection_instance_code not in ('configuration','AllYearsData') AND work_item_fix_id > :workItemId AND p.variable_name = :variableName AND p.alt_response_value is null AND p.final_value LIKE :finalValue ORDER BY p.work_item_fix_id ASC",
//                WorkItemFix.class);
//        query.setParameter("variableName", variableName);
//        query.setParameter("finalValue", finalValue);
//        query.setParameter("workItemId", workItemId);
//        List<WorkItemFix> res = query.setMaxResults(limit).getResultList();
//        return res;
        TypedQuery<WorkItemFix> query = entityManager.createQuery("SELECT p FROM WorkItemFix p WHERE p.collection_instance_id IN (33,34,35,36,37,38,40,41,42,44,45,46,47,49,51,52,53,124,192,193,266,272,274) AND work_item_fix_id > :workItemId AND p.variable_name = :variableName AND p.alt_response_value is null AND p.final_value LIKE :finalValue ORDER BY p.work_item_fix_id ASC",
                WorkItemFix.class);
        query.setParameter("variableName", variableName);
        query.setParameter("finalValue", finalValue);
        query.setParameter("workItemId", workItemId);
        List<WorkItemFix> res = query.setMaxResults(limit).getResultList();
        return res;
    }
}
