package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JCheckBoxMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import Database.ConfigUserConnection;
import Database.ExternalConnection;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Index extends JFrame {

	FrmEstadisticas estadistica;
	FrmExportar exportar;
	FrmInformacionPorUPM informacionUpm;
	private boolean temaClaro = false;
	private JPanel contentPane;
	private JTable table;
	public String ruta = "";
	private String configUser = "/AbiesProject/src/Database/ConfigUser.db";
	private String background = "/Icons/";
	private boolean leftPanel = true;

	private Connection baseDatosConfig;
	private java.sql.Statement sqlConfig;

	private ExternalConnection externalConnection = new ExternalConnection();
	private ConfigUserConnection configUserConnection = new ConfigUserConnection();

	public JButton btnEstadisticas;
	private JTextField textField;
	public JButton btnExportar;
	private JDesktopPane desktopPanelCentral;
	private JLabel lblBackground;
	private JPanel panelIzquierdo;
	private JCheckBoxMenuItem chckboxOcultarPanelIzquierdo;
	private JButton btnInfPorUpm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		configUserConnection.getConnection(configUser);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/Icons/g5296.png")));
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setTitle("Abies (V_1.2)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 824);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(0, 0, 0));
		contentPane.add(panelIzquierdo, BorderLayout.WEST);

		btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setEnabled(false);
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (estadistica.isVisible() == false) {

					desktopPanelCentral.add(estadistica);
					estadistica.setVisible(true);
				}

				if (estadistica.isBackgroundSet()) {
					estadistica.moveToFront();
				}

				try {
					estadistica.setMaximum(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		btnExportar = new JButton("CSV");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (exportar.isVisible() == false) {
					exportar.setVisible(true);
					desktopPanelCentral.add(exportar);

					// System.out.println("Arbir exportar");
				}

				if (exportar.isBackgroundSet()) {
					exportar.moveToFront();
				}

				try {
					exportar.setMaximum(false);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

			}
		});
		btnExportar.setEnabled(false);

		btnInfPorUpm = new JButton("Inf. por UPM");
		btnInfPorUpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (informacionUpm.isVisible() == false) {
					informacionUpm.setVisible(true);
					desktopPanelCentral.add(informacionUpm);

				}

				if (informacionUpm.isBackgroundSet()) {
					informacionUpm.moveToFront();
				}

				try {
					informacionUpm.setMaximum(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnInfPorUpm.setEnabled(false);
		GroupLayout gl_panelIzquierdo = new GroupLayout(panelIzquierdo);
		gl_panelIzquierdo.setHorizontalGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup().addGroup(gl_panelIzquierdo
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEstadisticas, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelIzquierdo.createSequentialGroup().addGap(1)
								.addGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
										.addComponent(btnInfPorUpm, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(btnExportar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_panelIzquierdo.setVerticalGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup().addGap(30).addComponent(btnEstadisticas).addGap(18)
						.addComponent(btnExportar).addGap(75).addComponent(btnInfPorUpm).addContainerGap(574,
								Short.MAX_VALUE)));
		panelIzquierdo.setLayout(gl_panelIzquierdo);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.BLACK);
		panelSuperior.setAlignmentY(Component.TOP_ALIGNMENT);
		panelSuperior.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 1, 0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		panelSuperior.add(menuBar);

		JMenu mnBaseDeDatos = new JMenu("Base de datos");
		menuBar.add(mnBaseDeDatos);

		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mntmCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cargarBaseDatos();
			}
		});
		mnBaseDeDatos.add(mntmCargar);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut);

		JMenu mnVentana = new JMenu("Ventana");
		menuBar.add(mnVentana);

		chckboxOcultarPanelIzquierdo = new JCheckBoxMenuItem("Ocultar panel izquierdo");
		chckboxOcultarPanelIzquierdo.setState(true);
		chckboxOcultarPanelIzquierdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckboxOcultarPanelIzquierdo.getState() == true) {
					panelIzquierdo.setVisible(true);
					setConfigLeftPanel(configUser, 1);
				}
				if (chckboxOcultarPanelIzquierdo.getState() == false) {
					chckboxOcultarPanelIzquierdo.setText("Mostrar panel izquierdo");
					panelIzquierdo.setVisible(false);
					setConfigLeftPanel(configUser, 0);
				}
			}
		});
		mnVentana.add(chckboxOcultarPanelIzquierdo);

		JMenuItem mntmCambiarFondo = new JMenuItem("Fondo conifera");
		mntmCambiarFondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(configUser, "AbiesProject_background2.png");
				lblBackground.setIcon(new ImageIcon(Index.class.getResource("/Icons/AbiesProject_background2.png")));
			}
		});
		mnVentana.add(mntmCambiarFondo);

		JMenuItem mntmFondoHoja = new JMenuItem("Fondo hoja");
		mntmFondoHoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(configUser, "Abies_background.png");
				lblBackground.setIcon(new ImageIcon(Index.class.getResource("/Icons/Abies_background.png")));
			}
		});
		mnVentana.add(mntmFondoHoja);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);

		JLabel lblBdActual = new JLabel("BD actual:");
		lblBdActual.setEnabled(false);
		menuBar.add(lblBdActual);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField.selectAll();
			}
		});
		textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(textField);
		textField.setColumns(10);

		desktopPanelCentral = new JDesktopPane();
		contentPane.add(desktopPanelCentral, BorderLayout.CENTER);

		lblBackground = new JLabel("");
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_desktopPanelCentral = new GroupLayout(desktopPanelCentral);
		gl_desktopPanelCentral.setHorizontalGroup(gl_desktopPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPanelCentral.createSequentialGroup().addGap(61)
						.addComponent(lblBackground, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE).addGap(79)));
		gl_desktopPanelCentral.setVerticalGroup(gl_desktopPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPanelCentral.createSequentialGroup().addGap(29)
						.addComponent(lblBackground, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE).addGap(35)));
		desktopPanelCentral.setLayout(gl_desktopPanelCentral);

		getUserConfigs(configUser);
	}

	public void cargarBaseDatos() {
		JFileChooser fcBaseDatos = new JFileChooser();
		fcBaseDatos.setDialogTitle("Base de datos a importar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.cons", "cons");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		int returnVal =fcBaseDatos.showOpenDialog(this);
		// fcBaseDatos.showOpenDialog(Main.main);
		try {
			File baseDatos = fcBaseDatos.getSelectedFile();
			ruta = baseDatos.getAbsolutePath();
			int tamanio = ruta.length();
			int cadena = tamanio - 4;
			String extension = ruta.substring(cadena, tamanio);

			if (!extension.equals("cons")) {
				// System.out.println(extension);
				JOptionPane.showMessageDialog(null, "Debe seleccionar un base de datos valida a importar" + "",
						"Importación", JOptionPane.INFORMATION_MESSAGE);

			} else {

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					externalConnection.getConnection(ruta);
					enabledLeftPanelButtons();
					informacionUpm = new FrmInformacionPorUPM(ruta, this.desktopPanelCentral);
					estadistica = new FrmEstadisticas(ruta);
					exportar = new FrmExportar(ruta);
					textField.setText(ruta);
					JOptionPane.showMessageDialog(null, "Se conectó satisfactoriamente");
				}
			}
		} catch (Exception e) {
		}
	}

	public void enabledLeftPanelButtons() {
		btnEstadisticas.setEnabled(true);
		btnExportar.setEnabled(true);
		btnInfPorUpm.setEnabled(true);
	}

	public void getUserConfigs(String ruta) {
		String query = "SELECT idConfig, showLeftPanel, background FROM configUserAbies";

		this.baseDatosConfig = ConfigUserConnection.getConnection(ruta);
		try {
			sqlConfig = baseDatosConfig.createStatement();
			ResultSet rsConfig = sqlConfig.executeQuery(query);

			while (rsConfig.next()) {

				lblBackground
						.setIcon(new ImageIcon(Index.class.getResource("/Icons/" + rsConfig.getString("background"))));
				panelIzquierdo.setVisible(rsConfig.getBoolean("showLeftPanel"));
				chckboxOcultarPanelIzquierdo.setSelected(rsConfig.getBoolean("showLeftPanel"));
				if (chckboxOcultarPanelIzquierdo.getState() == true) {
					panelIzquierdo.setVisible(true);

				}
				if (chckboxOcultarPanelIzquierdo.getState() == false) {
					chckboxOcultarPanelIzquierdo.setText("Mostrar panel izquierdo");
					panelIzquierdo.setVisible(false);
				}
			}
			baseDatosConfig.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBackground(String ruta, String background) {
		String query = "UPDATE configUserAbies SET background='" + background + "'";
		Connection configConnection = ConfigUserConnection.getConnection(ruta);
		try {
			java.sql.Statement st = configConnection.createStatement();
			st.executeUpdate(query);
			configConnection.commit();
			// System.out.println("Actualizado background");
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setConfigLeftPanel(String ruta, int visible) {
		String query = "UPDATE configUserAbies SET showLeftPanel=" + visible;
		// System.out.println(query);
		Connection configConnection = ConfigUserConnection.getConnection(ruta);
		try {
			java.sql.Statement st = configConnection.createStatement();
			st.executeUpdate(query);
			configConnection.commit();
			// System.out.println("Actualizado panel");
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
