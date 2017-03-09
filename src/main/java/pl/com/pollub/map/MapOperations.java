package pl.com.pollub.map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import pl.com.pollub.Data;
import pl.com.pollub.test.constants.Names;
import pl.com.pollub.test.dto.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmaciasz on 2017-03-09.
 */
@Fork(2)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
public class MapOperations {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public List<Person> toMapCollect() {
        Map<Long, Person> ret = new HashMap<>();
        for (final Person person : Data.getPersons()) {
            ret.put(person.getPersonId(), person);
        }
        final List<Person> personList = new ArrayList<>();
        for (final Map.Entry<Long, Person> p : ret.entrySet()) {
            if (p.getValue().getFirstName().equals(Names.Aaliyah.name())) {
                personList.add(p.getValue());
            }
        }
        return personList;
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void toMapWithoutCollect(Blackhole bh) {
        Map<Long, Person> ret = new HashMap<>();
        for (final Person person : Data.getPersons()) {
            ret.put(person.getPersonId(), person);
        }
        for (final Map.Entry<Long, Person> p : ret.entrySet()) {
            if (p.getValue().getFirstName().equals(Names.Aaliyah.name())) {
                bh.consume(p);
            }
        }
    }
}
