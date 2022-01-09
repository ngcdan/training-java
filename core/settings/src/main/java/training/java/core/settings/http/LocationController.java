package training.java.core.settings.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import training.java.core.settings.location.LocationService;
import training.java.core.settings.location.entity.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/rest/v1.0.0/settings/location")
public class LocationController {

  @Autowired
  private LocationService service;

  @GetMapping("country/group/{code}")
  public @ResponseBody CountryGroup getCountryGroup(HttpSession session, @PathVariable("code") String code) {
    return service.getCountryGroup(code);
  }

  @GetMapping("country/group/{groupId}/children")
  public @ResponseBody List<CountryGroup> getCountryGroupChildren(HttpSession session, @PathVariable("groupId") Long groupId) {
    return service.findCountryGroupChildren(groupId);
  }

  @PutMapping("country/group")
  public @ResponseBody CountryGroup saveCountryGroup(HttpSession session, @RequestBody CountryGroup group) {
    return service.saveCountryGroup(group);
  }

  @DeleteMapping("country/group/{id}")
  public @ResponseBody Boolean deletePartnerGroup(HttpSession session, @PathVariable("id") Long id) {
    return service.deleteCountryGroup(id);
  }

  @PutMapping("country/group/{groupId}/relations")
  public @ResponseBody boolean createCountryGroupRelations(
    HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<Long> countryIds) {
    service.createCountryGroupRelations(groupId, countryIds);
    return true;
  }

  @DeleteMapping("country/group/{groupId}/relations")
  public @ResponseBody boolean deleteCountryGroupRelations(
    HttpSession session, @PathVariable("groupId") Long groupId, @RequestBody List<Long> countryIds) {
    service.deleteCountryGroupRelations(groupId, countryIds);
    return true;
  }

  @GetMapping("country/{code}")
  public @ResponseBody Country loadCountry(
    HttpSession session, @PathVariable("code") String code) {
    return service.getCountry(code);
  }

  @GetMapping("country/{code}/states")
  public @ResponseBody List<State> findStatesInCountry(HttpSession session, @PathVariable("code") String code) {
    return service.findStatesInCountry(code);
  }

  @PutMapping("country")
  public @ResponseBody Country saveCountry(HttpSession session, @RequestBody Country country) {
    return service.saveCountry(country);
  }

  @GetMapping("country/all")
  public @ResponseBody List<Country> findAllCountries(HttpSession session) {
    return service.findAllCountries();
  }

  @GetMapping("state/{code}")
  public @ResponseBody State loadState(HttpSession session, @PathVariable("code") String code) {
    return service.getState(code);
  }

  @GetMapping("state/{code}/cities")
  public @ResponseBody List<City> fincCitiesInState(HttpSession session, @PathVariable("code") String code) {
    return service.findCitiesInState(code);
  }

  @PutMapping("state")
  public @ResponseBody State saveState(HttpSession session, @RequestBody State state) {
    return service.createState(state);
  }

  @GetMapping("city/{code}")
  public @ResponseBody City loadCity(HttpSession session, @PathVariable("code") String code) {
    return service.loadCity(code);
  }

  @PutMapping("city")
  public @ResponseBody City saveCityModel(HttpSession session, @RequestBody City city) {
    return service.saveCity(city);
  }

  @GetMapping("{code}")
  public @ResponseBody Location loadLocation(HttpSession session, @PathVariable("code") String code){
    return service.getLocation(code);
  }

  @PutMapping
  public @ResponseBody Location saveLocationModel(HttpSession session, @RequestBody Location location) {
    return service.saveLocation(location);
  }

  @PutMapping("type")
  public @ResponseBody LocationType saveLocationType(HttpSession session, @RequestBody LocationType locationType) {
    return service.saveLocationType(locationType);
  }

  @PutMapping("alias")
  public @ResponseBody LocationAlias saveLocationAlias(HttpSession session, @RequestBody LocationAlias alias) {
    return service.saveLocationAlias(alias);
  }
}