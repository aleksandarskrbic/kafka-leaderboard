package aggregation.service.domain.pipeline;

import org.springframework.stereotype.Service;
import aggregation.service.domain.model.Aggregate;
import aggregation.service.domain.pipeline.ops.Processor;
import aggregation.service.domain.pipeline.ops.Source;
import aggregation.service.domain.repository.AggregateRepository;
import avro.events.CommentCreated;
import io.vavr.control.Option;

@Service
public class CommentPipeline implements Processor<CommentCreated> {

    private final Source<CommentCreated> source;
    private final AggregateRepository aggregateRepository;

    public CommentPipeline(final Source<CommentCreated> source, final AggregateRepository aggregateRepository) {
        this.source = source;
        this.aggregateRepository = aggregateRepository;
    }

    @Override
    public void process() {
        source.emit().forEach(comment ->
            aggregateRepository.findByUsername(comment.getUser())
                .map(aggregate -> {
                    final Aggregate newAggregate = aggregate.update(5L);
                    return aggregateRepository.update(newAggregate);
                })
                .orElse(() -> {
                    final Aggregate aggregate = new Aggregate(
                        comment.getUser(),
                        null,
                        null,
                        5L
                    );
                    return Option.of(aggregateRepository.update(aggregate));
                })
        );
    }
}
