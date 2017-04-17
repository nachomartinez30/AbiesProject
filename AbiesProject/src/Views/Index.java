package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JCheckBoxMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Index extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setTitle("Estadisticas UPM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		
		JInternalFrame internalFrame = new JInternalFrame("Estadisticas");
		panelCentral.add(internalFrame);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		internalFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPaneUPM = new JLayeredPane();
		tabbedPane.addTab("UPM", null, layeredPaneUPM, null);
		layeredPaneUPM.setLayout(null);
		
		JLabel lblUpmsPorEstado = new JLabel("UPMs por estado");
		lblUpmsPorEstado.setBounds(30, 5, 95, 16);
		layeredPaneUPM.add(lblUpmsPorEstado);
		
		table = new JTable();
		table.setBounds(30, 33, 150, 105);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Estado", "Conteo UPMs"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(171);
		layeredPaneUPM.add(table);
		
		JLayeredPane layeredPaneSitio = new JLayeredPane();
		tabbedPane.addTab("Sitio", null, layeredPaneSitio, null);
		internalFrame.setVisible(true);
		
		JPanel panelIzquierdo = new JPanel();
		contentPane.add(panelIzquierdo, BorderLayout.WEST);
		GridBagLayout gbl_panelIzquierdo = new GridBagLayout();
		gbl_panelIzquierdo.columnWidths = new int[]{70, 0};
		gbl_panelIzquierdo.rowHeights = new int[]{28, 0, 0};
		gbl_panelIzquierdo.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelIzquierdo.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelIzquierdo.setLayout(gbl_panelIzquierdo);
		
		JButton btnRevisar = new JButton("Estadisticas");
		GridBagConstraints gbc_btnRevisar = new GridBagConstraints();
		gbc_btnRevisar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRevisar.gridx = 0;
		gbc_btnRevisar.gridy = 1;
		panelIzquierdo.add(btnRevisar, gbc_btnRevisar);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setAlignmentY(Component.TOP_ALIGNMENT);
		panelSuperior.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panelSuperior.add(menuBar);
		
		JMenu mnBaseDeDatos = new JMenu("Base de datos");
		menuBar.add(mnBaseDeDatos);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mnBaseDeDatos.add(mntmCargar);
		
		JMenu mnVentana = new JMenu("Ventana");
		menuBar.add(mnVentana);
		
		JCheckBoxMenuItem chckbxmntmOcultarPanelIzquierdo = new JCheckBoxMenuItem("Ocultar panel izquierdo");
		mnVentana.add(chckbxmntmOcultarPanelIzquierdo);
	}
}
