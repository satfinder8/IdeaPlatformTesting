import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


class JsonParserToTickets {

    File ticketsFile;

    public JsonParserToTickets(File fileJson) {
        ticketsFile = fileJson;
    }
    TicketList jsonToTickets() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(ticketsFile, new TypeReference<TicketList>() {});
    }

}
