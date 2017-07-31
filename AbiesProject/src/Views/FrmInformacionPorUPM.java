package Views;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
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

import Database.ExternalConnection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;

public class FrmInformacionPorUPM extends JInternalFrame {
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	int upmInt = 0;
	private JLabel lblEstadoResp;
	private JLabel lblMunicipioResp;
	private JLabel lblAltitudResp;
	private JLabel lblPendienteRepresentativaResp;
	private JLabel lblExposicionResp;
	private JLabel lblFisiografiaResp;
	private JTextField txtRegistrosTotales;
	private JTextField txtIndividuosTotales;
	private JTable tblVegetacionPorSitio;
	private JTable tblEspeciesPorSitioArbolado;
	private JLabel lblUPM;
	private JList<String> lsUPM;
	Vector<String> upmTotal = new Vector<String>();
	JDesktopPane desktopPanelCentral;
	public String[] vegetacionPorSitioColumnName = { "Sitio", "Tipo vegetaci�n", "Fase sucesional",
			"Conteo registros", "Conteo individuos" };
	public String[] especiesPorSitioColumnName = { "Sitio", "Entidad taxonomica", "Forma de vida" };
	public String[] especiesPorSitioSotobosqueColumnName = { "Sitio", "Entidad taxonomica", "Vigor" };
	public String[] especiesPorSitioRepobladoColumnName = { "Sitio", "Entidad taxonomica", "Vigor" };
	public String[] informacionSitioColumnName = { "Sitio", "Sitio accesible", "Tipo inaccesibilidad",
			"Descripcion inaccesibilidad", "Tipo de vegetaci�n", "Fase sucecional" };

	public DefaultTableModel vegetacionPorSitioModel = new DefaultTableModel(null, vegetacionPorSitioColumnName);
	public DefaultTableModel especiesPorSitioArboladoModel = new DefaultTableModel(null, especiesPorSitioColumnName);
	public DefaultTableModel especiesPorSitioSotobosqueModel = new DefaultTableModel(null,
			especiesPorSitioSotobosqueColumnName);
	public DefaultTableModel especiesPorSitioRepobladoModel = new DefaultTableModel(null,
			especiesPorSitioRepobladoColumnName);
	public DefaultTableModel especiesPorSitioModel = new DefaultTableModel(null, especiesPorSitioColumnName);
	public DefaultTableModel informacionSitioModel = new DefaultTableModel(null, informacionSitioColumnName);

	private JTable tblInformacionSItio;
	private JLabel lblAccesible;
	private JLabel lbl_25;
	private JLabel lblTipo;
	private JScrollPane scrollPaneInforSitio;
	private JLabel lblInformacinDeSitios;
	private JScrollPane scrollPaneTipoVegetacionPorSitio;
	private JScrollPane scrollPaneEspeciesPorSitio;
	private JButton btnGraficas;
	private JTable tblEspeciesPorSitioSotobosque;
	private JTable tblEspeciesPorSitioRepoblado;
	private JButton btnGraficasSotobosqueRepoblado;
	
	String Estado, Municipio, Y, X, Accesible, TipoUPM, Exposicion, Fisiografia,Region, UPMID, Altitud,  PendienteRepresentativa,  A,  B,  C,  D,  E,  F,  G,  H;

	

