package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;

public class FrmConversor extends JFrame {

	private JPanel contentPane;
	private JTextField txtLatDecimal;
	private JTextField txtLongDecimal;
	private JTextField txtGradosLong;
	private JTextField txtGradosLat;
	private JTextField txtMinutosLat;
	private JTextField txtSegundosLat;
	private JTextField txtMinutosLong;
	private JTextField txtSegundosLong;
	private JTable tblReusltados;
	private JRadioButton rdbtnDecimales;
	private JButton btnAdjuntar;

	public String ruta = "";
	private JTextField txtRuta;
	private JCheckBox chckbxAdjuntarArchivo;

	public String[] coordenadasColumnNameSexa = { "UPMID", "Gra. Y", "Min. Y", "Seg. Y", "Gra. X", "Min. X", "Seg. X",
			"Dec. Y", "Dec. X" };
	public DefaultTableModel coordenadasModel = new DefaultTableModel(null, coordenadasColumnNameSexa);
	public DefaultTableModel coordenadasModelLimpio = new DefaultTableModel();

	
	public FrmConversor() {
		setTitle("Conversor coordenadas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConversor.class.getResource("/Icons/g5296.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);

		JMenu mnDocumentacin = new JMenu("Documentaci\u00F3n");
		menuBar.add(mnDocumentacin);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAcercaDeCoordenadas acerca=new FrmAcercaDeCoordenadas();
				acerca.setVisible(true);
			}
		});
		mnDocumentacin.add(mntmAcercaDe);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		JRadioButton rdbtnGeograficas = new JRadioButton("Sexa.=>Dec.");
		rdbtnGeograficas.setActionCommand("");
		rdbtnGeograficas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnGeograficas.isSelected() == true) {
					rdbtnDecimales.setSelected(false);
				}
				if (rdbtnGeograficas.isSelected() == true && chckbxAdjuntarArchivo.isSelected() == false) {
					rdbtnDecimales.setSelected(false);
					txtLatDecimal.setEnabled(false);
					txtLongDecimal.setEnabled(false);
					txtGradosLat.setEnabled(true);
					txtMinutosLat.setEnabled(true);
					txtSegundosLat.setEnabled(true);
					txtGradosLong.setEnabled(true);
					txtMinutosLong.setEnabled(true);
					txtSegundosLong.setEnabled(true);
					limpiarTextBoxes();
				}
			}
		});
		rdbtnGeograficas.setSelected(true);

		rdbtnDecimales = new JRadioButton("Dec.=>Sexa.  ");
		rdbtnDecimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnDecimales.isSelected() == true) {
					rdbtnGeograficas.setSelected(false);
				}
				if (rdbtnDecimales.isSelected() == true && chckbxAdjuntarArchivo.isSelected() == false) {
					txtGradosLat.setEnabled(false);
					txtMinutosLat.setEnabled(false);
					txtSegundosLat.setEnabled(false);
					txtGradosLong.setEnabled(false);
					txtMinutosLong.setEnabled(false);
					txtSegundosLong.setEnabled(false);
					txtLatDecimal.setEnabled(true);
					txtLongDecimal.setEnabled(true);
					limpiarTextBoxes();
				}
			}
		});

		chckbxAdjuntarArchivo = new JCheckBox("Adjuntar archivo");
		chckbxAdjuntarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxAdjuntarArchivo.isSelected() == true) {
					btnAdjuntar.setEnabled(true);
					txtLatDecimal.setEnabled(false);
					txtLongDecimal.setEnabled(false);
					txtGradosLong.setEnabled(false);
					txtGradosLat.setEnabled(false);
					txtMinutosLat.setEnabled(false);
					txtSegundosLat.setEnabled(false);
					txtMinutosLong.setEnabled(false);
					txtSegundosLong.setEnabled(false);
					txtRuta.setEnabled(true);

				} else {
					btnAdjuntar.setEnabled(false);
					txtLatDecimal.setEnabled(true);
					txtLongDecimal.setEnabled(true);
					txtGradosLong.setEnabled(true);
					txtGradosLat.setEnabled(true);
					txtMinutosLat.setEnabled(true);
					txtSegundosLat.setEnabled(true);
					txtMinutosLong.setEnabled(true);
					txtSegundosLong.setEnabled(true);
					txtRuta.setEnabled(false);
				}
			}
		});

		btnAdjuntar = new JButton("Adjuntar");
		btnAdjuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarArchivoEntrada();
			}
		});
		btnAdjuntar.setMnemonic('A');
		btnAdjuntar.setEnabled(false);

		JLabel lblLatitud = new JLabel("Latitud");

		JLabel lblLongitud = new JLabel("Longitud");

		txtLatDecimal = new JTextField();
		txtLatDecimal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtLatDecimal.selectAll();
			}
		});
		txtLatDecimal.setEnabled(false);
		txtLatDecimal.setColumns(10);

		txtLongDecimal = new JTextField();
		txtLongDecimal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtLongDecimal.selectAll();
			}
		});
		txtLongDecimal.setEnabled(false);
		txtLongDecimal.setColumns(10);

		JLabel lblDecimales = new JLabel("Decimales");
		lblDecimales.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label = new JLabel("Geograficas");
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_1 = new JLabel("Latitud");

		JLabel label_2 = new JLabel("Longitud");

		txtGradosLong = new JTextField();
		txtGradosLong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtGradosLong.selectAll();
			}
		});
		txtGradosLong.setColumns(10);

		txtGradosLat = new JTextField();
		txtGradosLat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtGradosLat.selectAll();
			}
		});
		txtGradosLat.setColumns(10);

		txtMinutosLat = new JTextField();
		txtMinutosLat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMinutosLat.selectAll();
			}
		});
		txtMinutosLat.setColumns(10);

		txtSegundosLat = new JTextField();
		txtSegundosLat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSegundosLat.selectAll();
			}
		});
		txtSegundosLat.setColumns(10);

		txtMinutosLong = new JTextField();
		txtMinutosLong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMinutosLong.selectAll();
			}
		});
		txtMinutosLong.setColumns(10);

		txtSegundosLong = new JTextField();
		txtSegundosLong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSegundosLong.selectAll();
			}
		});
		txtSegundosLong.setColumns(10);

		JSeparator separator = new JSeparator();

		JScrollPane scrollPane = new JScrollPane();

		tblReusltados = new JTable();
		tblReusltados.setFillsViewportHeight(true);
		tblReusltados.setAutoCreateRowSorter(true);
		tblReusltados.setColumnSelectionAllowed(true);
		tblReusltados.setCellSelectionEnabled(true);
		tblReusltados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblReusltados.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		scrollPane.setViewportView(tblReusltados);

		JButton button = new JButton("Calcular");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sistema_coordenadas = 0;
				tblReusltados.setModel(coordenadasModelLimpio);
				if (rdbtnGeograficas.isSelected() == true) {
					sistema_coordenadas = 1;
				}
				if (rdbtnDecimales.isSelected() == true) {
					sistema_coordenadas = 2;
				}

				switch (sistema_coordenadas) {
				case 1: // Geograficas a decimal
					if (chckbxAdjuntarArchivo.isSelected() == true) {// calculo con un archivo csv
						csvGMStoDecimal(ruta);
					} else {

						double latDecimal = convertGMStoDecimal(Integer.parseInt(txtGradosLat.getText()),
								Integer.parseInt(txtMinutosLat.getText()),
								Double.parseDouble(txtSegundosLat.getText()));
						double longDecimal = convertGMStoDecimal(Integer.parseInt(txtGradosLong.getText()),
								Integer.parseInt(txtMinutosLong.getText()),
								Double.parseDouble(txtSegundosLong.getText()));
						txtLatDecimal.setText(Double.toString(latDecimal));
						txtLongDecimal.setText(Double.toString(longDecimal));
						txtLatDecimal.setEnabled(true);
						txtLongDecimal.setEnabled(true);
					}
					break;
				case 2:// Decimal a geograficas
					if (chckbxAdjuntarArchivo.isSelected() == true) {// calculo con un archivo csv
						csvDecimaltoGMSDecimal(ruta);
					} else {
						convertDecimalToGMS(txtLatDecimal.getText(), "Latitud");
						convertDecimalToGMS(txtLongDecimal.getText(), "Longitud");
						txtGradosLat.setEnabled(true);
						txtMinutosLat.setEnabled(true);
						txtSegundosLat.setEnabled(true);
						txtGradosLong.setEnabled(true);
						txtMinutosLong.setEnabled(true);
						txtSegundosLong.setEnabled(true);
					}
					break;

				default:
					break;
				}
			}
		});
		button.setMnemonic('c');

		txtRuta = new JTextField();
		txtRuta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtRuta.selectAll();
			}
		});
		txtRuta.setEnabled(false);
		txtRuta.setColumns(10);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarTextBoxes();
			}
		});
		btnLimpiar.setMnemonic('l');
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnGeograficas, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnDecimales, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(chckbxAdjuntarArchivo)
							.addGap(126))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(11)))
					.addComponent(btnAdjuntar)
					.addGap(12))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
					.addGap(12))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addComponent(lblDecimales, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addComponent(lblLatitud)
					.addGap(18)
					.addComponent(txtLatDecimal, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(txtGradosLat, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtMinutosLat, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtSegundosLat, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addComponent(lblLongitud)
					.addGap(8)
					.addComponent(txtLongDecimal, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(label_2)
					.addGap(8)
					.addComponent(txtGradosLong, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtMinutosLong, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtSegundosLong, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(272)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
					.addGap(12))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(rdbtnGeograficas)
							.addComponent(rdbtnDecimales))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(chckbxAdjuntarArchivo)
							.addGap(7)
							.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(36)
							.addComponent(btnAdjuntar)))
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDecimales)
						.addComponent(label))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(lblLatitud))
						.addComponent(txtLatDecimal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(label_1))
						.addComponent(txtGradosLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMinutosLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSegundosLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(lblLongitud))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(txtLongDecimal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(label_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(txtGradosLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtMinutosLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSegundosLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLimpiar)
						.addComponent(button))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(39))
		);
		panel_1.setLayout(gl_panel_1);
	}

	public void cargarArchivoEntrada() {
		JFileChooser fcBaseDatos = new JFileChooser();
		fcBaseDatos.setDialogTitle("Insumo de entrada");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		int returnVal = fcBaseDatos.showOpenDialog(this);
		try {
			File baseDatos = fcBaseDatos.getSelectedFile();
			ruta = baseDatos.getAbsolutePath();
			int tamanio = ruta.length();
			int cadena = tamanio - 3;
			String extension = ruta.substring(cadena, tamanio);
			// System.out.println(extension);
			if (!extension.equals("csv")) {
				// System.out.println(extension);
				JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo valido a calcular" + "", "Importación",
						JOptionPane.INFORMATION_MESSAGE);

			} else {

				if (returnVal == JFileChooser.APPROVE_OPTION) { /* OBJETOS */
					txtRuta.setText(ruta);
					JOptionPane.showMessageDialog(null, "Se conectó satisfactoriamente" + "", "CSV",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (Exception e) {
		}
	}

	public double convertGMStoDecimal(int grados, int minutos, double segundos) {
		double resultado;
		resultado = minutos + (segundos / 60);
		resultado = resultado / 60;
		// System.out.println("Resultados="+ resultado);
		resultado = Math.abs(grados) + resultado;
		return resultado;
	}

	public void convertDecimalToGMS(String decimal, String referencia) {
		double segundos, acarreo;
		int grados, minutos;
		grados = (int) Math.abs(Double.parseDouble(decimal));
		acarreo = (Double.parseDouble(decimal) % 1) * 60;
		minutos = (int) Math.abs(acarreo);
		segundos = (acarreo % 1) * 60;

		if (referencia.equals("Latitud")) {
			txtGradosLat.setText(Integer.toString(grados));
			txtMinutosLat.setText(Integer.toString(minutos));
			txtSegundosLat.setText(Double.toString(segundos));
		}
		if (referencia.equals("Longitud")) {
			txtGradosLong.setText(Integer.toString(grados));
			txtMinutosLong.setText(Integer.toString(minutos));
			txtSegundosLong.setText(Double.toString(segundos));
		}
	}

	public void csvGMStoDecimal(String ruta) {
		int i = 0;
		String UPM, gradosY, minutosY, segundosY, gradosX, minutosX, segundosX, decimalY = null, decimalX = null;
		try {
			// Abrimos el archivo
			FileReader leerarchivo = new FileReader(ruta);
			BufferedReader buffer = new BufferedReader(leerarchivo);
			String linea = null;

			while ((linea = buffer.readLine()) != null) {
				i++;
				//System.out.println(linea);
				if (i == 1) {// Salto cabezera
					System.out.println(linea);
				} else {
					StringTokenizer tokensaux = new StringTokenizer(linea, ",");
					while (tokensaux.hasMoreTokens()) {
						UPM = tokensaux.nextToken();

						gradosY = tokensaux.nextToken();
						minutosY = tokensaux.nextToken();
						segundosY = tokensaux.nextToken();

						gradosX = tokensaux.nextToken();
						minutosX = tokensaux.nextToken();
						segundosX = tokensaux.nextToken();

						if (gradosY.equals("NULL") || minutosY.equals("NULL") || segundosY.equals("NULL")
								|| gradosX.equals("NULL") || minutosX.equals("NULL") || segundosX.equals("NULL")) {
							decimalY = "NULL";
							decimalX = "NULL";
						} else {
							decimalY = Double.toString(convertGMStoDecimal(Integer.parseInt(gradosY),
									Integer.parseInt(minutosY), Double.parseDouble(segundosY)));
							decimalX = Double.toString(convertGMStoDecimal(Integer.parseInt(gradosX),
									Integer.parseInt(minutosX), Double.parseDouble(segundosX)));

						}
						coordenadasModel.addRow(new Object[] { UPM, gradosY, minutosY, segundosY, gradosX, minutosX,
								segundosX, decimalY, "-" + decimalX });
					}
					tblReusltados.setModel(coordenadasModel);
				}
			}
			leerarchivo.close();
		} catch (Exception e) { // Catch de excepciones
			System.err.println("Ocurrio un error: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void csvDecimaltoGMSDecimal(String ruta) {
		int i = 0;
		String UPM, gradosY, minutosY, segundosY, gradosX, minutosX, segundosX, decimalY = null, decimalX = null;
		try {
			// Abrimos el archivo
			FileReader leerarchivo = new FileReader(ruta);
			BufferedReader buffer = new BufferedReader(leerarchivo);
			String linea = null;

			while ((linea = buffer.readLine()) != null) {
				i++;
				//System.out.println(linea);
				if (i == 1) {// Salto cabezera
					System.out.println(linea);
				} else {
					StringTokenizer tokensaux = new StringTokenizer(linea, ",");
					while (tokensaux.hasMoreTokens()) {
						UPM = tokensaux.nextToken();

						decimalY = tokensaux.nextToken();
						decimalX = tokensaux.nextToken();
						if(decimalY.equals("NULL") || decimalX.equals("NULL")) {
							gradosY="NULL";
							minutosY="NULL";
							segundosY="NULL";
							gradosX="NULL";
							minutosX="NULL";
							segundosX="NULL";
						}else {
						double segundos_y, acarreo_y;
						int grados_y, minutos_y;
						grados_y = (int) Math.abs(Double.parseDouble(decimalY));
						acarreo_y = (Double.parseDouble(decimalY) % 1) * 60;
						minutos_y = (int) Math.abs(acarreo_y);
						segundos_y = (acarreo_y % 1) * 60;

						gradosY = Integer.toString(grados_y);
						minutosY = Integer.toString(minutos_y);
						segundosY = Double.toString(segundos_y);

						double segundos_x, acarreo_x;
						int grados_x, minutos_x;
						grados_x = (int) Math.abs(Double.parseDouble(decimalX));
						acarreo_x = (Double.parseDouble(decimalX) % 1) * 60;
						minutos_x = (int) Math.abs(acarreo_x);
						segundos_x = (acarreo_x % 1) * 60;

						gradosX = Integer.toString(grados_x);
						minutosX = Integer.toString(minutos_x);
						segundosX = Double.toString(segundos_x);
						}
						coordenadasModel.addRow(new Object[] { UPM, gradosY, minutosY, segundosY, gradosX, minutosX,
								segundosX, decimalY,  decimalX });
					}
					tblReusltados.setModel(coordenadasModel);
				}
			}
			leerarchivo.close();
		} catch (Exception e) { // Catch de excepciones
			System.err.println("Ocurrio un error: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiarTextBoxes() {
		txtGradosLat.setText("");
		txtMinutosLat.setText("");
		txtSegundosLat.setText("");
		txtGradosLong.setText("");
		txtMinutosLong.setText("");
		txtSegundosLong.setText("");
		txtLatDecimal.setText("");
		txtLongDecimal.setText("");
		tblReusltados.setModel(coordenadasModelLimpio);
	}
}
