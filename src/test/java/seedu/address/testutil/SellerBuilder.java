package seedu.address.testutil;


import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.seller.SellHouseInfo;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.util.SampleDataUtil;

/**
 * Builds a seller.
 */
public class SellerBuilder extends PersonBuilder {

    public static final String DEFAULT_SELLING_ADDRESS = "47D Lor Sarhad, Singapore 119164";
    public static final String DEFAULT_SELL_HOUSE_INFO = "4 Room Flat in Sarhad Ville";

    private Address sellingAddress;
    private SellHouseInfo sellHouseInfo;

    /**
     * Creates a {@code SellerBuilder} with the default details.
     */
    public SellerBuilder() {
        super();
        sellingAddress = new Address(DEFAULT_SELLING_ADDRESS);
        sellHouseInfo = new SellHouseInfo(DEFAULT_SELL_HOUSE_INFO);
    }

    /**
     * Initializes the SellerBuilder with the data of {@code buyerToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        super(sellerToCopy);
        sellingAddress = sellerToCopy.getSellingAddress();
        sellHouseInfo = sellerToCopy.getSellHouseInfo();
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    @Override
    public SellerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Seller} that we are building.
     */
    @Override
    public SellerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code SellingAddress} of the {@code Seller} that we are building.
     */
    public SellerBuilder withSellingAddress(String sellingAddress) {
        this.sellingAddress = new Address(sellingAddress);
        return this;
    }

    /**
     * Sets the {@code SellHouseInfo()} of the {@code Seller} that we are building.
     */
    public SellerBuilder withSellHouseInfo(String sellHouseInfo) {
        this.sellHouseInfo = new SellHouseInfo(sellHouseInfo);
        return this;
    }

    @Override
    public Seller build() {
        return new Seller(super.name, super.phone, super.email,
                super.address, sellingAddress, sellHouseInfo, super.tags);
    }
}
