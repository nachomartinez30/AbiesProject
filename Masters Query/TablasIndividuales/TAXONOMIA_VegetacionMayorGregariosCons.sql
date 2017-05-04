SELECT
vegetacionMayorGregarios.UPMID,
vegetacionMayorGregarios.SitioID,
vegetacionMayorGregarios.VegetacionMayorID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
vegetacionMayorGregarios.Consecutivo,
vegetacionMayorGregarios.NoIndividuo,
tipoFormaVidaZA.Morfotipo AS FormaVida ,
condicionVM.Descripcion AS Condicion ,
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
CASE vegetacionMayorGregarios.EsColecta WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END AS EsColecta, 
vegetacionMayorGregarios.NombreComun,
tipoFormaCrecimiento.Descripcion AS FormaCrecimiento ,
densidadColonia.Descripcion AS DensidadColonia ,
vegetacionMayorGregarios.AlturaTotalMaxima,
vegetacionMayorGregarios.AlturaTotalMedia,
vegetacionMayorGregarios.AlturaTotalMinima,
vegetacionMayorGregarios.DiametroCoberturaMayor,
vegetacionMayorGregarios.DiametroCoberturaMenor,
tipoVigorArbolado.Descripcion AS Vigor ,
vegetacionMayorGregarios.ClaveColecta

FROM
TAXONOMIA_VegetacionMayorGregarios vegetacionMayorGregarios

JOIN SITIOS_Sitio sitio ON sitio.SitioID=vegetacionMayorGregarios.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=vegetacionMayorGregarios.UPMID

LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = vegetacionMayorGregarios.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = vegetacionMayorGregarios.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = vegetacionMayorGregarios.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = vegetacionMayorGregarios.InfraespecieID

LEFT JOIN CAT_TipoFormaVidaZA tipoFormaVidaZA ON tipoFormaVidaZA.FormaVidaZAID =  vegetacionMayorGregarios.FormaVidaID 
LEFT JOIN CAT_CondicionVM condicionVM ON condicionVM.CondicionVMID= vegetacionMayorGregarios.CondicionID 
LEFT JOIN CAT_TipoFormaCrecimiento tipoFormaCrecimiento ON tipoFormaCrecimiento.FormaCrecimientoID= vegetacionMayorGregarios.FormaCrecimientoID 
LEFT JOIN CAT_DensidadColonia densidadColonia ON densidadColonia.DensidadColoniaID = vegetacionMayorGregarios.DensidadColoniaID 
LEFT JOIN CAT_TipoVigorArbolado tipoVigorArbolado ON tipoVigorArbolado.VigorID = vegetacionMayorGregarios.VigorID 

GROUP BY
vegetacionMayorGregarios.UPMID,
vegetacionMayorGregarios.SitioID,
vegetacionMayorGregarios.VegetacionMayorID
ORDER BY
vegetacionMayorGregarios.UPMID