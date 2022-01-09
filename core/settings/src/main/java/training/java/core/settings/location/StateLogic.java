package training.java.core.settings.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.Country;
import training.java.core.settings.location.repository.CountryRepository;
import training.java.core.settings.location.entity.State;
import training.java.core.settings.location.repository.StateRepository;

import java.util.List;

@Component
public class StateLogic {
  @Autowired
  private StateRepository stateRepo;

  @Autowired
  private CountryRepository countryRepo;

  public State getState(String code) {
    return stateRepo.getByCode(code);
  }
 
  public State getStateByCodeAndCountryCode( String code, String countryCode) {
    return stateRepo.getStateByCodeAndCountryCode(code, countryCode);
  }

  public State createState(State state) {
    Country country = countryRepo.getByCode(state.getCountryCode());
    if(country == null) {
      String mesg = "Country does not exist, country " + state.getCountryCode() + ", state = " + state.getLabel();
      throw new IllegalArgumentException(mesg);
    }
    return stateRepo.save(state);
  }

  public State saveState(State state) {
    return stateRepo.save(state);
  }

  public List<State> findStatesInCountry( String countryCode) {
    return stateRepo.findStatesInCountry(countryCode);
  }
}