package com.gridnine.testing.filter;

import com.gridnine.testing.View;

public class Filter_0 extends Filter {

    public Filter_0(View view) {
        super(view);
    }

    @Override
    public void applyFilter() {
        System.out.println(new View().getFlightViewingList());
    }
}
