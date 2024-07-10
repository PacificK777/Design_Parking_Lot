package Services;

import Exceptions.GateNotFoundException;
import Factory.SpotAssignmentStrategyFactory;
import Models.*;
import Repositories.GateRepository;
import Repositories.ParkingLotRepository;
import Repositories.TicketRepository;
import Repositories.VehicleRepository;
import Strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateID,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              VehicleType vehicleType) throws GateNotFoundException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        //Get the gate object from the database
        Optional<Gate> optionalGate = gateRepository.findGateById(gateID);

        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Invalid GateID :" + gateID);
        }
        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        //Get the vehicle object from the database with vehicleNumber, if not present, save it to DB
        Optional<Vehicle> optionalVehicle =
                vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);

        Vehicle savedVehicle = null;
        if (optionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);
            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);

        //Assign the spot and use spot Assignment Strategy
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateID(gateID);
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();
        SpotAssignmentStrategy spotAssignmentStrategy =
                SpotAssignmentStrategyFactory.getSpotAssignmentStrategyForType(spotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        ticket.setParkingSpot(parkingSpot);

        ticket.setNumber("TICKET: " + gateID + "_" + ticket.getEntryTime() + "_" + vehicleNumber);

        return ticketRepository.save(ticket);


    /*
    1        M
    PL ---- Gate => 1:M
    1  ----  1
    => Id of one side on M side.
    => ParkingLot id in the Gate table.
    */

    }
}
