
class Ride {
    int rideId;
    String pickup;
    String drop;
    double fare;

    public Ride(int rideId, String pickup, String drop, double fare) {
        this.rideId = rideId;
        this.pickup = pickup;
        this.drop = drop;
        this.fare = fare;
    }

    public void displayRide() {
        System.out.println("Ride ID: " + rideId +
                ", Pickup: " + pickup +
                ", Drop: " + drop +
                ", Fare: ₹" + fare);
    }
}

class Node {
    Ride ride;
    Node next;

    public Node(Ride ride) {
        this.ride = ride;
        this.next = null;
    }
}

class RideHistory {
    private Node head;
    public void addRide(Ride r) {
        Node newNode = new Node(r);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }



    public void deleteLastRide() {
        if (head == null) {
            System.out.println("No rides available.");
            return;
        }

        if (head.next == null) {
            head = null;
            System.out.println("Last ride deleted.");
            return;
        }

        Node temp = head;

        while (temp.next.next != null) {
            temp = temp.next;
        }

        temp.next = null;

        System.out.println("Last ride deleted.");
    }


    public void displayRides() {
        if (head == null) {
            System.out.println("No ride history.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            temp.ride.displayRide();
            temp = temp.next;
        }
    }


    public void searchRide(String location) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {

            if (temp.ride.pickup.equalsIgnoreCase(location) ||
                    temp.ride.drop.equalsIgnoreCase(location)) {

                temp.ride.displayRide();
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("No rides found for location: " + location);
        }
    }


    public void reverseHistory() {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }

        head = prev;

        System.out.println("Ride history reversed.");
    }
}


public class RideBookingSystem {

    public static void main(String[] args) {

        RideHistory history = new RideHistory();

        history.addRide(new Ride(101, "Bangalore", "Mysore", 1200));
        history.addRide(new Ride(102, "Chennai", "Pondicherry", 900));
        history.addRide(new Ride(103, "Hyderabad", "Airport", 650));

        System.out.println("All Rides:");
        history.displayRides();

        System.out.println("\nSearch by location: Chennai");
        history.searchRide("Chennai");

        System.out.println("\nDeleting last ride...");
        history.deleteLastRide();

        System.out.println("\nUpdated Ride History:");
        history.displayRides();

        System.out.println("\nReversing History...");
        history.reverseHistory();

        System.out.println("\nLatest First View:");
        history.displayRides();
    }
}