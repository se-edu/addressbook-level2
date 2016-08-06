# AddressBook (Level 1)
* This is a CLI (Command Line Interface) Address Book application **written in procedural fashion**. 
* It is a Java sample application intended for students learning Software Engineering while using Java as 
  the main programming language. 
* It provides a **reasonably well-written** code example that is **significantly bigger** than what students 
  usually write in data structure modules. 
* It can be used to achieve a number of beginner-level [learning outcomes](#learning-outcomes) without 
  running into the complications of OOP or GUI programmings.

**Table of Contents**
* [User Guide](#user-guide)
* [Developer Guide](#developer-guide)
* [Learning Outcomes](#learning-outcomes)
* [Contributors](#contributors)
* [Contact Us](#contact-us)

-----------------------------------------------------------------------------------------------------
# User Guide

This product is not meant for end-users and therefore there is no user-friendly installer. 
Please refer to the [Setting up](#setting-up) section to learn how to set up the project.

#### Starting the program

**Using Eclipse**

1. Find the project in the `Project Explorer` or `Package Explorer` (usually located at the left side)
2. Right click on the project
3. Click `Run As` > `Java Application`
4. The program now should run on the `Console` (usually located at the bottom side)
5. Now you can interact with the program through the `Console`

**Using Command Line**

1. 'Build' the project using Eclipse
2. Open the `Terminal`/`Command Prompt`
3. `cd` into the project's `bin` directory
4. Type `java seedu.addressbook.AddressBook`, then <kbd>Enter</kbd> to execute
5. Now you can interact with the program through the CLI

#### Viewing help : `help`
Format: `help` 
 > Help is also shown if you enter an incorrect command e.g. `abcd`
 
#### Adding a person: `add`
> Adds a person to the address book

Format: `add NAME p/PHONE_NUMBER e/EMAIL`  
> Words in `UPPER_CASE` are the parameters<br>
  Phone number and email can be in any order but the name must come first.

Examples: 
* `add John Doe p/98765432 e/johnd@gmail.com`
* `add Betsy Crowe e/bencrowe@gmail.com p/1234567 `

#### Listing all persons : `list`

> Shows a list of persons, as an indexed list, in the order they were added to the address book, 
oldest first.

Format: `list`  

#### Finding a person by keyword `find`
> Finds persons that match given keywords

Format: `find KEYWORD [MORE_KEYWORDS]`  
> The search is case sensitive, the order of the keywords does not matter, only the name is searched, 
and persons matching at least one keyword will be returned (i.e. `OR` search).

Examples: 
* `find John`
  > Returns `John Doe` but not `john`
   
* `find Betsy Tim John`
  > Returns Any person having names `Betsy`, `Tim`, or `John`

#### Deleting a person : `delete`

Format: `delete INDEX`  
> Deletes the person at the specified `INDEX`. 
  The index refers to the index numbers shown in the most recent listing.

Examples: 
* `list`<br>
  `delete 2`
  > Deletes the 2nd person in the address book.
  
* `find Betsy` <br> 
  `delete 1`
  > Deletes the 1st person in the results of the `find` command.

#### Clearing all entries : `clear`
> Clears all entries from the address book.  
Format: `clear`  

#### Exiting the program : `exit`
Format: `exit`  

#### Saving the data 
Address book data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

#### Changing the save location
Address book data are saved in a file called `addressbook.txt` in the project root folder.
You can change the location by specifying the file path as a program argument.

Examples: 
* `java seedu.addressbook.AddressBook mydata.txt`

> The file name must end in `.txt` for it to be acceptable to the program.

> When running the program inside Eclipse, there is a way to set command line parameters 
  before running the program.

-----------------------------------------------------------------------------------------------------
# Developer Guide

## Setting up

**Prerequisites**

* JDK 8 or later 
* Eclipse IDE

**Importing the project into Eclipse**

1. Open Eclipse
2. Click `File` > `Import`
3. Click `General` > `Existing Projects into Workspace` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

## Testing

**Windows**

1. Open a DOS window in the `test` folder
2. Run the `runtests.bat` script
3. If the script reports that there is no difference between `actual.txt` and `expected.txt`, 
   the test has passed.

**Mac/Unix/Linux**

Create a script similar to the windows version.
You can use the `diff` command in place of the `FC` command.

**Troubleshooting test failures**

* Problem: How do I examine the exact differences between `actual.txt` and `expected.txt`?<br>
  Solution: You can use a diff/merge tool with a GUI e.g. WinMerge (on Windows)
* Problem: The two files look exactly the same, but the test script reports they are different.<br>
  Solution: This can happen because the line endings used by Windows is different from Unix-based
  OSes. Convert the actual.txt to the format used by your OS using some [utility](https://kb.iu.edu/d/acux).
* Problem: Test fails during the very first time.<br>
  Solution: The output of the very first test run could be slightly different because the program
  creates a new storage file. Tests should pass from the 2nd run onwards.

-----------------------------------------------------------------------------------------------------
# Learning Outcomes
Here are the things you should be able to do after studying this code and completing the
corresponding exercises.

### Set up a project in an IDE `[LO-IdeSetup]`

##### Exercise : Setup project in Eclipse 
* Learn [how to set up a new project in Eclipse]
  (https://se-edu.github.io/addressbook-level1/doc/Getting started with Eclipse.pptx). <br>
  Create a new project in Eclipse and write a small HelloWorld program.
* Download the source code for this project, using one of the following options:
   1. Go to the 'Releases' tab, download the `src.zip` from the latest release, and unzip content.
   2. Clone this repo (if you know how to use Git) to your Computer.
* [Set up](#setting-up) the project in Eclipse.
* [Run the program](#starting-the-program) from within Eclipse, and try the features described in
  the [User guide](#user-guide) section

### Navigate code efficiently `[LO-CodeNavigation]`
The `AddressBook.java` code is rather, which makes it cumbersome to navigate by scrolling alone. 
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
Prerequisite: `[LO-IdeSetup]`

Learn Eclipse debugging features from [these slides](https://se-edu.github.io/addressbook-level1/doc/Debugging with Eclipse.pptx)
or other online resources.<br>
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
* Examine the test script to understand how the script works.
* Add a few more tests to the `input.txt`. Run the tests. It should fail.<br> 
  Modify `expected.txt` to make the tests pass again.
* Edit the `AddressBook.java` to modify the behavior slightly and modify tests to match.

### Use Collections `[LO-Collections]`

Note how the `AddressBook` class uses `ArrayList<>` class (from the Java `Collections` library)
to store a list of `String` or `String[]` objects.

Resources: [ArrayList class tutorial (from javaTpoint.com)](http://www.javatpoint.com/ArrayList-in-collection-framework)

##### Ex: Use `HashMap`

Currently, a person's details are stored as a `String[]`. Modify the code to use a `HashMap<String, String>` instead.
A sample code snippet is given below.
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

Similar to the exercise in the `LO-Collections` section, but also bring in Java `enum` feature.
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



### Follow a coding standard `[LO-CodingStandard]`

The given code follows the [coding standard](http://www.comp.nus.edu.sg/~cs2103/AY1617S1/contents/coding-standards-java.html) 
for the most part.

This learning outcome is covered by the exercise in `[LO-Refactor]`.

### Apply coding best practices `[LO-CodingBestPractices]`
Most of the given code follows the best practices mentioned
[in this document](http://www.comp.nus.edu.sg/~cs2103/AY1415S1/files/%255bHandout%2520for%2520L2P1%255d%2520%2520Good%2520Code,%2520Bad%2520Code%2520-%2520Toward%2520Production%2520Quality%2520Code.pdf).

This learning outcome is covered by the exercise in `[LO-Refactor]`

### Refactor code `[LO-Refactor]`

* [A catalog of common refactorings](http://refactoring.com/catalog/) - from http://refactoring.com/catalog
* [Screencast] [A short refactoring demo using Eclipse](http://www.youtube.com/watch?v=7KDruqCzdpc)

#### Ex: Refactor the code to make it better
Note: this exercise covers two other Learning Outcomes: `[LO-CodingBestPractices]`, `[LO-CodingBestPractices]`
* Improve the code in the following ways,
  * Fix [coding standard](http://www.comp.nus.edu.sg/~cs2103/AY1617S1/contents/coding-standards-java.html) 
    violations.
  * Fix violations of the best practices given in [in this document](http://www.comp.nus.edu.sg/~cs2103/AY1415S1/files/%255bHandout%2520for%2520L2P1%255d%2520%2520Good%2520Code,%2520Bad%2520Code%2520-%2520Toward%2520Production%2520Quality%2520Code.pdf)
  * Any other change that you think will improve the quality of the code.
* Try to do the modification as a combination of standard refactorings given in this
  [catalog](http://refactoring.com/catalog/)
* As far as possible, use automated refactoring features in Eclipse.
* If you know how to use Git, commit code after each refactoring.<br>
  In the commit message, mention which refactoring you applied.<br>
  Example commit messages: `Extracted variable isValidPerson`, `Inlined method isValidPerson()`
* Remember to run the test script after each refactoring to prevent [regressions](https://en.wikipedia.org/wiki/Software_regression).

### Abstract methods well `[LO-MethodAbstraction]`

Notice how most of the methods in `AddressBook` are short, focused, and written at a single
level of abstraction (_cf_ [SLAP](http://programmers.stackexchange.com/questions/110933/how-to-determine-the-levels-of-abstraction))

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

##### Ex 1: Reduce SLAP of method
In the `main` method, replace the `processProgramArgs(args)` call with the actual code of that method.
The `main` method no longer has SLAP. Notice how mixing low level code with high level code reduces
readability.

##### Ex 2: Refactor the code to make it worse!
Sometimes, going in the wrong direction can be a good learning experience too. 
In this exercise, we explore how low code code quality can go.
* Refactor the code to make the code as worse as possible.<br>
  i.e. How bad can you make it without breaking the functionality while still making it look like it was written by a 
  programmer (but a very bad programmer :-).
* In particular, inlining methods can worsen the code quality fast.

### Work in a 1kLoC code base`[LO-1KLoC]`

#### Ex : Enhance the code
Enhance the AddressBook to prove that you can successfully work in a codebase of 1KLoC. <br>
Remember to change code in small steps and commit after each significant change.

Some suggested enhancements:

* Make the `find` command case insensitive e.g. `find john` should match `John`
* Add a `sort` command that can list the persons in alphabetical order
* Add an `edit` command that can edit properties of a specific person

-----------------------------------------------------------------------------------------------------
# Contributors

* [Jeffry Hartanto](http://github.com/jeffryhartanto) : Created a ToDo app that was used as the basis for this code.
* [Leow Yijin](http://github.com/yijinl) : Main developer for the first version of the AddressBook-level1
* [Damith C. Rajapakse](http://www.comp.nus.edu.sg/~damithch) : Project Advisor

-----------------------------------------------------------------------------------------------------
# Contact Us

* **Bug reports, Suggestions** : Post in our [issue tracker](https://github.com/se-edu/addressbook-level1/issues)
  if you noticed bugs or have suggestions on how to improve.
* **Contributing** : We welcome pull requests.