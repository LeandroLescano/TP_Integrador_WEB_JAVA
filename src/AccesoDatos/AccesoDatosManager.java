package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoDatosManager {

	public static String host = "jdbc:mysql://localhost:3306/";
	public static String user = "root";
	public static String pass = "root";
	public static String dbName = "gestionadministrativa";
	public static String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static Connection cn = null;
	private static ResultSet rs = null;
	private static Statement st = null;
	
	public void abrirConexion()
	{
		try {
			cn = DriverManager.getConnection(host+dbName+timeZone,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(cn != null) cn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int executeAccion(String query)
	{
		int filas = 0;
		try
		{
			st= cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		return filas;
	}
	
	public int executeAccionReturn(String query)
	{
		try
		{
			st= cn.createStatement();
			return st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	
	}
	
	public ResultSet executeConsulta(String query)
	{
		try
		{
//			cn = DriverManager.getConnection(host+dbName+timeZone,user,pass);
			st = cn.createStatement();
			rs = st.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return rs;
	}
	
	
}
