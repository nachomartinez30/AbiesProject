SELECT
hojarasca.UPMID,
hojarasca.SitioID,
hojarasca.HojarascaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
hojarasca.Punto,
tipoHojarasca.Descripcion,
hojarasca.EspesorHO,
hojarasca.EspesorF,
hojarasca.PesoTotalHO,
hojarasca.PesoTotalF,
hojarasca.PesoMuestraHO,
hojarasca.PesoMuestraF,
hojarasca.Observaciones,
hojarasca.ClaveHO,
hojarasca.ClaveF

FROM
SUELO_Hojarasca hojarasca

JOIN SITIOS_Sitio sitio ON sitio.SitioID=hojarasca.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=hojarasca.UPMID

LEFT JOIN CAT_TipoHojarasca tipoHojarasca ON tipoHojarasca.TipoHojarascaID = hojarasca.TipoHojarascaID


GROUP BY
hojarasca.UPMID,
hojarasca.SitioID,
hojarasca.HojarascaID
ORDER BY
hojarasca.UPMID