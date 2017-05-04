package Views;

import java.awt.EventQueue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.components.JSpinField;

import Database.ExternalConnection;

import com.csvreader.CsvWriter;
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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FrmExportar extends JInternalFrame {
	private JCheckBox chckbxArbolado;
	private JCheckBox chckbxSitios;
	private JCheckBox chckbxUpms;
	private JButton btnExportar;
	private JProgressBar pbProgresoExportacion;
	public String ruta;
	public String exportPath;

	private JLabel lblExportando;

	public FrmExportar(String ruta) {
		setClosable(true);
		this.ruta = ruta;
		setBounds(100, 100, 450, 300);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSaverDialog();

			}
		});
		btnExportar.setBounds(353, 237, 80, 24);
		panel.add(btnExportar);

		pbProgresoExportacion = new JProgressBar();
		pbProgresoExportacion.setBounds(12, 237, 330, 24);
		panel.add(pbProgresoExportacion);

		chckbxUpms = new JCheckBox("UPMs");
		chckbxUpms.setBounds(66, 28, 116, 24);
		panel.add(chckbxUpms);

		chckbxSitios = new JCheckBox("Sitios");
		chckbxSitios.setBounds(66, 55, 116, 24);
		panel.add(chckbxSitios);

		chckbxArbolado = new JCheckBox("Arbolado");
		chckbxArbolado.setBounds(66, 82, 116, 24);
		panel.add(chckbxArbolado);

		lblExportando = new JLabel("Exportando:");
		lblExportando.setBounds(12, 209, 330, 16);
		panel.add(lblExportando);

	}

	public void openSaverDialog() {
		JFileChooser fcBaseDatos = new JFileChooser();
		fcBaseDatos.setDialogTitle("Exportar arbolado");
		fcBaseDatos.setApproveButtonText("Exportar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");
		
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		fcBaseDatos.showOpenDialog(this);
		try {
			File f = fcBaseDatos.getSelectedFile();
			exportPath = f.getAbsolutePath();
			ProgressExport progresoExportado = new ProgressExport(pbProgresoExportacion, lblExportando, ruta, exportPath,chckbxUpms,chckbxSitios,chckbxArbolado);
			
			progresoExportado.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					if(arg0.getPropertyName().equalsIgnoreCase("progress")){
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
					}else{
						if(arg0.getPropertyName().equalsIgnoreCase("state")){
							switch ((SwingWorker.StateValue) arg0.getNewValue()) {
							case DONE:
								setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
								break;
							case STARTED:
								setCursor(new Cursor(Cursor.WAIT_CURSOR));
								break;
								case PENDING:
									break;
							default:
								break;
							}
						}
					}
					
				}
			});
			
			if (chckbxArbolado.isSelected() == true) {
				progresoExportado.setArbolado(true);
				
			}
			if (chckbxUpms.isSelected() == true) {
				progresoExportado.setUpms(true);
			}
			if (chckbxSitios.isSelected() == true) {
				progresoExportado.setSitios(true);
			}
			progresoExportado.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
