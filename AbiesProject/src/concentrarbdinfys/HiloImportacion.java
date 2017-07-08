package concentrarbdinfys;

import java.awt.BasicStroke;
import java.io.File;

//import gob.conafor.sys.mod.CDImportacionBD;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class HiloImportacion extends SwingWorker<Integer, String> {

	private CDImportacionBD bdImportar = new CDImportacionBD();
	// private CDConentrarBD bdConcentrar =new CDConentrarBD();
	private JLabel lblEstatus;
	public JTextField txtUbicacion;
	private JProgressBar pbExportacion;
	private JButton btnEjecutar,btnBuscar;
	
	public boolean termino;
	public File[] baseDatos;
	public int contadorBD = 0;

	public HiloImportacion(JLabel lblEstatus, JProgressBar pbExportacion, JButton btnEjecutar,File[] baseDatos, JButton btnBuscar, JTextField txtUbicacion) {
		this.lblEstatus = lblEstatus;
		this.baseDatos = baseDatos;
		this.pbExportacion = pbExportacion;
		this.btnEjecutar = btnEjecutar;
		this.btnBuscar = btnBuscar;
		this.txtUbicacion = txtUbicacion;
		this.baseDatos=baseDatos;
		
	}

	
	
	@Override
	protected Integer doInBackground() {
		int i = 0;
		pbExportacion.setIndeterminate(true);
		while (i != baseDatos.length) {
			System.out.println(baseDatos[i].getPath().toString());
			txtUbicacion.setText(baseDatos[i].getPath().toString());
			migrar(baseDatos[i].getPath().toString());
			
			i++;
		}
		pbExportacion.setIndeterminate(false);
		return 0;
		
	}

	
	

	public JButton getBtnEjecutar() {
		return btnEjecutar;
	}

	public void setBtnEjecutar(JButton btnEjecutar) {
		this.btnEjecutar = btnEjecutar;
	}

	public void migrar(String pathUbicacion){
		lblEstatus.setText("Iniciando importación...");
		btnEjecutar.setEnabled(false);
		btnBuscar.setEnabled(false);
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblEstatus.setText("Importando UPM...");
		bdImportar.validarRepetidos(pathUbicacion);
		bdImportar.importarUPM_UPM(pathUbicacion); // 1

		lblEstatus.setText("Importando Contacto...");
		bdImportar.importarUPMContacto(pathUbicacion); // 2

		lblEstatus.setText("Importando PC...");
		bdImportar.importarPC(pathUbicacion); // 3

		lblEstatus.setText("Importando Información de accesibilidad del PC...");
		bdImportar.importarAccesibilidadPC(pathUbicacion); // 4

		lblEstatus.setText("Importando Epífitas...");
		bdImportar.importarUPMEpifitas(pathUbicacion); // 5

		lblEstatus.setText("Importando Información de sitios...");
		bdImportar.importarSitios(pathUbicacion); // 6

		lblEstatus.setText("Importando Cobertura de suelo de sitio...");
		bdImportar.importarSitiosCoberturaSuelo(pathUbicacion); // 7
		// System.err.println("Entro a Cobertura Suelo");

		lblEstatus.setText("Importando Fotografía hemisferica...");
		bdImportar.importarFotografiaHemisferica(pathUbicacion); // 8

		lblEstatus.setText("Importando Información de transponder...");
		bdImportar.importarTransponder(pathUbicacion); // 9

		lblEstatus.setText("Importando Parámetros físico químicos...");
		bdImportar.importarParametrosFisicoQuimicos(pathUbicacion); // 10

		lblEstatus.setText("Importando Información de suelo...");
		bdImportar.importarSueloInformacion(pathUbicacion); // 11
		lblEstatus.setText("Importando varillas de erosión...");
		bdImportar.importarSueloVarillasErosion(pathUbicacion); // 12
		lblEstatus.setText("Importando cobertura del suelo...");
		bdImportar.importarSueloCobertura(pathUbicacion); // 13


		lblEstatus.setText("Importando evidencia de erosión del suelo...");
		bdImportar.importarSueloEvidenciaErosion(pathUbicacion); // 14
		lblEstatus.setText("Importando Pedestal...");
		bdImportar.importarSueloPedestal(pathUbicacion); // 15
		lblEstatus.setText("Importando Erosión laminar...");
		bdImportar.importarSueloErosionLaminar(pathUbicacion); // 16
		lblEstatus.setText("Importando Costras...");
		bdImportar.importarSueloCostras(pathUbicacion); // 17
		lblEstatus.setText("Importando Canalillo...");
		bdImportar.importarSueloCanalillo(pathUbicacion); // 18
		lblEstatus.setText("Importando Cárcava...");
		bdImportar.importarSueloCarcava(pathUbicacion); // 19
		lblEstatus.setText("Importando Pavimentos...");
		bdImportar.importarSueloPavimentos(pathUbicacion); // 20
		lblEstatus.setText("Importando Medición canalillos...");
		bdImportar.importarSueloMedicionCanalillos(pathUbicacion); // 21
		lblEstatus.setText("Importando Medición carcavas...");
		bdImportar.importarSueloMedicionCarcavas(pathUbicacion); // 22
		lblEstatus.setText("Importando Medición dunas...");
		bdImportar.importarSueloMedicionDunas(pathUbicacion); // 23
		lblEstatus.setText("Importando Erosión hídrica canalillo...");
		bdImportar.importarSueloErosionHidricaCanalillo(pathUbicacion); // 24
		lblEstatus.setText("Importando Longitud canalillo...");
		bdImportar.importarSueloLongitudCanalillo(pathUbicacion); // 25
		lblEstatus.setText("Importando Erosión hidrica carcava...");
		bdImportar.importarSueloErosionHidricaCarcava(pathUbicacion); // 26
		lblEstatus.setText("Importando longitud de carcava...");
		bdImportar.importarSueloLongitudCarcava(pathUbicacion); // 27
		lblEstatus.setText("Importando deformación por viento...");
		bdImportar.importarSueloDeformacionViento(pathUbicacion); // 28
		lblEstatus.setText("Importando longitud montículo...");
		bdImportar.importarSueloLongitudMonticulo(pathUbicacion); // 29
		lblEstatus.setText("Importando hojarasca...");
		bdImportar.importarSueloHojarasca(pathUbicacion); // 30
		lblEstatus.setText("Importando profundidad de suelo...");
		bdImportar.importarSueloProfundidad(pathUbicacion); // 31
		lblEstatus.setText("Importando perfil...");
		bdImportar.importarSueloMuestrasPerfil(pathUbicacion); // 32
		lblEstatus.setText("Importando muestras del perfil...");
		bdImportar.importarSueloMuestras(pathUbicacion); // 33

		lblEstatus.setText("Importando Información de carbono e incendios...");

		lblEstatus.setText("Importando Material leñoso caído de 100...");
		bdImportar.importarCarbonoMaterialLenioso100(pathUbicacion); // 34
		lblEstatus.setText("Importando Material leñoso caído de 1000...");
		bdImportar.importarCarbonoMaterialLenioso1000(pathUbicacion); // 35
		lblEstatus.setText("Importando cubierta vegetal...");
		bdImportar.importarCarbonoCubiertaVegetal(pathUbicacion); // 36
		lblEstatus.setText("Importando cobertura dosel...");
		bdImportar.importarCarbonoCoberturaDosel(pathUbicacion); // 37
		lblEstatus.setText("Importando longitud por componente...");
		bdImportar.importarCarbonoLongitudComponente(pathUbicacion); // 38


		lblEstatus.setText("Importando Arbolado...");
		bdImportar.importarTaxonomiaArbolado(pathUbicacion); // 39
		bdImportar.importarArboladoDanioSeveridad(pathUbicacion); // 40
		lblEstatus.setText("Importando Submuestra...");
		bdImportar.importarSubmuestra(pathUbicacion); // 41
		bdImportar.importarSubmuestraTroza(pathUbicacion); // 42
		bdImportar.importarSubmuestraObservaciones(pathUbicacion);

		lblEstatus.setText("Importando Repoblado...");
		bdImportar.importarTaxonomiaRepoblado(pathUbicacion); // 43

		lblEstatus.setText("Importando Repoblado vegetación menor...");
		bdImportar.importarTaxonomiaRepobladoVM(pathUbicacion); // 44

		lblEstatus.setText("Importando Sotobosque...");
		bdImportar.importarTaxonomiaSotoBosque(pathUbicacion); // 45

		lblEstatus.setText("Importando Vegetación mayor gregarios...");
		bdImportar.importarTaxonomiaVegetacionMayorGregarios(pathUbicacion); // 46
		bdImportar.importarVegetacionMayorGDanioSeveridad(pathUbicacion); // 47

		lblEstatus.setText("Importando Vegetación mayor individual...");
		bdImportar.importarTaxonomiaVegetacionMayorIndividual(pathUbicacion); // 48
		bdImportar.importarVegetacionMayorIDanioSeveridad(pathUbicacion); // 49

		lblEstatus.setText("Importando Vegetación menor...");
		bdImportar.importarTaxonomiaVegetacionMenor(pathUbicacion); // 50
		bdImportar.importarVegetacionMenorDanioSeveridad(pathUbicacion); // 51

		lblEstatus.setText("Importando Colecta botánica...");
		bdImportar.importarTaxonomiaColectaBotanica(pathUbicacion); // 52

		lblEstatus.setText("Importando datos de Brigada");
		bdImportar.importarBrigadas(pathUbicacion); // 53

		lblEstatus.setText("Finalizando importacion...");
		// bdImportar.importarSecuencias(pathUbicacion); //54
		// bdImportar.importarUPMRevision(pathUbicacion);
	
	}
	


}
