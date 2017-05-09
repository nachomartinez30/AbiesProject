package Views;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.block.BlockParams;


public class FrmInformacionPorUPM extends JInternalFrame {
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	private JLabel lblEstadoResp;
	private JLabel lblMunicipioResp;
	private JLabel lblAltitudResp;
	private JLabel lblPendienteRepresentativaResp;
	private JLabel lblExposicionResp;
	private JLabel lblFisiografiaResp;
	private JTextField txtRegistrosTotales;
	private JTextField txtIndividuosTotales;
	private JTable tblVegetacionPorSitio;
	private JTable table;
	
	
	public FrmInformacionPorUPM() {
		setBounds(100, 100, 979, 895);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.WEST);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"6804", "8228", "11284", "11288", "12873", "13659", "14411", "14465", "14469", "14471", "15276", "16045", "16105", "16107", "16125", "16127", "16940", "17775", "18505", "19362", "20231", "20301", "22779", "22799", "22979", "23841", "23851", "24528", "24708", "28985", "28995", "29902", "31729", "31733", "32640", "32650", "41486", "42868", "46176", "47340", "56339", "57386", "58427", "58497", "60437", "61437", "61451", "62433", "62453", "63475", "63485", "64480", "65505", "65517", "66540", "67549", "68565", "75694", "85825", "90746", "93652", "99246", "102043", "102966", "102972", "104822", "105736", "105746", "106670"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lblUpms = new JLabel("UPMS");
		lblUpms.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpms.setFont(new Font("Dialog", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblUpms);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Arbolado", null, layeredPane_1, null);
		layeredPane_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("No. Registros totales:");
		lblNewLabel_2.setBounds(27, 22, 132, 20);
		layeredPane_1.add(lblNewLabel_2);
		
		txtRegistrosTotales = new JTextField();
		txtRegistrosTotales.setBounds(165, 22, 67, 20);
		layeredPane_1.add(txtRegistrosTotales);
		txtRegistrosTotales.setColumns(10);
		
		JLabel lblNoIndividuosTotales = new JLabel("No. Individuos totales:");
		lblNoIndividuosTotales.setBounds(27, 49, 132, 20);
		layeredPane_1.add(lblNoIndividuosTotales);
		
		txtIndividuosTotales = new JTextField();
		txtIndividuosTotales.setColumns(10);
		txtIndividuosTotales.setBounds(165, 49, 67, 20);
		layeredPane_1.add(txtIndividuosTotales);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(27, 108, 869, 148);
		layeredPane_1.add(scrollPane_1);
		
		tblVegetacionPorSitio = new JTable();
		tblVegetacionPorSitio.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sitio", "Tipo de vegetaci\u00F3n", "Fase sucecional", "Conteo registros", "Conteo Individuos"
			}
		));
		scrollPane_1.setViewportView(tblVegetacionPorSitio);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(27, 294, 869, 148);
		layeredPane_1.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sitio", "Entidad taxonomica", "Forma de vida"
			}
		));
		scrollPane_2.setViewportView(table);
		
		JLabel lblTipoDeVetacion = new JLabel("Tipo de vetacion por sitio");
		lblTipoDeVetacion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTipoDeVetacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeVetacion.setBounds(27, 80, 869, 20);
		layeredPane_1.add(lblTipoDeVetacion);
		
		JLabel lblEspeciesPorSitio = new JLabel("Especies por sitio");
		lblEspeciesPorSitio.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEspeciesPorSitio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspeciesPorSitio.setBounds(27, 268, 869, 20);
		layeredPane_1.add(lblEspeciesPorSitio);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 454, 404, 340);
		layeredPane_1.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(492, 454, 404, 340);
		layeredPane_1.add(panel_2);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Sotobosque", null, layeredPane_2, null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Repoblado", null, layeredPane, null);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Modulo H", null, layeredPane_3, null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblEstadoResp = new JLabel("OAXACA");
		lblEstadoResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEstadoResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel = new JLabel("Municipio:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblMunicipioResp = new JLabel("Santo Domingo Zanatepec");
		lblMunicipioResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMunicipioResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblAltitud = new JLabel("Altitud:");
		lblAltitud.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAltitud.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblAltitudResp = new JLabel("...");
		lblAltitudResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAltitudResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblPendRepresentativa = new JLabel("Pend. Representativa:");
		lblPendRepresentativa.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPendRepresentativa.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblPendienteRepresentativaResp = new JLabel("...");
		lblPendienteRepresentativaResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPendienteRepresentativaResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblExposicin = new JLabel("Exposici\u00F3n:");
		lblExposicin.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblExposicin.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblExposicionResp = new JLabel("...");
		lblExposicionResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblExposicionResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Fisiograf\u00EDa");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblFisiografiaResp = new JLabel("...");
		lblFisiografiaResp.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFisiografiaResp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUpm = new JLabel("UPM:");
		lblUpm.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel.add(lblUpm);
		
		JLabel label = new JLabel("...");
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		panel.add(label);
		panel.add(lblEstado);
		panel.add(lblEstadoResp);
		
		JSeparator separator = new JSeparator();
		panel.add(separator);
		panel.add(lblNewLabel);
		panel.add(lblMunicipioResp);
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1);
		panel.add(lblAltitud);
		panel.add(lblAltitudResp);
		
		JSeparator separator_2 = new JSeparator();
		panel.add(separator_2);
		panel.add(lblPendRepresentativa);
		panel.add(lblPendienteRepresentativaResp);
		
		JSeparator separator_3 = new JSeparator();
		panel.add(separator_3);
		panel.add(lblExposicin);
		panel.add(lblExposicionResp);
		
		JSeparator separator_4 = new JSeparator();
		panel.add(separator_4);
		panel.add(lblNewLabel_1);
		panel.add(lblFisiografiaResp);

	}
}
