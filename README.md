# AddressBook (Level 1)
* This is an AddressBook application written in procedural fashion.
* It is a Java sample application intended for students learning Java. 
* Learning objectives:
  * 

# Setting up
### Prerequisites
* JDK 8
* Eclipse IDE

### Importing the project into Eclipse
1. Open Eclipse
2. Click `File` > `Import`
3. Click `General` > `Existing Projects into Workspace` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

# Running the program
### Using Eclipse
1. Find the project in the `Project Explorer` or `Package Explorer` (usually located at the left side)
2. Right click on the project
3. Click `Run As` > `Java Application`
4. The program now should run on the `Console` (usually located at the bottom side)
5. Now you can interact with the program through the `Console`

### Using Command Line Interface
1. Open `Terminal` for Unix or `Command Prompt` for Windows
2. Move to the project's bin directory, using `cd <project_path>/bin` for Unix and `dir <project_path>/bin` for Windows
3. Type `java nus.cs2103.addressbook.AddressBook [storage_file_name.txt]`, then <kbd>Enter</kbd> to execute
4. Command above enables you to interact with the program through the CLI and data will be saved in the file you specified

### Usage
```sh
Welcome to ToDoBuddy!
Enter command: help 

add      : Adds a person to the addressbook. 
		Parameters: [name] p/[phone number] e/[email]
	 	Example: add John Doe p/98765432 e/johnd@gmail.com

find     : Finds all persons whose names contain any of the specified keywords (case-sensitive) and displays them as a list with index numbers. 
	 	Parameters: [keyword 1] [keyword 2] ...
	 	Example: find alice bob charlie

list     : Displays all persons as a list with index numbers. 
	 	Example: view 

delete   : Deletes a person identified by the index number used in the last find/list call. 
	 	Parameters: [target's index number] 
	 	Example: delete 1 

clear    : Clears address book permanently.
	 	Example: clear 

exit     : Exit the program.
	 	Example: exit 

help     : Shows program usage instructions. 
	 	Example: help
```

# Testing
Make sure the file `storage.txt` exists in the working directory.

1. Open `Terminal` for Unix or `Command Prompt` for Windows
2. Move to the project's bin directory, using `cd <project_path>/bin` in Unix and `dir <project_path>/bin` in Windows
3. Type `java nus.todobuddy.ToDoBuddy storageFilename.txt < ../test/input.txt > ../test/output.txt`, 
then `Enter` to execute
4. Command above will tell the program to save the data in `storageFilename.txt`, 
then run the command provided from `../testing/input.txt`. Finally, it will write the output to `../testing/output.txt`

Sample Windows batch file to automate testing:
```sh
javac  ..\src\nus\cs2103\addressbook\Addressbook.java -d ..\bin
java -classpath ..\bin nus.cs2103.addressbook.AddressBook < input.txt > actual.txt
FC actual.txt expected.txt
```
Put the code above into a file `test\test.bat`,
open a DOS window in the `test` folder,
and run the `call test.bat` command.

# Exercises

### ABL1-E1: 

### ABL1-E2: 

### ABL1-E3: 

# External Acknowledgements
* Jeffry

# Contact us
* Reporting bugs
* Suggesting features
* Contributing as developers