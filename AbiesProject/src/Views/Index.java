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

import Database.ExternalConnection;
import concentrarbdinfys.Concentrar;

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
	private boolean temaClaro = false;
	private JPanel contentPane;
	private JTable table;
	public String ruta = "";

	FrmArbolado arbolado;
	Concentrar concentrador = new Concentrar();

	// static String oscuro = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
	private ExternalConnection externalConnection = new ExternalConnection();

	public JButton btnEstadisticas;
	private JTextField textField;
	public JButton btnArbolado;
	private JDesktopPane desktopPanelCentral;
	private JLabel lblNewLabel;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/Icons/g5296.png")));
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setTitle("Estadisticas UPM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 824);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(0, 0, 0));
		contentPane.add(panelIzquierdo, BorderLayout.WEST);

		btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setEnabled(false);
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println(ruta);

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

		btnArbolado = new JButton("Arbolado");
		btnArbolado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (arbolado.isVisible() == false) {
					desktopPanelCentral.add(arbolado);
					arbolado.setVisible(true);
				}
				if (arbolado.isBackgroundSet()) {
					arbolado.moveToFront();
				}
				try {
					arbolado.setMaximum(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btnArbolado.setEnabled(false);
		GroupLayout gl_panelIzquierdo = new GroupLayout(panelIzquierdo);
		gl_panelIzquierdo.setHorizontalGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup()
						.addGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEstadisticas, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_panelIzquierdo.createSequentialGroup().addGap(1)
										.addComponent(btnArbolado, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelIzquierdo.setVerticalGroup(gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup().addGap(30).addComponent(btnEstadisticas).addGap(18)
						.addComponent(btnArbolado).addContainerGap(665, Short.MAX_VALUE)));
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

		JCheckBoxMenuItem chckboxOcultarPanelIzquierdo = new JCheckBoxMenuItem("Ocultar panel izquierdo");
		chckboxOcultarPanelIzquierdo.setState(true);
		chckboxOcultarPanelIzquierdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckboxOcultarPanelIzquierdo.getState() == true) {
					panelIzquierdo.setVisible(true);

				}
				if (chckboxOcultarPanelIzquierdo.getState() == false) {
					chckboxOcultarPanelIzquierdo.setText("Mostrar panel izquierdo");
					panelIzquierdo.setVisible(false);
				}
			}
		});
		mnVentana.add(chckboxOcultarPanelIzquierdo);

		JMenuItem mntmCambiarFondo = new JMenuItem("Fondo conifera");
		mntmCambiarFondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setIcon(new ImageIcon(Index.class.getResource("/Icons/AbiesProject_background2.png")));
			}
		});
		mnVentana.add(mntmCambiarFondo);

		JMenuItem mntmFondoHoja = new JMenuItem("Fondo hoja");
		mntmFondoHoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setIcon(new ImageIcon(Index.class.getResource("/Icons/Abies_background.png")));
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

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Index.class.getResource("/Icons/AbiesProject_background2.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_desktopPanelCentral = new GroupLayout(desktopPanelCentral);
		gl_desktopPanelCentral.setHorizontalGroup(gl_desktopPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPanelCentral.createSequentialGroup().addGap(61)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE).addGap(79)));
		gl_desktopPanelCentral.setVerticalGroup(gl_desktopPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPanelCentral.createSequentialGroup().addGap(29)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE).addGap(35)));
		desktopPanelCentral.setLayout(gl_desktopPanelCentral);
	}

	public void cargarBaseDatos() {
		JFileChooser fcBaseDatos = new JFileChooser();
		fcBaseDatos.setDialogTitle("Base de datos a importar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.cons", "cons");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		fcBaseDatos.showOpenDialog(this);
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
				externalConnection.getConnection(ruta);
				enabledLeftPanelButtons();
				estadistica = new FrmEstadisticas(ruta);
				arbolado = new FrmArbolado(ruta);
				textField.setText(ruta);
				JOptionPane.showMessageDialog(null, "Se conectó satisfactoriamente");

				// System.out.println(ruta);
			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void enabledLeftPanelButtons() {
		btnEstadisticas.setEnabled(true);
		btnArbolado.setEnabled(true);
	}
}
