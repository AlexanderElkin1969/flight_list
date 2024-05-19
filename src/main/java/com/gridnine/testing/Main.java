package com.gridnine.testing;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        System.out.println("All flights :");
        new Filter_0(view).applyFilter();
        System.out.println("Condition 1 :");
        new Filter_1(new View()).applyFilter();
        System.out.println("Condition 2 :");
        new Filter_2(new View()).applyFilter();
        System.out.println("Condition 3 :");
        new Filter_3(new View()).applyFilter();
    }
}