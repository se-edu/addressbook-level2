package seedu.addressbook.data.person;

public class Postal {
    private int postalNum;
    public Postal(int postalNum) {
        this.postalNum = postalNum;
    }
    public int getPostalNum() {
        return postalNum;
    }
    public void setPostalNum(int postalNum) {
        this.postalNum = postalNum;
    }
}