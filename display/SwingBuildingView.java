package display;

import building.BuildingReport;
import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is used to create the view for the building system.
 * It will display the status of the building and the elevators.
 * It will also allow the user to interact with the system.
 * The user can start, stop, step, and request an elevator.
 * The user can also exit the application.
 * The user can also input the start and end floor for the request.
 * The user can also see the status of the building and the elevators.
 * The user can also see the up and down requests.
 */
public class SwingBuildingView extends JFrame {
  public final JButton startButton = new JButton("Start");
  public final JButton stopButton = new JButton("Stop");
  public final JButton stepButton = new JButton("Step");
  public final JButton requestButton = new JButton("Request");
  public final JButton exitButton = new JButton("Exit");
  public final JTextField startFloorInput = new JTextField(5);
  public final JTextField endFloorInput = new JTextField(5);
  private SwingBuildingController controller;
  private final int numberOfFloors;
  private JLabel buildingStatusLabel;
  private JLabel numFloorsLabel;
  private JLabel numElevatorsLabel;
  private JLabel elevatorCapacityLabel;
  private JLabel elevatorStatusLabel = new JLabel();
  private JLabel upRequestLabel = new JLabel();
  private JLabel downRequestLabel = new JLabel();

  /**
   * This constructor is used to create a new SwingBuildingView object.
   * @param numberOfFloors the number of floors in the building
   * @param numberOfElevators the number of elevators in the building
   * @param capacity the capacity of the elevators
   */

  public SwingBuildingView(int numberOfFloors, int numberOfElevators, int capacity) {
    requestButton.setEnabled(false);
    this.numberOfFloors = numberOfFloors;
    initializeComponents();
  }


  private void initializeComponents() {

    setTitle("Building Elevator System");
    setSize(800, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    JPanel containerPanel = new JPanel();
    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    add(containerPanel, BorderLayout.CENTER);

    // Creat a panel for the building status
    JPanel statusPanel = new JPanel();
    statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
    buildingStatusLabel = new JLabel();
    statusPanel.add(buildingStatusLabel);

    numFloorsLabel = new JLabel();
    numElevatorsLabel = new JLabel();
    elevatorCapacityLabel = new JLabel();
    statusPanel.add(numFloorsLabel);
    statusPanel.add(numElevatorsLabel);
    statusPanel.add(elevatorCapacityLabel);
    containerPanel.add(statusPanel);

    // Create a panel for the elevator status
    JPanel elevatorStatusPanel = new JPanel();
    elevatorStatusPanel.setLayout(new GridLayout(1, 0));
    elevatorStatusLabel = new JLabel();
    elevatorStatusPanel.add(elevatorStatusLabel);
    containerPanel.add(elevatorStatusPanel);

    // Build a panel for requests
    JPanel requestPanel = new JPanel();
    requestPanel.setLayout(new GridLayout(2, 2));
    JLabel upLabel = new JLabel("Up Request: ");
    requestPanel.add(upLabel);
    upRequestLabel = new JLabel();
    requestPanel.add(upRequestLabel);

    JLabel downLabel = new JLabel("Down Request: ");
    requestPanel.add(downLabel);
    downRequestLabel = new JLabel();
    requestPanel.add(downRequestLabel);
    containerPanel.add(requestPanel);

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    // Add the buttons to the panel
    buttonPanel.add(new JLabel("Start Floor: "));
    buttonPanel.add(startFloorInput);
    buttonPanel.add(new JLabel("End Floor: "));
    buttonPanel.add(endFloorInput);
    buttonPanel.add(requestButton);
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(stepButton);
    buttonPanel.add(exitButton);
    containerPanel.add(buttonPanel);

    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Exit the application
        System.exit(0);
      }
    });

    // Set the default button
    getRootPane().setDefaultButton(requestButton);

    setLocationRelativeTo(null); // Center on screen
    setVisible(true);
  }

  /**
   * This method is used to update the status of the elevator system.
   * @param report the report of the elevator system
   */

  public void updateElevatorStatus(BuildingReport report) {
    String status = report.toString();

    elevatorStatusLabel.setText(this.getElevatorStatus(report));

    buildingStatusLabel.setText("Building Status: " + report.getSystemStatus().toString());
    numFloorsLabel.setText(" Floors: " + report.getNumFloors());
    numElevatorsLabel.setText(" Number of Elevators: " + report.getNumElevators());
    elevatorCapacityLabel.setText(" Elevator Capacity: " + report.getElevatorCapacity());
    upRequestLabel.setText(report.getUpRequests().toString());
    downRequestLabel.setText(report.getDownRequests().toString());
    if (report.getSystemStatus() == ElevatorSystemStatus.running) {
      requestButton.setEnabled(true);
    } else {
      requestButton.setEnabled(false);
    }
    if (report.getSystemStatus() == ElevatorSystemStatus.stopping
        || report.getSystemStatus() == ElevatorSystemStatus.running) {
      startButton.setEnabled(false);
    } else {
      startButton.setEnabled(true);
    }
  }

  /**
   * This method is used to get the string of status of all the elevators.
   *
   * @param report the report of the elevator system
   * @return the string of the status of all the elevators
   */
  private String getElevatorStatus(BuildingReport report) {
    StringBuilder elevatorStatuses = new StringBuilder("<html>Elevator Status:<br>");
    // Start with HTML tags and a line break
    for (ElevatorReport elevatorReport : report.getElevatorReports()) {
      String reportString = elevatorReport.toString().replace("\n", "<br>");
      // Replace newline characters with HTML line breaks
      elevatorStatuses.append(reportString).append("<br>"); // Append each report and a line break
    }
    elevatorStatuses.append("</html>"); // Close the HTML tags
    return elevatorStatuses.toString();
  }
}
