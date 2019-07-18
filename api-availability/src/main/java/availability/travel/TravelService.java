package availability.travel;

import availability.common.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Created by dami on 08/07/19.
 */
@Service
public class TravelService {

    private final TravelRepository travelRepository;
    private final TravelProducer travelProducer;
    private final TravelTranslator translator;
    private final TravelValidator validator;

    @Autowired
    public TravelService(TravelRepository travelRepository, TravelProducer travelProducer, TravelTranslator translator, TravelValidator validator) {
        this.travelRepository = travelRepository;
        this.travelProducer = travelProducer;
        this.translator = translator;
        this.validator = validator;
    }

    public TravelDto addTravel(Long id, TravelDto dto) throws Exception {
        validator.validate(id, dto);
//        TODO:DELETE THIS - MOCKING TIME
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime until = now.plusHours((int) (Math.random() * ((30 - 0) + 10)));

        Travel travel = travelRepository.save(Travel.newBuilder()
                .destiny(dto.getDestiny())
                .source(id)
                .arrivalTime(Optional.ofNullable(dto.getArrivalTime()).map(LocalDateTime::parse).orElse(now))
                .departureTime(Optional.ofNullable(dto.getDepartureTime()).map(LocalDateTime::parse).orElse(until))
                .duration(now.until(until, HOURS))
                .build());

        TravelDto travelDto = translator.toDto(travel);
        travelProducer.sendMessage(travelDto);
        return travelDto;
    }

    public List<TravelDto> fetchTravels(PageDto page) {
        return travelRepository.findAll(PageRequest.of(page.getPage(), page.getSize())).getContent()
                .stream()
                .map(travel -> translator.toDto(travel))
                .collect(Collectors.toList());
    }
}
