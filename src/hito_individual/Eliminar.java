package hito_individual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Eliminar{

	public static void main(String[] args) {
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver para MySQL");
			return;
		}
		System.out.println("Se ha cargado el Driver de MySQL");
		
		String cadenaConexion = "jdbc:mysql://localhost:3306/mercado";
		String user = "root";
		String pass = "";
		Connection con;
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
		} catch (SQLException e) {
			System.out.println("No se ha podido establecer la conexión con la BD");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Se ha establecido la conexión con la Base de datos");
		
		try {
			Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM producto WHERE idProducto = '00Q'");
			boolean existe = rs.next();
			if (existe) {
				rs.deleteRow();
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar al producto");
			System.out.println(e.getMessage());
		}		
		
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido cerrar la conexión con la BD");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Se ha cerrado la base de datos");

	}
}
