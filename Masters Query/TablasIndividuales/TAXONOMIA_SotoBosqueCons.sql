SELECT 
sotoBosque.UPMID,
sotoBosque.SitioID,
sotoBosque.SotoBosqueID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sotoBosque.Consecutivo,
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
sotoBosque.NombreComun,
sotoBosque.EsColecta,
sotoBosque.Frecuencia025150,
sotoBosque.Cobertura025150,
sotoBosque.Frecuencia151275,
sotoBosque.Cobertura151275,
sotoBosque.Frecuencia275,
sotoBosque.Cobertura275,
tipoVigorSotobosqueRepoblado.Descripcion AS Vigor,
agenteDanio.Agente AS AgenteDanio ,
sotoBosque.PorcentajeDanio,
sotoBosque.ClaveColecta


FROM
TAXONOMIA_SotoBosque sotoBosque

JOIN SITIOS_Sitio sitio ON sitio.SitioID=sotoBosque.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=sotoBosque.UPMID
 
LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = sotoBosque.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = sotoBosque.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = sotoBosque.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = sotoBosque.InfraespecieID

LEFT JOIN CAT_TipoVigorSotobosqueRepoblado tipoVigorSotobosqueRepoblado ON tipoVigorSotobosqueRepoblado.VigorID=sotoBosque.VigorID
LEFT JOIN CAT_AgenteDanio agenteDanio ON agenteDanio.AgenteDanioID=sotoBosque.DanioID

GROUP BY
sotoBosque.UPMID,
sotoBosque.SitioID,
sotoBosque.SotoBosqueID
ORDER BY
sotoBosque.UPMID