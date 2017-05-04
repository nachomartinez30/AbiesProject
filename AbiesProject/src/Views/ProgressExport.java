package Views;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.TreeWillExpandListener;

import com.csvreader.CsvWriter;

import Database.ExternalConnection;

public class ProgressExport extends SwingWorker<Integer, String> {
	JProgressBar barraProgreso;
	JButton btnExportar;
	JCheckBox chkupms, chksitios, chkarbolado, chksotobosque, chkrepoblado, chktodo;
	JLabel etiquetaExportacion;
	String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;
	public String exportPath;
	boolean arbolado = false, upms = false, sitios = false, repoblado = false, sotobosque = false;

	public ProgressExport(JProgressBar barraProgreso, JLabel etiquetaExportacion, String ruta, String exportPath,
			JCheckBox chkupms, JCheckBox chksitios, JCheckBox chkarbolado, JCheckBox chksotobosque,
			JCheckBox chkrepoblado, JCheckBox chkTodo, JButton btnExportar) {
		super();
		this.barraProgreso = barraProgreso;
		this.etiquetaExportacion = etiquetaExportacion;
		this.ruta = ruta;
		this.exportPath = exportPath;
		this.chkupms = chkupms;
		this.chksitios = chksitios;
		this.chkarbolado = chkarbolado;
		this.btnExportar = btnExportar;
		this.chksotobosque = chksotobosque;
		this.chkrepoblado = chkrepoblado;
		this.chktodo = chkTodo;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		setCargandoComponentesGraficos();
		if (upms == true) {
			etiquetaExportacion.setText("Exportando: UPM's...");
			exportarUPMs(ruta);
			Thread.sleep(800);
			upms = false;
		}
		if (sitios == true) {
			/* exportarSitios(ruta); */
			etiquetaExportacion.setText("Exportando: Sitios...");
			exportarSitios(ruta);
			sitios = false;
		}
		if (arbolado == true) {
			etiquetaExportacion.setText("Exportando: Arbolado...");
			exportarArbolado(ruta);
			arbolado = false;
		}
		if (repoblado == true) {
			etiquetaExportacion.setText("Exportando: Repoblado...");
			exportarRepoblado(ruta);
			repoblado = false;
		}

		if (sotobosque == true) {
			etiquetaExportacion.setText("Exportando: Sotobosque...");
			exportarSotobosque(ruta);
			sotobosque = false;
		}

		getBarraProgreso().setIndeterminate(false);

		Thread.sleep(500);
		etiquetaExportacion.setText("Exportando:");
		JOptionPane.showMessageDialog(null, "Terminó exportacion con exito");
		btnExportar.setEnabled(true);
		return 0;

	}

	public JProgressBar getBarraProgreso() {
		return barraProgreso;
	}

	public void setBarraProgreso(JProgressBar barraProgreso) {
		this.barraProgreso = barraProgreso;
	}

	public JLabel getEtiquetaExportacion() {
		return etiquetaExportacion;
	}

	public void setEtiquetaExportacion(JLabel etiquetaExportacion) {
		this.etiquetaExportacion = etiquetaExportacion;
	}

	public boolean isArbolado() {
		return arbolado;
	}

	public void setArbolado(boolean arbolado) {
		this.arbolado = arbolado;
	}

	public boolean isUpms() {
		return upms;
	}

	public void setUpms(boolean upms) {
		this.upms = upms;
	}

	public boolean isSitios() {
		return sitios;
	}

	public void setSitios(boolean sitios) {
		this.sitios = sitios;
	}

	public JCheckBox getChkupms() {
		return chkupms;
	}

	public void setChkupms(JCheckBox chkupms) {
		this.chkupms = chkupms;
	}

	public JCheckBox getChksitios() {
		return chksitios;
	}

	public void setChksitios(JCheckBox chksitios) {
		this.chksitios = chksitios;
	}

	public JCheckBox getChkarbolado() {
		return chkarbolado;
	}

	public void setChkarbolado(JCheckBox chkarbolado) {
		this.chkarbolado = chkarbolado;
	}

	public boolean isRepoblado() {
		return repoblado;
	}

	public void setRepoblado(boolean repoblado) {
		this.repoblado = repoblado;
	}

	public boolean isSotobosque() {
		return sotobosque;
	}

	public void setSotobosque(boolean sotobosque) {
		this.sotobosque = sotobosque;
	}

