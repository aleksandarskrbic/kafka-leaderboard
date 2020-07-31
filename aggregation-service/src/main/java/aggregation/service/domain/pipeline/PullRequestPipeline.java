package aggregation.service.domain.pipeline;

import org.springframework.stereotype.Service;
import aggregation.service.domain.model.Aggregate;
import aggregation.service.domain.pipeline.ops.Processor;
import aggregation.service.domain.pipeline.ops.Source;
import aggregation.service.domain.repository.AggregateRepository;
import avro.events.PullRequestCreated;
import io.vavr.control.Option;

@Service
public class PullRequestPipeline implements Processor<PullRequestCreated> {

    private final Source<PullRequestCreated> source;
    private final AggregateRepository aggregateRepository;

    public PullRequestPipeline(final Source<PullRequestCreated> source, final AggregateRepository aggregateRepository) {
        this.source = source;
        this.aggregateRepository = aggregateRepository;
    }

    @Override
    public void process() {
        source.emit().map(PullRequestCreated::getUser).forEach(user ->
            aggregateRepository.findByUsername(user.getUsername())
                .map(aggregate -> {
                    final Aggregate newAggregate = new Aggregate(
                        user.getUsername(),
                        user.getUrl(),
                        user.getAvatarUrl(),
                        aggregate.getPoints() + 100L
                    );
                    return aggregateRepository.update(newAggregate);
                })
                .orElse(() -> {
                    final Aggregate aggregate = new Aggregate(
                        user.getUsername(),
                        user.getUrl(),
                        user.getAvatarUrl(),
                        100L
                    );
                    return Option.of(aggregateRepository.update(aggregate));
                })
        );
    }
}
