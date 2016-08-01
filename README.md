# AddressBook (Level 1)
* This is a CLI (Command Line Interface) AddressBook application written in procedural fashion. 
* It is a Java sample application intended for students learning Software Engineering while using Java as 
  the main programming language. 
* It provides a reasonably well-written code example that is significantly bigger than what students 
  usually write in data structure modules. 
* It can be used to achieve a number of beginner-level learning outcomes without running into the complications 
  of OOP or GUI programmings.

# Learning outcomes

### `[LO-IdeSetup]` Set up a project in an IDE

##### Exercise : Setup project in Eclipse 
* Download the source code for this project, using one of the following options:
   1. Go to the [Releases](releases) tab and download the src.zip from the latest release.
   2. Clone this repo (if you know how to use Git)
* [Set up](#Setting-up) the project in Eclipse.
* [Run the program](#Running-the-program) from within Eclipse, and try the features described in 
  the [Usage](#Usage) section 

### `[LO-CodeNavigation]` Navigate code efficiently
The `AddressBook.java` code is too big to navigate by scrolling. 
Navigating code using shortcuts is a more efficient option. 
For example, <kbd>F3</kbd> will navigate to the definition of the method/variable at the cursor.
 
##### Ex : Learn to navigate code using shortcuts
Learn some Eclipse code navigation shortcuts 
(you can use Web resources like [this one](https://www.shortcutworld.com/en/win/Eclipse.html)this one). 
For example, how to,
  * go to the definition of a method
  * go back to the previous location
  * view the documentation of a method from where the method is being used, 
    without navigating to the method itself
  * find where a method/field is being used

### `[LO-Debugging]` Use a debugger

##### Ex : Learn to step through code using the debugger
Learn Eclipse debugging features. 
Demonstrate your debugging skills using the AddressBook code. 

Here are some things you can do in your demonstration.
1. Set a break point
2. Run the program in debug mode
3. Step through a few lines of code while examining variable values
4. Step into, and out of, methods as you step through the code
5. ...

### `[LO-AutomatedCliTesting]`Automate CLI testing 

##### Ex : Practice automated CLI testing

* Run the tests as explained in the [Testing](#Testing) section.
* Add few more tests to the `input.txt`. Run the tests. It should fail. 
  Modify `expected.txt` to make the tests pass again.
* Edit the `AddressBook.java` to modify the behavior slightly and modify tests to match.

### `[LO-EnumsEtc]` Use Java Enums, Collections, Var args

##### Ex 1: Use Enums

##### Ex 2: Use Collections

##### Ex 3: Use Varargs


### `[LO-MethodAbstraction]` Abstract methods well
Code has small methods. Each one is doing a single task. SLAP applied.
e.g. main method

##### Ex : Reduce SLAP of method

### Follow a coding standard

### Apply coding best practices

### Refactor code


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



# Contributors
* Jeffry Hartanto : Created a ToDo app that was used as the basis for this code. 
* [Leow Yijin](@yijinl) : Main developer for the first version of the AddressBook-level1
* [Damith C. Rajapakse](@damithc) : Project Advisor

# Contact us
* Reporting bugs
* Suggesting features
* Contributing as developers