	public void exportarUPMs(String ruta) {

		try {
			String exportPathUPM = exportPath + "_UPMs" + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPathUPM), ',');
			String query = "SELECT  upm.UPMID, CASE upmMalla.A WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END A, CASE upmMalla.B WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END B, CASE upmMalla.C WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END C, CASE upmMalla.D WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END D, CASE upmMalla.E WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END E, CASE upmMalla.F WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END F, CASE upmMalla.G WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END G, CASE upmMalla.H WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END H, upmMalla.Estado, upmMalla.Municipio, upmMalla.Region, CASE upmMalla.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor, upm.FechaInicio, upm.FechaFin, upm.Altitud, upm.PendienteRepresentativa, tipoExposicion.Descripcion AS Exposicion, tipoFisiografia.TipoFisiografia AS Fisiografia, upm.Predio, upm.Paraje, tipoTenencia.Descripcion AS TipoTenencia, CASE upm.Accesible WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END Accesible, upm.GradosLatitud, upm.MinutosLatitud, upm.SegundosLatitud, upm.GradosLongitud, upm.MinutosLongitud, upm.SegundosLongitud, upm.Datum, upm.ErrorPresicion, upm.Azimut, upm.Distancia, upm.TipoInaccesibilidadID, upm.OtroTipoInaccesibilidad, upm.ExplicacionInaccesibilidad, CASE upm.InformacionContacto WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END HuboContacto, tipoColocacion.Descripcion AS TipoColocacion, sitioTransponder.Especifique, sitioTransponder.Observaciones as Observaciones_Transponder, sitioObservaciones.Observaciones as Observaciones_Sitio, CASE upmContacto.TipoContacto WHEN 1 THEN 'Precencial' WHEN 2 THEN 'Remoto' END TipoContacto, upmContacto.Nombre, upmContacto.Direccion, CASE upmContacto.TipoTelefono WHEN 0 THEN 'N/A' WHEN 1 THEN 'Fijo' WHEN 2 THEN 'Movil' END TipoTelefono, upmContacto.NumeroTelefono, CASE upmContacto.TieneCorreo WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END TieneCorreo, upmContacto.DireccionCorreo, CASE upmContacto.TieneRadio WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END TieneRadio, upmContacto.Canal, upmContacto.Frecuencia, upmContacto.Observaciones as Observaciones_contacto  FROM UPM_UPM upm  LEFT JOIN UPM_Contacto upmContacto ON upmContacto.UPMID=upm.UPMID LEFT JOIN SITIOS_Sitio sitio ON sitio.UPMID=upm.UPMID LEFT JOIN SITIOS_Transponder sitioTransponder ON sitioTransponder.SitioID=sitio.SitioID LEFT JOIN SITIOS_Observaciones sitioObservaciones ON sitioObservaciones.SitioID=sitio.SitioID INNER JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID INNER JOIN UPM_Brigada upmBrigada ON upmBrigada.UPMID=upm.UPMID LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID LEFT JOIN CAT_TipoFisiografia tipoFisiografia ON tipoFisiografia.FisiografiaID=upm.FisiografiaID LEFT JOIN CAT_TipoExposicion tipoExposicion ON tipoExposicion.ExposicionID=upm.ExposicionID LEFT JOIN CAT_TipoTenencia tipoTenencia ON tipoTenencia. TipoTenenciaID=upm.TipoTenenciaID LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=upm.TipoInaccesibilidadID  JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=sitioTransponder.TipoColocacionID GROUP BY upm.UPMID ORDER BY upm.UPMID ";
			this.baseDatosExterna = ExternalConnection.getConnection(ruta);

			// before we open the file check to see if it already exists

			try {
				// use FileWriter constructor that specifies open for appending
				writer.write("UPMID");
				writer.write("A");
				writer.write("B");
				writer.write("C");
				writer.write("D");
				writer.write("E");
				writer.write("F");
				writer.write("G");
				writer.write("H");
				writer.write("Estado");
				writer.write("Municipio");
				writer.write("Region");
				writer.write("Proveedor");
				writer.write("FechaInicio");
				writer.write("FechaFin");
				writer.write("Altitud");
				writer.write("PendienteRepresentativa");
				writer.write("Exposicion");
				writer.write("Fisiografia");
				writer.write("Predio");
				writer.write("Paraje");
				writer.write("TipoTenencia");
				writer.write("Accesible");
				writer.write("GradosLatitud");
				writer.write("MinutosLatitud");
				writer.write("SegundosLatitud");
				writer.write("GradosLongitud");
				writer.write("MinutosLongitud");
				writer.write("SegundosLongitud");
				writer.write("Datum");
				writer.write("ErrorPresicion");
				writer.write("Azimut");
				writer.write("Distancia");
				writer.write("TipoInaccesibilidadID");
				writer.write("OtroTipoInaccesibilidad");
				writer.write("ExplicacionInaccesibilidad");
				writer.write("HuboContacto");
				writer.write("TipoColocacion");
				writer.write("Especifique");
				writer.write("Observaciones_Transponder");
				writer.write("Observaciones_Sitio");
				writer.write("TipoContacto");
				writer.write("Nombre");
				writer.write("Direccion");
				writer.write("TipoTelefono");
				writer.write("NumeroTelefono");
				writer.write("TieneCorreo");
				writer.write("DireccionCorreo");
				writer.write("TieneRadio");
				writer.write("Canal");
				writer.write("Frecuencia");
				writer.write("Observaciones_contacto");

				writer.endRecord();

				sqlExterno = baseDatosExterna.createStatement();
				ResultSet rsExterno = sqlExterno.executeQuery(query);

				while (rsExterno.next()) {
					writer.write(rsExterno.getString("UPMID"));
					writer.write(rsExterno.getString("A"));
					writer.write(rsExterno.getString("B"));
					writer.write(rsExterno.getString("C"));
					writer.write(rsExterno.getString("D"));
					writer.write(rsExterno.getString("E"));
					writer.write(rsExterno.getString("F"));
					writer.write(rsExterno.getString("G"));
					writer.write(rsExterno.getString("H"));
					writer.write(rsExterno.getString("Estado"));
					writer.write(rsExterno.getString("Municipio"));
					writer.write(rsExterno.getString("Region"));
					writer.write(rsExterno.getString("Proveedor"));
					writer.write(rsExterno.getString("FechaInicio"));
					writer.write(rsExterno.getString("FechaFin"));
					writer.write(rsExterno.getString("Altitud"));
					writer.write(rsExterno.getString("PendienteRepresentativa"));
					writer.write(rsExterno.getString("Exposicion"));
					writer.write(rsExterno.getString("Fisiografia"));
					writer.write(rsExterno.getString("Predio"));
					writer.write(rsExterno.getString("Paraje"));
					writer.write(rsExterno.getString("TipoTenencia"));
					writer.write(rsExterno.getString("Accesible"));
					writer.write(rsExterno.getString("GradosLatitud"));
					writer.write(rsExterno.getString("MinutosLatitud"));
					writer.write(rsExterno.getString("SegundosLatitud"));
					writer.write(rsExterno.getString("GradosLongitud"));
					writer.write(rsExterno.getString("MinutosLongitud"));
					writer.write(rsExterno.getString("SegundosLongitud"));
					writer.write(rsExterno.getString("Datum"));
					writer.write(rsExterno.getString("ErrorPresicion"));
					writer.write(rsExterno.getString("Azimut"));
					writer.write(rsExterno.getString("Distancia"));
					writer.write(rsExterno.getString("TipoInaccesibilidadID"));
					writer.write(rsExterno.getString("OtroTipoInaccesibilidad"));
					writer.write(rsExterno.getString("ExplicacionInaccesibilidad"));
					writer.write(rsExterno.getString("HuboContacto"));
					writer.write(rsExterno.getString("TipoColocacion"));
					writer.write(rsExterno.getString("Especifique"));
					writer.write(rsExterno.getString("Observaciones_Transponder"));
					writer.write(rsExterno.getString("Observaciones_Sitio"));
					writer.write(rsExterno.getString("TipoContacto"));
					writer.write(rsExterno.getString("Nombre"));
					writer.write(rsExterno.getString("Direccion"));
					writer.write(rsExterno.getString("TipoTelefono"));
					writer.write(rsExterno.getString("NumeroTelefono"));
					writer.write(rsExterno.getString("TieneCorreo"));
					writer.write(rsExterno.getString("DireccionCorreo"));
					writer.write(rsExterno.getString("TieneRadio"));
					writer.write(rsExterno.getString("Canal"));
					writer.write(rsExterno.getString("Frecuencia"));
					writer.write(rsExterno.getString("Observaciones_contacto"));

					writer.endRecord();
				}

				writer.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al exportar UPM's\n" + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}

	}

