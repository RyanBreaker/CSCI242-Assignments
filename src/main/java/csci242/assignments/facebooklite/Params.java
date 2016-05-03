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

    String getFirst() {
        if (count() == 0)
            return null;
        return params[0];
    }

    String getSecond() {
        if (count() != 2)
            return null;
        return params[1];
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
