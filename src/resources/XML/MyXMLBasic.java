/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.XML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Administrator
 */
public class MyXMLBasic {

    private SAXBuilder builder;
    private Document DOCUMENT;
    private Element ROOT;
    private String PATH = "results/test.xml";
    private String NAME_OF_ROOT_ELEM;

    public MyXMLBasic() {
    }

    /**
     * Creates an Empty Document by default
     *
     * @param nameOfRootElem
     */
    public MyXMLBasic(String nameOfRootElem) {
        this.NAME_OF_ROOT_ELEM = nameOfRootElem;
        go();
    }

    private void go() {
        createEmptyDoc();
    }

    /**
     * Initialize document and other common things
     */
    private void createEmptyDoc() {
        //
        DOCUMENT = createDoc();
        //
        ROOT = DOCUMENT.getRootElement();
        ROOT.setName(NAME_OF_ROOT_ELEM);
        ROOT.addContent("\n");
    }

    public Document loadDocFromFile(String path) throws JDOMException, IOException {
        //
        builder = new SAXBuilder();
        //
        DOCUMENT = builder.build(path);//GP.QUESTIONS_FILE_URL
        //
        ROOT = DOCUMENT.getRootElement();
        //
        NAME_OF_ROOT_ELEM = ROOT.getName();
        //
        return DOCUMENT;
    }

    public List getElements() {
        return ROOT.getChildren();
    }

    private Document createDoc() {
        Document doc = new Document(new Element("root"));
        return doc;
    }

    public Element getElemByName(String elemName) {
        return ROOT.getChild(elemName);
    }

    public String getElemTextByName(String elemName) {
        try {
            return ROOT.getChild(elemName).getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getElemTextByName(String parent, String elemName) {
        try {
            return getElemByName(parent).getChildText(elemName);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean setElemTextByName(String elemName, String value) {
        try {
            ROOT.getChild(elemName).setText(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getAttributeValue(Element elem, String attributeName) {
        return elem.getAttributeValue(attributeName);
    }

    public String getAttributeValue(String elem, String attributeName) {
        return ROOT.getChild(elem).getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String elem, String attributeName, String value) {
        try {
            ROOT.getChild(elem).getAttribute(attributeName).setValue(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addAttribute(String elem, String attributeName, String value) {
        try {
            ROOT.getChild(elem).setAttribute(attributeName, value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List getElementsChildren(Element elem) {
        return elem.getChildren();
    }

    public void addToRoot(String elemName, String elemText) {
        Element elem = new Element(elemName);
        //
        elem.setText(elemText);
        //
        ROOT.addContent(elem);
        ROOT.addContent("\n");
    }

    public void addToRoot(String elemName, String elemText, ArrayList<Attribute> attributes) {
        //
        Element elem = new Element(elemName);
        //
        elem.setText(elemText);
        //
        if (attributes != null) {
            for (Attribute attribute : attributes) {
                elem.setAttribute(attribute.getName(), attribute.getValue());
            }
        }
        //
        ROOT.addContent(elem);
        ROOT.addContent("\n");
    }

    public void addElement(ELEMENT elem) {
        //
        Element ELEM = new Element(elem.getElemName());
        //
        //
        ELEM.setText(elem.getElemText());
        //
        if (elem.getElemText() == null || elem.getElemText().isEmpty()) {
            ELEM.addContent("\n");
        }
        //
        if (elem.getAttributes() != null) {
            for (Attribute attribute : elem.getAttributes()) {
                ELEM.setAttribute(attribute.getName(), attribute.getValue());
            }
        }
        //
        addToParent(elem, ELEM);
        //
        if (elem.getChildren() != null) {
            for (ELEMENT child : elem.getChildren()) {
                addElement(child);
            }
        }
        //
    }

    private void addToParent(ELEMENT elem, Element elemm) {
        Element parent;
        if (elem.getParentName().equals("ROOT")) {
            parent = ROOT;
        } else {
            parent = ROOT.getChild(elem.getParentName());
        }
        //
        parent.addContent(elemm);
        parent.addContent("\n");
    }

    /**
     *
     * @param path - path of the file
     * @param append - better to have it = false
     * @param omitDeclaration - defines if the doctype should be written
     */
    public void writeXMLDocToFile(String path, boolean append, boolean omitDeclaration) {
        try {
            // Output to console
//            new XMLOutputter().output(DOCUMENT, System.out);
            //
//            deleteFileIfExist(new File(path));
            //
            FileWriter fstream = new FileWriter(path, append);
            BufferedWriter out = new BufferedWriter(fstream);
            XMLOutputter outp = new XMLOutputter();
            outp.getFormat().setOmitDeclaration(omitDeclaration);
            outp.output(DOCUMENT, out);
            //
            out.close();
            fstream.close();
        } catch (Exception ex) {
            Logger.getLogger(MyXMLBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        createEmptyDoc(); // OBS!!!
        //
    }

    public void testWrite() {
        writeXMLDocToFile(PATH, false, false);
    }

    public void example1() {
        ArrayList<Attribute> chld1Attribute = new ArrayList<Attribute>();
        chld1Attribute.add(new Attribute("chldAttr1", "attr1"));
        chld1Attribute.add(new Attribute("chldAttr2", "attr2"));
        // 
        ELEMENT child1 = new ELEMENT("PARENT", chld1Attribute, "child1", "text child1", null);
        ELEMENT child2 = new ELEMENT("PARENT", chld1Attribute, "child2", "text child2", null);
        //
        ArrayList<ELEMENT> children = new ArrayList<ELEMENT>();
        children.add(child1);
        children.add(child2);
        //
        ArrayList<Attribute> list = new ArrayList<Attribute>();
        list.add(new Attribute("parent", "yes"));
        list.add(new Attribute("children", "2"));
        ELEMENT parent = new ELEMENT(ELEMENT.ROOT, list, "PARENT", null, children);
        //
        addElement(parent);
    }

    public static void main(String[] args) {
        MyXMLBasic basics = new MyXMLBasic("TEST");
        basics.example1();
//        basics.example1();
        basics.testWrite();
    }

    public class Attribute {

        private final String name;
        private final String value;

        public Attribute(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public class ELEMENT {

        private ArrayList<Attribute> attributes;
        private ArrayList<ELEMENT> children;
        private String elemName;
        private String elemText;
        private String parentName;
        public final static String ROOT = "ROOT";

        public ELEMENT(String elemName, String elemText) {
            this.parentName = ROOT;
            this.elemName = elemName;
            this.elemText = elemText;
        }

        public ELEMENT(String parent, String elemName, String elemText) {
            this.parentName = parent;
            this.elemName = elemName;
            this.elemText = elemText;
        }

        public ELEMENT(String parent, ArrayList<Attribute> attributes, String elemName, String elemText, ArrayList<ELEMENT> children) {
            this.attributes = attributes;
            this.elemName = elemName;
            this.elemText = elemText;
            this.children = children;
            this.parentName = parent;
        }

        public String getParentName() {
            return parentName;
        }

        public String getElemName() {
            return elemName;
        }

        public String getElemText() {
            return elemText;
        }

        public ArrayList<Attribute> getAttributes() {
            return attributes;
        }

        public ArrayList<ELEMENT> getChildren() {
            return children;
        }
    }
}
