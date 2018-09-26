/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphReady;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.CategoryTableXYDataset;

/**
 * Easily creates a XYline graph
 * @author Administrator
 */
public class XYGraph {

    private String graphTitle = "";
    private String x_axis_title = "";
    private String y_axis_title = "";
    private String frame_title;
    private JFrame fr4;
    private JFreeChart chart;
    private CategoryTableXYDataset xydataset = new CategoryTableXYDataset();
    ChartPanel mypan;
    private Graphics g;

    public XYGraph(String fram_title) {
        this.frame_title = fram_title;
    }

    public XYGraph(String graphTitle, String x_asis_title, String y_axis_title, String frame_title) {
        this.graphTitle = graphTitle;
        this.x_axis_title = x_asis_title;
        this.y_axis_title = y_axis_title;
        this.frame_title = frame_title;
    }

    public void addPoint(double x, double y, String serie) {
        xydataset.add(x, y, serie);
    }

    /**
     * This can be used for example for Online graph drawing
     * Draw graph must be called before using this method
     * @param x
     * @param y
     * @param serie - name of the serie
     */
    public void addPointWithRefresh(double x, double y, String serie) {
        xydataset.add(x, y, serie);
        refresh();
    }

    /**
     * Create a Serie/Series
     * @param datasetX - array for x-axis
     * @param datasetY - array for y-axis
     * @param serie - name of the serie
     */
    public void addPointArray(double[] datasetX, double[] datasetY, String serie) {
        for (int i = 0; i < datasetX.length; i++) {
            xydataset.add(datasetX[i], datasetY[i], serie);
        }
    }

    /**
     * Refresh of the graph
     */
    public void refresh() {
        g = mypan.getGraphics();
        mypan.paint(g);
    }

    public void DrawGraph() {
        fr4 = new JFrame(frame_title);
        fr4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chart = ChartFactory.createXYLineChart(graphTitle, x_axis_title, y_axis_title, xydataset, PlotOrientation.VERTICAL, true, true, true);
        mypan = new ChartPanel(chart);
        mypan.setMouseZoomable(true);

        fr4.getContentPane().add(mypan);
//        fr4.setSize(400, 800);
        fr4.setLocation(345, 340);
        fr4.setVisible(true);
        fr4.pack();
    }

    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] y = {50, 60, 50, 70, 65, 70, 50, 80, 75, 90};
        double[] x2 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        double[] y2 = {50, 60, 50, 70, 65, 70, 50, 80, 75, 90};
//        createXYGraph(x, y, "torque", "", "", "");
        XYGraph xg = new XYGraph("MCMills");
        xg.DrawGraph();
        xg.addPointArray(y2, y2, "");

    }
}
