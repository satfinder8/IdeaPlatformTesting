import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
class Ticket {

    String origin; //"VVO"
    String origin_name; //"Владивосток"
    String destination; //"TLV"
    String destination_name; //"Тель-Авив"
    @JsonSetter("departure_date")
    LocalDate departureDate; //12.05.18
    @JsonSetter("departure_time")
    LocalTime departureTime; //12:10
    @JsonSetter("arrival_date")
    LocalDate arrivalDate; //12.05.18
    @JsonSetter("arrival_time")
    LocalTime arrivalTime; //18:10
    String carrier; //SU
    Integer stops; //0
    Double price; //15300
    Long durationTime;
    String durationTimeToString;

    public Ticket() {
    }

    public void setDepartureDate(String departure_date) {
        this.departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public void setArrivalDate(String arrival_date) {
        this.arrivalDate = LocalDate.parse(arrival_date, DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    public void setDepartureTime(String departure_time) {
        this.departureTime = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("H:mm"));
    }

    public void setArrivalTime(String arrival_time) {
        this.arrivalTime = LocalTime.parse(arrival_time, DateTimeFormatter.ofPattern("H:mm"));
        evaluateDuration();
    }

    private void evaluateDuration() {
        LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
        LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
        Duration difference = Duration.between(departureDateTime, arrivalDateTime);
        durationTime = difference.get(ChronoUnit.SECONDS);
        long durationInHours = durationTime / 3600;
        long durationInMinutes = durationTime % 3600 / 60;
        durationTimeToString = durationInHours + "h:" + durationInMinutes + "m";
    }

}
