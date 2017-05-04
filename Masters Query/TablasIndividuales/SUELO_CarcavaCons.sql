SELECT
carcava.UPMID,
carcava.SitioID,
carcava.CarcavaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
carcava.Numero,
carcava.Ancho,
carcava.Profundidad

FROM
SUELO_Carcava carcava

JOIN SITIOS_Sitio sitio ON sitio.SitioID=carcava.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=carcava.UPMID

GROUP BY
carcava.UPMID,
carcava.SitioID,
carcava.CarcavaID
ORDER BY
carcava.UPMID