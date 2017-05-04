SELECT
Observaciones.UPMID,
Observaciones.SitioID,
Observaciones.ObservacionesID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
formatos.Formato AS Formato,
Observaciones.Observaciones

FROM
SITIOS_Observaciones observaciones

JOIN SITIOS_Sitio sitio ON sitio.SitioID=observaciones.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=observaciones.UPMID


LEFT JOIN SYS_Formatos formatos ON formatos.FormatoID = observaciones.FormatoID 

GROUP BY
Observaciones.UPMID,
Observaciones.SitioID,
Observaciones.ObservacionesID
ORDER BY
Observaciones.UPMID