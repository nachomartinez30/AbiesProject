package Views;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableCellRenderer;

import Database.ExternalConnection;

import javax.swing.JFrame;

public class FrmDistribucionEspecies extends JInternalFrame {
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tblDistribuciones;
	private JTable tblExcepciones;
	private JLabel label;
	public FrmInformacionPorUPM infoUpm;
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;

	public FrmDistribucionEspecies(String ruta) {
		setClosable(true);
		setFrameIcon(null);
		setBounds(100, 100, 874, 672);
		this.ruta = ruta;

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();

		tblDistribuciones = new JTable();
		scrollPane.setViewportView(tblDistribuciones);

		scrollPane_1 = new JScrollPane();

		tblExcepciones = new JTable();
		scrollPane_1.setViewportView(tblExcepciones);

		JLabel lblNewLabel = new JLabel("Excepciones");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		label = new JLabel("Fuera de Distribuci\u00F3n");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE))
						.addGap(26))
				.addGroup(gl_panel.createSequentialGroup().addGap(34)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE).addGap(26))
				.addGroup(gl_panel.createSequentialGroup().addGap(36)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE).addGap(26)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(12)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addGap(33).addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
						.addGap(12).addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE).addGap(5)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE).addGap(29)));
		panel.setLayout(gl_panel);
//		getEntidadesTaxonomicas(ruta);

	}

//	public void getEntidadesTaxonomicas(String ruta) {
//		String query = "SELECT  DISTINCT  arbolado.UPMID, upm.Estado, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad FROM TAXONOMIA_Arbolado arbolado  JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID  JOIN UPM_MallaPuntos upm on upm.UPMID= arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=arbolado.UPMID  LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional  LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID   LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID   LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID   LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID   LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  GROUP BY arbolado.UPMID , arbolado.SitioID , arbolado.ArboladoID ORDER BY sitio.sitio ";
//		String entidad = null, estado = null, estadoCase = null, upm = null;
//		String seDistribuye = null;
//		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
//		try {
//			sqlExterno = baseDatosExterna.createStatement();
//			ResultSet rsExterno = sqlExterno.executeQuery(query);
//
//			while (rsExterno.next()) {
//				estadoCase = rsExterno.getString("Estado");
//				upm = rsExterno.getString("UPMID");
//				switch (estadoCase) {
//				case "Aguascalientes":
//					estado = "Aguascalientes";
//					break;
//				case "Baja California":
//					estado = "Baja_California";
//					break;
//				case "Baja California Sur":
//					estado = "Baja_California_Sur";
//					break;
//				case "Campeche":
//					estado = "Campeche";
//					break;
//				case "Chiapas":
//					estado = "Chiapas";
//					break;
//				case "Chihuahua":
//					estado = "Chihuahua";
//					break;
//				case "Coahuila de Zara":
//					estado = "Coahuila_de_Zara";
//					break;
//				case "Colima":
//					estado = "Colima";
//					break;
//				case "Distrito Federal":
//					estado = "Distrito_Federal";
//					break;
//				case "Durango":
//					estado = "Durango";
//					break;
//				case "Guanajuato":
//					estado = "Guanajuato";
//					break;
//				case "Guerrero":
//					estado = "Guerrero";
//					break;
//				case "Hidalgo":
//					estado = "Hidalgo";
//					break;
//				case "México":
//					estado = "Mexico";
//					break;
//				case "Jalisco":
//					estado = "Jalisco";
//					break;
//				case "Michoacán":
//					estado = "Michoacán";
//					break;
//				case "Morelos":
//					estado = "Morelos";
//					break;
//				case "Nayarit":
//					estado = "Nayarit";
//					break;
//				case "Nuevo León":
//					estado = "Nuevo_León";
//					break;
//				case "Oaxaca":
//					estado = "Oaxaca";
//					break;
//				case "Puebla":
//					estado = "Puebla";
//					break;
//				case "Querétaro":
//					estado = "Querétaro";
//					break;
//				case "Quintana Roo":
//					estado = "Quintana_Roo";
//					break;
//				case "San Luis Potosí":
//					estado = "San_Luis_Potosí";
//					break;
//				case "Sinaloa":
//					estado = "Sinaloa";
//					break;
//				case "Sonora":
//					estado = "Sonora";
//					break;
//				case "Tabasco":
//					estado = "Tabasco";
//					break;
//				case "Tamaulipas":
//					estado = "Tamaulipas";
//					break;
//				case "Tlaxcala":
//					estado = "Tlaxcala";
//					break;
//				case "Veracruz":
//					estado = "Veracruz";
//					break;
//				case "Yucatán":
//					estado = "Yucatán";
//					break;
//				case "Zacatecas":
//					estado = "Zacatecas";
//					break;
//				}
//
//				entidad = rsExterno.getString("Entidad");
//				seDistribuye = isInDistribucion(entidad, estado);
//				System.out.println(seDistribuye);
//				if (seDistribuye != null) {
//					switch (seDistribuye) {
//					case "SI":
//						break;
//					case "NO":
//						/* Agregar al modelo */
//						System.out.println(upm + "\t" + estadoCase + "\t" + entidad);
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			baseDatosExterna.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public String isInDistribucion(String entidad, String estado) {
//		String seSistribuye = null;
//		String catalogo = "Nombre_completo_apg";
//		String query = "SELECT CASE Pintar_de_rojo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Excepcion, CASE " + estado
//				+ " WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Se_ditribuye FROM Cat_Distribucion WHERE " + catalogo
//				+ "='" + entidad + "' Limit 1 ";
//		this.baseDatosExterna = ExternalConnection
//				.getConnection("C:/Users/Ignacio/Desktop/AbiesProject/AbiesProject/src/Database/Distribuciones.ab");
//
//		try {
//			sqlExterno = baseDatosExterna.createStatement();
//			ResultSet rsExterno = sqlExterno.executeQuery(query);
//			while (rsExterno.next()) {
//				/* rsExterno.getString("Excepcion"); */
//				if (rsExterno.getString("Se_ditribuye").equals("SI")) {
//					seSistribuye = "SI";
//				}
//				if (rsExterno.getString("Se_ditribuye").equals("NO")) {
//					seSistribuye = "NO";
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return seSistribuye;
//	}
}
