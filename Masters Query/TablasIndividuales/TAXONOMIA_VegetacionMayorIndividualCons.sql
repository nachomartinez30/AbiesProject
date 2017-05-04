SELECT
vegetacionMayorIndividual.UPMID,
vegetacionMayorIndividual.SitioID,
vegetacionMayorIndividual.VegetacionMayorID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
vegetacionMayorIndividual.Consecutivo,
vegetacionMayorIndividual.NoIndividuo,
tipoFormaVidaZA.Morfotipo AS FormaVida , 
condicionVM.Descripcion AS Condicion , 
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
CASE vegetacionMayorIndividual.EsColecta WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END AS EsColecta,
vegetacionMayorIndividual.NombreComun,
tipoFormaCrecimiento.Descripcion AS FormaCrecimiento , 
densidadFollaje.Descripcion AS DensidadFollaje , 
vegetacionMayorIndividual.DiametroBase,
vegetacionMayorIndividual.AlturaTotal,
vegetacionMayorIndividual.DiametroCoberturaMayor,
vegetacionMayorIndividual.DiametroCoberturaMenor,
tipoVigorArbolado.Descripcion AS Vigor , 							
vegetacionMayorIndividual.ClaveColecta 							

FROM
TAXONOMIA_VegetacionMayorIndividual vegetacionMayorIndividual


JOIN SITIOS_Sitio sitio ON sitio.SitioID=vegetacionMayorIndividual.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=vegetacionMayorIndividual.UPMID

LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = vegetacionMayorIndividual.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = vegetacionMayorIndividual.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = vegetacionMayorIndividual.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = vegetacionMayorIndividual.InfraespecieID

LEFT JOIN CAT_TipoFormaVidaZA tipoFormaVidaZA ON tipoFormaVidaZA.FormaVidaZAID =  vegetacionMayorIndividual.FormaVidaID 
LEFT JOIN CAT_CondicionVM condicionVM ON condicionVM.CondicionVMID= vegetacionMayorIndividual.CondicionID 
LEFT JOIN CAT_TipoFormaCrecimiento tipoFormaCrecimiento ON tipoFormaCrecimiento.FormaCrecimientoID= vegetacionMayorIndividual.DensidadFollajeID
LEFT JOIN CAT_DensidadFollaje densidadFollaje ON densidadFollaje.DensidadFollajeID = vegetacionMayorIndividual.DensidadFollajeID 
LEFT JOIN CAT_TipoVigorArbolado tipoVigorArbolado ON tipoVigorArbolado.VigorID = vegetacionMayorIndividual.VigorID 

GROUP BY
vegetacionMayorIndividual.UPMID,
vegetacionMayorIndividual.SitioID,
vegetacionMayorIndividual.VegetacionMayorID
ORDER BY
vegetacionMayorIndividual.UPMID