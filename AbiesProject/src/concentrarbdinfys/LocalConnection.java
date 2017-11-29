package concentrarbdinfys;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Views.Index;

public class LocalConnection {

	private static String driver = "org.sqlite.JDBC";
	private static Connection connect;
	public static String ruta ="";




	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection("jdbc:sqlite:" + ruta);
						connect.setAutoCommit(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		return connect;
	}

	public static void closeConnection() {
		try {
			connect.close();
			JOptionPane.showConfirmDialog(null, "Desconectado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static String getURL() {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();

		return "jdbc:sqlite:" + path + "/MuestreoINF_2016.cons"; // Para distribuir

		//return "jdbc:sqlite:" + path + "/src/db/MuestreoINF_2016.cons"; //En producción.
	}
	
	public static String getRuta() {
		return ruta;
	}

	public static void setRuta(String ruta) {
		LocalConnection.ruta = ruta;
	}

}
