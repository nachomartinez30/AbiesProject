SELECT
medicionDunas.UPMID,
medicionDunas.SitioID,
medicionDunas.MedicionDunas,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
medicionDunas.NumeroDunas,
medicionDunas.AlturaPromedio,
medicionDunas.AnchoPromedio,
medicionDunas.Longitud,
medicionDunas.Volumen

FROM
SUELO_MedicionDunas medicionDunas

JOIN SITIOS_Sitio sitio ON sitio.SitioID=medicionDunas.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=medicionDunas.UPMID

GROUP BY
medicionDunas.UPMID,
medicionDunas.SitioID,
medicionDunas.MedicionDunas
ORDER BY
medicionDunas.UPMID