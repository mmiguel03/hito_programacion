package hito_individual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Principal {

	public static void main(String[] args) {

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
			System.out.println("Error en la conexión con la BD");
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Se ha establecido la conexión con la Base de datos");

		
		try {
			Statement sentencia = con.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM producto");
			while (rs.next()) {
				System.out.print(rs.getString("idproducto"));
				System.out.print(" - ");
				System.out.print(rs.getString("nombre"));
				System.out.print(" - ");
				System.out.print(rs.getString("fechaEnvasado"));
				System.out.print(" - ");
				System.out.print(rs.getString("unidades"));
				System.out.print(" - ");
				System.out.print(rs.getInt("precio"));
				System.out.print(" - "); 
				System.out.print(rs.getBoolean("disponible"));
				System.out.print(" - "); 
				System.out.println(); 
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar el listado de productos");
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