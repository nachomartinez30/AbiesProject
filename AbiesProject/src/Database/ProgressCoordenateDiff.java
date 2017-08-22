package Database;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class ProgressCoordenateDiff extends SwingWorker<Integer, String> {

	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	JTable table;
	public String[] columnNameCoordenadas = { "UPM", "Lat Teorica", "Long Teorica", "Lat Capturada", "Long Capturada",
			"Tipo UPM", "Evidencia_Muestreo", "Error presición", "Diferencia m:" };
	public DefaultTableModel CoordenadasModel = new DefaultTableModel(null, columnNameCoordenadas);
	public DefaultTableModel CoordenadasModelNew = new DefaultTableModel(null, columnNameCoordenadas);
	JFrame calidad;
	JProgressBar barraProgreso;
	public String ruta;


	public ProgressCoordenateDiff(JProgressBar barraProgreso, String ruta, JTable table) {
		super();
		this.barraProgreso = barraProgreso;
		this.ruta = ruta;
		this.table = table;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		barraProgreso.setIndeterminate(true);
		getCoordenates(ruta);
		barraProgreso.setIndeterminate(false);
		return 0;
	}

	public void getCoordenates(String ruta) {
		table.setModel(CoordenadasModelNew);
		String query = "SELECT sitio.ErrorPresicion,CASE sitio.EvidenciaMuestreo when 1 then 'SI' when 0 then 'NO' END Evidencia_Muestreo,tipoUPM.TipoUPM as Tipo_UPM, upmMalla.UPMID, upmMalla.LatDms as CoordenadasLongitud_Teoricas, upmMalla.LongDms as CoordenadasLatitud_Teoricas, upmMalla.X as X_Teorico, upmMalla.Y as Y_Teorico, sitio.UPMID as UPM_Capturado, -1 * ((-1 * sitio.GradosLongitud) + (sitio.MinutosLongitud / 60.0) +  (sitio.SegundosLongitud / 3600.0)) AS X_Capturado, sitio.GradosLatitud + (sitio.MinutosLatitud / 60.0) +  (sitio.SegundosLatitud / 3600.0) AS Y_Capturado, sitio.GradosLongitud || ' '||sitio.MinutosLongitud || ' '||sitio.SegundosLongitud||' ' as CoordenadasLongitud_capturadas, sitio.GradosLatitud || ' '||sitio.MinutosLatitud || ' '||sitio.SegundosLatitud||' ' as CoordenadasLatitud_capturadas FROM SITIOS_Sitio sitio  join UPM_UPM upm ON sitio.UPMID= upm.UPMID JOIN UPM_MallaPuntos upmMalla ON sitio.UPMID=upmMalla.UPMID LEFT JOIN CAT_TipoUPM tipoUPM ON upm.TipoUPMID = tipoUPM.TipoUPMID WHERE sitio.Sitio=1 order by tipoUPM.TipoUPM";
		String UPMID, lat_teo_string, long_teo_string, lat_cap_string, long_cap_string, tipo_upm, incial_remplazo;
		int erro_presicion;
		double lat_teo_flot, long_teo_flot, lat_cap_flot, long_cap_flot, diferencia;

		this.baseDatosExterna = ExternalConnection.getConnection(ruta);

		try {
			sqlExterno = baseDatosExterna.createStatement();
			ResultSet rsExterno = sqlExterno.executeQuery(query);

			while (rsExterno.next()) {

				UPMID = rsExterno.getString("UPMID");

				lat_teo_string = rsExterno.getString("CoordenadasLatitud_Teoricas");
				long_teo_string = rsExterno.getString("CoordenadasLongitud_Teoricas");
				lat_cap_string = rsExterno.getString("CoordenadasLatitud_capturadas");
				long_cap_string = rsExterno.getString("CoordenadasLongitud_capturadas");

				lat_teo_flot = rsExterno.getDouble("Y_Teorico");
				long_teo_flot = rsExterno.getDouble("X_Teorico");
				lat_cap_flot = rsExterno.getDouble("Y_Capturado");
				long_cap_flot = rsExterno.getDouble("X_Capturado");

				tipo_upm = rsExterno.getString("Tipo_UPM");
				incial_remplazo = rsExterno.getString("Evidencia_Muestreo");
				erro_presicion = rsExterno.getInt("ErrorPresicion");

				diferencia = Math.round(diferenciaDistancia(lat_teo_flot, long_teo_flot, lat_cap_flot, long_cap_flot));
				if (diferencia > 12.55) {
					CoordenadasModel.addRow(new Object[] { UPMID, lat_teo_string, "-" + long_teo_string, lat_cap_string,
							long_cap_string, tipo_upm, incial_remplazo, erro_presicion, (int) diferencia });
				}

			}
			baseDatosExterna.close();
			table.setModel(CoordenadasModel);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static double diferenciaDistancia(double Lat_1, double Long_1, double Lat_2, double Long_2) {
		double resultado = 0;
		System.out.println();
		resultado = Math.acos(
				Math.sin(Math.toRadians(Lat_1)) * Math.sin(Math.toRadians(Lat_2)) + Math.cos(Math.toRadians(Lat_1))
						* Math.cos(Math.toRadians(Lat_2)) * Math.cos(Math.toRadians(Long_1) - Math.toRadians(Long_2)))
				* 6371;
		return resultado * 1000;
	}
}
