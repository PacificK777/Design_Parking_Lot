package Controllers;

import DTOS.IssueTicketRequestDTO;
import DTOS.IssueTicketResponseDTO;
import DTOS.ResponseStatus;
import Exceptions.GateNotFoundException;
import Models.Ticket;
import Services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO){

        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();

        try{
            Ticket ticket = ticketService.issueTicket(
                    requestDTO.getGateID(),
                    requestDTO.getVehicleNumber(),
                    requestDTO.getVehicleOwnerName(),
                    requestDTO.getVehicleType()
            );
            responseDTO.setTicket(ticket);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
            catch (GateNotFoundException gateNotFoundException){
                gateNotFoundException.getMessage();
                responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