	public void exportarArbolado(String ruta) {
		try {

			String exportPathArbolado = exportPath + "_Arbolado" + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPathArbolado), ',');

			String query = "SELECT  arbolado.UPMID, arbolado.SitioID, arbolado.ArboladoID, upmMala.Estado, upmMala.Municipio, CASE upmMala.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor, upm.FechaInicio, upm.FechaFin, upm.Altitud, upm.PendienteRepresentativa, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia, sitio.Sitio, claveSerieV.TipoVegetacion , faseSucecional.Clave  AS FaseSucecional, CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera, sitio.CondicionEcotono, arbolado.Consecutivo, arbolado.NoIndividuo AS Individuo, arbolado.NoRama AS Rama, arbolado.Azimut, arbolado.Distancia, familia.Nombre AS Familia, genero.Nombre AS Genero, especie.Nombre AS Especie, infraespecie.Nombre AS Infraespecie, arbolado.NombreComun, CASE arbolado.EsSubmuestra WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END EsSubmuestra, formaVida.Descripcion AS FormaVida, formaFuste.Descripcion AS FormaFuste, condicion.Descripcion AS Condicion, muertoPie.Clave AS TipoMuertoPie, gradoPutrefaccion.Clave AS GradoPutrefaccion, tipoTocon.Clave AS TipoTocon, arbolado.DiametroNormal, arbolado.DiametroBasal, arbolado.AlturaTotal, arbolado.AnguloInclinacion, arbolado.AlturaFusteLimpio, arbolado.AlturaComercial, arbolado.DiametroCopaNS, arbolado.DiametroCopaEO, porcentajeCopaViva.Clave AS ProporcionCopaViva, exposicionLuzCopa.Codigo AS ExposicionLuzCopa, posicionCopa.PosicionCopa, densidadCopa.Clave AS DensidadCopa, muerteRegresiva.Clave AS MuerteRegresiva, transparenciaFollaje.Clave AS TransparenciaFollaje, agenteDanio1.Agente1, agenteDanio1.Severidad1, agenteDanio2.Agente2, agenteDanio2.Severidad2, vigor.Descripcion AS Vigor, nivelVigor.Descripcion AS NivelVigor, arbolado.ClaveColecta  FROM TAXONOMIA_Arbolado arbolado JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID JOIN UPM_UPM upm ON upm.UPMID=arbolado.UPMID JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=arbolado.UPMID LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID  LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID  LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID  LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID  LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID  LEFT JOIN CAT_TipoFormaFuste formaFuste ON arbolado.FormaFusteID = formaFuste.FormaFusteID  LEFT JOIN CAT_CondicionArbolado condicion ON arbolado.CondicionID = condicion.CondicionID  LEFT JOIN CAT_CondicionMuertoPie muertoPie ON arbolado.MuertoPieID = muertoPie.MuertoPieID  LEFT JOIN CAT_GradoPutrefaccionArbolado gradoPutrefaccion ON arbolado.GradoPutrefaccionID = gradoPutrefaccion.GradoPutrefaccionID  LEFT JOIN CAT_TipoTocon tipoTocon ON arbolado.TipoToconID = tipoTocon.TipoToconID LEFT JOIN CAT_PorcentajeArbolado porcentajeCopaViva ON arbolado.ProporcionCopaVivaID = porcentajeCopaViva.PorcentajeArboladoID  LEFT JOIN CAT_ExposicionLuzCopa exposicionLuzCopa ON arbolado.ExposicionCopaID = exposicionLuzCopa.ExposicionLuzID  LEFT JOIN CAT_PosicionCopa posicionCopa ON arbolado.PosicionCopaID = posicionCopa.PosicionCopaID  LEFT JOIN CAT_PorcentajeArbolado densidadCopa ON arbolado.DensidadCopaID = densidadCopa.PorcentajeArboladoID  LEFT JOIN CAT_PorcentajeArbolado muerteRegresiva ON arbolado.MuerteRegresivaID = muerteRegresiva.PorcentajeArboladoID  LEFT JOIN CAT_PorcentajeArbolado transparenciaFollaje ON arbolado.TransparenciaFollajeID = transparenciaFollaje.PorcentajeArboladoID  LEFT JOIN (         SELECT          agenteDanio.ArboladoID,          ca.Agente AS Agente1,          cp.Clave AS Severidad1         FROM ARBOLADO_DanioSeveridad agenteDanio         LEFT JOIN CAT_AgenteDanio ca ON agenteDanio.AgenteDanioID = ca.AgenteDanioID          LEFT JOIN CAT_PorcentajeArbolado cp ON agenteDanio.SeveridadID = cp.PorcentajeArboladoID          WHERE agenteDanio.NumeroDanio = 1         ) agenteDanio1 ON arbolado.ArboladoID = agenteDanio1.ArboladoID  LEFT JOIN (        SELECT         agenteDanio.ArboladoID,         ca.Agente AS Agente2,         cp.Clave AS Severidad2        FROM ARBOLADO_DanioSeveridad agenteDanio        LEFT JOIN CAT_AgenteDanio ca ON agenteDanio.AgenteDanioID = ca.AgenteDanioID         LEFT JOIN CAT_PorcentajeArbolado cp ON agenteDanio.SeveridadID = cp.PorcentajeArboladoID         WHERE agenteDanio.NumeroDanio = 2        ) agenteDanio2 ON arbolado.ArboladoID = agenteDanio2.ArboladoID LEFT JOIN CAT_TipoVigorArbolado vigor ON arbolado.VigorID = vigor.VigorID LEFT JOIN CAT_NivelVigor nivelVigor ON arbolado.NivelVigorID = nivelVigor.NivelVigorID GROUP BY arbolado.UPMID, arbolado.SitioID, arbolado.ArboladoID ORDER BY arbolado.UPMID";
			this.baseDatosExterna = ExternalConnection.getConnection(ruta);

