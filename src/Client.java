import Controllers.TicketController;
import DTOS.IssueTicketRequestDTO;
import DTOS.IssueTicketResponseDTO;
import Models.Ticket;
import Models.VehicleType;
import Repositories.GateRepository;
import Repositories.ParkingLotRepository;
import Repositories.TicketRepository;
import Repositories.VehicleRepository;
import Services.TicketService;

public class Client {
    public static void main(String[] args) {

    // Create a ticket
        IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
        requestDTO.setGateID(123L);
        requestDTO.setVehicleNumber("UP32HZ5487");
        requestDTO.setVehicleOwnerName("Scaler");
        requestDTO.setVehicleType(VehicleType.FOUR_WHEELER);

        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();


        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );
        TicketController ticketController = new TicketController(ticketService);
        IssueTicketResponseDTO responseDTO = ticketController.issueTicket(requestDTO);

        Ticket ticket = responseDTO.getTicket();


    }
}
