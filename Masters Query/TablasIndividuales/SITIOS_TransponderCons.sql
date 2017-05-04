SELECT
transponder.UPMID,
transponder.SitioID,
transponder.TransponderID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
tipoColocacion.Descripcion AS Colocación,
transponder.Especifique,
transponder.Observaciones

FROM
SITIOS_Transponder transponder

JOIN SITIOS_Sitio sitio ON sitio.SitioID=transponder.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=transponder.UPMID

LEFT JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID= transponder.TipoColocacionID


GROUP BY
transponder.UPMID,
transponder.SitioID,
transponder.TransponderID
ORDER BY
transponder.UPMID