	public FrmInformacionPorUPM(String ruta, JDesktopPane desktopPanelCentral) {
		setFrameIcon(null);
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		this.desktopPanelCentral = desktopPanelCentral;
		this.ruta = ruta;
		setUpmsTotales(ruta);
		setBounds(100, 100, 1260, 895);

		JScrollPane scrollPane = new JScrollPane();

		lsUPM = new JList(upmTotal);
		lsUPM.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lsUPM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsUPM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2 && !arg0.isConsumed()) {
					arg0.consume();
					String UPMElegido = lsUPM.getSelectedValue();
					upmInt = Integer.parseInt(UPMElegido);

					lblUPM.setText(UPMElegido);

					getRegistrosTotales(ruta, upmInt);
					getIndividuosTotales(ruta, upmInt);
					getInformacionUPM(ruta, upmInt);
					getVegetacionPorSitio(ruta, upmInt);
					getEspeciesPorSitioArbolado(ruta, upmInt);
					getInformacionSitio(ruta, upmInt);
					getEspeciesPorSitioSotobosque(ruta, upmInt);
					getEspeciesPorSitioRepoblado(ruta, upmInt);
					createKml();
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

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setAutoscrolls(true);

		JLayeredPane layeredPaneArbolado = new JLayeredPane();
		layeredPaneArbolado.setAutoscrolls(true);
		tabbedPane.addTab("Arbolado", null, layeredPaneArbolado, null);

		JLabel lblNewLabel_2 = new JLabel("No. Registros totales:");

		txtRegistrosTotales = new JTextField();
		txtRegistrosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrosTotales.setColumns(10);

		JLabel lblNoIndividuosTotales = new JLabel("No. Individuos totales:");

		txtIndividuosTotales = new JTextField();
		txtIndividuosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtIndividuosTotales.setColumns(10);

		scrollPaneEspeciesPorSitio = new JScrollPane();
		scrollPaneEspeciesPorSitio.setBackground(Color.WHITE);
		scrollPaneEspeciesPorSitio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		tblEspeciesPorSitioArbolado = new JTable();
		tblEspeciesPorSitioArbolado.setBackground(Color.DARK_GRAY);
		tblEspeciesPorSitioArbolado.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblEspeciesPorSitioArbolado.setRowHeight(21);
		tblEspeciesPorSitioArbolado.setRowMargin(3);
		tblEspeciesPorSitioArbolado.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tblEspeciesPorSitioArbolado.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPaneEspeciesPorSitio.setViewportView(tblEspeciesPorSitioArbolado);

		JLabel lblEspeciesPorSitio = new JLabel("Especies por sitio");
		lblEspeciesPorSitio.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEspeciesPorSitio.setHorizontalAlignment(SwingConstants.CENTER);

		btnGraficas = new JButton("Gr\u00E1ficas");
		btnGraficas.addActionListener(new ActionListener() {
			@Override
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
		GroupLayout gl_layeredPaneArbolado = new GroupLayout(layeredPaneArbolado);
		gl_layeredPaneArbolado.setHorizontalGroup(gl_layeredPaneArbolado.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPaneArbolado.createSequentialGroup().addContainerGap().addGroup(
						gl_layeredPaneArbolado.createParallelGroup(Alignment.LEADING).addGroup(gl_layeredPaneArbolado
								.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_layeredPaneArbolado.createSequentialGroup()
												.addComponent(btnGraficas, GroupLayout.PREFERRED_SIZE, 82,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
								.addGroup(Alignment.TRAILING,
										gl_layeredPaneArbolado.createSequentialGroup()
												.addComponent(scrollPaneEspeciesPorSitio, GroupLayout.DEFAULT_SIZE, 650,
														Short.MAX_VALUE)
												.addContainerGap())
								.addGroup(gl_layeredPaneArbolado.createSequentialGroup()
										.addComponent(lblEspeciesPorSitio, GroupLayout.DEFAULT_SIZE, 650,
												Short.MAX_VALUE)
										.addContainerGap()))
								.addGroup(Alignment.TRAILING, gl_layeredPaneArbolado.createSequentialGroup()
										.addGroup(gl_layeredPaneArbolado.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 132,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE, 132,
														GroupLayout.PREFERRED_SIZE))
										.addGap(6)
										.addGroup(gl_layeredPaneArbolado.createParallelGroup(Alignment.LEADING)
												.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE, 67,
														GroupLayout.PREFERRED_SIZE))
										.addContainerGap()))));
		gl_layeredPaneArbolado.setVerticalGroup(gl_layeredPaneArbolado.createParallelGroup(Alignment.LEADING).addGroup(
				gl_layeredPaneArbolado.createSequentialGroup().addContainerGap().addGroup(gl_layeredPaneArbolado
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPaneArbolado.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(lblNoIndividuosTotales, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPaneArbolado.createSequentialGroup()
								.addComponent(txtRegistrosTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(txtIndividuosTotales, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(lblEspeciesPorSitio, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneEspeciesPorSitio, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
						.addGap(18).addComponent(btnGraficas).addContainerGap()));
		layeredPaneArbolado.setLayout(gl_layeredPaneArbolado);

		JPanel panel = new JPanel();

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

		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE).addGap(9))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane))
				.addGap(0)));

