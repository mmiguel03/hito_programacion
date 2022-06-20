package hito_individual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A�adir {

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
			System.out.println("No se ha podido establecer la conexi�n con la BD");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Se ha establecido la conexi�n con la Base de datos");
		

		try {
			Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM producto");

			rs.moveToInsertRow(); 
			rs.updateString("idProducto", "00Q"); 
			rs.updateString("NOMBRE", "Leche"); 
			rs.updateString("fechaEnvasado", "10/05/2021"); 
			rs.updateString("unidades", "2"); 
			rs.updateInt("precio", 5); 
			rs.updateBoolean("disponible", true); 
			rs.insertRow(); 

		} catch (SQLException e) {
			System.out.println("Error al a�adir el nuevo producto");
			System.out.println(e.getMessage());
		}		
		

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("No se ha podido cerrar la conexi�n con la BD");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Se ha cerrado la base de datos");

	}
}
