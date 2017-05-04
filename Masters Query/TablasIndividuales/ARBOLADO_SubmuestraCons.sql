SELECT
submuestra.UPMID,
submuestra.SitioID,
submuestra.SubmuestraID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
submuestra.ArboladoID,
submuestra.DiametroBasal,
submuestra.Edad,
submuestra.NumeroAnillos25,
submuestra.LongitudAnillos10,
submuestra.GrozorCorteza

FROM
ARBOLADO_Submuestra	submuestra

JOIN SITIOS_Sitio sitio ON sitio.SitioID=submuestra.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=submuestra.UPMID

GROUP BY
submuestra.UPMID,
submuestra.SitioID,
submuestra.SubmuestraID
ORDER BY
submuestra.UPMID