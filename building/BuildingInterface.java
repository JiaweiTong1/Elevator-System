package building;

import scanerzus.Request;

/**
 * This interface is used to represent a building.
 * It is used to interact with the building.
 * It is used to get the status of the building.
 * It is used to add requests to the building.
 * It is used to start and stop the building.
 * It is used to step the building.
 * It is used to get the status of the building.
 */
public interface BuildingInterface {

  /**
   * This method is used to get the number of floors in the building.
   * @return the number of floors in the building
   */
  int getNumberOfFloors();

  /**
   * This method is used to get the number of elevators in the building.
   * @return the number of elevators in the building
   */

  int getNumberOfElevators();

  /**
   * This method is used to get the max occupancy of the elevator.
   * @return the max occupancy of the elevator.
   */

  int getElevatorCapacity();

  /**
   * This method is used to get the status of the elevators.
   * @return the status of the elevators.
   */

  boolean startElevatorSystem();

  /**
   * This method is used to get the up requests for the elevators.
   */
  void stopElevatorSystem();

  /**
   * This method is used to get the status of the building.
   * @return the status of the building.
   */
  BuildingReport getStatusElevatorSystem();

  /**
   *  This method is used to add a request to the building.
   * @param request The request to add to the building.
   */
  void addRequestToElevatorSystem(Request request);

  /**
   * This method is used to step the building.
   */

  void stepElevatorSystem();

}
