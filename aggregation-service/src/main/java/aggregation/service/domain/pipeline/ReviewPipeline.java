package aggregation.service.domain.pipeline;

import org.springframework.stereotype.Service;
import aggregation.service.domain.model.Aggregate;
import aggregation.service.domain.pipeline.ops.Processor;
import aggregation.service.domain.pipeline.ops.Source;
import aggregation.service.domain.repository.AggregateRepository;
import avro.events.ReviewCreated;
import io.vavr.control.Option;

@Service
public class ReviewPipeline implements Processor<ReviewCreated> {

    private final Source<ReviewCreated> source;
    private final AggregateRepository aggregateRepository;

    public ReviewPipeline(final Source<ReviewCreated> source, final AggregateRepository aggregateRepository) {
        this.source = source;
        this.aggregateRepository = aggregateRepository;
    }

    @Override
    public void process() {
        source.emit().forEach(review ->
            aggregateRepository.findByUsername(review.getReviewer())
                .map(aggregate -> {
                    final Aggregate newAggregate = aggregate.update(10L);
                    return aggregateRepository.update(newAggregate);
                })
                .orElse(() -> {
                    final Aggregate aggregate = new Aggregate(
                        review.getReviewer(),
                        null,
                        null,
                        10L
                    );
                    return Option.of(aggregateRepository.update(aggregate));
                })
        );
    }
}
