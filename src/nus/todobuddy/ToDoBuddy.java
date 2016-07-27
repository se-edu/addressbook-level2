package nus.todobuddy;
/* ==============NOTE TO STUDENTS======================================
 * This class is not written in pure Object-Oriented fashion. 
 * Yes, it is possible to write non-OO code using an OO language.
 * ====================================================================
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is used to store, remove and retrieve to-do items which are saved
 * in a text file. Each to-do items has a task title, a priority(p/) and a
 * duration(d/). Furthermore, you can retrieve to-do items by providing a
 * keyword and a priority(optional). In case multiple items are retrieved, it
 * will be ordered from earliest to latest entries. User can also view all to-do
 * list and delete a to-do item by providing to-do item's number. The storage
 * file will updated every time user make any changes to the to-do list.
 **/

public class ToDoBuddy {
    /*
     * ==============NOTE TO STUDENTS======================================
     * These messages shown to the user are defined in one place for convenient
     * editing and proof reading. Such messages are considered part of the UI
     * and may be subjected to review by UI experts or technical writers. Note
     * that Some of the strings below include '%1$s' etc to mark the locations
     * at which java String.format(...) method can insert values.
     * ====================================================================
     */
    private static final String MESSAGE_ADDED = "New to-do item added: %1$s, Priority %2$s, Duration %3$s";
    private static final String MESSAGE_CANNOT_DELETE_TODO_ITEM = "Could not delete to-do item";
    private static final String MESSAGE_CANNOT_SAVE_TODO_ITEM = "Could not save to-do item";
    private static final String MESSAGE_CANNOT_RENAME_FILE = "Could not rename storage file";
    private static final String MESSAGE_CANNOT_DELETE_FILE = "Could not delete storage file";
    private static final String MESSAGE_DELETED = "Deleted to-do item: %1$s, Priority %2$s, Duration %3$s";
    private static final String MESSAGE_FILE_NOT_FOUND = "Could not locate storage file";
    private static final String MESSAGE_HELP_COMMAND = "%1$-8s : %2$s \n";
    private static final String MESSAGE_HELP_PARAMETER = "\t Parameter: %1$s \n";
    private static final String MESSAGE_HELP_EXAMPLE = "\t Example: %1$s \n";
    private static final String MESSAGE_INVALID_FORMAT = "invalid command format :%1$s \n%2$s";
    private static final String MESSAGE_NO_TODO_VIEWED = "No to-do item to be viewed";
    private static final String MESSAGE_NO_TODO_FOUND = "No to-do item exists with specified keywords or priority";
    private static final String MESSAGE_TODO_ITEM = "%1$s %2$s %3$s";
    private static final String MESSAGE_TODO_ITEM_WITHOUT_PARAM_SIGNS = "%1$s, Priority %2$s, Duration %3$s \n";
    private static final String MESSAGE_TODO_ITEM_WITHOUT_PARAM_SIGNS_WITH_NUMBERS = "%1$s. %2$s \n";
    private static final String MESSAGE_TODO_CLEARED = "To-do list has been cleared!";
    private static final String MESSAGE_WELCOME = "Welcome to ToDoBuddy!";

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_ADD_DESC = "Adds a to-do items to the storage.";
    private static final String COMMAND_ADD_PARAMETER = "[priority](HIGH/MEDIUM/LOW/UNSPECIFIED) [duration] [title]";
    private static final String COMMAND_ADD_EXAMPLE = "add p/HIGH d/0.5 Finish CS2103 Assignment";

    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_FIND_DESC = "Retrieve to-do items that match the keyword, with/without priority.";
    private static final String COMMAND_FIND_PARAMETER = " [priority](HIGH/MEDIUM/LOW/UNSPECIFIED) [keywords]";
    private static final String COMMAND_FIND_EXAMPLE = "find CS2103 CS5103 or find p/HIGH CS2103 CS5103";

    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_VIEW_DESC = "Shows sorted to-do list with its index numbers.";
    private static final String COMMAND_VIEW_EXAMPLE = "view";

    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_DELETE_DESC = "Deletes to-do item based on index numbers.";
    private static final String COMMAND_DELETE_PARAMETER = "[index number]";
    private static final String COMMAND_DELETE_EXAMPLE = "delete 1";

    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_CLEAR_DESC = "Clears to-do list permanently.";
    private static final String COMMAND_CLEAR_EXAMPLE = "clear";

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HELP_DESC = "Shows program usage instructions.";
    private static final String COMMAND_HELP_EXAMPLE = "help";

    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_EXIT_DESC = "Exit from this program.";
    private static final String COMMAND_EXIT_EXAMPLE = "exit";

