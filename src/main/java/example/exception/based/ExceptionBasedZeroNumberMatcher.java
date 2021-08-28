package example.exception.based;

import example.NumberSignException;
import example.NumberSignMatcher;

public class ExceptionBasedZeroNumberMatcher implements NumberSignMatcher {

    @Override
    public boolean test(final int integer) throws NumberSignException {
        if(integer != 0) {
            throw new NumberSignException();
        }
        return true;
    }
}
