package com.gridnine.testing;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        new Filter_0(view).applyFilter();
        System.out.println("Condition 1 :");
        new Filter_1(view).applyFilter();
        System.out.println("condition 2 :");
        new Filter_2(view).applyFilter();
        System.out.println("condition 3 :");
        new Filter_3(view).applyFilter();
    }
}