SELECT
costras.UPMID,
costras.SitioID,
costras.CostrasID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
costras.Numero,
costras.Diametro


FROM
SUELO_Costras costras

JOIN SITIOS_Sitio sitio ON sitio.SitioID=costras.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=costras.UPMID

GROUP BY
costras.UPMID,
costras.SitioID,
costras.CostrasID
ORDER BY
costras.UPMID