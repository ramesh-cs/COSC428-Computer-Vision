package nz.govt.stats.mdt.dl.localrepo;

import nz.govt.stats.mdt.dl.localrepo.models.Image;
import nz.govt.stats.mdt.dl.localrepo.models.WorkItemMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkItemMetaRepository extends JpaRepository<WorkItemMeta, Integer> {

    @Query("select w from WorkItemMeta w where w.country=:country and w.state=:state")
    WorkItemMeta findFirstBycountryAndstate(@Param("country") String country, @Param("state") String state);

    @Transactional
    @Modifying
    @Query("delete from WorkItemMeta f where f.country=:country and f.state=:state")
    void deleteByCountryAndState(@Param("country") String country, @Param("state") String state);

}
