/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KOCMOC
 */
public class A_MySqlLib {
    
    public static String extractTableName(String q) {
        Pattern p = Pattern.compile("from\\s+(?:\\w+\\.)*(\\w+)($|\\s+[WHERE,JOIN,START\\s+WITH,ORDER\\s+BY,GROUP\\s+BY])", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(q);
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }
    
     public static String quotes(String str, boolean number) {
        //
        if (str == null || str.equals("NULL")) {
            return "NULL";
        }
        //
        if (number) {
            return str.replaceAll("'", "");
        } else {
            if (str.contains("'")) {
                return str;
            } else {
                return "'" + str + "'";
            }
        }
    }
    
     public static boolean columnExistsSqlTable(SqlBasicLocal sql, String colName, String tableName) {
        String q = "select top 1 " + colName + " from " + tableName;
        //
        try {
            sql.execute(q);
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(HelpA.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //
    }

    

    public static boolean entryExistsSql(SqlBasicLocal sql, String q) {
        try {
            ResultSet rs = sql.execute(q);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(A_MySqlLib.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //
        return false;
    }

    public static String getSingleParamSql(SqlBasicLocal sql, String tableName,
            String columnWhere, String valueWhere, String columnGet, boolean number) {
        //
        String q = "SELECT * from " + tableName
                + " where " + columnWhere + "=" + quotes(valueWhere, number);
        //
        try {
            ResultSet rs = sql.execute(q);
            if (rs.next()) {
                return rs.getString(columnGet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(A_MySqlLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
            Logger.getLogger(A_MySqlLib.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(A_MySqlLib.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        //
        return ammount;
    }

    public static int getRowCount(SqlBasicLocal sql, String tableName, String whereCondition) {
        //
        String q = "SELECT COUNT(*) FROM " + tableName
                + " where " + whereCondition;
        //
        int ammount = -1;
        //
        try {
            ResultSet rs = sql.execute(q);

            if (rs.next()) {
                ammount = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(A_MySqlLib.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        //
        return ammount;
    }
}
