package Views;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import com.csvreader.CsvWriter;

import Database.ExternalConnection;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.io.CSV;


public class FrmExportar extends JFrame{
	private JTable tblArbolado;
	public String ruta;
	private Connection baseDatosExterna;
	private java.sql.Statement sqlExterno;

	public FrmExportar() {
		
	}
	
	public void exportarArbolado(String ruta) {

		JFileChooser fcBaseDatos = new JFileChooser();
		fcBaseDatos.setDialogTitle("Exportar arbolado");
		fcBaseDatos.setApproveButtonText("Exportar");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");
		
		fcBaseDatos.setAcceptAllFileFilterUsed(false);
		fcBaseDatos.setFileFilter(filtro);
		fcBaseDatos.showOpenDialog(this);
		// fcBaseDatos.showOpenDialog(Main.main);
		try {
			
			File f = fcBaseDatos.getSelectedFile();
			String exportPath = f.getAbsolutePath();
			exportPath = exportPath + ".csv";
			CsvWriter writer = new CsvWriter(new FileWriter(exportPath), ',');
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
				JOptionPane.showMessageDialog(null, "Se exportó correctamente");
				writer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "El archivo que intenta
			// importar no es una base de datos balida" + e);
		}
	}

}
