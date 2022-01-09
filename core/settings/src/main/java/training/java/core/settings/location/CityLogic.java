package training.java.core.settings.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.entity.City;
import training.java.core.settings.location.entity.State;
import training.java.core.settings.location.repository.CityRepository;

import java.util.List;

@Component
public class CityLogic {
  @Autowired
  private CityRepository cityRepo;

  public City getCity(String code) {
    return cityRepo.getByCode(code);
  }

  public City createCity(Country country, City city) {
    city.setCountryCode(country.getCode());
    return cityRepo.save(city);
  }

  
  public City createCity(State state, City city) {
    city.setStateCode(state.getCode());
    return cityRepo.save(city);
  }

  public City saveCity(City city) {
    return cityRepo.save(city);
  }

  public List<City> findCitiesInState(String stateCode) {
    return cityRepo.findCitiesInState(stateCode);
  }
}