    // This arrayList will be used to store the to-do items
    private static ArrayList<String[]> toDoList = new ArrayList<>();

    // These are the correct number of parameters for each command
    private static final int PARAM_COUNT_FOR_ADD_TODO = 3;
    private static final int PARAM_COUNT_FOR_DELETE_TODO = 1;
    private static final int PARAM_COUNT_FOR_FIND_TODO = 1;
    private static final int PARAM_COUNT_FOR_FIND_TODO_PLUS_PRIORITY = 2;
    
    // These are the sign to define priority and duration in command's parameter
    private static final String SIGN_PRIORITY = "p/";
    private static final String SIGN_DURATION = "d/";
    
    // These are the locations at which various parameters will appear in a
    // command
    private static final int PARAM_PRIORITY = 0;
    private static final int PARAM_DURATION = 1;
    private static final int PARAM_TITLE = 2;

    private static final int PARAM_DELETE_NUMBER = 0;
    private static final int DELETE_NUMBER_OFFSET = 1;

    private static final int PARAM_FIND_PRIORITY = 0;
    private static final int PARAM_FIND_KEYWORD_PLUS_PRIORITY = 1;
    private static final int PARAM_FIND_KEYWORD = 0;
    
    /* This filename is used if the user doesn't provide the file name*/
	private static final String DEFAULT_STORAGE_FILE = "storage.txt";
    
    // These are the possible command types
    enum PriorityType{
        HIGH, MEDIUM, LOW, UNSPECIFIED
    };
    /*
     * This variable is declared for the whole class (instead of declaring it
     * inside the readUserCommand() method to facilitate automated testing using
     * the I/O redirection technique. If not, only the first line of the input
     * text file will be processed.
     */
    private static Scanner scanner = new Scanner(System.in);

    
    private static String storageFileName;

    /*
     * ==============NOTE TO STUDENTS======================================
     * Notice how this method solves the whole problem at a very high level. We
     * can understand the high-level logic of the program by reading this method
     * alone.
     * ====================================================================
     */
    public static void main(String[] args) {
        setStorageFileName(args);
        showToUser(MESSAGE_WELCOME);
        while (true) {
            String userCommand = readCommand();
            String feedback = executeCommand(userCommand);
            showToUser(feedback);
        }
    }

    /*
     * ==============NOTE TO STUDENTS==========================================
     * If the reader wants a deeper understanding of the solution, he/she can go
     * to the next level of abstraction by reading the methods (given below)
     * that is referenced by the method above.
     * ====================================================================
     */

    /**
     * Sets storage's filename if user specified it
     * 
     * @param args  Array of arguments provided by the user
     */
    private static void setStorageFileName(String[] args) {
        storageFileName = args.length == 1 ? args[0] : DEFAULT_STORAGE_FILE;
    }

