SELECT
deformacionViento.UPMID,
deformacionViento.SitioID,
deformacionViento.DeformacionVientoID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
deformacionViento.Medicion,
deformacionViento.Altura,
deformacionViento.Diametro,
deformacionViento.Longitud,
deformacionViento.Distancia,
deformacionViento.Azimut


FROM
SUELO_DeformacionViento deformacionViento

JOIN SITIOS_Sitio sitio ON sitio.SitioID=deformacionViento.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=deformacionViento.UPMID

GROUP BY
deformacionViento.UPMID,
deformacionViento.SitioID,
deformacionViento.DeformacionVientoID
ORDER BY
deformacionViento.UPMID