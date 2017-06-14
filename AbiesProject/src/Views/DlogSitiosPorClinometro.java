package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class DlogSitiosPorClinometro extends JDialog {
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
	public DlogSitiosPorClinometro(String ruta, boolean clinometro) {
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
				scrollPane.setViewportView(tblClinoHipso);
			}
			{
				JLabel lblNewLabel = new JLabel("New label");
				scrollPane.setColumnHeaderView(lblNewLabel);
			}
		}
		if (clinometro == true) {
			getSitiosPorClinometro(ruta);
		}
		if (clinometro == false) {
			getSitiosPorHipsometro(ruta);
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
}
