# AddressBook (Level 1)
* This is an AddressBook application written in procedural fashion.
* It is a Java sample application intended for students learning Java. 
* Learning objectives:
  * 

# Setting up
### Prerequisites
*
* 

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
3. Type `java nus.todobuddy.ToDoBuddy storageFilename.txt`, then `Enter` to execute
4. Command above enables you to interact with the program through the CLI and data will be saved in `storageFilename.txt`

### Usage
```sh
Welcome to ToDoBuddy!
Enter command:help 
add      : Adds a to-do items to the storage. 
	 Parameter: [priority] [duration] [title] 
	 Example: add p/H d/0.5 Finish CS2103 Assignment 
find     : Retrieve to-do items that match the keyword, with/without priority. 
	 Parameter: [keyword] [priority] 
	 Example: find CS2103 or find CS2103 p/H 
view     : Shows sorted to-do list with its index numbers. 
	 Example: view 
delete   : Deletes to-do item based on index numbers. 
	 Parameter: [index number] 
	 Example: delete 1 
clear    : Clears to-do list permanently. 
	 Example: clear 
exit     : Exit from this program. 
	 Example: exit 
help     : Shows program usage instructions. 
	 Example: help

Enter command:add p/H d/1.5 Do CS2103 coding exercise
New to-do item added: Do CS2103 coding exercise, Priority p/H, Duration d/1.5
Enter command:add p/L d/0.5 Check CS2103 next lecture slides
New to-do item added: Check CS2103 next lecture slides, Priority p/L, Duration d/0.5
Enter command:find CS2103
Do CS2103 coding exercise, Priority H, Duration 1.5 
Check CS2103 next lecture slides, Priority L, Duration 0.5 
Enter command:find CS5430
No to-do item exists with key word CS5430
Enter command:find CS5430 p/L
No to-do item exists with key word CS5430 and priority p/L
Enter command:find CS2103 p/L
Check CS2103 next lecture slides, Priority L, Duration 0.5 
Enter command:view
1. Do CS2103 coding exercise, Priority H, Duration 1.5 
2. Check CS2103 next lecture slides, Priority L, Duration 0.5 
Enter command:delete 1
Deleted to-do item: Do CS2103 coding exercise, Priority H, Duration 1.5
Enter command:view
1. Check CS2103 next lecture slides, Priority L, Duration 0.5 
Enter command:clear
To-do list has been cleared!
Enter command:view
No to-do item to be viewed
Enter command:exit
```

# Testing
1. Open `Terminal` for Unix or `Command Prompt` for Windows
2. Move to the project's bin directory, using `cd <project_path>/bin` in Unix and `dir <project_path>/bin` in Windows
3. Type `java nus.todobuddy.ToDoBuddy storageFilename.txt < ../testing/input.txt > ../testing/output.txt`, 
then `Enter` to execute
4. Command above will tell the program to save the data in `storageFilename.txt`, 
then run the command provided from `../testing/input.txt`. Finally, it will write the output to `../testing/output.txt`
# Exercises

### ABL1-E1: 

### ABL1-E2: 

### ABL1-E3: 

# Acknowledgements
* Jeffry

# Contact us
* Reporting bugs
* Suggesting features
* Contributing as developers