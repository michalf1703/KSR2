package ksr2.ksrproject2;

import ksr2.ksrproject2.logic.calculation.membershipFunctions.TrapezoidalFunction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class TrapezoidalFunctionPlot extends JFrame {

    public TrapezoidalFunctionPlot(TrapezoidalFunction function) {
        super("Trapezoidal Function Plot");

        XYSeries series = new XYSeries("Trapezoidal Function");
        for (double x = function.getLeftBoundary(); x <= function.getRightBoundary(); x += 0.1) {
            series.add(x, function.getValue(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Trapezoidal Function Plot",
                "x",
                "Membership Degree",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }
}