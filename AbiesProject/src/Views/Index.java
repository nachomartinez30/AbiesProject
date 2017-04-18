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
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.SystemColor;

public class Index extends JFrame {
	private boolean temaClaro=false;
	private JPanel contentPane;
	private JTable table;
	FrmEstadisticas estadistica =new FrmEstadisticas();
	static String oscuro = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
		setBounds(100, 100, 1023, 824);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(0, 0, 0));
		panelIzquierdo.setBounds(new Rectangle(0, 0, 2, 0));
		contentPane.add(panelIzquierdo, BorderLayout.WEST);
		
		JButton btnRevisar = new JButton("Estadisticas");
		btnRevisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCentral.add(estadistica);
				estadistica.setVisible(true);
			}
		});
		GroupLayout gl_panelIzquierdo = new GroupLayout(panelIzquierdo);
		gl_panelIzquierdo.setHorizontalGroup(
			gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addComponent(btnRevisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panelIzquierdo.setVerticalGroup(
			gl_panelIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIzquierdo.createSequentialGroup()
					.addGap(30)
					.addComponent(btnRevisar))
		);
		panelIzquierdo.setLayout(gl_panelIzquierdo);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.BLACK);
		panelSuperior.setAlignmentY(Component.TOP_ALIGNMENT);
		panelSuperior.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		panelSuperior.add(menuBar);
		
		JMenu mnBaseDeDatos = new JMenu("Base de datos");
		menuBar.add(mnBaseDeDatos);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mnBaseDeDatos.add(mntmCargar);
		
		JMenu mnVentana = new JMenu("Ventana");
		menuBar.add(mnVentana);
		
		JCheckBoxMenuItem chckboxOcultarPanelIzquierdo = new JCheckBoxMenuItem("Ocultar panel izquierdo");
		chckboxOcultarPanelIzquierdo.setState(true);
		chckboxOcultarPanelIzquierdo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckboxOcultarPanelIzquierdo.getState()==true){
					panelIzquierdo.setVisible(true);
					
				}
				if(chckboxOcultarPanelIzquierdo.getState()==false){
					chckboxOcultarPanelIzquierdo.setText("Mostrar panel izquierdo");
					panelIzquierdo.setVisible(false);
				}
			}
		});
		mnVentana.add(chckboxOcultarPanelIzquierdo);
	}
}
