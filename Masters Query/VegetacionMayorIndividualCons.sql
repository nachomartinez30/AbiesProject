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
--infromacion vegetacion Mayor Individual
vegetacionMayorIndividual.Consecutivo,
vegetacionMayorIndividual.NoIndividuo,
formaVidaZonasAridas.Morfotipo AS FormaVida,
condicionVM.Descripcion AS Condicion,
familiia.Nombre AS Familia,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
vegetacionMayorIndividual.NombreComun,
formaGemoetrica.Descripcion AS FormaGeometrica,
densidadFollaje.Descripcion AS DensidadFollaje,
vegetacionMayorIndividual.DiametroBase,
vegetacionMayorIndividual.AlturaTotal,
vegetacionMayorIndividual.DiametroCoberturaMayor,
vegetacionMayorIndividual.DiametroCoberturaMenor,
agenteDanio1.Agente1,
agenteDanio1.Severidad1,
agenteDanio2.Agente2,
agenteDanio2.Severidad2,
vigor.Descripcion AS Vigor,
vegetacionMayorIndividual.ClaveColecta

FROM 
TAXONOMIA_VegetacionMayorIndividual vegetacionMayorIndividual 

LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=vegetacionMayorIndividual.SitioID
LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID

LEFT JOIN CAT_TipoFormaVidaZA formaVidaZonasAridas ON vegetacionMayorIndividual.FormaVidaID = formaVidaZonasAridas.FormaVidaZAID 
LEFT JOIN CAT_CondicionVM condicionVM ON vegetacionMayorIndividual.CondicionID = condicionVM.CondicionVMID 
LEFT JOIN CAT_FamiliaEspecie familiia ON vegetacionMayorIndividual.FamiliaID = familiia.FamiliaID 
LEFT JOIN CAT_Genero genero ON vegetacionMayorIndividual.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie ON vegetacionMayorIndividual.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecie ON vegetacionMayorIndividual.InfraespecieID = infraespecie.InfraespecieID 
LEFT JOIN CAT_TipoFormaGeometrica formaGemoetrica ON vegetacionMayorIndividual.FormaGeometricaID = formaGemoetrica.FormaGeometricaID 
LEFT JOIN CAT_DensidadFollaje densidadFollaje ON vegetacionMayorIndividual.DensidadFollajeID = densidadFollaje.DensidadFollajeID 
LEFT JOIN CAT_TipoVigorSotobosqueRepoblado vigor ON vegetacionMayorIndividual.VigorID = vigor.VigorID 
LEFT JOIN 
(
			SELECT
			 ad.VegetacionMayorID
			, ca.Clave AS Agente1
			, cp.Descripcion AS severidad1 
			FROM VEGETACIONMAYORI_DanioSeveridad ad 
			LEFT JOIN CAT_AgenteDanio ca ON ad.AgenteDanioID = ca.AgenteDanioID 
			LEFT JOIN CAT_SeveridadZA cp ON ad.SeveridadID = cp.SeveridadID WHERE ad.NumeroDanio = 1

	) agenteDanio1 ON vegetacionMayorIndividual.VegetacionMayorID = agenteDanio1.VegetacionMayorID 
LEFT JOIN 
(
	SELECT
	ad.VegetacionMayorID
	, ca.Clave AS Agente2
	, cp.Descripcion AS severidad2
	FROM VEGETACIONMAYORI_DanioSeveridad ad 
	LEFT JOIN CAT_AgenteDanio ca ON ad.AgenteDanioID = ca.AgenteDanioID 
	LEFT JOIN CAT_SeveridadZA cp ON ad.SeveridadID = cp.SeveridadID WHERE ad.NumeroDanio = 2

) agenteDanio2 ON vegetacionMayorIndividual.VegetacionMayorID = agenteDanio2.VegetacionMayorID

GROUP BY
vegetacionMayorIndividual.UPMID,
vegetacionMayorIndividual.SitioID,
vegetacionMayorIndividual.VegetacionMayorID

ORDER BY
vegetacionMayorIndividual.UPMID