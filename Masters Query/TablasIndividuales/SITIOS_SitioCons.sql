SELECT
sitio.UPMID,
sitio.SitioID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
sitio.SenialGPS,
sitio.GradosLatitud,
sitio.MinutosLatitud,
sitio.SegundosLatitud,
sitio.GradosLongitud,
sitio.MinutosLongitud,
sitio.SegundosLongitud,
sitio.ErrorPresicion,
sitio.EvidenciaMuestreo,
sitio.Datum,
sitio.Azimut,
sitio.Distancia,
CASE sitio.SitioAccesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SitioAccesible,
catTipoInaccesibilidad.Tipo AS TipoInaccesibilidad,
catTipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad,
sitio.ExplicacionInaccesibilidad,
sitio.CoberturaForestal,
sitio.Condicion,
catClaveSerieV.TipoVegetacion ,
catFaseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.Ecotono,
sitio.CondicionPresenteCampo,
sitio.CondicionEcotono,
CASE sitio.RepobladoFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END RepobladoFuera,
sitio.PorcentajeRepoblado, 
CASE sitio.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera,
sitio.PorcentajeSotobosqueFuera, 
sitio.Observaciones,
CASE sitio.HipsometroBrujula WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END HipsometroBrujula ,
CASE sitio.CintaClinometroBrujula WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END CintaClinometroBrujula ,
sitio.Cuadrante1,
sitio.Cuadrante2,
sitio.Cuadrante3,
sitio.Cuadrante4,
sitio.Distancia1,
sitio.Distancia2,
sitio.Distancia3,
sitio.Distancia4

from
SITIOS_Sitio sitio

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=sitio.UPMID

LEFT JOIN CAT_ClaveSerieV catClaveSerieV ON catClaveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional catFaseSucecional on catFaseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_TipoInaccesibilidad catTipoInaccesibilidad ON catTipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad

GROUP BY
sitio.UPMID,
sitio.SitioID
ORDER BY
sitio.UPMID