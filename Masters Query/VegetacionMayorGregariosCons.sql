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
--informacion de Sitio
sitio.Sitio,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.CondicionEcotono,
--informacion de vegetación mayor gregarios zonas áridas-
vegetacionMayorGregarios.Consecutivo,
vegetacionMayorGregarios.NoIndividuo,
formaVidaZonasAridas.Morfotipo AS FormaVida,
condicionVM.Descripcion AS Condicion,
familia.Nombre AS Familia,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
inf.Nombre AS Infraespecie,
vegetacionMayorGregarios.NombreComun,
formaCrecimiento.Descripcion AS FormaCrecimiento,
densidadColonia.Descripcion AS DensidadColonia,
vegetacionMayorGregarios.AlturaTotalMaxima,
vegetacionMayorGregarios.AlturaTotalMedia,
vegetacionMayorGregarios.AlturaTotalMinima,
vegetacionMayorGregarios.DiametroCoberturaMayor,
vegetacionMayorGregarios.DiametroCoberturaMenor,
agenteDanio1.Agente1,
agenteDanio1.Severidad1,
agenteDanio2.Agente2,
agenteDanio2.Severidad2,
vigor.Descripcion AS Vigor,
vegetacionMayorGregarios.ClaveColecta

FROM TAXONOMIA_VegetacionMayorGregarios vegetacionMayorGregarios

LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=vegetacionMayorGregarios.SitioID
LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID

LEFT JOIN CAT_FamiliaEspecie familia ON vegetacionMayorGregarios.FamiliaID = familia.FamiliaID 
LEFT JOIN CAT_Genero genero ON vegetacionMayorGregarios.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie ON vegetacionMayorGregarios.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie inf ON vegetacionMayorGregarios.InfraespecieID = inf.InfraespecieID 
LEFT JOIN CAT_TipoFormaVidaZA formaVidaZonasAridas ON vegetacionMayorGregarios.FormaVidaID = formaVidaZonasAridas.FormaVidaZAID 
LEFT JOIN CAT_CondicionVM condicionVM ON vegetacionMayorGregarios.CondicionID = condicionVM.CondicionVMID 
LEFT JOIN CAT_TipoFormaCrecimiento formaCrecimiento ON vegetacionMayorGregarios.FormaCrecimientoID = formaCrecimiento.FormaCrecimientoID 
LEFT JOIN CAT_DensidadColonia densidadColonia ON vegetacionMayorGregarios.DensidadColoniaID = densidadColonia.DensidadColoniaID 
LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigor ON vegetacionMayorGregarios.VigorID = vigor.VigorID 
LEFT JOIN 
(
		SELECT 
		ad.VegetacionMayorID
		, ca.Clave AS Agente1
		, cp.Descripcion AS severidad1 
		FROM VEGETACIONMAYORG_DanioSeveridad ad 
		LEFT JOIN CAT_AgenteDanio ca ON ad.AgenteDanioID = ca.AgenteDanioID 
		LEFT JOIN CAT_SeveridadZA cp ON ad.SeveridadID = cp.SeveridadID WHERE ad.NumeroDanio = 1

) agenteDanio1 ON vegetacionMayorGregarios.VegetacionMayorID = agenteDanio1.VegetacionMayorID 
LEFT JOIN 
(
		SELECT 
		ad.VegetacionMayorID
		, ca.Clave AS Agente2
		, cp.Descripcion AS severidad2 
		FROM VEGETACIONMAYORG_DanioSeveridad ad 
		LEFT JOIN CAT_AgenteDanio ca ON ad.AgenteDanioID = ca.AgenteDanioID 
		LEFT JOIN CAT_SeveridadZA cp ON ad.SeveridadID = cp.SeveridadID WHERE ad.NumeroDanio = 2
) agenteDanio2 ON vegetacionMayorGregarios.VegetacionMayorID = agenteDanio2.VegetacionMayorID

GROUP BY
vegetacionMayorGregarios.UPMID,
vegetacionMayorGregarios.SitioID,
vegetacionMayorGregarios.VegetacionMayorID

ORDER BY
vegetacionMayorGregarios.UPMID