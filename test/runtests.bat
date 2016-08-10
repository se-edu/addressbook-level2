@ECHO OFF
REM compile the code into the bin folder
javac  javac  -cp ..\src -Xlint:none -d ..\bin ..\src\seedu\addressbook\Main.java

REM run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ..\bin seedu.addressbook.Main < input.txt > actual.txt

REM compare the output to the expected output
FC actual.txt expected.txt