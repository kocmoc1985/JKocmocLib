/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.XML;

import java.util.ArrayList;

/**
 *
 * @author KOCMOC
 */
public class XMLWriterExample extends MyXMLBasic {

    private ArrayList<ELEMENT> singleValues = new ArrayList<ELEMENT>();
    private int counter = 1;
    private final static String PATH = "results/";

   
    private String ORDER = "Time,Torque,Force,PhaseAngle,CureSpeed,Temp1,Temp2";

    public XMLWriterExample(String nameOfRootElem) {
        super(nameOfRootElem);
    }

    public void finalizeXmlDoc(ParamStr str) {
        setTestParams(str);
        addValues();
        addTestCriteria();
        String path = PATH + str.getPROBE() + "_" + str.getCHARGE() + "_" + str.getMISH() + ".xml";
        writeXMLDocToFile(path, false, false);
        reset();
    }

    private void reset() {
        singleValues = new ArrayList<ELEMENT>();
        torqMax = Double.MIN_VALUE;
        torqMin = Double.MAX_VALUE;
        cureSpeedMax = 0;
        counter = 1;
    }

    public void addSingleValue(String signalString) {
        singleValues.add(new ELEMENT("SingleValues", "P_" + counter, signalString));
        counter++;
    }

    private void setTestParams(ParamStr str) {
        addToRoot("Probe", str.getPROBE());
        addToRoot("Charge", str.getCHARGE());
        addToRoot("Mishung", str.getMISH());
        //
        addToRoot("Temperature", str.getTemperature());
        addToRoot("TestTime", str.getTestTime());
        addToRoot("Amplitude", str.getAmplitude());
        addToRoot("Frequency", str.getFrequency());
    }

    private void addTestCriteria() {
        //
        ArrayList<ELEMENT> children = new ArrayList<ELEMENT>();
        //
        ELEMENT minValue = new ELEMENT("TestCriteria", null, "MinTorque", "" + torqMin, null);
        ELEMENT maxValue = new ELEMENT("TestCriteria", null, "MaxTorque", "" + torqMax, null);
        ELEMENT diffMaxMin = new ELEMENT("TestCriteria", null, "DiffMaxMin", "" + calcDiff(), null);
        ELEMENT cureSpeed = new ELEMENT("TestCriteria", null, "CureSpeedMaxValue", "" + cureSpeedMax, null);
        //
        children.add(minValue);
        children.add(maxValue);
        children.add(diffMaxMin);
        children.add(cureSpeed);
        //
        ELEMENT testCiriteria = new ELEMENT("ROOT", null, "TestCriteria", null, children);
        //
        addElement(testCiriteria);
    }

    public void addValues() {
        //
        ArrayList<ELEMENT> children = new ArrayList<ELEMENT>();
        //
        ELEMENT elementCount = new ELEMENT("SingleValues", "Count", "" + (counter - 1));
        //
        ELEMENT elementOrder = new ELEMENT("SingleValues", null, "Order", ORDER, singleValues);
        //
        children.add(elementCount);
        children.add(elementOrder);
        //
        ELEMENT elementSingleValues = new ELEMENT("ROOT", null, "SingleValues", null, children);
        //
        addElement(elementSingleValues);
    }
    private double torqMax = Double.MIN_VALUE;
    private double torqMin = Double.MAX_VALUE;

    public void torqueCalc(String torque) {
        //
        if (HelpM.isDouble(torque) == false) {
            return;
        }
        //
        double torq = Double.parseDouble(torque);
        //
        if (torq > torqMax) {
            torqMax = torq;
        } else if (torq < torqMin) {
            torqMin = torq;
        }
    }

    public double calcDiff() {
        return HelpM.roundDOuble(torqMax - torqMin);
    }
    private double cureSpeedMax = 0;

    public void cureSpeedCalc(String value) {
        //
        if (HelpM.isDouble(value) == false) {
            return;
        }
        //
        double val = Double.parseDouble(value);
        //
        if (val > cureSpeedMax) {
            cureSpeedMax = val;
        }

    }
}
