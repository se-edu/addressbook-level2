@ECHO OFF
REM compile the code into the bin folder
javac  ..\src\seedu\addressbook\Addressbook.java -d ..\bin

REM run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ..\bin seedu.addressbook.AddressBook < input.txt > actual.txt

REM compare the output to the expected output
FC actual.txt expected.txt