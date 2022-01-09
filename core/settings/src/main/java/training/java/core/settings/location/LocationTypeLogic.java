package training.java.core.settings.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import training.java.core.settings.location.entity.LocationType;
import training.java.core.settings.location.repository.LocationTypeRepository;

@Component
public class LocationTypeLogic {
  
  @Autowired
  private LocationTypeRepository locationTypeRepo;
  
  public LocationType getLocationType(String type) {
    return locationTypeRepo.getByType(type);
  }
  
  public LocationType save( LocationType locationType) {
    return locationTypeRepo.save(locationType);
  }
}