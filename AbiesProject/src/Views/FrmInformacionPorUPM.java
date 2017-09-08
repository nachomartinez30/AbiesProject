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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.ConfigUserConnection;
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
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmInformacionPorUPM extends JInternalFrame {
	boolean accesible_sitio_1, accesible_sitio_2, accesible_sitio_3, accesible_sitio_4;
	String googleEarth;
	String UPMElegido, sitio_1, sitio_2, sitio_3, sitio_4;
	private String configUser = "/ConfigUser.db";
	private Connection baseDatosConfig;
	private java.sql.Statement sqlConfig;
	public String ruta;
	Path currentPath = Paths.get("");
	String path = currentPath.toAbsolutePath().toString();
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
	private JTable tblEspeciesPorSitioArbolado;
	private JLabel lblUPM;
	private JList<String> lsUPM;
	Vector<String> upmTotal = new Vector<String>();
	JDesktopPane desktopPanelCentral;
	public String[] vegetacionPorSitioColumnName = { "Sitio", "Accesible", "Tipo vegetación", "Fase sucesional",
			"Conteo registros", "Conteo individuos" };
	public String[] especiesPorSitioColumnName = { "Sitio", "Entidad taxonomica", "Forma de vida" };
	public String[] especiesPorSitioSotobosqueColumnName = { "Sitio", "Entidad taxonomica", "Vigor" };
	public String[] especiesPorSitioRepobladoColumnName = { "Sitio", "Entidad taxonomica", "Vigor" };
	public String[] informacionSitioColumnName = { "Sitio", "Sitio accesible", "Tipo inaccesibilidad",
			"Descripcion inaccesibilidad", "Tipo de vegetación", "Fase sucecional", "Registros", "Individuos" };

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
	private JScrollPane scrollPaneEspeciesPorSitio;
	private JButton btnGraficas;
	private JTable tblEspeciesPorSitioSotobosque;
	private JTable tblEspeciesPorSitioRepoblado;
	private JButton btnGraficasSotobosqueRepoblado;

	String Estado, Municipio, Y, X, Accesible, TipoUPM, Exposicion, Fisiografia, Region, UPMID, Altitud,
			PendienteRepresentativa, A, B, C, D, E, F, G, H;
	private JLayeredPane layeredPane;
	private JComboBox cmbSitio;
	private JButton btnVerEnMapa;
	private JTextField txtLatitud;
	private JTextField txtLongitud;
	private JTextField txtErrorPresicion;
	private JTextField txtTipoInaccesibilidad;
	private JLabel lblS_2;
	private JLabel lblS1;
	private JLabel lblS4;
	private JLabel lblS3;
	private JTextField txtAzimut;
	private JTextField txtDistancia;
	private JTextField txtDescripcionInaccesibilidad;
	private JTextField txtCobertura;
	private JTextField txtCondicion;
	private JTextField txtTipoVegetacion;
	private JTextField txtFaseSucecional;
	private JTextField txtCuadrante1;
	private JTextField txtCuadrante2;
	private JTextField txtCuadrante4;
	private JTextField txtCuadrante3;
	private JTextField txtDistancia1;
	private JTextField txtDistancia2;
	private JTextField txtDistancia3;
	private JTextField txtDistancia4;
	private JCheckBox chckbxAccesible;
	private JCheckBox chckbxSealGps;
	private JCheckBox chckbxEvidenciaDeMuestreo;
	private JCheckBox chckbxArbolFuera;
	private JCheckBox chckbxEcotono;
	private JScrollPane scrollPane_3;
	private JTextArea txtACondicionPresente;
	private JScrollPane scrollPane_4;
	private JTextArea txtAObservaciones;
	private JScrollPane scrollPane_5;
	private JTextArea txtAExplicacionInaccesibilidad;

	String X_sitio_1, Y_sitio_1, X_sitio_2, Y_sitio_2, X_sitio_3, Y_sitio_3, X_sitio_4, Y_sitio_4;

	public FrmInformacionPorUPM(String ruta, JDesktopPane desktopPanelCentral) {
		setMinimumSize(new Dimension(1309, 888));
		setTitle("Informacion por UPM");
		setIconifiable(true);
		getGoogleEarth(configUser);
		setFrameIcon(null);
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		this.desktopPanelCentral = desktopPanelCentral;
		this.ruta = ruta;
		setUpmsTotales(ruta);
		setBounds(100, 100, 1309, 888);

		JScrollPane scrollPane = new JScrollPane();

		lsUPM = new JList(upmTotal);
		lsUPM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					arg0.consume();
					UPMElegido = lsUPM.getSelectedValue();
					upmInt = Integer.parseInt(UPMElegido);

					lblUPM.setText(UPMElegido);

					getRegistrosTotales(ruta, upmInt);
					getIndividuosTotales(ruta, upmInt);
					getInformacionUPM(ruta, upmInt);
					getEspeciesPorSitioArbolado(ruta, upmInt);
					getEspeciesPorSitioSotobosque(ruta, upmInt);
					getEspeciesPorSitioRepoblado(ruta, upmInt);
					getCoordenadasSitios(ruta, "1", UPMElegido);
					getCoordenadasSitios(ruta, "2", UPMElegido);
					getCoordenadasSitios(ruta, "3", UPMElegido);
					getCoordenadasSitios(ruta, "4", UPMElegido);
					createKml();
					sitio_1 = getSitioAccesible(ruta, "1", UPMElegido);
					sitio_2 = getSitioAccesible(ruta, "2", UPMElegido);
					sitio_3 = getSitioAccesible(ruta, "3", UPMElegido);
					sitio_4 = getSitioAccesible(ruta, "4", UPMElegido);

					if (sitio_1.equals("SI")) {
						lblS1.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS1.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_2.equals("SI")) {
						lblS_2.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS_2.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_3.equals("SI")) {
						lblS3.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS3.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_4.equals("SI")) {
						lblS4.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS4.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					getSitio(ruta, cmbSitio.getSelectedItem().toString(), UPMElegido);
					cmbSitio.setSelectedIndex(0);
					getInformacionGralSitio(ruta, upmInt);

					btnVerEnMapa.setEnabled(true);
				}
			}
		});
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
					UPMElegido = lsUPM.getSelectedValue();
					upmInt = Integer.parseInt(UPMElegido);

					lblUPM.setText(UPMElegido);

					getRegistrosTotales(ruta, upmInt);
					getIndividuosTotales(ruta, upmInt);
					getInformacionUPM(ruta, upmInt);
					getEspeciesPorSitioArbolado(ruta, upmInt);
					getEspeciesPorSitioSotobosque(ruta, upmInt);
					getEspeciesPorSitioRepoblado(ruta, upmInt);
					getCoordenadasSitios(ruta, "1", UPMElegido);
					getCoordenadasSitios(ruta, "2", UPMElegido);
					getCoordenadasSitios(ruta, "3", UPMElegido);
					getCoordenadasSitios(ruta, "4", UPMElegido);
					createKml();
					sitio_1 = getSitioAccesible(ruta, "1", UPMElegido);
					sitio_2 = getSitioAccesible(ruta, "2", UPMElegido);
					sitio_3 = getSitioAccesible(ruta, "3", UPMElegido);
					sitio_4 = getSitioAccesible(ruta, "4", UPMElegido);

					if (sitio_1.equals("SI")) {
						lblS1.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS1.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_2.equals("SI")) {
						lblS_2.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS_2.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_3.equals("SI")) {
						lblS3.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS3.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					if (sitio_4.equals("SI")) {
						lblS4.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_accesible.png")));
					} else {
						lblS4.setIcon(
								new ImageIcon(FrmInformacionPorUPM.class.getResource("/Icons/sitio_inaccesible.png")));
					}
					getSitio(ruta, cmbSitio.getSelectedItem().toString(), UPMElegido);
					cmbSitio.setSelectedIndex(0);
					getInformacionGralSitio(ruta, upmInt);

					btnVerEnMapa.setEnabled(true);
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
		
				layeredPane = new JLayeredPane();
				tabbedPane.addTab("Sitio", null, layeredPane, null);
				
						cmbSitio = new JComboBox();
						cmbSitio.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								getSitio(ruta, cmbSitio.getSelectedItem().toString(), UPMElegido);
							}
						});
						
								cmbSitio.setForeground(Color.ORANGE);
								cmbSitio.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
								
										JLabel lblSitio = new JLabel("Sitio");
										lblSitio.setFont(new Font("Dialog", Font.BOLD, 15));
										lblSitio.setForeground(Color.ORANGE);
										
												chckbxSealGps = new JCheckBox("Se\u00F1al GPS");
												
														JLabel lblCoordenadasLatitud = new JLabel("Coordenadas latitud");
														
																txtLatitud = new JTextField();
																txtLatitud.addFocusListener(new FocusAdapter() {
																	@Override
																	public void focusGained(FocusEvent arg0) {
																		txtLatitud.selectAll();
																	}
																});
																txtLatitud.setFont(new Font("Dialog", Font.PLAIN, 12));
																txtLatitud.setColumns(10);
																
																		txtLongitud = new JTextField();
																		txtLongitud.addFocusListener(new FocusAdapter() {
																			@Override
																			public void focusGained(FocusEvent arg0) {
																				txtLongitud.selectAll();
																			}
																		});
																		txtLongitud.setFont(new Font("Dialog", Font.PLAIN, 12));
																		txtLongitud.setColumns(10);
																		
																				JLabel label = new JLabel("Coordenadas longitud");
																				
																						JLabel lblEPresicin = new JLabel("E. presici\u00F3n");
																						
																								txtErrorPresicion = new JTextField();
																								txtErrorPresicion.addFocusListener(new FocusAdapter() {
																									@Override
																									public void focusGained(FocusEvent arg0) {
																										txtErrorPresicion.selectAll();
																									}
																								});
																								txtErrorPresicion.setFont(new Font("Dialog", Font.PLAIN, 12));
																								txtErrorPresicion.setColumns(10);
																								
																										chckbxEvidenciaDeMuestreo = new JCheckBox("Evidencia de muestreo");
																										
																												JLabel lblTipoInaccesibilidad = new JLabel("Tipo inaccesibilidad");
																												
																														txtTipoInaccesibilidad = new JTextField();
																														txtTipoInaccesibilidad.addFocusListener(new FocusAdapter() {
																															@Override
																															public void focusGained(FocusEvent arg0) {
																																txtTipoInaccesibilidad.selectAll();
																															}
																														});
																														txtTipoInaccesibilidad.setFont(new Font("Dialog", Font.PLAIN, 12));
																														txtTipoInaccesibilidad.setColumns(10);
																														
																																chckbxAccesible = new JCheckBox("Accesible");
																																chckbxAccesible.setForeground(Color.ORANGE);
																																chckbxAccesible.setFont(new Font("Dialog", Font.BOLD, 15));
																																
																																		JSeparator separator_6 = new JSeparator();
																																		separator_6.setOrientation(SwingConstants.VERTICAL);
																																		
																																				JLabel lblAzimut = new JLabel("Azimut");
																																				
																																						txtAzimut = new JTextField();
																																						txtAzimut.addFocusListener(new FocusAdapter() {
																																							@Override
																																							public void focusGained(FocusEvent arg0) {
																																								txtAzimut.selectAll();
																																							}
																																						});
																																						txtAzimut.setFont(new Font("Dialog", Font.PLAIN, 12));
																																						txtAzimut.setColumns(10);
																																						
																																								JLabel lblDistancia = new JLabel("Distancia");
																																								
																																										txtDistancia = new JTextField();
																																										txtDistancia.addFocusListener(new FocusAdapter() {
																																											@Override
																																											public void focusGained(FocusEvent arg0) {
																																												txtDistancia.selectAll();
																																											}
																																										});
																																										txtDistancia.setFont(new Font("Dialog", Font.PLAIN, 12));
																																										txtDistancia.setColumns(10);
																																										
																																												JLabel lblExplicacion = new JLabel("Explicaci\u00F3n:");
																																												
																																														JLabel lblDescripcion = new JLabel("Descripcion:");
																																														
																																																txtDescripcionInaccesibilidad = new JTextField();
																																																txtDescripcionInaccesibilidad.addFocusListener(new FocusAdapter() {
																																																	@Override
																																																	public void focusGained(FocusEvent arg0) {
																																																		txtDescripcionInaccesibilidad.selectAll();
																																																	}
																																																});
																																																txtDescripcionInaccesibilidad.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																txtDescripcionInaccesibilidad.setColumns(10);
																																																
																																																		JLabel lblCoberturaForestal = new JLabel("Cobertura");
																																																		
																																																				txtCobertura = new JTextField();
																																																				txtCobertura.addFocusListener(new FocusAdapter() {
																																																					@Override
																																																					public void focusGained(FocusEvent arg0) {
																																																						txtCobertura.selectAll();
																																																					}
																																																				});
																																																				txtCobertura.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																				txtCobertura.setColumns(10);
																																																				
																																																						JLabel label_1 = new JLabel("Condici\u00F3n");
																																																						
																																																								txtCondicion = new JTextField();
																																																								txtCondicion.addFocusListener(new FocusAdapter() {
																																																									@Override
																																																									public void focusGained(FocusEvent arg0) {
																																																										txtCondicion.selectAll();
																																																									}
																																																								});
																																																								txtCondicion.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																								txtCondicion.setColumns(10);
																																																								
																																																										JLabel label_2 = new JLabel("Tipo de vegetaci\u00F3n");
																																																										
																																																												txtTipoVegetacion = new JTextField();
																																																												txtTipoVegetacion.addFocusListener(new FocusAdapter() {
																																																													@Override
																																																													public void focusGained(FocusEvent arg0) {
																																																														txtTipoVegetacion.selectAll();
																																																													}
																																																												});
																																																												txtTipoVegetacion.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																												txtTipoVegetacion.setColumns(10);
																																																												
																																																														JLabel lblFaseSucecional = new JLabel("Fase sucecional");
																																																														
																																																																txtFaseSucecional = new JTextField();
																																																																txtFaseSucecional.addFocusListener(new FocusAdapter() {
																																																																	@Override
																																																																	public void focusGained(FocusEvent arg0) {
																																																																		txtFaseSucecional.selectAll();
																																																																	}
																																																																});
																																																																txtFaseSucecional.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																txtFaseSucecional.setColumns(10);
																																																																
																																																																		chckbxArbolFuera = new JCheckBox("Arbol fuera");
																																																																		
																																																																				chckbxEcotono = new JCheckBox("Ecotono");
																																																																				
																																																																						JLabel label_3 = new JLabel("Condicion presente en campo");
																																																																						
																																																																								JLabel label_4 = new JLabel("Observaciones:");
																																																																								
																																																																										JLabel lblCueadrante = new JLabel("Cuadrante 1");
																																																																										
																																																																												txtCuadrante1 = new JTextField();
																																																																												txtCuadrante1.addFocusListener(new FocusAdapter() {
																																																																													@Override
																																																																													public void focusGained(FocusEvent arg0) {
																																																																														txtCuadrante1.selectAll();
																																																																													}
																																																																												});
																																																																												txtCuadrante1.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																												txtCuadrante1.setColumns(10);
																																																																												
																																																																														txtCuadrante2 = new JTextField();
																																																																														txtCuadrante2.addFocusListener(new FocusAdapter() {
																																																																															@Override
																																																																															public void focusGained(FocusEvent arg0) {
																																																																																txtCuadrante2.selectAll();
																																																																															}
																																																																														});
																																																																														txtCuadrante2.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																														txtCuadrante2.setColumns(10);
																																																																														
																																																																																JLabel label_5 = new JLabel("Cuadrante 2");
																																																																																
																																																																																		txtCuadrante4 = new JTextField();
																																																																																		txtCuadrante4.addFocusListener(new FocusAdapter() {
																																																																																			@Override
																																																																																			public void focusGained(FocusEvent arg0) {
																																																																																				txtCuadrante4.selectAll();
																																																																																			}
																																																																																		});
																																																																																		txtCuadrante4.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																		txtCuadrante4.setColumns(10);
																																																																																		
																																																																																				JLabel label_6 = new JLabel("Cuadrante 4");
																																																																																				
																																																																																						JLabel label_7 = new JLabel("Cuadrante 3");
																																																																																						
																																																																																								txtCuadrante3 = new JTextField();
																																																																																								txtCuadrante3.addFocusListener(new FocusAdapter() {
																																																																																									@Override
																																																																																									public void focusGained(FocusEvent arg0) {
																																																																																										txtCuadrante3.selectAll();
																																																																																									}
																																																																																								});
																																																																																								txtCuadrante3.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																								txtCuadrante3.setColumns(10);
																																																																																								
																																																																																										JLabel label_8 = new JLabel("Distancia 1");
																																																																																										
																																																																																												txtDistancia1 = new JTextField();
																																																																																												txtDistancia1.addFocusListener(new FocusAdapter() {
																																																																																													@Override
																																																																																													public void focusGained(FocusEvent arg0) {
																																																																																														txtDistancia1.selectAll();
																																																																																													}
																																																																																												});
																																																																																												txtDistancia1.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																												txtDistancia1.setColumns(10);
																																																																																												
																																																																																														txtDistancia2 = new JTextField();
																																																																																														txtDistancia2.addFocusListener(new FocusAdapter() {
																																																																																															@Override
																																																																																															public void focusGained(FocusEvent arg0) {
																																																																																																txtDistancia2.selectAll();
																																																																																															}
																																																																																														});
																																																																																														txtDistancia2.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																														txtDistancia2.setColumns(10);
																																																																																														
																																																																																																JLabel label_9 = new JLabel("Distancia 2");
																																																																																																
																																																																																																		JLabel label_10 = new JLabel("Distancia 3");
																																																																																																		
																																																																																																				txtDistancia3 = new JTextField();
																																																																																																				txtDistancia3.addFocusListener(new FocusAdapter() {
																																																																																																					@Override
																																																																																																					public void focusGained(FocusEvent arg0) {
																																																																																																						txtDistancia3.selectAll();
																																																																																																					}
																																																																																																				});
																																																																																																				txtDistancia3.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																																				txtDistancia3.setColumns(10);
																																																																																																				
																																																																																																						txtDistancia4 = new JTextField();
																																																																																																						txtDistancia4.addFocusListener(new FocusAdapter() {
																																																																																																							@Override
																																																																																																							public void focusGained(FocusEvent arg0) {
																																																																																																								txtDistancia4.selectAll();
																																																																																																							}
																																																																																																						});
																																																																																																						txtDistancia4.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																																						txtDistancia4.setColumns(10);
																																																																																																						
																																																																																																								JLabel label_11 = new JLabel("Distancia 4");
																																																																																																								
																																																																																																										scrollPane_3 = new JScrollPane();
																																																																																																										
																																																																																																												scrollPane_4 = new JScrollPane();
																																																																																																												
																																																																																																														scrollPane_5 = new JScrollPane();
																																																																																																														
																																																																																																																txtAExplicacionInaccesibilidad = new JTextArea();
																																																																																																																txtAExplicacionInaccesibilidad.addFocusListener(new FocusAdapter() {
																																																																																																																	@Override
																																																																																																																	public void focusGained(FocusEvent arg0) {
																																																																																																																		txtAExplicacionInaccesibilidad.selectAll();
																																																																																																																	}
																																																																																																																});
																																																																																																																txtAExplicacionInaccesibilidad.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																																																scrollPane_5.setViewportView(txtAExplicacionInaccesibilidad);
																																																																																																																
																																																																																																																		txtAObservaciones = new JTextArea();
																																																																																																																		txtAObservaciones.addFocusListener(new FocusAdapter() {
																																																																																																																			@Override
																																																																																																																			public void focusGained(FocusEvent arg0) {
																																																																																																																				txtAObservaciones.selectAll();
																																																																																																																			}
																																																																																																																		});
																																																																																																																		txtAObservaciones.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																																																		scrollPane_4.setViewportView(txtAObservaciones);
																																																																																																																		
																																																																																																																				txtACondicionPresente = new JTextArea();
																																																																																																																				txtACondicionPresente.addFocusListener(new FocusAdapter() {
																																																																																																																					@Override
																																																																																																																					public void focusGained(FocusEvent arg0) {
																																																																																																																						txtACondicionPresente.selectAll();
																																																																																																																					}
																																																																																																																				});
																																																																																																																				txtACondicionPresente.setFont(new Font("Dialog", Font.PLAIN, 12));
																																																																																																																				scrollPane_3.setViewportView(txtACondicionPresente);
																																																																																																																				GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
																																																																																																																				gl_layeredPane.setHorizontalGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																						.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup().addGap(386)
																																																																																																																								.addComponent(lblSitio, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(18)
																																																																																																																								.addComponent(cmbSitio, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE).addGap(78)
																																																																																																																								.addComponent(chckbxAccesible, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGap(40))
																																																																																																																						.addGroup(gl_layeredPane.createSequentialGroup().addGap(12).addComponent(chckbxSealGps).addGap(52)
																																																																																																																								.addComponent(chckbxEvidenciaDeMuestreo).addGap(168).addComponent(lblCoordenadasLatitud)
																																																																																																																								.addGap(12).addComponent(txtLatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																										GroupLayout.PREFERRED_SIZE))
																																																																																																																						.addGroup(gl_layeredPane.createSequentialGroup().addGap(12)
																																																																																																																								.addComponent(lblAzimut, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addGap(18)
																																																																																																																								.addComponent(txtAzimut, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE).addGap(34)
																																																																																																																								.addComponent(lblDistancia, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGap(18)
																																																																																																																								.addComponent(txtDistancia, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGap(158)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING).addComponent(label)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(58).addComponent(lblEPresicin)))
																																																																																																																								.addGap(12)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addComponent(txtLongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addComponent(txtErrorPresicion, GroupLayout.PREFERRED_SIZE, 40,
																																																																																																																												GroupLayout.PREFERRED_SIZE)))
																																																																																																																						.addGroup(gl_layeredPane.createSequentialGroup().addGap(27)
																																																																																																																								.addComponent(lblCoberturaForestal, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGap(7)
																																																																																																																								.addComponent(txtCobertura, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
																																																																																																																						.addGroup(gl_layeredPane.createSequentialGroup().addGap(27).addGroup(gl_layeredPane
																																																																																																																								.createParallelGroup(Alignment.LEADING)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(7).addComponent(txtCondicion, GroupLayout.PREFERRED_SIZE, 216,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(9).addComponent(txtTipoVegetacion, GroupLayout.PREFERRED_SIZE, 158,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(lblFaseSucecional, GroupLayout.PREFERRED_SIZE, 122,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(9).addComponent(txtFaseSucecional, GroupLayout.PREFERRED_SIZE, 158,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(chckbxArbolFuera, GroupLayout.PREFERRED_SIZE, 116,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(57).addComponent(chckbxEcotono, GroupLayout.PREFERRED_SIZE, 116,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE).addGap(7))
																																																																																																																								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE).addGap(7))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(lblCueadrante, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3)
																																																																																																																										.addComponent(txtCuadrante1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(31)
																																																																																																																										.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3).addComponent(txtDistancia1, GroupLayout.PREFERRED_SIZE, 55,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3)
																																																																																																																										.addComponent(txtCuadrante2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(31)
																																																																																																																										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3).addComponent(txtDistancia2, GroupLayout.PREFERRED_SIZE, 55,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3)
																																																																																																																										.addComponent(txtCuadrante3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(31)
																																																																																																																										.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3).addComponent(txtDistancia3, GroupLayout.PREFERRED_SIZE, 55,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3)
																																																																																																																										.addComponent(txtCuadrante4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(31)
																																																																																																																										.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(3).addComponent(txtDistancia4, GroupLayout.PREFERRED_SIZE, 55,
																																																																																																																												GroupLayout.PREFERRED_SIZE)))
																																																																																																																								.addGap(60)
																																																																																																																								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGap(28)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(4).addComponent(
																																																																																																																												lblTipoInaccesibilidad, GroupLayout.PREFERRED_SIZE, 216,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(4).addComponent(
																																																																																																																												txtTipoInaccesibilidad, GroupLayout.PREFERRED_SIZE, 216,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(4).addComponent(lblDescripcion,
																																																																																																																												GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(4).addComponent(
																																																																																																																												txtDescripcionInaccesibilidad, GroupLayout.PREFERRED_SIZE, 216,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																										.addComponent(lblExplicacion, GroupLayout.PREFERRED_SIZE, 274,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
																																																																																																																								.addGap(12)));
																																																																																																																				gl_layeredPane.setVerticalGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING).addGroup(gl_layeredPane
																																																																																																																						.createSequentialGroup().addGap(8)
																																																																																																																						.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(4).addComponent(lblSitio,
																																																																																																																										GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(cmbSitio,
																																																																																																																										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addComponent(chckbxAccesible, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
																																																																																																																						.addGap(26)
																																																																																																																						.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(chckbxSealGps))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(2)
																																																																																																																										.addComponent(chckbxEvidenciaDeMuestreo))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(lblCoordenadasLatitud))
																																																																																																																								.addComponent(txtLatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																										GroupLayout.PREFERRED_SIZE))
																																																																																																																						.addGap(4)
																																																																																																																						.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(16).addComponent(lblAzimut))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(14).addComponent(txtAzimut,
																																																																																																																										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(16).addComponent(lblDistancia))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(14).addComponent(txtDistancia,
																																																																																																																										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addComponent(label).addGap(18)
																																																																																																																										.addComponent(lblEPresicin))
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup()
																																																																																																																										.addComponent(txtLongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(12).addComponent(txtErrorPresicion, GroupLayout.PREFERRED_SIZE,
																																																																																																																												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																																																																																																																						.addGap(22)
																																																																																																																						.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(lblCoberturaForestal))
																																																																																																																								.addComponent(txtCobertura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																										GroupLayout.PREFERRED_SIZE))
																																																																																																																						.addGap(5)
																																																																																																																						.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING).addGroup(gl_layeredPane
																																																																																																																								.createSequentialGroup().addGap(5)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_1))
																																																																																																																										.addComponent(txtCondicion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(12)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_2))
																																																																																																																										.addComponent(txtTipoVegetacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(10)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2)
																																																																																																																												.addComponent(lblFaseSucecional))
																																																																																																																										.addComponent(txtFaseSucecional, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(32)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING).addComponent(chckbxArbolFuera)
																																																																																																																										.addComponent(chckbxEcotono))
																																																																																																																								.addGap(18).addComponent(label_3).addGap(6)
																																																																																																																								.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE).addGap(12)
																																																																																																																								.addComponent(label_4).addGap(6)
																																																																																																																								.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE).addGap(31)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(lblCueadrante))
																																																																																																																										.addComponent(txtCuadrante1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_8))
																																																																																																																										.addComponent(txtDistancia1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(5)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_5))
																																																																																																																										.addComponent(txtCuadrante2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_9))
																																																																																																																										.addComponent(txtDistancia2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(6)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_7))
																																																																																																																										.addComponent(txtCuadrante3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_10))
																																																																																																																										.addComponent(txtDistancia3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(5)
																																																																																																																								.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_6))
																																																																																																																										.addComponent(txtCuadrante4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGroup(gl_layeredPane.createSequentialGroup().addGap(2).addComponent(label_11))
																																																																																																																										.addComponent(txtDistancia4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																																																																																																																												GroupLayout.PREFERRED_SIZE))
																																																																																																																								.addGap(47))
																																																																																																																								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addGroup(gl_layeredPane.createSequentialGroup().addGap(7).addComponent(lblTipoInaccesibilidad)
																																																																																																																										.addGap(12)
																																																																																																																										.addComponent(txtTipoInaccesibilidad, GroupLayout.PREFERRED_SIZE,
																																																																																																																												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(12).addComponent(lblDescripcion).addGap(3)
																																																																																																																										.addComponent(txtDescripcionInaccesibilidad, GroupLayout.PREFERRED_SIZE,
																																																																																																																												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																																																																																																										.addGap(77).addComponent(lblExplicacion).addGap(6)
																																																																																																																										.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
																																																																																																																										.addGap(216)))));
																																																																																																																				layeredPane.setLayout(gl_layeredPane);

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
		tblEspeciesPorSitioArbolado.setAutoCreateRowSorter(true);
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
		btnGraficas.setMnemonic('g');
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
		btnGraficasSotobosqueRepoblado.setMnemonic('g');
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
		tblEspeciesPorSitioSotobosque.setAutoCreateRowSorter(true);
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
		tblEspeciesPorSitioRepoblado.setAutoCreateRowSorter(true);
		tblEspeciesPorSitioRepoblado.setBackground(Color.DARK_GRAY);
		tblEspeciesPorSitioRepoblado.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblEspeciesPorSitioRepoblado.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		scrollPane_2.setViewportView(tblEspeciesPorSitioRepoblado);
		layeredPaneSotobosque.setLayout(gl_layeredPaneSotobosque);

		scrollPaneInforSitio = new JScrollPane();
		scrollPaneInforSitio.setBounds(12, 76, 399, 124);
		scrollPaneInforSitio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneInforSitio.setBackground(Color.WHITE);

		tblInformacionSItio = new JTable();
		scrollPaneInforSitio.setViewportView(tblInformacionSItio);

		lblInformacinDeSitios = new JLabel("Informaci\u00F3n general de sitios");
		lblInformacinDeSitios.setBounds(0, 38, 411, 20);
		lblInformacinDeSitios.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDeSitios.setFont(new Font("Dialog", Font.BOLD, 16));

		btnVerEnMapa = new JButton("Ver en mapa");
		btnVerEnMapa.setBounds(12, 211, 88, 24);
		btnVerEnMapa.setEnabled(false);
		btnVerEnMapa.setMnemonic('v');
		btnVerEnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String UPM_earth = lsUPM.getSelectedValue();
					Runtime.getRuntime().exec(new String[] { googleEarth, path + "\\doc.kml" });
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});
		panel_2.setLayout(null);

		lblS1 = new JLabel("");
		lblS1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblS1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getSitio(ruta, "1", UPMElegido);
				cmbSitio.setSelectedItem("1");
			}
		});
		lblS1.setBorder(null);
		lblS1.setBounds(179, 342, 95, 95);
		lblS1.setPreferredSize(new Dimension(42, 16));
		panel_2.add(lblS1);
		panel_2.add(btnVerEnMapa);
		panel_2.add(scrollPaneInforSitio);
		panel_2.add(lblInformacinDeSitios);

		lblS3 = new JLabel("");
		lblS3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblS3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getSitio(ruta, "3", UPMElegido);
				cmbSitio.setSelectedItem("3");
			}
		});
		lblS3.setPreferredSize(new Dimension(42, 16));
		lblS3.setBorder(null);
		lblS3.setBounds(275, 442, 95, 95);
		panel_2.add(lblS3);

		lblS4 = new JLabel("");
		lblS4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblS4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getSitio(ruta, "4", UPMElegido);
				cmbSitio.setSelectedItem("4");
			}
		});
		lblS4.setPreferredSize(new Dimension(42, 16));
		lblS4.setBorder(null);
		lblS4.setBounds(85, 442, 95, 95);
		panel_2.add(lblS4);

		lblS_2 = new JLabel("");
		lblS_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblS_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getSitio(ruta, "2", UPMElegido);
				cmbSitio.setSelectedItem("2");
			}
		});
		lblS_2.setPreferredSize(new Dimension(42, 16));
		lblS_2.setBorder(null);
		lblS_2.setBounds(179, 211, 95, 95);
		panel_2.add(lblS_2);
		getContentPane().setLayout(groupLayout);
		// scrollPaneEast.setViewportView(scrollPaneInforSitio);

	}

	public void createKml() {
		String kml = "<?xml version='1.0' encoding='UTF-8'?> <kml xmlns='http://www.opengis.net/kml/2.2' xmlns:gx='http://www.google.com/kml/ext/2.2' xmlns:kml='http://www.opengis.net/kml/2.2' xmlns:atom='http://www.w3.org/2005/Atom'> <Document> 	<name>"
				+ UPMID
				+ ".kmz</name> <NetworkLink>   <name>UPM</name>     <visibility>0</visibility>     <snippet>Check/refresh NetworkLink to draw circle</snippet>     <description><![CDATA[First move to location where you want a circle drawn then check NetworkLink in Google Earth's Places panel to create the circle with its center at center of current view point. <P>Manually refresh to redraw.<P> Powered by <a href=\"http://kml4earth.appspot.com/\">Kml4Earth</a>]]></description>     <balloonVisibility xmlns='http://www.google.com/kml/ext/2.2'>1</balloonVisibility>     <Link>       <href>http://kml4earth.appspot.com:80/circle.gsp?color=ff00ff00&amp;width=2</href>       <viewRefreshMode>onRequest</viewRefreshMode>       <viewFormat>LookAt=[lookatLon],[lookatLat]&amp;LonLat=[lookatTerrainLon],[lookatTerrainLat]</viewFormat>       <httpQuery>meters=112.9&amp;desc=radius+56.4+meters</httpQuery>     </Link>   </NetworkLink>  	<Style id='IconStyle04018'> 		<IconStyle> 			<scale>0.25</scale> 			<Icon> 				<href>files/Layer0_Symbol_dc4f048_0.png</href> 			</Icon> 		</IconStyle> 		<LabelStyle> 			<color>00000000</color> 			<scale>0</scale> 		</LabelStyle> 		<PolyStyle> 			<color>ff000000</color> 			<outline>0</outline> 		</PolyStyle> 	</Style> 	<Placemark id='ID_04018'> 		<name>"
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
				+ X + "," + Y
				+ ",0</coordinates> 		</Point> <NetworkLink>     <name>Circle generator radius 4 meters</name>     <snippet></snippet>     <description><![CDATA[Circle auto-refreshes itself whenever the view changes.<P> To change circle options visit <a href=\"http://kml4earth.appspot.com/circlegen.html?auto=1\">web site</a>.<P> Powered by <a href=\"http://kml4earth.appspot.com/\">Kml4Earth</a>]]></description>     <Link>       <href>https://kml4earth.appspot.com:443/circle.gsp?color=ff00ff00&amp;width=2</href>       <viewRefreshMode>onStop</viewRefreshMode>       <viewRefreshTime>2</viewRefreshTime>       <viewFormat>LookAt=[lookatLon],[lookatLat]&amp;LonLat=[lookatTerrainLon],[lookatTerrainLat]</viewFormat>       <httpQuery>meters=8.0&amp;desc=radius+4+meters</httpQuery>     </Link>   </NetworkLink>   \r\n"
				+ "</Placemark>" + "<Folder>";

		if (accesible_sitio_1 == true) {
			kml = kml + "<Placemark>" + "<name>Sitio 1</name>" + "<Point>" + "<coordinates>" + X_sitio_1 + ","
					+ Y_sitio_1 + ",0</coordinates>" + "</Point>" + "</Placemark>";
		}

		if (accesible_sitio_2 == true) {
			kml = kml + "<Placemark>" + "<name>Sitio 2</name>" + "<Point>" + "<coordinates>" + X_sitio_2 + ","
					+ Y_sitio_2 + ",0</coordinates>" + "</Point>" + "</Placemark>";
		}
		if (accesible_sitio_3 == true) {
			kml = kml + "<Placemark>" + "<name>Sitio 3</name>" + "<Point>" + "<coordinates>" + X_sitio_3 + ","
					+ Y_sitio_3 + ",0</coordinates>" + "</Point>" + "</Placemark>";
		}
		if (accesible_sitio_4 == true) {
			kml = kml + "<Placemark>" + "<name>Sitio 4</name>" + "<Point>" + "<coordinates>" + X_sitio_4 + ","
					+ Y_sitio_4 + ",0</coordinates>" + "</Point>" + "</Placemark>";
		}
		kml = kml + "</Folder> </Document>" + "</kml>";

		//System.out.println("KML\n" + kml);
		Writer writer = null;

		try {

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "\\doc.kml"), "utf-8"));
			writer.write(kml);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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

	public void getGoogleEarth(String ruta) {
		String query = "SELECT google_earth FROM configUserAbies";

		this.baseDatosConfig = ConfigUserConnection.getConnection(ruta);
		// System.out.println(ruta);
		try {
			sqlConfig = baseDatosConfig.createStatement();
			ResultSet rsConfig = sqlConfig.executeQuery(query);

			while (rsConfig.next()) {
				googleEarth = rsConfig.getString("google_earth");
			}
			// System.out.println("Inf_CONG googleEarth="+googleEarth);
			baseDatosConfig.close();
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
				+ upmid + " AND sitio.Sitio=1";
		System.out.println("infor UPM\t"+query);
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				UPMID = Integer.toString(rsExterno.getInt("UPMID"));
				Altitud = Integer.toString(rsExterno.getInt("Altitud"));
				PendienteRepresentativa = Integer.toString(rsExterno.getInt("PendienteRepresentativa"));
				A = Integer.toString(rsExterno.getInt("A"));
				B = Integer.toString(rsExterno.getInt("B"));
				C = Integer.toString(rsExterno.getInt("C"));
				D = Integer.toString(rsExterno.getInt("D"));
				E = Integer.toString(rsExterno.getInt("E"));
				F = Integer.toString(rsExterno.getInt("F"));
				G = Integer.toString(rsExterno.getInt("G"));
				H = Integer.toString(rsExterno.getInt("H"));
				Estado = rsExterno.getString("Estado");
				Municipio = rsExterno.getString("Municipio");
				Y = rsExterno.getString("Y");
				X = rsExterno.getString("X");
				Accesible = rsExterno.getString("Accesible");
				TipoUPM = rsExterno.getString("TipoUPM");
				Exposicion = rsExterno.getString("Exposicion");
				Fisiografia = rsExterno.getString("Fisiografia");
				Region = rsExterno.getString("Region");

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
			if (especiesPorSitioSotobosqueModel.getRowCount() > 0) {
				for (int i = especiesPorSitioSotobosqueModel.getRowCount() - 1; i > -1; i--) {
					especiesPorSitioSotobosqueModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {

				especiesPorSitioSotobosqueModel.addRow(new Object[] { rsExterno.getString("sitio"),
						rsExterno.getString("Entidad"), rsExterno.getString("Vigor") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblEspeciesPorSitioSotobosque.setModel(especiesPorSitioSotobosqueModel);
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

	public String getSitioAccesible(String ruta, String sitio, String UPMID) {
		String sitio_accesible = "";
		String query = " SELECT CASE sitio.SitioAccesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SitioAccesible FROM SITIOS_Sitio sitio WHERE sitio.Sitio="
				+ sitio + " AND sitio.UPMID=" + UPMID;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			// System.out.println(query);
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			if (informacionSitioModel.getRowCount() > 0) {
				for (int i = informacionSitioModel.getRowCount() - 1; i > -1; i--) {
					informacionSitioModel.removeRow(i);
				}
			}
			while (rsExterno.next()) {
				sitio_accesible = rsExterno.getString("SitioAccesible");
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sitio_accesible;
	}

	public void getInformacionGralSitio(String ruta, int upmid) {
		String query = "SELECT sitio.SitioID, sitio.Sitio, CASE sitio.SitioAccesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SitioAccesible, tipoInaccesibilidad.Tipo AS TipoInaccesibilidad, tipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad, claveSerieV.Clave, faseSucecional.Clave  AS FaseSucecional,  arbolado.Consecutivo as No_registros, arbolado.NoIndividuo AS Individuo  from SITIOS_Sitio sitio LEFT JOIN TAXONOMIA_Arbolado arbolado ON sitio.SitioID=arbolado.SitioID and sitio.UPMID=arbolado.UPMID  LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad WHERE sitio.UPMID="
				+ upmid + " GROUP BY sitio.UPMID, sitio.SitioID ORDER BY sitio.sitio ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			// System.out.println(query);
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
						rsExterno.getString("FaseSucecional"), rsExterno.getString("No_registros"),
						rsExterno.getString("Individuo") });
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tblInformacionSItio.setModel(informacionSitioModel);
		alignCellsTables(informacionSitioModel, tblInformacionSItio);
	}

	public void getSitio(String ruta, String sitio, String UPMID) {
		String query = "SELECT sitio.Sitio, sitio.SenialGPS SenialGPS, sitio.GradosLongitud || ' '||sitio.MinutosLongitud || ' '||sitio.SegundosLongitud||' ' as CoordenadasLongitud, sitio.GradosLatitud || ' '||sitio.MinutosLatitud || ' '||sitio.SegundosLatitud||' ' as CoordenadasLatitud, sitio.ErrorPresicion, sitio.EvidenciaMuestreo EvidenciaMuestreo, sitio.Azimut, sitio.Distancia, sitio.SitioAccesible SitioAccesible, tipoInaccesibilidad.Tipo AS TipoInaccesibilidad, tipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad, sitio.ExplicacionInaccesibilidad, CASE claveSerieV.EsForestal WHEN 1 THEN 'FORESTAL' WHEN 0 THEN 'NO FORESTAL' END CoberturaForestal, CASE sitio.Condicion WHEN 1 THEN 'PRIMARIO' WHEN 0 THEN 'SECUNDARIA' END Condicion, claveSerieV.Clave, faseSucecional.Clave  AS FaseSucecional, sitio.ArbolFuera ArbolFuera, sitio.CondicionEcotono CondicionEcotono, sitio.Ecotono, sitio.CondicionPresenteCampo, sitio.Observaciones, sitio.HipsometroBrujula, sitio.CintaClinometroBrujula, sitio.Cuadrante1, sitio.Cuadrante2, sitio.Cuadrante3, sitio.Cuadrante4, sitio.Distancia1, sitio.Distancia2, sitio.Distancia3, sitio.Distancia4 from SITIOS_Sitio sitio LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad WHERE sitio.UPMID="
				+ UPMID + " AND sitio.Sitio=" + sitio + " GROUP BY sitio.UPMID, sitio.SitioID ORDER BY sitio.UPMID ";
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			// System.out.println(query);
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {

				txtLongitud.setText(rsExterno.getString("CoordenadasLongitud"));
				txtLatitud.setText(rsExterno.getString("CoordenadasLatitud"));
				txtErrorPresicion.setText(rsExterno.getString("ErrorPresicion"));
				txtAzimut.setText(rsExterno.getString("Azimut"));
				txtDistancia.setText(rsExterno.getString("Distancia"));
				txtTipoInaccesibilidad.setText(rsExterno.getString("TipoInaccesibilidad"));
				txtDescripcionInaccesibilidad.setText(rsExterno.getString("DescripcionInaccesibilidad"));
				txtAExplicacionInaccesibilidad.setText(rsExterno.getString("ExplicacionInaccesibilidad"));
				txtTipoVegetacion.setText(rsExterno.getString("Clave"));
				txtFaseSucecional.setText(rsExterno.getString("FaseSucecional"));
				txtACondicionPresente.setText(rsExterno.getString("CondicionPresenteCampo"));
				txtAObservaciones.setText(rsExterno.getString("Observaciones"));
				txtCuadrante1.setText(rsExterno.getString("Cuadrante1"));
				txtCuadrante2.setText(rsExterno.getString("Cuadrante2"));
				txtCuadrante3.setText(rsExterno.getString("Cuadrante3"));
				txtCuadrante4.setText(rsExterno.getString("Cuadrante4"));
				txtDistancia1.setText(rsExterno.getString("Distancia1"));
				txtDistancia2.setText(rsExterno.getString("Distancia2"));
				txtDistancia3.setText(rsExterno.getString("Distancia3"));
				txtDistancia4.setText(rsExterno.getString("Distancia4"));
				txtCobertura.setText(rsExterno.getString("CoberturaForestal"));
				txtCondicion.setText(rsExterno.getString("Condicion"));

				chckbxSealGps.setSelected(rsExterno.getBoolean("SenialGPS"));
				if (chckbxSealGps.isSelected() == true) {
					txtAzimut.setText("");
					txtAzimut.setEnabled(false);
					txtDistancia.setText("");
					txtDistancia.setEnabled(false);
				} else {
					txtAzimut.setEnabled(true);
					txtDistancia.setEnabled(true);
				}
				chckbxEvidenciaDeMuestreo.setSelected(rsExterno.getBoolean("EvidenciaMuestreo"));
				chckbxAccesible.setSelected(rsExterno.getBoolean("SitioAccesible"));
				chckbxArbolFuera.setSelected(rsExterno.getBoolean("ArbolFuera"));
				chckbxEcotono.setSelected(rsExterno.getBoolean("Ecotono"));
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCoordenadasSitios(String ruta, String sitio, String UPMID) {
		String query = "SELECT sitio.SitioAccesible,sitio.Sitio, sitio.GradosLongitud || '°'||sitio.MinutosLongitud || '´'||sitio.SegundosLongitud||'´´' as CoordenadasLongitud, sitio.GradosLatitud || '°'||sitio.MinutosLatitud || '´'||sitio.SegundosLatitud||'´´' as CoordenadasLatitud, -1 * ((-1 * sitio.GradosLongitud) + (sitio.MinutosLongitud / 60.0) +  (sitio.SegundosLongitud / 3600.0)) AS X, sitio.GradosLatitud + (sitio.MinutosLatitud / 60.0) +  (sitio.SegundosLatitud / 3600.0) AS Y from  SITIOS_Sitio sitio where sitio.UPMID="
				+ UPMID + " and sitio.Sitio=" + sitio;
		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			// System.out.println(query);
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {
				switch (sitio) {
				case "1":
					accesible_sitio_1 = rsExterno.getBoolean("SitioAccesible");
					X_sitio_1 = rsExterno.getString("X");
					if (X_sitio_1.equals("0")) {
						X_sitio_1 = "";
					}
					Y_sitio_1 = rsExterno.getString("Y");
					if (Y_sitio_1.equals("0")) {
						Y_sitio_1 = "";
					}
					break;
				case "2":
					accesible_sitio_2 = rsExterno.getBoolean("SitioAccesible");
					X_sitio_2 = rsExterno.getString("X");
					if (X_sitio_2.equals("0")) {
						X_sitio_2 = "";
					}
					Y_sitio_2 = rsExterno.getString("Y");
					if (Y_sitio_2.equals("0")) {
						Y_sitio_2 = "";
					}
					break;
				case "3":
					accesible_sitio_3 = rsExterno.getBoolean("SitioAccesible");
					X_sitio_3 = rsExterno.getString("X");
					if (X_sitio_3.equals("0")) {
						X_sitio_3 = "";
					}
					Y_sitio_3 = rsExterno.getString("Y");
					if (Y_sitio_3.equals("0")) {
						Y_sitio_3 = "";
					}
					break;
				case "4":
					accesible_sitio_4 = rsExterno.getBoolean("SitioAccesible");
					X_sitio_4 = rsExterno.getString("X");
					if (X_sitio_4.equals("0")) {
						X_sitio_4 = "";
					}
					Y_sitio_4 = rsExterno.getString("Y");
					if (Y_sitio_4.equals("0")) {
						Y_sitio_4 = "";
					}
					break;
				default:
					System.err.println("error al obtener coordenadas");
					break;
				}

			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// Final
