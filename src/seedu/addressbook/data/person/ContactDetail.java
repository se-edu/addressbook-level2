package seedu.addressbook.data.person;

/**
 * Represents a person's contact detail. Can be marked private for hiding self from display.
 */
public abstract class ContactDetail implements Printable{

    private boolean isPrivate;

    public ContactDetail(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isVisible() {
        return !isPrivate;
    }

    @Override
    public String getPrintableString(){
        return isPrivate ? "" : getFullPrintableString();
    }

    public abstract String getFullPrintableString();

}
