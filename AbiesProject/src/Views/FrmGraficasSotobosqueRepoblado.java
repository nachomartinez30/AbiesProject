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

public class FrmGraficasSotobosqueRepoblado extends JInternalFrame {
	public String ruta;
	int upmid;

	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	private JPanel panelRepoblado;
	private JPanel panelSotobosque;

	public FrmGraficasSotobosqueRepoblado(String ruta) {
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		setFrameIcon(null);
		this.ruta = ruta;
		setBounds(100, 100, 868, 719);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Repoblado", null, scrollPane, null);

		panelRepoblado = new JPanel();
		scrollPane.setViewportView(panelRepoblado);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Sotobosque", null, scrollPane_1, null);

		panelSotobosque = new JPanel();
		scrollPane_1.setViewportView(panelSotobosque);
	}

	public void generarBarChartFrecuenciasRepoblado(String ruta, int upmid) {
		int frecuencia025150 = 0, frecuencia151275 = 0, frecuencia275 = 0;
		String query = "SELECT  SUM(Frecuencia025150) AS Frecuencia025150, SUM(Frecuencia151275) AS Frecuencia151275, SUM(Frecuencia275) AS Frecuencia275  FROM TAXONOMIA_Repoblado  WHERE UPMID="
				+ upmid;
		// System.out.println(query);
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				frecuencia025150 = rsExterno.getInt("Frecuencia025150");
				frecuencia151275 = rsExterno.getInt("Frecuencia151275");
				frecuencia275 = rsExterno.getInt("Frecuencia275");
			}

			try {
				int Resultado = 0;

				final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.addValue(frecuencia025150, "Frecuencia 0.25-150", "Individuos");
				dataset.addValue(frecuencia151275, "Frecuencia 151-275", "Individuos");
				dataset.addValue(frecuencia275, "Frecuencia >275", "Individuos");
				JFreeChart barChart = ChartFactory.createBarChart3D("Repoblado", "Frecuencias", "No.Registros", dataset,
						PlotOrientation.VERTICAL, true, true, false);

				ChartPanel chartPanel = new ChartPanel(barChart);

				chartPanel.setBounds(panelRepoblado.getBounds());
				GroupLayout gl_panelRepoblado = new GroupLayout(panelRepoblado);
				gl_panelRepoblado.setHorizontalGroup(gl_panelRepoblado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRepoblado.createSequentialGroup().addGap(31)
								.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addGap(22)));
				gl_panelRepoblado.setVerticalGroup(gl_panelRepoblado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRepoblado.createSequentialGroup().addGap(5)
								.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE).addGap(91)));
				panelRepoblado.setLayout(gl_panelRepoblado);

			} catch (Exception e) {
				// TODO: handle exception
			}

			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarBarChartFrecuenciasSotbosque(String ruta, int upmid) {
		int frecuencia025150 = 0, frecuencia151275 = 0, frecuencia275 = 0;
		String query = "SELECT  SUM(Frecuencia025150) AS Frecuencia025150, SUM(Frecuencia151275) AS Frecuencia151275, SUM(Frecuencia275) AS Frecuencia275  FROM TAXONOMIA_Sotobosque  WHERE UPMID="
				+ upmid;
		// System.out.println(query);
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				frecuencia025150 = rsExterno.getInt("Frecuencia025150");
				frecuencia151275 = rsExterno.getInt("Frecuencia151275");
				frecuencia275 = rsExterno.getInt("Frecuencia275");
			}

			try {
				int Resultado = 0;

				final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.addValue(frecuencia025150, "Frecuencia 0.25-150", "Individuos");
				dataset.addValue(frecuencia151275, "Frecuencia 151-275", "Individuos");
				dataset.addValue(frecuencia275, "Frecuencia >275", "Individuos");
				JFreeChart barChart = ChartFactory.createBarChart3D("Repoblado", "Frecuencias", "No.Registros", dataset,
						PlotOrientation.VERTICAL, true, true, false);

				ChartPanel chartPanel = new ChartPanel(barChart);

				chartPanel.setBounds(panelSotobosque.getBounds());
				GroupLayout gl_panelSotobosque = new GroupLayout(panelSotobosque);
				gl_panelSotobosque.setHorizontalGroup(gl_panelSotobosque.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSotobosque.createSequentialGroup().addGap(31)
								.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addGap(22)));
				gl_panelSotobosque.setVerticalGroup(gl_panelSotobosque.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSotobosque.createSequentialGroup().addGap(5)
								.addComponent(chartPanel, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE).addGap(91)));
				panelSotobosque.setLayout(gl_panelSotobosque);

			} catch (Exception e) {
				// TODO: handle exception
			}

			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getUpmid() {
		return upmid;
	}

	public void setUpmid(int upmid) {
		this.upmid = upmid;
	}

}
