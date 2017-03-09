package pl.com.pollub;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.map.MapOperations;

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
                .build();

        new Runner(opt).run();
    }

}
