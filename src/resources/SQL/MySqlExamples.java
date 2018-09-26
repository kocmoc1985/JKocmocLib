/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.SQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import resources.MyFile;

/**
 *
 * @author Administrator
 */
public class MySqlExamples {

    //This class (MySql_old) is the most rational one!
    MySql_old ms = new MySql_old();

    /**
     * An Extremly good example of using the prepared statements in connection with
     * file uploading to the db
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException 
     */
    public void prepared_statement_use_example() throws FileNotFoundException, IOException, SQLException {

        ms.connectODBC("ODBC", "user", "pass");

        String q = "insert into DAYRECORD(r_id,datum,dayRecord,line,millsNr) " + "values(?,?,?,?,?)";
        ms.prepareStatement(q);

        ms.getPreparedStatement().setString(1, "17"); // setting the r_id
        ms.getPreparedStatement().setString(2, "2012-12-31");
        //Option 1
        FileInputStream stream = new FileInputStream(new File("pathToFile"));
        ms.getPreparedStatement().setBinaryStream(3, (InputStream) stream);
        //Option 2
        byte[] bytes = MyFile.filetoByteArray("pathToFile"); // this could also be bytes[] from a db!
        ms.getPreparedStatement().setBytes(3, bytes); //proved!
        //
        ms.getPreparedStatement().setString(4, "20");
        ms.getPreparedStatement().setInt(5, 1);

    }

    /**
     * Executing en ordinary statement
     * @throws SQLException 
     */
    public void ordinary_statement_example() throws SQLException {
        ms.connetctMsSql("host", "port", "db_name", "user", "pass");

        String q = "select * from MainTable";

        ResultSet rs = ms.execute(q);

        while (rs.next()) {
            String a = rs.getString("columnName");
            int b = rs.getInt("columnName");
            byte[] bytes = rs.getBytes("columnName");
        }
    }

    public void queryExamples() {
        String q = "select Band.namn,Band.stil, PlaysOn.tidpunkt,Scen.namn FROM PlaysOn INNER JOIN"
                + " Band ON Band.bandID = PlaysOn.bandID INNER JOIN Scen ON Scen.scenID = PlaysOn.scenID"
                + " WHERE PlaysOn.datum ='" + "xxxxxxx" + "' ORDER BY tidpunkt";


        String q1 = "Delete From Artist where Artist.artID =" + "xxxxxxx" + "";

        String[] array = new String[10];

        String q2 = "insert into Band (namn,land,stil,beskrivning) values" + "('" + array[0] + "','" + array[1] + "','"
                + array[2] + "','" + array[3] + "')";

        String q3 = "select count(Artist.namn) FROM Artist inner join Band on Band.bandID = Artist.bandId where"
                + " Band.bandID =" + "xxxxxxxx" + "";

        String q4 = "select distinct SecurityP.persID, skills from SecurityP inner join Personal "
                + "on Personal.persID = SecurityP.persID where Personal.namn ='" + "xxxx" + "'";
    }
}
