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
	private JPanel panelAT;

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
		tabbedPane.addTab("Alturas totales", null, scrollPane_1, null);

		panelAT = new JPanel();
		scrollPane_1.setViewportView(panelAT);
	}

	public int setDiametrosTotales(String ruta, int upmid, String rangoMenor, String rangoMayor) {
		int result = 0;
		String query = "SELECT count(*) AS CONTEO FROM TAXONOMIA_Arbolado WHERE DiametroNormal>=" + rangoMenor
				+ " and DiametroNormal<=" + rangoMayor + " and UPMID=" + upmid;
		//System.out.println(query);
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

	public int setALturasTotales(String ruta, int upmid, String rangoMenor, String rangoMayor) {
		int result = 0;
		String query = "SELECT count(*) AS CONTEO FROM TAXONOMIA_Arbolado WHERE AlturaTotal>=" + rangoMenor
				+ " and AlturaTotal<=" + rangoMayor + " and UPMID=" + upmid;
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
			dataset.addValue(setDiametrosTotales(ruta, upmid, "0.1", "7.49"), "0.1-7.49 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "7.5", "10.99"), "7.5-10 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "11", "15.99"), "11-15 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "16", "20.99"), "16-20 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "21", "25.99"), "21-25 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "26", "30.99"), "26-30 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "31", "40.99"), "31-40 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "41", "50.99"), "41-50 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "51", "70.99"), "51-70 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "71", "90.99"), "71-90 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "91", "120.99"), "91-120 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "121", "150.99"), "121-150 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "151", "250.99"), "151-250 cm", "Individuos");
			dataset.addValue(setDiametrosTotales(ruta, upmid, "251", "500.99"), "251-n cm", "Individuos");
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

	public void generateBarChartAlturasTotales(String ruta, int upmid) {

		try {
			int Resultado = 0;

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			dataset.addValue(setALturasTotales(ruta, upmid, "0", "5.99"), "0-5 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "6", "10.99"), "6-10 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "11", "15.99"), "11-15 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "16", "20.99"), "16-20 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "21", "25.99"), "21-25 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "26", "30.99"), "26-30 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "31", "35.99"), "31-35 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "36", "40.99"), "36-40 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "41", "45.99"), "41-45 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "46", "50.99"), "46-50 m", "Individuos");
			dataset.addValue(setALturasTotales(ruta, upmid, "50", "200"), "50-n m", "Individuos");
			JFreeChart barChart = ChartFactory.createBarChart3D("Alturas Totales", "Alturas m", "No.Registros", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			ChartPanel chartPanel = new ChartPanel(barChart);

			chartPanel.setBounds(panelAT.getBounds());
			GroupLayout gl_panelDN = new GroupLayout(panelAT);
			gl_panelDN.setHorizontalGroup(gl_panelDN.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDN.createSequentialGroup().addGap(31)
							.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addGap(22)));
			gl_panelDN.setVerticalGroup(gl_panelDN.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDN.createSequentialGroup().addGap(5)
							.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE).addGap(91)));
			panelAT.setLayout(gl_panelDN);

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
