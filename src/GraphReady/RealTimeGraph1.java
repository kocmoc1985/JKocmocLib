/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphReady;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;


import org.jfree.chart.JFreeChart;


import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author Administrator
 */
public class RealTimeGraph1  {

    JFrame fr4 = new JFrame();
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    JFreeChart chart = ChartFactory.createTimeSeriesChart("Realtime monitoring", "time", "torque", dataset, true, true, false);
    ChartPanel mypan = new ChartPanel(chart);
    TimeSeries pop = new TimeSeries("Torque");

    public RealTimeGraph1() {
        fr4.getContentPane().add(mypan);
        dataset.addSeries(pop);
        fr4.setSize(400, 800);
        fr4.setVisible(true);
        fr4.pack();
    }


    public void draw_graph(double rst) {
        pop.add(new Second(), rst);
    }

}
