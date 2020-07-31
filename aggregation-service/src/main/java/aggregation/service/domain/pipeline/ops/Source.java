package aggregation.service.domain.pipeline.ops;

import io.vavr.collection.Stream;

public interface Source<R> {

    Stream<R> emit();
}
