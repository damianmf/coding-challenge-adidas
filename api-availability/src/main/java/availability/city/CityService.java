package availability.city;

import availability.common.PageDto;
import availability.exception.AdidasApiException;
import availability.travel.TravelDto;
import availability.travel.TravelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dami on 08/07/19.
 */
@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    private final CityRepository repository;
    private final TravelService travelService;
    private final CityTranslator translator;

    @Autowired
    public CityService(CityRepository repository, TravelService travelService, CityTranslator translator) {
        this.repository = repository;
        this.travelService = travelService;
        this.translator = translator;
    }

    public CityDto get(Long id) throws Exception {
        City city = repository.findById(id)
                .orElseThrow(() -> new AdidasApiException(String.format("City could not be found %d", id)));
        return translator.toDto(city);
    }

    public CityDto save(CityDto city) {
        return translator.toDto(repository.save(translator.toDomain(city)));
    }

    public List<CityDto> get(PageDto page) {
        return repository.findAll(PageRequest.of(page.getPage(), page.getSize())).getContent()
                .stream()
                .map(translator::toDto)
                .collect(Collectors.toList());
    }

    public CityDto getByName(String id) throws AdidasApiException {
        City city = repository.findByName(id)
                .orElseThrow(() -> new AdidasApiException(String.format("City could not be found %d", id)));
        return translator.toDto(city);
    }

    public void init() throws Exception {
        CityDto bue = this.save(CityDto.newBuilder().name("BUE").build());
        CityDto mad = this.save(CityDto.newBuilder().name("MAD").build());
        CityDto bar = this.save(CityDto.newBuilder().name("BAR").build());
        CityDto cor = this.save(CityDto.newBuilder().name("COR").build());
        CityDto ros = this.save(CityDto.newBuilder().name("ROS").build());
        CityDto juj = this.save(CityDto.newBuilder().name("JUJ").build());
        CityDto net = this.save(CityDto.newBuilder().name("NET").build());

        travelService.addTravel(bue.getId(), TravelDto.newBuilder().source(bue.getId()).destiny(mad.getId()).build());
        travelService.addTravel(bue.getId(), TravelDto.newBuilder().source(bue.getId()).destiny(bar.getId()).build());
        travelService.addTravel(bar.getId(), TravelDto.newBuilder().source(bar.getId()).destiny(mad.getId()).build());

        travelService.addTravel(bue.getId(), TravelDto.newBuilder().source(bue.getId()).destiny(cor.getId()).build());
        travelService.addTravel(cor.getId(), TravelDto.newBuilder().source(cor.getId()).destiny(mad.getId()).build());
        travelService.addTravel(ros.getId(), TravelDto.newBuilder().source(ros.getId()).destiny(mad.getId()).build());
        travelService.addTravel(cor.getId(), TravelDto.newBuilder().source(cor.getId()).destiny(ros.getId()).build());
        travelService.addTravel(bar.getId(), TravelDto.newBuilder().source(bar.getId()).destiny(ros.getId()).build());
        travelService.addTravel(ros.getId(), TravelDto.newBuilder().source(ros.getId()).destiny(bue.getId()).build());
        travelService.addTravel(cor.getId(), TravelDto.newBuilder().source(cor.getId()).destiny(juj.getId()).build());
        travelService.addTravel(juj.getId(), TravelDto.newBuilder().source(juj.getId()).destiny(net.getId()).build());

    }
}
