SELECT 

arbolado.UPMID,
arbolado.SitioID,
arbolado.ArboladoID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
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
muertoPie.Clave AS TipoMuertoPie,
gradoPutrefaccion.Clave AS GradoPutrefaccion,
tipoTocon.Clave AS TipoTocon,
arbolado.DiametroNormal,

arbolado.DiametroBasal,
 
arbolado.AlturaTotal,
arbolado.AnguloInclinacion,
arbolado.AlturaFusteLimpio,
arbolado.AlturaComercial,
arbolado.DiametroCopaNS,
arbolado.DiametroCopaEO,

porcentajeCopaViva.Clave AS ProporcionCopaViva,
exposicionLuzCopa.Codigo AS ExposicionLuzCopa,
posicionCopa.PosicionCopa,
densidadCopa.Clave AS DensidadCopa,
muerteRegresiva.Clave AS MuerteRegresiva,
transparenciaFollaje.Clave AS TransparenciaFollaje,



vigor.Descripcion AS Vigor,
nivelVigor.Descripcion AS NivelVigor,
arbolado.ClaveColecta 

FROM TAXONOMIA_Arbolado arbolado

JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=arbolado.UPMID

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


LEFT JOIN CAT_TipoVigorArbolado vigor ON arbolado.VigorID = vigor.VigorID
LEFT JOIN CAT_NivelVigor nivelVigor ON arbolado.NivelVigorID = nivelVigor.NivelVigorID

GROUP BY
arbolado.UPMID,
arbolado.SitioID,
arbolado.ArboladoID

ORDER BY
arbolado.UPMID