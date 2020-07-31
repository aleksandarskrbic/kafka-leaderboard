package aggregation.service.domain.repository;

import java.util.List;
import aggregation.service.domain.model.Aggregate;
import io.vavr.control.Option;

public interface AggregateRepository {

    List<Aggregate> findAll();

    Option<Aggregate> findByUsername(final String username);

    Aggregate update(final Aggregate aggregate);
}
