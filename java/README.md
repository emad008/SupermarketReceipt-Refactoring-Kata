# Code smells and problems: (ordered by priority of resolving)

## 1) Use inheritance for Offer class
See handleOffers method of ShoppingCart class. We have used an if statement for each of 
special offer types we have, and it's needless to say that if we continue with the same
technique for future offer types we will end up with a huge method with tons of ifs.

It's better to use polymorphism for these kinds of problems. Let the offer object calculate
the amount of discount.

## 2) Remove redundancy:
We have stored same value multiple times in multiple parts of the code. (Search 
"TODO 2"). It's easy to forget to keep both stored values updated, and it is only rational 
when we can achieve higher performance but here, there is no performance requirement

## 3) Unusual Classes:
Usually in object-oriented design, classes are mappings of reality. We map the real 
things and functionalities into classes with data and behavior. So our classes have been
rooted in a metaphor or at least our understanding of the expected functionality.

So does a Teller mean? What is usage of a ProductQuantity class? What are their usages and roots?
They are classes we have made up to make it work, but they can be a problem for further development
as they are not something real, and they don't have a precise boundary or behavior 

## 4) Extract method:
Extract one piece of logic into a separate method for more readability and usability

## 5) Replace temp with query
Instead of calculating a specific value, query it from another method or even 
another class who is the expert.

## 6) Unused methods:
Unused methods lower the code readability. 

## 7) Use built in functionality:
Using a built-in function or a library will make your code smaller. You can also trust
it more, because it had been tested a lot more.

## 8) Consistent naming:
When there are multiple names or labels for one meaning there will be confusion.

## 6) Pass the logic to the class:
There are several place that we use 
