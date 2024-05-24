package com.gridnine.testing.filter;

import com.gridnine.testing.FlightViewing;
import com.gridnine.testing.View;

import java.util.List;
import java.util.stream.Collectors;

public class Filter_2 extends Filter {

    public Filter_2(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        List<FlightViewing> newList = view.getFlightViewingList().stream()
                .filter(FlightViewing::isValidated)
                .collect(Collectors.toList());
        System.out.println(newList);
        view.setFlightViewingList(newList);
    }

}
