SELECT
repoblado.UPMID,
repoblado.SitioID,
repoblado.RepobladoID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
repoblado.Consecutivo,
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
repoblado.EsColecta,
repoblado.NombreComun,
repoblado.Frecuencia025150,
repoblado.Edad025150,
repoblado.Frecuencia151275,
repoblado.Edad151275,
repoblado.Frecuencia275,
repoblado.Edad275,
tipoVigorSotobosqueRepoblado.Descripcion AS Vigor,
agenteDanio.Agente AS AgenteDanio,
repoblado.PorcentajeDanio,
repoblado.ClaveColecta

FROM
TAXONOMIA_Repoblado repoblado

JOIN SITIOS_Sitio sitio ON sitio.SitioID=repoblado.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=repoblado.UPMID

LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = repoblado.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = repoblado.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = repoblado.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = repoblado.InfraespecieID

LEFT JOIN CAT_TipoVigorSotobosqueRepoblado 	tipoVigorSotobosqueRepoblado ON tipoVigorSotobosqueRepoblado.VigorID = repoblado.VigorID
LEFT JOIN CAT_AgenteDanio 	agenteDanio ON agenteDanio.AgenteDanioID = repoblado.DanioID

GROUP BY
repoblado.UPMID,
repoblado.SitioID,
repoblado.RepobladoID

ORDER BY
repoblado.UPMID