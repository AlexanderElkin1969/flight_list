package com.gridnine.testing.filter;

import com.gridnine.testing.FlightViewing;
import com.gridnine.testing.View;

import java.util.List;
import java.util.stream.Collectors;

public class Filter_3 extends Filter {

    public Filter_3(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        List<FlightViewing> newList = view.getFlightViewingList().stream()
                .filter(flight -> flight.getWaitingTimeForTransfer().toMinutes() <= 120)
                .collect(Collectors.toList());
        System.out.println(newList);
        view.setFlightViewingList(newList);
    }

}