			// before we open the file check to see if it already exists

			try {
				// use FileWriter constructor that specifies open for appending

				writer.write("UPMID");
				writer.write("SitioID");
				writer.write("ArboladoID");
				writer.write("Estado");
				writer.write("Municipio");
				writer.write("Proveedor");
				writer.write("FechaInicio");
				writer.write("FechaFin");
				writer.write("Altitud");
				writer.write("PendienteRepresentativa");
				writer.write("Descripcion");
				writer.write("TipoFisiografia");
				writer.write("Sitio");
				writer.write("TipoVegetacion");
				writer.write("FaseSucecional");
				writer.write("ArbolFuera");
				writer.write("CondicionEcotono");
				writer.write("Consecutivo");
				writer.write("Individuo");
				writer.write("Rama");
				writer.write("Azimut");
				writer.write("Distancia");
				writer.write("Familia");
				writer.write("Genero");
				writer.write("Especie");
				writer.write("Infraespecie");
				writer.write("NombreComun");
				writer.write("EsSubmuestra");
				writer.write("FormaVida");
				writer.write("FormaFuste");
				writer.write("Condicion");
				writer.write("TipoMuertoPie");
				writer.write("GradoPutrefaccion");
				writer.write("TipoTocon");
				writer.write("DiametroNormal");
				writer.write("DiametroBasal");
				writer.write("AlturaTotal");
				writer.write("AnguloInclinacion");
				writer.write("AlturaFusteLimpio");
				writer.write("AlturaComercial");
				writer.write("DiametroCopaNS");
				writer.write("DiametroCopaEO");
				writer.write("ProporcionCopaViva");
				writer.write("ExposicionLuzCopa");
				writer.write("PosicionCopa");
				writer.write("DensidadCopa");
				writer.write("MuerteRegresiva");
				writer.write("TransparenciaFollaje");
				writer.write("Agente1");
				writer.write("Severidad1");
				writer.write("Agente2");
				writer.write("Severidad2");
				writer.write("Vigor");
				writer.write("NivelVigor");
				writer.write("ClaveColecta");
				writer.endRecord();

				sqlExterno = baseDatosExterna.createStatement();
				ResultSet rsExterno = sqlExterno.executeQuery(query);

				while (rsExterno.next()) {
					writer.write(rsExterno.getString("UPMID"));
					writer.write(rsExterno.getString("SitioID"));
					writer.write(rsExterno.getString("ArboladoID"));
					writer.write(rsExterno.getString("Estado"));
					writer.write(rsExterno.getString("Municipio"));
					writer.write(rsExterno.getString("Proveedor"));
					writer.write(rsExterno.getString("FechaInicio"));
					writer.write(rsExterno.getString("FechaFin"));
					writer.write(rsExterno.getString("Altitud"));
					writer.write(rsExterno.getString("PendienteRepresentativa"));
					writer.write(rsExterno.getString("Exposicion"));
					writer.write(rsExterno.getString("Fisiografia"));
					writer.write(rsExterno.getString("Sitio"));
					writer.write(rsExterno.getString("TipoVegetacion"));
					writer.write(rsExterno.getString("FaseSucecional"));
					writer.write(rsExterno.getString("ArbolFuera"));
					writer.write(rsExterno.getString("CondicionEcotono"));
					writer.write(rsExterno.getString("Consecutivo"));
					writer.write(rsExterno.getString("Individuo"));
					writer.write(rsExterno.getString("Rama"));
					writer.write(rsExterno.getString("Azimut"));
					writer.write(rsExterno.getString("Distancia"));
					writer.write(rsExterno.getString("Familia"));
					writer.write(rsExterno.getString("Genero"));
					writer.write(rsExterno.getString("Especie"));
					writer.write(rsExterno.getString("Infraespecie"));
					writer.write(rsExterno.getString("NombreComun"));
					writer.write(rsExterno.getString("EsSubmuestra"));
					writer.write(rsExterno.getString("FormaVida"));
					writer.write(rsExterno.getString("FormaFuste"));
					writer.write(rsExterno.getString("Condicion"));
					writer.write(rsExterno.getString("TipoMuertoPie"));
					writer.write(rsExterno.getString("GradoPutrefaccion"));
					writer.write(rsExterno.getString("TipoTocon"));
					writer.write(rsExterno.getString("DiametroNormal"));
					writer.write(rsExterno.getString("DiametroBasal"));
					writer.write(rsExterno.getString("AlturaTotal"));
					writer.write(rsExterno.getString("AnguloInclinacion"));
					writer.write(rsExterno.getString("AlturaFusteLimpio"));
					writer.write(rsExterno.getString("AlturaComercial"));
					writer.write(rsExterno.getString("DiametroCopaNS"));
					writer.write(rsExterno.getString("DiametroCopaEO"));
					writer.write(rsExterno.getString("ProporcionCopaViva"));
					writer.write(rsExterno.getString("ExposicionLuzCopa"));
					writer.write(rsExterno.getString("PosicionCopa"));
					writer.write(rsExterno.getString("DensidadCopa"));
					writer.write(rsExterno.getString("MuerteRegresiva"));
					writer.write(rsExterno.getString("TransparenciaFollaje"));
					writer.write(rsExterno.getString("Agente1"));
					writer.write(rsExterno.getString("Severidad1"));
					writer.write(rsExterno.getString("Agente2"));
					writer.write(rsExterno.getString("Severidad2"));
					writer.write(rsExterno.getString("Vigor"));
					writer.write(rsExterno.getString("NivelVigor"));
					writer.write(rsExterno.getString("ClaveColecta"));
					writer.endRecord();
				}

				writer.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al exportar Arbolado\n" + e);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}

	}

