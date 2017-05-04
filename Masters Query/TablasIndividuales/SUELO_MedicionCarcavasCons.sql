SELECT
medicionCarcavas.UPMID,
medicionCarcavas.SitioID,
medicionCarcavas.MedicionCarcavasID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
medicionCarcavas.NumeroCarcavas,
medicionCarcavas.ProfundidadPromedio,
medicionCarcavas.AnchoPromedio,
medicionCarcavas.Longitud,
medicionCarcavas.Volumen


FROM
SUELO_MedicionCarcavas medicionCarcavas

JOIN SITIOS_Sitio sitio ON sitio.SitioID=medicionCarcavas.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=medicionCarcavas.UPMID

GROUP BY
medicionCarcavas.UPMID,
medicionCarcavas.SitioID,
medicionCarcavas.MedicionCarcavasID
ORDER BY
medicionCarcavas.UPMID