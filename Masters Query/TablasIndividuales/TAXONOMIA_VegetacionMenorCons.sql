SELECT
vegetacionMenor.UPMID,
vegetacionMenor.SitioID,
vegetacionMenor.VegetacionMenorID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
vegetacionMenor.Consecutivo,
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
vegetacionMenor.NombreComun,
CASE vegetacionMenor.EsColecta WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END AS EsColecta,
tipoFormaVidaZA.Morfotipo AS FormaVida , 
condicionVM.Descripcion AS Condicion , 
vegetacionMenor.Numero0110,
vegetacionMenor.Numero1125,
vegetacionMenor.Numero2650,
vegetacionMenor.Numero5175,
vegetacionMenor.Numero76100,
vegetacionMenor.Numero101125,
vegetacionMenor.Numero126150,
vegetacionMenor.Numero150,
vegetacionMenor.PorcentajeCobertura,
tipoVigorSotobosqueRepoblado.Descripcion AS Vigor , 							
vegetacionMenor.ClaveColecta 							

FROM
TAXONOMIA_VegetacionMenor vegetacionMenor

JOIN SITIOS_Sitio sitio ON sitio.SitioID=vegetacionMenor.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=vegetacionMenor.UPMID

LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = vegetacionMenor.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = vegetacionMenor.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = vegetacionMenor.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = vegetacionMenor.InfraespecieID

LEFT JOIN CAT_TipoFormaVidaZA tipoFormaVidaZA ON tipoFormaVidaZA.FormaVidaZAID =  vegetacionMenor.FormaVidaID 
LEFT JOIN CAT_CondicionVM condicionVM ON condicionVM.CondicionVMID= vegetacionMenor.CondicionID 
LEFT JOIN CAT_TipoVigorSotobosqueRepoblado tipoVigorSotobosqueRepoblado ON tipoVigorSotobosqueRepoblado.VigorID = vegetacionMenor.VigorID 

GROUP BY
vegetacionMenor.UPMID,
vegetacionMenor.SitioID,
vegetacionMenor.VegetacionMenorID
ORDER BY
vegetacionMenor.UPMID