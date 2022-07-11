package designpattern;

public class BSingleRepositoryPrinciplePattern {

}
/*

Single Responsibility Principle Design pattern:

SRP is one of the design pattern that states that every module , class
should have single responsibility over a single part of that programs
functionality

Why SRP? Real Life Example
When a class is highly coupled with more responsibilities
any small change to requirement could easily lead to many changes in other
parts of code which is harder to maintain

SRP helps developers write code that are decoupled , where each class
has its own job and encapsulate responsibilities to other classes
if specifications of this job changes , developer makes changes to that
specific class only

The change is less likely to break the entire application as other
classes should still be doing their job as before, unless of course they
were broken in first place


Suppose we see page objects class , all the elements in page are in a class
instead we can divide the page based on some tabs inside it , thus isolating
the class with one responsibility

*/
//*******************************************************************************//
/*
 
Breaking the page object pattern into multiple sub components:

in our maven project
we have main and test folders

inside src/main/java - create a class - TravelHomePage

previuosly we used to place all the web elements of a page in a single class

go to https://rahulshettyacademy.com/dropdownsPractise/
and Flights

Lets create seperate class for naviagtion bar , footer section 

and when we r in FooterNavigation we should be able to limit scope
of the driver to footer only

for this create a abstract class , and extend it to the FooterNav class

 */
