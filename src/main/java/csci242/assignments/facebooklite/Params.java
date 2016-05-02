package csci242.assignments.facebooklite;

import java.util.Arrays;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 5
 * @bugs None
 */
class Params {
    private String[] params;

    Params(String... params) {
        this.params = params;
    }

    int count() {
        return params.length;
    }

    String get(int n) {
        if (n > count() || count() < 0)
            throw new IndexOutOfBoundsException();
        return params[n];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Params params1 = (Params) o;

        return Arrays.equals(params, params1.params);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(params);
    }
}
