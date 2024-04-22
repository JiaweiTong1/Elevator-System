package main;

import building.Building;
import building.BuildingReport;
import building.enums.ElevatorSystemStatus;
import display.SwingBuildingController;
import display.SwingBuildingView;
import java.util.Scanner;
import scanerzus.Request;

/**
 * This is the main class for the console application.
 * It is used to run the console application.
 * It has a main method that reads user input and interacts with the building.
 * It uses the Building class to interact.
 */

public class MainConsole {
  /**
   * This is the main method for the console application.
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    Building model = new Building(10, 3, 3);
    SwingBuildingView view = new SwingBuildingView(10, 3, 3);
    SwingBuildingController controller = new SwingBuildingController(model, view);

  }
}
