package example.branch.based;

import example.NumberSignException;
import example.NumberSignMatcher;

public class BranchBasedNegativeNumberMatcher implements NumberSignMatcher {
    @Override
    public boolean test(int i) throws NumberSignException {
        return i<0;
    }
}
