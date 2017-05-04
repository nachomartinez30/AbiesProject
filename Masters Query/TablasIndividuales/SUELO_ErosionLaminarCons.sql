SELECT
erosionLaminar.UPMID,
erosionLaminar.SitioID,
erosionLaminar.ErosionLaminarID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
erosionLaminar.Numero,
erosionLaminar.Ancho,
erosionLaminar.Largo

FROM
SUELO_ErosionLaminar erosionLaminar

JOIN SITIOS_Sitio sitio ON sitio.SitioID=erosionLaminar.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=erosionLaminar.UPMID

GROUP BY
erosionLaminar.UPMID,
erosionLaminar.SitioID,
erosionLaminar.ErosionLaminarID
ORDER BY
erosionLaminar.UPMID