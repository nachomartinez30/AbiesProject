SELECT
coberturaSuelo.UPMID,
coberturaSuelo.SitioID,
coberturaSuelo.CoberturaSueloID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
coberturaSuelo.Transecto,
coberturaSuelo.Pendiente

FROM
SUELO_CoberturaSuelo coberturaSuelo

JOIN SITIOS_Sitio sitio ON sitio.SitioID=coberturaSuelo.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=coberturaSuelo.UPMID

GROUP BY
coberturaSuelo.UPMID,
coberturaSuelo.SitioID,
coberturaSuelo.CoberturaSueloID
ORDER BY
coberturaSuelo.UPMID