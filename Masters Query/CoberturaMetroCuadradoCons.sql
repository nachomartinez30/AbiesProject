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
--información de la tabla cobertura del suelo del metro cuadrado-
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
SITIOS_CoberturaSuelo coberturaSuelo

LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=coberturaSuelo.SitioID
LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional

GROUP BY
coberturaSuelo.UPMID,
coberturaSuelo.SitioID,
coberturaSuelo.CoberturaID

ORDER BY
coberturaSuelo.UPMID