package ksr2.ksrproject2;

import ksr2.ksrproject2.logic.calculation.membershipFunctions.TrapezoidalFunction;

class Main {
    public static void main(String[] args) {
        TrapezoidalFunction function = new TrapezoidalFunction(6, 6, 16, 23);
        TrapezoidalFunctionPlot plot = new TrapezoidalFunctionPlot(function);
        plot.pack();
        plot.setVisible(true);
    }
}