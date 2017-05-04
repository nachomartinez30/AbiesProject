SELECT
suelo.UPMID,
suelo.SitioID,
suelo.SueloID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
usoSuelo.Descripcion,
suelo.OtroUsoSuelo,
suelo.Espesor,
suelo.PendienteDominante,
suelo.Observaciones,
suelo.NumeroCanalillos,
suelo.ProfundidadPromedioCanalillos,
suelo.AnchoPromedioCanalillos,
suelo.LongitudCanalillos,
suelo.VolumenCanalillos,
suelo.NumeroCarcavas,
suelo.ProfundidadPromedioCarcavas,
suelo.AnchoPromedioCarcavas,
suelo.LongitudCarcavas,
suelo.VolumenCarcavas,
suelo.NumeroMonticulos,
suelo.AlturaPromedioMonticulos,
suelo.AnchoPromedioMonticulos,
suelo.LongitudPromedioMonticulos,
suelo.VolumenMonticulos



FROM
SUELO_Suelo suelo
LEFT JOIN CAT_UsoSuelo usoSuelo ON usoSuelo.UsoSueloID = suelo.UsoSueloID

JOIN SITIOS_Sitio sitio ON sitio.SitioID=suelo.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=suelo.UPMID

GROUP BY
suelo.UPMID,
suelo.SitioID,
suelo.SueloID
ORDER BY
suelo.UPMID