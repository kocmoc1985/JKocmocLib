/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphExamples;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing how to create a combined chart using...
 *
 */
public class CombinedGraph1 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public CombinedGraph1(final String title) {

        super(title);
        final JFreeChart chart = createCombinedChart();
        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    /**
     * Creates a combined XYPlot chart.
     *
     * @return the combined chart.
     */
    private JFreeChart createCombinedChart() {

        // create a default chart based on some sample data...
        final TimeSeriesCollection dataset0 = new TimeSeriesCollection();
        final TimeSeries eur = new TimeSeries("eur");
        dataset0.addSeries(eur);

        final TimeSeriesCollection dataset1 = new TimeSeriesCollection();
        final TimeSeries mav = MovingAverage.createMovingAverage(
            eur, "EUR/GBP (30 Day MA)", 30, 30
        );
        dataset1.addSeries(eur);
        dataset1.addSeries(mav);

        final TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(eur);

        JFreeChart chart = null;

        // make a common vertical axis for all the sub-plots
        final NumberAxis valueAxis = new NumberAxis("Value");
        valueAxis.setAutoRangeIncludesZero(false);  // override default

        // make a horizontally combined plot
        final CombinedRangeXYPlot parent = new CombinedRangeXYPlot(valueAxis);

        // add subplot 1...
        final XYPlot subplot1 = new XYPlot(dataset0, new DateAxis("Date 1"), null,
                                     new StandardXYItemRenderer());
        parent.add(subplot1, 1);

        // add subplot 2...
        final XYPlot subplot2 = new XYPlot(dataset1, new DateAxis("Date 2"), null,
                                     new StandardXYItemRenderer());
        parent.add(subplot2, 1);

        // add subplot 3...
        final XYPlot subplot3 = new XYPlot(dataset2, new DateAxis("Date 3"),
                                     null, new XYBarRenderer(0.20));
        parent.add(subplot3, 1);

        // now make the top level JFreeChart
        chart = new JFreeChart("Demo Chart", JFreeChart.DEFAULT_TITLE_FONT, parent, true);

        // then customise it a little...
        final TextTitle subtitle = new TextTitle("This is a subtitle",
                                           new Font("SansSerif", Font.BOLD, 12));
        chart.addSubtitle(subtitle);
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));
        return chart;

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final CombinedGraph1 demo = new CombinedGraph1("Combined XY Plot Demo 3");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}