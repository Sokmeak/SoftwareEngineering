
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        Reservation reservation = new Reservation();

        ArrayList<Reservation> myReservations = new ArrayList<>();
        // the first three of reservations
        Reservation reservation1 = new Reservation("F-106", "John", "2024-12-01 14:00", "2024-12-01 18:00",
                "The first come");
        Reservation reservation2 = new Reservation("F-107", "Smith", "2024-10-11 14:00", "2024-11-13 19:00",
                "Back at the midnight");
        Reservation reservation3 = new Reservation("F-108", "Jane", "2024-10-11 14:00", "2024-11-13 19:00",
                "No extra time");

        myReservations.add(reservation1);
        myReservations.add(reservation2);
        myReservations.add(reservation3);

        // create an object of reservation for getting data for update

        Reservation resToUpdate = new Reservation("F-107",
                "F-111", "new-user",
                "2024-11-13 19:00", "2024-12-13 19:00", "nothing to say");

        Boolean isUpated = reservation.updateReservation(myReservations, resToUpdate);

        if (isUpated) {
            System.out.println("Update successfully");
        } else {
            System.out.println("Unable to update");
        }
        // Reservation rsToCancel = new Reservation("F-107");

        // System.out.println("Before cancel");
        // myReservations.forEach((rs -> {
        // System.out.println(rs.toString());
        // }));
        // Boolean isCanceled = reservation.cancelReservation(rsToCancel,
        // myReservations);

        // if (isCanceled) {
        // System.out.println("Cancel successfully");
        // System.out.println("After cancel");
        // myReservations.forEach((rs -> {
        // System.out.println(rs.toString());
        // }));

        // } else {
        // System.out.println("Failed");
        // }

    }
}
