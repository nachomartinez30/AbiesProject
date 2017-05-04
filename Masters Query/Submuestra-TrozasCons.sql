SELECT
--Informacion de UPM
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,
upmMalla.Region,
--informacion de Sitio
sitio.Sitio,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
--informacion de Arbolado
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
formaVida.Descripcion AS FormaVida,
formaFuste.Descripcion AS FormaFuste,
condicion.Descripcion AS Condicion,
arbolado.DiametroNormal,
arbolado.AlturaTotal,
--información de tabla de submuestra-
submuestra.DiametroBasal AS SubmuestraDiametroBasal,
submuestra.Edad AS SubmuestraEdad,
submuestra.NumeroAnillos25 AS SubmuestraNumeroAnillos25,
submuestra.LongitudAnillos10 AS SubmuestraLongitudAnillos10,
submuestra.GrozorCorteza AS SubmuestraGrozorCorteza,
--información de tabla de troza-   -cada registro de submuestra (tabla anterior) puede tener una o varias trozas-
troza.NoTroza,
troza.TipoTroza,

--información de tabla submuestra observaciones-  -las observaciones son a nivel sitio, es decir se hace observación por el total de registros que tienen submuestra, puede no haber-
submuestraObservaciones.Observaciones

FROM

ARBOLADO_Submuestra submuestra

LEFT JOIN TAXONOMIA_Arbolado arbolado ON arbolado.ArboladoID=submuestra.ArboladoID--*
--UPM Y SITIO
LEFT JOIN SITIOS_Sitio sitio ON sitio.SitioID=arbolado.SitioID
LEFT JOIN SUBMUESTRA_Observaciones submuestraObservaciones ON submuestraObservaciones.SitioID=sitio.SitioID--*
LEFT JOIN UPM_UPM upm ON upm.UPMID=submuestra.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN (
	
			SELECT
			tr.SubmuestraID,
			tr.NoTroza AS NoTroza,
			tipoTroza.Descripcion AS TipoTroza
			FROM
			ARBOLADO_Troza tr
			LEFT JOIN CAT_TipoTroza tipoTroza ON tipoTroza.TipoTrozaID=tr.TipoTrozaID

)troza ON troza.SubmuestraID=submuestra.SubmuestraID


--arbolado
LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_FamiliaEspecie familia ON arbolado.FamiliaID = familia.FamiliaID 
LEFT JOIN CAT_Genero genero ON arbolado.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie ON arbolado.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecie ON arbolado.InfraespecieID = infraespecie.InfraespecieID 
LEFT JOIN CAT_TipoFormaVidaArbolado formaVida ON arbolado.FormaVidaID = formaVida.FormaVidaID 
LEFT JOIN CAT_TipoFormaFuste formaFuste ON arbolado.FormaFusteID = formaFuste.FormaFusteID 
LEFT JOIN CAT_CondicionArbolado condicion ON arbolado.CondicionID = condicion.CondicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID

GROUP BY
submuestra.UPMID,
submuestra.SitioID,
submuestra.SubmuestraID

ORDER BY
submuestra.UPMID