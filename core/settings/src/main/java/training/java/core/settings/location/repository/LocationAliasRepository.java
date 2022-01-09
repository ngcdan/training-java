package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training.java.core.settings.location.entity.LocationAlias;

import java.io.Serializable;
import java.util.List;

@Repository
public interface LocationAliasRepository extends JpaRepository<LocationAlias, Serializable> {
  LocationAlias getByCode(String code);

  @Query("SELECT lr FROM LocationAlias lr WHERE lr.id IN :ids")
  List<LocationAlias> findLocationAliases(@Param("ids") List<Long> ids);
}