SELECT
cubiertaVegetal.UPMID,
cubiertaVegetal.SitioID,
cubiertaVegetal.CubiertaVegetalID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
cubiertaVegetal.Transecto,
carbonoComponente.Componente,
cubiertaVegetal.Altura5,
cubiertaVegetal.Altura10


FROM
CARBONO_CubiertaVegetal	cubiertaVegetal

JOIN SITIOS_Sitio sitio ON sitio.SitioID=cubiertaVegetal.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=cubiertaVegetal.UPMID

LEFT JOIN CAT_CarbonoComponente carbonoComponente ON carbonoComponente.ComponenteID =cubiertaVegetal.ComponenteID

GROUP BY
cubiertaVegetal.UPMID,
cubiertaVegetal.SitioID,
cubiertaVegetal.CubiertaVegetalID
ORDER BY
cubiertaVegetal.UPMID