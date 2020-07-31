package aggregation.service.domain.pipeline.ops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;
import avro.events.CommentCreated;
import avro.events.PullRequestCreated;
import avro.events.ReviewCreated;

@Service
public class PipelineManager {

    private final ExecutorService executorService;
    private final Processor<CommentCreated> commentProcessor;
    private final Processor<PullRequestCreated> pullRequestProcessor;
    private final Processor<ReviewCreated> reviewProcessor;

    public PipelineManager(
        final Processor<CommentCreated> commentProcessor,
        final Processor<PullRequestCreated> pullRequestProcessor,
        final Processor<ReviewCreated> reviewProcessor
    ) {
        this.executorService = Executors.newFixedThreadPool(3);
        this.commentProcessor = commentProcessor;
        this.pullRequestProcessor = pullRequestProcessor;
        this.reviewProcessor = reviewProcessor;
    }

    public void startPipelines() {
        executorService.submit(() -> PipelineRunnerFactory.create(commentProcessor).loop());
        executorService.submit(() -> PipelineRunnerFactory.create(pullRequestProcessor).loop());
        executorService.submit(() -> PipelineRunnerFactory.create(reviewProcessor).loop());
    }
}
