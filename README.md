# AddressBook (Level 2)
A Java sample application for students. An AddressBook application that uses OOP basics.

Learning objectives:
* 
* 

# Setting Up

# Usage
```sh
c:> java AddressBook addressbook.txt

c:> add John Doe p/3232232 e/johnd@gmail.com a/311, Clementi Ave 2, #25-177 t/friends

c:> add Tim Hank pp/3434567 pe/timh@gmail.com t/friends t/colleagues

c:> list
1. John Doe 3232232 johnd@gmail.com 311, Clementi Ave 2, #25-177 [friends]
2. Tim Hank [friends][colleagues]

c:> delete 1

c:> search tim
1. Tim Hank [friends][colleagues]

c:> view 1
Tim Hank [friends][colleagues]

c:> viewall 1
Tim Hank 3434567 timh@gmail.com [friends][colleagues]

c:> clear
```
What's different from AddressBook-Level1:
* Support for storing address (`a/`) and tags (`t/`)
* Support for marking a contact detail as 'private' (`pa/`) (`pe/`) (`pp/`) 
* View details of a person (`view` : shows non-private details), (`viewall` : shows all details)

# Testing

# Design

# Exercises

#### ABL2-E1: Extract more classes 
(extract Unit, Street, PostalCode)
             
#### ABL2-E2: Improve exception handling

#### ABL2-E3: Add an interface 
(e.g. ReadOnlyPerson, add ReadOnlyTag)

#### ABL2-E4:  Add abstract class 
(e.g. Contact::getDisplayString())

#### ABL2-E4:  Encapsulate a variable 
(apply to Contact::isPrivate)

#### ABL2-E4:  Add some unit tests

#### ABL2-E4:  Add some unit tests using TDD