//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package building;

import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorInterface;
import elevator.ElevatorReport;
import java.util.ArrayList;
import java.util.List;
import scanerzus.Request;


/**
 * Building class that implements BuildingInterface.
 * The class represents a building with a number of floors, elevators, and elevator capacity.
 * The class contains a list of up requests, down requests, and elevators.
 * The class also contains the status of the elevator system.
 * The class contains methods to start and stop the elevator system, get the number of floors,
 * get the number of elevators, get the elevator capacity, get the status of the elevator system,
 * add a request to the elevator system, step the elevator system, get the elevators in the
 * building,get the status of the elevators, and distribute requests to the elevators.
 * The class also contains a method to check if all elevators are on the first floor.
 * The class also contains a method to get the requests for the elevator.
 * The class also contains a method to get the status of the elevators.
 * The class also contains a constructor that takes the number of floors, number of elevators,
 * and elevator capacity as parameters.
 */

public class Building implements BuildingInterface {
  private final int numberOfFloors;
  private final int numberOfElevators;
  private final int elevatorCapacity;
  private final ElevatorInterface[] elevators;
  private final List<Request> upRequests = new ArrayList();
  private final List<Request> downRequests = new ArrayList();
  private ElevatorSystemStatus elevatorsStatus;

  /**
   * Constructor for Building class.
   * @param numberOfFloors building's number of floors
   * @param numberOfElevators building's number of elevators
   * @param elevatorCapacity elevator capacity
   * @throws IllegalArgumentException if numberOfFloors is less than 2,
   *        numberOfElevators is less than 1,
   *        or elevatorCapacity is less than 1
   */
  public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity)
      throws IllegalArgumentException {
    if (numberOfFloors < 2) {
      throw new IllegalArgumentException("numberOfFloors must be greater than or equal to 2");
    } else if (numberOfElevators < 1) {
      throw new IllegalArgumentException("numberOfElevators must be greater than or equal to 1");
    } else if (elevatorCapacity < 1) {
      throw new IllegalArgumentException("maxOccupancy must be greater than or equal to 1");
    } else {
      this.numberOfFloors = numberOfFloors;
      this.numberOfElevators = numberOfElevators;
      this.elevatorCapacity = elevatorCapacity;
      this.elevators = new Elevator[numberOfElevators];

      for (int i = 0; i < numberOfElevators; ++i) {
        this.elevators[i] = new Elevator(numberOfFloors, this.elevatorCapacity);
      }
      this.elevatorsStatus = ElevatorSystemStatus.outOfService;
    }
  }

  /**
   * Constructor for Building class.
   * @param numberOfFloors building's number of floors
   * @param numberOfElevators building's number of elevators
   * @param elevatorCapacity building's elevator capacity
   * @param elevators elevators in the building
   */

  public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity,
                   ElevatorInterface[] elevators) {
    this.numberOfFloors = numberOfFloors;
    this.numberOfElevators = numberOfElevators;
    this.elevatorCapacity = elevatorCapacity;
    this.elevators = elevators;
  }

  /**
   * Starts the elevator system.
   * @return true if the elevator system is started successfully
   * @throws IllegalStateException if the elevator system is not stopped
   */
  public boolean startElevatorSystem() {
    if (this.elevatorsStatus != ElevatorSystemStatus.running) {
      if (elevatorsStatus == ElevatorSystemStatus.stopping) {
        throw new IllegalStateException("Elevator cannot be started until it is stopped");
      }
    }
    if (this.elevatorsStatus == ElevatorSystemStatus.outOfService) {
      // Start each elevator
      for (ElevatorInterface elevator : elevators) {
        elevator.start();
      }
      elevatorsStatus = ElevatorSystemStatus.running;
      // Clear upRequests and downRequests
      upRequests.clear();
      downRequests.clear();
    }
    return true;
  }

  /**
   * Stops the elevator system.
   */
  public void stopElevatorSystem() {
    if (this.elevatorsStatus != ElevatorSystemStatus.outOfService
        && this.elevatorsStatus != ElevatorSystemStatus.stopping) {
      for (ElevatorInterface elevator : elevators) {
        elevator.takeOutOfService();
        elevator.step();

        upRequests.clear();
        downRequests.clear();
        elevatorsStatus = ElevatorSystemStatus.stopping;
      }
    }
  }

  /**
   *  Get the number of floors in the building.
   * @return the number of floors in the building
   */
  public int getNumberOfFloors() {
    return this.numberOfFloors;
  }

  /**
   * Get the number of elevators in the building.
   * @return the number of elevators in the building
   */

  public int getNumberOfElevators() {
    return this.numberOfElevators;
  }

  /**
   * Get the elevator capacity.
   * @return the elevator capacity
   */

  public int getElevatorCapacity() {
    return this.elevatorCapacity;
  }

  /**
   * Get the status of the elevator system.
   * @return the status of the elevator system
   */

  public BuildingReport getStatusElevatorSystem() {
    ElevatorReport[] elevatorReports = new ElevatorReport[this.elevators.length];

    for (int i = 0; i < this.elevators.length; ++i) {
      elevatorReports[i] = this.elevators[i].getElevatorStatus();
    }

    BuildingReport buildingReport = new BuildingReport(this.numberOfFloors,
        this.numberOfElevators, this.elevatorCapacity, elevatorReports,
        this.upRequests, this.downRequests, this.elevatorsStatus);
    return buildingReport;
  }

  /**
   *  Add a request to the elevator system. The request will be added to the upRequests
   *  list if the start floor is less than the end floor, and the downRequests list if
   *  the start floor is greater than the end floor.
   *  If the elevator system is out of service and stopping the request will not be added.
   *  If the number of requests in the upRequests and downRequests lists is greater than
   *  the elevator capacity the request will not be added.
   * @param request the request to add
   */

  public void addRequestToElevatorSystem(Request request) {

    if (this.elevatorsStatus == ElevatorSystemStatus.outOfService
        || this.elevatorsStatus == ElevatorSystemStatus.stopping) {
      throw new IllegalStateException("Elevator system not accepting requests.");
    }
    if (this.upRequests.size() + this.downRequests.size() > this.elevatorCapacity) {
      throw new IllegalStateException("Over elevator's max capacity.");
    }

    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    } else if (request.getStartFloor() < 0 || request.getStartFloor() >= this.numberOfFloors) {
      throw new IllegalArgumentException("Start floor must be between 0 and "
          + (this.numberOfFloors - 1));
    } else if (request.getEndFloor() < 0 || request.getEndFloor() >= this.numberOfFloors) {
      throw new IllegalArgumentException("End floor must be between 0 and "
            + (this.numberOfFloors - 1));
    } else if (request.getStartFloor() == request.getEndFloor()) {
      throw new IllegalArgumentException("Start floor and end floor cannot be the same");
    } else {
      if (request.getStartFloor() < request.getEndFloor()) {
        this.upRequests.add(request);
        this.elevatorsStatus = ElevatorSystemStatus.running;
      } else {
        this.downRequests.add(request);
        this.elevatorsStatus = ElevatorSystemStatus.running;
      }
    }
  }

  /**
   *  Step the elevator system.
   *  If the elevator system is out of service or stopping
   *  the method will return without doing anything.
   *  The method will distribute requests to the elevators.
   *  The method will step each elevator.
   *  If all elevators are on the first floor the system
   *  status will be set to out of service.
   */

  public void stepElevatorSystem() {
    if (this.elevatorsStatus != ElevatorSystemStatus.outOfService) {
      if (this.elevatorsStatus != ElevatorSystemStatus.stopping) {
        this.distributeRequests();
      }
    }

    for (ElevatorInterface elevator : elevators) {
      elevator.step(); // Step each elevator
    }

    if (elevatorsStatus == ElevatorSystemStatus.stopping && areAllElevatorsOnGroundFloor()) {
      elevatorsStatus = ElevatorSystemStatus.outOfService;
      // Set system status to out of service if all elevators are on the first floor
    }
  }

  /**
   *  Check if all elevators are on the first floor.
   *  If all elevators are on the first floor the method will return true.
   * @return true if all elevators are on the first floor
   */
  private boolean areAllElevatorsOnGroundFloor() {
    for (ElevatorInterface elevator : elevators) {
      if (elevator.getCurrentFloor() != 0) {
        return false; // Return false if any elevator is not on the first floor
      }
    }
    return true; // Return true if all elevators are on the first floor
  }

  private void distributeRequests() {
    if (upRequests.isEmpty() && downRequests.isEmpty()) {
      return; // No requests to distribute
    }

    for (ElevatorInterface elevator : elevators) {
      if (elevator.isTakingRequests()) {
        List requestsForElevator;

        if (elevator.getCurrentFloor() == 0) {
          requestsForElevator = this.getRequests(this.upRequests);
          elevator.processRequests(requestsForElevator);
        } else if (elevator.getCurrentFloor() == numberOfFloors - 1) {
          requestsForElevator = this.getRequests(this.downRequests);
          elevator.processRequests(requestsForElevator);
        } else {
          continue;
        }

        elevator.processRequests(requestsForElevator);
      }
    }
  }

  /**
   * Get the requests for the elevator.
   * @param requests the requests to get
   * @return return the list of requests
   */

  private List<Request> getRequests(List<Request> requests) {

    List<Request> returnRequest = new ArrayList();
    while (!requests.isEmpty() && returnRequest.size() < this.elevatorCapacity) {
      returnRequest.add((Request) requests.remove(0));
    }

    return returnRequest;
  }

  /**
   * Get the elevators in the building.
   * @return the elevators in the building
   */
  public ElevatorInterface[] getElevators() {
    return elevators;
  }

  /**
   * Get the status of the elevators.
   * @return the status of the elevators
   */
  public ElevatorSystemStatus getElevatorsStatus() {
    return elevatorsStatus;
  }
}
