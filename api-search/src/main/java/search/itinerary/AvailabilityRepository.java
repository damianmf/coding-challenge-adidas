package search.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AvailabilityRepository {
    @Value("${adidas.challenge.city.server}")
    private String availabilityUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public AvailabilityRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CityDto getCityByName(String source) {
        return restTemplate.getForObject(getUrlCityByName(source), CityDto.class);
    }

    public CityDto getCityById(Long id) {
        return restTemplate.getForObject(getUrl(id), CityDto.class);
    }

    private String getUrl(Long cityId) {
        return "http://" + availabilityUrl + "/city/" + cityId.toString();
    }
    private String getUrlCityByName(String cityId) {
        return "http://" + availabilityUrl + "/city-name/" + cityId;
    }

}
