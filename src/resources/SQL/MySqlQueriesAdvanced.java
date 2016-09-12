/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author KOCMOC
 */
public class MySqlQueriesAdvanced {

    public static JTable jTable1 = new JTable();
    private static MySqlBasic sql_dist = new MySqlBasic(1, 1, null, null, null, null, null);
    private static MySqlBasic sql_source = new MySqlBasic(1, 1, null, null, null, null, null);

    public static String qe_1_formatted(int curr_row) {
        String q_2 = String.format("INSERT INTO MC_BARCODES (lot,part,quantity,test,date_recieve,date_scan)"
                + " values "
                + "("
                + "'%s','%s','%s','%s','%s','%s'"
                + ")",
                jTable1.getValueAt(curr_row, 1),
                jTable1.getValueAt(curr_row, 2),
                jTable1.getValueAt(curr_row, 3),
                jTable1.getValueAt(curr_row, 4),
                jTable1.getValueAt(curr_row, 5),
                jTable1.getValueAt(curr_row, 6));
        return q_2;
    }
    
    public static String qe_1_2_formatted(String param1, String param2, String param3, String param4, String param5, String param6) {
        String q = String.format("UPDATE Recipe_Recipe SET "
                + "PHR = %s,"
                + "weight = %s,"
                + "ContainerNb = '%s',"
                + "phase = '%s',"
                + "MatIndex = '%s' "
                + "WHERE Recipe_Recipe_ID = %s",
                param1, param2, param3, param4, param5, param6);
        return q;
    }
    
    /**
     * Very nice and tested example
     * @param ingredCodeId
     * @param comments - Note this field was of type "varchar(50)"
     * @param updatedOn
     * @param updatedBy 
     */
    private void qe_2_prepared_statement(String ingredCodeId, String comments, String updatedOn, String updatedBy) {

        String q1 = "update Ingred_Comments set "
                + "Comments = ?" + ","
                + "UpdatedOn = ?" + ","
                + "UpdatedBy = ?" + ""
                + "where IngredientCode_ID = " + ingredCodeId;
        //
        byte[] bytes = comments.getBytes();
        //
        //
        try {
            sql.prepareStatement(q1);
            //
            sql.getPreparedStatement().setBytes(1, bytes);
            sql.getPreparedStatement().setString(2, updatedOn);
            sql.getPreparedStatement().setString(3, updatedBy);
            //
            //
            sql.executeUpdatePreparedStatement();
            //
        } catch (SQLException ex) {
            Logger.getLogger(MySqlQueriesAdvanced.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void qe_2_1_prepared_statement(String q){
        try {
            ResultSet rs = sql_source.execute(q);

            while (rs.next()) {
//                String q1 = "insert into DAYRECORD(r_id,datum,dayRecord,line,millsNr) values(?,?,?,?,?)";

                String q1;
                
                    q1 = "insert into " + "table_name" + "(" + "columnd_1" + ","
                            + "columnd_2" + "," + "columnd_3" + "," + "columnd_3" + ","
                            + "columnd_5" + ") " + "values(?,?,?,?,?)";

                byte[] bytes = rs.getBytes("column_name");

                if (bytes == null) {
                    return;
                }

                if (bytes.length > 1) {
                    sql_dist.prepareStatement(q1);
                    
                        //from sql to .mdb
                        sql_dist.getPreparedStatement().setString(1, rs.getString("column_name_1"));
                        sql_dist.getPreparedStatement().setString(2, rs.getString("column_name_2"));
                        sql_dist.getPreparedStatement().setBytes(3, bytes);
                        sql_dist.getPreparedStatement().setString(4, rs.getString("column_name_3"));
                        sql_dist.getPreparedStatement().setInt(5, rs.getInt("column_name_4"));
                    
                    try {
                        sql_dist.executeUpdatePreparedStatement();
                    } catch (SQLException ex2) {
                        System.out.println("dayrecord already exists ");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlQueriesAdvanced.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
