package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.buyer.Buyer;
import seedu.address.model.person.seller.Seller;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BUYER_DISPLAYED_INDEX = "The buyer index provided is higher than "
            + "the last number in the list!";
    public static final String MESSAGE_INVALID_SELLER_DISPLAYED_INDEX = "The seller index provided is higher than "
            + "the last number in the list!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code buyer} for display to the user.
     */
    public static String format(Buyer buyer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(buyer.getName())
                .append("; Phone: ")
                .append(buyer.getPhone())
                .append("; Email: ")
                .append(buyer.getEmail())
                .append("; Address: ")
                .append(buyer.getAddress())
                .append("; Buying Info: ")
                .append(buyer.getBuyHouseInfo())
                .append("; Tags: ");
        buyer.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Seller seller) {
        final StringBuilder builder = new StringBuilder();
        builder.append(seller.getName())
                .append("; Phone: ")
                .append(seller.getPhone())
                .append("; Email: ")
                .append(seller.getEmail())
                .append("; Address: ")
                .append(seller.getAddress())
                .append("; Selling Address: ")
                .append(seller.getSellingAddress())
                .append("; Selling Info: ")
                .append(seller.getSellHouseInfo())
                .append("; Tags: ");
        seller.getTags().forEach(builder::append);
        return builder.toString();
    }
}
