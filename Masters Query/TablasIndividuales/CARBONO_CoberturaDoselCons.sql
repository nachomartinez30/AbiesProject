SELECT
coberturaDosel.UPMID,
coberturaDosel.SitioID,
coberturaDosel.CoberturaDoselID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
coberturaDosel.Transecto,
coberturaDosel.Punto1,
coberturaDosel.Punto2,
coberturaDosel.Punto3,
coberturaDosel.Punto4,
coberturaDosel.Punto5,
coberturaDosel.Punto6,
coberturaDosel.Punto7,
coberturaDosel.Punto8,
coberturaDosel.Punto9,
coberturaDosel.Punto10

FROM
CARBONO_CoberturaDosel coberturaDosel

JOIN SITIOS_Sitio sitio ON sitio.SitioID=coberturaDosel.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=coberturaDosel.UPMID

GROUP BY
coberturaDosel.UPMID,
coberturaDosel.SitioID,
coberturaDosel.CoberturaDoselID
ORDER BY
coberturaDosel.UPMID