import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AirlineReservationSystemGUI extends JFrame {
    private Flight[] flights;
    private JTextArea outputTextArea;
    private JTextField inputField;

    public AirlineReservationSystemGUI() {
        super("Airline Reservation System");
        flights = createFlights();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter your choice:");
        inputField = new JTextField(10);
        topPanel.add(label);
        topPanel.add(inputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
        JButton viewFlightsButton = new JButton("View Flights");
        JButton bookSeatButton = new JButton("Book a Seat");
        JButton cancelReservationButton = new JButton("Cancel Reservation");
        JButton checkFlightStatusButton = new JButton("Flight Status");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(viewFlightsButton);
        buttonPanel.add(bookSeatButton);
        buttonPanel.add(cancelReservationButton);
        buttonPanel.add(checkFlightStatusButton);
        buttonPanel.add(exitButton);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        viewFlightsButton.addActionListener(e -> viewFlights());
        bookSeatButton.addActionListener(e -> bookSeat());
        cancelReservationButton.addActionListener(e -> cancelReservation());
        checkFlightStatusButton.addActionListener(e -> checkFlightStatus());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private Flight[] createFlights() {
        return new Flight[]{
                 new Flight("F001", "New York", "Los Angeles", 100, 200.0),
                new Flight("F002", "Los Angeles", "Chicago", 100, 150.0),
                new Flight("F003", "Chicago", "Houston", 100, 180.0),
                new Flight("F004", "Delhi", "Mumbai", 150, 250.0),
                new Flight("F005", "Chennai", "Delhi", 120, 280.0),
                new Flight("F006", "Chennai", "Hyderabad", 100, 180.0),
                new Flight("F007", "Mumbai", "Kolkata", 180, 300.0),
                new Flight("F008", "Kolkata", "Bangalore", 200, 320.0),
                new Flight("F009", "Bangalore", "Ahmedabad", 150, 270.0),
                new Flight("F010", "Ahmedabad", "Pune", 100, 200.0),
                new Flight("F011", "Pune", "Jaipur", 120, 230.0),
                new Flight("F012", "Jaipur", "Lucknow", 150, 260.0),
                new Flight("F013", "Lucknow", "Patna", 100, 210.0),
                new Flight("F014", "Patna", "Guwahati", 120, 240.0),
                new Flight("F015", "Guwahati", "Bhubaneswar", 150, 270.0),
                new Flight("F016", "Bhubaneswar", "Varanasi", 130, 220.0),
                new Flight("F017", "Varanasi", "Agra", 100, 200.0),
                new Flight("F018", "Agra", "Goa", 150, 290.0),
                new Flight("F019", "Goa", "Chandigarh", 120, 260.0),
                new Flight("F020", "Chandigarh", "Ranchi", 130, 240.0),
                new Flight("F021", "Ranchi", "Nagpur", 140, 270.0),
                new Flight("F022", "Nagpur", "Coimbatore", 100, 220.0),
                new Flight("F023", "Coimbatore", "Trivandrum", 150, 250.0),
                new Flight("F024", "Trivandrum", "Amritsar", 120, 280.0),
                new Flight("F025", "Amritsar", "Jodhpur", 130, 260.0),
                new Flight("F026", "Jodhpur", "Visakhapatnam", 140, 270.0),
                new Flight("F027", "Visakhapatnam", "Indore", 100, 240.0),
                new Flight("F028", "Indore", "Kochi", 150, 290.0),
                new Flight("F029", "Kochi", "Bhopal", 120, 230.0),
                new Flight("F030", "Bhopal", "Srinagar", 130, 220.0)
        };
    }

    private void viewFlights() {
        outputTextArea.setText("");
        for (Flight flight : flights) {
            outputTextArea.append("Flight Number: " + flight.getFlightNumber() +
                    ", Source: " + flight.getSource() +
                    ", Destination: " + flight.getDestination() +
                    ", Available Seats: " + flight.getAvailableSeats() +
                    ", Ticket Price: $" + flight.getTicketPrice() + "\n");
        }
    }

    private void bookSeat() {
        String flightNumber = JOptionPane.showInputDialog("Enter the flight number:");
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.viewAvailableSeats();
                int seatNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the seat number to book:"));
                String passengerName = JOptionPane.showInputDialog("Enter passenger name:");
                JOptionPane.showMessageDialog(null, "Proceed to payment...\nPayment Successful!");
                flight.bookSeat(seatNumber, passengerName);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Flight not found.");
        }
    }

    private void cancelReservation() {
        String flightNumber = JOptionPane.showInputDialog("Enter the flight number:");
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.viewAvailableSeats();
                int seatNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the seat number to cancel reservation:"));
                flight.cancelSeat(seatNumber);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Flight not found.");
        }
    }

    private void checkFlightStatus() {
        String flightNumber = JOptionPane.showInputDialog("Enter the flight number:");
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                outputTextArea.setText("Flight " + flightNumber + " Status:\n" +
                        "Source: " + flight.getSource() + "\n" +
                        "Destination: " + flight.getDestination() + "\n" +
                        "Available Seats: " + flight.getAvailableSeats());
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Flight not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AirlineReservationSystemGUI app = new AirlineReservationSystemGUI();
            app.setVisible(true);
        });
    }
}
