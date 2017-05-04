SELECT
arboladoTroza.UPMID,
arboladoTroza.SitioID,
arboladoTroza.TrozaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________

arboladoTroza.SubmuestraID,
arboladoTroza.NoTroza,
tipoTroza.Descripcion as TipoTroza

FROM
ARBOLADO_Troza	arboladoTroza

JOIN SITIOS_Sitio sitio ON sitio.SitioID=arboladoTroza.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=arboladoTroza.UPMID

LEFT JOIN CAT_TipoTroza	tipoTroza ON tipoTroza.TipoTrozaID=arboladoTroza.TipoTrozaID

GROUP BY
arboladoTroza.UPMID,
arboladoTroza.SitioID,
arboladoTroza.TrozaID
ORDER BY
arboladoTroza.UPMID