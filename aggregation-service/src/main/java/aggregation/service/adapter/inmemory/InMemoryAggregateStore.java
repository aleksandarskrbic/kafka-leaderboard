package aggregation.service.adapter.inmemory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import aggregation.service.domain.model.Aggregate;
import aggregation.service.domain.repository.AggregateRepository;
import io.vavr.control.Option;

@Repository
public class InMemoryAggregateStore implements AggregateRepository {

    private final Map<String, Aggregate> store = new ConcurrentHashMap<>();

    @Override
    public List<Aggregate> findAll() {
        return store.values()
            .stream()
            .sorted(Comparator.comparing(Aggregate::getPoints).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public Option<Aggregate> findByUsername(final String username) {
        return Option.of(store.get(username));
    }

    @Override
    public Aggregate update(final Aggregate aggregate) {
        store.put(aggregate.getUsername(), aggregate);
        return aggregate;
    }
}
