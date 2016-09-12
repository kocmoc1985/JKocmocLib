/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Listeners;

import java.awt.Color;
import java.util.Properties;

/**
 *
 * @author KOCMOC
 */
public class Consumer implements MyColorChangedListener, MySignalsRecievedListener {

    private Implementor implementor = new Implementor();

    public Consumer(Implementor implementor) {
        this.implementor = implementor;
        this.implementor.addMyColorChangeListener(this);
        this.implementor.addMySignalsRecievedListener(this);
    }

    @Override
    public void colorChangedEvent(Color color) {
        System.out.println("Color changed´to: " + color.toString());
    }

    @Override
    public void receiveSignals(Object signals) {
        System.out.println("Signal set received:");
        Properties p = (Properties) signals;
        p.list(System.out);
        System.out.println("\n");
    }
}
