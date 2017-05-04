SELECT
puntoControl.UPMID,
puntoControl.PuntoControlID,
upmMala.Estado,
upmMala.Municipio,
puntoControl.Descripcion,
puntoControl.Paraje,
puntoControl.GradosLatitud,
puntoControl.MinutosLatitud,
puntoControl.SegundosLatitud,
puntoControl.GradosLongitud,
puntoControl.MinutosLongitud,
puntoControl.SegundosLongitud,
puntoControl.ErrorPresicion,
puntoControl.Datum,
puntoControl.Azimut,
puntoControl.Distancia

FROM
PC_PuntoControl	puntoControl 

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=puntoControl.UPMID

GROUP BY
puntoControl.UPMID,
puntoControl.PuntoControlID
ORDER BY
puntoControl.UPMID