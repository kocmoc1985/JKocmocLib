/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.SQL;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator
 */
public class MySqlTable {

    public static int getRowByValue(JTable table, String col_name, String row_value) {
        for (int i = 0; i < table.getColumnCount(); ++i) {
            if (table.getColumnName(i).equals(col_name)) {
                for (int y = 0; y < table.getRowCount(); ++y) {
                    String curr_row_value = (String) table.getValueAt(y, i);
                    //
                    if (curr_row_value == null) {
                        continue;
                    }
                    //
                    if (curr_row_value.equals(row_value)) {
                        return y;
                    }
                }
            }
        }
        return -1;
    }
    
    public static String getLastIncrementedId(SqlBasicLocal sql, String tableName) {
        //
        String q = "SELECT IDENT_CURRENT('" + tableName + "')";
        //
        try {
            ResultSet rs = sql.execute(q);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int getRowCount(SqlBasicLocal sql, String tableName) {
        String q = "SELECT COUNT(*) FROM " + tableName;
        int ammount = -1;
        //
        try {
            ResultSet rs = sql.execute(q);

            if (rs.next()) {
                ammount = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlTable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return ammount;
    }

    public static String jTableToCSV(JTable table, boolean writeToFile) {
        //
        String csv = "";
        //
        for (Object colName : getVisibleColumnsNames(table)) {
            csv += colName + ";";
        }
        //
        csv += "\n";
        //
        //
        for (int x = 0; x < table.getRowCount(); x++) {
            for (Object rowValue : getLineValuesVisibleColsOnly(table, x)) {
                csv += rowValue + ";";
            }
            csv += "\n";
        }
        //
        String path = get_desktop_path() + "\\csv_table.csv";
        //
        if (writeToFile) {
            try {
                writeToFile(path, csv);
                JOptionPane.showMessageDialog(null, "Export file ready, the file is in: " + path);
            } catch (IOException ex) {
                Logger.getLogger(MySqlTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //
        return csv;
    }
    
     private String sqlTableToCsv(SqlBasicLocal sql,String query,int startCol,int endCol) throws SQLException {
        String CSV_STRING = "";
        ResultSet rs = sql.execute(query);

        rs.beforeFirst();

        while (rs.next()) {
            for (int i = startCol; i < endCol; i++) {
                String actual_field = rs.getString(i);
                CSV_STRING += actual_field + ";";
            }
            CSV_STRING += "\n";
        }
        return CSV_STRING.trim();
    }

    /**
     * OBS! JTable row index start with 0
     *
     * @param table
     * @param rowNr
     * @return
     */
    public static ArrayList getLineValuesVisibleColsOnly(JTable table, int rowNr) {
        ArrayList rowValues = new ArrayList();
        for (int x = 0; x < table.getColumnCount(); x++) {
            if (columnIsVisible(table, x)) {
                String value = (String) table.getValueAt(rowNr, x);
                rowValues.add(value);
            }
        }
        return rowValues;
    }

    public static ArrayList getVisibleColumnsNames(JTable table) {
        ArrayList columnNames = new ArrayList();
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (columnIsVisible(table, i)) {
                columnNames.add(getColumnNameByIndex(table, i));
            }
        }
        return columnNames;
    }

    public static boolean columnIsVisible(JTable table, int column) {
        int width = table.getColumnModel().getColumn(column).getWidth();
        return width == 0 ? false : true;
    }

    public static String getColumnNameByIndex(JTable table, int column) {
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(column);
        return (String) tc.getHeaderValue();
    }

    /**
     * This one is great if you are adding rows to the table and have an id by
     * which you can track the latest added one with this method
     *
     * @param table
     * @param colName
     * @return
     */
    public static String getLatestFromTable(JTable table, String colName) {
        int max = 0;
        int act_val;
        for (int i = 0; i < table.getRowCount(); i++) {
            try {
                act_val = Integer.parseInt(getValueGivenRow(table, i, colName));
            } catch (Exception ex) {
                act_val = 0;
            }
            if (act_val > max) {
                max = act_val;
            }
        }
        return "" + max;
    }

    /**
     * SUPER SUPER SUPER GOOD!!
     *
     * @param table
     * @param colName
     * @return
     */
    public static String getValueSelectedRow(JTable table, String colName) {
        int selected_row = table.getSelectedRow();
        return (String) table.getValueAt(selected_row, getColumnByName(table, colName));
    }

    /**
     * Almost the same as "getValueSelectedRow" but the row is predefined in
     * this case
     *
     * @param table
     * @param row
     * @param colName
     * @return
     */
    public static String getValueGivenRow(JTable table, int row, String colName) {
        return (String) table.getValueAt(row, getColumnByName(table, colName));
    }

    /**
     * SUPER!!!
     *
     * @param table
     * @param row
     * @param colName
     * @param value
     */
    public static void setValueGivenRow(JTable table, int row, String colName, Object value) {
        table.setValueAt(value, row, getColumnByName(table, colName));
    }

    /**
     * Super Important!!!
     *
     * @param table
     * @param name
     * @return
     */
    public static int getColumnByName(JTable table, String name) {
        for (int i = 0; i < table.getColumnCount(); ++i) {
            if (table.getColumnName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * #Column width, col width, set column width
     *
     * @param colName
     * @param table
     * @param percent
     */
    public static void setColumnWidthByName(String colName, JTable table, double percent) {
        int table_width = table.getWidth();
        double width = table_width * percent;
        table.getColumn(colName).setPreferredWidth((int) width);
    }
    

    public static void setColumnWidthByIndex(int colIndex, JTable table, int width) {
        table.getColumnModel().getColumn(colIndex).setPreferredWidth(width);
    }

    public static int getColumnWidthByIndex(int colIndex, JTable table) {
        return table.getColumnModel().getColumn(colIndex).getWidth();
    }

    public static void synchColumnWidths(JTable source, JTable dest) {
        for (int i = 0; i < source.getColumnCount(); i++) {
            int srcWidth = getColumnWidthByIndex(i, source);
            setColumnWidthByIndex(i, dest, srcWidth);
        }
    }

    public static ArrayList<Integer> saveColumnWidths(ArrayList<Integer> listBeforeChanges,int initialWidth,JTable table) {
        //
        ArrayList<Integer> list = new ArrayList<Integer>();
        //
        for (int i = 0; i < table.getColumnCount(); i++) {
            list.add(getColumnWidthByIndex(i, table));
        }
        //
        if(listBeforeChanges.isEmpty()){
            return list;
        }
        //
        if(checkIfInitialWidths(list, initialWidth) == false){
            return list;
        }else{
            return listBeforeChanges;
        }
        //
    }

    private static boolean checkIfInitialWidths(ArrayList<Integer> list, int initialWidth) {
        for (Integer i : list) {
            if (i != initialWidth && i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void restoreColumnWidths(JTable table, ArrayList<Integer> list) {
        //
        if (list.isEmpty()) {
            return;
        }
        //
        for (int i = 0; i < table.getColumnCount(); i++) {
            setColumnWidthByIndex(i, table, list.get(i));
        }
    }

    public static boolean hideColumnByName(JTable table, String name) {
        for (int i = 0; i < table.getColumnCount(); ++i) {
            if (table.getColumnName(i).equals(name)) {
                table.getColumnModel().getColumn(i).setMinWidth(0);
                table.getColumnModel().getColumn(i).setMaxWidth(0);
                table.getColumnModel().getColumn(i).setWidth(0);
                return true;
            }
        }
        return false;
    }

    public static void clearAllRowsJTable(JTable table) {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        //
        int rowCount = dm.getRowCount();
        //
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    public static void addRowJTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{});
    }

    public static void changeTableHeaderTitleOfOneColumn(JTable table, int column, String newTitle) {
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(column);
        tc.setHeaderValue(newTitle);
        th.repaint();
    }

    public static void changeTableHeaderColorOneColumn(JTable table, int column, final Color color) {
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(column);
        //
        TableCellRenderer renderer = new TableCellRenderer() {
            JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                label.setOpaque(true);
                label.setText("" + value);
                label.setBackground(color);
                return label;
            }
        };
        //
        tc.setHeaderRenderer(renderer);
        th.repaint();
    }

    public static void changeTableHeaderBorderOneColumn(JTable table, int column, final Color borederColor) {
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(column);
        //
        TableCellRenderer renderer = new TableCellRenderer() {
            JLabel label = new JLabel();

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                label.setOpaque(true);
                label.setText("" + value);
                label.setBorder(BorderFactory.createLineBorder(borederColor));
                return label;
            }
        };
        //
        tc.setHeaderRenderer(renderer);
        th.repaint();
    }

    /**
     * SUPER!
     *
     * @param table_name
     * @param jTable
     * @param selected_row
     * @param auto_increment
     * @return
     */
    public static String update_table_after_manually_changing_row(String table_name, JTable jTable, int selected_row, boolean auto_increment) {
        StringBuilder stringBuilder = new StringBuilder("insert into " + table_name + " values(");
        //
        boolean first_record = true;
        //
        int column_count = jTable.getModel().getColumnCount();
        for (int i = 0; i < column_count; i++) {
            if (first_record && auto_increment) {
                first_record = false;
                continue;
            }
            //
            String formatted_value = isString("" + jTable.getValueAt(selected_row, i), true);
            stringBuilder.append(formatted_value);
            stringBuilder.append(",");
        }
        //
        //
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     *
     * @param value
     * @param only_string_values - this means that table contains only string
     * values
     * @return
     */
    private static String isString(String value, boolean only_string_values) {
        //
        if (only_string_values) {
            return "'" + value + "'";
        }
        //
        try {
            Double.parseDouble(value);
            return value;
        } catch (Exception e) {
            return "'" + value + "'";
        }
    }
    
    

    

    /**
     * Count nr of columns in table
     *
     * @param rs
     * @return
     * @throws SQLException
     * @deprecated
     */
    public int get_column_count(ResultSet rs) throws SQLException {
        ResultSetMetaData meta;
        String[] headers;
        meta = rs.getMetaData();
        headers = new String[meta.getColumnCount()];
        return headers.length;
    }

    public static int get_column_count(DefaultTableModel model) {
        return model.getColumnCount();
    }

    public static int get_row_count(DefaultTableModel model) {
        return model.getRowCount();
    }

    public static int moveRowToEnd(JTable table, int currRow) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.moveRow(currRow, currRow, table.getRowCount() - 1);
        return table.getRowCount() - 1;
    }

    public static void moveRowTo(JTable table, int rowToMove, int rowToMoveTo) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.moveRow(rowToMove, rowToMove, rowToMoveTo);
    }
    
    /**
     * 
     * @param rs
     * @param jTable
     * @param roundingFormat - is passed like "#.##"
     */
    public static synchronized void build_table_common_with_rounding(ResultSet rs, JTable jTable, String roundingFormat) {
        //
        if (rs == null) {
            return;
        }
        //
        try {
            String[] headers = getHeaders(rs);
            Object[][] content = getContentRounding(rs, roundingFormat);
            jTable.setModel(new DefaultTableModel(content, headers));
        } catch (SQLException ex) {
            Logger.getLogger(MySqlTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void build_table_common(ResultSet rs, JTable jTable) {
        if (rs == null) {
            return;
        }
        try {
            String[] headers = getHeaders(rs);
            Object[][] content = getContent(rs);
            jTable.setModel(new DefaultTableModel(content, headers));
        } catch (SQLException ex) {
            Logger.getLogger(MySqlTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * A very useful function, used to get Headers from the ResultSet(From
     * database) This method is often used together with the
     * "getContent(ResultSet) method" Example 1. rs =
     * SqlConnection.statement.executeQuery("SELECT * FROM Vara"); 2. Object[]
     * headers = Controller.getHeaders(rs);
     *
     * @param rs
     * @return String array containing Headers from resultSet
     * @throws SQLException
     */
    private static String[] getHeaders(ResultSet rs) throws SQLException {
        ResultSetMetaData meta; // Returns the number of columns
        String[] headers; // skapar en ny array att lagra titlar i
        meta = rs.getMetaData(); // Den parameter som skickas in "ResultSet rs" innehåller Sträng vid initialisering
        headers = new String[meta.getColumnCount()]; // ger arrayen "headers" initialisering och anger antalet positioner
        for (int i = 0; i < headers.length; i++) {
            headers[i] = meta.getColumnLabel(i + 1);
        }
        return headers;
    }

    /**
     * This method makes 2D Array from The Resultset used to fill the table,
     * Example 1. rs = SqlConnection.statement.executeQuery("SELECT * FROM
     * Vara"); 2. Object[][] content = Controller.getContent(rs); 3. the
     * "content" Obj is than used for creating a instance of DefaultTableModel.
     * Note that Headers are also needed for creating!!
     *
     * @param ResultSet
     * @return 2D Object Array
     * @throws SQLException
     */
    private static Object[][] getContent(ResultSet rs) throws SQLException {

        ResultSetMetaData rsmt;
        Object[][] content;
        int rows, columns;
        rsmt = rs.getMetaData(); // får in antalet columner
        rs.last(); // flyttar pekaren till sista positon
        rows = rs.getRow(); // retrieves the current antalet rows och lagrar det i variabeln "rows"
        columns = rsmt.getColumnCount(); // retrieves number of columns och lagrar det i "columns".
        content = new Object[rows][columns]; // ger arrayen content som är en "Object"
        // initialisering i den första demensionen är "rows" i den andra "columns"

        for (int row = 0; row < rows; row++) {
            rs.absolute(row + 1); // Flytta till rätt rad i resultatmängden
            for (int col = 0; col < columns; col++) {
                content[row][col] = rs.getObject(col + 1);
            }
        }

        return content;
    }
    
    private static synchronized Object[][] getContentRounding(ResultSet rs, String format) throws SQLException {
        ResultSetMetaData rsmt;
        Object[][] content;
        int rows, columns;
        rsmt = rs.getMetaData(); // får in antalet columner
        rs.last(); // flyttar pekaren till sista positon
        rows = rs.getRow(); // retrieves the current antalet rows och lagrar det i variabeln "rows"
        columns = rsmt.getColumnCount(); // retrieves number of columns och lagrar det i "columns".
        content = new Object[rows][columns]; // ger arrayen content som är en "Object"
        // initialisering i den första demensionen är "rows" i den andra "columns"
        //
        for (int row = 0; row < rows; row++) {
            rs.absolute(row + 1); // Flytta till rätt rad i resultatmängden
            for (int col = 0; col < columns; col++) {
                Object obj = rs.getString(col + 1);
                content[row][col] = roundDouble(obj, format);//-----------------------OBS ROUNDING IS DONE HERE
            }
        }
        //
        return content;
    }
    
    private static synchronized Object roundDouble(Object obj, String format) {
        if (isDouble(obj)) {
            String val = (String) obj;
            double ret = Double.parseDouble(val);
            return "" + roundDouble(ret, format);
        } else {
            return obj;
        }
    }

    /**
     *
     * @param number
     * @param format - is passed like "#.###"
     * @return
     */
    private static synchronized double roundDouble(double number, String format) {
        DecimalFormat twoDForm = new DecimalFormat(format);
        DecimalFormatSymbols s = DecimalFormatSymbols.getInstance();
        s.setDecimalSeparator('.');
        twoDForm.setDecimalFormatSymbols(s);
        return Double.valueOf(twoDForm.format(number));
    }

    private static synchronized boolean isDouble(Object obj) {
        if (obj instanceof String) {
            String val = (String) obj;
            //
            try {
                Integer.parseInt(val);
                return false;
            } catch (Exception ex) {
            }
            //
            try {
                Double.parseDouble(val);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    /**
     * This one i used once in a "getContent(ResultSet rs)" method
     *
     * @param obj
     * @return
     */
    private static Object formatDate(Object obj) {
        if (obj instanceof Timestamp) {
            Timestamp t = (Timestamp) obj;
            String date = t.toString().substring(0, 10);
            return date;
        }
        return obj;
    }

    /**
     * This one doesn't use "rs.last()" which is important in those cases when
     * the "statement =
     * connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
     * ResultSet.CONCUR_UPDATABLE)" causes troubles
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public Object[][] getContent2(ResultSet rs) throws SQLException {
        int MAX_ROWS = 100;
        ResultSetMetaData rsmt;
        Object[][] content;
        int columns;
        rsmt = rs.getMetaData(); // får in antalet columner
        columns = rsmt.getColumnCount(); // retrieves number of columns och lagrar det i "columns".
        content = new Object[MAX_ROWS][columns]; // ger arrayen content som är en "Object"

        int row = 0;
        while (rs.next() && row < (MAX_ROWS - 1)) {
            for (int col = 0; col < columns; col++) {
                content[row][col] = rs.getObject(col + 1);
            }
            row++;
        }

        return content;
    }

    /**
     * SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER
     * IMPORTANT
     *
     * @param jTable
     */
    public static void stop_editing_so_adjusted_value_is_not_lost(JTable jTable) {
        jTable.editCellAt(0, 0);
    }

    /**
     * SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT
     * Automatically creates the insert String/Query
     *
     * @param table_name
     * @param jTable
     * @param selected_row
     * @param auto_increment - points if the table has auto_increment
     * @return
     */
    public static String build_insert_query(String table_name, JTable jTable, int selected_row, boolean auto_increment) {
        StringBuilder stringBuilder = new StringBuilder("insert into " + table_name + " values(");
        //
        boolean first_record = true;
        //
        int column_count = jTable.getModel().getColumnCount();
        for (int i = 0; i < column_count; i++) {
            if (first_record && auto_increment) {
                first_record = false;
                continue;
            }
            //
            String formatted_value = isString("" + jTable.getValueAt(selected_row, i));
            stringBuilder.append(formatted_value);
            stringBuilder.append(",");
        }
        //
        //
        stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        stringBuilder.append(")");
//        System.out.println("s_builer = " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT This
     * method checks if a database table value is a String or a numeric value
     *
     * @param value
     * @return
     */
    private static String isString(String value) {
        try {
            Double.parseDouble(value);
            return value;
        } catch (Exception e) {
            return "'" + value + "'";
        }
    }

    /**
     * 
     *
     * @param rowsToHighlight
     * @param jTable
     * @param idColumnName
     * @param color
     */
    public void paint_selected_rows(final LinkedList<Integer> rowsToHighlight, final JTable jTable, final String idColumnName, final Color color) {
        jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(null);
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //
                for (Integer planid : rowsToHighlight) {
                    if (row == getRowByValue(table, idColumnName, "" + planid)) {
                        c.setBackground(color);
                        return c;
                    }
                }
                return this;
            }
        });
        //
        jTable.repaint();
    }

    public void unpaint_row(JTable table,String id,String idColumnName, LinkedList<Integer> rowsToHighlight) {
        Iterator<Integer> iter = rowsToHighlight.iterator();
        int i = 0;
        //
        while (iter.hasNext()) {
            //
            int res = iter.next();
            //
            if (res == Integer.parseInt(id)) {
                rowsToHighlight.remove(i);
            }
            i++;
        }
        //
       paint_selected_rows(rowsToHighlight, table, idColumnName, Color.lightGray);
    }

    /**
     * This method is able to paint / unpaint a given row. To unpaint a row call
     * this method with "-1" for "row_" parameter
     *
     * @param row_ - row to paint/unpaint
     * @param jTable
     * @param jFrame
     */
    public static void paint_selected_row(final int row_, final JTable jTable, final Color color) {
        jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setBackground(null);
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row == row_) {
                    c.setBackground(color);
                    return c;
                } else {
                    return this;
                }
            }
        });
        //
        //This one is the best one to call if some graphichal update is needed, other "repaint methods" cause not what is wanted
        jTable.repaint();
    }

    /**
     * This should be done after "initComponents()"
     *
     * @param table
     */
    public static void paintEachSecondTableRow(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });
    }

    /**
     * This method is for easy filling of the table and is to be used with
     * "getHeaders(...) && getContent(...)" mehods Example: 1. rs =
     * SqlConnection.statement.executeQuery("SELECT * FROM Vara"); 2. String[]
     * headers = Controller.getHeaders(rs); 3. Object[][] content =
     * Controller.getContent(rs); 4. JTable table = new
     * JTable(setTableContent(........)) -->> Ready the table is created and
     * filled with info
     *
     * @param content
     * @param headers
     * @return new "filled(contains data)" instance of DefaultTableModel.
     */
    public static DefaultTableModel setTableContent(Object[][] content, Object[] headers) {
        return new DefaultTableModel(content, headers);
    }

    /**
     *
     * @param table
     * @param column
     * @return
     */
    public static Object getValueFromSelectedRow(JTable table, int column) {
        return table.getValueAt(table.getSelectedRow(), column);
    }

    /**
     * Just in case if you have forgotten how to do the atomatic row sortation
     *
     * @param table
     * @return Jtable Columns that can be sorted
     */
    public static JTable automatic_JTable_Row_Sorter(JTable table) {
        table.setAutoCreateRowSorter(true);
        return table;
    }

    /**
     * Just in case if you have forgotten how to do the atomatic column resizing
     *
     * @param table
     * @return JTable with resizible columns
     */
    public static JTable automatic_Column_resizing(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    /**
     * If you want to have some columns with preffered width
     *
     * @param table
     */
    private void adjustColumnWidths(JTable table) {
        //not sure this one is needed
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        TableColumn column_date = table.getColumnModel().getColumn(1);
        column_date.setPreferredWidth(column_date.getWidth() * 2);

        TableColumn column_recipe = table.getColumnModel().getColumn(2);
        column_recipe.setPreferredWidth((int) (column_recipe.getWidth() * 1.5));
    }

    /**
     *
     * @param table
     * @param row - the row to mark
     */
    public static void markGivenRow(JTable table, int row) {
        try {
            table.changeSelection(row, 0, false, false);
        } catch (Exception ex) {
        }

    }

    /**
     * This function is very useful to be able to jump to the end of the table !
     * The JTabble is usually placed into a JScrollPane element
     *
     * @param scroll_pane
     * @return
     */
    public static JScrollPane scroll_to_the_end(JScrollPane scroll_pane) {
        scroll_pane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
        return scroll_pane;
    }

    public static String get_desktop_path() {
        return System.getProperty("user.home") + "\\" + "Desktop";
    }

    public static void writeToFile(String fileName, String textToWrite) throws IOException {
        FileWriter fstream = new FileWriter(fileName, false);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(textToWrite);
        out.newLine();
        out.flush();
    }
}
