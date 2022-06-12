package training.java.core.settings.location;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.java.core.settings.location.entity.*;

import java.util.List;

@Service
public class LocationService {

  @Autowired @Getter
  private CountryLogic countryLogic;

  @Autowired @Getter
  private StateLogic stateLogic;

  @Autowired @Getter
  private CityLogic cityLogic;

  @Autowired @Getter
  private CountryGroupLogic countryGroupLogic;

  @Autowired @Getter
  private LocationAliasLogic locationAliasLogic;

  @Autowired @Getter
  private LocationTypeLogic locationTypeLogic;

  @Autowired @Getter
  private LocationLogic locationLogic;

  //Country group
  public List<CountryGroup> findAllCountryGroups() {
    return countryGroupLogic.findAll();
  }

  @Transactional(readOnly = true)
  public CountryGroup getCountryGroup(String name) {
    return countryGroupLogic.getCountryGroup(name);
  }

  @Transactional
  public Boolean deleteCountryGroup(Long id) {
    return countryGroupLogic.deleteCountryGroup( id);
  }

  @Transactional(readOnly = true)
  public List<CountryGroup> findCountryGroupChildren(Long groupId) {
    return countryGroupLogic.findChildren(groupId);
  }

  @Transactional
  public CountryGroup saveCountryGroup(CountryGroup countryGroup) {
    return countryGroupLogic.saveCountryGroup(countryGroup);
  }

  @Transactional
  public CountryCountryGroupRelation createCountryGroupRelation(CountryGroup countryGroup, Country country) {
    return countryGroupLogic.createCountryGroupRelation(countryGroup, country);
  }

  @Transactional
  public CountryCountryGroupRelation createCountryGroupRelation(CountryCountryGroupRelation relation) {
    return countryGroupLogic.saveCountryGroupRelation(relation);
  }

  @Transactional
  public boolean createCountryGroupRelations(Long groupId, List<Long> countryIds) {
    return countryGroupLogic.createCountryGroupRelations(groupId, countryIds);
  }

  @Transactional
  public boolean deleteCountryGroupRelations(Long groupId, List<Long> countryIds) {
    return countryGroupLogic.deleteCountryGroupRelations(groupId, countryIds);
  }

  @Transactional(readOnly = true)
  public List<CountryGroup> findCountryGroups(Country country) {
    return countryGroupLogic.findCountryGroups(country);
  }

  //Country
  @Transactional(readOnly = true)
  public Country getCountry(String code) {
    return countryLogic.getCountry(code);
  }

  @Transactional
  public Country saveCountry(Country country) {
    return countryLogic.saveCountry(country);
  }

  @Transactional(readOnly = true)
  public List<Country> findAllCountries() {
    return countryLogic.findActiveCountries();
  }

  //State
  @Transactional(readOnly=true)
  public State getState(String code) {
    return stateLogic.getState(code);
  }

  @Transactional
  public State saveState(State state) {
    return stateLogic.saveState(state);
  }

  @Transactional
  public State createState(State state) {
    return stateLogic.createState(state);
  }

  @Transactional(readOnly = true)
  public List<State> findStatesInCountry(String countryCode) {
    return stateLogic.findStatesInCountry( countryCode);
  }

  // City
  @Transactional(readOnly = true)
  public City loadCity(String code) {
    return cityLogic.getCity(code);
  }

  @Transactional(readOnly = true)
  public List<City> findCitiesInState(String stateCode) {
    return cityLogic.findCitiesInState( stateCode);
  }

  @Transactional
  public City createCity(Country country, City city) {
    return cityLogic.createCity(country, city);
  }

  @Transactional
  public City createCity(State state, City city) {
    return cityLogic.createCity(state, city);
  }

  @Transactional
  public City saveCity(City city) {
    return cityLogic.saveCity(city);
  }

  //Location Type
  @Transactional(readOnly=true)
  public LocationType getLocationType(String type) {
    return locationTypeLogic.getLocationType(type);
  }

  @Transactional
  public LocationType saveLocationType(LocationType locationType) {
    return locationTypeLogic.save(locationType);
  }

  //Location Reference Code
  @Transactional
  public LocationAlias saveLocationAlias(LocationAlias alias) {
    return locationAliasLogic.save(alias);
  }

  //Location
  @Transactional(readOnly=true)
  public Location getLocation(String code) {
    return locationLogic.getLocation(code);
  }

  @Transactional
  public Location createLocation(City city, Location location) {
    return locationLogic.createLocation(city, location);
  }

  @Transactional
  public Location saveLocation(Location location) {
    return locationLogic.saveLocation(location);
  }

}