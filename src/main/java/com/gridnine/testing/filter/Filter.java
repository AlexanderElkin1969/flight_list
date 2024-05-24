package com.gridnine.testing.filter;

import com.gridnine.testing.View;

public abstract class Filter {

    public View view;

    public Filter(View view) {
        this.view = view;
    }

    public abstract void applyFilter();

}
