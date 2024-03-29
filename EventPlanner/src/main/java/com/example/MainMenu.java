package com.example;

import com.example.data.EventData;
import com.example.data.NotifcationData;
import com.example.data.RoomData;
import com.example.entites.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenu {
    public static final String LOCATION_CRITERIA = "Location";
    public static final String TYPE_CRITERIA = "Type";
    public static final String PRICE_CRITERIA = "Price";
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());
    private static final String WELCOME_STRING = """
                  
             __          __    _                                _______        _____  _               _          _____   _                                  
             \\ \\        / /   | |                              |__   __|      / ____|| |             | |        |  __ \\ | |                                 
              \\ \\  /\\  / /___ | |  ___  ___   _ __ ___    ___     | |  ___   | (___  | |_  _   _   __| | _   _  | |__) || |  __ _  _ __   _ __    ___  _ __ 
               \\ \\/  \\/ // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\    | | / _ \\   \\___ \\ | __|| | | | / _` || | | | |  ___/ | | / _` || '_ \\ | '_ \\  / _ \\| '__|
                \\  /\\  /|  __/| || (__| (_) || | | | | ||  __/    | || (_) |  ____) || |_ | |_| || (_| || |_| | | |     | || (_| || | | || | | ||  __/| |   
                 \\/  \\/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|    |_| \\___/  |_____/  \\__| \\__,_| \\__,_| \\__, | |_|     |_| \\__,_||_| |_||_| |_| \\___||_|   
                                                                                                       __/ |                                             
                                                                                                      |___/                                              
            """;
    App app = new App();
    private String username;
    private String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        boolean isRunning = true;
        while (isRunning) {
            logger.info(WELCOME_STRING);
            logger.info("\n" + """
                    +--------------------+
                    | 1. log in          |
                    | 2. Sign up         |
                    | X. Exit            |
                    +--------------------+
                    """);
            logger.info("Enter your choice please : ");
            String choose = input.next();
            switch (choose) {
                case "1":
                    logIn();
                    break;
                case "2":
                    signUp();
                    break;
                case "X":
                    isRunning = false; // Terminate the loop
                    break;
                default:
                    logger.info("Invalid choice. Please try again.");
                    break;
            }
        }


    }

    public void logIn() {
        char role = 'a';
        while (true) {
            if (Back()) return;
            logger.info("Please enter the username and the password :");
            logger.info("Username :");
            username = input.next();
            logger.info("Password :");
            password = input.next();
            user = app.getLoginService().LoginPerformed(username, password);
            if (user == null) {
                logger.info(app.getLoginService().getErrorMessage());
            } else {
                app.setLoggedInUser(user);
                role = user.getRole();
                break;
            }
        }

        app.setLoggedInUser(user);
        app.getCalenderService().setLoggedInUser(user);
        switch (role) {
            case 'a':
                logger.info("""
                        ╔════════════════════════╗
                              ║ You logged in as Admin ║
                              ╚════════════════════════╝
                        """);
                Page("2. Add Room", user.getRole());
                break;

            case 's':
                logger.info("""
                        ╔═══════════════════════════════════╗
                              ║ You logged in as Service Provider ║
                              ╚═══════════════════════════════════╝
                        """);
                ServiceProvider.getSPFromData(username).setFirstLogin(true);
                Page("2. Complete", user.getRole());
                break;

            case 'c':
                logger.info("""
                        ╔════════════════════════╗
                              ║ You logged in as Client║
                              ╚════════════════════════╝
                        """);
                Page("2. Upgrade", user.getRole());
                break;

            default:
                break;
        }

    }

    public void signUp() {
        while (true) {
            if (Back()) return;
            logger.info("Please enter your username :");
            String username = input.next();
            logger.info("Please enter your password :");
            String password = input.next();
            logger.info("Please enter your email :");
            String email = input.next();
            logger.info("Please enter your role :");
            String role = input.next();
            boolean flag = app.getSignUpService().register(username, password, email, role);
            if (flag) {
                break;
            } else {
                logger.info(app.getSignUpService().getMsg());
            }
        }
    }


    public void Page(String specification, char role) {
        while (true) {
            String info = generateMenu(specification, role);
            logger.info(info);
            logger.info("Enter your choice please : ");

            String choose = input.next();
            switch (choose) {
                case "1":
                    accountInformation(role);
                    break;
                case "2":
                    handleChoiceTwo(role);
                    break;
                case "3":
                    searchServiceProvider();
                    break;
                case "4":
                    showUpcomingEvents();
                    break;
                case "5":
                    if (handleChoiceFive(role)) {
                        return;
                    }
                    if (role == 's') {
                        return;
                    }
                    break;
                case "6":
                    if (role == 'a') {
                        deleteRoom();
                    } else if (role == 'c') {
                        bookEvent();
                    }
                    break;
                case "7":
                    if (role == 'a') {
                        showNotifications();
                    } else if (role == 'c') {
                        cancelBookEvent();
                    }
                    break;
                case "8":
                    if (role == 'a') {
                        return;
                    } else if (role == 'c') {
                        showEventDetails();
                    }
                    break;
                case "9":
                    if (role == 'c') {
                        yourEvents();
                    }
                    break;
                case "10":
                    if (role == 'c') {
                        return;
                    }
                case "X":
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private String generateMenu(String specification, char role) {
        StringBuilder menu = new StringBuilder();
        menu.append("\n")
                .append("1. Show account information\n")
                .append(specification).append("\n")
                .append("3. Search Service Provider\n")
                .append("4. Show upcoming events\n");
        if (role == 'c') {
            menu.append("5. Enter organizer mode\n");
        } else if (role == 'a') {
            menu.append("5. Show all rooms\n")
                    .append("6. Delete room\n")
                    .append("7. Notifications\n")
                    .append("8. Log out\n");
        }
        if (role == 's') {
            menu.append("5. Log out\n");
        } else if (role == 'c') {
            menu.append("6. Book event\n")
                    .append("7. Cancel Booking\n")
                    .append("8. Show Event Details\n")
                    .append("9. Your Events\n")
                    .append("10. Log out\n");
        }
        menu.append("X. Exit");
        return menu.toString();
    }

    private void handleChoiceTwo(char role) {
        if (role == 'a') {
            app.getAddRoomService().setLoggedInUser(user);
            addRoom();
        } else if (role == 's') {
            complete();
        } else {
            upgrade();
        }
    }

    private boolean handleChoiceFive(char role) {
        if (role == 'a') {
            showAllRooms();
        } else if (role == 'c') {
            if (!organizerPage()) {
                return true;
            }
        }
        return false;
    }

    public void deleteRoom() {
        showAllRooms();
        logger.info("Please enter the id of the room to be deleted");
        String id = input.next();
        app.getDeleteRoomService().DeleteRoomPerform(id);
        logger.info(app.getDeleteRoomService().getMsg());
    }

    public void bookEvent() {
        String eventID = bookList("book", app.getBookEventService().chooseBookEvent());
        app.getBookEventService().BookEventPerform(eventID, user.getUsername());
        logger.info(app.getBookEventService().getMsg());
    }

    public String bookList(String cancel, ArrayList<Event> arrayList) {
        StringBuilder listEvent = new StringBuilder();
        listEvent.append("\n");
        if (arrayList.isEmpty()) {
            logger.info("There is no Events to " + cancel);
            return null;
        }
        for (Event e : arrayList) {
            listEvent.append("ID : " + e.getId() + "\tEvent Name :" + e.getEventName() + "\tEvent Date : " + e.getStartDate() + "\n");
        }
        logger.info(listEvent.toString());
        logger.info("Please enter the ID of the event you want to " + cancel);
        String eventID = input.next();
        return eventID;
    }

    public void showNotifications() {
        for (Notification n : NotifcationData.getNotifcations()) {
            logger.info(n.getId() + n.getMsg());
        }
    }

    public void cancelBookEvent() {
        String eventID = bookList("cancel", app.getBookEventService().chooseCancelBookEvent(user.getUsername()));
        app.getBookEventService().cancelBookEvent(eventID, user.getUsername());
        logger.info(app.getBookEventService().getMsg());
    }

    public void accountInformation(char role) {
        while (true) {
            String info = """
                    Username : """ + user.getUsername() + """
                    Password : """ + user.getPassword() + """
                    Email : """ + user.getContactEmail() + """
                    """;

            if (role == 'a')
                info += "      Role : Admin";
            else if (role == 's') {
                info += "Role : Service Provider";
            } else {
                info += "Role : Clint";
            }
            logger.info(info);
            if (Back())
                return;
        }
    }

    public void addRoom() {
        while (true) {
            if (Back()) return;
            logger.info("Please enter the name of the new room : ");
            String roomName = input.next();
            logger.info("Please enter the Capacity of the new room : ");
            String roomCapacity = input.next();
            logger.info("Please enter the cost per hour of the new room :");
            String roomCost = input.next();
            logger.info("Please enter the description of the new room :");
            String roomDes = input.next();
            logger.info("Please enter Yes or No to determine the Availability of the room");
            String roomAvailability = input.next();
            if (roomAvailability.equalsIgnoreCase("yes")) {
                roomAvailability = "Available";
            } else if (roomAvailability.equalsIgnoreCase("no")) {
                roomAvailability = "not";
            } else {
                logger.info("Invalid choice");
                break;
            }
            boolean flag = app.getAddRoomService().AddRoomPerformed(roomName, roomAvailability, roomCapacity, roomCost, roomDes);
            if (flag) {
                logger.info(app.getAddRoomService().getMsg());
                break;
            } else {
                logger.info(app.getAddRoomService().getMsg());
            }
        }


    }

    public void searchServiceProvider() {

        while (true) {
            if (Back()) return;
            String info = """
                    Search based on :
                          1. Location
                          2. Type
                          3. Price
                    Please enter your choice :
                    """;

            logger.info(info);
            String choose = input.next();
            switch (choose) {
                case "1":
                    app.getSearchSP().setSelectedCriteria(LOCATION_CRITERIA);
                    searchCriteria(LOCATION_CRITERIA);
                    break;
                case "2":
                    app.getSearchSP().setSelectedCriteria(TYPE_CRITERIA);
                    searchCriteria(TYPE_CRITERIA);
                    break;
                case "3":
                    app.getSearchSP().setSelectedCriteria(PRICE_CRITERIA);
                    searchCriteria(PRICE_CRITERIA);
                    break;
                case "":
                    app.getSearchSP().setSelectedCriteria("");
                    logger.info(app.getSearchSP().getErrorMsg());
                    break;
                default:
                    app.getSearchSP().setSelectedCriteria("a");
                    logger.info(app.getSearchSP().getErrorMsg());
                    break;
            }
        }
    }

    public void showUpcomingEvents() {
        for (Event e : app.getCalenderService().showUpcomingEvents()) {
            logger.info(e.getId() + " " + e.getEventName() + " " + e.getEventDescription() + " " + e.getStartDate() + " " + e.getEndDate() + " " + e.getStartClock() + " " + e.getEndClock() + " " + e.getAttendeeCount());
        }
    }

    public void showAllRooms() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (Room r : RoomData.getRooms()) {
            stringBuilder.append("ID : " + r.getId() + "\tName : " + r.getName() + "\tCapacity : " + r.getCapacity() + "\tCost Per Hour : " + r.getCostPerHour() + "\tDescription : " + r.getDescription());
            if (r.isAvailable()) {
                stringBuilder.append("\tAvailability : Available");
            }
            stringBuilder.append("\n");
        }
        logger.info(stringBuilder.toString());

    }

    public void complete() {
        while (true) {
            if (Back()) return;
            if (!ServiceProvider.getSPFromData(app.getLoggedInUser().getUsername()).isFirstLogin()) {
                logger.info("Your Account is completed");
                return;
            }
//            for(ServiceProvider s : UserData.getSps()){
//                if(s.getUsername().equals(user.getUsername())){
//                    if(s.isFirstLogin()){
//                        logger.info(app.getSPAccount().getCompleteAccountMsg());
//                    }
//                }
//            }
            String location, productPrice, productType;
            logger.info("To complete your account please enter your:");
            logger.info("Location : ");
            location = input.next();
            logger.info("Product Price : ");
            productPrice = input.next();
            logger.info("Product Type : ");
            productType = input.next();
            productType += " " + input.next();

            boolean flag = app.getSPAccount().completeAccountPerform(location, productPrice, productType);

            if (flag) {
                logger.info(app.getSPAccount().getCompleteAccountMsg());
                ServiceProvider.getSPFromData(username).setFirstLogin(false);
                return;
            } else {
                logger.info(app.getSPAccount().getCompleteAccountMsg());
            }
        }
    }

    public void upgrade() {
        while (true) {
            if (Back()) return;
            app.getUpgradeClient().setLoggedInUser(user);
            app.getUpgradeClient().UpgradeClientPerform();
            logger.info(app.getUpgradeClient().getMsg());
        }
    }

    public void printCriteria(List<ServiceProvider> serviceProviders) {
        for (int i = 0; i < serviceProviders.size(); i++) {
            if (!serviceProviders.get(i).isFirstLogin())
                logger.info(String.format("%d. %s", i, serviceProviders.get(i).getUsername()));
        }
    }


    public void searchCriteria(String criteria) {
        List<ServiceProvider> tmpArray = new ArrayList<>();
        switch (criteria) {
            case "Location":
                logger.info("Please enter the name of the location to search :");
                String location = input.next();
                app.getSearchSP().setLocation(location);
                tmpArray.addAll(app.getSearchSP().SearchSPPerformed());
                printCriteria(tmpArray);
                return;
            case "Type":
                logger.info("Please enter the type of the Service provider : ");
                String type = input.next() + " " + input.next();
                app.getSearchSP().setType(type);
                printCriteria(app.getSearchSP().SearchSPPerformed());
                return;
            default:
                logger.info("Please enter the price of the product : ");
                String price = input.next();
                app.getSearchSP().setPrice(price);
                printCriteria(app.getSearchSP().SearchSPPerformed());
                return;
        }
    }


    public boolean Back() {
        logger.info("Please Enter B if you want to go back otherwise enter anything : ");
        String back = input.next();
        if (back.equals("B")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean organizerPage() {
        if (Back()) return true;
        if (!app.getAddEventService().isOrgnaizer(user.getUsername())) {
            logger.info("You must be an Organizer");
            return true;
        }
        while (true) {
            logger.info("""
                    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.
                          |  1. Add Event                                  |
                          |  2. Update Event                               |
                          !  3. Delete Event                               !
                          :  4. Search for Service Provider                :
                          .  5. Reserve Room for an Event                  .
                          .  6. Reserve Service Provider for an Event      .
                          :  7. Show upcoming Events                       :
                          !  8. Show information account                   !
                          |  9. Log out                                    |
                          |  X. Exit                                       |
                          `-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-`
                    """);

            logger.info("Pleas enter your choice :");
            String choice = input.next();

            switch (choice) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    updateEvent();
                    break;
                case "3":
                    deleteEvent();
                    break;
                case "4":
                    searchServiceProvider();
                    break;
                case "5":
                    reserveRoom();
                    break;
                case "6":
                    reserveServiceProvider();
                    break;
                case "7":
                    showUpcomingEvents();
                    break;
                case "8":
                    accountInformation(user.getRole());
                    break;
                case "9":
                    return false;
                case "X":
                    System.exit(0);
                default:
                    throw new IllegalArgumentException("Invalid choice: " + choice);
            }


        }

    }

    public void addEvent() {
        while (true) {
            logger.info("Please enter the information for the new event : ");
            logger.info("Event name : ");
            String eventName = input.next();
            logger.info("Description :");
            String eventDes = input.next();
            logger.info("Start date : ");
            String startDate = input.next();
            logger.info("End date : ");
            String endDate = input.next();
            logger.info("Starting hour :");
            String startHour = input.next();
            logger.info("End hour :");
            String endHour = input.next();
            logger.info("Attendee count :");
            String attendeeCount = input.next();
            logger.info("Image path :");
            String imagePath = input.next();

            DateEvent dateEvent = new DateEvent(startDate,endDate,startHour,endHour);
            boolean flag = app.getAddEventService().addEvent(user.getUsername(), EventData.getEvents().size() + 1, eventName, eventDes, dateEvent, attendeeCount, imagePath);
            if (flag) {
                logger.info(app.getAddEventService().getMsg());
                break;
            } else {
                logger.info(app.getAddEventService().getMsg());
            }
        }


    }

    public void updateEvent() {
        while (true) {
            showUpcomingEvents();
            logger.info("Please enter the new information for your event : ");
            logger.info("Event name : ");
            String eventName = input.next();
            logger.info("Event id : ");
            String eventId = input.next();
            logger.info("Description :");
            String eventDes = input.next();
            logger.info("Start date : ");
            String startDate = input.next();
            logger.info("End date : ");
            String endDate = input.next();
            logger.info("Starting hour :");
            String startHour = input.next();
            logger.info("End hour :");
            String endHour = input.next();
            logger.info("Attendee count :");
            String attendeeCount = input.next();
            logger.info("Image path :");
            String imagePath = input.next();
            DateEvent dateEvent = new DateEvent(startDate,endDate,startHour,endHour);
            boolean flag = app.getDeleteUpdateEventService().UpdateEventPerform(eventId, user.getUsername(), eventName, eventDes, dateEvent, attendeeCount, imagePath);
            if (flag) {
                logger.info(app.getDeleteUpdateEventService().getMsg());
                //Created getMsg in DeleteUpdateEvent
                break;
            } else {
                logger.info(app.getDeleteUpdateEventService().getMsg());
            }
        }


    }

    public void deleteEvent() {
        while (true) {
            showUpcomingEvents();
            logger.info("Please enter the ID of the event you want to delete : ");
            String eventId = input.next();
            boolean flag = app.getDeleteUpdateEventService().DeleteEventPerform(eventId);
            if (flag) {
                logger.info(app.getDeleteUpdateEventService().getMsg());
                //Created getMsg in DeleteUpdateEvent
                break;

            } else {
                logger.info(app.getDeleteUpdateEventService().getMsg());
            }

        }
    }

    public void reserveRoom() {
        logger.info("Please enter the ID of the event to reserve a room for it :");
        showUpcomingEvents();
        String event = input.next();
        logger.info("Please enter the ID of the room :");
        showAllRooms();
        String room = input.next();
        app.getReserveRoomService().ReserveRoomPerform(event, room);
        logger.info(app.getReserveRoomService().getMsg());
    }

    public void reserveServiceProvider() {
        logger.info("Please enter the ID of the event to reserve a Service Provider for it :");
        showUpcomingEvents();
        String event = input.next();
        logger.info("Please enter the ID of the Service Provider :");
        app.getSearchSP().setSelectedCriteria("price");
        app.getSearchSP().setPrice("1000");
        printCriteria(app.getSearchSP().SearchSPPerformed());
        String SP = input.next();
        app.getReserveSPService().ReserveSPPerform(event, SP);
        logger.info(app.getReserveSPService().getMsg());
    }

    public void showEventDetails() {
        logger.info("Please Enter the ID of the Event : ");
        String id = input.next();
        Event e = app.getCalenderService().showEventDetails(id);
        logger.info(app.getCalenderService().getMsg());
        if (e == null) return;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.getId() + " " + e.getEventName() + " " + e.getEventDescription() + " " + e.getStartDate() + " " + e.getEndDate() + " " + e.getStartClock() + " " + e.getEndClock() + " " + e.getAttendeeCount());
        logger.info(stringBuilder.toString());
    }

    public void yourEvents() {
        logger.info("Please enter 1 (Past) or 2 (Future) to show your events :");
        String pastOrFuture = input.next();
        app.getCalenderService().clearCalenderEvents();
        if (pastOrFuture.equals("1")) {
            app.getCalenderService().calenderPerform("past");
        } else if (pastOrFuture.equals("2")) {
            app.getCalenderService().calenderPerform("future");
        } else {
            logger.info("Invalid choice");
            return;
        }
        printEvents();
    }

    public void printEvents() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        for (Event e : app.getCalenderService().getResEvents()) {
            stringBuilder.append(e.getId() + " " + e.getEventName() + " " + e.getEventDescription() + " " + e.getStartDate() + " " + e.getEndDate() + " " + e.getStartClock() + " " + e.getEndClock() + " " + e.getAttendeeCount() + "\n");
        }
        logger.info(stringBuilder.toString());
    }
}
