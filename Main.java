import java.util.Date;

class RoomBooking {
    public int BookRoom(Date start, Date end, int guest) {
        System.out.println("Room booked from " + start + " to " + end + " for " + guest + " guests.");
        return 101;
    }

    public boolean Payment(int room) {
        System.out.println("Payment processed for room " + room);
        return true;
    }
}

class CleaningService {
    public void NotifyCleaning(Date start, Date end, int room) {
        System.out.println("Cleaning service notified for room " + room + " from " + start + " to " + end + ".");
    }

    public void CheckOut(Date start, Date end, int room) {
        System.out.println("Checked out from room " + room + " for the duration from " + start + " to " + end + ".");
    }
}

class RestaurantSystem {
    public void NotifyRest(Date start, Date end, int guest) {
        System.out.println("Restaurant notified for " + guest + " guests from " + start + " to " + end + ".");
    }
}

class HotelFacade {
    private RoomBooking roomBooking;
    private CleaningService cleaningService;
    private RestaurantSystem restaurantSystem;

    public HotelFacade() {
        roomBooking = new RoomBooking();
        cleaningService = new CleaningService();
        restaurantSystem = new RestaurantSystem();
    }

    public void BookRoom(Date start, Date end, int guest) {
        int roomID = roomBooking.BookRoom(start, end, guest);
        cleaningService.NotifyCleaning(start, end, roomID);
        restaurantSystem.NotifyRest(start, end, guest);
    }

    public void CheckOut(Date start, Date end, int room) {
        cleaningService.CheckOut(start, end, room);
        roomBooking.Payment(room);
    }
}

public class Main {
    public static void main(String[] args) {
        HotelFacade hotelFacade = new HotelFacade();

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000);
        int guests = 2;

        hotelFacade.BookRoom(startDate, endDate, guests);

        int roomID = 101;
        hotelFacade.CheckOut(startDate, endDate, roomID);
    }
}