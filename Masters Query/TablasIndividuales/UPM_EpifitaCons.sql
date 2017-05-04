SELECT
epifita.UPMID,
epifita.EpifitaID,
upmMala.Estado,
upmMala.Municipio,
tipoEpifita.Nombre AS TipoEpifita,
presenciaTroncos.Descripcion AS PresenciaTroncos,
presenciaRamas.Descripcion AS PresenciaRamas


FROM
UPM_Epifita epifita

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=epifita.UPMID


LEFT JOIN CAT_TipoEpifita tipoEpifita ON tipoEpifita.TipoEpifitaID = epifita.ClaseTipoID
LEFT JOIN CAT_PresenciaEpifita presenciaTroncos ON presenciaTroncos.PresenciaEpifitaID = epifita.PresenciaTroncosID
LEFT JOIN CAT_PresenciaEpifita presenciaRamas   ON presenciaRamas.PresenciaEpifitaID = epifita.PresenciaRamasID

GROUP BY
epifita.UPMID,
epifita.EpifitaID
ORDER BY
epifita.UPMID