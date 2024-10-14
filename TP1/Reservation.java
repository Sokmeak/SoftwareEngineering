import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class Reservation {
    private String roomNumber; // unique
    private String roomNumberToUpdate;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String otherNote; // new field for additional notes

    // default constructor
    Reservation() {
    }

    // 3 constructors for update, add, delete
    // add / create constructor
    Reservation(String roomNum, String name, String startDate, String endDate, String otherNote) {
        setRoomNumber(roomNum);
        setName(name);
        setStartDate(startDate);
        setEndDate(startDate, endDate);
        setOtherNote(otherNote); // set the otherNote
    }

    // delete constructor
    Reservation(String roomNum) {
        setRoomNumber(roomNum);
    }

    // update constructor
    Reservation(String roomNumberToUp, String roomNum, String name, String startDate, String endDate,
            String otherNote) {
        setRoomNumber(roomNum);
        setRoomNumberToUpdate(roomNumberToUp);
        setName(name);
        setStartDate(startDate);
        setEndDate(startDate, endDate);
        setOtherNote(otherNote); // set the otherNote
    }

    private Boolean ValidateRoomNumber(String roomNum) {
        String roomPattern = "^[A-Za-z]-\\d{3}$";
        if (roomNum.matches(roomPattern)) {
            // System.out.println("Room number is valid!");
            return true;
        } else {
            // System.out.println("Error, Room number is invalid!");
            return false;
        }
    }

    private Boolean ValidationName(String name) {
        String vowelPattern = ".*[AEIOUaeiou].*";
        String consonantPattern = ".*[BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz].*";
        return !name.isEmpty() && name.matches(vowelPattern) && name.matches(consonantPattern);
    }

    private Boolean ValidationTime(String startTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
            return startTime.isAfter(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private Boolean ValidationTimeEnd(String startTimeString, String endTimeString) {
        boolean isValidtime = ValidationTime(endTimeString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (isValidtime) {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
            Duration duration = Duration.between(startTime, endTime);
            return duration.toHours() >= 1;
        }
        return false;
    }

    public String getRoomNumberToUpdate() {
        return roomNumberToUpdate;
    }

    public void setRoomNumberToUpdate(String newRoomNumber) {
        if (ValidateRoomNumber(newRoomNumber)) {
            this.roomNumberToUpdate = newRoomNumber;
        } else {
            this.roomNumberToUpdate = "";
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        if (ValidationTime(startDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.startDate = LocalDateTime.parse(startDate, formatter);
        } else {
            this.startDate = null;
        }
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(String startDate, String endDate) {
        if (ValidationTimeEnd(startDate, endDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.endDate = LocalDateTime.parse(endDate, formatter);
        } else {
            this.endDate = null;
        }
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (ValidateRoomNumber(roomNumber)) {
            this.roomNumber = roomNumber;
        } else {
            this.roomNumber = "";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (ValidationName(name)) {
            this.name = name;
        } else {
            this.name = "ADMIN";
        }
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote; // set otherNote
    }

    public boolean hasStarted() {
        Boolean isStarted = LocalDateTime.now().isAfter(startDate);
        return isStarted;
    }

    @Override
    public String toString() {
        return "Reservation [roomNumber=" + roomNumber + ", name=" + name + ", startDate=" + startDate + ", endDate="
                + endDate
                + ", otherNote=" + otherNote + "]"; // include otherNote in toString
    }

    public boolean cancelReservation(Reservation res, ArrayList<Reservation> reservationsList) {
        // String roomNumbetoCancel;

        Reservation reservation = null;

        for (Reservation rs : reservationsList) {
            if (rs.getRoomNumber().equals(res.getRoomNumber())) {
                reservation = rs;
                if (!reservation.hasStarted()) {
                    reservationsList.remove(reservation);
                    return true;
                } else {
                    System.out.println("Failed to Cancel cuz the reservatio has started!");
                }
                break;
            }

        }

        return false;
    }

    public boolean updateReservation(ArrayList<Reservation> arrRes, Reservation resToUpdate) {
        // use the #room to search that reservation

        // String roomNumberToUpdate =reservation.getRoomNumber();

        Reservation res = null;

        boolean isFound = false;

        for (Reservation rs : arrRes) {
            if (rs.getRoomNumber().equals(resToUpdate.getRoomNumberToUpdate())) {
                res = rs;
                isFound = true;

            }
        }
        if (isFound) {
            //
            System.out.println("The old reservation: " + res.toString());

        } else {
            System.out.println("Reservation not found!");
        }

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
        // HH:mm");
        if (!res.hasStarted()) {
            res.setRoomNumber(resToUpdate.getRoomNumber());
            res.setName(resToUpdate.getName());
            res.setStartDate(resToUpdate.getStartDate());
            res.setOtherNote(resToUpdate.getOtherNote()); // update otherNote

            System.out.println("The new reservation: " + res.toString());

            return true;
        }
        return false;
    }

    public boolean swapRooms(Reservation res1, Reservation res2) {
        if (res1.getStartDate().equals(res2.getStartDate()) && res1.getEndDate().equals(res2.getEndDate())) {
            String tempRoom = res1.getRoomNumber();
            res1.setRoomNumber(res2.getRoomNumber());
            res2.setRoomNumber(tempRoom);
            return true;
        }
        return false;
    }
}
