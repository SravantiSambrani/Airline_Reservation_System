import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private int availableSeats;
    private double ticketPrice;
    private boolean[] seats;
    private String[] passengerNames;

    public Flight(String flightNumber, String source, String destination, int totalSeats, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.seats = new boolean[totalSeats];
        this.passengerNames = new String[totalSeats];
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void viewAvailableSeats() {
        System.out.println("Available Seats for Flight " + flightNumber + ":");
        System.out.println("Total Seats Available: " + availableSeats);
        System.out.println("Ticket Price: $" + ticketPrice); // Display ticket price
        System.out.print("Seat Numbers: ");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println(); // Add a newline after printing seat numbers
    }

    public void bookSeat(int seatNumber, String passengerName) {
        if (seatNumber <= seats.length && seatNumber >= 1) {
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                passengerNames[seatNumber - 1] = passengerName;
                availableSeats--;
                System.out.println("Seat " + seatNumber + " booked successfully for " + passengerName);
            } else {
                System.out.println("Seat " + seatNumber + " is already booked.");
            }
        } else {
            System.out.println("Invalid seat number.");
        }
    }

    public void cancelSeat(int seatNumber) {
        if (seatNumber <= seats.length && seatNumber >= 1) {
            if (seats[seatNumber - 1]) {
                seats[seatNumber - 1] = false;
                availableSeats++;
                System.out.println("Reservation for seat " + seatNumber + " canceled successfully.");
            } else {
                System.out.println("Seat " + seatNumber + " is not booked.");
            }
        } else {
            System.out.println("Invalid seat number.");
        }
    }

    public void checkFlightStatus() {
        System.out.println("Flight " + flightNumber + " Status:");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Available Seats: " + availableSeats);
    }
}

public class AirlineReservationSystem {
    public static void main(String[] args) {
        // Sample flights
        Flight[] flights = {
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

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. View Flights");
            System.out.println("2. Book a Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Flight Status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewFlights(flights);
                    break;
                case 2:
                    bookSeat(flights, scanner);
                    break;
                case 3:
                    cancelReservation(flights, scanner);
                    break;
                case 4:
                    checkFlightStatus(flights, scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void viewFlights(Flight[] flights) {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber() +
                    ", Source: " + flight.getSource() +
                    ", Destination: " + flight.getDestination() +
                    ", Available Seats: " + flight.getAvailableSeats() +
                    ", Ticket Price: $" + flight.getTicketPrice());
        }
    }

    private static void bookSeat(Flight[] flights, Scanner scanner) {
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.next();
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.viewAvailableSeats();
                System.out.print("Enter the seat number to book: ");
                int seatNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter passenger name: ");
                String passengerName = scanner.nextLine();
                System.out.println("Proceed to payment...");
                System.out.println("Payment Successful!");
                flight.bookSeat(seatNumber, passengerName);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Flight not found.");
        }
    }

    private static void cancelReservation(Flight[] flights, Scanner scanner) {
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.next();
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.viewAvailableSeats();
                System.out.print("Enter the seat number to cancel reservation: ");
                int seatNumber = scanner.nextInt();
                flight.cancelSeat(seatNumber);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Flight not found.");
        }
    }

    private static void checkFlightStatus(Flight[] flights, Scanner scanner) {
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.next();
        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.checkFlightStatus();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Flight not found.");
        }
    }
}