package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class View {

    public List<FlightViewing> flightViewingList;

    public View() {
        this.flightViewingList = new ArrayList<>();
        List<Flight> flights = FlightBuilder.createFlights();
        for (Flight flight : flights) {
            flightViewingList.add(new FlightViewing(flight.getSegments()));
        }
    }

    public View(List<FlightViewing> flightViewingList) {
        this.flightViewingList = flightViewingList;
    }

    public List<FlightViewing> getFlightViewingList() {
        return flightViewingList;
    }

    public void setFlightViewingList(List<FlightViewing> flightViewingList) {
        this.flightViewingList = flightViewingList;
    }

}
