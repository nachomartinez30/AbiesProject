SELECT
pavimentoErosion.UPMID,
pavimentoErosion.SitioID,
pavimentoErosion.PavimentoErosionID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
pavimentoErosion.Numero,
pavimentoErosion.Diametro


FROM
SUELO_PavimentoErosion  pavimentoErosion

JOIN SITIOS_Sitio sitio ON sitio.SitioID=pavimentoErosion.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=pavimentoErosion.UPMID

GROUP BY
pavimentoErosion.UPMID,
pavimentoErosion.SitioID,
pavimentoErosion.PavimentoErosionID
ORDER BY
pavimentoErosion.UPMID