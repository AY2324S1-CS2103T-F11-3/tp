package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.seller.Seller;

/**
 * Adds a seller to the address book.
 */
public class AddSellerCommand extends Command {

    public static final String COMMAND_WORD = "seller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a seller to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SELLING_ADDRESS + "SELLING_ADDRESS "
            + PREFIX_INFO + "HOUSE_INFO "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Ryan "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "ryan@gmail.com "
            + PREFIX_ADDRESS + "My Secret Home "
            + PREFIX_SELLING_ADDRESS + "47D Lor Sarhad, Singapore 119164 "
            + PREFIX_INFO + "4 Room Flat in Sarhad Ville "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Got it. I've added a seller contact:\n%1$s";
    public static final String MESSAGE_DUPLICATE_SELLER = "This seller already exists in the address book";

    private final Seller toAdd;

    /**
     * Creates an AddSellerCommand to add the specified {@code Person}
     */
    public AddSellerCommand(Seller seller) {
        requireNonNull(seller);
        toAdd = seller;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSeller(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SELLER);
        }

        model.addSeller(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSellerCommand)) {
            return false;
        }

        AddSellerCommand otherAddCommand = (AddSellerCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
