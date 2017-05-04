SELECT
repobladoVM.UPMID,	
repobladoVM.SitioID,	
repobladoVM.RepobladoVMID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
repobladoVM.Consecutivo,	
tipoFormaVidaRepobladoVM.Descripcion AS TipoFormaVidaRepobladoVM, 	
familiaEspecie.Nombre AS FamiliaEspecie, 	
genero.Nombre AS Genero, 	
especie.Nombre AS Especie, 	
infraespecie.Nombre AS Infraespecie, 	
repobladoVM.NombreComun,	
repobladoVM.EsColecta,	
repobladoVM.Frecuencia50,	
repobladoVM.PorcentajeCobertura50,	
repobladoVM.Frecuencia51200,	
repobladoVM.PorcentajeCobertura51200,	
repobladoVM.Frecuencia200,	
repobladoVM.PorcentajeCobertura200,	
tipoVigorSotobosqueRepoblado.Descripcion AS Vigor, 	
repobladoVM.ClaveColecta	


FROM
TAXONOMIA_RepobladoVM	repobladoVM

JOIN SITIOS_Sitio sitio ON sitio.SitioID=repobladoVM.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=repobladoVM.UPMID

LEFT JOIN CAT_TipoFormaVidaRepobladoVM	tipoFormaVidaRepobladoVM ON	tipoFormaVidaRepobladoVM.FormaVidaRepobladoVMID = repobladoVM.FormaVidaID
LEFT JOIN CAT_FamiliaEspecie	familiaEspecie ON	familiaEspecie. FamiliaID= repobladoVM.FamiliaID
LEFT JOIN CAT_Genero	genero ON	genero. GeneroID= repobladoVM.GeneroID
LEFT JOIN CAT_Especie	especie ON	especie. EspecieID= repobladoVM.EspecieID
LEFT JOIN CAT_Infraespecie	infraespecie ON	infraespecie.InfraespecieID = repobladoVM.InfraespecieID
LEFT JOIN CAT_TipoVigorSotobosqueRepoblado	tipoVigorSotobosqueRepoblado ON	tipoVigorSotobosqueRepoblado.VigorID = repobladoVM.VigorID


 GROUP BY
repobladoVM.UPMID,
repobladoVM.SitioID,
repobladoVM.RepobladoVMID

ORDER BY
repobladoVM.UPMID