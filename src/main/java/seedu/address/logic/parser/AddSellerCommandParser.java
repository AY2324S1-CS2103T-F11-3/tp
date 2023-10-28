package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.displayable.Address.ADDRESS_DEFAULT_STRING;
import static seedu.address.model.displayable.Email.EMAIL_DEFAULT_STRING;
import static seedu.address.model.displayable.Name.NAME_DEFAULT_STRING;
import static seedu.address.model.displayable.Phone.PHONE_DEFAULT_STRING;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.seller.SellHouseInfo;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddSellerCommand object
 */
public class AddSellerCommandParser implements Parser<AddSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSellerCommand
     * and returns an AddSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSellerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SELLING_ADDRESS, PREFIX_INFO, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
        }
        String nameString = argMultimap.getOrDefault(PREFIX_NAME, NAME_DEFAULT_STRING);
        assert (!nameString.equals(NAME_DEFAULT_STRING));

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_INFO);
        Name name = ParserUtil.parseName(nameString);

        Phone phone = ParserUtil.parsePhone(argMultimap.getOrDefault(PREFIX_PHONE, PHONE_DEFAULT_STRING));
        Email email = ParserUtil.parseEmail(argMultimap.getOrDefault(PREFIX_EMAIL, EMAIL_DEFAULT_STRING));
        Address address = ParserUtil.parseAddress(argMultimap.getOrDefault(PREFIX_ADDRESS, ADDRESS_DEFAULT_STRING));
        Address sellingAddress = ParserUtil.parseAddress(argMultimap
                .getOrDefault(PREFIX_SELLING_ADDRESS, ADDRESS_DEFAULT_STRING));
        SellHouseInfo sellHouseInfo = ParserUtil.parseSellHouseInfo(argMultimap
                .getOrDefault(PREFIX_INFO, INFO_DEFAULT_STRING));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Seller seller = new Seller(name, phone, email, address, sellingAddress, sellHouseInfo, tagList);

        return new AddSellerCommand(seller);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getOrDefault(prefix, null) != null);
    }

}
