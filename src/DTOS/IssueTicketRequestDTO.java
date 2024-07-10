package DTOS;

import Models.Vehicle;
import Models.VehicleType;

public class IssueTicketRequestDTO {
    private Long gateID;
    private String vehicleNumber;
    private String vehicleOwnerName;
    private VehicleType vehicleType;

    public Long getGateID() {
        return gateID;
    }

    public void setGateID(Long gateID) {
        this.gateID = gateID;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
