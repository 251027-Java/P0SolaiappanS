package org.example.Repository;

import org.example.Flight;

public interface IRepository {
    public void bookTicket(Flight flight);

    public void changeTicket(Flight flight);

    public void deleteTicket(int id);
}
