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
	
	
	public FrmInformacionPorUPM() {
		setBounds(100, 100, 903, 670);
		
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
		scrollPane.setRowHeaderView(list);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("New tab", null, layeredPane_1, null);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("New tab", null, layeredPane_2, null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("New tab", null, layeredPane, null);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("New tab", null, layeredPane_3, null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblEstado = new JLabel("Estado:");
		
		lblEstadoResp = new JLabel("OAXACA");
		lblEstadoResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEstadoResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblNewLabel = new JLabel("Municipio:");
		
		lblMunicipioResp = new JLabel("Santo Domingo Zanatepec");
		lblMunicipioResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMunicipioResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblAltitud = new JLabel("Altitud:");
		
		lblAltitudResp = new JLabel("...");
		lblAltitudResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAltitudResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblPendRepresentativa = new JLabel("Pend. Representativa:");
		
		lblPendienteRepresentativaResp = new JLabel("...");
		lblPendienteRepresentativaResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPendienteRepresentativaResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblExposicin = new JLabel("Exposici\u00F3n:");
		
		lblExposicionResp = new JLabel("...");
		lblExposicionResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblExposicionResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Fisiograf\u00EDa");
		
		lblFisiografiaResp = new JLabel("...");
		lblFisiografiaResp.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblFisiografiaResp.setFont(new Font("Dialog", Font.ITALIC, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblEstado)
					.addGap(5)
					.addComponent(lblEstadoResp)
					.addGap(10)
					.addComponent(lblNewLabel)
					.addGap(5)
					.addComponent(lblMunicipioResp)
					.addGap(5)
					.addComponent(lblAltitud)
					.addGap(5)
					.addComponent(lblAltitudResp)
					.addGap(5)
					.addComponent(lblPendRepresentativa)
					.addGap(5)
					.addComponent(lblPendienteRepresentativaResp)
					.addGap(5)
					.addComponent(lblExposicin)
					.addGap(5)
					.addComponent(lblExposicionResp)
					.addGap(5)
					.addComponent(lblNewLabel_1)
					.addGap(5)
					.addComponent(lblFisiografiaResp))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblEstado))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblEstadoResp))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblMunicipioResp))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblAltitud))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblAltitudResp))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblPendRepresentativa))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblPendienteRepresentativaResp))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblExposicin))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblExposicionResp))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel_1))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblFisiografiaResp))
		);
		panel.setLayout(gl_panel);

	}
}
