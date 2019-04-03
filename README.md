# CSC301 - Assignment 2 - Pizza Parlour Application
--------------------------------------------------
### Dev 1: Atharva Karandikar
### Dev 2: Mateen Ahmed, Sheikh

## Functionality
--------------------------------------------------
The program developed is for a hypothetical pizza parlour business. The program is designed to:
1) Accept the pizza order - type, size, toppings.
2) Accept the drinks - Coke, Pepsi, etc.
3) Accept the customer details - name, address, phone #
4) Accepting the delivery option - Pickup, In house delivery, Uber Eats, Foodora
    a) If choosing 'Uber Eats Option', order/customer details are output to a file on disk in JSON format.
    b) If choosing 'Foodora Option', order/customer details are output to a file on disk in CSV format.
5) See the full menu

The program also has the functionality to update and delete an order once it has already been placed. In order to
edit/update or delete an order, once it has already been placed, simply enter the order reference (order id) to find
the order. If the order exists, it will allow you to either cancel the order if in delete mode or update the order if
in update mode. Lastly the application has the functionality to display the entire parlor's menu - including all pizzas,
toppings, sizes and drinks.

## Application Design
--------------------------------------------------
We designed the application as follows:

- PizzaParlor: The pizza parlor class is the starting point of our application. It contains the main method. Thus we
dedicated the class entirely to handle the main menu including responsibilites such as displaying menus and accepting
user input.

- Pizza: The pizza class is an exact abstraction of its real world counterpart - it represents an actual pizza. It's
data (instance variables) include its size, type and list of toppings and has the functionality to return any of its
data.

- Order: Similar to the pizza object, the order class is also an exact abstraction of a real world order. It's data
includes an order number, a customer object, a pizza object, a drinks list, a cost and choice of delivery. Its
functionality includes returning a string representation of the order, accessor methods to access its various data
elements and mutator methods to set/update its various data elements.

- Customer: Lastly, the customer class is an abstraction of a real world customer. It contains the customer's name,
limited between 2-30 characters, the customer's address (limited between 10-30 characters) and the customer's phone
number (limited to 10 numeric characters).

- InputValidator: We dedicated this class entirely to handle the validation of user's input in various scenarios and
contexts. For example, it checks if the menu options the users has entered are in the correct format and within the
expected range. It checks if the customer details entered - name, address and phone number are the right length and in
the right format.

- PriceCalculator: We dedicated this class to handle the cost calculations. It calculates the cost of the following
items: the individual pizza, price of the drinks and price of the entire order.

- DataAccessObject: In the design of our application. The base pizzas, their toppings, their sizes, drinks and their
respective prices are stored in files on disk which our application reads on startup. The DataAccessObject is used to
handle all I/O operations to read this data. The paths to the required data files is hard coded here.

- CustomerManager, OrderManager - The manager classes were dedicated to be responsible for storing their relative
object instances. For example customer and order manager classes both hold a list of customers and orders respectively.
Furthermore, they are responsible for performing operations such as adding new instances to the list and removing
instances from the list.

- DeliveryManager - The delivery manager has a functionality similar to the Customer and Order manager. However, in
addition to that the delivery manager also prepares the JSON for Uber Eats and CSV for Foodora. Furthermore it also is
responsible for writing that JSON or CSV to file.

## Tools
--------------------------------------------------
We used IntelliJ as our IDE to develop this application. The linter helped us catch errors quicky and autosuggestion
for methods sped up our process considerably. We used the debugger to sort out any bugs, and made extensive use of the
Refactor > Rename function to change our naming (something which we had to do a lot because of the difference in our
naming styles).

## Design Patterns
--------------------------------------------------
Data Access Object: We used a DataAccessObject class to abstract away the fetching and storing of all the data in our
application, which consisted mainly of menu items and prices. Only a few functions of this class are responsible for
reading information from this files in data structures in memory. The rest of the functions work on the those data
structures to provide only the minimal amount of information. This class was really the cornerstone of our application,
and its clean and intelligent implementation simplified many things for us.

Builder: There are many parts to an order, such as the pizza order, the drinks, the delivery option. Hence, we used the
Builder design pattern to set the attributes of an Order instance outside of the constructor. This allowed for much
greater flexibility as to when to collect order information.

## Pair Programming
--------------------------------------------------
For this project, we broke up the work into two phases. The first phase involved building the core architecture of our
application. This included designing and developing the classes, their methods, design, functionality, coupling, cohesion,
etc. The second phase involved building the additional features on top of the base architecture including: update
functionality, delete functionality, display functionality, delivery output to JSON for Uber Eats and CSV for Foodora
functionality and lastly the testing. Since the initial base architecture phase was critical for both developers to
understand in order to build features on top of later, we decided it was best if this phase was pair programmed. This
would allow both programmers to work together on the core architecture and split up later to develop the necessary
features/utilities individually.

In our pair programming exercise, we first planned out the designs using pen and paper and verbal discussion whenever
needed and then proceed to start coding. Our coding phases lasted 20-30 minutes of intense coding before swapping and
every work session had 4-6 phases of coding. We discovered that having smaller 20-30 minute coding phases allowed us to
be more productive. Furthermore we also found taking a break after every 4 phases was optimal to maintain productivity
reduce burnout. The breakup of tasks/responsibilities were as follows:

Feature: Base Architecture/Placing an Order
 - Driver: Mateen & Atharva (Rotating every 20-30 mins),
 - Navigator: Mateen & Atharva (Rotating every 20-30 mins)

After completing the base architecture using pair programming, we then decided that we could then split up and develop
the required features individually. Since we had both pair programmed the core architecture, it allowed the transition
to developing the separate features relatively easily due to strong fundamental API knowledge. We broke down the
individual tasks as follows:

1) Feature: Updating Order(s) - Mateen
2) Feature: Deleting Orders(s) - Mateen
3) Feature: Displaying Order(s) - Mateen

1) Feature: Input Validation - Atharva
2) Feature: Serialization - Atharva
3) Feature: Unit Tests - Atharva

Overall we found pair programming to be a pleasant learning opportunity/experience. Furthermore, we especially
found pair programming to have the following pros and cons for our team:

### Pros
-------------------
1) While simply observing, the navigator can find many problems that the driver overlooks while coding. This allowed us
to gain an additional perspective on our code.
2) Efficient and continuous workflow. We found that the switching roles every half hour gave us a nice break and allowed
us to come back as the Driver, refreshed and with a fresh perspective. Moreover, we worked for long hours as a result of
this switching without any burnout, which is always a problem when working individually.
We have strong synergy, and hence our particular experience was a positive one. 
3) We learned and improved eachother's coding styles and habits. Specifically, we picked up eachother's tricks while
using IntelliJ, such as using the Refactor > Rename functionality.
4) As partners, we were responsible for keeping the codebase clean for eachother. Hence, we focused on good habits from
the very start, allowing for a robust and easy to maintain codebase.

### Cons
-------------------
1) It could easily have been difficult for us should we have been of different skill/experience levels. Thankfully, both
of us were of similar skill/experience level and thus it worked out well. In cases where the two programmers are of
different skill levels pair programming can easily turn into a bottleneck.
2) It was challenging to implement because both developers did not stay/live in the same locality. Pair programming
for us was best done face to face and in person. Thus it was necessary both programmers meet. Pair programming over
phone or video conferencing or screen sharing was not as efficient as when both developers were face to face.
3) It takes up double the amount of manpower and thus time. Since the navigator is simply observing/helping the driver
while he/she is coding, and only one developer is actually programming/writing code, it doubles the amount of time taken
as compared to if both developers were developing simultaneously.
