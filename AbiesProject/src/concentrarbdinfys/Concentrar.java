
package concentrarbdinfys;

//import gob.conafor.utils.FuncionesComunes;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import Database.ConfigUserConnection;
import Database.ExternalConnection;
import Views.Index;

public class Concentrar extends javax.swing.JFrame {
	
	public String ruta;
	private String configUser = "/AbiesProject/src/Database/ConfigUser.db";
	private ExternalConnection externalConnection = new ExternalConnection();
	private ConfigUserConnection configUserConnection = new ConfigUserConnection();
	private Connection baseDatosConfig;
	private java.sql.Statement sqlConfig;

	public Concentrar() {
		initComponents();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = this.getSize();
		getPathConcentrador(configUser);
		this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		txtUbicacion = new javax.swing.JTextField();
		btnBuscar = new javax.swing.JButton();
		btnEjecutar = new javax.swing.JButton();
		btnSalir = new javax.swing.JButton();
		pbExportacion = new javax.swing.JProgressBar();
		lblEstatus = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		txtUbicacion.setEnabled(false);

		btnBuscar.setText("Buscar");
		btnBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});

		btnEjecutar.setText("Ejecutar");
		btnEjecutar.setEnabled(false);
		btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEjecutarActionPerformed(evt);
			}
		});

		btnSalir.setText("Salir");
		btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSalirActionPerformed(evt);
			}
		});

		pbExportacion.setForeground(new java.awt.Color(33, 98, 26));
		pbExportacion.setStringPainted(true);

		lblEstatus.setText("Importar base de datos");

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Concentración");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(189, 189, 189)
												.addComponent(btnEjecutar).addGap(18, 18, 18)
												.addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE,
														74, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(144, 144, 144))
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(pbExportacion,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblEstatus, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createSequentialGroup().addComponent(btnBuscar)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(txtUbicacion)))))
						.addContainerGap())
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(83, 83, 83)
										.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(89, 89, 89)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(51, 51, 51)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addGap(31, 31, 31)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnEjecutar).addComponent(btnSalir))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblEstatus)
						.addGap(7, 7, 7)
						.addComponent(pbExportacion, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(39, 39, 39)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarActionPerformed
		JFileChooser fcBaseDatos = new JFileChooser(ruta);
		fcBaseDatos.setMultiSelectionEnabled(true);
		fcBaseDatos.setDialogTitle("Base de datos a importar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.oct", "oct");
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		fcBaseDatos.showOpenDialog(this);

		// fcBaseDatos.showOpenDialog(Main.main);
		try {
			File baseDatos = fcBaseDatos.getSelectedFile();
			String ruta = baseDatos.getAbsolutePath();
			int tamanio = ruta.length();
			int cadena = tamanio - 3;
			String extension = ruta.substring(cadena, tamanio);
			if (!extension.equals("oct")) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un base de datos valida a importar" + "",
						"Importación", JOptionPane.INFORMATION_MESSAGE);
				txtUbicacion.setText("");
			} else {
				txtUbicacion.setText(ruta);
				this.ruta = ruta;
				setPathConcentrador(ruta);
				btnEjecutar.setEnabled(true);

			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}// GEN-LAST:event_btnBuscarActionPerformed

	private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEjecutarActionPerformed
		// System.out.println(this.ruta);
		HiloImportacion hiloImportacion = new HiloImportacion(this.lblEstatus, this.pbExportacion, this.btnEjecutar,
				this.btnSalir, this.ruta);
		hiloImportacion.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equalsIgnoreCase("progress")) {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
				} else if (evt.getPropertyName().equalsIgnoreCase("state")) {
					switch ((SwingWorker.StateValue) evt.getNewValue()) {
					case DONE:
						txtUbicacion.setText("");
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						break;
					case STARTED:
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						break;
					case PENDING:
						break;
					}
				}
			}
		});
		hiloImportacion.execute();

	}// GEN-LAST:event_btnEjecutarActionPerformed

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

	public void getPathConcentrador(String bdConfig) {
		
			String query = "SELECT pathConcentrador FROM configUserAbies";

			this.baseDatosConfig = ConfigUserConnection.getConnection(ruta);
			try {
				sqlConfig = baseDatosConfig.createStatement();
				ResultSet rsConfig = sqlConfig.executeQuery(query);

				while (rsConfig.next()) {

					ruta=rsConfig.getString("pathConcentrador");
				}
				baseDatosConfig.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalirActionPerformed
		this.dispose();
		this.txtUbicacion.setText("");
		this.btnEjecutar.setEnabled(false);
	}// GEN-LAST:event_btnSalirActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnEjecutar;
	private javax.swing.JButton btnSalir;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel lblEstatus;
	private static javax.swing.JProgressBar pbExportacion;
	private javax.swing.JTextField txtUbicacion;
	// End of variables declaration//GEN-END:variables
}
