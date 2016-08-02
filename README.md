# AddressBook (Level 1)
* This is a CLI (Command Line Interface) AddressBook application written in procedural fashion. 
* It is a Java sample application intended for students learning Software Engineering while using Java as 
  the main programming language. 
* It provides a reasonably well-written code example that is significantly bigger than what students 
  usually write in data structure modules. 
* It can be used to achieve a number of beginner-level learning outcomes without running into the complications 
  of OOP or GUI programmings.

# Learning outcomes
Here are the things a student should be able to do after studying this code and completing the
corresponding exercises.

### Set up a project in an IDE `[LO-IdeSetup]`

##### Exercise : Setup project in Eclipse 
* Download the source code for this project, using one of the following options:
   1. Go to the 'Releases' tab, download the `src.zip` from the latest release, and unzip content.
   2. Clone this repo (if you know how to use Git) to your Computer.
* [Set up](#setting-up) the project in Eclipse.
* [Run the program](#running-the-program) from within Eclipse, and try the features described in 
  the [Usage](#usage) section 

### Navigate code efficiently `[LO-CodeNavigation]`
The `AddressBook.java` code is too big to navigate by scrolling. 
Navigating code using IDE shortcuts is a more efficient option. 
For example, <kbd>F3</kbd> will navigate to the definition of the method/field at the cursor.
 
##### Ex : Learn to navigate code using shortcuts
Learn some Eclipse code navigation shortcuts 
(you can use Web resources like [this one](https://www.shortcutworld.com/en/win/Eclipse.html)). 
For example, learn the shortcuts to,
  * go to the definition of a method
  * go back to the previous location
  * view the documentation of a method from where the method is being used, 
    without navigating to the method itself
  * find where a method/field is being used
  * ...

### Use a debugger `[LO-Debugging]`

##### Ex : Learn to step through code using the debugger
Learn Eclipse debugging features. 
Demonstrate your debugging skills using the AddressBook code. 

Here are some things you can do in your demonstration.

1. Set a 'break point'
2. Run the program in debug mode
3. 'Step through' a few lines of code while examining variable values
4. 'Step into', and 'step out of', methods as you step through the code
5. ...

### Automate CLI testing `[LO-AutomatedCliTesting]`

##### Ex : Practice automated CLI testing

* Run the tests as explained in the [Testing](#testing) section.
* Add a few more tests to the `input.txt`. Run the tests. It should fail.<br> 
  Modify `expected.txt` to make the tests pass again.
* Edit the `AddressBook.java` to modify the behavior slightly and modify tests to match.

### Use Collections `[LO-Collections]`

Note how the `AddressBook` class uses `ArrayList<>` class (from the Java `Collections` library)
to store a list of `String` or `String[]` objects.

Resources: [ArrayList class tutorial (from javaTpoint.com)](http://www.javatpoint.com/ArrayList-in-collection-framework)

##### Ex: Use `HashMap`

Currently, a person's details are stored as a `String[]`. Modify the code to use a `HashMap<String, String>` instead.
```java
private static final PERSON_PROPERTY_NAME = "name";
private static final PERSON_PROPERTY_EMAIL = "email";
...
HashMap<String,String> john = new HashMap<>();
john.put(PERSON_PROPERTY_NAME, "John Doe");
john.put(PERSON_PROPERTY_EMAIL, "john.doe@email.com");
//etc.
```
Resources: [HashMap tutorial (from beginnersbook.com)](http://beginnersbook.com/2013/12/hashmap-in-java-with-example/)

### Use Enums `[LO-Enums]`

##### Ex: Use `HashMap` + `Enum`

Similar the exercise in the `LO-Collections` section, but also bring in Java `enum` feature.
```java
private enum PersonProperty  {NAME, EMAIL, PHONE};
...
HashMap<PersonProperty,String> john = new HashMap<>();
john.put(PersonProperty.NAME, "John Doe");
john.put(PersonProperty.EMAIL, "john.doe@email.com");
//etc.
```

### Use Varargs `[LO-Varargs]`

Note how the `showToUser` method uses
[Java Varargs feature](http://docs.oracle.com/javase/1.5.0/docs/guide/language/varargs.html) .

##### Ex: Use Varargs
Modify the code to remove the use of the Varargs feature.
Compare the code with and without the varargs feature.


### Abstract methods well `[LO-MethodAbstraction]`

Notice how most of the methods in `AddressBook` are short, focused, and written at a single
level of abstraction (cf [SLAP](http://programmers.stackexchange.com/questions/110933/how-to-determine-the-levels-of-abstraction))

Here is an example.
```java
    public static void main(String[] args) {
        showWelcomeMessage();
        processProgramArgs(args);
        loadDataFromStorage();
        while (true) {
            userCommand = getUserInput();
            echoUserCommand(userCommand);
            String feedback = executeCommand(userCommand);
            showResultToUser(feedback);
        }
    }
```

##### Ex : Reduce SLAP of method
In the `main` method, replace the `processProgramArgs(args)` call with the actual code of that method.
The `main` method no longer has SLAP. Notice how mixing low level code with high level code reduces
readability.

### Follow a coding standard `[LO-CodingStandard]`

The given code follows the [coding standard](http://www.comp.nus.edu.sg/~cs2103/AY1617S1/contents/coding-standards-java.html) 
for the most part.

#### Ex : Fix coding standard violations

Find and fix coding standard violations in the code, if any.

### Apply coding best practices `[LO-CodingBestPractices]`

#### Ex : Find violation of coding best practices
See if you can find where the code contradicts best practices mentioned
[in this document](http://www.comp.nus.edu.sg/~cs2103/AY1415S1/files/%255bHandout%2520for%2520L2P1%255d%2520%2520Good%2520Code,%2520Bad%2520Code%2520-%2520Toward%2520Production%2520Quality%2520Code.pdf)

### Refactor code `[LO-Refactor]`

#### Ex 1: Refactor the code to make it better
* Refactor the code, applying one refactoring at a time.
* If you are using Git, commit code after each refactoring.<br>
  In the commit message, mention which refactoring you applied.<br>
  Example commit messages: `Extracted variable isValidPerson`, `Inlined method isValidPerson()`
* As far as possible, use automated refactoring features in Eclipse.
* Remember to run the test script after each refactoring to prevent [regressions](https://en.wikipedia.org/wiki/Software_regression).

#### Ex 2: Refactor the code to make it worse!
* Similar to the previous exercise, but this time try to make the code as bad as possible.<br>
  How bad can you make it without breaking the functionality while still making it look like it was written by a 
  programmer (but a very bad programmer :-).
* In particular, inlining methods can worsen the code quality fast.

# Setting up
### Prerequisites
* JDK 8 or later 
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

### Using Command Line
1. Open the `Terminal`/`Command Prompt` 
2. `cd` into the project's `bin` directory
3. Type `java seedu.addressbook.AddressBook`, then <kbd>Enter</kbd> to execute
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

Setting storage location

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
java -classpath ..\bin seedu.addressbook.AddressBook < input.txt > actual.txt
FC actual.txt expected.txt
```
Put the code above into a file `test\test.bat`,
open a DOS window in the `test` folder,
and run the `call test.bat` command.

Troubleshooting test results:
* differences between line endings
* diffing the two files
* failure during first run

# Contributors
* [Jeffry Hartanto](http://github.com/jeffryhartanto) : Created a ToDo app that was used as the basis for this code.
* [Leow Yijin](http://github.com/yijinl) : Main developer for the first version of the AddressBook-level1
* [Damith C. Rajapakse](http://www.comp.nus.edu.sg/~damithch) : Project Advisor

# Contact us
* **Bug reports, Suggestions** : Post in our [issue tracker](https://github.com/se-edu/addressbook-level1/issues)
  if you noticed bugs or have suggestions on how to improve.
* **Contributing** : We welcome pull requests.