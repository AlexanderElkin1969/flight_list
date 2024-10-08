package com.gridnine.testing.filter;

import com.gridnine.testing.FlightViewing;
import com.gridnine.testing.View;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Filter_1 extends Filter {

    public Filter_1(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        List<FlightViewing> newList = view.getFlightViewingList().stream()
                .filter(flight -> flight.getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        System.out.println(newList);
        view.setFlightViewingList(newList);
    }

}
