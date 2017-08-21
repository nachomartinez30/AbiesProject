package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmAcercaDeCoordenadas extends JFrame {

	private JPanel contentPane;

	public FrmAcercaDeCoordenadas() {
		setTitle("Acerca de...");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAcercaDeCoordenadas.class.getResource("/Icons/g5296.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 921, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblConversorDeCoordenadas = new JLabel("Conversor de coordenadas");
		lblConversorDeCoordenadas.setBounds(12, 12, 877, 42);
		lblConversorDeCoordenadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversorDeCoordenadas.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblEsteConversorDe = new JLabel("Este conversor de coordenadas, permite cambiar coordenadas sexagesimales (X\u00B0X'X'') a Decimales (X.XXXX), ya sea un registro, o un archivo CSV");
		lblEsteConversorDe.setBounds(12, 91, 803, 16);
		lblEsteConversorDe.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblFormatoArchivoCsv = new JLabel("Formato archivo CSV de entrada para convertir coordenadas Sexagesimales a Decimales");
		lblFormatoArchivoCsv.setBounds(12, 142, 803, 19);
		lblFormatoArchivoCsv.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblCabeceras = new JLabel("Cabeceras");
		lblCabeceras.setBounds(12, 173, 803, 16);
		lblCabeceras.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblUpm = new JLabel("UPM,Grados Y,Minutos Y, Segundos Y,Grados X,Minutos X, Segundos X");
		lblUpm.setBounds(12, 195, 803, 16);
		lblUpm.setFont(new Font("Dialog", Font.ITALIC, 14));
		
		JLabel label = new JLabel("Formato archivo CSV Sexagesimales a Decimales");
		label.setBounds(12, 647, 803, 19);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel label_1 = new JLabel("Cabeceras");
		label_1.setBounds(12, 678, 803, 16);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_2 = new JLabel("UPM,Grados Y,Minutos Y, Segundos Y,Grados X,Minutos X, segundos X");
		label_2.setBounds(12, 700, 803, 16);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblUpmdecimalesYDecimales = new JLabel("UPM,Decimales Y, Decimales X");
		lblUpmdecimalesYDecimales.setBounds(12, 316, 803, 16);
		lblUpmdecimalesYDecimales.setFont(new Font("Dialog", Font.ITALIC, 14));
		
		JLabel label_4 = new JLabel("Cabeceras");
		label_4.setBounds(12, 294, 803, 16);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblFormatoArchivoCsv_1 = new JLabel("Formato archivo CSV de entrada para convertir coordenadas Decimales a Sexagesimales");
		lblFormatoArchivoCsv_1.setBounds(12, 263, 803, 19);
		lblFormatoArchivoCsv_1.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.setLayout(null);
		panel.add(lblUpmdecimalesYDecimales);
		panel.add(label_4);
		panel.add(lblFormatoArchivoCsv_1);
		panel.add(lblConversorDeCoordenadas);
		panel.add(lblEsteConversorDe);
		panel.add(lblFormatoArchivoCsv);
		panel.add(lblCabeceras);
		panel.add(lblUpm);
		panel.add(label);
		panel.add(label_1);
		panel.add(label_2);
	}

}