	public void exportarSitios(String ruta) {
		try {
			String exportPathSitios = exportPath + "_Sitios" + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPathSitios), ',');
			String query = "SELECT upm.UPMID, upmMalla.Estado, upmMalla.Municipio, CASE upmMalla.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor, upm.FechaInicio, upm.FechaFin, upm.Altitud, upm.PendienteRepresentativa, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia, upmMalla.Region, sitio.Sitio, sitio.SenialGPS, sitio.GradosLatitud, sitio.MinutosLatitud, sitio.SegundosLatitud, sitio.GradosLongitud, sitio.MinutosLongitud, sitio.SegundosLongitud, sitio.ErrorPresicion, sitio.EvidenciaMuestreo, sitio.Datum, sitio.Azimut, sitio.Distancia, CASE sitio.SitioAccesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SitioAccesible, tipoInaccesibilidad.Tipo AS TipoInaccesibilidad, tipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad, sitio.ExplicacionInaccesibilidad, CASE claveSerieV.EsForestal WHEN 1 THEN 'FORESTAL' WHEN 0 THEN 'NO FORESTAL' END CoberturaForestal, CASE sitio.Condicion WHEN 1 THEN 'Primario' WHEN 0 THEN 'Secundario' END Condicion, claveSerieV.TipoVegetacion , faseSucecional.Clave  AS FaseSucecional, CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera, sitio.CondicionEcotono, sitio.Ecotono, sitio.CondicionPresenteCampo, sitio.Observaciones, sitio.HipsometroBrujula, sitio.CintaClinometroBrujula, sitio.Cuadrante1, sitio.Cuadrante2, sitio.Cuadrante3, sitio.Cuadrante4, sitio.Distancia1, sitio.Distancia2, sitio.Distancia3, sitio.Distancia4 from SITIOS_Sitio sitio LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad GROUP BY sitio.UPMID, sitio.SitioID ORDER BY sitio.UPMID";
			this.baseDatosExterna = ExternalConnection.getConnection(ruta);

			// before we open the file check to see if it already exists

