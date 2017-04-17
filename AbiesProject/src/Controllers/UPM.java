package Controllers;

import java.util.Date;

public class UPM {
	
	int UPMID,tipoUPMID,pendienteRepresentativa,fisiografiaID,exposicionID,tipoTenenciaID,gradosLatitud,minutosLatitud,gradosLongitud,minutosLongitud,errorPresicion,azimut,tipoInaccesibilidadID;
	int informacionContacto,brigadaID,brigadistaID,puestoID,empresaID,contactoID,epifitaID,claseTipoID,presenciaTroncosID,presenciaRamasID,conglomeradoID,secuenciaID,ciclo;
	int proveedorID,municipioID;

	boolean accesible,tipoContacto,tipoTelefono,tieneCorreo,tieneRadio,A,B,C,D,E,F,G,H;
	
	Date fechaInicio,fechaFin;
	
	float altitud,segundosLatitud,segundosLongitud,distancia,X,Y;
	
	String predio,paraje,datum,otroTipoInaccesibilidad,explicacionInaccesibilidad,nombre,direccion,numeroTelefono,direccionCorreo,canal,frecuencia,observaciones,latDms,longDms,estado,region,municipio;

	
	
	
	public int getUPMID() {
		return UPMID;
	}

	public void setUPMID(int uPMID) {
		UPMID = uPMID;
	}

	public int getTipoUPMID() {
		return tipoUPMID;
	}

	public void setTipoUPMID(int tipoUPMID) {
		this.tipoUPMID = tipoUPMID;
	}

	public int getPendienteRepresentativa() {
		return pendienteRepresentativa;
	}

	public void setPendienteRepresentativa(int pendienteRepresentativa) {
		this.pendienteRepresentativa = pendienteRepresentativa;
	}

	public int getFisiografiaID() {
		return fisiografiaID;
	}

	public void setFisiografiaID(int fisiografiaID) {
		this.fisiografiaID = fisiografiaID;
	}

	public int getExposicionID() {
		return exposicionID;
	}

	public void setExposicionID(int exposicionID) {
		this.exposicionID = exposicionID;
	}

	public int getTipoTenenciaID() {
		return tipoTenenciaID;
	}

	public void setTipoTenenciaID(int tipoTenenciaID) {
		this.tipoTenenciaID = tipoTenenciaID;
	}

	public int getGradosLatitud() {
		return gradosLatitud;
	}

	public void setGradosLatitud(int gradosLatitud) {
		this.gradosLatitud = gradosLatitud;
	}

	public int getMinutosLatitud() {
		return minutosLatitud;
	}

	public void setMinutosLatitud(int minutosLatitud) {
		this.minutosLatitud = minutosLatitud;
	}

	public int getGradosLongitud() {
		return gradosLongitud;
	}

	public void setGradosLongitud(int gradosLongitud) {
		this.gradosLongitud = gradosLongitud;
	}

	public int getMinutosLongitud() {
		return minutosLongitud;
	}

	public void setMinutosLongitud(int minutosLongitud) {
		this.minutosLongitud = minutosLongitud;
	}

	public int getErrorPresicion() {
		return errorPresicion;
	}

	public void setErrorPresicion(int errorPresicion) {
		this.errorPresicion = errorPresicion;
	}

	public int getAzimut() {
		return azimut;
	}

	public void setAzimut(int azimut) {
		this.azimut = azimut;
	}

	public int getTipoInaccesibilidadID() {
		return tipoInaccesibilidadID;
	}

	public void setTipoInaccesibilidadID(int tipoInaccesibilidadID) {
		this.tipoInaccesibilidadID = tipoInaccesibilidadID;
	}

	public int getInformacionContacto() {
		return informacionContacto;
	}

	public void setInformacionContacto(int informacionContacto) {
		this.informacionContacto = informacionContacto;
	}

	public int getBrigadaID() {
		return brigadaID;
	}

	public void setBrigadaID(int brigadaID) {
		this.brigadaID = brigadaID;
	}

	public int getBrigadistaID() {
		return brigadistaID;
	}

	public void setBrigadistaID(int brigadistaID) {
		this.brigadistaID = brigadistaID;
	}

	public int getPuestoID() {
		return puestoID;
	}

	public void setPuestoID(int puestoID) {
		this.puestoID = puestoID;
	}

	public int getEmpresaID() {
		return empresaID;
	}

	public void setEmpresaID(int empresaID) {
		this.empresaID = empresaID;
	}

	public int getContactoID() {
		return contactoID;
	}

