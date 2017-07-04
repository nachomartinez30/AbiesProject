package concentrarbdinfys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Database.ConfigUserConnection;
import Database.ExternalConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class ConcentradorAbies extends JFrame {

	public String ruta;
	private String configUser = "/AbiesProject/src/Database/ConfigUser.db";
	private ExternalConnection externalConnection = new ExternalConnection();
	private ConfigUserConnection configUserConnection = new ConfigUserConnection();
	private Connection baseDatosConfig;
	private java.sql.Statement sqlConfig;

	private JPanel contentPane;
	public JProgressBar pbExportacion;
	public JTextField txtUbicacion;
	public JTextField txtRutaSalida;
	public JButton btnBuscar;
	public JButton btnEjecutar;
	public JLabel lblEstatus;
	public File[] baseDatos;
	  

	/**
	 * Create the frame.
	 */
	public ConcentradorAbies() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ConcentradorAbies.class.getResource("/Icons/AbiesProject.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		getPathConcentrador(configUser);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblConcentrador = new JLabel("Concentrador");
		lblConcentrador.setFont(new Font("Dialog", Font.BOLD, 50));
		lblConcentrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblConcentrador.setBounds(220, 57, 335, 56);
		panel.add(lblConcentrador);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ConcentradorAbies.class.getResource("/Icons/ConcentradorOCT.png")));
		lblNewLabel.setBounds(56, 12, 152, 162);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConcentradorAbies.class.getResource("/Icons/ConcentradorCONS.png")));
		label.setBounds(567, 12, 137, 162);
		panel.add(label);

		pbExportacion = new JProgressBar();
		pbExportacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pbExportacion.setStringPainted(true);
		pbExportacion.setForeground(new Color(184, 134, 11));
		pbExportacion.setBackground(Color.DARK_GRAY);
		pbExportacion.setBounds(12, 360, 752, 23);
		panel.add(pbExportacion);

		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(135, 215, 595, 20);
		panel.add(txtUbicacion);
		txtUbicacion.setColumns(10);

		lblEstatus = new JLabel("Importando...");
		lblEstatus.setBounds(12, 342, 752, 16);
		panel.add(lblEstatus);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cargarBaseDatos();
			}
		});
		btnBuscar.setBounds(43, 213, 80, 24);
		panel.add(btnBuscar);

		txtRutaSalida = new JTextField();
		txtRutaSalida.setBounds(401, 247, 329, 20);
		panel.add(txtRutaSalida);
		txtRutaSalida.setColumns(10);

		JLabel lblBaseDeDatos = new JLabel("Base de datos concentrada:");
		lblBaseDeDatos.setBounds(231, 249, 166, 16);
		panel.add(lblBaseDeDatos);

		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// excecuteConcentrar();
				int  i = 0;
				HiloImportacion hiloImportacion = new HiloImportacion(lblEstatus, pbExportacion, btnEjecutar, ruta);

				while (i < baseDatos.length) {
					ruta = baseDatos[i].getPath();
					System.out.println(ruta);
					hiloImportacion.execute();
					i++;
				}

			}
		});
		btnEjecutar.setEnabled(false);
		btnEjecutar.setBounds(351, 293, 111, 24);
		panel.add(btnEjecutar);
	}

	public void setPathConcentrador(String ruta) {
		String query = "UPDATE configUserAbies SET pathConcentrador='" + ruta + "'";
		System.out.println(query);
		Connection configConnection = ConfigUserConnection.getConnection(ruta);
		try {
			java.sql.Statement st = configConnection.createStatement();
			st.executeUpdate(query);
			configConnection.commit();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cargarBaseDatos() {
		JFileChooser fcBaseDatos = new JFileChooser(ruta);
		fcBaseDatos.setMultiSelectionEnabled(true);
		fcBaseDatos.setDialogTitle("Base de datos a importar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.oct", "oct");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		int returnVal = fcBaseDatos.showOpenDialog(this);

		// fcBaseDatos.showOpenDialog(Main.main);
		try {
			baseDatos = fcBaseDatos.getSelectedFiles();
			ruta = baseDatos[0].getAbsolutePath();
			int tamanio = ruta.length();
			int cadena = tamanio - 3;
			String extension = ruta.substring(cadena, tamanio);
			if (!extension.equals("oct")) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un base de datos valida a importar" + "",
						"Importación", JOptionPane.INFORMATION_MESSAGE);
				txtUbicacion.setText("");
			} else {
				txtUbicacion.setText(ruta);
				Path currentPath = Paths.get("");
				String path = currentPath.toAbsolutePath().toString();
				txtRutaSalida.setText(path + "/MuestreoINF_2015.cons");
				setPathConcentrador(ruta);
				btnEjecutar.setEnabled(true);

			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void excecuteConcentrar(String rute) {

	}

	public void getPathConcentrador(String bdConfig) {

		String query = "SELECT pathConcentrador FROM configUserAbies";

		this.baseDatosConfig = ConfigUserConnection.getConnection(ruta);
		try {
			sqlConfig = baseDatosConfig.createStatement();
			ResultSet rsConfig = sqlConfig.executeQuery(query);

			while (rsConfig.next()) {

				ruta = rsConfig.getString("pathConcentrador");
			}
			baseDatosConfig.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
