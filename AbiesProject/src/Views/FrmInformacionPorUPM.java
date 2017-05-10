package Views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Database.ExternalConnection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmInformacionPorUPM extends JInternalFrame {
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	int upmInt =0;
	private JLabel lblEstadoResp;
	private JLabel lblMunicipioResp;
	private JLabel lblAltitudResp;
	private JLabel lblPendienteRepresentativaResp;
	private JLabel lblExposicionResp;
	private JLabel lblFisiografiaResp;
	private JTextField txtRegistrosTotales;
	private JTextField txtIndividuosTotales;
	private JTable tblVegetacionPorSitio;
	private JTable tblEspeciesPorSitio;
	private JLabel lblUPM;
	private JList<String> lsUPM;
	Vector<String> upmTotal = new Vector<String>();
	JDesktopPane desktopPanelCentral;
	public String[] vegetacionPorSitioColumnName = { "Sitio", "Tipo vegetación", "Fase sucesional", "Conteo registros",
			"Conteo individuos" };
	public String[] especiesPorSitioColumnName = { "Sitio", "Entidad taxonomica", "Forma de vida" };
	public String[] informacionSitioColumnName = { "Sitio", "Sitio accesible", "Tipo inaccesibilidad",
			"Descripcion inaccesibilidad", "Tipo de vegetación", "Fase sucecional" };

	public DefaultTableModel vegetacionPorSitioModel = new DefaultTableModel(null, vegetacionPorSitioColumnName);
	public DefaultTableModel especiesPorSitioModel = new DefaultTableModel(null, especiesPorSitioColumnName);
	public DefaultTableModel informacionSitioModel = new DefaultTableModel(null, informacionSitioColumnName);
	private JTable tblInformacionSItio;
	private JLabel lblAccesible;
	private JLabel lbl_25;
	private JLabel lblTipo;
	

	public FrmInformacionPorUPM(String ruta, JDesktopPane desktopPanelCentral) {
		setFrameIcon(null);
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		this.desktopPanelCentral = desktopPanelCentral;
		this.ruta = ruta;
		setUpmsTotales(ruta);
		setBounds(100, 100, 1060, 895);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.WEST);

		lsUPM = new JList(upmTotal);
		lsUPM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2 && !arg0.isConsumed()) {
					arg0.consume();
					String UPMElegido = (String) lsUPM.getSelectedValue();
					upmInt = Integer.parseInt(UPMElegido);

					lblUPM.setText(UPMElegido);
					
					getRegistrosTotales(ruta, upmInt);
					getIndividuosTotales(ruta, upmInt);
					getInformacionUPM(ruta, upmInt);
					getvegetacionPorSitio(ruta, upmInt);
					getEspeciesPorSitio(ruta, upmInt);
					getInformacionSitio(ruta, upmInt);
				}

			}
		});
		lsUPM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lsUPM);

		JLabel lblUpms = new JLabel("UPMS");
		lblUpms.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpms.setFont(new Font("Dialog", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblUpms);

		JPanel panel_1 = new JPanel();
		scrollPane.setRowHeaderView(panel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setAutoscrolls(true);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setAutoscrolls(true);
		tabbedPane.addTab("Arbolado", null, layeredPane_1, null);

		JLabel lblNewLabel_2 = new JLabel("No. Registros totales:");

		txtRegistrosTotales = new JTextField();
		txtRegistrosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrosTotales.setColumns(10);

		JLabel lblNoIndividuosTotales = new JLabel("No. Individuos totales:");

		txtIndividuosTotales = new JTextField();
		txtIndividuosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtIndividuosTotales.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBackground(Color.WHITE);

		tblVegetacionPorSitio = new JTable();
		tblVegetacionPorSitio.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblVegetacionPorSitio.setBackground(new Color(51, 51, 51));
		tblVegetacionPorSitio.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblVegetacionPorSitio.setRowHeight(21);
		tblVegetacionPorSitio.setRowMargin(3);
		tblVegetacionPorSitio.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tblVegetacionPorSitio.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tblVegetacionPorSitio.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane_1.setViewportView(tblVegetacionPorSitio);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(Color.WHITE);
		scrollPane_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		tblEspeciesPorSitio = new JTable();
		tblEspeciesPorSitio.setBackground(new Color(51, 51, 51));
		tblEspeciesPorSitio.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblEspeciesPorSitio.setRowHeight(21);
		tblEspeciesPorSitio.setRowMargin(3);
		tblEspeciesPorSitio.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tblEspeciesPorSitio.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tblEspeciesPorSitio.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane_2.setViewportView(tblEspeciesPorSitio);

		JLabel lblTipoDeVetacion = new JLabel("Tipo de vetacion por sitio");
		lblTipoDeVetacion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTipoDeVetacion.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblEspeciesPorSitio = new JLabel("Especies por sitio");
		lblEspeciesPorSitio.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEspeciesPorSitio.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_3.setBackground(Color.WHITE);

		tblInformacionSItio = new JTable();
		scrollPane_3.setViewportView(tblInformacionSItio);

		JLabel lblInformacinDeSitios = new JLabel("Informaci\u00F3n de sitios");
		lblInformacinDeSitios.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDeSitios.setFont(new Font("Dialog", Font.BOLD, 16));

		JButton btnNewButton = new JButton("Gr\u00E1ficas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmGraficasArbolado graficasArb = new FrmGraficasArbolado(ruta);
				graficasArb.setUpmid(upmInt);
				if (graficasArb.isVisible() == false) {
					graficasArb.setVisible(true);
					desktopPanelCentral.add(graficasArb);
					graficasArb.generateBarChartDiametrosNormales(ruta, upmInt);
					graficasArb.generateBarChartAlturasTotales(ruta, upmInt);
				}

				if (graficasArb.isBackgroundSet()) {
					graficasArb.moveToFront();
				}
			}
		});
		GroupLayout gl_layeredPane_1 = new GroupLayout(layeredPane_1);
		gl_layeredPane_1.setHorizontalGroup(
				gl_layeredPane_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_layeredPane_1
						.createSequentialGroup().addGap(27).addGroup(gl_layeredPane_1
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layeredPane_1
										.createSequentialGroup()
										.addComponent(lblInformacinDeSitios, GroupLayout.DEFAULT_SIZE, 459,
												Short.MAX_VALUE)
										.addGap(266))
								.addGroup(gl_layeredPane_1.createSequentialGroup()
										.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
										.addGap(59)
										.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 132,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE, 132,
														GroupLayout.PREFERRED_SIZE))
										.addGap(6)
										.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
												.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE))
										.addGap(2))
								.addGroup(
										gl_layeredPane_1.createSequentialGroup()
												.addComponent(lblTipoDeVetacion, GroupLayout.DEFAULT_SIZE, 723,
														Short.MAX_VALUE)
												.addGap(2))
								.addGroup(
										gl_layeredPane_1.createSequentialGroup()
												.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 723,
														Short.MAX_VALUE)
												.addGap(2))
								.addComponent(lblEspeciesPorSitio, GroupLayout.PREFERRED_SIZE, 725,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
						.addGap(30))
						.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(384)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addGap(316)));
		gl_layeredPane_1.setVerticalGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(30)
						.addComponent(lblInformacinDeSitios, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(29)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE,
												20, GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(29)
										.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(33)
						.addComponent(lblTipoDeVetacion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(lblEspeciesPorSitio, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(6).addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE).addGap(42)
						.addComponent(btnNewButton).addGap(60)));
		layeredPane_1.setLayout(gl_layeredPane_1);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);

		lblEstadoResp = new JLabel("...");
		lblEstadoResp.setForeground(Color.ORANGE);
		lblEstadoResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEstadoResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JLabel lblNewLabel = new JLabel("Municipio:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);

		lblMunicipioResp = new JLabel("...");
		lblMunicipioResp.setForeground(Color.ORANGE);
		lblMunicipioResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMunicipioResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JLabel lblAltitud = new JLabel("Altitud:");
		lblAltitud.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAltitud.setHorizontalAlignment(SwingConstants.LEFT);

		lblAltitudResp = new JLabel("...");
		lblAltitudResp.setForeground(Color.ORANGE);
		lblAltitudResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAltitudResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JLabel lblPendRepresentativa = new JLabel("Pend. Representativa:");
		lblPendRepresentativa.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPendRepresentativa.setHorizontalAlignment(SwingConstants.LEFT);

		lblPendienteRepresentativaResp = new JLabel("...");
		lblPendienteRepresentativaResp.setForeground(Color.ORANGE);
		lblPendienteRepresentativaResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPendienteRepresentativaResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JLabel lblExposicin = new JLabel("Exposici\u00F3n:");
		lblExposicin.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblExposicin.setHorizontalAlignment(SwingConstants.LEFT);

		lblExposicionResp = new JLabel("...");
		lblExposicionResp.setForeground(Color.ORANGE);
		lblExposicionResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblExposicionResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JLabel lblNewLabel_1 = new JLabel("Fisiograf\u00EDa");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);

		lblFisiografiaResp = new JLabel("...");
		lblFisiografiaResp.setForeground(Color.ORANGE);
		lblFisiografiaResp.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFisiografiaResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblUpm = new JLabel("UPM:");
		lblUpm.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel.add(lblUpm);

		lblUPM = new JLabel("...");
		lblUPM.setForeground(Color.ORANGE);
		lblUPM.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		panel.add(lblUPM);

		JSeparator separator_5 = new JSeparator();
		panel.add(separator_5);
		panel.add(lblEstado);
		panel.add(lblEstadoResp);

		JSeparator separator = new JSeparator();
		panel.add(separator);
		panel.add(lblNewLabel);
		panel.add(lblMunicipioResp);

		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1);
		panel.add(lblAltitud);
		panel.add(lblAltitudResp);

		JSeparator separator_2 = new JSeparator();
		panel.add(separator_2);
		panel.add(lblPendRepresentativa);
		panel.add(lblPendienteRepresentativaResp);

		JSeparator separator_3 = new JSeparator();
		panel.add(separator_3);
		panel.add(lblExposicin);
		panel.add(lblExposicionResp);

		JSeparator separator_4 = new JSeparator();
		panel.add(separator_4);
		panel.add(lblNewLabel_1);
		panel.add(lblFisiografiaResp);
		
		JLabel label_18 = new JLabel("Accesible");
		label_18.setHorizontalAlignment(SwingConstants.LEFT);
		label_18.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel.add(label_18);
		
		lblAccesible = new JLabel("...");
		lblAccesible.setForeground(Color.ORANGE);
		lblAccesible.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblAccesible.setAlignmentX(1.0f);
		panel.add(lblAccesible);
		
		lbl_25 = new JLabel("Tipo:");
		lbl_25.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_25.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel.add(lbl_25);
		
		lblTipo = new JLabel("...");
		lblTipo.setForeground(Color.ORANGE);
		lblTipo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblTipo.setAlignmentX(1.0f);
		panel.add(lblTipo);
		
		lsUPM.setListData(upmTotal);

	}

	public void getRegistrosTotales(String ruta, int upmid) {
		String query = "SELECT COUNT(ArboladoID) AS Registros_totales FROM  TAXONOMIA_Arbolado WHERE UPMID=" + upmid;

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtRegistrosTotales.setText(Integer.toString(rsExterno.getInt("Registros_totales")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getIndividuosTotales(String ruta, int upmid) {
		String query = "SELECT DISTINCT  NoIndividuo FROM  TAXONOMIA_Arbolado WHERE UPMID=" + upmid
				+ " GROUP BY UPMID,SitioID ORDER BY UPMID ";
		int total = 0, totalrs;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				totalrs = rsExterno.getInt("NoIndividuo");
				total = total + totalrs;
			}
			txtIndividuosTotales.setText(Integer.toString(total));
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getInformacionUPM(String ruta, int upmid) {
		String query = "SELECT  upmMalla.Estado, upmMalla.Municipio, upm.Altitud, upm.PendienteRepresentativa, CASE upm.Accesible WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END Accesible, tipoUPM.TipoUPM, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia FROM UPM_UPM upm JOIN UPM_MallaPuntos upmMalla ON upm.UPMID=upmMalla.UPMID LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID where upm.UPMID="
				+ upmid;

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				lblEstadoResp.setText(rsExterno.getString("Estado"));
				lblMunicipioResp.setText(rsExterno.getString("Municipio"));
				lblAltitudResp.setText(Integer.toString(rsExterno.getInt("Altitud")));
				lblPendienteRepresentativaResp.setText(Integer.toString(rsExterno.getInt("PendienteRepresentativa")));
				lblExposicionResp.setText(rsExterno.getString("Exposicion"));
				lblFisiografiaResp.setText(rsExterno.getString("Fisiografia"));
				lblAccesible.setText(rsExterno.getString("Accesible"));
				lblTipo.setText(rsExterno.getString("TipoUPM"));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpmsTotales(String ruta) {
		String query = "SELECT UPMID FROM UPM_UPM WHERE Accesible=1 order by UPMID";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				upmTotal.add(Integer.toString(rsExterno.getInt("UPMID")));

			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getvegetacionPorSitio(String ruta, int upmid) {
		String query = "SELECT DISTINCT sitio.SitioID, sitio.Sitio, claveSerieV.TipoVegetacion, faseSucecional.Clave  AS FaseSucecional, arbolado.Consecutivo as No_registros, arbolado.NoIndividuo AS Individuo FROM TAXONOMIA_Arbolado arbolado LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID and sitio.UPMID=arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional WHERE arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID ORDER BY arbolado.UPMID  ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (vegetacionPorSitioModel.getRowCount() > 0) {
				for (int i = vegetacionPorSitioModel.getRowCount() - 1; i > -1; i--) {
					vegetacionPorSitioModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {

				vegetacionPorSitioModel.addRow(new Object[] { rsExterno.getString("Sitio"),
						rsExterno.getString("TipoVegetacion"), rsExterno.getString("FaseSucecional"),
						rsExterno.getString("No_registros"), rsExterno.getString("Individuo") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblVegetacionPorSitio.setModel(vegetacionPorSitioModel);
		alignCellsTables(vegetacionPorSitioModel, tblVegetacionPorSitio);

	}

	public void getEspeciesPorSitio(String ruta, int upmid) {
		String query = "SELECT  DISTINCT sitio.sitio, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, formaVida.Descripcion AS FormaVida FROM TAXONOMIA_Arbolado arbolado JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=arbolado.UPMID LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID  LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID  LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID  LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  WHERE /*arbolado.CondicionID!=2 and*/ arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID, arbolado.ArboladoID ORDER BY sitio.sitio ";
		String genero = null, especie = null, infraespecie = null, entidadTaxonomica;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (especiesPorSitioModel.getRowCount() > 0) {
				for (int i = especiesPorSitioModel.getRowCount() - 1; i > -1; i--) {
					especiesPorSitioModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {

				especiesPorSitioModel.addRow(new Object[] { rsExterno.getString("sitio"),
						rsExterno.getString("Entidad"), rsExterno.getString("FormaVida") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblEspeciesPorSitio.setModel(especiesPorSitioModel);
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(JLabel.CENTER);
		tblEspeciesPorSitio.getColumnModel().getColumn(0).setCellRenderer(cellRenderedCenter);
		tblEspeciesPorSitio.getColumnModel().getColumn(2).setCellRenderer(cellRenderedCenter);
	}

	public void alignCellsTables(DefaultTableModel model, JTable tbl) {
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < model.getColumnCount(); i++) {
			tbl.getColumnModel().getColumn(i).setCellRenderer(cellRenderedCenter);
		}

	}

	public void getInformacionSitio(String ruta, int upmid) {
		String query = "SELECT sitio.SitioID, sitio.Sitio, CASE sitio.SitioAccesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SitioAccesible, tipoInaccesibilidad.Tipo AS TipoInaccesibilidad, tipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad, claveSerieV.Clave, faseSucecional.Clave  AS FaseSucecional from SITIOS_Sitio sitio LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad WHERE sitio.UPMID="
				+ upmid + " GROUP BY sitio.UPMID, sitio.SitioID ORDER BY sitio.sitio ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (informacionSitioModel.getRowCount() > 0) {
				for (int i = informacionSitioModel.getRowCount() - 1; i > -1; i--) {
					informacionSitioModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				informacionSitioModel.addRow(new Object[] { rsExterno.getString("Sitio"),
						rsExterno.getString("SitioAccesible"), rsExterno.getString("TipoInaccesibilidad"),
						rsExterno.getString("DescripcionInaccesibilidad"), rsExterno.getString("Clave"),
						rsExterno.getString("FaseSucecional") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblInformacionSItio.setModel(informacionSitioModel);
		alignCellsTables(informacionSitioModel, tblInformacionSItio);
	}
}// Final
