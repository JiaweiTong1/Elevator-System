package display;

import building.Building;
import building.BuildingReport;
import scanerzus.Request;

/**
 * SwingBuildingController is the controller for the SwingBuildingView. It is responsible for
 * handling user input and updating the view.
 */
public class SwingBuildingController {
  private Building building;
  private SwingBuildingView view;

  /**
   * This constructor is used to create a new SwingBuildingController object.
   * @param building The building.
   * @param view The view of the building system
   */
  public SwingBuildingController(Building building, SwingBuildingView view) {
    this.building = building;
    this.view = view;
    // Initialize view with action listeners
    initViewActions();
  }

  /**
   * This method is used to initialize the view actions.
   */

  public void initViewActions() {

    view.startButton.addActionListener(e -> clickStartButton());
    view.stepButton.addActionListener(e -> stepElevatorSystem());
    view.requestButton.addActionListener(e -> addRequest());
    view.exitButton.addActionListener(e -> exitApplication());
    view.stopButton.addActionListener(e -> stopElevatorSystem());
  }

  /**
   * This method is used to handle the start button click.
   */
  public void clickStartButton() {

    //view.requestButton.setEnabled(true);
    this.startElevatorSystem();
  }

  /**
   * This method is used to start the elevator system.
   */
  public void startElevatorSystem() {
    building.startElevatorSystem();
    BuildingReport report = building.getStatusElevatorSystem();
    view.updateElevatorStatus(report);
  }

  /**
   * This method is used to stop the elevator system.
   */
  public void stopElevatorSystem() {
    building.stopElevatorSystem();
    BuildingReport report = building.getStatusElevatorSystem();
    view.updateElevatorStatus(report);
    view.requestButton.setEnabled(false);
  }

  /**
   * This method is used to step the elevator system.
   */
  public void stepElevatorSystem() {
    building.stepElevatorSystem();
    BuildingReport report = building.getStatusElevatorSystem();
    view.updateElevatorStatus(report);
  }

  /**
   *  This method is used to add a request to the building.
   */

  public void addRequest() {
    System.out.println(building.getElevatorsStatus().toString());
    int startFloor = Integer.parseInt(view.startFloorInput.getText());
    int endFloor = Integer.parseInt(view.endFloorInput.getText());
    Request request = new Request(startFloor, endFloor);
    building.addRequestToElevatorSystem(request);
    BuildingReport report = building.getStatusElevatorSystem();
    view.updateElevatorStatus(report);
  }

  /**
   * This method is used to exit the application.
   */

  private void exitApplication() {
    building.stopElevatorSystem();
    System.exit(0);
  }
}