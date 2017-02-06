package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.*;

public class Formatter {
	/** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    

    public Formatter(){
    	
    }

    /**
     * 
     * @param message
     * @return divider prefix formatted accordingly as: LINE_PREFIX + DIVIDER + LS + LINE_PREFIX + message;
     */
    
    public String addLinePrefix(String message){
    	return LINE_PREFIX + message;
    }
    /**
     * || ====================================
     * || message goes here
     * @param message
     * @return
     */
    public String addDividerPrefix(String message){
    	return LINE_PREFIX + DIVIDER + LS + LINE_PREFIX + message;
    }
    /**
     * @param message
     * @return divider suffix formatted accordingly as: LINE_PREFIX + message + LS + LINE_PREFIX + DIVIDER;
     */
    public String addDividerSuffix(String message){
    	return LINE_PREFIX + message + LS + LINE_PREFIX + DIVIDER;
    }

    /**
     * || message goes here
     * =======================================
     * @param message
     * @return
     */
    public String formatResultToUser(String message){
    	return addLinePrefix(message) + LS + DIVIDER;
    }
    
    /**
     * || Enter command: 
     * @return
     */
    public String formatEnterCommand(){
    	return LINE_PREFIX + "Enter command: ";
    }
    
    /**
     * formats etc. [Command entered: add]
     * @param fullInputLine
     * @return
     */
    public String formatCommandEntered(String fullInputLine){
    	return "[Command entered:" + fullInputLine + "]";
    }
        
    /**
     * formats goodbye message according to:
     * showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
     * @return
     */
    public String formatGoodbyeMessage(){
    	return addDividerSuffix(addDividerSuffix(MESSAGE_GOODBYE));
    }
    /**
     * formats init failed message according to:
     * showToUser(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
     * @param message
     * @return
     */
    public String formatInitFailedMessage(){
    	return addDividerSuffix(addDividerSuffix(MESSAGE_INIT_FAILED));
    }
    
    /**
     * format welcome message according to:
     * return DIVIDER + LS +
               DIVIDER + LS +
               MESSAGE_WELCOME + LS +
               version + LS +
               MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE + LS +
               storageFileInfo + LS +
               DIVIDER;
     * @param version
     * @param storageFileInfo
     * @return
     */
    public String formatWelcomeMessage(String version, String storageFileInfo){
    	String baseMessage = MESSAGE_WELCOME + LS +
    						 version + LS + 
    						 MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE + LS +
    						 storageFileInfo + LS;
    	
    	return addDividerSuffix(addDividerPrefix(addDividerPrefix(baseMessage)));	
    }
    

}