    /**
     * Shows a message to the user
     * 
     * @param text  String to be printed
     */
    private static void showToUser(String text) {
        System.out.println(text);

    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * 
     * @return text entered by the user
     */
    private static String readCommand() {
        System.out.print("Enter command:");
        return scanner.nextLine();
    }

    /**
     * Checks which command the user want to trigger, then run the corresponding
     * function
     * 
     * @param userCommand  String represent command from the user
     * @return  Message from respective function
     */
    public static String executeCommand(String userCommand) {
        if (userCommand.trim().isEmpty())
            return String.format(MESSAGE_INVALID_FORMAT, userCommand, showUsageInstruction());

        String commandType = getFirstWord(userCommand);

        switch (commandType) {
        case COMMAND_ADD:
            return addToDo(userCommand);
        case COMMAND_FIND:
            return findToDo(userCommand);
        case COMMAND_VIEW:
            return viewToDoList();
        case COMMAND_DELETE:
            return deleteToDoItem(userCommand);
        case COMMAND_CLEAR:
            return clearToDoList(userCommand);
        case COMMAND_HELP:
            return showUsageInstruction();
        case COMMAND_EXIT:
            System.exit(0);
        default:
            return String.format(MESSAGE_INVALID_FORMAT, userCommand, showUsageInstruction());
        }
    }

    /**
     * Formats string for usage instruction, then shows it to the user
     * 
     * @return  Usage instruction
     */
    private static String showUsageInstruction() {
        String usageInstruction = getUsageInfoForAddCommand() + getUsageInfoForFindCommand() + getUsageInfoForViewCommand()
                + getUsageInfoForDeleteCommand() + getUsageInfoForClearCommand() + getUsageInfoForExitCommand() + getUsageInfoForHelpCommand();

        return usageInstruction;
    }

    /**
     * Returns the first word from user command
     * 
     * @param userCommand  String represent command from the user
     * 
     * @return  First word of user command
     */
    private static String getFirstWord(String userCommand) {
        String commandTypeString = userCommand.trim().split("\\s+")[0];
        return commandTypeString;
    }

    /**
     * Adds a to-do items to the storage.
     * 
     * @param userCommand  Command that's provided by the user (although we receive the
     *                     full user command, we assume without checking the first word
     *                     to be 'add')
     *                      
     * @return  Message of the operation
     */
    private static String addToDo(String userCommand) {
        String[] parameters = getParams(userCommand, PARAM_COUNT_FOR_ADD_TODO);

        if (isValidAddParameter(parameters)) {
            String priority = parameters[PARAM_PRIORITY];
            String duration = parameters[PARAM_DURATION];
            String title = parameters[PARAM_TITLE];

            String[] toDoItem = new String[] { priority, duration, title };
            
            toDoList.add(toDoItem);

            saveToDoItemToFile(toDoItem);

            return String.format(MESSAGE_ADDED, title, 
                    removeParameterSign(priority, SIGN_PRIORITY), 
                    removeParameterSign(duration, SIGN_DURATION));
        }

        return String.format(MESSAGE_INVALID_FORMAT, userCommand, showUsageInstruction());
    }

    /**
     * Checks whether the user provides keyword only or with priority Then pass
     * those parameters to respective retrieval function
     * 
     * @param userCommand  Command that's provided by the user (although we receive the
     *                     full user command, we assume without checking the first word
     *                     to be 'find')
     * @return  Result of the found to-do items
     */
    private static String findToDo(String userCommand) {
        String[] parameters = null;
        if (removeFirstWord(userCommand).startsWith(SIGN_PRIORITY)) {
            parameters = getParams(userCommand, PARAM_COUNT_FOR_FIND_TODO_PLUS_PRIORITY);
        } else {
            parameters = getParams(userCommand, PARAM_COUNT_FOR_FIND_TODO);
        }
        
        if (isValidFindParameter(parameters)) { 
            if (parameters.length == PARAM_COUNT_FOR_FIND_TODO) {
                String[] keyword = parameters[PARAM_FIND_KEYWORD].split("\\s+");   
                return retrieveToDoItem(keyword);
            } else if (parameters.length == PARAM_COUNT_FOR_FIND_TODO_PLUS_PRIORITY) {
                String priority = parameters[PARAM_FIND_PRIORITY];
                String[] keyword = parameters[PARAM_FIND_KEYWORD_PLUS_PRIORITY].split("\\s+");   
                return retrieveToDoItem(keyword, priority);
            }
        }

        return String.format(MESSAGE_INVALID_FORMAT, userCommand, showUsageInstruction());
    }
    
    /**
     * Deletes selected to-do item
     * 
     * @param userCommand  Command that's provided by the user (although we receive the
     *                     full user command, we assume without checking the first word
     *                     to be 'delete')
     * @return  Message of the operation
     */
    private static String deleteToDoItem(String userCommand) {
        String[] parameters = getParams(userCommand, PARAM_COUNT_FOR_DELETE_TODO);

        if (isValidDeleteParameter(parameters)) {
            int selectedNumber = Integer.parseInt(parameters[PARAM_DELETE_NUMBER]) - DELETE_NUMBER_OFFSET;

            String[] deletedToDo = getToDoItem(selectedNumber);
            
            String deletedTitle = deletedToDo[PARAM_TITLE];
            String deletedPriority = removeParameterSign(deletedToDo[PARAM_PRIORITY], SIGN_PRIORITY);
            String deletedDuration = removeParameterSign(deletedToDo[PARAM_DURATION], SIGN_DURATION);
            
            toDoList.remove(selectedNumber);

            deleteToDoItemFromFile(deletedToDo);

            return String.format(MESSAGE_DELETED, deletedTitle, deletedPriority, deletedDuration);
            
        }
        
        return String.format(MESSAGE_INVALID_FORMAT, userCommand, showUsageInstruction());
    }
    
    /**
     * Clears the to-do list
     * 
     * @param userCommand  Command that's provided by the user (although we receive the
     *                     full user command, we assume without checking the first word
     *                     to be 'clear')
     * @return  Message of the operation
     */
    private static String clearToDoList(String userCommand) {
        try {
            File file = new File(storageFileName);
            
            if (!file.delete()) {
                return MESSAGE_CANNOT_DELETE_FILE;
            }
            
            toDoList.clear();
        } catch (Exception e) {
            return MESSAGE_CANNOT_DELETE_TODO_ITEM;
        }
        return MESSAGE_TODO_CLEARED;
    }

    /**
     * Saves new to-do item to storage file
     * 
     * @param toDoItem  The to-do item to be saved
     */
    private static void saveToDoItemToFile(String[] toDoItem) {
        try {
            File file = new File(storageFileName);
            
            if (!file.exists()) { 
                file.createNewFile();
            }
            
            //true = append file
            FileWriter writer = new FileWriter(storageFileName, true);
            writer.append(formatToDoItemWithoutParameterSigns(toDoItem) + System.getProperty("line.separator"));
            writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println(MESSAGE_FILE_NOT_FOUND);
		} catch (IOException e) {
			System.out.println(MESSAGE_CANNOT_SAVE_TODO_ITEM);
		}
    }
    
    /**
     * Deletes to-do item from storage file
     * 
     * @param toDoItem  The to-do item to be deleted
     */
    private static void deleteToDoItemFromFile(String[] toDoItem) {
        try {
            File file = new File(storageFileName);
            
            if (!file.exists()) { 
                file.createNewFile();
            }
            
            File tempFile = new File(file.getAbsolutePath() + ".tmp");;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            while ((line = reader.readLine()) != null) {
                if (!line.trim().equals(formatToDoItemWithoutParameterSigns(toDoItem))) {
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
            
            writer.close(); 
            reader.close(); 

            // Delete the original file
            if (!file.delete()) {
                System.out.println(MESSAGE_CANNOT_DELETE_FILE);
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(file))
                System.out.println(MESSAGE_CANNOT_RENAME_FILE);
		} catch (FileNotFoundException ex) {
			System.out.println(MESSAGE_FILE_NOT_FOUND);
		} catch (IOException e) {
			System.out.println(MESSAGE_CANNOT_DELETE_TODO_ITEM);
		}
    }


    /**
     * Sorts the to-do list by its priority, then build a string to show sorted
     * to-do list
     * 
     * @return  Message shows to-do items after sorted by priority
     */
    private static String viewToDoList() {
        if (toDoList.size() > 0) {
            sortToDoListByPriority(toDoList);
            return formatToDoListForPrintingWithNumbers(toDoList);
        } else {
            return MESSAGE_NO_TODO_VIEWED;
        }
    }

    /**
     * Sorts the to-do list by its priority
     * 
     * @param toDoList  The to-do list to be sorted
     */
    private static void sortToDoListByPriority(ArrayList<String[]> toDoList) {
        Collections.sort(toDoList, new Comparator<String[]>() {
            public int compare(String[] s1, String[] s2) {
                return s1[PARAM_PRIORITY].compareTo(s2[PARAM_PRIORITY]);
            }
        });
    }
    
    /**
     * Gets to-do list's item based on the index provided by the user
     * 
     * @param selectedIndex  Index of to-do item
     * 
     * @return  Selected to-do item
     */
    private static String[] getToDoItem(int selectedIndex) {
        String[] item = toDoList.get(selectedIndex);
        return item;
    }
    
    /**
     * Retrieves any to-do items in the storage that correspond with provided
     * keywords and priority
     * 
     * @param keywords  Keywords for to-do title
     * @param priority  Priority for to-do priority
     * 
     * @return  Message of to-do items that have corresponding keyword
     */
    private static String retrieveToDoItem(String[] keywords, String priority) {
        // This arrayList will be used to store found to-do items
        ArrayList<String[]> foundToDo = new ArrayList<>();

        for (String[] toDoItems : toDoList) {
            if (containKeywords(toDoItems[PARAM_TITLE], keywords)
                    && toDoItems[PARAM_PRIORITY].equals(priority)) {
                foundToDo.add(toDoItems);
            }
        }

        if (foundToDo.isEmpty()) {
            return String.format(MESSAGE_NO_TODO_FOUND);
        } else {
            return formatToDoListForPrinting(foundToDo);
        }
    }

    /**
     * Retrieves any to-do items in the storage that correspond with provided
     * keywords only
     * 
     * @param keywords  Keywords for to-do title
     * 
     * @return  Message of to-do items that have corresponding keyword
     */
    private static String retrieveToDoItem(String[] keywords) {
        // This arrayList will be used to store found to-do items
        ArrayList<String[]> foundToDo = new ArrayList<>();

        for (String[] toDoItems : toDoList) {
            if (containKeywords(toDoItems[PARAM_TITLE], keywords)) {
                foundToDo.add(toDoItems);
            }
        }

        if (foundToDo.isEmpty()) {
            return String.format(MESSAGE_NO_TODO_FOUND);
        } else {
            return formatToDoListForPrinting(foundToDo);
        }
    }

    /**
     * Retrieves any to-do items in the storage that correspond with provided
     * keyword only
     * 
     * @param title  Title of the task
     * @param keywords  Keywords to check with to-do title
     * 
     * @return  Boolean whether the title contains one of the keywords or not
     */
    private static Boolean containKeywords(String title, String[] keywords) {
        for(String word : keywords) {
            if(title.contains(word)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Builds a string to show the user the to-do items that are successfully
     * found from the storage
     * 
     * @param toDoList  List containing to-do items
     * 
     * @return  Formatted string for printing to-do items
     */
    private static String formatToDoListForPrinting(ArrayList<String[]> toDoList) {
        String result = "";

        for (String[] todoItems : toDoList) {
            result += formatToDoItemWithParameterSigns(todoItems);
        }

        return result;
    }
    
    /**
     * Builds a string to show the user the to-do items that are successfully
     * found from the storage
     * 
     * @param toDoList  List containing to-do items
     * 
     * @return  Formatted string for printing to-do items with number
     */
    private static String formatToDoListForPrintingWithNumbers(ArrayList<String[]> toDoList) {
        String result = "";

        int toDoItemNumber = 1;

        for (String[] todoItems : toDoList) {
            result += String.format(MESSAGE_TODO_ITEM_WITHOUT_PARAM_SIGNS_WITH_NUMBERS, toDoItemNumber, 
                                formatToDoItemWithParameterSigns(todoItems));

            toDoItemNumber++;
        }

        return result;
    }
    
    /**
     * Converts a to-do item to a string WITH parameter signs
     * 
     * @param toDoItem  to-do item to be formatted
     * 
     * @return  Formatted string from a to-do item
     */
    private static String formatToDoItemWithParameterSigns(String[] toDoItem){
        return String.format(MESSAGE_TODO_ITEM_WITHOUT_PARAM_SIGNS, 
                    toDoItem[PARAM_TITLE],
                    removeParameterSign(toDoItem[PARAM_PRIORITY], SIGN_PRIORITY),
                    removeParameterSign(toDoItem[PARAM_DURATION], SIGN_DURATION));
    }
    
    /**
     * Builds a string from to-do items WITHOUT parameter signs
     * 
     * @param toDoItem  to-do item to be formatted
     * 
     * @return  Formatted string from a to-do item
     */
    private static String formatToDoItemWithoutParameterSigns(String[] toDoItem) {
        return String.format(MESSAGE_TODO_ITEM, 
                    toDoItem[PARAM_TITLE], 
                    toDoItem[PARAM_PRIORITY],
                    toDoItem[PARAM_DURATION]);
    }

    

    /**
     * Checks if there is any character after p/ and nothing before
     * 
     * @param priority  A parameter from user command that indicate the priority
     * 
     * @return  Boolean that indicate validity of the parameter
     */
    private static boolean isValidPriority(String priority) {
        return (checkCharBeforeParamSign(priority, SIGN_PRIORITY)
                && isValidPriorityType(removeParameterSign(priority, SIGN_PRIORITY)));
    }
    
    /**
     * Checks if given priority is valid priority type
     * 
     * @param priority  The priority type of the task given by user
     * 
     * @return  Boolean that indicate validity of given priority type
     */
    private static boolean isValidPriorityType(String priority) {
        for (PriorityType pt : PriorityType.values()) {
            if (pt.name().equals(priority)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Checks if there is any character after d/ and nothing before, also check
     * if the character is a valid Float value
     * 
     * @param duration  A parameter from user command that indicate the duration
     * 
     * @return  Boolean that indicate validity of the duration
     */
    private static boolean isValidDuration(String duration) {
        return (checkCharBeforeParamSign(duration, SIGN_DURATION)
                && isPositiveNonZeroFloat(removeParameterSign(duration, SIGN_DURATION)));
    }

    /**
     * Checks if the user provide the title for to-do item
     * 
     * @param title  A parameter from user command that indicate the title
     * 
     * @return  Boolean that indicate validity of the title
     */
    private static boolean isValidTaskTitle(String title) {
        // check if there is any word for task title
        return !title.trim().isEmpty();
    }

    /**
     * Checks if the string is a positive non-zero float value
     * 
     * @param s  The string to be checked
     * 
     * @return  Boolean that indicate if the string is a positive non-zero float
     *              value
     */
    private static boolean isPositiveNonZeroFloat(String s) {
        try {
            float i = Float.parseFloat(s);
            return i > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Checks if the string is a positive integer value
     * 
     * @param s  The string to be checked
     * 
     * @return  Boolean that indicate if the string is a positive integer value
     */
    private static boolean isPositiveInteger(String s) {
        try {
            float i = Integer.parseInt(s);
            // return true if i is greater than 0
            return i > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Retrieves all parameters of the command provided by the user
     * 
     * @param userCommand  String represents command from the user
     * 
     * @return  Parameters in user command
     */
    private static String[] getParams(String userCommand, int paramCount) {
        return splitParameters(removeFirstWord(userCommand), paramCount);
    }
    
    /**
     * Checks validity of find command parameters
     * 
     * @param parameters  Parameters in find command
     * 
     * @return  Validity result
     */
    private static Boolean isValidFindParameter(String[] parameters) {
        return ((parameters.length == PARAM_COUNT_FOR_FIND_TODO_PLUS_PRIORITY 
                    && isValidPriority(parameters[PARAM_FIND_PRIORITY])
                    && !parameters[PARAM_FIND_KEYWORD_PLUS_PRIORITY].trim().isEmpty())
                || (parameters.length == PARAM_COUNT_FOR_FIND_TODO
                    && !isValidPriority(parameters[PARAM_FIND_PRIORITY])
                    && !parameters[PARAM_FIND_KEYWORD].trim().isEmpty()));
    }
    /**
     * Checks validity of add command parameters
     * 
     * @param parameters  Parameters in find command
     * 
     * @return  Validity result
     */
    private static Boolean isValidAddParameter(String[] parameters) {
        return (parameters.length == PARAM_COUNT_FOR_ADD_TODO
                && isValidPriority(parameters[PARAM_PRIORITY]) 
                && isValidDuration(parameters[PARAM_DURATION]) 
                && isValidTaskTitle(parameters[PARAM_TITLE]));
    }
    /**
     * Checks validity of delete command parameters
     * 
     * @param parameters  Parameters in delete command
     * 
     * @return  Validity result
     */
    private static Boolean isValidDeleteParameter(String[] parameters) {
        return (parameters.length == PARAM_COUNT_FOR_DELETE_TODO 
                && isPositiveInteger(parameters[PARAM_DELETE_NUMBER])
                && Integer.parseInt(parameters[PARAM_DELETE_NUMBER]) <= toDoList.size());
            
    }

    /**
     * Splits the user command according to the provided split limit
     * 
     * @param commandParametersString  String represents command from the user to be split
     * 
     * @return  Array of string of the split user command
     */
    private static String[] splitParameters(String commandParametersString, int paramSize) {
        String[] parameters = commandParametersString.trim().split("\\s+", paramSize);
        return parameters;
    }

    /**
     * Removes the first word from user command
     * 
     * @param userCommand  String represents command from the user
     * 
     * @return  User command without the first word
     */
    private static String removeFirstWord(String userCommand) {
        return userCommand.replace(getFirstWord(userCommand), "").trim();
    }

    /**
     * Builds string for showing 'add' command usage instruction
     * 
     * @return  'add' command usage instruction
     */
    private static String getUsageInfoForAddCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_ADD, COMMAND_ADD_DESC)
                + String.format(MESSAGE_HELP_PARAMETER, COMMAND_ADD_PARAMETER)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_ADD_EXAMPLE);
    }

    /**
     * Builds string for showing 'find' command usage instruction
     * 
     * @return  'find' command usage instruction
     */
    private static String getUsageInfoForFindCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_FIND, COMMAND_FIND_DESC)
                + String.format(MESSAGE_HELP_PARAMETER, COMMAND_FIND_PARAMETER)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_FIND_EXAMPLE);
    }

    /**
     * Builds string for showing 'delete' command usage instruction
     * 
     * @return  'delete' command usage instruction
     */
    private static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_DELETE, COMMAND_DELETE_DESC)
                + String.format(MESSAGE_HELP_PARAMETER, COMMAND_DELETE_PARAMETER)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE);
    }

    /**
     * Builds string for showing 'clear' command usage instruction
     * 
     * @return  'clear' command usage instruction
     */
    private static String getUsageInfoForClearCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_CLEAR, COMMAND_CLEAR_DESC)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_CLEAR_EXAMPLE);
    }

    /**
     * Builds string for showing 'view' command usage instruction
     * 
     * @return  'view' command usage instruction
     */
    private static String getUsageInfoForViewCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_VIEW, COMMAND_VIEW_DESC)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_VIEW_EXAMPLE);
    }

    /**
     * Builds string for showing 'help' command usage instruction
     * 
     * @return  'help' command usage instruction
     */
    private static String getUsageInfoForHelpCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_HELP, COMMAND_HELP_DESC)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE);
    }

    /**
     * Builds string for showing 'exit' command usage instruction
     * 
     * @return  'exit' command usage instruction
     */
    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_HELP_COMMAND, COMMAND_EXIT, COMMAND_EXIT_DESC)
                + String.format(MESSAGE_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE);
    }
    
    /**
     * Removes sign(p/, d/, etc) from parameter string
     * 
     * @param s  Parameter as a string
     * @param sign  Parameter sign to be removed
     * 
     * @return  Priority string without p/
     */
    private static String removeParameterSign(String s, String sign) {
        return s.replace(sign, "");
    }
    
    /**
     * Checks if there is any char before parameter sign or not
     * 
     * @param s  Parameter as a string
     * @param sign  Parameter sign
     * 
     * @return  Priority string without p/
     */
    private static Boolean checkCharBeforeParamSign(String s, String sign) {
        return (s.trim().split(sign).length == 2 
                && !s.trim().split(sign)[1].isEmpty());
    }
}