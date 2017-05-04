SELECT
profundidad.UPMID,
profundidad.SitioID,
profundidad.ProfundidadSueloID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
profundidad.Punto,
profundidad.Profundidad030,
profundidad.Profundidad3060,
profundidad.PesoTotal030,
profundidad.PesoTotal3060,
profundidad.Equipo030,
profundidad.Equipo3060,
profundidad.Observaciones,
profundidad.Clave030,
profundidad.Clave3060


FROM
SUELO_Profundidad profundidad

JOIN SITIOS_Sitio sitio ON sitio.SitioID=profundidad.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=profundidad.UPMID

GROUP BY
profundidad.UPMID,
profundidad.SitioID,
profundidad.ProfundidadSueloID

ORDER BY
profundidad.UPMID