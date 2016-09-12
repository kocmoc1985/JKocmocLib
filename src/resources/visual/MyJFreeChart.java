/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import GraphReady.RealTimeGraph1;
import java.awt.BasicStroke;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.CategoryTableXYDataset;

/**
 *
 * @author Administrator
 */
public class MyJFreeChart {

    /**
     * My first try to make a graph, so not so good
     * Ticking line graph, adds a value per second - loks like real time graph
     * @param arr
     */
    public static void Line_graph1(double[] arr) {
        RealTimeGraph1 g1 = new RealTimeGraph1();
        for (int i = 0; i < arr.length; i++) {
            try {
                Thread.sleep(1000);
                g1.draw_graph(arr[i]);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Creates a graph with a single serie
     * @param datasetX - points for x-axis
     * @param datasetY - points for y-axis
     * @param serie - name of the serie
     * @param title - title name of the graph
     * @param xtitle - name of x-axis
     * @param ytitle - name of y-axis
     */
    public static void createXYGraph(double[] datasetX, double[] datasetY, String serie, String title, String xtitle, String ytitle) {
        JFrame fr4 = new JFrame();
        CategoryTableXYDataset xydataset = new CategoryTableXYDataset();
        for (int i = 0; i < datasetX.length; i++) {
            xydataset.add(datasetX[i], datasetY[i], serie);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(title, xtitle, ytitle, xydataset, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel mypan = new ChartPanel(chart);
        mypan.setMouseZoomable(true);

        fr4.getContentPane().add(mypan);
        fr4.setSize(400, 800);
        fr4.setVisible(true);
        fr4.pack();
        fr4.getContentPane().setBackground(Color.blue);
    }

    /**
     * Creates a line graph with 2 series
     * @param datasetX - x values for serie 1
     * @param datasetY - y values for serie 1
     * @param datasetX2 - x values for serie 2
     * @param datasetY2 - y values for serie 2
     * @param serie - name of serie 2
     * @param serie2 - name of serie 2
     * @param title - graph title
     * @param xtitle - title of the x-axis
     * @param ytitle - title of the y-axis
     */
    public static void createXYGraph_2_Series(double[] datasetX, double[] datasetY, double[] datasetX2, double[] datasetY2, String serie, String serie2, String title, String xtitle, String ytitle) {
        JFrame fr4 = new JFrame("MCMills");
        CategoryTableXYDataset xydataset = new CategoryTableXYDataset();
        for (int i = 0; i < datasetX.length; i++) {
            xydataset.add(datasetX[i], datasetY[i], serie);
            xydataset.add(datasetX2[i], datasetY2[i], serie2);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(title, xtitle, ytitle, xydataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel mypan = new ChartPanel(chart);
        mypan.setMouseZoomable(true);

        fr4.getContentPane().add(mypan);
        fr4.setSize(400, 800);
        fr4.setVisible(true);
        fr4.pack();

    }

    /**
     * Creates a bargraph, with the given number of entities
     * @param frame_title - name for the frame
     * @param chart_title - name for the chart
     * @param entity_names - Array with entity names
     * @param entity_values - Array with entity values
     * @param save_to_file - if the file should be saved, note the path is "C:/chart.jpg"
     */
    public static void createBarGraph(String frame_title, String chart_title, String[] entity_names, double[] entity_values) {
        JFrame fr4 = new JFrame(frame_title);
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < entity_names.length; i++) {
            pieDataset.setValue(entity_names[i], new Double(entity_values[i]));
        }

        JFreeChart chart = ChartFactory.createPieChart(chart_title, pieDataset, true, true, false);
        ChartPanel mypan = new ChartPanel(chart);
        mypan.setMouseZoomable(true);

        fr4.getContentPane().add(mypan);
        fr4.setSize(400, 800);
        fr4.setVisible(true);
        fr4.pack();
    }

    /**
     * This is a very nice examle of an JFree Bargraph 
     * with many adjustable options
     * @return 
     */
    public static ChartPanel getBarGraph() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for (int i = 0; i < 2; i++) {
            pieDataset.setValue("step " + (i + 1), 100 / 2);
        }

        JFreeChart chart = ChartFactory.createPieChart("", pieDataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        plot.setSectionPaint("" + 0, Color.WHITE);
        plot.setSectionPaint("" + 1, Color.WHITE);

        plot.setSectionOutlinePaint("" + 1, Color.BLACK);
        plot.setSectionOutlinePaint("" + 2, Color.BLACK);
        plot.setSectionOutlineStroke("" + 2, new BasicStroke(
                3.0f, //thickness of the line
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{1.0f, 1.0f}, //// to make dotted {10.0f, 6.0f}
                0.0f));

        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);

        ChartPanel mypan = new ChartPanel(chart);
        mypan.setMouseZoomable(true);
        return mypan;
    }

    public static void main(String[] args) {
        String[] s = {"Johan", "Anders", "Rahil"};
        double[] d = {5, 6, 9};
        createBarGraph("Report", "Arbets tid fördelning", s, d);
    }
}
