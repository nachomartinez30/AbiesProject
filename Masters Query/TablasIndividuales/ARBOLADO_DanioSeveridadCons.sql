SELECT
danioSeveridad.UPMID,
danioSeveridad.SitioID,
danioSeveridad.DanioSeveridadID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
danioSeveridad.ArboladoID,
danioSeveridad.NumeroDanio,
agenteDanio.Descripcion AS AgenteDanio,
porcentajeArbolado.Descripcion AS Severidad

FROM

ARBOLADO_DanioSeveridad  danioSeveridad

JOIN SITIOS_Sitio sitio ON sitio.SitioID=danioSeveridad.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=danioSeveridad.UPMID

LEFT JOIN CAT_AgenteDanio agenteDanio ON agenteDanio.AgenteDanioID =danioSeveridad.AgenteDanioID
LEFT JOIN CAT_PorcentajeArbolado  porcentajeArbolado ON porcentajeArbolado.PorcentajeArboladoID =danioSeveridad.SeveridadID

GROUP BY
danioSeveridad.UPMID,
danioSeveridad.SitioID,
danioSeveridad.DanioSeveridadID
ORDER BY
danioSeveridad.UPMID