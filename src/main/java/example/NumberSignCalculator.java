package example;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class NumberSignCalculator implements Function<Integer, NumberSign> {

    private final Map<NumberSignMatcher, NumberSign> matchers;


    public NumberSignCalculator(final Map<NumberSignMatcher, NumberSign> matchers){
        this.matchers = Objects.requireNonNull(matchers, "matchers map can't be null");

    }

    @Override
    public NumberSign apply(final Integer integer) {
        for(Map.Entry<NumberSignMatcher, NumberSign> eachEntry:matchers.entrySet()){
            try {
                if(eachEntry.getKey().test(integer)){
                    return eachEntry.getValue();
                }
            } catch (final NumberSignException ignored) {
                //this line is intentionally left blank as we're using exceptions as goto :P
            }
        }
        throw new RuntimeException("We could not find sign of number " + integer);
    }
}
