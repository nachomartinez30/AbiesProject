SELECT
fotografiaHemisferica.UPMID,
fotografiaHemisferica.SitioID,
fotografiaHemisferica.FotografiaHemisfericaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
CASE fotografiaHemisferica.CoberturaArborea WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ExisteCoberturaArborea,
CASE fotografiaHemisferica.TomaFotografia WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ExisteFotografia,
fotografiaHemisferica.Hora,
fotografiaHemisferica.DeclinacionMagnetica


FROM
SITIOS_FotografiaHemisferica	fotografiaHemisferica

JOIN SITIOS_Sitio sitio ON sitio.SitioID=fotografiaHemisferica.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=fotografiaHemisferica.UPMID

GROUP BY
fotografiaHemisferica.UPMID,
fotografiaHemisferica.SitioID,
fotografiaHemisferica.FotografiaHemisfericaID
ORDER BY
fotografiaHemisferica.UPMID