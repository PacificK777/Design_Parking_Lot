package Repositories;

import Models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {

    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Long previousTicketID = 0L;

    public Ticket save(Ticket ticket){
        if(ticket.getId()==null){
            previousTicketID = previousTicketID+1;
            ticket.setId(previousTicketID);
            ticketMap.put(previousTicketID,ticket);
        }
        // else update the existing object

        return ticket;
    }
}
