package pl.com.pollub;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.test.DataPrepare;
import pl.com.pollub.test.constants.Names;
import pl.com.pollub.test.dto.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmaciasz on 2017-02-18.
 */
public class MainBenchmark {

    private static final Logger log;

    private static List<Person> persons;

    static {
        log = LoggerFactory.getLogger(MainBenchmark.class);
        persons = DataPrepare.prepareData(100000);
    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void toMapCollect() {
        Map<Long, Person> ret = new HashMap<>();
        for (final Person person : persons) {
            ret.put(person.getPersonId(), person);
        }
        final List<Person> personList = new ArrayList<>();
        for (final Map.Entry<Long, Person> p : ret.entrySet()) {
            if (p.getValue().getFirstName().equals(Names.Aaliyah.name())) {
                personList.add(p.getValue());
            }
        }
        log.info("Result size: {}", personList.size());
    }


    public static void main(String[] args) throws RunnerException {
        log.info("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(MainBenchmark.class.getSimpleName())
                .shouldDoGC(true)
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(2)
                .syncIterations(true)
                .build();

        new Runner(opt).run();
    }

}
