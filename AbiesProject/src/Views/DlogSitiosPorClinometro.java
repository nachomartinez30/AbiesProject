package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.ExternalConnection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Point;
import java.awt.Cursor;

public class DlogSitiosPorClinometro extends JFrame {
	private JTable tblClinoHipso;
	private JLabel lblSitiosPorClinometro;
	private String ruta = "";
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;

	public String[] columnNameSitioPorClinometro_Hipsometro = { "UPMID", "Sitio" };
	public DefaultTableModel ModelSitioPorClinometro = new DefaultTableModel(null,
			columnNameSitioPorClinometro_Hipsometro);

	/**
	 * Create the dialog.
	 */
	public DlogSitiosPorClinometro(String ruta, String opcion) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.ruta = ruta;
		setBounds(100, 100, 302, 362);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				lblSitiosPorClinometro = new JLabel("Sitios por ");
				lblSitiosPorClinometro.setHorizontalAlignment(SwingConstants.CENTER);
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup().addContainerGap()
							.addComponent(lblSitiosPorClinometro, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
							.addGap(5)));
			gl_buttonPane
					.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
							gl_buttonPane.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblSitiosPorClinometro).addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				tblClinoHipso = new JTable();
				tblClinoHipso.setColumnSelectionAllowed(true);
				tblClinoHipso.setCellSelectionEnabled(true);
				tblClinoHipso.setFillsViewportHeight(true);
				tblClinoHipso.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				scrollPane.setViewportView(tblClinoHipso);
			}
			{
				JLabel lblNewLabel = new JLabel("New label");
				scrollPane.setColumnHeaderView(lblNewLabel);
			}
		}
		switch (opcion) {
		case "upm_fechaInicial":
			getUpmAntiguo(ruta);
			break;
		case "upm_fechaFinal":
			getUpmReciente(ruta);
			break;
		case "upm_estado":
			getUpmPorEstado(ruta);
			break;
		case "upm_dias":
			getUpmPorDias(ruta);
			break;
		case "upm_tipo":
			getUpmTipo(ruta);
			break;
		case "upm_tag":
			getUpmTag(ruta);
			break;
		case "sitios_clinometro":
			getSitiosPorClinometro(ruta);
			break;
		case "sitios_hipsometro":
			getSitiosPorHipsometro(ruta);
			break;
		case "sitios_accesibles":
			break;
		case "sicios_inaccesibles":
			getSitioInaccesibilidad(ruta);
			break;
		case "sitios_condicionVegetacion":
			break;
		}
	}

	public void getSitiosPorClinometro(String ruta) {
		String query = "Select   UPMID, sitio FROM SITIOS_Sitio sitio where sitio.CintaClinometroBrujula=1 GROUP BY  UPMID,sitio order by UPMID,sitio ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getInt("sitio") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("Sitios por Clinometro");

	}

	public void getSitiosPorHipsometro(String ruta) {
		String query = "Select   UPMID, sitio FROM SITIOS_Sitio sitio where sitio.HipsometroBrujula GROUP BY  UPMID,sitio order by UPMID,sitio ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getInt("sitio") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("Sitios por Hipsometro");

	}

	public void getUpmPorDias(String ruta) {
		String query = "SELECT DISTINCT  upm.UPMID, julianday(upm.FechaFin)-julianday(upm.FechaInicio)+1 as DIAS FROM UPM_UPM upm  LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID  GROUP BY upm.UPMID,DIAS  order by DIAS";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getInt("DIAS") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Dias" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs por Dias");

	}

	public void getUpmPorEstado(String ruta) {
		String query = "SELECT upm.UPMID, Estado FROM UPM_UPM upm LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID GROUP BY upm.UPMID, Estado ORDER by Estado";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("Estado") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Estado" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs por Estado");

	}

	public void getUpmAntiguo(String ruta) {
		String query = "SELECT  UPMID, MIN(FechaInicio) as Min FROM UPM_UPM";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("Min") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Fecha" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs Antiguo");

	}

	public void getUpmReciente(String ruta) {
		String query = "SELECT  UPMID, MAX(FechaInicio) as Max FROM UPM_UPM";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("Max") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Fecha" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs Antiguo");

	}

	public void getUpmTipo(String ruta) {
		String query = "SELECT  upm.UPMID, tipoUPM.TipoUPM AS TipoUPM  FROM UPM_UPM upm  LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID  GROUP BY upm.UPMID, tipoUPM.TipoUPM order BY tipoUPM.TipoUPM";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("TipoUPM") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Tipo" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs por Tipo");

	}

	public void getUpmTag(String ruta) {
		String query = "SELECT  transponder.UPMID, tipoColocacion.Descripcion as Colocacion FROM SITIOS_Transponder transponder  LEFT JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=transponder.TipoColocacionID  GROUP BY transponder.UPMID, tipoColocacion.Descripcion  order by tipoColocacion.Descripcion";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("Colocacion") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID", "Colocacion" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs por TAG");

	}
	
	public void getSitioInaccesibilidad(String ruta) {
		String query = "Select  sitio.UPMID,sitio.Sitio, tipoInaccesibilidad.Tipo as Tipo_inaccesibilidad FROM SITIOS_Sitio sitio  LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad  WHERE SitioAccesible=0  GROUP BY sitio.UPMID,sitio.Sitio ,tipoInaccesibilidad.Tipo ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (ModelSitioPorClinometro.getRowCount() > 0) {
				for (int i = ModelSitioPorClinometro.getRowCount() - 1; i > -1; i--) {
					ModelSitioPorClinometro.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ModelSitioPorClinometro
						.addRow(new Object[] { rsExterno.getString("UPMID"), rsExterno.getString("Sitio"), rsExterno.getString("Tipo_inaccesibilidad") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] columnNameUpmPordias = { "UPMID","Sitio" ,"Causa" };
		ModelSitioPorClinometro.setColumnIdentifiers(columnNameUpmPordias);
		tblClinoHipso.setModel(ModelSitioPorClinometro);
		lblSitiosPorClinometro.setText("UPMs por Inaccesibilidad");

	}

}
