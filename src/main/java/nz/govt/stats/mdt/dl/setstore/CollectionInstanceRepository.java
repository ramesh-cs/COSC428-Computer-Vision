package nz.govt.stats.mdt.dl.setstore;

import nz.govt.stats.mdt.dl.setstore.models.CollectionInstance;
import nz.govt.stats.mdt.dl.setstore.models.WorkItemFix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionInstanceRepository extends JpaRepository<CollectionInstance, Integer> {

    //collection_code='ITM' == collection_id = 14
    @Query(nativeQuery = true, value = "SELECT * FROM meta.collection_instance WHERE collection_id = 14 ORDER BY collection_instance_id ")
    List<CollectionInstance> findAllITMCollectionInstancesByQuery();
}
