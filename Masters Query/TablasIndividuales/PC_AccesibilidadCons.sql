SELECT
puntoControlAccesibilidad.UPMID,
puntoControlAccesibilidad.AccesibilidadID,
upmMala.Estado,
upmMala.Municipio,
medioTransporte.Medio AS MedioTransporte,
viaAccesibilidad.Via AS ViaAccesibilidad,	/**/
puntoControlAccesibilidad.Distancia,
condicionAccesibilidad.Condicion AS CondicionAccesibilidad	/**/

FROM
PC_Accesibilidad puntoControlAccesibilidad

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=puntoControlAccesibilidad.UPMID

LEFT JOIN CAT_MedioTransporte 	medioTransporte ON medioTransporte.MedioTransporteID = puntoControlAccesibilidad.MedioTransporteID
LEFT JOIN CAT_ViaAccesibilidad 	viaAccesibilidad ON viaAccesibilidad.ViaAccesibilidadID = puntoControlAccesibilidad.ViaAccesibilidadID
LEFT JOIN CAT_CondicionAccesibilidad 	condicionAccesibilidad ON condicionAccesibilidad.CondicionAccesibilidadID = puntoControlAccesibilidad.CondicionAccesibilidadID

GROUP BY
puntoControlAccesibilidad.UPMID,
puntoControlAccesibilidad.AccesibilidadID
ORDER BY
puntoControlAccesibilidad.UPMID