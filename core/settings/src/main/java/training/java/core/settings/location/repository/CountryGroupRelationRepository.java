package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.CountryGroupRelation;

import java.io.Serializable;

public interface CountryGroupRelationRepository extends JpaRepository<CountryGroupRelation, Serializable> {

  @Query("DELETE FROM CountryGroupRelation r WHERE r.countryId = :countryId")
  void deleteByCountryId(@Param("countryId") long countryId);

  @Query("DELETE FROM CountryGroupRelation r WHERE r.groupId = :groupId")
  void deleteByCountryGroupId(@Param("groupId") long groupId);

  @Query("SELECT r FROM CountryGroupRelation r WHERE r.groupId = :groupId AND r.countryId = :countryId")
  CountryGroupRelation getRelation(@Param("countryId") Long countryId, @Param("groupId") Long groupId);
}