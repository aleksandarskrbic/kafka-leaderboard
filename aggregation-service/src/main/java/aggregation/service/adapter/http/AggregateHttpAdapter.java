package aggregation.service.adapter.http;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import aggregation.service.adapter.http.exception.UserNotFoundException;
import aggregation.service.domain.model.Aggregate;
import aggregation.service.domain.repository.AggregateRepository;

@RestController
@RequestMapping(path = "/api/aggregate")
@CrossOrigin(origins = "http://localhost:3000")
public class AggregateHttpAdapter {

    private final AggregateRepository aggregateRepository;

    public AggregateHttpAdapter(final AggregateRepository aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

    @GetMapping
    public List<Aggregate> findAll() {
        return aggregateRepository.findAll().stream().limit(100L).collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public Aggregate findByUsername(@PathVariable final String username) {
        return aggregateRepository.findByUsername(username)
            .getOrElseThrow(() -> new UserNotFoundException(String.format("Contributor with username: %s not found", username)));
    }
}

