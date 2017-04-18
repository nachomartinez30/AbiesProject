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
import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import com.toedter.components.JSpinField;

import Controllers.UPM;
import Database.ExternalConnection;

import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmEstadisticas extends JInternalFrame {
	public UPM upm = new UPM();
	private JTextField txtTotalUpms;
	
	private JTable tblUpmPorEstado;
	private JTable tblTipoUPM;
	private JTable tblDiasMuestreo;
	private JTable tblColocacionTAG;
	
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	
	private JFormattedTextField ftxtfechaInicial;
	private JFormattedTextField ftxtfechafinal;
	
	public String[] columnNameEstados={"Estado","Conteo UPMs"};
	public String[] columnNameDiasMuestreados = { "Dias", "Conteo UPMs" };
	public String[] columnNameColocacionTag = { "Colocacion TAG", "Conteo UPMs" };
	public String[] columnNameTipoUpm = { "Tipo UPM", "Conteo UPMs" };
	
	public DefaultTableModel UpmsPorEstadoModelClear = new DefaultTableModel(columnNameEstados, 0);
	public DefaultTableModel TipoUpmModelClear = new DefaultTableModel(columnNameTipoUpm, 0);
	public DefaultTableModel DiasMuestreoModelClear = new DefaultTableModel(columnNameDiasMuestreados, 0);
	public DefaultTableModel ColocacionTagModelClear = new DefaultTableModel(columnNameColocacionTag, 0);
	
	public DefaultTableModel UpmsPorEstadoModel = new DefaultTableModel(null, columnNameEstados);
	public DefaultTableModel TipoUpmModel = new DefaultTableModel(null, columnNameTipoUpm);
	public DefaultTableModel DiasMuestreoModel = new DefaultTableModel(null, columnNameDiasMuestreados);
	public DefaultTableModel ColocacionTagModel = new DefaultTableModel(null, columnNameColocacionTag);
	
	public Object[] UpmsPorEstadoVector =new Object[2];
	public Object[] TipoUpmVector =new Object[2];
	public Object[] DiasMuestreoVector =new Object[2];
	public Object[] ColocacionTagVector =new Object[2];


	public FrmEstadisticas() {
		
		setClosable(true);
		setTitle("Estadistica");
		setFrameIcon(null);
		setBounds(100, 100, 771, 702);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JLayeredPane layPanUpm = new JLayeredPane();
		tabbedPane.addTab("UPM", null, layPanUpm, null);

		JLabel lblUpmsPorEstado = new JLabel("UPMs por estado");
		lblUpmsPorEstado.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPanePorEstado = new JScrollPane();

		tblUpmPorEstado = new JTable();

		scrollPanePorEstado.setViewportView(tblUpmPorEstado);
		scrollPanePorEstado.setPreferredSize(layPanUpm.getParent().getPreferredSize());

		JLabel lblRelacionPorTipo = new JLabel("Relacion por tipo de conglomerado");
		lblRelacionPorTipo.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPaneTipoUPM = new JScrollPane();

		tblTipoUPM = new JTable();
		
		scrollPaneTipoUPM.setViewportView(tblTipoUPM);

		JLabel lblEstadisticasDeUpm = new JLabel("Informe General BD (UPM)");
		lblEstadisticasDeUpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticasDeUpm.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEstadisticasDeUpm.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblFechaInicial = new JLabel("Fecha inicial");

		JLabel label = new JLabel("Fecha final");

		ftxtfechaInicial = new JFormattedTextField();
		ftxtfechaInicial.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtfechaInicial.setEditable(false);
		ftxtfechaInicial.setAlignmentX(Component.RIGHT_ALIGNMENT);

		ftxtfechafinal = new JFormattedTextField();
		ftxtfechafinal.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtfechafinal.setEditable(false);
		ftxtfechafinal.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel lblUpmTotales = new JLabel("UPMs Totales:");
		lblUpmTotales.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtTotalUpms = new JTextField();
		txtTotalUpms.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalUpms.setEditable(false);
		txtTotalUpms.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		txtTotalUpms.setColumns(10);

		JSeparator separator = new JSeparator();

		JLabel lblDiasDeMuestro = new JLabel("Dias de muestro por UPM");
		lblDiasDeMuestro.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPaneDiasDeMuestreo = new JScrollPane();
		scrollPaneDiasDeMuestreo.setAlignmentX(Component.LEFT_ALIGNMENT);

		tblDiasMuestreo = new JTable();
		scrollPaneDiasDeMuestreo.setViewportView(tblDiasMuestreo);

		JLabel lblColocacinDelTag = new JLabel("Colocaci\u00F3n del TAG");
		lblColocacinDelTag.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPane_1 = new JScrollPane();

		tblColocacionTAG = new JTable();
		scrollPane_1.setViewportView(tblColocacionTAG);
		GroupLayout gl_layPanUpm = new GroupLayout(layPanUpm);
		gl_layPanUpm.setHorizontalGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layPanUpm.createSequentialGroup().addGap(16)
						.addComponent(lblEstadisticasDeUpm, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE).addGap(21))
				.addGroup(gl_layPanUpm.createSequentialGroup().addGap(154).addGroup(gl_layPanUpm
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_layPanUpm.createSequentialGroup()
								.addComponent(lblUpmTotales, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(txtTotalUpms, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addGap(146)
								.addComponent(lblFechaInicial, GroupLayout.PREFERRED_SIZE, 74,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12))
						.addGroup(gl_layPanUpm.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
								.addComponent(ftxtfechaInicial, GroupLayout.PREFERRED_SIZE, 112,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(ftxtfechafinal, GroupLayout.PREFERRED_SIZE, 112,
										GroupLayout.PREFERRED_SIZE))
						.addGap(121))
				.addGroup(gl_layPanUpm.createSequentialGroup().addGap(10)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE).addGap(6))
				.addGroup(Alignment.TRAILING, gl_layPanUpm.createSequentialGroup().addGap(41)
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDiasDeMuestro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319,
										Short.MAX_VALUE)
								.addComponent(scrollPaneDiasDeMuestreo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										319, Short.MAX_VALUE))
						.addGap(66)
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
								.addComponent(lblColocacinDelTag, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
						.addGap(40))
				.addGroup(Alignment.TRAILING, gl_layPanUpm.createSequentialGroup().addGap(26)
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPanePorEstado, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
								.addComponent(lblUpmsPorEstado, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
						.addGap(81)
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRelacionPorTipo, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
								.addComponent(scrollPaneTipoUPM, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
						.addGap(40)));
		gl_layPanUpm.setVerticalGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING).addGroup(gl_layPanUpm
				.createSequentialGroup().addGap(6)
				.addComponent(lblEstadisticasDeUpm, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
				.addGap(25)
				.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING).addGroup(gl_layPanUpm
						.createSequentialGroup()
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtfechaInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaInicial))
						.addGap(6)
						.addGroup(gl_layPanUpm.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtfechafinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label)))
						.addGroup(gl_layPanUpm.createSequentialGroup().addGap(6)
								.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_layPanUpm.createSequentialGroup().addGap(6)
												.addComponent(lblUpmTotales))
										.addComponent(txtTotalUpms, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING).addComponent(lblUpmsPorEstado)
						.addComponent(lblRelacionPorTipo))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanePorEstado, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(scrollPaneTipoUPM, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
				.addGap(116)
				.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING).addComponent(lblColocacinDelTag)
						.addComponent(lblDiasDeMuestro))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_layPanUpm.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneDiasDeMuestreo, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
				.addGap(101)));
		layPanUpm.setLayout(gl_layPanUpm);
		layPanUpm.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { txtTotalUpms, ftxtfechaInicial, ftxtfechafinal, scrollPanePorEstado,
						scrollPaneTipoUPM, scrollPaneDiasDeMuestreo, scrollPane_1, lblUpmsPorEstado, tblUpmPorEstado,
						lblRelacionPorTipo, tblTipoUPM, lblEstadisticasDeUpm, lblFechaInicial, label, lblUpmTotales,
						separator, lblDiasDeMuestro, tblDiasMuestreo, lblColocacinDelTag, tblColocacionTAG }));

		JLayeredPane layPanSitio = new JLayeredPane();
		tabbedPane.addTab("Sitio", null, layPanSitio, null);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { tabbedPane, layPanUpm,
				lblUpmsPorEstado, scrollPanePorEstado, tblUpmPorEstado, lblRelacionPorTipo, scrollPaneTipoUPM,
				tblTipoUPM, lblEstadisticasDeUpm, lblFechaInicial, label, ftxtfechaInicial, ftxtfechafinal,
				lblUpmTotales, txtTotalUpms, separator, lblDiasDeMuestro, scrollPaneDiasDeMuestreo, tblDiasMuestreo,
				lblColocacinDelTag, scrollPane_1, tblColocacionTAG, layPanSitio }));
		
		clearTablas();

	}

	public void getMaxMinFechas(String ruta) {
		String fechaInicio = null,fechaFin = null;
		String query = "SELECT MIN(FechaInicio) as Min, MAX(FechaFin) as Max FROM UPM_UPM ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				fechaInicio = rsExterno.getString("Min");
				fechaFin= rsExterno.getString("Max");
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		ftxtfechafinal.setText(fechaFin);
		ftxtfechaInicial.setText(fechaInicio);
	}
	
	public void getCalculoUpmsTotales(String ruta) {
		int upmTotales = 0;
		String query = "SELECT DISTINCT COUNT(UPMID) as TOTALES FROM UPM_UPM upm ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				upmTotales = rsExterno.getInt("TOTALES");
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		txtTotalUpms.setText(Integer.toString(upmTotales));
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void getCalculoUpmsEstado(String ruta) {
		String query = "SELECT Estado, Count(Estado) AS Conteo_UPMS FROM UPM_UPM upm LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID GROUP BY Estado ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				UpmsPorEstadoVector[0] = rsExterno.getString("Estado");
				UpmsPorEstadoVector[1] = rsExterno.getInt("Conteo_UPMS");
				UpmsPorEstadoModel.addRow(UpmsPorEstadoVector);

			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tblUpmPorEstado.setModel(UpmsPorEstadoModel);
		System.out.println(UpmsPorEstadoVector.length);
		
	}

	public void getCalculoUpmsTipo(String ruta) {
		String query = "SELECT tipoUPM.TipoUPM AS TipoUPM , Count(upm.UPMID) AS Conteo_UPMS FROM UPM_UPM upm LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID GROUP BY tipoUPM.TipoUPM ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				TipoUpmVector[0] = rsExterno.getString("TipoUPM");
				TipoUpmVector[1] = rsExterno.getInt("Conteo_UPMS");
				TipoUpmModel.addRow(TipoUpmVector);
				
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tblTipoUPM.setModel(TipoUpmModel);
	}

	public void getCalculoUpmsDiasMuestreados(String ruta) {
		String query = "SELECT DISTINCT julianday(upm.FechaFin)-julianday(upm.FechaInicio)+1 as DIAS, COUNT(upm.UPMID) AS CUENTA_UPMS FROM UPM_UPM upm LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID GROUP BY DIAS ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				DiasMuestreoVector[0] = rsExterno.getInt("DIAS");
				DiasMuestreoVector[1] = rsExterno.getInt("CUENTA_UPMS");
				DiasMuestreoModel.addRow(DiasMuestreoVector);
				
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tblDiasMuestreo.setModel(DiasMuestreoModel);
}

	public void getCalculoUpmsColocacionTag(String ruta) {
		String query = "SELECT tipoColocacion.Descripcion as Colocacion, Count(transponder.UPMID) as Conteo_UPMS FROM SITIOS_Transponder transponder LEFT JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=transponder.TipoColocacionID GROUP BY tipoColocacion.Descripcion ";

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);
		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);
			while (rsExterno.next()) {

				ColocacionTagVector[0] = rsExterno.getString("Colocacion");
				ColocacionTagVector[1] = rsExterno.getInt("Conteo_UPMS");
				ColocacionTagModel.addRow(ColocacionTagVector);
				
			}
			baseDatosExterna.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tblColocacionTAG.setModel(ColocacionTagModel);
}
	
	public void clearTablas(){
		tblUpmPorEstado.setModel(UpmsPorEstadoModelClear);
		tblTipoUPM.setModel(TipoUpmModelClear);
		tblDiasMuestreo.setModel(DiasMuestreoModelClear);
		tblColocacionTAG.setModel(ColocacionTagModelClear);	
	}
}
