@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del actual.txt

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\seedu\addressbook\Main.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE ********** 
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ..\bin seedu.addressbook.Main < input.txt > actual.txt

REM compare the output to the expected output
FC actual.txt expected.txt