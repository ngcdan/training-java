package training.java.core.settings.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.repository.CountryRepository;

import java.util.List;

@Component
public class CountryLogic {
  @Autowired
  private CountryRepository countryRepo;

  public Country getCountry(String code) {
    return countryRepo.getByCode(code);
  }

  public Country saveCountry(Country country) {
    return countryRepo.save(country);
  }

  public List<Country> findActiveCountries() {
    return countryRepo.findAll();
  }
}