SELECT
longitudCarcava.UPMID,
longitudCarcava.SitioID,
longitudCarcava.LongitudCarcavaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
longitudCarcava.CampoLongitud,
longitudCarcava.Longitud

FROM
SUELO_LongitudCarcava longitudCarcava

JOIN SITIOS_Sitio sitio ON sitio.SitioID=longitudCarcava.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=longitudCarcava.UPMID

GROUP BY
longitudCarcava.UPMID,
longitudCarcava.SitioID,
longitudCarcava.LongitudCarcavaID
ORDER BY
longitudCarcava.UPMID
