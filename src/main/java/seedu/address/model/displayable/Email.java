package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a Displayable's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {
    public static final String MESSAGE_CONSTRAINTS = "Emails must contain at least one '@'.";
    public static final String VALIDATION_REGEX = "[\\S\\s]*@[\\S\\s]*";
    public static final String DEFAULT_EMAIL_STRING = "default@email.com";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_RECOMMENDATIONS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String AFFIRMATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    // A minimally valid email must have at least one @ symbol.

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        AppUtil.validateArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isAppropriateEmail(String test) {
        return test.matches(AFFIRMATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}