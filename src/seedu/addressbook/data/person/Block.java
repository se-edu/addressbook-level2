package seedu.addressbook.data.person;

public class Block {
    private String value;

    public Block(String block) {
        value = block;
    }

    public String getBlock() {
        return value;
    }

    public boolean isValid() {
        for(int i = 0; i < value.length(); i++) {
            if(!Character.isLetterOrDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
