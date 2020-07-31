package aggregation.service;

import org.springframework.stereotype.Service;
import aggregation.service.domain.pipeline.ops.PipelineManager;

@Service
public class ApplicationRunner {

    private final PipelineManager pipelineManager;

    public ApplicationRunner(final PipelineManager pipelineManager) {
        this.pipelineManager = pipelineManager;
    }

    public void run() {
        pipelineManager.startPipelines();
    }
}
