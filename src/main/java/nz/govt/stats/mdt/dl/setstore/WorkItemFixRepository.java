package nz.govt.stats.mdt.dl.setstore;

import nz.govt.stats.mdt.dl.setstore.models.WorkItemFix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkItemFixRepository extends JpaRepository<WorkItemFix, Long> {

//    @Query(nativeQuery = true, value = "SELECT TOP 5 * FROM mi.work_item_fix WHERE collection_instance_id= :collectionCode \tAND variable_name= :varName AND alt_response_value is null AND final_value like Concat(CHAR(123),'\"code\":\"AU007%')")
    @Query(nativeQuery = true, value = "SELECT * FROM mi.work_item_fix WHERE collection_instance_id= :collectionCode \tAND variable_name= :varName AND alt_response_value is null AND final_value like :finalValue")
    List<WorkItemFix> findByQuery(@Param("collectionCode") Integer collectionCode, @Param("varName") String varName, @Param("finalValue") String finalValue);

    List<WorkItemFix>  findTop10By();
}
