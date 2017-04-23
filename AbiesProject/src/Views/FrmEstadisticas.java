package Views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import com.toedter.components.JSpinField;

import Database.ExternalConnection;

import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmEstadisticas extends JInternalFrame {
	
	
	private JTextField txtTotalUpms;

	private JTable tblUpmPorEstado;
	private JTable tblTipoUPM;
	private JTable tblDiasMuestreo;
	private JTable tblColocacionTAG;

	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;

	private JFormattedTextField ftxtfechaInicial;
	private JFormattedTextField ftxtfechafinal;

	public String[] columnNameEstados = { "Estado", "Conteo UPMs" };
	public String[] columnNameDiasMuestreados = { "Dias", "Conteo UPMs" };
	public String[] columnNameColocacionTag = { "Colocacion TAG", "Conteo UPMs" };
	public String[] columnNameTipoUpm = { "Tipo UPM", "Conteo UPMs" };
	/************************************************** Sitios *****************************************************************************************/
	public String[] columnNameSitiosAccesiblesPorConglomerados = { "Conteo UPMs", "Sitios accesibles" };
	public String[] columnNameSitiosInaccesibles = { "Conteo sitios", "Inaccesibilidad" };
	public String[] columnNameSitioPorCondicion = { "Conteo sitios", "Condición de vegetación" };
	public String[] columnNameCoberturaPorUpm = { "Conteo UPMs", "Condición" };

	public DefaultTableModel UpmsPorEstadoModel = new DefaultTableModel(null, columnNameEstados);
	public DefaultTableModel TipoUpmModel = new DefaultTableModel(null, columnNameTipoUpm);
	public DefaultTableModel DiasMuestreoModel = new DefaultTableModel(null, columnNameDiasMuestreados);
	public DefaultTableModel ColocacionTagModel = new DefaultTableModel(null, columnNameColocacionTag);
	/************************************************** Sitios *****************************************************************************************/
	public DefaultTableModel sitiosAccesiblesPorConglomeradosModel = new DefaultTableModel(null,columnNameSitiosAccesiblesPorConglomerados);
	public DefaultTableModel sitiosInaccesiblesModel = new DefaultTableModel(null, columnNameSitiosInaccesibles);
	public DefaultTableModel sitioPorCondicionModel = new DefaultTableModel(null, columnNameSitioPorCondicion);
	public DefaultTableModel CoberturaPorUpmModel = new DefaultTableModel(null, columnNameCoberturaPorUpm);

	private JTextField txtSitioTotales;
	private JTextField txtSitioAccesibles;
	private JTextField txtSitiosInaccesibles;
	private JTable tblSitiosAccesiblesPorConglomerados;
	private JTable tblSitiosInaccesibles;
	private JTextField txtClinometro;
	private JTextField txtHipsometro;
	private JTextField txtNoForestal;
	private JTextField txtForestal;
	private JTextField txtArbolFuera;
	private JTable tblSitioPorCondicion;
	private JTable tblCoberturaPorUpm;

	public FrmEstadisticas(String ruta) {
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("Estadistica");
		setFrameIcon(null);
		setBounds(100, 100, 764, 698);		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JLayeredPane layPanUpm = new JLayeredPane();
		tabbedPane.addTab("UPM", null, layPanUpm, null);

		JLabel lblUpmsPorEstado = new JLabel("UPMs por estado");
		lblUpmsPorEstado.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPanePorEstado = new JScrollPane();

		tblUpmPorEstado = new JTable();

		scrollPanePorEstado.setViewportView(tblUpmPorEstado);
		scrollPanePorEstado.setPreferredSize(layPanUpm.getParent().getPreferredSize());

		JLabel lblRelacionPorTipo = new JLabel("Relacion por tipo de conglomerado");
		lblRelacionPorTipo.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPaneTipoUPM = new JScrollPane();

		tblTipoUPM = new JTable();

		scrollPaneTipoUPM.setViewportView(tblTipoUPM);

		JLabel lblEstadisticasDeUpm = new JLabel("Informe General BD (UPM)");
		lblEstadisticasDeUpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticasDeUpm.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEstadisticasDeUpm.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblFechaInicial = new JLabel("Fecha inicial");

		JLabel label = new JLabel("Fecha final");

		ftxtfechaInicial = new JFormattedTextField();
		ftxtfechaInicial.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtfechaInicial.setEditable(false);
		ftxtfechaInicial.setAlignmentX(Component.RIGHT_ALIGNMENT);

		ftxtfechafinal = new JFormattedTextField();
		ftxtfechafinal.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtfechafinal.setEditable(false);
		ftxtfechafinal.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel lblUpmTotales = new JLabel("UPMs Totales:");
		lblUpmTotales.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtTotalUpms = new JTextField();
		txtTotalUpms.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalUpms.setEditable(false);
		txtTotalUpms.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		txtTotalUpms.setColumns(10);

		JSeparator separator = new JSeparator();

		JLabel lblDiasDeMuestro = new JLabel("Dias de muestro por UPM");
		lblDiasDeMuestro.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPaneDiasDeMuestreo = new JScrollPane();
		scrollPaneDiasDeMuestreo.setAlignmentX(Component.LEFT_ALIGNMENT);

		tblDiasMuestreo = new JTable();
		scrollPaneDiasDeMuestreo.setViewportView(tblDiasMuestreo);

		JLabel lblColocacinDelTag = new JLabel("Colocaci\u00F3n del TAG");
		lblColocacinDelTag.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPane_1 = new JScrollPane();

		tblColocacionTAG = new JTable();
		scrollPane_1.setViewportView(tblColocacionTAG);
		GroupLayout gl_layPanUpm = new GroupLayout(layPanUpm);
		gl_layPanUpm.setHorizontalGroup(
			gl_layPanUpm.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(16)
					.addComponent(lblEstadisticasDeUpm, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
					.addGap(21))
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(154)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_layPanUpm.createSequentialGroup()
							.addComponent(lblUpmTotales, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(txtTotalUpms, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(146)
							.addComponent(lblFechaInicial, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(12))
						.addGroup(gl_layPanUpm.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(ftxtfechaInicial, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(ftxtfechafinal, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
					.addGap(121))
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(10)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanePorEstado, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						.addComponent(lblUpmsPorEstado, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
					.addGap(81)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRelacionPorTipo, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
						.addComponent(scrollPaneTipoUPM, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
					.addGap(40))
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDiasDeMuestro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						.addComponent(scrollPaneDiasDeMuestreo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
					.addGap(66)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
						.addComponent(lblColocacinDelTag, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
					.addGap(40))
		);
		gl_layPanUpm.setVerticalGroup(
			gl_layPanUpm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layPanUpm.createSequentialGroup()
					.addGap(6)
					.addComponent(lblEstadisticasDeUpm, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layPanUpm.createSequentialGroup()
							.addGroup(gl_layPanUpm.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtfechaInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaInicial))
							.addGap(6)
							.addGroup(gl_layPanUpm.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtfechafinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)))
						.addGroup(gl_layPanUpm.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layPanUpm.createSequentialGroup()
									.addGap(6)
									.addComponent(lblUpmTotales))
								.addComponent(txtTotalUpms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUpmsPorEstado)
						.addComponent(lblRelacionPorTipo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanePorEstado, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addComponent(scrollPaneTipoUPM, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
					.addGap(116)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColocacinDelTag)
						.addComponent(lblDiasDeMuestro))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneDiasDeMuestreo, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addGap(101))
		);
		layPanUpm.setLayout(gl_layPanUpm);
		layPanUpm.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { txtTotalUpms, ftxtfechaInicial, ftxtfechafinal, scrollPanePorEstado,
						scrollPaneTipoUPM, scrollPaneDiasDeMuestreo, scrollPane_1, lblUpmsPorEstado, tblUpmPorEstado,
						lblRelacionPorTipo, tblTipoUPM, lblEstadisticasDeUpm, lblFechaInicial, label, lblUpmTotales,
						separator, lblDiasDeMuestro, tblDiasMuestreo, lblColocacinDelTag, tblColocacionTAG }));

		JLayeredPane layPanSitio = new JLayeredPane();
		tabbedPane.addTab("Sitios", null, layPanSitio, null);

		JLabel lblInformeGeneralBd = new JLabel("Informe General BD (Sitios)");
		lblInformeGeneralBd.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformeGeneralBd.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblInformeGeneralBd.setAlignmentX(0.5f);

		JLabel lblSitiosTotales = new JLabel("Sitios totales:");
		lblSitiosTotales.setFont(new Font("DialogInput", Font.BOLD, 14));

		txtSitioTotales = new JTextField();
		txtSitioTotales.setEditable(false);
		txtSitioTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtSitioTotales.setColumns(10);

		JLabel lblSitioAccesibles = new JLabel("Sitios accesibles:");
		lblSitioAccesibles.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioAccesibles.setFont(new Font("Dialog", Font.ITALIC, 12));

		txtSitioAccesibles = new JTextField();
		txtSitioAccesibles.setHorizontalAlignment(SwingConstants.CENTER);
		txtSitioAccesibles.setEditable(false);
		txtSitioAccesibles.setColumns(10);

		JLabel lblSitioInaccesibles = new JLabel("Sitios inaccesibles:");
		lblSitioInaccesibles.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioInaccesibles.setFont(new Font("Dialog", Font.ITALIC, 12));

		txtSitiosInaccesibles = new JTextField();
		txtSitiosInaccesibles.setHorizontalAlignment(SwingConstants.CENTER);
		txtSitiosInaccesibles.setEditable(false);
		txtSitiosInaccesibles.setColumns(10);

		JScrollPane scrollPaneSitiosAccesiblesPorConglomerados = new JScrollPane();

		tblSitiosAccesiblesPorConglomerados = new JTable();
		scrollPaneSitiosAccesiblesPorConglomerados.setViewportView(tblSitiosAccesiblesPorConglomerados);

		JScrollPane scrollPaneSitiosInaccesibles = new JScrollPane();

		tblSitiosInaccesibles = new JTable();
		scrollPaneSitiosInaccesibles.setViewportView(tblSitiosInaccesibles);

		JLabel lblMedidosCon = new JLabel("Medidos con Clin\u00F3metro");
		lblMedidosCon.setFont(new Font("Dialog", Font.PLAIN, 12));

		JLabel lblMedidosCon_1 = new JLabel("Medidos con Hips\u00F3metro");
		lblMedidosCon_1.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtClinometro = new JTextField();
		txtClinometro.setEditable(false);
		txtClinometro.setHorizontalAlignment(SwingConstants.CENTER);
		txtClinometro.setColumns(10);

		txtHipsometro = new JTextField();
		txtHipsometro.setEditable(false);
		txtHipsometro.setHorizontalAlignment(SwingConstants.CENTER);
		txtHipsometro.setColumns(10);

		JLabel lblForestal = new JLabel("Forestal");
		lblForestal.setFont(new Font("Dialog", Font.PLAIN, 12));

		JLabel lblNoForestal = new JLabel("No Forestal");
		lblNoForestal.setFont(new Font("Dialog", Font.PLAIN, 12));

		txtNoForestal = new JTextField();
		txtNoForestal.setEditable(false);
		txtNoForestal.setHorizontalAlignment(SwingConstants.CENTER);
		txtNoForestal.setColumns(10);

		txtForestal = new JTextField();
		txtForestal.setEditable(false);
		txtForestal.setHorizontalAlignment(SwingConstants.CENTER);
		txtForestal.setColumns(10);

		JSeparator separator_2 = new JSeparator();

		txtArbolFuera = new JTextField();
		txtArbolFuera.setEditable(false);
		txtArbolFuera.setHorizontalAlignment(SwingConstants.CENTER);
		txtArbolFuera.setColumns(10);

		JLabel lblArbolFueraDe = new JLabel("Arbol Fuera de Bosque");
		lblArbolFueraDe.setFont(new Font("Dialog", Font.PLAIN, 12));

		JLabel lblCondicion = new JLabel("Sitios por condici\u00F3n de la vetaci\u00F3n");

		JScrollPane scrollPane = new JScrollPane();

		tblSitioPorCondicion = new JTable();
		scrollPane.setViewportView(tblSitioPorCondicion);

		JLabel lblCoberturaPorUpm = new JLabel("Cobertura por UPM");

		JScrollPane scrollPane_2 = new JScrollPane();

		tblCoberturaPorUpm = new JTable();
		scrollPane_2.setViewportView(tblCoberturaPorUpm);

		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_layPanSitio = new GroupLayout(layPanSitio);
		gl_layPanSitio.setHorizontalGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layPanSitio.createSequentialGroup().addGap(12)
						.addComponent(lblInformeGeneralBd, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE).addGap(12))
				.addGroup(gl_layPanSitio.createSequentialGroup().addGap(12)
						.addComponent(lblSitiosTotales, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(txtSitioTotales, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(55).addComponent(lblSitioAccesibles, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(txtSitioAccesibles, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(42).addComponent(lblSitioInaccesibles, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(txtSitiosInaccesibles, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(38))
				.addGroup(gl_layPanSitio.createSequentialGroup().addGap(12)
						.addGroup(gl_layPanSitio
								.createParallelGroup(Alignment.LEADING).addComponent(separator_2,
										GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layPanSitio.createSequentialGroup()
										.addComponent(lblMedidosCon, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtClinometro, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layPanSitio.createSequentialGroup()
										.addComponent(lblMedidosCon_1, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtHipsometro, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_layPanSitio.createSequentialGroup()
										.addComponent(lblForestal, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtForestal, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layPanSitio.createSequentialGroup()
										.addComponent(lblNoForestal, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtNoForestal, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_layPanSitio.createSequentialGroup()
										.addComponent(lblArbolFueraDe, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtArbolFuera, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(53)
						.addComponent(scrollPaneSitiosAccesiblesPorConglomerados, GroupLayout.DEFAULT_SIZE, 206,
								Short.MAX_VALUE)
						.addGap(42)
						.addComponent(scrollPaneSitiosInaccesibles, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addGap(38))
				.addGroup(gl_layPanSitio.createSequentialGroup().addGap(42)
						.addComponent(lblCondicion, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addGap(23)
						.addComponent(lblCoberturaPorUpm, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addGap(38))
				.addGroup(gl_layPanSitio.createSequentialGroup().addGap(42)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addGap(23)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addGap(38)));
		gl_layPanSitio.setVerticalGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING).addGroup(gl_layPanSitio
				.createSequentialGroup()
				.addComponent(lblInformeGeneralBd, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
				.addGap(12)
				.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSitiosTotales, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSitioTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSitioAccesibles)
						.addComponent(txtSitioAccesibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblSitioInaccesibles))
						.addComponent(txtSitiosInaccesibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING).addGroup(gl_layPanSitio
						.createSequentialGroup()
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(21)
						.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblMedidosCon))
								.addComponent(txtClinometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblMedidosCon_1))
								.addComponent(txtHipsometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblForestal))
								.addComponent(txtForestal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblNoForestal))
								.addComponent(txtNoForestal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_layPanSitio.createSequentialGroup().addGap(2).addComponent(lblArbolFueraDe))
								.addComponent(txtArbolFuera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_layPanSitio.createSequentialGroup()
								.addComponent(scrollPaneSitiosAccesiblesPorConglomerados, GroupLayout.DEFAULT_SIZE, 166,
										Short.MAX_VALUE)
								.addGap(15))
						.addGroup(gl_layPanSitio.createSequentialGroup()
								.addComponent(scrollPaneSitiosInaccesibles, GroupLayout.DEFAULT_SIZE, 166,
										Short.MAX_VALUE)
								.addGap(15)))
				.addGap(48)
				.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING).addComponent(lblCondicion)
						.addComponent(lblCoberturaPorUpm))
				.addGap(9)
				.addGroup(gl_layPanSitio.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
				.addGap(12)));
		layPanSitio.setLayout(gl_layPanSitio);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tabbedPane, layPanUpm,
				lblUpmsPorEstado, scrollPanePorEstado, tblUpmPorEstado, lblRelacionPorTipo, scrollPaneTipoUPM,
				tblTipoUPM, lblEstadisticasDeUpm, lblFechaInicial, label, ftxtfechaInicial, ftxtfechafinal,
				lblUpmTotales, txtTotalUpms, separator, lblDiasDeMuestro, scrollPaneDiasDeMuestreo, tblDiasMuestreo,
				lblColocacinDelTag, scrollPane_1, tblColocacionTAG, layPanSitio }));
		
		getCalculoUpmsTotales(ruta);
		getMaxMinFechas(ruta);
		getCalculoUpmsEstado(ruta);
		getCalculoUpmsTipo(ruta);
		getCalculoUpmsDiasMuestreados(ruta);
		getCalculoUpmsColocacionTag(ruta);
		getSitiosAccesibles(ruta);
		getSitiosInaccesibles(ruta);
		getSitiosTotales(ruta);
		getSitiosPorHipsometro(ruta);
		getSitiosPorClinometro(ruta);
		getSitiosForestal(ruta);
		getSitiosNoForestal(ruta);
		getSitiosArbolFuera(ruta);
		getSitiosInaccesiblesCausas(ruta);
		getSitiosConteoAccesiblesPorUPM(ruta);
		getSitioPorCondicionVegetacion(ruta);
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void getMaxMinFechas(String ruta) {
		String fechaInicio = null, fechaFin = null;
		String query = "SELECT MIN(FechaInicio) as Min, MAX(FechaFin) as Max FROM UPM_UPM ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				fechaInicio = rsExterno.getString("Min");
				fechaFin = rsExterno.getString("Max");
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		ftxtfechafinal.setText(fechaFin);
		ftxtfechaInicial.setText(fechaInicio);
	}

	public void getCalculoUpmsTotales(String ruta) {
		int upmTotales = 0;
		String query = "SELECT DISTINCT COUNT(UPMID) as TOTALES FROM UPM_UPM upm ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {
				//System.out.println("RUTA UPMS_Totaes " +ruta);
				upmTotales = rsExterno.getInt("TOTALES");
			}
			baseDatosExterna.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
		//System.out.println("UPMS Totales"+upmTotales);
		txtTotalUpms.setText(Integer.toString(upmTotales));
	}

	public void getCalculoUpmsEstado(String ruta) {
		String query = "SELECT Estado, Count(Estado) AS Conteo_UPMS FROM UPM_UPM upm LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID GROUP BY Estado ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (UpmsPorEstadoModel.getRowCount() > 0) {
				for (int i = UpmsPorEstadoModel.getRowCount() - 1; i > -1; i--) {
					UpmsPorEstadoModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				UpmsPorEstadoModel
						.addRow(new Object[] { rsExterno.getString("Estado"), rsExterno.getInt("Conteo_UPMS") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblUpmPorEstado.setModel(UpmsPorEstadoModel);

	}

	public void getCalculoUpmsTipo(String ruta) {
		String query = "SELECT tipoUPM.TipoUPM AS TipoUPM , Count(upm.UPMID) AS Conteo_UPMS FROM UPM_UPM upm LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID GROUP BY tipoUPM.TipoUPM ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			/**/if (TipoUpmModel.getRowCount() > 0) {
				for (int i = TipoUpmModel.getRowCount() - 1; i > -1; i--) {
					TipoUpmModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				TipoUpmModel.addRow(new Object[] { rsExterno.getString("TipoUPM"), rsExterno.getInt("Conteo_UPMS") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblTipoUPM.setModel(TipoUpmModel);
	}

	public void getCalculoUpmsDiasMuestreados(String ruta) {
		String query = "SELECT DISTINCT julianday(upm.FechaFin)-julianday(upm.FechaInicio)+1 as DIAS, COUNT(upm.UPMID) AS Conteo_UPMS FROM UPM_UPM upm LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID GROUP BY DIAS ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			/**/if (DiasMuestreoModel.getRowCount() > 0) {
				for (int i = DiasMuestreoModel.getRowCount() - 1; i > -1; i--) {
					DiasMuestreoModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				DiasMuestreoModel.addRow(new Object[] { rsExterno.getString("DIAS"), rsExterno.getInt("Conteo_UPMS") });

			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblDiasMuestreo.setModel(DiasMuestreoModel);
	}

	public void getCalculoUpmsColocacionTag(String ruta) {
		String query = "SELECT tipoColocacion.Descripcion as Colocacion, Count(transponder.UPMID) as Conteo_UPMS FROM SITIOS_Transponder transponder LEFT JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=transponder.TipoColocacionID GROUP BY tipoColocacion.Descripcion ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			/**/if (ColocacionTagModel.getRowCount() > 0) {
				for (int i = ColocacionTagModel.getRowCount() - 1; i > -1; i--) {
					ColocacionTagModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				ColocacionTagModel
						.addRow(new Object[] { rsExterno.getString("Colocacion"), rsExterno.getInt("Conteo_UPMS") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblColocacionTAG.setModel(ColocacionTagModel);
	}

	public void getSitiosAccesibles(String ruta) {
		String query = "Select SitioAccesible, count(SitioAccesible) as Conteo_SitiosAccesibles FROM SITIOS_Sitio WHERE SitioAccesible=1 GROUP BY SitioAccesible ORDER BY SitioAccesible DESC";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtSitioAccesibles.setText(Integer.toString(rsExterno.getInt("Conteo_SitiosAccesibles")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSitiosInaccesibles(String ruta) {
		String query = "Select SitioAccesible, count(SitioAccesible) as Conteo_SitiosAccesibles FROM SITIOS_Sitio WHERE SitioAccesible=0 GROUP BY SitioAccesible ORDER BY SitioAccesible DESC";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtSitiosInaccesibles.setText(Integer.toString(rsExterno.getInt("Conteo_SitiosAccesibles")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosTotales(String ruta) {
		String query = "SELECT COUNT(SitioID) as Sitios_totales FROM SITIOS_Sitio";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtSitioTotales.setText(Integer.toString(rsExterno.getInt("Sitios_totales")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosPorClinometro(String ruta) {
		String query = "Select  Count(sitio.CintaClinometroBrujula) as Cuenta_CintaClinometro FROM SITIOS_Sitio sitio where sitio.CintaClinometroBrujula=1";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtClinometro.setText(Integer.toString(rsExterno.getInt("Cuenta_CintaClinometro")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosPorHipsometro(String ruta) {
		String query = "Select Count(HipsometroBrujula) as Cuenta_Hipsometro FROM SITIOS_Sitio sitio where HipsometroBrujula=1 ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtHipsometro.setText(Integer.toString(rsExterno.getInt("Cuenta_Hipsometro")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosForestal(String ruta) {
		String query = "Select  Count(sitio.SitioID) as Conteo_Sitios FROM SITIOS_Sitio sitio Left JOIN CAT_ClaveSerieV claveSerieV ON  claveSerieV.ClaveSerieVID=sitio.ClaveSerieV WHERE claveSerieV.EsForestal=1";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtForestal.setText(Integer.toString(rsExterno.getInt("Conteo_Sitios")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosNoForestal(String ruta) {
		String query = "Select  Count(sitio.SitioID) as Conteo_Sitios FROM SITIOS_Sitio sitio Left JOIN CAT_ClaveSerieV claveSerieV ON  claveSerieV.ClaveSerieVID=sitio.ClaveSerieV WHERE claveSerieV.EsForestal=0";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtNoForestal.setText(Integer.toString(rsExterno.getInt("Conteo_Sitios")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosArbolFuera(String ruta) {
		String query = "Select  Count(sitio.SitioID) as Conteo_Sitios FROM SITIOS_Sitio sitio where  sitio.ArbolFuera=1 ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				txtArbolFuera.setText(Integer.toString(rsExterno.getInt("Conteo_Sitios")));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSitiosConteoAccesiblesPorUPM(String ruta) {
		String query = "SELECT DISTINCT UPMID, COUNT(Sitio) as Conteo_sitios FROM SITIOS_Sitio WHERE  SitioAccesible=1 Group by UPMID";
		int uno = 0, dos = 0, tres = 0, cuatro = 0, numero = 0;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			if (sitiosAccesiblesPorConglomeradosModel.getRowCount() > 0) {
				for (int i = sitiosAccesiblesPorConglomeradosModel.getRowCount() - 1; i > -1; i--) {
					sitiosAccesiblesPorConglomeradosModel.removeRow(i);
				}

			}

			while (rsExterno.next()) {
				numero = rsExterno.getInt("Conteo_sitios");

				switch (numero) {
				case 1:
					uno++;
					break;
				case 2:
					dos++;
					break;
				case 3:
					tres++;
					break;
				case 4:
					cuatro++;
					break;
				}

				/*
				 * Model.addRow(new Object[] {
				 * rsExterno.getString(""),rsExterno.getInt("") });
				 */

			}
			sitiosAccesiblesPorConglomeradosModel.addRow(new Object[] { cuatro, "4" });
			sitiosAccesiblesPorConglomeradosModel.addRow(new Object[] { tres, "3" });
			sitiosAccesiblesPorConglomeradosModel.addRow(new Object[] { dos, "2" });
			sitiosAccesiblesPorConglomeradosModel.addRow(new Object[] { uno, "1" });

			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblSitiosAccesiblesPorConglomerados.setModel(sitiosAccesiblesPorConglomeradosModel);
	}

	public void getSitiosInaccesiblesCausas(String ruta) {
		String query = "Select Distinct tipoInaccesibilidad.Tipo as Tipo_inaccesibilidad, Count(sitio.SitioID) as Conteo FROM SITIOS_Sitio sitio left Join CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad where SitioAccesible=0 GROUP BY tipoInaccesibilidad.Tipo ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (sitiosInaccesiblesModel.getRowCount() > 0) {
				for (int i = sitiosInaccesiblesModel.getRowCount() - 1; i > -1; i--) {
					sitiosInaccesiblesModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				sitiosInaccesiblesModel.addRow(new Object[] { rsExterno.getString("Tipo_inaccesibilidad"), rsExterno.getInt("Conteo") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblSitiosInaccesibles.setModel(sitiosInaccesiblesModel);
	}

	public void getSitioPorCondicionVegetacion(String ruta) {
		String query = "Select  faseSucecional.Clave as Condicion_Fase, Count(sitio.SitioID) as Conteo_Sitios FROM SITIOS_Sitio sitio Left JOIN CAT_FaseSucecional faseSucecional ON  faseSucecional.FaseSucecionalID=sitio.FaseSucecional Left JOIN CAT_ClaveSerieV claveSerieV ON  claveSerieV.ClaveSerieVID=sitio.ClaveSerieV where  claveSerieV.EsForestal=1 Group by faseSucecional.Clave Order by faseSucecional.Clave ASC";
		String fase=null;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (sitioPorCondicionModel.getRowCount() > 0) {
				for (int i = sitioPorCondicionModel.getRowCount() - 1; i > -1; i--) {
					sitioPorCondicionModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				if(rsExterno.getString("Condicion_Fase")==null){
					fase="Primario";
				}else{
					fase=rsExterno.getString("Condicion_Fase");
				}
				sitioPorCondicionModel.addRow(new Object[] { fase, rsExterno.getInt("Conteo_Sitios") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblSitioPorCondicion.setModel(sitioPorCondicionModel);
	}
}
