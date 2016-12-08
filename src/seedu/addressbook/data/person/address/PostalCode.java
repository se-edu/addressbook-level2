package seedu.addressbook.data.person.address;

public class PostalCode {
	private String postalCode;

	public PostalCode(String postalCode) {
		this.setPostalCode(postalCode);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
