package example.bench;

import example.NumberSign;
import example.NumberSignCalculator;
import example.NumberSignMatcher;
import example.branch.based.BranchBasedNegativeNumberMatcher;
import example.branch.based.BranchBasedPositiveNumberMatcher;
import example.branch.based.BranchBasedZeroNumberMatcher;
import example.exception.based.ExceptionBasedNegativeNumberMatcher;
import example.exception.based.ExceptionBasedPositiveNumberMatcher;
import example.exception.based.ExceptionBasedZeroNumberMatcher;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
public class Bench {
    private NumberSignCalculator exceptionBasedCalculator;
    private NumberSignCalculator branchBasedCalculator;
    private List<Integer> numbers;

    @Setup
    public void setup(){
        exceptionBasedCalculator = new NumberSignCalculator(exceptionBasedMatcher());
        branchBasedCalculator = new NumberSignCalculator(branchBasedMatchers());
        numbers = generateRandomIntegers();
    }

    @Benchmark
    public void exceptionBased(final Blackhole blackhole) {
        for(int i:numbers) {
            blackhole.consume(exceptionBasedCalculator.apply(i));
        }
    }

    @Benchmark
    public void branchBased(final Blackhole blackhole) {
        for(int i:numbers) {
            blackhole.consume(branchBasedCalculator.apply(i));
        }
    }

    private static final List<Integer> generateRandomIntegers() {
        final Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        return r.ints(10000).boxed().collect(Collectors.toList());
    }

    private static final Map<NumberSignMatcher, NumberSign> exceptionBasedMatcher() {
        final Map<NumberSignMatcher, NumberSign> matchers = new LinkedHashMap<>();
        matchers.put(new ExceptionBasedZeroNumberMatcher(), NumberSign.ZERO);
        matchers.put(new ExceptionBasedNegativeNumberMatcher(), NumberSign.NEGATIVE);
        matchers.put(new ExceptionBasedPositiveNumberMatcher(), NumberSign.POSITIVE);
        return matchers;
    }

    private static final Map<NumberSignMatcher, NumberSign> branchBasedMatchers() {
        final Map<NumberSignMatcher, NumberSign> matchers = new LinkedHashMap<>();
        matchers.put(new BranchBasedZeroNumberMatcher(), NumberSign.ZERO);
        matchers.put(new BranchBasedNegativeNumberMatcher(), NumberSign.NEGATIVE);
        matchers.put(new BranchBasedPositiveNumberMatcher(), NumberSign.POSITIVE);
        return matchers;
    }
}
