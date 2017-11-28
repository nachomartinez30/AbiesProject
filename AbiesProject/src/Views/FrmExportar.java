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
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.components.JSpinField;

import Database.ExternalConnection;
import Database.ProgressExport;

import com.csvreader.CsvWriter;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
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
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JTextArea;

public class FrmExportar extends JInternalFrame {

	private JButton btnExportar;
	private JProgressBar pbProgresoExportacion;
	public String ruta;
	public String exportPath;
	public ArrayList<String> listUpmsConsultar = new ArrayList<String>();
	public ProgressExport progresoExportado;

	private JLabel lblExportando;
	private JCheckBox chckbxArbolado;
	private JCheckBox chckbxSitios;
	private JCheckBox chckbxUpms;
	private JCheckBox chckbxRepoblado;
	private JCheckBox chckbxSotobosque;
	private JCheckBox chckbxTodo;
	private JCheckBox chckbxInlcuirCoordenadas;
	private JCheckBox chckbxInlcuirProveedores;
	private JCheckBox chckbxVegetacionMayorGregarios;
	private JCheckBox chckbxVegetacionMayorIndividual;
	private JCheckBox chckbxVegetacionMenor;
	private JCheckBox chckbxRepobladoVm;
	private JCheckBox chckbxFiltrarPorUpm;
	private JTextArea txtAUPMS;

