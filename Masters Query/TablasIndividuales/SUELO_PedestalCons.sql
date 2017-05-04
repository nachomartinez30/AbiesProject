SELECT
pedestal.UPMID,
pedestal.SitioID,
pedestal.PedestalID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
pedestal.Numero,
pedestal.Altura

FROM
SUELO_Pedestal pedestal 

JOIN SITIOS_Sitio sitio ON sitio.SitioID=pedestal.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=pedestal.UPMID

GROUP BY
pedestal.UPMID,
pedestal.SitioID,
pedestal.PedestalID

ORDER BY
pedestal.UPMID 

