package training.java.core.settings.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.entity.Location;
import training.java.core.settings.location.entity.City;
import training.java.core.settings.location.entity.State;
import training.java.core.settings.location.repository.LocationRepository;

@Component
public class LocationLogic {
  @Autowired
  private LocationRepository locationRepo;
  
  public Location getLocation(String code) {
    return locationRepo.getByCode(code);
  }
  
  public Location getByCodeAndCountryCode(String code, String countryCode) {
    return locationRepo.getByCodeAndCountryCode(code, countryCode);
  }
  
  public Location createLocation( City city, Location location) {
    location.withCity(city);
    return locationRepo.save(location);
  }
  
  public Location createLocation(State state, Location location) {
    location.setStateCode(state.getCode());
    return locationRepo.save(location);
  }
  
  public Location createLocation(Country country, Location location) {
    location.setCountryCode(country.getCode());
    return locationRepo.save(location);
  }
  
  public Location saveLocation(Location location) {
    return locationRepo.save(location);
  }
  
}