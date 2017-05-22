package Database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import Views.FrmDistribucionEspecies;

public class ProgressDistribucion extends SwingWorker<Integer, String> {

	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	JFrame calidad;
	JProgressBar barraProgreso;
	public String ruta;
	public String[] columnNameDistribucionesMal = { "UPM", "Entidad taxonomica", "Estado", "Cantidad" };
	public DefaultTableModel DistribucionesMalModel = new DefaultTableModel(null, columnNameDistribucionesMal);
	public String[] columnNameException = { "UPM", "Entidad taxonomica", "Estado", "Cantidad" };
	public DefaultTableModel ExceptionModel = new DefaultTableModel(null, columnNameException);
	JTable table, tableException;
	JTextArea textArea;

	public ProgressDistribucion(JProgressBar barraProgreso, String ruta, JTable table, JTable tableException) {
		super();
		this.barraProgreso = barraProgreso;
		this.ruta = ruta;
		this.table = table;
		this.tableException = tableException;
	}

	@Override
	public Integer doInBackground() throws Exception {
		barraProgreso.setIndeterminate(true);
		getEntidadesTaxonomicas(ruta);
		barraProgreso.setIndeterminate(false);
		return 0;
	}

	public void getEntidadesTaxonomicas(String ruta) {
		String query = "SELECT  DISTINCT  arbolado.UPMID, upm.Estado, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, COUNT(genero.Nombre) AS Cantidad FROM TAXONOMIA_Arbolado arbolado  JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID  JOIN UPM_MallaPuntos upm on upm.UPMID= arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=arbolado.UPMID  LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional  LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID    JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID   LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID   LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  GROUP BY arbolado.UPMID , arbolado.SitioID , arbolado.ArboladoID ORDER BY Entidad";
		String entidad = null, estado = null, estadoCase = null, upm = null;
		String seDistribuye = null;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);

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
					estado = "Baja_California_Norte";
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
				case "Coahuila de Zaragoza":
					estado = "Coahuila";
					break;
				case "Colima":
					estado = "Colima";
					break;
				case "Distrito Federal":
					estado = "Ciudad_de_Mexico";
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
				case "Jalisco":
					estado = "Jalisco";
					break;
				case "México":
					estado = "Estado_de_Mexico";
					break;
				case "Michoacan de Ocampo":
					estado = "Michoacan";
					break;
				case "Morelos":
					estado = "Morelos";
					break;
				case "Nayarit":
					estado = "Nayarit";
					break;
				case "Nuevo Leon":
					estado = "Nuevo_Leon";
					break;
				case "Oaxaca":
					estado = "Oaxaca";
					break;
				case "Puebla":
					estado = "Puebla";
					break;
				case "Queretaro de Arteaga":
					estado = "Queretaro";
					break;
				case "Quintana Roo":
					estado = "Quintana_Roo";
					break;
				case "San Luis Potosi":
					estado = "San_Luis_Potosi";
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
				case "Veracruz de Ignacio de la Llave":
					estado = "Veracruz";
					break;
				case "Yucatan":
					estado = "Yucatán";
					break;
				case "Zacatecas":
					estado = "Zacatecas";
					break;
				}

				entidad = rsExterno.getString("Entidad");
				seDistribuye = isInDistribucion(entidad, estado, "Nombre_completo_apg");
				if (seDistribuye != null) {
					switch (seDistribuye) {
					case "SI":
						break;
					case "NO":
						/* Agregar al modelo */
						DistribucionesMalModel
								.addRow(new Object[] { upm, entidad, estadoCase, rsExterno.getString("Cantidad") });
						break;
					}
				} else {
					seDistribuye = isInDistribucion(entidad, estado, "Nombre_completo_Cronquist");
					if (seDistribuye != null) {
						switch (seDistribuye) {
						case "SI":
							break;
						case "NO":
							/* Agregar al modelo */
							DistribucionesMalModel
									.addRow(new Object[] { upm, entidad, estadoCase, rsExterno.getString("Cantidad") });
							break;
						}
					} else {
						seDistribuye = isInDistribucion(entidad, estado, "Nombre_Original_Corregido");
						if (seDistribuye != null) {
							switch (seDistribuye) {
							case "SI":
								break;
							case "NO":
								/* Agregar al modelo */
								DistribucionesMalModel.addRow(
										new Object[] { upm, entidad, estadoCase, rsExterno.getString("Cantidad") });
								break;
							}
						} else {
							ExceptionModel.addRow(
									new Object[] { upm, entidad, estadoCase, rsExterno.getString("Cantidad") });
						}
					}
				}
			}
			baseDatosExterna.close();
			table.setModel(DistribucionesMalModel);
			tableException.setModel(ExceptionModel);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	public String isInDistribucion(String entidad, String estado, String catalogo) {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		String seDistribuye = null;
		String query = "SELECT CASE Pintar_de_rojo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Excepcion, CASE " + estado
				+ " WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Se_ditribuye FROM Cat_Distribucion WHERE " + catalogo
				+ "= rtrim(ltrim('" + entidad + "')) Limit 1 ";

//		this.baseDatosExterna = ExternalConnection.getConnection(path + "/src/Database/Distribuciones.ab");
		 this.baseDatosExterna = ExternalConnection.getConnection(path +"/Distribuciones.ab");

		try {
			//System.out.print(query+"\t");
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				if (rsExterno.getString("Se_ditribuye").equals("SI")) {
					seDistribuye = "SI";
				}
				if (rsExterno.getString("Se_ditribuye").equals("NO")) {
					seDistribuye = "NO";
				}
			}
		} catch (Exception e) {
			System.out.println(entidad+"\t"+catalogo);
			e.printStackTrace();
		}
		// System.out.println(seDistribuye);
		return seDistribuye;
	}

	public JProgressBar getBarraProgreso() {
		return barraProgreso;
	}

	public void setBarraProgreso(JProgressBar barraProgreso) {
		this.barraProgreso = barraProgreso;
	}

}