	public void setContactoID(int contactoID) {
		this.contactoID = contactoID;
	}

	public int getEpifitaID() {
		return epifitaID;
	}

	public void setEpifitaID(int epifitaID) {
		this.epifitaID = epifitaID;
	}

	public int getClaseTipoID() {
		return claseTipoID;
	}

	public void setClaseTipoID(int claseTipoID) {
		this.claseTipoID = claseTipoID;
	}

	public int getPresenciaTroncosID() {
		return presenciaTroncosID;
	}

	public void setPresenciaTroncosID(int presenciaTroncosID) {
		this.presenciaTroncosID = presenciaTroncosID;
	}

	public int getPresenciaRamasID() {
		return presenciaRamasID;
	}

	public void setPresenciaRamasID(int presenciaRamasID) {
		this.presenciaRamasID = presenciaRamasID;
	}

	public int getConglomeradoID() {
		return conglomeradoID;
	}

	public void setConglomeradoID(int conglomeradoID) {
		this.conglomeradoID = conglomeradoID;
	}

	public int getSecuenciaID() {
		return secuenciaID;
	}

	public void setSecuenciaID(int secuenciaID) {
		this.secuenciaID = secuenciaID;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	public int getProveedorID() {
		return proveedorID;
	}

	public void setProveedorID(int proveedorID) {
		this.proveedorID = proveedorID;
	}

	public int getMunicipioID() {
		return municipioID;
	}

	public void setMunicipioID(int municipioID) {
		this.municipioID = municipioID;
	}

	public boolean isAccesible() {
		return accesible;
	}

	public void setAccesible(boolean accesible) {
		this.accesible = accesible;
	}

	public boolean isTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(boolean tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public boolean isTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(boolean tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public boolean isTieneCorreo() {
		return tieneCorreo;
	}

	public void setTieneCorreo(boolean tieneCorreo) {
		this.tieneCorreo = tieneCorreo;
	}

	public boolean isTieneRadio() {
		return tieneRadio;
	}

	public void setTieneRadio(boolean tieneRadio) {
		this.tieneRadio = tieneRadio;
	}

	public boolean isA() {
		return A;
	}

	public void setA(boolean a) {
		A = a;
	}

	public boolean isB() {
		return B;
	}

	public void setB(boolean b) {
		B = b;
	}

	public boolean isC() {
		return C;
	}

	public void setC(boolean c) {
		C = c;
	}

	public boolean isD() {
		return D;
	}

	public void setD(boolean d) {
		D = d;
	}

	public boolean isE() {
		return E;
	}

	public void setE(boolean e) {
		E = e;
	}

	public boolean isF() {
		return F;
	}

	public void setF(boolean f) {
		F = f;
	}

	public boolean isG() {
		return G;
	}

	public void setG(boolean g) {
		G = g;
	}

	public boolean isH() {
		return H;
	}

	public void setH(boolean h) {
		H = h;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public float getAltitud() {
		return altitud;
	}

	public void setAltitud(float altitud) {
		this.altitud = altitud;
	}

	public float getSegundosLatitud() {
		return segundosLatitud;
	}

	public void setSegundosLatitud(float segundosLatitud) {
		this.segundosLatitud = segundosLatitud;
	}

	public float getSegundosLongitud() {
		return segundosLongitud;
	}

	public void setSegundosLongitud(float segundosLongitud) {
		this.segundosLongitud = segundosLongitud;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	public String getPredio() {
		return predio;
	}

	public void setPredio(String predio) {
		this.predio = predio;
	}

	public String getParaje() {
		return paraje;
	}

	public void setParaje(String paraje) {
		this.paraje = paraje;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getOtroTipoInaccesibilidad() {
		return otroTipoInaccesibilidad;
	}

	public void setOtroTipoInaccesibilidad(String otroTipoInaccesibilidad) {
		this.otroTipoInaccesibilidad = otroTipoInaccesibilidad;
	}

	public String getExplicacionInaccesibilidad() {
		return explicacionInaccesibilidad;
	}

	public void setExplicacionInaccesibilidad(String explicacionInaccesibilidad) {
		this.explicacionInaccesibilidad = explicacionInaccesibilidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDireccionCorreo() {
		return direccionCorreo;
	}

	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getLatDms() {
		return latDms;
	}

	public void setLatDms(String latDms) {
		this.latDms = latDms;
	}

	public String getLongDms() {
		return longDms;
	}

	public void setLongDms(String longDms) {
		this.longDms = longDms;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}
