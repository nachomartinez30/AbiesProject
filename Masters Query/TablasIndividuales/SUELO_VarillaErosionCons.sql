SELECT
varillaErosion.UPMID,
varillaErosion.SitioID,
varillaErosion.VarillaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
varillaErosion.NoVarilla,
varillaErosion.Azimut,
varillaErosion.Distancia,
varillaErosion.Profundidad



FROM
SUELO_VarillaErosion varillaErosion


JOIN SITIOS_Sitio sitio ON sitio.SitioID=varillaErosion.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=varillaErosion.UPMID

GROUP BY
varillaErosion.UPMID,
varillaErosion.SitioID,
varillaErosion.VarillaID
ORDER BY
varillaErosion.UPMID