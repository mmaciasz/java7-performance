package pl.com.pollub.collections;

import org.openjdk.jmh.annotations.*;
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
@State(Scope.Thread)
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
    public Map<Long, Person> toMap() {
        Map<Long, Person> ret = new HashMap<>();
        for (final Person person : Data.getPersons()) {
            ret.put(person.getPersonId(), person);
        }
        return ret;
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
