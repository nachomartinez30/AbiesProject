SELECT
--Informacion de UPM
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,
CASE upmMalla.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upm.FechaInicio,
upm.FechaFin,
upm.Altitud,
upm.PendienteRepresentativa,
exposicionUPM.Descripcion AS Exposicion,
fisiografia.TipoFisiografia AS Fisiografia,
upmMalla.Region,
--información de la tabla sitio-
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
tipoInaccesibilidad.Tipo AS TipoInaccesibilidad,
tipoInaccesibilidad.Descripcion AS DescripcionInaccesibilidad,
sitio.ExplicacionInaccesibilidad,
CASE claveSerieV.EsForestal WHEN 1 THEN 'FORESTAL' WHEN 0 THEN 'NO FORESTAL' END CoberturaForestal,
CASE sitio.Condicion WHEN 1 THEN 'Primario' WHEN 0 THEN 'Secundario' END Condicion,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.CondicionEcotono,
sitio.Ecotono,
sitio.CondicionPresenteCampo,
sitio.Observaciones,
sitio.HipsometroBrujula,
sitio.CintaClinometroBrujula,
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

LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID
LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad

GROUP BY
sitio.UPMID,
sitio.SitioID


ORDER BY
sitio.UPMID