SELECT
submuestraObservaciones.UPMID,
submuestraObservaciones.SitioID,
submuestraObservaciones.SubmuestraObservacionesID,
submuestraObservaciones.Observaciones


FROM
SUBMUESTRA_Observaciones submuestraObservaciones

JOIN SITIOS_Sitio sitio ON sitio.SitioID=submuestraObservaciones.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=submuestraObservaciones.UPMID

GROUP BY
submuestraObservaciones.UPMID,
submuestraObservaciones.SitioID,
submuestraObservaciones.SubmuestraObservacionesID
ORDER BY
submuestraObservaciones.UPMID