		JLayeredPane layeredPaneSotobosque = new JLayeredPane();
		tabbedPane.addTab("Repoblado/Sotobosque", null, layeredPaneSotobosque, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBackground(Color.WHITE);

		btnGraficasSotobosqueRepoblado = new JButton("Gr\u00E1ficas");
		btnGraficasSotobosqueRepoblado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmGraficasSotobosqueRepoblado graficasRepobladoSotobosque = new FrmGraficasSotobosqueRepoblado(ruta);
				graficasRepobladoSotobosque.setUpmid(upmInt);
				if (graficasRepobladoSotobosque.isVisible() == false) {
					graficasRepobladoSotobosque.setVisible(true);
					desktopPanelCentral.add(graficasRepobladoSotobosque);
					graficasRepobladoSotobosque.generarBarChartFrecuenciasRepoblado(ruta, upmInt);
					graficasRepobladoSotobosque.generarBarChartFrecuenciasSotbosque(ruta, upmInt);
				}

				if (graficasRepobladoSotobosque.isBackgroundSet()) {
					graficasRepobladoSotobosque.moveToFront();
				}
			}
		});

		JLabel lblEspeciesPorSitio_1 = new JLabel("Especies por sitio Sotobosque");
		lblEspeciesPorSitio_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspeciesPorSitio_1.setFont(new Font("Dialog", Font.BOLD, 16));

		tblEspeciesPorSitioSotobosque = new JTable();
		tblEspeciesPorSitioSotobosque.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblEspeciesPorSitioSotobosque.setBackground(Color.DARK_GRAY);
		tblEspeciesPorSitioSotobosque.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		scrollPane_1.setViewportView(tblEspeciesPorSitioSotobosque);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setBackground(Color.WHITE);

		JLabel lblEspeciesPorSitio_2 = new JLabel("Especies por sitio Repoblado");
		lblEspeciesPorSitio_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspeciesPorSitio_2.setFont(new Font("Dialog", Font.BOLD, 16));
		GroupLayout gl_layeredPaneSotobosque = new GroupLayout(layeredPaneSotobosque);
		gl_layeredPaneSotobosque.setHorizontalGroup(gl_layeredPaneSotobosque.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPaneSotobosque.createSequentialGroup().addGap(12)
						.addComponent(lblEspeciesPorSitio_1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(12))
				.addGroup(gl_layeredPaneSotobosque.createSequentialGroup().addGap(12)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(12))
				.addGroup(gl_layeredPaneSotobosque.createSequentialGroup().addGap(12)
						.addComponent(lblEspeciesPorSitio_2, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(12))
				.addGroup(gl_layeredPaneSotobosque.createSequentialGroup().addGap(12)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(12))
				.addGroup(Alignment.TRAILING,
						gl_layeredPaneSotobosque.createSequentialGroup().addGap(580)
								.addComponent(btnGraficasSotobosqueRepoblado, GroupLayout.PREFERRED_SIZE, 82,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)));
		gl_layeredPaneSotobosque.setVerticalGroup(gl_layeredPaneSotobosque.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPaneSotobosque.createSequentialGroup().addGap(29)
						.addComponent(lblEspeciesPorSitio_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGap(6).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE).addGap(12)
						.addComponent(lblEspeciesPorSitio_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
						.addGap(28).addComponent(btnGraficasSotobosqueRepoblado).addGap(11)));

		tblEspeciesPorSitioRepoblado = new JTable();
		tblEspeciesPorSitioRepoblado.setBackground(Color.DARK_GRAY);
		tblEspeciesPorSitioRepoblado.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblEspeciesPorSitioRepoblado.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		scrollPane_2.setViewportView(tblEspeciesPorSitioRepoblado);
		layeredPaneSotobosque.setLayout(gl_layeredPaneSotobosque);

		scrollPaneInforSitio = new JScrollPane();
		scrollPaneInforSitio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneInforSitio.setBackground(Color.WHITE);

		tblInformacionSItio = new JTable();
		scrollPaneInforSitio.setViewportView(tblInformacionSItio);

		lblInformacinDeSitios = new JLabel("Informaci\u00F3n de sitios");
		lblInformacinDeSitios.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDeSitios.setFont(new Font("Dialog", Font.BOLD, 16));

		scrollPaneTipoVegetacionPorSitio = new JScrollPane();
		scrollPaneTipoVegetacionPorSitio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneTipoVegetacionPorSitio.setBackground(Color.WHITE);

		tblVegetacionPorSitio = new JTable();
		tblVegetacionPorSitio.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblVegetacionPorSitio.setBackground(new Color(51, 51, 51));
		tblVegetacionPorSitio.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblVegetacionPorSitio.setRowHeight(21);
		tblVegetacionPorSitio.setRowMargin(3);
		tblVegetacionPorSitio.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tblVegetacionPorSitio.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tblVegetacionPorSitio.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPaneTipoVegetacionPorSitio.setViewportView(tblVegetacionPorSitio);

		JLabel lblTipoDeVetacion = new JLabel("Tipo de vetacion por sitio en arbolado");
		lblTipoDeVetacion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTipoDeVetacion.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnVerEnMapa = new JButton("Ver en mapa");
		btnVerEnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String UPM_earth = lsUPM.getSelectedValue();
					Runtime.getRuntime()
							.exec(new String[] {
									"C:\\Program Files (x86)\\Google\\Google Earth Pro\\client\\googleearth.exe",
									"C:\\Users\\ignacio.martinez\\Desktop\\AbiesProject\\doc.kml" });
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup().addGap(0).addComponent(lblInformacinDeSitios,
								GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPaneInforSitio, Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
										.addComponent(lblTipoDeVetacion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												403, Short.MAX_VALUE)
										.addComponent(scrollPaneTipoVegetacionPorSitio, GroupLayout.DEFAULT_SIZE, 403,
												Short.MAX_VALUE))))
						.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup().addContainerGap(205, Short.MAX_VALUE)
						.addComponent(btnVerEnMapa).addGap(139)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(38)
				.addComponent(lblInformacinDeSitios, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(scrollPaneInforSitio, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
				.addGap(103).addComponent(lblTipoDeVetacion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(scrollPaneTipoVegetacionPorSitio, GroupLayout.PREFERRED_SIZE, 167,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 227, Short.MAX_VALUE).addComponent(btnVerEnMapa)
				.addGap(60)));
		panel_2.setLayout(gl_panel_2);
		getContentPane().setLayout(groupLayout);
		// scrollPaneEast.setViewportView(scrollPaneInforSitio);

	}

	public void createKml() {
		String kml = "<?xml version='1.0' encoding='UTF-8'?> <kml xmlns='http://www.opengis.net/kml/2.2' xmlns:gx='http://www.google.com/kml/ext/2.2' xmlns:kml='http://www.opengis.net/kml/2.2' xmlns:atom='http://www.w3.org/2005/Atom'> <Document> 	<name>"
				+ UPMID
				+ ".kmz</name> 	<Style id='IconStyle04018'> 		<IconStyle> 			<scale>0.25</scale> 			<Icon> 				<href>files/Layer0_Symbol_dc4f048_0.png</href> 			</Icon> 		</IconStyle> 		<LabelStyle> 			<color>00000000</color> 			<scale>0</scale> 		</LabelStyle> 		<PolyStyle> 			<color>ff000000</color> 			<outline>0</outline> 		</PolyStyle> 	</Style> 	<Placemark id='ID_04018'> 		<name>"
				+ UPMID
				+ "</name> 		<Snippet maxLines='0'></Snippet> 		<description><![CDATA[<html xmlns:fo='http://www.w3.org/1999/XSL/Format' xmlns:msxsl='urn:schemas-microsoft-com:xslt'> <head> <META http-equiv='Content-Type' content='text/html'> <meta http-equiv='content-type' content='text/html; charset=UTF-8'> </head> <body style='margin:0px 0px 0px 0px;overflow:auto;background:#FFFFFF;'> <table style='font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-collapse:collapse;padding:3px 3px 3px 3px'> <tr style='text-align:center;font-weight:bold;background:#9CBCE2'> <td>"
				+ UPMID
				+ "</td> </tr> <tr> <td> <table style='font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-spacing:0px; padding:3px 3px 3px 3px'> <tr> <td>FID</td> <td>"
				+ UPMID + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>ZONA_INFYS</td> <td>" + Region
				+ "</td> </tr> <tr> <td>A16</td> <td>" + A + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>C16</td> <td>" + C
				+ "</td> </tr> <tr> <td>D16</td> <td>" + D + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>E16</td> <td>" + E
				+ "</td> </tr> <tr> <td>F16</td> <td>" + F + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>G16</td> <td>" + G
				+ "</td> </tr> <tr> <td>H16</td> <td>" + H + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>B16</td> <td>" + B
				+ "</td> </tr> <tr> <td>X</td> <td>" + X + "</td> </tr> <tr bgcolor='#D4E4F3'> <td>Y</td> <td>" + Y
				+ "</td> </tr> <tr> <td>NOM_EDO</td> <td>" + Estado
				+ "</td> </tr> <tr bgcolor='#D4E4F3'> <td>NOM_MUN</td> <td>" + Municipio
				+ "</td> </tr> <tr bgcolor='#D4E4F3'> <td>ID_UPM</td> <td>" + UPMID
				+ "</td> </tr> </table> </td> </tr> </table> </body> </html>]]></description> 		<styleUrl>#IconStyle04018</styleUrl> 		<gx:balloonVisibility>1</gx:balloonVisibility> 		<Point> 			<coordinates>"
				+ X + "," + Y + ",0</coordinates> 		</Point> 	</Placemark> </Document> </kml>";
		//System.out.println(kml);
		Writer writer = null;

		try {
			Path currentPath = Paths.get("");
			String path = currentPath.toAbsolutePath().toString();
			
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(path+"\\doc.kml"), "utf-8"));
		    writer.write(kml);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

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
		String query = "SELECT DISTINCT sitio.SitioID, sitio.Sitio, claveSerieV.TipoVegetacion, faseSucecional.Clave  AS FaseSucecional, arbolado.Consecutivo as No_registros, arbolado.NoIndividuo AS Individuo FROM TAXONOMIA_Arbolado arbolado LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID and sitio.UPMID=arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional WHERE arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID ORDER BY arbolado.UPMID  ";
		// System.out.println(query);
		int total = 0, totalrs;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				totalrs = Integer.parseInt(rsExterno.getString("Individuo"));

				total = total + totalrs;
			}
			txtIndividuosTotales.setText(Integer.toString(total));
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getInformacionUPM(String ruta, int upmid) {
		String query = "SELECT  upm.UPMID, upmMalla.Estado, upmMalla.Municipio, upm.Altitud, upm.PendienteRepresentativa, upmMalla.A, upmMalla.B, upmMalla.C, upmMalla.D, upmMalla.E, upmMalla.F, upmMalla.G, upmMalla.H, sitio.GradosLatitud + (sitio.MinutosLatitud / 60.0) +  (sitio.SegundosLatitud / 3600.0) AS Y, -1 * ((-1 * sitio.GradosLongitud) + (sitio.MinutosLongitud / 60.0) +  (sitio.SegundosLongitud / 3600.0)) AS X, CASE upm.Accesible WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END Accesible, tipoUPM.TipoUPM, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia, upmMalla.Region FROM  UPM_UPM upm  JOIN UPM_MallaPuntos upmMalla ON upm.UPMID=upmMalla.UPMID JOIN sitios_Sitio sitio ON sitio.UPMID=upm.UPMID LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID  LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID  LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID WHERE upm.UPMID="
				+ upmid+" AND sitio.Sitio=1";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				UPMID=Integer.toString(rsExterno.getInt("UPMID"));
				Altitud=Integer.toString(rsExterno.getInt("Altitud"));
				PendienteRepresentativa=Integer.toString(rsExterno.getInt("PendienteRepresentativa"));
				A=Integer.toString(rsExterno.getInt("A"));
				B=Integer.toString(rsExterno.getInt("B"));
				C=Integer.toString(rsExterno.getInt("C"));
				D=Integer.toString(rsExterno.getInt("D"));
				E=Integer.toString(rsExterno.getInt("E"));
				F=Integer.toString(rsExterno.getInt("F"));
				G=Integer.toString(rsExterno.getInt("G"));
				H=Integer.toString(rsExterno.getInt("H"));
				Estado=rsExterno.getString("Estado");
				Municipio=rsExterno.getString("Municipio");
				Y=rsExterno.getString("Y");
				X=rsExterno.getString("X");
				Accesible=rsExterno.getString("Accesible");
				TipoUPM=rsExterno.getString("TipoUPM");
				Exposicion=rsExterno.getString("Exposicion");
				Fisiografia=rsExterno.getString("Fisiografia");
				Region=rsExterno.getString("Region");
				
				lblEstadoResp.setText(Estado);
				lblMunicipioResp.setText(Municipio);
				lblAltitudResp.setText(Altitud);
				lblPendienteRepresentativaResp.setText(PendienteRepresentativa);
				lblExposicionResp.setText(Exposicion);
				lblFisiografiaResp.setText(Fisiografia);
				lblAccesible.setText(Accesible);
				lblTipo.setText(TipoUPM);
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

	public void getVegetacionPorSitio(String ruta, int upmid) {
		String query = "SELECT DISTINCT sitio.SitioID, sitio.Sitio, claveSerieV.TipoVegetacion, faseSucecional.Clave  AS FaseSucecional, arbolado.Consecutivo as No_registros, arbolado.NoIndividuo AS Individuo FROM TAXONOMIA_Arbolado arbolado LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID and sitio.UPMID=arbolado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional WHERE arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID ORDER BY arbolado.UPMID  ";
		// System.out.println(query);
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

	public void getEspeciesPorSitioArbolado(String ruta, int upmid) {
		String query = "SELECT  DISTINCT sitio.sitio, printf('%s %s %s', genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, formaVida.Descripcion AS FormaVida FROM TAXONOMIA_Arbolado arbolado JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=arbolado.UPMID LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID  LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID  LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID  LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  WHERE /*arbolado.CondicionID!=2 and*/ arbolado.UPMID="
				+ upmid + " GROUP BY arbolado.UPMID, arbolado.SitioID, arbolado.ArboladoID ORDER BY sitio.sitio ";
		String genero = null, especie = null, infraespecie = null, entidadTaxonomica;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		// System.out.println(query);
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

		tblEspeciesPorSitioArbolado.setModel(especiesPorSitioModel);
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(SwingConstants.CENTER);
		tblEspeciesPorSitioArbolado.getColumnModel().getColumn(0).setCellRenderer(cellRenderedCenter);
		tblEspeciesPorSitioArbolado.getColumnModel().getColumn(2).setCellRenderer(cellRenderedCenter);
	}

	public void getEspeciesPorSitioSotobosque(String ruta, int upmid) {
		String query = "SELECT  DISTINCT  sitio.sitio, printf('%s %s %s',genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, vigorSotobosque.Descripcion AS Vigor FROM TAXONOMIA_Sotobosque sotobosque  JOIN SITIOS_Sitio sitio ON sitio.SitioID=sotobosque.SitioID  LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=sotobosque.UPMID  LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional  LEFT JOIN CAT_FamiliaEspecie familia ON sotobosque.FamiliaID = familia.FamiliaID   LEFT JOIN CAT_Genero genero ON sotobosque.GeneroID = genero.GeneroID   LEFT JOIN CAT_Especie especie ON sotobosque.EspecieID = especie.EspecieID   LEFT JOIN CAT_Infraespecie infraespecie ON sotobosque.InfraespecieID = infraespecie.InfraespecieID   LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorSotobosque ON vigorSotobosque.VigorID=sotobosque.VigorID WHERE sotobosque.UPMID="
				+ upmid
				+ "  GROUP BY sotobosque.UPMID, sotobosque.SitioID, sotobosque.SotoBosqueID ORDER BY sitio.sitio";
		// System.out.println(query);
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		// System.out.println(query);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (especiesPorSitioArboladoModel.getRowCount() > 0) {
				for (int i = especiesPorSitioArboladoModel.getRowCount() - 1; i > -1; i--) {
					especiesPorSitioArboladoModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {

				especiesPorSitioArboladoModel.addRow(new Object[] { rsExterno.getString("sitio"),
						rsExterno.getString("Entidad"), rsExterno.getString("Vigor") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblEspeciesPorSitioSotobosque.setModel(especiesPorSitioArboladoModel);
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(SwingConstants.CENTER);
		tblEspeciesPorSitioSotobosque.getColumnModel().getColumn(0).setCellRenderer(cellRenderedCenter);
		tblEspeciesPorSitioSotobosque.getColumnModel().getColumn(2).setCellRenderer(cellRenderedCenter);
	}

	public void getEspeciesPorSitioRepoblado(String ruta, int upmid) {
		String query = "SELECT  DISTINCT  sitio.sitio, printf('%s %s %s',genero.Nombre, especie.Nombre, infraespecie.Nombre) as Entidad, vigorSotobosque.Descripcion AS Vigor  FROM TAXONOMIA_Repoblado repoblado   JOIN SITIOS_Sitio sitio ON sitio.SitioID=repoblado.SitioID   LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV and sitio.UPMID=repoblado.UPMID   LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional   LEFT JOIN CAT_FamiliaEspecie familia ON repoblado.FamiliaID = familia.FamiliaID    LEFT JOIN CAT_Genero genero ON repoblado.GeneroID = genero.GeneroID    LEFT JOIN CAT_Especie especie ON repoblado.EspecieID = especie.EspecieID    LEFT JOIN CAT_Infraespecie infraespecie ON repoblado.InfraespecieID = infraespecie.InfraespecieID    LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorSotobosque ON vigorSotobosque.VigorID=repoblado.VigorID  WHERE repoblado.UPMID= "
				+ upmid + "  GROUP BY repoblado.UPMID,repoblado.SitioID,repoblado.RepobladoID ORDER BY sitio.sitio ";
		// System.out.println(query);
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		// System.out.println(query);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (especiesPorSitioRepobladoModel.getRowCount() > 0) {
				for (int i = especiesPorSitioRepobladoModel.getRowCount() - 1; i > -1; i--) {
					especiesPorSitioRepobladoModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {

				especiesPorSitioRepobladoModel.addRow(new Object[] { rsExterno.getString("sitio"),
						rsExterno.getString("Entidad"), rsExterno.getString("Vigor") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblEspeciesPorSitioRepoblado.setModel(especiesPorSitioRepobladoModel);
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(SwingConstants.CENTER);
		tblEspeciesPorSitioRepoblado.getColumnModel().getColumn(0).setCellRenderer(cellRenderedCenter);
		tblEspeciesPorSitioRepoblado.getColumnModel().getColumn(2).setCellRenderer(cellRenderedCenter);
	}

	public void alignCellsTables(DefaultTableModel model, JTable tbl) {
		DefaultTableCellRenderer cellRenderedCenter = new DefaultTableCellRenderer();

		cellRenderedCenter.setHorizontalAlignment(SwingConstants.CENTER);
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
