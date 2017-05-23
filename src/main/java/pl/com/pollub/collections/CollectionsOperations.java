package pl.com.pollub.collections;

import javafx.util.Pair;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import pl.com.pollub.Data;
import pl.com.pollub.test.dto.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmaciasz on 2017-05-23.
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class CollectionsOperations {

    @Benchmark
    public List<Pair<Person, Person>> pairPersons() {
        final List<Pair<Person, Person>> personPairs = new ArrayList<>();
        for (final Person person : Data.getPersons()) {
            personPairs.add(new Pair<>(person, person.getPartner()));
        }
        return personPairs;
    }
}
