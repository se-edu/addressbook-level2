javac  ..\src\seedu\addressbook\Addressbook.java -d ..\bin
java -classpath ..\bin seedu.addressbook.AddressBook < input.txt > actual.txt
FC actual.txt expected.txt