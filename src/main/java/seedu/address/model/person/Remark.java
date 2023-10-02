package seedu.address.model.person;
// Remark.java taken from tutorial at https://nus-cs2103-ay2324s1.github.io/tp/tutorials/AddRemark.html and from
// https://github.com/se-edu/addressbook-level3/commit/4516e099699baa9e2d51801bd26f016d812dedcc#diff-41bb13c581e280c686198251ad6cc337cd5e27032772f06ed9bf7f1440995ece
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}