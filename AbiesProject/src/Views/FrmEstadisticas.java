package Views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class FrmEstadisticas extends JInternalFrame {
	private JTable tblUpmPorEstado;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public FrmEstadisticas() {
		setTitle("Estadistica");
		setFrameIcon(null);
		setBounds(100, 100, 541, 485);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layPanUpm = new JLayeredPane();
		tabbedPane.addTab("UPM", null, layPanUpm, null);
		
		JLabel lblUpmsPorEstado = new JLabel("UPMs por estado");
		lblUpmsPorEstado.setBounds(6, 6, 119, 16);
		layPanUpm.add(lblUpmsPorEstado);
		
		JScrollPane scrollPanePorEstado = new JScrollPane();
		scrollPanePorEstado.setBounds(16, 34, 227, 99);
		layPanUpm.add(scrollPanePorEstado);
		
		tblUpmPorEstado = new JTable();
		tblUpmPorEstado.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Estado", "Conteo UPMs "
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPanePorEstado.setViewportView(tblUpmPorEstado);
		
		JLayeredPane layPanSitio = new JLayeredPane();
		tabbedPane.addTab("Sitio", null, layPanSitio, null);

	}
}
