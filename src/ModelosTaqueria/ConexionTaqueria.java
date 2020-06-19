package ModelosTaqueria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionTaqueria{

    private static final String host = "localhost";
    private static final String user = "root";
    private static final String pwd = "28938924";
    private static final String db = "taqueria";
    public static Connection con;

    public static void  crearConexion(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+db+"?useSSl=false",user,pwd);
            System.out.println("Se inicio la conexión con la base de datos: Taqueria");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
