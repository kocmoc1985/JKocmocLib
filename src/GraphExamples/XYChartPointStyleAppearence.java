/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphExamples;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * {@link XYDataset}.
 *
 */
public class XYChartPointStyleAppearence extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public XYChartPointStyleAppearence(final String title) {

        super(title);

        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));




        chartPanel.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                XYPlot xyplot = chart.getXYPlot();
                double x = 0;
                double y = 0;
                try {
                    System.out.println("" + event.getEntity().toString());

                    if (event.getEntity() instanceof XYItemEntity) {
                        XYItemEntity xe = (XYItemEntity) event.getEntity();
                        XYDataset xySeries = xe.getDataset();
                        x = xySeries.getXValue(xe.getSeriesIndex(), xe.getItem());
                        y = xySeries.getYValue(xe.getSeriesIndex(), xe.getItem());
                        System.out.println("" + xe.getDataset().toString());
                        System.out.println("x = " + x + "  y= " + y);
                    }
                    //clear cursor
                    xyplot.clearDomainMarkers();
                    xyplot.clearRangeMarkers();

                    //<cursor>
                    //Cursor settings (for both)
                    float dash[] = {10.0f};
                    Stroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f);
                    Font font = new Font("Arial", Font.BOLD, 12);
                    
                    //**
                    final ValueMarker horizontal = new ValueMarker(y);
                    horizontal.setPaint(Color.white);
                    horizontal.setStroke(stroke);
                    xyplot.addRangeMarker(horizontal);
                    
                    //**
                    final ValueMarker vertical = new ValueMarker(x);
                    vertical.setPaint(Color.white);
                    vertical.setStroke(stroke);
                    vertical.setLabelFont(font);
                    vertical.setLabelPaint(Color.white);
                    vertical.setLabel("x = " + x + "  y = " + y);
                    vertical.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                    vertical.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                    xyplot.addDomainMarker(vertical);
                    //</cursor>


                } catch (Exception ex) {
                    System.out.println("" + ex);
                }

            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {
                System.out.print("");
            }
        });
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createDataset() {

        final XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);




        return dataset;

    }

    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo 6", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
//        final StandardL legend = (StandardLegend) chart.getLegend();
//        legend.setDisplaySeriesShapes(true);



        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        //<lineappearence>The appereance of the lines/graphs
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();


        //Graph lines points
        renderer.setSeriesLinesVisible(0, true); // line visible/unvisible
        renderer.setSeriesShapesVisible(0, true); //points visible/unvisible

        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, true);

        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, true);

        //Color of the lines
        renderer.setSeriesPaint(0, Color.WHITE); // Sets the color of the line
        renderer.setSeriesPaint(1, Color.YELLOW);
        renderer.setSeriesPaint(2, Color.green);

        renderer.setSeriesStroke(
                0, new BasicStroke(
                3.0f, //thickness of the line
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{1.0f, 1.0f}, //// to make dotted {10.0f, 6.0f}
                0.0f));
        renderer.setSeriesStroke(
                1, new BasicStroke(
                3.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{1.0f, 1.0f},
                0.0f));
        renderer.setSeriesStroke(
                2, new BasicStroke(
                3.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{1.0f, 1.0f},
                0.0f));
        //</lineappearence>



        plot.setRenderer(renderer);
        //</lineappearence>    

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.


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

        final XYChartPointStyleAppearence demo = new XYChartPointStyleAppearence("Line Chart Demo 6");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}
