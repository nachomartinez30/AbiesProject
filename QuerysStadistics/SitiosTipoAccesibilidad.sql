Select Distinct
tipoInaccesibilidad.Tipo,
Count(sitio.SitioID) as Conteo
FROM

SITIOS_Sitio sitio
left Join CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=sitio.TipoInaccesibilidad

where
SitioAccesible=0

GROUP BY tipoInaccesibilidad.Tipo