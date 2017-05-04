Select
--Informacion de UPM
upmSotobosque.UPMID,
upmMallaSotobosque.Estado,
upmMallaSotobosque.Municipio,
CASE upmMallaSotobosque.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upmSotobosque.FechaInicio,
upmSotobosque.FechaFin,
upmSotobosque.Altitud,
upmSotobosque.PendienteRepresentativa,
exposicionUPMSotobosque.Descripcion AS Exposicion,
fisiografiaSotobosque.TipoFisiografia AS Fisiografia,

--informacion de Sitio
sitioSotobosque.Sitio,
claveSerieVSotobosque.TipoVegetacion ,
faseSucecionalSotobosque.Clave  AS FaseSucecional,
CASE sitioSotobosque.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitioSotobosque.CondicionEcotono,
CASE sitioSotobosque.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera,
sitioSotobosque.PorcentajeSotobosqueFuera,
CASE sitioSotobosque.SotobosqueFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END SotobosqueFuera,
sitioSotobosque.PorcentajeSotobosqueFuera,

--informacion de Sotobosque
sotobosque.Consecutivo,
familiaSotobosque.Nombre AS Familia,
generoSotobosque.Nombre AS Genero,
especieSotobosque.Nombre AS Especie,
infraespecieSotobosque.Nombre AS Infraespecie,
sotobosque.EsColecta,
sotobosque.NombreComun,
sotobosque.Frecuencia025150,
sotobosque.Cobertura025150,
sotobosque.Frecuencia151275,
sotobosque.Cobertura151275,
sotobosque.Frecuencia275,
sotobosque.Cobertura275,
vigorSotobosque.Descripcion AS Vigor,
danioSotobosque.Agente AS Danio,
sotobosque.PorcentajeDanio,
sotobosque.ClaveColecta


FROM 
TAXONOMIA_Sotobosque sotobosque

--Sotobosque
LEFT JOIN SITIOS_Sitio sitioSotobosque ON sitioSotobosque.SitioID=sotobosque.SitioID
LEFT JOIN UPM_UPM upmSotobosque ON upmSotobosque.UPMID=sitioSotobosque.UPMID
LEFT JOIN UPM_MallaPuntos upmMallaSotobosque ON upmMallaSotobosque.UPMID=upmSotobosque.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieVSotobosque ON claveSerieVSotobosque.ClaveSerieVID = sitioSotobosque.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecionalSotobosque ON faseSucecionalSotobosque.FaseSucecionalID =sitioSotobosque.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPMSotobosque ON exposicionUPMSotobosque.ExposicionID =upmSotobosque.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografiaSotobosque ON fisiografiaSotobosque.FisiografiaID=upmSotobosque.FisiografiaID

LEFT JOIN CAT_FamiliaEspecie familiaSotobosque ON sotobosque.FamiliaID = familiaSotobosque.FamiliaID 
LEFT JOIN CAT_Genero generoSotobosque ON sotobosque.GeneroID = generoSotobosque.GeneroID 
LEFT JOIN CAT_Especie especieSotobosque ON sotobosque.EspecieID = especieSotobosque.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecieSotobosque ON sotobosque.InfraespecieID = infraespecieSotobosque.InfraespecieID 

LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigorSotobosque ON vigorSotobosque.VigorID=sotobosque.VigorID
LEFT JOIN CAT_AgenteDanio danioSotobosque ON danioSotobosque.AgenteDanioID=sotobosque.DanioID

GROUP BY
sotobosque.UPMID,
sotobosque.SitioID,
sotobosque.Soto

ORDER BY
sotobosque.UPMID
