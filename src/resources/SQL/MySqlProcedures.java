/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class MySqlProcedures {

    public static int runProcedureIntegerReturn_A(Connection sqlConnection, String procedure) throws SQLException {
        CallableStatement proc = sqlConnection.prepareCall("{ ? = call " + procedure + " }");
        proc.registerOutParameter(1, Types.INTEGER);
        proc.execute();
        int ret;
        //
        try {
            ret = proc.getInt(1);
        } catch (Exception ex) {
            Logger.getLogger(MySqlProcedures.class.getName()).log(Level.SEVERE, null, ex);
            ret = -1;
        }
        //
        return ret;
    }

    public static int runProcedureIntegerReturn_A_2(SqlBasicLocal sql, String procedure) {
        CallableStatement proc;
        //
        try {
            proc = sql.getConnection().prepareCall("{ ? = call " + procedure + " }");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlProcedures.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        //
        int ret;
        //
        try {
            ret = proc.getInt(1);
        } catch (Exception ex) {
            Logger.getLogger(MySqlProcedures.class.getName()).log(Level.SEVERE, null, ex);
            ret = -1;
        }
        //
        return ret;
    }
}
