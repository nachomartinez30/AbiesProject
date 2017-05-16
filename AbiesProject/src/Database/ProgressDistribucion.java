package Database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import Views.FrmDistribucionEspecies;

public class ProgressDistribucion extends SwingWorker<Integer, String> {

	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	JFrame calidad;
	JProgressBar barraProgreso;
	public String ruta;
	public String[] columnNameDistribucionesMal = { "UPM", "Estado", "Entidad taxonomica", "Cantidad" };
	public DefaultTableModel DistribucionesMalModel = new DefaultTableModel(null, columnNameDistribucionesMal);
	JTable table;

	public ProgressDistribucion(JProgressBar barraProgreso, String ruta, JTable table) {
		super();
		this.barraProgreso = barraProgreso;
		this.ruta = ruta;
		this.table = table;
	}

	@Override
	public Integer doInBackground() throws Exception {
		getEntidadesTaxonomicas(ruta);
		getBarraProgreso().setIndeterminate(false);
		return 0;
	}

	public void getEntidadesTaxonomicas(String ruta) {
		String query = "SELECT  DISTINCT  arbolado.UPMID, upm.Estado, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad COUNT(genero.Nombre) AS Cantidad FROM TAXONOMIA_Arbolado arbolado  JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID  JOIN UPM_MallaPuntos upm on upm.UPMID= arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=arbolado.UPMID  LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional  LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID    JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID   LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID   LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  GROUP BY arbolado.UPMID , arbolado.SitioID , arbolado.ArboladoID,Cantidad ORDER BY sitio.sitio ";
		String entidad = null, estado = null, estadoCase = null, upm = null;
		String seDistribuye = null;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		System.out.println(query);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {

				estadoCase = rsExterno.getString("Estado");
				upm = rsExterno.getString("UPMID");
				switch (estadoCase) {
				case "Aguascalientes":
					estado = "Aguascalientes";
					break;
				case "Baja California":
					estado = "Baja_California";
					break;
				case "Baja California Sur":
					estado = "Baja_California_Sur";
					break;
				case "Campeche":
					estado = "Campeche";
					break;
				case "Chiapas":
					estado = "Chiapas";
					break;
				case "Chihuahua":
					estado = "Chihuahua";
					break;
				case "Coahuila de Zara":
					estado = "Coahuila_de_Zara";
					break;
				case "Colima":
					estado = "Colima";
					break;
				case "Distrito Federal":
					estado = "Distrito_Federal";
					break;
				case "Durango":
					estado = "Durango";
					break;
				case "Guanajuato":
					estado = "Guanajuato";
					break;
				case "Guerrero":
					estado = "Guerrero";
					break;
				case "Hidalgo":
					estado = "Hidalgo";
					break;
				case "M�xico":
					estado = "Mexico";
					break;
				case "Jalisco":
					estado = "Jalisco";
					break;
				case "Michoac�n":
					estado = "Michoac�n";
					break;
				case "Morelos":
					estado = "Morelos";
					break;
				case "Nayarit":
					estado = "Nayarit";
					break;
				case "Nuevo Le�n":
					estado = "Nuevo_Le�n";
					break;
				case "Oaxaca":
					estado = "Oaxaca";
					break;
				case "Puebla":
					estado = "Puebla";
					break;
				case "Quer�taro":
					estado = "Quer�taro";
					break;
				case "Quintana Roo":
					estado = "Quintana_Roo";
					break;
				case "San Luis Potos�":
					estado = "San_Luis_Potos�";
					break;
				case "Sinaloa":
					estado = "Sinaloa";
					break;
				case "Sonora":
					estado = "Sonora";
					break;
				case "Tabasco":
					estado = "Tabasco";
					break;
				case "Tamaulipas":
					estado = "Tamaulipas";
					break;
				case "Tlaxcala":
					estado = "Tlaxcala";
					break;
				case "Veracruz":
					estado = "Veracruz";
					break;
				case "Yucat�n":
					estado = "Yucat�n";
					break;
				case "Zacatecas":
					estado = "Zacatecas";
					break;
				}

				entidad = rsExterno.getString("Entidad");
				seDistribuye = isInDistribucion(entidad, estado);

				if (seDistribuye != null) {
					switch (seDistribuye) {
					case "SI":
						break;
					case "NO":
						/* Agregar al modelo */
						// System.out.println(upm + "\t" + estado + "\t" +
						// entidad);
						DistribucionesMalModel
								.addRow(new Object[] { upm, entidad, estadoCase, rsExterno.getString("Cantidad") });
						break;
					default:
						break;
					}
				}
			}
			baseDatosExterna.close();
			table.setModel(DistribucionesMalModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String isInDistribucion(String entidad, String estado) {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		String seSistribuye = null;
		String catalogo = "Nombre_completo_apg";
		String query = "SELECT CASE Pintar_de_rojo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Excepcion, CASE " + estado
				+ " WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Se_ditribuye FROM Cat_Distribucion WHERE " + catalogo
				+ "= rtrim(ltrim('" + entidad + "')) Limit 1 ";
		// System.out.println(query);
		// System.out.println(currentPath.toAbsolutePath().toString());
		this.baseDatosExterna = ExternalConnection.getConnection(path + "/src/Database/Distribuciones.ab");

		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				/* rsExterno.getString("Excepcion"); */
				if (rsExterno.getString("Se_ditribuye").equals("SI")) {
					seSistribuye = "SI";
				}
				if (rsExterno.getString("Se_ditribuye").equals("NO")) {
					seSistribuye = "NO";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seSistribuye;
	}

	public JProgressBar getBarraProgreso() {
		return barraProgreso;
	}

	public void setBarraProgreso(JProgressBar barraProgreso) {
		this.barraProgreso = barraProgreso;
	}

}
