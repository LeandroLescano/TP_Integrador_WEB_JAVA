package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccesoDatosManager {

	public static String host = "jdbc:mysql://localhost:3306/";
	public static String user = "root";
	public static String pass = "root";
	public static String dbName = "gestionadministrativa";
	public static String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public int executeAccion(String query)
	{
		Connection cn = null;
		int filas = 0;
		try
		{
			cn = DriverManager.getConnection(host+dbName+timeZone,user,pass);
			Statement st= cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return filas;
	}
	
	public ResultSet executeConsulta(String query)
	{
		Connection cn = null;
		ResultSet rs = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName+timeZone,user,pass);
			Statement st= cn.createStatement();
			rs = st.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return rs;
	}
	
}