			try {
				// use FileWriter constructor that specifies open for appending

				writer.write("UPMID");
				writer.write("Estado");
				writer.write("Municipio");
				writer.write("Proveedor");
				writer.write("FechaInicio");
				writer.write("FechaFin");
				writer.write("Altitud");
				writer.write("PendienteRepresentativa");
				writer.write("Exposicion");
				writer.write("Fisiografia");
				writer.write("Region");
				writer.write("Sitio");
				writer.write("SenialGPS");
				writer.write("GradosLatitud");
				writer.write("MinutosLatitud");
				writer.write("SegundosLatitud");
				writer.write("GradosLongitud");
				writer.write("MinutosLongitud");
				writer.write("SegundosLongitud");
				writer.write("ErrorPresicion");
				writer.write("EvidenciaMuestreo");
				writer.write("Datum");
				writer.write("Azimut");
				writer.write("Distancia");
				writer.write("SitioAccesible");
				writer.write("TipoInaccesibilidad");
				writer.write("DescripcionInaccesibilidad");
				writer.write("ExplicacionInaccesibilidad");
				writer.write("CoberturaForestal");
				writer.write("Condicion");
				writer.write("TipoVegetacion ");
				writer.write("FaseSucecional");
				writer.write("ArbolFuera");
				writer.write("CondicionEcotono");
				writer.write("Ecotono");
				writer.write("CondicionPresenteCampo");
				writer.write("Observaciones");
				writer.write("HipsometroBrujula");
				writer.write("CintaClinometroBrujula");
				writer.write("Cuadrante1");
				writer.write("Cuadrante2");
				writer.write("Cuadrante3");
				writer.write("Cuadrante4");
				writer.write("Distancia1");
				writer.write("Distancia2");
				writer.write("Distancia3");
				writer.write("Distancia");

				writer.endRecord();

				sqlExterno = baseDatosExterna.createStatement();
				ResultSet rsExterno = sqlExterno.executeQuery(query);

				while (rsExterno.next()) {

					writer.write(rsExterno.getString("UPMID"));
					writer.write(rsExterno.getString("Estado"));
					writer.write(rsExterno.getString("Municipio"));
					writer.write(rsExterno.getString("Proveedor"));
					writer.write(rsExterno.getString("FechaInicio"));
					writer.write(rsExterno.getString("FechaFin"));
					writer.write(rsExterno.getString("Altitud"));
					writer.write(rsExterno.getString("PendienteRepresentativa"));
					writer.write(rsExterno.getString("Exposicion"));
					writer.write(rsExterno.getString("Fisiografia"));
					writer.write(rsExterno.getString("Region"));
					writer.write(rsExterno.getString("Sitio"));
					writer.write(rsExterno.getString("SenialGPS"));
					writer.write(rsExterno.getString("GradosLatitud"));
					writer.write(rsExterno.getString("MinutosLatitud"));
					writer.write(rsExterno.getString("SegundosLatitud"));
					writer.write(rsExterno.getString("GradosLongitud"));
					writer.write(rsExterno.getString("MinutosLongitud"));
					writer.write(rsExterno.getString("SegundosLongitud"));
					writer.write(rsExterno.getString("ErrorPresicion"));
					writer.write(rsExterno.getString("EvidenciaMuestreo"));
					writer.write(rsExterno.getString("Datum"));
					writer.write(rsExterno.getString("Azimut"));
					writer.write(rsExterno.getString("Distancia"));
					writer.write(rsExterno.getString("SitioAccesible"));
					writer.write(rsExterno.getString("TipoInaccesibilidad"));
					writer.write(rsExterno.getString("DescripcionInaccesibilidad"));
					writer.write(rsExterno.getString("ExplicacionInaccesibilidad"));
					writer.write(rsExterno.getString("CoberturaForestal"));
					writer.write(rsExterno.getString("Condicion"));
					writer.write(rsExterno.getString("TipoVegetacion"));
					writer.write(rsExterno.getString("FaseSucecional"));
					writer.write(rsExterno.getString("ArbolFuera"));
					writer.write(rsExterno.getString("CondicionEcotono"));
					writer.write(rsExterno.getString("Ecotono"));
					writer.write(rsExterno.getString("CondicionPresenteCampo"));
					writer.write(rsExterno.getString("Observaciones"));
					writer.write(rsExterno.getString("HipsometroBrujula"));
					writer.write(rsExterno.getString("CintaClinometroBrujula"));
					writer.write(rsExterno.getString("Cuadrante1"));
					writer.write(rsExterno.getString("Cuadrante2"));
					writer.write(rsExterno.getString("Cuadrante3"));
					writer.write(rsExterno.getString("Cuadrante4"));
					writer.write(rsExterno.getString("Distancia1"));
					writer.write(rsExterno.getString("Distancia2"));
					writer.write(rsExterno.getString("Distancia3"));
					writer.write(rsExterno.getString("Distancia"));

					writer.endRecord();
				}
				writer.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al exportar Sitios\n" + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void exportarRepoblado(String ruta) {
		try {
			String exportPathRepoblado = exportPath + "_Repoblado" + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPathRepoblado), ',');
			String query = "Select upmRepoblado.UPMID, upmMallaRepoblado.Estado, upmMallaRepoblado.Municipio, CASE upmMallaRepoblado.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor, upmRepoblado.FechaInicio, upmRepoblado.FechaFin, upmRepoblado.Altitud, upmRepoblado.PendienteRepresentativa, exposicionUPM.Descripcion AS Exposicion, fisiografia.TipoFisiografia AS Fisiografia, sitioRepoblado.Sitio, claveSerieV.TipoVegetacion , faseSucecional.Clave  AS FaseSucecional, CASE sitioRepoblado.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera, sitioRepoblado.CondicionEcotono, CASE sitioRepoblado.RepobladoFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END RepobladoFuera, sitioRepoblado.PorcentajeRepoblado, CASE sitioRepoblado.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera, sitioRepoblado.PorcentajeSotobosqueFuera, repoblado.Consecutivo, familiaRepoblado.Nombre AS Familia, generoRepoblado.Nombre AS Genero, especieRepoblado.Nombre AS Especie, infraespecieRepoblado.Nombre AS Infraespecie, repoblado.EsColecta, repoblado.NombreComun, repoblado.Frecuencia025150, repoblado.Edad025150, repoblado.Frecuencia151275, repoblado.Edad151275, repoblado.Frecuencia275, repoblado.Edad275, vigorRepoblado.Descripcion AS Vigor, danioRepoblado.Agente AS Danio, repoblado.PorcentajeDanio, repoblado.ClaveColecta FROM  TAXONOMIA_Repoblado repoblado LEFT JOIN SITIOS_Sitio sitioRepoblado ON sitioRepoblado.SitioID=repoblado.SitioID LEFT JOIN UPM_UPM upmRepoblado ON upmRepoblado.UPMID=sitioRepoblado.UPMID LEFT JOIN UPM_MallaPuntos upmMallaRepoblado ON upmMallaRepoblado.UPMID=upmRepoblado.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitioRepoblado.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecional ON faseSucecional.FaseSucecionalID =sitioRepoblado.FaseSucecional LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upmRepoblado.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upmRepoblado.FisiografiaID LEFT JOIN CAT_FamiliaEspecie familiaRepoblado ON repoblado.FamiliaID = familiaRepoblado.FamiliaID  LEFT JOIN CAT_Genero generoRepoblado ON repoblado.GeneroID = generoRepoblado.GeneroID  LEFT JOIN CAT_Especie especieRepoblado ON repoblado.EspecieID = especieRepoblado.EspecieID  LEFT JOIN CAT_Infraespecie infraespecieRepoblado ON repoblado.InfraespecieID = infraespecieRepoblado.InfraespecieID  LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorRepoblado ON vigorRepoblado.VigorID=repoblado.VigorID LEFT JOIN CAT_AgenteDanio danioRepoblado ON danioRepoblado.AgenteDanioID=repoblado.DanioID GROUP BY repoblado.UPMID, repoblado.SitioID, repoblado.RepobladoID ORDER BY repoblado.UPMID";
			this.baseDatosExterna = ExternalConnection.getConnection(ruta);

