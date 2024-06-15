import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class main {
    public static void main(String[] args) {

        JsonParserToTickets parser = new JsonParserToTickets(new File(
//                "C:\\Users\\User\\IdeaProjects\\" +
//                        "IdeaPlatformTesting\\src\\main\\resources\\"
                "/mnt/c/users/user/IdeaProjects/IdeaPlatformTesting/target/classes/Tickets.json"
        ));

        try {
            TicketList ticketList = parser.jsonToTickets();
            for (Map.Entry<String, Optional<Ticket>> element : Service.minDurationTime(ticketList).entrySet()) {
                System.out.println("Carier - " + element.getKey() +
                        ", Minimal duration - " + (element.getValue().get().getDurationTimeToString()));
            }
            System.out.println("Difference between average and median price: "
                    + (Service.averagePrice(ticketList) - Service.medianPrice(ticketList)));

        } catch (IOException ioe) {
//            ioe.printStackTrace();
            System.out.println("Ошибка создания объекта из JSON");
        }
    }
}
