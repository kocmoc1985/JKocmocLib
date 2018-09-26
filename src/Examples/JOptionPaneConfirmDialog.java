/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

/**
 * Det här är en ***Länkad-Lista*** som lagrar "Integers" OBS ej Objekt!!!!
 * Den fungerar genom att man skapar en Objekt av Typen IntNode och ger den referens
 * till ett annat IntNode Objekt beroende var i "Listan" man vill ha Objektet på
 * så sätt skapas en nät med objekt som refererar till varandra. 
 * @author KOCMOC
 */
public class JOptionPaneConfirmDialog {

    private int data;
    private JOptionPaneConfirmDialog next;

    public JOptionPaneConfirmDialog(int data, JOptionPaneConfirmDialog next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public JOptionPaneConfirmDialog getNext() {
        return this.next;
    }

    /**
     * Ändrar skapar/länken till ett annat Objekt av typen IntNode som kommer att
     * placeras efter det Objektet man just nu befinner sig på(this)
     * @param next
     */
    public void setNext(JOptionPaneConfirmDialog next) {
        this.next = next;
    }

    /**
     * Returnerar värde av en bestämd element, som skickas med vid anrop
     * @param pos
     * @return
     */
    public int get(int pos) {
        int x = 0;
        JOptionPaneConfirmDialog node = this;
        for (int i = 0; i < pos - 1; i++) {
            x = node.getNext().getData();
            node = node.getNext();

        }
        return x;
    }

    /**
     * Lägger till ett element i angiven position, kann ej lägga till i position 0
     * @param list
     * @param index
     * @param value
     * @return
     */
    public JOptionPaneConfirmDialog add(JOptionPaneConfirmDialog list, int index, int value) {

        for (int i = 0; i < index - 1; i++) {
            list = list.getNext();
        }
        JOptionPaneConfirmDialog node = new JOptionPaneConfirmDialog(value, list.getNext());
        list.setNext(node);

        return list;
    }

    /**
     * Tar bort en element i angiven Position och nämligen river länken mellan n-1 och n
     * och skapar länken med n-1 och n+1, OBS element i pos 0 kan också tas bort!!
     * @param list
     * @param index
     * @return
     */
    public JOptionPaneConfirmDialog remove(JOptionPaneConfirmDialog list, int index) {

        if (index == 0) {
            list.setData(list.getNext().getData());
            list.setNext(list.getNext().getNext());
        } else {

            for (int i = 0; i < index - 1; i++) {
                list = list.getNext();
            }
            list.setNext(list.getNext().getNext());
        }
        return list;
    }

    /**
     * Tar bort det första Objektet i Listan
     */
    public void removeFirst() {
        this.setData(this.getNext().getData());
        this.setNext(this.getNext().getNext());
    }

    /**
     * Lägger till ett Objekt i början av Listan, kräver att man skriver
     * t.ex lista = lista.addFirst(10) annars läggs inte 10 till Listan!
     * Vet ej varför???
     * @param value
     * @return
     */
    public JOptionPaneConfirmDialog addFirst(int value) {
        JOptionPaneConfirmDialog node = new JOptionPaneConfirmDialog(value, this);
        return node;
    }

    /**
     * Lägger till ett Objekt i slutet av Listan
     * @param value
     * @return
     */
    public void addLast(int value) {
        int x = this.size();
        JOptionPaneConfirmDialog n = this;
        for (int i = 0; i < x - 1; i++) {
            n = n.getNext();
        }
        JOptionPaneConfirmDialog node = new JOptionPaneConfirmDialog(value, null);
        n.setNext(node);

    }

    /**
     * Raderar det sista elementet i listan
     */
    public void removeLast() {
        int x = this.size();
        JOptionPaneConfirmDialog node = this;
        for (int i = 0; i < x - 2; i++) {
            node = node.getNext();
        }
        node.setNext(null);

    }

    /**
     * Kollar upp längden på arrayen
     * @param list
     * @return
     */
    public int size() {
        int i = 0;
        JOptionPaneConfirmDialog node = this;
        while (node != null) {
            node = node.getNext();
            i++;
        }
        return i;
    }

    public static void println(JOptionPaneConfirmDialog node) {
        System.out.print("[ ");
        while (node != null) {
            System.out.print(node.getData());
            node = node.getNext();
            if (node != null) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}
