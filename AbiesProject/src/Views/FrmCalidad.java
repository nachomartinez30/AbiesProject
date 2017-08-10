package Views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;

import Database.*;

import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmCalidad extends JInternalFrame {
	private JDesktopPane desktopPane;
	FrmDistribucionEspecies distribucion;
	FrmDiferenciaCoordenadas coordenadas;
	ProgressDistribucion progresoDistribucion;
	ProgressCoordenateDiff progresoCoordenadas;
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	public JProgressBar progressBar;
	private JButton btnDistribucin;

	public FrmCalidad(String ruta) {
		setIconifiable(true);
		this.ruta = ruta;
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Pruebas");
		setFrameIcon(null);
		setBounds(100, 100, 979, 673);
		

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);

		btnDistribucin = new JButton("Distribuci\u00F3n");
		btnDistribucin.setMnemonic('d');
		btnDistribucin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progresoDistribucion=new ProgressDistribucion(progressBar, ruta,distribucion.tblDistribuciones,distribucion.tblException);
				progresoDistribucion.execute();
				if (distribucion.isVisible() == false) {
					distribucion.setVisible(true);
					
					desktopPane.add(distribucion);

				}

				if (distribucion.isBackgroundSet()) {
					distribucion.moveToFront();
				}

				try {
					distribucion.setMaximum(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		JLabel lblCalidad = new JLabel("Calidad");
		lblCalidad.setFont(new Font("Dialog", Font.BOLD, 17));
		lblCalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalidad.setAlignmentX(Component.CENTER_ALIGNMENT);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.ORANGE);
		
		JButton btnDifCoordenadas = new JButton("Dif. Coordenadas");
		btnDifCoordenadas.setMnemonic('c');
		btnDifCoordenadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progresoCoordenadas=new ProgressCoordenateDiff(progressBar, ruta,coordenadas.tblResultadosCoordenadas);
				progresoCoordenadas.execute();
				if (coordenadas.isVisible() == false) {
					coordenadas.setVisible(true);
					
					desktopPane.add(coordenadas);

				}

				if (coordenadas.isBackgroundSet()) {
					coordenadas.moveToFront();
				}

				try {
					coordenadas.setMaximum(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCalidad, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(btnDistribucin, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDifCoordenadas, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblCalidad)
					.addGap(26)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnDistribucin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDifCoordenadas)
					.addContainerGap(480, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);

		distribucion = new FrmDistribucionEspecies(ruta);
		coordenadas = new FrmDiferenciaCoordenadas();
	}
}
