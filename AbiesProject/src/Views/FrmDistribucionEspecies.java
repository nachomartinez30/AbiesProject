package Views;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Database.ExternalConnection;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FrmDistribucionEspecies extends JInternalFrame {
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public JTable tblDistribuciones;
	private JLabel label;
	public FrmInformacionPorUPM infoUpm;
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	public JTable tblException;

	public FrmDistribucionEspecies(String ruta) {
		setClosable(true);
		setFrameIcon(null);
		setBounds(100, 100, 874, 672);
		this.ruta = ruta;

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();

		tblDistribuciones = new JTable();
		tblDistribuciones.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tblDistribuciones);

		scrollPane_1 = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Excepciones");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		label = new JLabel("Fuera de Distribuci\u00F3n");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE))
						.addGap(26))
				.addGroup(gl_panel.createSequentialGroup().addGap(34)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE).addGap(26))
				.addGroup(gl_panel.createSequentialGroup().addGap(36)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE).addGap(26)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(12)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addGap(33).addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
						.addGap(12).addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE).addGap(5)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE).addGap(29)));
		
		tblException = new JTable();
		tblException.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(tblException);
		panel.setLayout(gl_panel);

	}
}
