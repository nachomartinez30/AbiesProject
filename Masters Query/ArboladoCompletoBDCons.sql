SELECT 

arbolado.UPMID,
arbolado.SitioID,
arbolado.ArboladoID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
upmMala.Estado,
upmMala.Municipio,
CASE upmMala.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upm.FechaInicio,
upm.FechaFin,
upm.Altitud,
upm.PendienteRepresentativa,
exposicionUPM.Descripcion AS Exposicion,
fisiografia.TipoFisiografia AS Fisiografia,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.CondicionEcotono,

-- _____________________________________________________________________________________________________________________________________________________________________________________________
arbolado.Consecutivo,
arbolado.NoIndividuo AS Individuo,
arbolado.NoRama AS Rama,
arbolado.Azimut,
arbolado.Distancia,
familia.Nombre AS Familia,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
arbolado.NombreComun,
CASE arbolado.EsSubmuestra WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END EsSubmuestra,
formaVida.Descripcion AS FormaVida,
formaFuste.Descripcion AS FormaFuste,
condicion.Descripcion AS Condicion,
muertoPie.Descripcion AS TipoMuertoPie,
gradoPutrefaccion.Descripcion AS GradoPutrefaccion,
tipoTocon.Descripcion AS TipoTocon,
arbolado.DiametroNormal,

arbolado.DiametroBasal,
 
arbolado.AlturaTotal,
arbolado.AnguloInclinacion,
arbolado.AlturaFusteLimpio,
arbolado.AlturaComercial,
arbolado.DiametroCopaNS,
arbolado.DiametroCopaEO,

porcentajeCopaViva.Descripcion AS ProporcionCopaViva,
exposicionLuzCopa.Descripcion AS ExposicionLuzCopa,
posicionCopa.Descripcion AS posicionCopa,
densidadCopa.Clave AS DensidadCopa,
muerteRegresiva.Clave AS MuerteRegresiva,
transparenciaFollaje.Clave AS TransparenciaFollaje,


agenteDanio1.Agente1,
agenteDanio1.Severidad1,
agenteDanio2.Agente2,
agenteDanio2.Severidad2,
vigor.Descripcion AS Vigor,
nivelVigor.Descripcion AS NivelVigor,
arbolado.ClaveColecta

FROM TAXONOMIA_Arbolado arbolado

JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID
JOIN UPM_UPM upm ON upm.UPMID=arbolado.UPMID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=arbolado.UPMID

LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional

LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID 
LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID 
LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID 
LEFT JOIN CAT_TipoFormaFuste formaFuste ON arbolado.FormaFusteID = formaFuste.FormaFusteID 
LEFT JOIN CAT_CondicionArbolado condicion ON arbolado.CondicionID = condicion.CondicionID 
LEFT JOIN CAT_CondicionMuertoPie muertoPie ON arbolado.MuertoPieID = muertoPie.MuertoPieID 
LEFT JOIN CAT_GradoPutrefaccionArbolado gradoPutrefaccion ON arbolado.GradoPutrefaccionID = gradoPutrefaccion.GradoPutrefaccionID 
LEFT JOIN CAT_TipoTocon tipoTocon ON arbolado.TipoToconID = tipoTocon.TipoToconID

LEFT JOIN CAT_PorcentajeArbolado porcentajeCopaViva ON arbolado.ProporcionCopaVivaID = porcentajeCopaViva.PorcentajeArboladoID 
LEFT JOIN CAT_ExposicionLuzCopa exposicionLuzCopa ON arbolado.ExposicionCopaID = exposicionLuzCopa.ExposicionLuzID 
LEFT JOIN CAT_PosicionCopa posicionCopa ON arbolado.PosicionCopaID = posicionCopa.PosicionCopaID 
LEFT JOIN CAT_PorcentajeArbolado densidadCopa ON arbolado.DensidadCopaID = densidadCopa.PorcentajeArboladoID 
LEFT JOIN CAT_PorcentajeArbolado muerteRegresiva ON arbolado.MuerteRegresivaID = muerteRegresiva.PorcentajeArboladoID 
LEFT JOIN CAT_PorcentajeArbolado transparenciaFollaje ON arbolado.TransparenciaFollajeID = transparenciaFollaje.PorcentajeArboladoID 

LEFT JOIN (

        SELECT --consulta los danios y severidades
        agenteDanio.ArboladoID, 
        ca.Agente AS Agente1, 
        cp.Clave AS Severidad1

        FROM ARBOLADO_DanioSeveridad agenteDanio
        LEFT JOIN CAT_AgenteDanio ca ON agenteDanio.AgenteDanioID = ca.AgenteDanioID 
        LEFT JOIN CAT_PorcentajeArbolado cp ON agenteDanio.SeveridadID = cp.PorcentajeArboladoID 

        WHERE agenteDanio.NumeroDanio = 1

        ) agenteDanio1 ON arbolado.ArboladoID = agenteDanio1.ArboladoID 
LEFT JOIN (
       
       SELECT 
       agenteDanio.ArboladoID, 
       ca.Agente AS Agente2, 
       cp.Clave AS Severidad2

       FROM ARBOLADO_DanioSeveridad agenteDanio
       LEFT JOIN CAT_AgenteDanio ca ON agenteDanio.AgenteDanioID = ca.AgenteDanioID 
       LEFT JOIN CAT_PorcentajeArbolado cp ON agenteDanio.SeveridadID = cp.PorcentajeArboladoID 
       
       WHERE agenteDanio.NumeroDanio = 2

       ) agenteDanio2 ON arbolado.ArboladoID = agenteDanio2.ArboladoID

LEFT JOIN CAT_TipoVigorArbolado vigor ON arbolado.VigorID = vigor.VigorID
LEFT JOIN CAT_NivelVigor nivelVigor ON arbolado.NivelVigorID = nivelVigor.NivelVigorID

GROUP BY
arbolado.UPMID,
arbolado.SitioID,
arbolado.ArboladoID

ORDER BY
arbolado.UPMID