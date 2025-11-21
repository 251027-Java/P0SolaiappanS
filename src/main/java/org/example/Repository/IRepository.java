package org.example.Repository;

import org.example.*;

public interface IRepository {
    public int createFlight(Flight flight);
    public int insertPassenger(Passenger passenger);
    public void insertPassengerFlight(int pid, int fid);
}