			// before we open the file check to see if it already exists

			try {
				// use FileWriter constructor that specifies open for appending

				writer.write("UPMID");
				writer.write("Estado");
				writer.write("Municipio");
				writer.write("Proveedor");
				writer.write("FechaInicio");
				writer.write("FechaFin");
				writer.write("Altitud");
				writer.write("PendienteRepresentativa");
				writer.write("Exposicion");
				writer.write("Fisiografia");
				writer.write("Sitio");
				writer.write("TipoVegetacion ");
				writer.write("FaseSucecional");
				writer.write("ArbolFuera");
				writer.write("CondicionEcotono");
				writer.write("RepobladoFuera");
				writer.write("PorcentajeRepoblado");
				writer.write("SotobosqueFuera");
				writer.write("PorcentajeSotobosqueFuera");
				writer.write("Consecutivo");
				writer.write("Familia");
				writer.write("Genero");
				writer.write("Especie");
				writer.write("Infraespecie");
				writer.write("EsColecta");
				writer.write("NombreComun");
				writer.write("Frecuencia025150");
				writer.write("Edad025150");
				writer.write("Frecuencia151275");
				writer.write("Edad151275");
				writer.write("Frecuencia275");
				writer.write("Edad275");
				writer.write("Vigor");
				writer.write("Danio");
				writer.write("PorcentajeDanio");
				writer.write("ClaveColecta");

				writer.endRecord();

				sqlExterno = baseDatosExterna.createStatement();
				ResultSet rsExterno = sqlExterno.executeQuery(query);

				while (rsExterno.next()) {

					writer.write(rsExterno.getString("UPMID"));
					writer.write(rsExterno.getString("Estado"));
					writer.write(rsExterno.getString("Municipio"));
					writer.write(rsExterno.getString("Proveedor"));
					writer.write(rsExterno.getString("FechaInicio"));
					writer.write(rsExterno.getString("FechaFin"));
					writer.write(rsExterno.getString("Altitud"));
					writer.write(rsExterno.getString("PendienteRepresentativa"));
					writer.write(rsExterno.getString("Exposicion"));
					writer.write(rsExterno.getString("Fisiografia"));
					writer.write(rsExterno.getString("Sitio"));
					writer.write(rsExterno.getString("TipoVegetacion"));
					writer.write(rsExterno.getString("FaseSucecional"));
					writer.write(rsExterno.getString("ArbolFuera"));
					writer.write(rsExterno.getString("CondicionEcotono"));
					writer.write(rsExterno.getString("RepobladoFuera"));
					writer.write(rsExterno.getString("PorcentajeRepoblado"));
					writer.write(rsExterno.getString("SotobosqueFuera"));
					writer.write(rsExterno.getString("PorcentajeSotobosqueFuera"));
					writer.write(rsExterno.getString("Consecutivo"));
					writer.write(rsExterno.getString("Familia"));
					writer.write(rsExterno.getString("Genero"));
					writer.write(rsExterno.getString("Especie"));
					writer.write(rsExterno.getString("Infraespecie"));
					writer.write(rsExterno.getString("EsColecta"));
					writer.write(rsExterno.getString("NombreComun"));
					writer.write(rsExterno.getString("Frecuencia025150"));
					writer.write(rsExterno.getString("Edad025150"));
					writer.write(rsExterno.getString("Frecuencia151275"));
					writer.write(rsExterno.getString("Edad151275"));
					writer.write(rsExterno.getString("Frecuencia275"));
					writer.write(rsExterno.getString("Edad275"));
					writer.write(rsExterno.getString("Vigor"));
					writer.write(rsExterno.getString("Danio"));
					writer.write(rsExterno.getString("PorcentajeDanio"));
					writer.write(rsExterno.getString("ClaveColecta"));

					writer.endRecord();
				}

				writer.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al exportar Repoblado\n" + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void exportarSotobosque(String ruta) {
		try {
			String exportPathSotobosque = exportPath + "_Sotobosque" + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPathSotobosque), ',');
			String query = "Select upmSotobosque.UPMID, upmMallaSotobosque.Estado, upmMallaSotobosque.Municipio, CASE upmMallaSotobosque.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor, upmSotobosque.FechaInicio, upmSotobosque.FechaFin, upmSotobosque.Altitud, upmSotobosque.PendienteRepresentativa, exposicionUPMSotobosque.Descripcion AS Exposicion, fisiografiaSotobosque.TipoFisiografia AS Fisiografia, sitioSotobosque.Sitio, claveSerieVSotobosque.TipoVegetacion , faseSucecionalSotobosque.Clave  AS FaseSucecional, CASE sitioSotobosque.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera, sitioSotobosque.CondicionEcotono, CASE sitioSotobosque.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera, sitioSotobosque.PorcentajeSotobosqueFuera, sotobosque.Consecutivo, familiaSotobosque.Nombre AS Familia, generoSotobosque.Nombre AS Genero, especieSotobosque.Nombre AS Especie, infraespecieSotobosque.Nombre AS Infraespecie, sotobosque.EsColecta, sotobosque.NombreComun, sotobosque.Frecuencia025150, sotobosque.Cobertura025150, sotobosque.Frecuencia151275, sotobosque.Cobertura151275, sotobosque.Frecuencia275, sotobosque.Cobertura275, vigorSotobosque.Descripcion AS Vigor, danioSotobosque.Agente AS Danio, sotobosque.PorcentajeDanio, sotobosque.ClaveColecta FROM  TAXONOMIA_Sotobosque sotobosque LEFT JOIN SITIOS_Sitio sitioSotobosque ON sitioSotobosque.SitioID=sotobosque.SitioID LEFT JOIN UPM_UPM upmSotobosque ON upmSotobosque.UPMID=sitioSotobosque.UPMID LEFT JOIN UPM_MallaPuntos upmMallaSotobosque ON upmMallaSotobosque.UPMID=upmSotobosque.UPMID LEFT JOIN CAT_ClaveSerieV claveSerieVSotobosque ON claveSerieVSotobosque.ClaveSerieVID = sitioSotobosque.ClaveSerieV LEFT JOIN CAT_FaseSucecional faseSucecionalSotobosque ON faseSucecionalSotobosque.FaseSucecionalID =sitioSotobosque.FaseSucecional LEFT JOIN CAT_TipoExposicion exposicionUPMSotobosque ON exposicionUPMSotobosque.ExposicionID =upmSotobosque.ExposicionID LEFT JOIN CAT_TipoFisiografia fisiografiaSotobosque ON fisiografiaSotobosque.FisiografiaID=upmSotobosque.FisiografiaID LEFT JOIN CAT_FamiliaEspecie familiaSotobosque ON sotobosque.FamiliaID = familiaSotobosque.FamiliaID  LEFT JOIN CAT_Genero generoSotobosque ON sotobosque.GeneroID = generoSotobosque.GeneroID  LEFT JOIN CAT_Especie especieSotobosque ON sotobosque.EspecieID = especieSotobosque.EspecieID  LEFT JOIN CAT_Infraespecie infraespecieSotobosque ON sotobosque.InfraespecieID = infraespecieSotobosque.InfraespecieID  LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorSotobosque ON vigorSotobosque.VigorID=sotobosque.VigorID LEFT JOIN CAT_AgenteDanio danioSotobosque ON danioSotobosque.AgenteDanioID=sotobosque.DanioID GROUP BY sotobosque.UPMID, sotobosque.SitioID, sotobosque.SotoBosqueID ORDER BY sotobosque.UPMID ";
			this.baseDatosExterna = ExternalConnection.getConnection(ruta);

