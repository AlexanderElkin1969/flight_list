package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class Filter_3 extends Filter {

    public Filter_3(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        List<FlightViewing> newList = view.getFlightViewingList().stream()
                .filter(flight -> flight.getWaitingTimeForTransfer().toHoursPart() <= 2)
                .collect(Collectors.toList());
        System.out.println(newList);
        view.setFlightViewingList(newList);
    }

}