	public FrmExportar(String ruta) {
		setFrameIcon(null);
		setTitle("CVS");
		setIconifiable(true);
		setClosable(true);
		this.ruta = ruta;
		setBounds(100, 100, 585, 543);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		btnExportar = new JButton("Exportar");
		btnExportar.setMnemonic('e');
		btnExportar.setBounds(488, 480, 80, 24);
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openSaverDialog();

			}
		});

		pbProgresoExportacion = new JProgressBar();
		pbProgresoExportacion.setForeground(Color.ORANGE);
		pbProgresoExportacion.setBounds(12, 480, 465, 24);

		lblExportando = new JLabel("Exportando:");
		lblExportando.setBounds(12, 452, 330, 16);
		panel.setLayout(null);
		panel.add(lblExportando);
		panel.add(pbProgresoExportacion);
		panel.add(btnExportar);

		chckbxTodo = new JCheckBox("Todo");
		chckbxTodo.setMnemonic('t');
		chckbxTodo.setForeground(Color.ORANGE);
		chckbxTodo.setBounds(488, 444, 80, 24);
		chckbxTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxTodo.isSelected() == true) {
					chckbxArbolado.setSelected(true);
					chckbxSitios.setSelected(true);
					chckbxUpms.setSelected(true);
					chckbxRepoblado.setSelected(true);
					chckbxSotobosque.setSelected(true);
					chckbxVegetacionMayorGregarios.setSelected(true);
					chckbxVegetacionMayorIndividual.setSelected(true);
					chckbxVegetacionMenor.setSelected(true);
					chckbxRepobladoVm.setSelected(true);
				}
				if (chckbxTodo.isSelected() == false) {
					chckbxArbolado.setSelected(false);
					chckbxSitios.setSelected(false);
					chckbxUpms.setSelected(false);
					chckbxRepoblado.setSelected(false);
					chckbxSotobosque.setSelected(false);
					chckbxVegetacionMayorGregarios.setSelected(false);
					chckbxVegetacionMayorIndividual.setSelected(false);
					chckbxVegetacionMenor.setSelected(false);
					chckbxRepobladoVm.setSelected(false);
				}
			}
		});
		panel.add(chckbxTodo);

		JLabel lblTaxonomia = new JLabel("Taxonomia");
		lblTaxonomia.setBounds(147, 149, 175, 16);
		lblTaxonomia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaxonomia.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTaxonomia.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		panel.add(lblTaxonomia);

		JLabel lblExportacin = new JLabel("Exportaci\u00F3n");
		lblExportacin.setBounds(12, 12, 421, 31);
		lblExportacin.setFont(new Font("Dialog", Font.BOLD, 19));
		lblExportacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblExportacin.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(lblExportacin);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(147, 55, 155, 48);
		panel.add(verticalBox);

		chckbxInlcuirCoordenadas = new JCheckBox("Inlcuir coordenadas");
		chckbxInlcuirCoordenadas.setMnemonic('c');
		chckbxInlcuirCoordenadas.setForeground(Color.ORANGE);
		verticalBox.add(chckbxInlcuirCoordenadas);

		chckbxInlcuirProveedores = new JCheckBox("Inlcuir proveedores");
		chckbxInlcuirProveedores.setMnemonic('p');
		chckbxInlcuirProveedores.setForeground(Color.ORANGE);
		verticalBox.add(chckbxInlcuirProveedores);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBounds(136, 173, 206, 177);
		panel.add(verticalBox_1);

		chckbxArbolado = new JCheckBox("Arbolado");
		chckbxArbolado.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxArbolado);

		chckbxRepoblado = new JCheckBox("Repoblado");
		chckbxRepoblado.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxRepoblado);

		chckbxRepobladoVm = new JCheckBox("Repoblado V.M.");
		chckbxRepobladoVm.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxRepobladoVm);

		chckbxSotobosque = new JCheckBox("Sotobosque");
		chckbxSotobosque.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxSotobosque);

		chckbxVegetacionMayorGregarios = new JCheckBox("Vegetacion mayor gregarios");
		chckbxVegetacionMayorGregarios.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxVegetacionMayorGregarios);

		chckbxVegetacionMayorIndividual = new JCheckBox("Vegetacion mayor individual");
		chckbxVegetacionMayorIndividual.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxVegetacionMayorIndividual);

		chckbxVegetacionMenor = new JCheckBox("Vegetacion menor");
		chckbxVegetacionMenor.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_1.add(chckbxVegetacionMenor);

		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBounds(12, 173, 66, 48);
		panel.add(verticalBox_2);

		chckbxUpms = new JCheckBox("UPMs");
		chckbxUpms.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_2.add(chckbxUpms);

		chckbxSitios = new JCheckBox("Sitios");
		chckbxSitios.setFont(new Font("Dialog", Font.PLAIN, 12));
		verticalBox_2.add(chckbxSitios);

		chckbxFiltrarPorUpm = new JCheckBox("Filtrar por UPM");
		chckbxFiltrarPorUpm.setMnemonic('U');
		chckbxFiltrarPorUpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxFiltrarPorUpm.isSelected() == true) {
					txtAUPMS.setEnabled(true);
				} else {
					txtAUPMS.setEnabled(false);
					txtAUPMS.setText("");
				}
			}
		});
		chckbxFiltrarPorUpm.setBounds(400, 145, 143, 24);
		panel.add(chckbxFiltrarPorUpm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 167, 143, 183);
		panel.add(scrollPane);

		txtAUPMS = new JTextArea();
		txtAUPMS.setEnabled(false);
		scrollPane.setViewportView(txtAUPMS);

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
			progresoExportado = new ProgressExport(pbProgresoExportacion, lblExportando, ruta, exportPath,
					chckbxArbolado, chckbxSitios, chckbxUpms, chckbxRepoblado, chckbxSotobosque, chckbxTodo,
					chckbxInlcuirCoordenadas, chckbxInlcuirProveedores, chckbxVegetacionMayorGregarios,
					chckbxVegetacionMayorIndividual, chckbxVegetacionMenor, chckbxRepobladoVm, btnExportar);

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

			boolean coordenadas = chckbxInlcuirCoordenadas.isSelected(),
					proveedores = chckbxInlcuirProveedores.isSelected(), upms = chckbxFiltrarPorUpm.isSelected();

			if (chckbxArbolado.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setArbolado(true);
			}

			if (chckbxSitios.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setSitios(true);
			}

			if (chckbxUpms.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setUpms(true);
			}

			if (chckbxRepoblado.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setRepoblado(true);
			}

			if (chckbxSotobosque.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setSotobosque(true);
			}

			if (chckbxVegetacionMayorGregarios.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setVegetacionMayorGregarios(true);
			}

			if (chckbxVegetacionMenor.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setVegetacionMenor(true);
			}

			if (chckbxVegetacionMayorIndividual.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setVegetacionMayorIndividual(true);
			}

			if (chckbxRepobladoVm.isSelected() == true) {
				progresoExportado.setCoordenadasGrl(coordenadas);
				progresoExportado.setProveedoresGrl(proveedores);
				progresoExportado.setRepobladoVM(true);
			}

			if (upms == true) {
				progresoExportado.setFiltroUPM(upms);
				getUPMs(txtAUPMS.getText());
			}
			 progresoExportado.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getUPMs(String upms) {
		StringTokenizer token = new StringTokenizer(upms);
		String upmsAFiltrar = "";
		listUpmsConsultar.clear();

		while (token.hasMoreTokens()) {
			upmsAFiltrar = token.nextToken().toString();
			listUpmsConsultar.add(upmsAFiltrar);
		}
		progresoExportado.setListUpmsConsultar(listUpmsConsultar);
	}

}
