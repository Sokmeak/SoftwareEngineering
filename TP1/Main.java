import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reservation reserve = new Reservation();

        // Default reservations:
        ArrayList<Reservation> myReservations = new ArrayList<>();
        // the first three reservations
        Reservation reservation1 = new Reservation("F-106", "John", "2024-12-01 14:00", "2024-12-01 18:00",
                "The first come");
        Reservation reservation2 = new Reservation("F-107", "Smith", "2024-12-01 14:00", "2024-12-01 18:00",
                "Back at midnight");
        Reservation reservation3 = new Reservation("F-108", "Jane", "2024-10-11 14:00", "2024-11-13 19:00",
                "No extra time");

        myReservations.add(reservation1);
        myReservations.add(reservation2);
        myReservations.add(reservation3);

        Scanner scanner = new Scanner(System.in);
        int opt = 0;

        do {
            System.out.println("========= Menu =========");
            System.out.println("1. Create Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Update Reservation");
            System.out.println("4. Swap Reservation");
            System.out.println("5. List all Reservations");
            System.out.println("6. End program");

            System.out.print("Choose your option: ");
            opt = scanner.nextInt();

            // Consume the newline left after nextInt()
            scanner.nextLine();

            switch (opt) {
                case 1: // Create Reservation
                    System.out.println("1. Create Reservation");
                    System.out.print("Enter Room number: ");
                    String roomNum = scanner.nextLine();

                    System.out.print("Enter Customer's name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Start Date (yyyy-MM-dd HH:mm): ");
                    String startDate = scanner.nextLine();

                    System.out.print("Enter End Date (yyyy-MM-dd HH:mm): ");
                    String endDate = scanner.nextLine();

                    System.out.print("Enter other remark (optional): ");
                    String otherRemark = scanner.nextLine();

                    Reservation newReservation = new Reservation(roomNum, name, startDate, endDate, otherRemark);
                    myReservations.add(newReservation);
                    System.out.println("Reservation created successfully: " + newReservation.toString());

                    break;

                case 2: // Cancel Reservation
                    System.out.println("2. Cancel Reservation");
                    String cancelRoomNum;
                    System.out.print("Enter Room number to cancel: ");
                    cancelRoomNum = scanner.nextLine();
                    Reservation resToCancel = new Reservation(cancelRoomNum);

                    Reservation resCheck1 = findReservationByRoomNumber(myReservations, cancelRoomNum);
                    if (resCheck1 != null) {
                        boolean isCanceled = reserve.cancelReservation(resToCancel, myReservations);
                        if (isCanceled) {
                            System.out.println("Reservation canceled successfully.");
                        } else {
                            System.out.println("Oops, failed to cancel the reservation.");
                        }
                    } else {
                        System.out.println("No reservation found with the given room number.");
                    }
                    break;

                case 3: // Update Reservation
                    System.out.println("3. Update Reservation");
                    System.out.print("Enter Room number to update: ");
                    String updateRoomNum = scanner.nextLine();

                    Reservation resCheck2 = findReservationByRoomNumber(myReservations, updateRoomNum);
                    if (resCheck2 != null) {
                        System.out.print("Enter new Room number: ");
                        String newRoomNum = scanner.nextLine();

                        System.out.print("Enter new Customer's name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new Start Date (yyyy-MM-dd HH:mm): ");
                        String newStartDate = scanner.nextLine();

                        System.out.print("Enter new End Date (yyyy-MM-dd HH:mm): ");
                        String newEndDate = scanner.nextLine();

                        System.out.print("Enter new remark (optional): ");
                        String newRemark = scanner.nextLine();

                        Reservation resToUpdate = new Reservation(updateRoomNum, newRoomNum,
                                newName,
                                newStartDate, newEndDate, newRemark);

                        boolean isUpdated = reserve.updateReservation(myReservations, resToUpdate);
                        if (isUpdated) {
                            System.out.println("Reservation updated successfully: " + resToUpdate.toString());
                        } else {
                            System.out.println("Oops, failed to update the reservation.");
                        }
                    } else {
                        System.out.println("No reservation found with the given room number.");
                    }
                    break;

                case 4: // Swap Reservation
                    System.out.println("4. Swap Reservation");
                    System.out.print("Enter Room number of the first reservation to swap: ");
                    String roomNum1 = scanner.nextLine();
                    Reservation reservation1ToSwap = findReservationByRoomNumber(myReservations, roomNum1);

                    System.out.print("Enter Room number of the second reservation to swap: ");
                    String roomNum2 = scanner.nextLine();
                    Reservation reservation2ToSwap = findReservationByRoomNumber(myReservations, roomNum2);

                    if (reservation1ToSwap != null && reservation2ToSwap != null) {
                        boolean isSwapped = reserve.swapRooms(reservation1ToSwap, reservation2ToSwap);
                        if (isSwapped) {
                            System.out.println("Reservations swapped successfully.");
                        } else {
                            System.out.println("Oops, failed to swap reservations.");
                        }
                    } else {
                        System.out.println("Invalid room numbers provided.");
                    }
                    break;

                case 5: // List all Reservations
                    System.out.println("5. List all Reservations");
                    ListAllReservations(myReservations);
                    break;

                case 6: // End program
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (opt != 6);

        scanner.close();
    }

    // Helper method to find reservation by room number
    static Reservation findReservationByRoomNumber(ArrayList<Reservation> reservations, String roomNumber) {
        for (Reservation res : reservations) {
            if (res.getRoomNumber().equals(roomNumber)) {
                return res;
            }
        }
        return null;
    }

    // Display all reservations
    static void ListAllReservations(ArrayList<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(res -> {
                System.out.println(res.toString());
            });
        }
    }
}
