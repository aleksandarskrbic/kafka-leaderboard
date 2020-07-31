package aggregation.service.domain.pipeline.ops;

public interface PipelineRunnerFactory {

    static <R> PipelineRunner<R> create(final Processor<R> processor) {
        return new PipelineRunner<>(processor);
    }
}
