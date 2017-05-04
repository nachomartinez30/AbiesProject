SELECT

materialLenioso100.UPMID,
materialLenioso100.SitioID,
materialLenioso100.MaterialLenioso100ID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
materialLenioso100.Transecto,
materialLenioso100.Pendiente,
materialLenioso100.UnaHora,
materialLenioso100.DiezHoras,
materialLenioso100.CienHoras


FROM
CARBONO_MaterialLenioso100 materialLenioso100

JOIN SITIOS_Sitio sitio ON sitio.SitioID=materialLenioso100.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=materialLenioso100.UPMID

GROUP BY
materialLenioso100.UPMID,
materialLenioso100.SitioID,
materialLenioso100.MaterialLenioso100ID
ORDER BY
materialLenioso100.UPMID