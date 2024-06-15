import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class Service {

    static Map<String, Optional<Ticket>> minDurationTime(TicketList tickets) {

        Map<String, Optional<Ticket>> minDurationByGroup = tickets.getTickets().stream()
                .filter(ticket -> ticket.origin.equals("VVO"))
                .filter(ticket -> ticket.destination.equals("TLV"))
                .collect(Collectors.groupingBy(Ticket::getCarrier
                        , Collectors.minBy(Comparator.comparingLong(Ticket::getDurationTime))));
        return minDurationByGroup;
    }

    static Double averagePrice(TicketList tickets) {
        OptionalDouble averagePrice;
        averagePrice =
                tickets.getTickets().stream()
                        .filter(ticket -> ticket.origin.equals("VVO"))
                        .filter(ticket -> ticket.destination.equals("TLV"))
                        .mapToDouble(Ticket::getPrice)
//                    .forEach(System.out::println)
                        .average()
        ;
        return averagePrice.getAsDouble();
    }

    static Double medianPrice(TicketList tickets) {
        OptionalDouble medianPrice;
        if (tickets.getTickets().size() % 2 != 0) {
            medianPrice = tickets.getTickets().stream()
                    .filter(ticket -> ticket.origin.equals("VVO"))
                    .filter(ticket -> ticket.destination.equals("TLV"))
                    .mapToDouble(Ticket::getPrice)
                    .sorted()
                    .skip(tickets.getTickets().size() / 2)
//                    .forEach(System.out::println)
                    .min()
            ;
        } else {
            medianPrice = tickets.getTickets().stream()
                    .filter(ticket -> ticket.origin.equals("VVO"))
                    .filter(ticket -> ticket.destination.equals("TLV"))
                    .mapToDouble(Ticket::getPrice)
                    .sorted()
                    .skip((tickets.getTickets().size() / 2) - 1)
                    .limit(2)
//                    .forEach(System.out::println)
                    .average()
            ;
        }
        return medianPrice.getAsDouble();
    }


}
