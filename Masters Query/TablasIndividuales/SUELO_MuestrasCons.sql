SELECT
muestras.UPMID,
muestras.SitioID,
muestras.MuestrasID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
profundidadMuestras.Descripcion AS Profundidad,
muestras.PesoMuestra,
muestras.Lectura1,
muestras.Lectura2,
muestras.Lectura3,
muestras.Lectura4,
muestras.Promedio,
muestras.ClaveColecta


FROM
SUELO_Muestras muestras

JOIN SITIOS_Sitio sitio ON sitio.SitioID=muestras.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=muestras.UPMID

LEFT JOIN CAT_ProfundidadMuestras profundidadMuestras ON profundidadMuestras.ProfundidadMuestraID= muestras.ProfundidadID

GROUP BY
muestras.UPMID,
muestras.SitioID,
muestras.MuestrasID
ORDER BY
muestras.UPMID