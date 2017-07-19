package concentrarbdinfys;

import java.awt.BasicStroke;
import java.awt.Checkbox;
import java.io.File;

//import gob.conafor.sys.mod.CDImportacionBD;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class HiloImportacion extends SwingWorker<Integer, String> {

	private CDImportacionBD bdImportar = new CDImportacionBD();
	// private CDConentrarBD bdConcentrar =new CDConentrarBD();
	private JLabel lblEstatus;
	public JTextField txtUbicacion;
	private JProgressBar pbExportacion;
	private JButton btnEjecutar, btnBuscar;
	public JTextArea txtaMonitoreo;
	public JCheckBox chckbxContinuarSinRepetidos;

	public boolean termino;
	public File[] baseDatos;
	public int contadorBD = 0;

	public HiloImportacion(JLabel lblEstatus, JProgressBar pbExportacion, JButton btnEjecutar, File[] baseDatos,
			JButton btnBuscar, JTextField txtUbicacion, JTextArea txtaMonitoreo,JCheckBox chckbxContinuarSinRepetidos) {
		this.lblEstatus = lblEstatus;
		this.baseDatos = baseDatos;
		this.pbExportacion = pbExportacion;
		this.btnEjecutar = btnEjecutar;
		this.btnBuscar = btnBuscar;
		this.txtUbicacion = txtUbicacion;
		this.baseDatos = baseDatos;
		this.txtaMonitoreo = txtaMonitoreo;
		this.chckbxContinuarSinRepetidos=chckbxContinuarSinRepetidos;
	}

	@Override
	protected Integer doInBackground() {
		int i = 0;
		chckbxContinuarSinRepetidos.setEnabled(false);
		pbExportacion.setIndeterminate(true);
		while (i != baseDatos.length) {
			System.out.println(baseDatos[i].getPath().toString());
			txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + baseDatos[i].getPath().toString());
			txtUbicacion.setText(baseDatos[i].getPath().toString());
			migrar(baseDatos[i].getPath().toString());

			i++;
		}
		pbExportacion.setIndeterminate(false);
		chckbxContinuarSinRepetidos.setEnabled(true);
		btnBuscar.setEnabled(true);
		return 0;

	}

	public JButton getBtnEjecutar() {
		return btnEjecutar;
	}

	public void setBtnEjecutar(JButton btnEjecutar) {
		this.btnEjecutar = btnEjecutar;
	}

	public void migrar(String pathUbicacion) {
		lblEstatus.setText("Iniciando importaci�n...");
		btnEjecutar.setEnabled(false);
		btnBuscar.setEnabled(false);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblEstatus.setText("Importando UPM...");
		bdImportar.validarRepetidos(pathUbicacion);
			Object[] opciones = { "Si", "No" };
			for (int j = 0; j < bdImportar.arregloRepetidos.size(); j++) {
				if(chckbxContinuarSinRepetidos.isSelected()==true) {//continuar sin repetidos
					txtaMonitoreo.setText(txtaMonitoreo.getText()+"\nUPM REPETIDO="+bdImportar.arregloRepetidos.get(j));
				}else {
					int respuesta = JOptionPane.showOptionDialog(null,"El UPMID: " + bdImportar.arregloRepetidos.get(j)+ " ya se encuentra en la base de datos local, ¿desea reeplazarlo?","Importaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,opciones[1]);		
					if (respuesta == JOptionPane.YES_OPTION) {
						bdImportar.eliminarRepetido(bdImportar.arregloRepetidos.get(j));
					}
					if (respuesta == JOptionPane.NO_OPTION) {
						System.out.println("opcion no");
					}
				}
				
				

			}	
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarUPM_UPM(pathUbicacion); // 1
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.arregloRepetidos.clear();

		lblEstatus.setText("Importando Contacto...");
		bdImportar.importarUPMContacto(pathUbicacion); // 2
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando PC...");
		bdImportar.importarPC(pathUbicacion); // 3
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Informaci�n de accesibilidad del PC...");
		bdImportar.importarAccesibilidadPC(pathUbicacion); // 4
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Ep�fitas...");
		bdImportar.importarUPMEpifitas(pathUbicacion); // 5
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Informaci�n de sitios...");
		bdImportar.importarSitios(pathUbicacion); // 6
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Cobertura de suelo de sitio...");
		bdImportar.importarSitiosCoberturaSuelo(pathUbicacion); // 7
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		// System.err.println("Entro a Cobertura Suelo");

		lblEstatus.setText("Importando Fotograf�a hemisferica...");
		bdImportar.importarFotografiaHemisferica(pathUbicacion); // 8
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Informaci�n de transponder...");
		bdImportar.importarTransponder(pathUbicacion); // 9
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Par�metros f�sico qu�micos...");
		bdImportar.importarParametrosFisicoQuimicos(pathUbicacion); // 10
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Informaci�n de suelo...");
		bdImportar.importarSueloInformacion(pathUbicacion); // 11
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando varillas de erosi�n...");
		bdImportar.importarSueloVarillasErosion(pathUbicacion); // 12
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando cobertura del suelo...");
		bdImportar.importarSueloCobertura(pathUbicacion); // 13
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando evidencia de erosi�n del suelo...");
		bdImportar.importarSueloEvidenciaErosion(pathUbicacion); // 14
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Pedestal...");
		bdImportar.importarSueloPedestal(pathUbicacion); // 15
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Erosi�n laminar...");
		bdImportar.importarSueloErosionLaminar(pathUbicacion); // 16
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Costras...");
		bdImportar.importarSueloCostras(pathUbicacion); // 17
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Canalillo...");
		bdImportar.importarSueloCanalillo(pathUbicacion); // 18
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando C�rcava...");
		bdImportar.importarSueloCarcava(pathUbicacion); // 19
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Pavimentos...");
		bdImportar.importarSueloPavimentos(pathUbicacion); // 20
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Medici�n canalillos...");
		bdImportar.importarSueloMedicionCanalillos(pathUbicacion); // 21
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Medici�n carcavas...");
		bdImportar.importarSueloMedicionCarcavas(pathUbicacion); // 22
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Medici�n dunas...");
		bdImportar.importarSueloMedicionDunas(pathUbicacion); // 23
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Erosi�n h�drica canalillo...");
		bdImportar.importarSueloErosionHidricaCanalillo(pathUbicacion); // 24
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Longitud canalillo...");
		bdImportar.importarSueloLongitudCanalillo(pathUbicacion); // 25
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Erosi�n hidrica carcava...");
		bdImportar.importarSueloErosionHidricaCarcava(pathUbicacion); // 26
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando longitud de carcava...");
		bdImportar.importarSueloLongitudCarcava(pathUbicacion); // 27
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando deformaci�n por viento...");
		bdImportar.importarSueloDeformacionViento(pathUbicacion); // 28
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando longitud mont�culo...");
		bdImportar.importarSueloLongitudMonticulo(pathUbicacion); // 29
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando hojarasca...");
		bdImportar.importarSueloHojarasca(pathUbicacion); // 30
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando profundidad de suelo...");
		bdImportar.importarSueloProfundidad(pathUbicacion); // 31
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando perfil...");
		bdImportar.importarSueloMuestrasPerfil(pathUbicacion); // 32
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando muestras del perfil...");
		bdImportar.importarSueloMuestras(pathUbicacion); // 33
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Informaci�n de carbono e incendios...");

		lblEstatus.setText("Importando Material le�oso ca�do de 100...");
		bdImportar.importarCarbonoMaterialLenioso100(pathUbicacion); // 34
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Material le�oso ca�do de 1000...");
		bdImportar.importarCarbonoMaterialLenioso1000(pathUbicacion); // 35
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando cubierta vegetal...");
		bdImportar.importarCarbonoCubiertaVegetal(pathUbicacion); // 36
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando cobertura dosel...");
		bdImportar.importarCarbonoCoberturaDosel(pathUbicacion); // 37
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando longitud por componente...");
		bdImportar.importarCarbonoLongitudComponente(pathUbicacion); // 38
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Arbolado...");
		bdImportar.importarTaxonomiaArbolado(pathUbicacion); // 39
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarArboladoDanioSeveridad(pathUbicacion); // 40
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		lblEstatus.setText("Importando Submuestra...");
		bdImportar.importarSubmuestra(pathUbicacion); // 41
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarSubmuestraTroza(pathUbicacion); // 42
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarSubmuestraObservaciones(pathUbicacion);
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Repoblado...");
		bdImportar.importarTaxonomiaRepoblado(pathUbicacion); // 43
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Repoblado vegetaci�n menor...");
		bdImportar.importarTaxonomiaRepobladoVM(pathUbicacion); // 44
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Sotobosque...");
		bdImportar.importarTaxonomiaSotoBosque(pathUbicacion); // 45
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Vegetaci�n mayor gregarios...");
		bdImportar.importarTaxonomiaVegetacionMayorGregarios(pathUbicacion); // 46
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarVegetacionMayorGDanioSeveridad(pathUbicacion); // 47
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Vegetaci�n mayor individual...");
		bdImportar.importarTaxonomiaVegetacionMayorIndividual(pathUbicacion); // 48
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarVegetacionMayorIDanioSeveridad(pathUbicacion); // 49
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Vegetaci�n menor...");
		bdImportar.importarTaxonomiaVegetacionMenor(pathUbicacion); // 50
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		bdImportar.importarVegetacionMenorDanioSeveridad(pathUbicacion); // 51
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando Colecta bot�nica...");
		bdImportar.importarTaxonomiaColectaBotanica(pathUbicacion); // 52
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Importando datos de Brigada");
		bdImportar.importarBrigadas(pathUbicacion); // 53
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

		lblEstatus.setText("Finalizando importacion...");
		// bdImportar.importarSecuencias(pathUbicacion); //54
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());
		// bdImportar.importarUPMRevision(pathUbicacion);
		txtaMonitoreo.setText(txtaMonitoreo.getText() + "\n" + bdImportar.getState());

	}
}
