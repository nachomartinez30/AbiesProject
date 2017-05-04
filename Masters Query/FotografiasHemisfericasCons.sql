SELECT
--Informacion de UPM
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,
upm.FechaInicio,
upm.FechaFin,
--información de la tabla sitio-
sitio.Sitio,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.CondicionEcotono,
sitio.CondicionPresenteCampo,
--información de la tabla de fotografias hemisfericas-
CASE fotografiaHemisferica.CoberturaArborea WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END CoberturaArborea,
CASE fotografiaHemisferica.TomaFotografia WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END TomaFotografia,

fotografiaHemisferica.Hora,
fotografiaHemisferica.DeclinacionMagnetica

FROM
SITIOS_FotografiaHemisferica fotografiaHemisferica

LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=fotografiaHemisferica.SitioID
LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional

GROUP BY
fotografiaHemisferica.UPMID,
fotografiaHemisferica.SitioID,
fotografiaHemisferica.FotografiaHemisfericaID

ORDER BY
fotografiaHemisferica.UPMID