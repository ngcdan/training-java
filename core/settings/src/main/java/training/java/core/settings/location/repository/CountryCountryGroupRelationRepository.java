package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.java.core.settings.location.entity.CountryCountryGroupRelation;

import java.io.Serializable;

public interface CountryCountryGroupRelationRepository extends JpaRepository<CountryCountryGroupRelation, Serializable> {
  void deleteByCountryId(long countryId);
  void deleteByCountryGroupId(long countryGroupId);
  CountryCountryGroupRelation getRelationByCountryIdAndCountryGroupId(Long countryId, Long countryGroupId);
}