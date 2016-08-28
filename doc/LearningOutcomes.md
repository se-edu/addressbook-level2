# Learning Outcomes
Here are the things you should be able to do after studying this code and completing the
corresponding exercises.

------------------------------------------------------------------------------------------------------

### Apply Encapsulation `[LO-Encapsulation]`
  
##### Exercise: Encapsulate `CommandResult` class members 

* A member of the `CommandResult` class is not encapsulated. i.e. it is visible outside the object.
  Hide it so that it can only be accessed using methods provided.

------------------------------------------------------------------------------------------------------

### Implement a class `[LO-ImplementClass]`

##### Exercise: Split `Address` into more classes 
* Assume the address is entered in the following format `a/BLOCK, STREET, UNIT, POSTAL_CODE` <br>
  e.g. `a/123, Clementi Ave 3, #12-34, 231534`
* Split the `Address` class as follows.<br>
  <img src="images/AddressClasses.png" width='250'/>
* Update the user guide and tests to match.

------------------------------------------------------------------------------------------------------

### Follow the Single Responsibility Principle `[LO-SRP]`

The *Single Responsibility Principle (SRP)* states that a class should have only one reason to change. 
The code given follows SRP to a reasonable extent, but there are places where it can be applied further.
  
##### Exercise: Split `TextUi` class 

The exercise in the `LO-ImplementClass` section is somewhat related to SRP as well. 
Here's a slightly more difficult exercise.

* `TextUi` class has more than one responsibility. 
  Try to extract out the responsibility of Formatting text for display (e.g. adding decorations) in to a 
  separate class named `Formatter`.

##### Resources
* [An explanation of the SRP](http://www.oodesign.com/single-responsibility-principle.html) from www.oodesign.com
* [Another explanation (more detailed)](http://code.tutsplus.com/tutorials/solid-part-1-the-single-responsibility-principle--net-36074) 
  by Patkos Csaba
* [A book chapter on SRP](https://drive.google.com/file/d/0ByOwmqah_nuGNHEtcU5OekdDMkk/view) by Robert C. Martin

------------------------------------------------------------------------------------------------------

### Handle Exceptions `[LO-Exceptions]`

##### Exercise: Handle 'file deleted' situation 

* The current code does not handle the situation where the user deletes the storage file while the
  AddressBook program is running. Use exceptions to handle that situation.

------------------------------------------------------------------------------------------------------

### Use Inheritance to achieve code reuse `[LO-Inheritance]`

Note how the `Command` class contains some code that is reused by some of its child classes. 
By defining `*Command` classes as child classes of `Command`, we have avoided having to duplicate those methods
in multiple `*Command` classes.

##### Exercise: Extract a `Contact` class 

* Extract commonalities from `Phone`, `Email` and `Address` classes into a parent class called `Contact`.<br>
<img src="images/ContactClassHierarchy.png" width='250' />

