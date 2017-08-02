package Views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;

public class FrmDiferenciaCoordenadas extends JInternalFrame {
	public JLabel lblDiferenciaDeCoordenadas;
	public JTable tblResultadosCoordenadas;

	public FrmDiferenciaCoordenadas() {
		setClosable(true);
		setFrameIcon(null);
		setBounds(100, 100, 878, 574);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		lblDiferenciaDeCoordenadas = new JLabel("Diferencia de coordenadas");
		lblDiferenciaDeCoordenadas.setForeground(Color.WHITE);
		lblDiferenciaDeCoordenadas.setFont(new Font("SansSerif", Font.BOLD, 34));
		lblDiferenciaDeCoordenadas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tblResultadosCoordenadas = new JTable();
		tblResultadosCoordenadas.setCellSelectionEnabled(true);
		tblResultadosCoordenadas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		tblResultadosCoordenadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tblResultadosCoordenadas);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(115)
					.addComponent(lblDiferenciaDeCoordenadas, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(118))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
					.addGap(35))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblDiferenciaDeCoordenadas, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(scrollPane)
					.addGap(39))
		);
		panel.setLayout(gl_panel);

	}
}
