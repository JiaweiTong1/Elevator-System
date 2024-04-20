1. About/Overview. Give a general overview of the problem and how your program solves the problem.

The system is composed of several classes, including BuildingReport, SwingBuildingController, and SwingBuildingView.  The BuildingReport class encapsulates the state of the building at a given time, including the number of floors, the number of elevators, the maximum capacity of the elevators, the status of each elevator, and the up and down requests for elevator service.  The SwingBuildingController class is responsible for managing the interactions between the building and the user interface. It handles user actions such as starting and stopping the elevator system, stepping through the simulation, and adding elevator service requests.  

The SwingBuildingView class provides the user interface for the simulation. It displays the current state of the building and the elevators, and allows the user to interact with the system.The updateElevatorStatus method in the SwingBuildingView class is used to update the user interface with the current state of the building and the elevators. It takes a BuildingReport as an argument, which it uses to update the various components of the user interface.  The system solves the problem of simulating a building elevator system by encapsulating the state of the building in the BuildingReport class, managing the simulation with the SwingBuildingController class, and providing a user interface with the SwingBuildingView class.

2. List of features. List all features that are present in your program.
   
Elevator System Control: The program provides the ability to start and stop the elevator system. When the system is started, all elevators begin operation. When the system is stopped, all elevators cease operation.
   
Elevator Request Handling: The program can handle requests for elevator service. These requests specify a start floor and an end floor. The program adds these requests to the appropriate list (up requests or down requests) based on the direction of travel.  

Elevator System Stepping: The program provides a method to step the elevator system. This method distributes requests to the elevators and steps each elevator.

Elevator System Status Reporting: The program can generate a report on the status of the elevator system. This report includes the number of floors, the number of elevators, the elevator capacity, the status of each elevator, and the up and down requests for elevator service.  

User Interface: The program provides a graphical user interface that displays the current state of the building and the elevators. The user interface allows the user to interact with the system, including starting and stopping the elevator system, stepping through the simulation, and adding elevator service requests.  
Error Handling: The program includes error handling to ensure that the number of floors, the number of elevators, and the elevator capacity are within valid ranges. It also checks that the start floor and end floor of a request are valid and different from each other.  

Elevator Status Display: The program provides a method to update the user interface with the current state of the building and the elevators. This includes the status of each elevator, the number of floors, the number of elevators, the elevator capacity, and the up and down requests for elevator service.

Elevator Capacity Management: The program ensures that the number of requests does not exceed the elevator's capacity. If the number of requests in the upRequests and downRequests lists is greater than the elevator capacity, the request will not be added. 

Elevator System Status Management: The program manages the status of the elevator system. If all elevators are on the first floor, the system status will be set to out of service.

4. How To Run. Instructions to run the program should include the following:
   
How to run the jar file

The command `java -jar <ElevatorVersionPlusPlus.jar>` is used to run a Java application packaged as a JAR (Java ARchive) file. Here's what each part of the command means:

- `java`: This is the command to start the Java Runtime Environment (JRE). The JRE is what allows to run Java applications on computer.

- `-jar`: This is an option provided to the `java` command to specify that the file being run is a JAR file.

- `<ElevatorVersionPlusPlus.jar>`: This is a placeholder for the actual path to the JAR file, use `java -jar ElevatorVersionPlusPlus.jar` to run my jar.

So, in summary, `java -jar <path_to_jar_file>` is a command that tells computer to start the Java Runtime Environment and use it to run the JAR file located at `<ElevatorVersionPlusPlus.jar>`.

What arguments are needed (if any) to run the jar file, what do they mean
How to Use the Program. Instructions on how to use functionality in your program. if interactive, how to interact with your program? Pay particular attention to the parts that are not part of the example runs that you provided.

Start the Elevator System: Click the "Start" button to start the elevator system. When the system is started, all elevators begin operation.  
Stop the Elevator System: Click the "Stop" button to stop the elevator system. When the system is stopped, all elevators cease operation.  
Step Through the Simulation: Click the "Step" button to step the elevator system. This method distributes requests to the elevators and steps each elevator.  
Request an Elevator: To request an elevator, enter the start floor and end floor in the respective text fields and then click the "Request" button. The program will handle the request and add it to the appropriate list (up requests or down requests) based on the direction of travel.  
Exit the Application: Click the "Exit" button to exit the application.

6. Design/Model Changes. It is important to document what changes that you have made from earlier designs. Why were those changes required? You can write these changes in terms of version if you wish.
   
   V1 I designed the MVC model can sent the request but did not design to disable the request button and start button if the elevator status is not in the right status.
   V2 I added the SwingBuildingView class does not allow the user to make requests when the building is stopping or stopped. 

7. Assumptions. List what assumptions you made during program development and implementation. Be sure that these do not conflict with the requirements of the project.

   Valid Floor Numbers: It is assumed that the floor numbers in the building start from 0 and go up to numberOfFloors - 1. Any request for a floor outside this range is considered invalid. In the Jar, the valid range of number of floor is 0 to 9.
   Elevator Capacity: It is assumed that the capacity of the elevator is fixed and cannot be exceeded. If a request is made that would cause the elevator to exceed its capacity, the request is not added.
   Elevator System Status: It is assumed that the elevator system can be in one of three states: running, stopping, or out of service. The system starts in the out of service state and can be started by the user. If all elevators are on the first floor, the system status is set to out of service.
   Request Handling: It is assumed that requests for elevator service specify a start floor and an end floor. The start floor and end floor must be different, and the start floor must be less than the end floor for up requests and greater than the end floor for down requests.
   Elevator Movement: It is assumed that the elevators move one floor at a time. When an elevator is stepped, it moves one floor in the direction of its current request.
   Elevator Request Distribution: It is assumed that requests are distributed to the elevators in the order they are received. If an elevator is not currently taking requests, it is skipped.
   User Interface: It is assumed that the user interacts with the system through a graphical user interface. The user can start and stop the elevator system, step through the simulation, and add elevator service requests. User can exit the whole program by click on the EXIT button. The start button is enabled or disabled based on the current status of the elevator system. If the system status is running or stopping, the start button is disabled. If the system status is out of service, the start button is enabled.

8. Limitations. Limitations of your program if any. This should include any requirements that were not implemented or were not working correctly (including something that might work some of the time).
   
   The number of floor, number of elevator, and elevator capacity is fixed. There is no place to let user input those information.
   Valid Floor Numbers: It is assumed that the floor numbers in the building start from 0 and go up to numberOfFloors - 1. Any request for a floor outside this range is considered invalid. In the Jar, the valid range of number of floor is 0 to 9.
   Elevator Capacity: It is assumed that the capacity of the elevator is fixed and cannot be exceeded. If a request is made that would cause the elevator to exceed its capacity, the request is not added.

9. Citations. Be sure to include any citations that are required for your project. Citations should include references (paper, website, etc.) for any site that you used to research a solution. Proper APA format should be used. For websites this includes the name of the website, title of the article, the url, and the date of retrieval. If you have nothing to cite, you should indicate this.
    
Microsoft Copilot. (2024). Help with ansering the question for building a elevator system. Retrieved from Microsoft Copilot interface on [2024 April].

    
