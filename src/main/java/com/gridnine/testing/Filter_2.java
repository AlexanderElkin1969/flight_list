package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class Filter_2 extends Filter {

    public Filter_2(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        List<FlightViewing> newList = view.getFlightViewingList().stream()
                .filter(flight -> flight.getSegments()
                        .stream().allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
        System.out.println(newList);
        view.setFlightViewingList(newList);
    }

}
