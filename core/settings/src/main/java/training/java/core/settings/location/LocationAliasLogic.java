package training.java.core.settings.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.LocationAlias;
import training.java.core.settings.location.repository.LocationAliasRepository;

@Component
public class LocationAliasLogic {
  
  @Autowired
  private LocationAliasRepository aliasRepo;
  
  public LocationAlias getByCode( String code) {
    return aliasRepo.getByCode(code);
  }
  
  public LocationAlias save( LocationAlias refCode) {
    return aliasRepo.save(refCode);
  }
}