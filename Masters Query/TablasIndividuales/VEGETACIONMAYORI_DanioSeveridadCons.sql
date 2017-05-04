SELECT
danioSeveridad.SitioID,
danioSeveridad.UPMID,
danioSeveridad.DanioSeveridadID,
danioSeveridad.VegetacionMayorID,
danioSeveridad.NumeroDanio,
agenteDanio.Agente,
severidadZA.Descripcion AS Severidad


FROM
VEGETACIONMAYORI_DanioSeveridad danioSeveridad

LEFT JOIN CAT_AgenteDanio agenteDanio ON agenteDanio.AgenteDanioID = danioSeveridad.AgenteDanioID
LEFT JOIN CAT_SeveridadZA severidadZA ON severidadZA.SeveridadID = danioSeveridad.SeveridadID
