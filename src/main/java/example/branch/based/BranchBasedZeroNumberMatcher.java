package example.branch.based;

import example.NumberSignException;
import example.NumberSignMatcher;

public class BranchBasedZeroNumberMatcher implements NumberSignMatcher {

    @Override
    public boolean test(final int i) throws NumberSignException {
        return i==0;
    }
}
