package seedu.addressbook.data.person;
import seedu.addressbook.data.exception.IllegalValueException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Represents a Person's recorded time in the address book.
 */

public class TimeStamp {
    private final LocalDateTime currentTime;
    private String currTime;

    /**
     * Clocks current time.
     */
    public TimeStamp()
    {
        this.currentTime = LocalDateTime.now();
        currTime = ""+ currentTime;
    }
    /**
     * Clocks current time with a different starting time.
     */
    public TimeStamp(String currTime) {
        this.currTime = currTime;
        currentTime = LocalDateTime.now();
    }
    /**
     * returns current time.
     */
    public String getTime() {
        return currTime;
    }
    /**
     * Class conversion to string.
     */
    public String toString() {
        return (currTime);
    }
}
