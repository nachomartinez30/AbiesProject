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
	private JCheckBox chckbxRepoblado;
	private JCheckBox chckbxSotobosque;
	private JCheckBox chckbxTodo;

	public FrmExportar(String ruta) {
		setClosable(true);
		this.ruta = ruta;
		setBounds(100, 100, 450, 336);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(353, 261, 80, 24);
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSaverDialog();
				
			}
		});

		pbProgresoExportacion = new JProgressBar();
		pbProgresoExportacion.setBounds(12, 261, 330, 24);

		chckbxUpms = new JCheckBox("UPMs");
		chckbxUpms.setBounds(91, 113, 116, 24);

		chckbxSitios = new JCheckBox("Sitios");
		chckbxSitios.setBounds(91, 140, 116, 24);

		chckbxArbolado = new JCheckBox("Arbolado");
		chckbxArbolado.setBounds(226, 103, 97, 24);

		lblExportando = new JLabel("Exportando:");
		lblExportando.setBounds(12, 233, 330, 16);

		chckbxRepoblado = new JCheckBox("Repoblado");
		chckbxRepoblado.setBounds(226, 127, 97, 24);

		chckbxSotobosque = new JCheckBox("Sotobosque");
		chckbxSotobosque.setBounds(226, 151, 97, 24);
		panel.setLayout(null);
		panel.add(chckbxRepoblado);
		panel.add(chckbxSotobosque);
		panel.add(chckbxUpms);
		panel.add(chckbxSitios);
		panel.add(chckbxArbolado);
		panel.add(lblExportando);
		panel.add(pbProgresoExportacion);
		panel.add(btnExportar);

		chckbxTodo = new JCheckBox("Todo");
		chckbxTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxTodo.isSelected() == true) {
					chckbxRepoblado.setSelected(true);
					chckbxSotobosque.setSelected(true);
					chckbxArbolado.setSelected(true);
					chckbxSitios.setSelected(true);
					chckbxUpms.setSelected(true);
				}
				if (chckbxTodo.isSelected() == false) {
					chckbxRepoblado.setSelected(false);
					chckbxSotobosque.setSelected(false);
					chckbxArbolado.setSelected(false);
					chckbxSitios.setSelected(false);
					chckbxUpms.setSelected(false);
				}
			}
		});
		chckbxTodo.setBounds(353, 209, 80, 24);
		panel.add(chckbxTodo);

		JLabel lblTaxonomia = new JLabel("Taxonomia");
		lblTaxonomia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaxonomia.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTaxonomia.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblTaxonomia.setBounds(226, 78, 97, 16);
		panel.add(lblTaxonomia);
		
		JLabel lblExportacin = new JLabel("Exportaci\u00F3n");
		lblExportacin.setFont(new Font("Dialog", Font.BOLD, 19));
		lblExportacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblExportacin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblExportacin.setBounds(12, 12, 421, 31);
		panel.add(lblExportacin);

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
			ProgressExport progresoExportado = new ProgressExport(pbProgresoExportacion, lblExportando, ruta,
					exportPath, chckbxUpms, chckbxSitios, chckbxArbolado,chckbxSotobosque,chckbxRepoblado,chckbxTodo, btnExportar);

			progresoExportado.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					if (arg0.getPropertyName().equalsIgnoreCase("progress")) {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
					} else {
						if (arg0.getPropertyName().equalsIgnoreCase("state")) {
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
			if (chckbxRepoblado.isSelected() == true) {
				progresoExportado.setRepoblado(true);
			}
			if (chckbxSotobosque.isSelected() == true) {
				progresoExportado.setSotobosque(true);
			}
			
			progresoExportado.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
