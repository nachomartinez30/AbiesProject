package Views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Database.ExternalConnection;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FrmGraficasArbolado extends JInternalFrame {
	public String ruta;
	int upmid;

	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	private JPanel panelDN;

	public FrmGraficasArbolado(String ruta) {
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		setFrameIcon(null);
		this.ruta = ruta;
		setBounds(100, 100, 868, 719);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Diametros Normales", null, scrollPane, null);

		panelDN = new JPanel();
		scrollPane.setViewportView(panelDN);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane_1, null);
	}

	public int setRegistrosTotales(String ruta, int upmid, String rangoMenor, String rangoMayor) {
		int result = 0;
		String query = "SELECT count(ArboladoID) AS CONTEO FROM TAXONOMIA_Arbolado WHERE DiametroNormal>=" + rangoMenor
				+ " and DiametroNormal<=" + rangoMayor + " and UPMID=" + upmid;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				result = rsExterno.getInt("CONTEO");

			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void generateBarChartDiametrosNormales(String ruta, int upmid) {

		try {
			int Resultado = 0;

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			dataset.addValue(setRegistrosTotales(ruta, upmid, "7.5", "10"), "7.5-10", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "11", "15"), "11-15", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "16", "20"), "16-20", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "21", "25"), "21-25", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "26", "30"), "26-30", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "31", "40"), "31-40", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "41", "50"), "41-50", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "51", "70"), "51-70", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "71", "90"), "71-90", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "91", "120"), "91-120", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "121", "150"), "121-150", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "151", "250"), "151-250", "Individuos");
			dataset.addValue(setRegistrosTotales(ruta, upmid, "251", "500"), "251-n", "Individuos");
			JFreeChart barChart = ChartFactory.createBarChart3D("Diametros normales", "Rangos cm", "No.Registros",
					dataset, PlotOrientation.VERTICAL, true, true, false);

			ChartPanel chartPanel = new ChartPanel(barChart);

			chartPanel.setBounds(panelDN.getBounds());
			GroupLayout gl_panelDN = new GroupLayout(panelDN);
			gl_panelDN.setHorizontalGroup(gl_panelDN.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDN.createSequentialGroup().addGap(31)
							.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addGap(22)));
			gl_panelDN.setVerticalGroup(gl_panelDN.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDN.createSequentialGroup().addGap(5)
							.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE).addGap(91)));
			panelDN.setLayout(gl_panelDN);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int getUpmid() {
		return upmid;
	}

	public void setUpmid(int upmid) {
		this.upmid = upmid;
	}

}
