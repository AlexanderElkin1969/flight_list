package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> conditionOneList = FlightBrowser.conditionOne(FlightBuilder.createFlights());
        System.out.println("Condition 1 :");
        System.out.println(conditionOneList);
        List<Flight> conditionTwoList = FlightBrowser.conditionTwo(conditionOneList);
        System.out.println("condition 2 :");
        System.out.println(conditionTwoList);
        System.out.println("condition 3 :");
        System.out.println(FlightBrowser.conditionThree(conditionTwoList));

        FlightBrowser flightBrowser = new FlightBrowser(conditionTwoList);
        flightBrowser.viewing();

    }
}