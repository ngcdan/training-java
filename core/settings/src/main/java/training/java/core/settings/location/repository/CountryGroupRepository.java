package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.CountryGroup;

import java.io.Serializable;
import java.util.List;

public interface CountryGroupRepository extends JpaRepository<CountryGroup, Serializable> {
  CountryGroup getByName(String name);
  
  @Query("SELECT cg FROM CountryGroup cg WHERE cg.parentId is Null ORDER BY cg.label ASC")
  List<CountryGroup> findRootChildren();
  
  @Query("SELECT cg FROM CountryGroup cg WHERE cg.parentId = :parentId ORDER BY cg.label ASC")
  List<CountryGroup> findChildren(@Param("parentId") long parentId);
  
  @Query("SELECT cg FROM CountryGroup cg, CountryCountryGroupRelation r WHERE cg.id = r.countryGroupId AND r.countryId = :countryId")
  List<CountryGroup> findByCountry(@Param("countryId") long countryId);
}