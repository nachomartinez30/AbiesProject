SELECT
coberturaSuelo.UPMID,
coberturaSuelo.SitioID,
coberturaSuelo.CoberturaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
coberturaSuelo.Gramineas,
coberturaSuelo.Helechos,
coberturaSuelo.Musgos,
coberturaSuelo.Liquenes,
coberturaSuelo.Hierbas,
coberturaSuelo.Roca,
coberturaSuelo.SueloDesnudo,
coberturaSuelo.Hojarasca,
coberturaSuelo.Grava,
coberturaSuelo.Otros

FROM
SITIOS_CoberturaSuelo	 coberturaSuelo

JOIN SITIOS_Sitio sitio ON sitio.SitioID=coberturaSuelo.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=coberturaSuelo.UPMID

GROUP BY
coberturaSuelo.UPMID,
coberturaSuelo.SitioID,
coberturaSuelo.CoberturaID
ORDER BY
coberturaSuelo.UPMID