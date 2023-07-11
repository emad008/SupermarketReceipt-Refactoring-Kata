# Code smells and problems: (ordered by priority of resolving)

## 1) Use inheritance for Offer class:
See handleOffers method of ShoppingCart class. We have used an if statement for each of 
special offer types we have, and it's needless to say that if we continue with the same
technique for future offer types we will end up with a huge method with tons of ifs.

It's better to use polymorphism for these kinds of problems. Let the offer object calculate
the amount of discount.

#### What have been done?
I have created new classes for each offer type, and they extend a base Offer class. I have implemented 
offerDiscountOnPurchase for each of the subclasses. In handleOffers method of ShoppingCart class, I have used the
offerDiscountOnPurchase function for calculating a discount.

## 2) Remove redundancy:
We have stored same value multiple times in multiple parts of the code. (Search 
"TODO 2"). It's easy to forget to keep both stored values updated, and it is only rational 
when we can achieve higher performance but here, there is no performance requirement

#### What have been done?
TotalPrice can be calculated from the quantity and the unitPrice, so I have removed the attribute 
from ReceiptItem class and replaced the getTotalPrice method with multiplication of the quantity and
the unitPrice. 
I have removed ProductQuantity list from ShoppingCart class, because there were another
way of calculating it from product-quantities map.

## 3) Unusual Classes:
Usually in object-oriented design, classes are mappings of reality. We map the real 
things and functionalities into classes with data and behavior. So our classes have been
rooted in a metaphor or at least our understanding of the expected functionality.

So does a Teller mean? What is usage of a ProductQuantity class? What are their usages and roots?
They are classes we have made up to make it work, but they can be a problem for further development
as they are not something real, and they don't have a precise boundary or behavior 

#### What have been done?
I have renamed the unusual Teller class, because the name didn't make any sense to me.
Actually I can think the discount calculator as a cash register, who has a list of offers
and can calculate discount for the shopping cart.

I have also removed the ProductQuantity class because, It didn't provide any functionality,
and also I couldn't fit it in the story.

## 4) Extract method:
Extract one piece of logic into a separate method for more readability and usability


#### What have been done?
I have extracted the "for" who was adding products to a receipt inside checksOutArticlesFrom method 
of cash register class.

TotalItemsPrices and TotalDiscountAmount calculations have been extracted into calculator methods.

presentReceiptItems and presentDiscounts have been extracted too.

## 5) Replace temp with query
Instead of calculating a specific value, query it from another method or even 
another class who is the expert.

#### What have been done?
Some temporary variables were not needed anymore and direct method call was readable and enough.

The discount description, was fully provided by the creator offer class, so I have passed
the offer class to the discount and now, for description, discount asks the offer object.

handleOffers method have been removed also. Because in my opinion, the shopping cart wasn't
the right one (the expert) to do it. I think cash register must take the discounts calculation
responsibility.

## 6) Unused methods:
Unused methods lower the code readability. 

## 7) Use built in functionality:
Using a built-in function or a library will make your code smaller. You can also trust
it more, because it had been tested a lot more.

#### What have been done?
I have used built in getOrDefault functionality of Map object to increase quantity of 
a product inside map.

I have used String.repeat() method to concat a number of spaces to a string.

I have also used StringBuilder object to present ReceiptItem, instead of using
a string variable along-with '+' operator.


## 8) Consistent naming:
When there are multiple names or labels for one meaning there will be confusion.

#### What have been done?
We have used different names for total cost of a series of products. One name was 
price and other was totalPrice. We have also used price and unitPrice for just one 
kilo or item of a product. I have chosen the price-unitPrice language and removed the 
totalPrice word used in different parts of code.
