package aggregation.service.domain.pipeline.ops;

public class PipelineRunner<R> {

    private final Processor<R> processor;

    public PipelineRunner(final Processor<R> processor) {
        this.processor = processor;
    }

    public void loop() {
        while (true) {
            processor.process();
        }
    }
}
