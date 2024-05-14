My first spring boot project :) after learning spring-boot for one week or so ish...
but yeah, don't criticize too harshly!!

I still have a lot of learning ^ _ ^ to do.

it's just a Simple Car-Wash Managment Application.

#Functionalities...
 --> Has some basic employee-scheduling.
 
 #Simple Customer Functionalities 
--> No login required to Use application
--> Setting an appointment
--> Tracking appointment phases

#Car-wash Supervisor/Manager Functionalites 
--> can managing the Day-to-Day activites of a car-wash spot.
--> manage Employees (Add employee and Delete employee)
--> manage cars...

since i'ts a small web app, I decided not to add the registration-endpoint for the car-wash manager, rather hardcode the details in the application
and only expose the authentication for the car-wash manager before accessing the protected-endpoints( SupervisorController.java )
given it's a small application, ordinary users will likely find the registration-page in the front-end and could get so much influence in the application...
like "managing" cars...

//@EnableMethodSecurity, @PreAuthorize("")
// probably could have used these bad boys too, to have that granular control on 'functionality-access' too, but... will include stuff as I learn :)
