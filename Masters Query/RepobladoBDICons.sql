Select
--Informacion de UPM Repoblado
upmRepoblado.UPMID,
upmMallaRepoblado.Estado,
upmMallaRepoblado.Municipio,
CASE upmMallaRepoblado.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upmRepoblado.FechaInicio,
upmRepoblado.FechaFin,
upmRepoblado.Altitud,
upmRepoblado.PendienteRepresentativa,
exposicionUPM.Descripcion AS Exposicion,
fisiografia.TipoFisiografia AS Fisiografia,

--informacion de Sitio Repoblado
sitioRepoblado.Sitio,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitioRepoblado.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitioRepoblado.CondicionEcotono,
CASE sitioRepoblado.RepobladoFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END RepobladoFuera,
sitioRepoblado.PorcentajeRepoblado,
CASE sitioRepoblado.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera,
sitioRepoblado.PorcentajeSotobosqueFuera,

--informacion de Repoblado Repoblado
repoblado.Consecutivo,
familiaRepoblado.Nombre AS Familia,
generoRepoblado.Nombre AS Genero,
especieRepoblado.Nombre AS Especie,
infraespecieRepoblado.Nombre AS Infraespecie,
repoblado.EsColecta,
repoblado.NombreComun,
repoblado.Frecuencia025150,
repoblado.Edad025150,
repoblado.Frecuencia151275,
repoblado.Edad151275,
repoblado.Frecuencia275,
repoblado.Edad275,
vigorRepoblado.Descripcion AS Vigor,
danioRepoblado.Agente AS Danio,
repoblado.PorcentajeDanio,
repoblado.ClaveColecta

FROM 
TAXONOMIA_Repoblado repoblado

LEFT JOIN SITIOS_Sitio sitioRepoblado ON sitioRepoblado.SitioID=repoblado.SitioID
LEFT JOIN UPM_UPM upmRepoblado ON upmRepoblado.UPMID=sitioRepoblado.UPMID
LEFT JOIN UPM_MallaPuntos upmMallaRepoblado ON upmMallaRepoblado.UPMID=upmRepoblado.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitioRepoblado.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional ON faseSucecional.FaseSucecionalID =sitioRepoblado.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upmRepoblado.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upmRepoblado.FisiografiaID

LEFT JOIN CAT_FamiliaEspecie familiaRepoblado ON repoblado.FamiliaID = familiaRepoblado.FamiliaID 
LEFT JOIN CAT_Genero generoRepoblado ON repoblado.GeneroID = generoRepoblado.GeneroID 
LEFT JOIN CAT_Especie especieRepoblado ON repoblado.EspecieID = especieRepoblado.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecieRepoblado ON repoblado.InfraespecieID = infraespecieRepoblado.InfraespecieID 

LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorRepoblado ON vigorRepoblado.VigorID=repoblado.VigorID
LEFT JOIN CAT_AgenteDanio danioRepoblado ON danioRepoblado.AgenteDanioID=repoblado.DanioID

GROUP BY
repoblado.UPMID,
repoblado.SitioID,
repoblado.RepobladoID

ORDER BY
repoblado.UPMID