package seedu.addressbook.commands;

import java.util.*;
import seedu.addressbook.*;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class EditCommand extends Command {


    Scanner sc=new Scanner(System.in);

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_SUCCESS = "Successfully modified: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Just follow the prompts afterwards";

    public EditCommand(int targetVisibleIndex){
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();

            String name = target.getName().fullName;
            String phoneNum = target.getPhone().value;
            Boolean phonePriv = target.getPhone().isPrivate();
            String email = target.getEmail().value;
            Boolean emailPriv = target.getEmail().isPrivate();
            String address = target.getAddress().value;
            Boolean addressPriv = target.getAddress().isPrivate();
            Set<Tag> tagSet = target.getTags().toSet();



            //Build name
            System.out.println("Editting: " +name+"\n");

           //Build phone num
            System.out.println("Current phone number: "+phoneNum+"\n");
            System.out.print("Keep current phone number? (Y/N):");
            String ans = "";
            while(!ans.equals("Y") || !ans.equals("N")) {
                ans=sc.nextLine().toUpperCase();
                if (ans.equals("Y")) {
                    //Do nothing
                }else if(ans.equals("N")) {
                    System.out.print("Please enter new phone number: ");
                    while(true) {
                        String phoneNum2=sc.nextLine();
                        try {
                            Integer phoneCheck=Integer.parseInt(phoneNum2);
                            System.out.println("New phone num" +phoneNum2);
                            phoneNum=phoneNum2;
                            break;
                        }catch(NumberFormatException e) {
                            System.out.println("Please enter a valid phone number");
                        }
                    }

                }else{
                    System.out.println("Please enter Y or N");
                }
            }

            //Build email
            System.out.println("Current email: "+email);
            System.out.print("Keep current email? (Y/N):");
            ans="";
            while(!ans.equals("Y") || !ans.equals("N")) {
                ans=sc.nextLine().toUpperCase();
                if (ans.equals("Y")) {
                    //Do nothing
                }else if(ans.equals("N")) {
                    System.out.print("Please enter new email: ");
                    Boolean validEmail=false;
                    while(validEmail==false) {
                        String email2=sc.nextLine();
                        validEmail=email.matches("[\\w\\.]+@[\\w\\.]+"); //From email class

                        if(validEmail==true) {
                            System.out.println("New email:" + email2);
                            email = email2;
                        }else {
                            System.out.println("Please enter a valid email");
                        }

                    }

                }else{
                    System.out.println("Please enter Y or N");
                }
            }

            //Build Address
            System.out.println("Current address: "+ address);
            System.out.print("Keep current address? (Y/N):");
            ans="";
            while(!ans.equals("Y") || !ans.equals("N")) {
                ans=sc.nextLine().toUpperCase();
                if (ans.equals("Y")) {
                    //Do nothing
                }else if(ans.equals("N")) {
                    System.out.print("Please enter new address: ");
                    String address2=sc.nextLine();
                    address = address2;
                }else{
                    System.out.println("Please enter Y or N");
                }
            }


            //Build Tags
            System.out.println("Current tags: ");
            for (Tag t: tagSet) {
                System.out.print(t.tagName+",");
            }
            System.out.print("Add additional tags? (Y/N):");
            ans="";
            while(!ans.equals("Y") || !ans.equals("N")) {
                ans=sc.nextLine().toUpperCase();
                if(ans.equals("N")) {
                    //Do nothing
                }else if(ans.equals("Y")) {
                    System.out.println("Type in new tag and press Enter or type \"-\" when you are done");
                    Boolean done=false;
                    do {
                        System.out.println("Enter new tag");
                        String newTag=sc.nextLine().trim();
                        Boolean valid=newTag.matches("\\p{Alnum}+"); //From tag class
                        if(newTag=="-") {
                            done=true;
                            System.out.println("Done");
                        }else if(!valid) {
                            System.out.println(Tag.MESSAGE_TAG_CONSTRAINTS);
                        }else {
                            try {
                                tagSet.add(new Tag(newTag));
                            }catch(IllegalValueException e) {
                                System.out.println(Tag.MESSAGE_TAG_CONSTRAINTS);
                            }
                            System.out.println("Added tag: " +newTag);
                        }

                    }while(!done);
                }else{
                    System.out.println("Please enter Y or N");
                }
            }

            //Build the whole person. I have not added the privacy option yet. Setting it to false as default
            Person toAdd=null;
            try {
                toAdd = new Person(
                       new Name(name),
                       new Phone(phoneNum, false),
                       new Email(email, false),
                       new Address(address, false),
                       new UniqueTagList(tagSet)
               );
            } catch (IllegalValueException e) {
                e.printStackTrace();
            }
            //remove old
            addressBook.removePerson(target);
            //Add new
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException e) {
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_PERSON);
        }
    }



}

