package com.gridnine.testing;

public abstract class Filter {

    public View view;

    public Filter(View view) {
        this.view = view;
    }

    public abstract void applyFilter();

}
