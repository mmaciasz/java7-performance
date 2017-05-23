package pl.com.pollub;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.collections.CollectionsOperations;
import pl.com.pollub.collections.MapOperations;
import pl.com.pollub.sort.SortTest;
import pl.com.pollub.threads.PingPong;

/**
 * Created by mmaciasz on 2017-02-18.
 */
public class MainBenchmark {

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger(MainBenchmark.class);
    }

    public static void main(String[] args) throws RunnerException {
        log.info("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(MapOperations.class.getSimpleName())
                .include(PingPong.class.getSimpleName())
                .include(SortTest.class.getSimpleName())
                .include(CollectionsOperations.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(20)
                .build();

        new Runner(opt).run();
    }

}