			// before we open the file check to see if it already exists

			try {
				// use FileWriter constructor that specifies open for appending

				writer.write("UPMID");
				writer.write("Estado");
				writer.write("Municipio");
				writer.write("Proveedor");
				writer.write("FechaInicio");
				writer.write("FechaFin");
				writer.write("Altitud");
				writer.write("PendienteRepresentativa");
				writer.write("Exposicion");
				writer.write("Fisiografia");
				writer.write("Sitio");
				writer.write("TipoVegetacion ");
				writer.write("FaseSucecional");
				writer.write("ArbolFuera");
				writer.write("CondicionEcotono");
				writer.write("SotobosqueFuera");
				writer.write("PorcentajeSotobosqueFuera");
				writer.write("Consecutivo");
				writer.write("Familia");
				writer.write("Genero");
				writer.write("Especie");
				writer.write("Infraespecie");
				writer.write("EsColecta");
				writer.write("NombreComun");
				writer.write("Frecuencia025150");
				writer.write("Cobertura025150");
				writer.write("Frecuencia151275");
				writer.write("Cobertura151275");
				writer.write("Frecuencia275");
				writer.write("Cobertura275");
				writer.write("Vigor");
				writer.write("Danio");
				writer.write("PorcentajeDanio");
				writer.write("ClaveColecta");

				writer.endRecord();

				sqlExterno = baseDatosExterna.createStatement();
				ResultSet rsExterno = sqlExterno.executeQuery(query);

				while (rsExterno.next()) {
					writer.write(rsExterno.getString("UPMID"));
					writer.write(rsExterno.getString("Estado"));
					writer.write(rsExterno.getString("Municipio"));
					writer.write(rsExterno.getString("Proveedor"));
					writer.write(rsExterno.getString("FechaInicio"));
					writer.write(rsExterno.getString("FechaFin"));
					writer.write(rsExterno.getString("Altitud"));
					writer.write(rsExterno.getString("PendienteRepresentativa"));
					writer.write(rsExterno.getString("Exposicion"));
					writer.write(rsExterno.getString("Fisiografia"));
					writer.write(rsExterno.getString("Sitio"));
					writer.write(rsExterno.getString("TipoVegetacion"));
					writer.write(rsExterno.getString("FaseSucecional"));
					writer.write(rsExterno.getString("ArbolFuera"));
					writer.write(rsExterno.getString("CondicionEcotono"));
					writer.write(rsExterno.getString("SotobosqueFuera"));
					writer.write(rsExterno.getString("PorcentajeSotobosqueFuera"));
					writer.write(rsExterno.getString("Consecutivo"));
					writer.write(rsExterno.getString("Familia"));
					writer.write(rsExterno.getString("Genero"));
					writer.write(rsExterno.getString("Especie"));
					writer.write(rsExterno.getString("Infraespecie"));
					writer.write(rsExterno.getString("EsColecta"));
					writer.write(rsExterno.getString("NombreComun"));
					writer.write(rsExterno.getString("Frecuencia025150"));
					writer.write(rsExterno.getString("Cobertura025150"));
					writer.write(rsExterno.getString("Frecuencia151275"));
					writer.write(rsExterno.getString("Cobertura151275"));
					writer.write(rsExterno.getString("Frecuencia275"));
					writer.write(rsExterno.getString("Cobertura275"));
					writer.write(rsExterno.getString("Vigor"));
					writer.write(rsExterno.getString("Danio"));
					writer.write(rsExterno.getString("PorcentajeDanio"));
					writer.write(rsExterno.getString("ClaveColecta"));
					writer.endRecord();
				}

				writer.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al exportar Sotobosque\n" + e);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

	public void setCargandoComponentesGraficos() {
		getEtiquetaExportacion().setVisible(true);
		getBarraProgreso().setIndeterminate(true);
		btnExportar.setEnabled(false);
		chktodo.setSelected(false);
		chkrepoblado.setSelected(false);
		chksotobosque.setSelected(false);
		chkarbolado.setSelected(false);
		chksitios.setSelected(false);
		chkupms.setSelected(false);
	}
}
