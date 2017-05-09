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

public class FrmInformacionPorUPM extends JInternalFrame {
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
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

	public String[] vegetacionPorSitioColumnName = { "Sitio", "Tipo vegetación", "Fase sucesional", "Conte registros",
			"conte individuos" };
	public String[] especiesPorSitioColumnName = { "Sitio", "Entidad taxonomica", "Forma de vida" };

	public DefaultTableModel vegetacionPorSitioModel = new DefaultTableModel(null, vegetacionPorSitioColumnName);
	public DefaultTableModel especiesPorSitioModel = new DefaultTableModel(null, especiesPorSitioColumnName);
	private JPanel panelDiametrosNormales;

	public FrmInformacionPorUPM(String ruta) {

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
					int upmInt = Integer.parseInt(UPMElegido);

					lblUPM.setText(UPMElegido);

					getRegistrosTotales(ruta, upmInt);
					getIndividuosTotales(ruta, upmInt);
					getInformacionUPM(ruta, upmInt);
					getvegetacionPorSitio(ruta, upmInt);
					getEspeciesPorSitio(ruta, upmInt);
					charNuevo();
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

		JLabel lblDiametrosNormales = new JLabel("Diametros normales");
		lblDiametrosNormales.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiametrosNormales.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel lblAlturasTotales = new JLabel("Alturas totales");
		lblAlturasTotales.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAlturasTotales.setHorizontalAlignment(SwingConstants.CENTER);

		panelDiametrosNormales = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_layeredPane_1 = new GroupLayout(layeredPane_1);
		gl_layeredPane_1.setHorizontalGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(151)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGap(193)
						.addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE,
								132, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(27)
						.addComponent(lblTipoDeVetacion, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE).addGap(32))
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(27)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE).addGap(32))
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(27).addComponent(lblEspeciesPorSitio,
						GroupLayout.PREFERRED_SIZE, 715, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(27)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE).addGap(30))
				.addGroup(Alignment.TRAILING, gl_layeredPane_1.createSequentialGroup().addGap(52)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDiametrosNormales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panelDiametrosNormales, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblAlturasTotales, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
						.addGap(30)));
		gl_layeredPane_1.setVerticalGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup().addGap(12)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(12)
						.addComponent(lblTipoDeVetacion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(8)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(lblEspeciesPorSitio, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDiametrosNormales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblAlturasTotales))
						.addGap(12)
						.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelDiametrosNormales, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
						.addContainerGap()));
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

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtIndividuosTotales.setText(Integer.toString(rsExterno.getInt("NoIndividuo")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getInformacionUPM(String ruta, int upmid) {
		String query = "SELECT  upmMalla.Estado, upmMalla.Municipio, upm.Altitud, upm.PendienteRepresentativa, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia FROM UPM_UPM upm JOIN UPM_MallaPuntos upmMalla ON upm.UPMID=upmMalla.UPMID LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID where upm.UPMID= "
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
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpmsTotales(String ruta) {
		String query = "SELECT UPMID FROM UPM_UPM order by UPMID";

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
		String query = "SELECT DISTINCT sitio.Sitio, claveSerieV.TipoVegetacion , faseSucecional.Clave  AS FaseSucecional, arbolado.Consecutivo as No_registros, arbolado.NoIndividuo AS Individuo FROM TAXONOMIA_Arbolado arbolado JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID JOIN UPM_UPM upm ON upm.UPMID=arbolado.UPMID JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=arbolado.UPMID LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional WHERE arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID ORDER BY arbolado.UPMID ";

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
		String query = "SELECT  DISTINCT sitio.sitio, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, formaVida.Descripcion AS FormaVida FROM TAXONOMIA_Arbolado arbolado JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID  LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID  LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID  LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  WHERE arbolado.CondicionID!=2 and  arbolado.UPMID="
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

	public void charNuevo() {
		try {
			final String fait = "FAIT";
			final String audi = "AUDI";
			final String ford = "FORD";
			final String speed = "Speed";
			final String popular = "Popular";
			final String mailage = "Mailage";
			final String userrating = "User Rating";
			final String safety = "safety";
			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			dataset.addValue(1.0, fait, speed);
			dataset.addValue(4.0, fait, popular);
			dataset.addValue(3.0, fait, userrating);
			dataset.addValue(5.0, fait, mailage);
			dataset.addValue(5.0, fait, safety);

			dataset.addValue(5.0, audi, speed);
			dataset.addValue(7.0, audi, popular);
			dataset.addValue(6.0, audi, userrating);
			dataset.addValue(10.0, audi, mailage);
			dataset.addValue(4.0, audi, safety);

			dataset.addValue(4.0, ford, speed);
			dataset.addValue(3.0, ford, popular);
			dataset.addValue(2.0, ford, userrating);
			dataset.addValue(3.0, ford, mailage);
			dataset.addValue(6.0, ford, safety);

			JFreeChart barChart = ChartFactory.createBarChart3D("Car Usage Statistics", "Category", "Score", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			ChartPanel chartPanel = new ChartPanel(barChart);
			
			chartPanel.setBounds(panelDiametrosNormales.getBounds());
			panelDiametrosNormales.add(chartPanel, BorderLayout.CENTER);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}// Final
