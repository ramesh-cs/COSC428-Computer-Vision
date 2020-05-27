package nz.govt.stats.mdt.dl.localrepo;


import nz.govt.stats.mdt.dl.localrepo.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalRepository extends JpaRepository<Image, Long> {
    Image findByname(String name);

    @Query(value = "select * from Images where is_retrieved = 0 and country = :country and state = :state limit :limit", nativeQuery=true)
    List<Image> getImagesAreNotDownloadedByCountryAndState(@Param("limit") int limit, @Param("country") String country, @Param("state") String state);
}
