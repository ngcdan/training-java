package training.java.core.settings.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.entity.CountryCountryGroupRelation;
import training.java.core.settings.location.entity.CountryGroup;
import training.java.core.settings.location.repository.CountryCountryGroupRelationRepository;
import training.java.core.settings.location.repository.CountryGroupRepository;
import training.java.core.settings.location.repository.CountryRepository;

import java.util.List;
import java.util.Objects;

@Component
public class CountryGroupLogic {

  @Autowired
  private CountryGroupRepository groupRepo;

  @Autowired
  private CountryRepository countryRepo;

  @Autowired
  private CountryCountryGroupRelationRepository groupRelationRepo;

  public String message() {
    return "Hello";
  }

  public CountryGroup getCountryGroup(String name) {
    return groupRepo.getByName(name);
  }

  public List<CountryGroup> findChildren(Long groupId) {
    if(groupId <= 0) groupId = null;
    if(groupId == null) return groupRepo.findRootChildren();
    return groupRepo.findChildren(groupId);
  }

  public CountryGroup saveCountryGroup(CountryGroup group) {
    if(group.isNew() && Objects.nonNull(group.getParentId())) {
      CountryGroup groupParent = groupRepo.getById(group.getParentId());
      if(Objects.nonNull(groupParent)) {
        group.withParent(groupParent);
      }
    }
    return groupRepo.save(group);
  }

  public CountryCountryGroupRelation createCountryGroupRelation(CountryGroup group,
                                                                Country country) {
    return groupRelationRepo.save(new CountryCountryGroupRelation(group, country));
  }

  public boolean createCountryGroupRelations(Long groupId, List<Long> countryIds) {
    CountryGroup group = this.groupRepo.getById(groupId);
    for(Long countryId : countryIds) {
      CountryCountryGroupRelation relation = new CountryCountryGroupRelation();
      relation.setCountryGroupId(groupId);
      relation.setCountryId(countryId);
      groupRelationRepo.save(relation);
    }
    return true;
  }

  public Boolean deleteCountryGroup(Long id) {
    CountryGroup group = groupRepo.getById(id);
    List<CountryGroup> children = groupRepo.findChildren(group.getId());
    if (children.size() > 0) {
      throw new IllegalArgumentException("Cannot delete  group " + group.getLabel() + ", that has the children");
    } else {
      groupRelationRepo.deleteByCountryGroupId(group.getId());
      groupRepo.delete(group);
    }
    return true;
  }

  public boolean deleteCountryGroupRelations(Long groupId, List<Long> countryIds) {
    CountryGroup group = this.groupRepo.getById(groupId);
    for(Long countryId : countryIds) {
      CountryCountryGroupRelation relation = groupRelationRepo.getRelationByCountryIdAndCountryGroupId(countryId, groupId);
      groupRelationRepo.delete(relation);
    }
    return true;
  }

  public CountryCountryGroupRelation saveCountryGroupRelation(CountryCountryGroupRelation relation) {
    return groupRelationRepo.save(relation);
  }

  public List<CountryGroup> findCountryGroups(Country country) {
    return groupRepo.findByCountry(country.getId());
  }

  public List<Country> findCountries(CountryGroup countryGroup) {
    return countryRepo.findByCountryGroup(countryGroup.getId());
  }

  public List<CountryGroup> findAll() {
    return groupRepo.findAll();
  }
}