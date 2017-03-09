package seedu.addressbook.parser;

import java.util.Set;

public class ParsedInput {
    public static final String INCORRECT_COMMAND = "incorrect command";

    private final String commandType;

    private String name;
    private String phone;
    private String email;
    private String address;

    private boolean isPhonePrivate;
    private boolean isEmailPrivate;
    private boolean isAddressPrivate;

    private Set<String> tags;
    private int targetVisibleIndex;
    private Set<String> keywords;
    String feedbackToUser;

    public String getCommandType() {
        return commandType;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIsPhonePrivate() {
        return isPhonePrivate;
    }

    public boolean getIsEmailPrivate() {
        return isEmailPrivate;
    }

    public boolean getIsAddressPrivate() {
        return isAddressPrivate;
    }

    public Set<String> getTags() {
        return tags;
    }

    public int getTargetVisibleIndex() {
        return targetVisibleIndex;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public ParsedInput(String commandType) {
        this.commandType = commandType;
    }

    public ParsedInput setName(String name) {
        this.name = name;
        return this;
    }

    public ParsedInput setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ParsedInput setEmail(String email) {
        this.email = email;
        return this;
    }

    public ParsedInput setAddress(String address) {
        this.address = address;
        return this;
    }

    public ParsedInput setIsPhonePrivate(boolean isPhonePrivate) {
        this.isPhonePrivate = isPhonePrivate;
        return this;
    }

    public ParsedInput setIsEmailPrivate(boolean isEmailPrivate) {
        this.isEmailPrivate = isEmailPrivate;
        return this;
    }

    public ParsedInput setIsAddressPrivate(boolean isAddressPrivate) {
        this.isAddressPrivate = isAddressPrivate;
        return this;
    }

    public ParsedInput setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public ParsedInput setTargetVisibleIndex(int targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
        return this;
    }

    public ParsedInput setKeywords(Set<String> keywords) {
        this.keywords = keywords;
        return this;
    }

    public ParsedInput setFeedbackToUser(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        return this;
    }

}
