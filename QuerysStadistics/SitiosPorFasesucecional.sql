Select 
faseSucecional.Clave,
Count(sitio.SitioID) as Conteo_Sitios

FROM

SITIOS_Sitio sitio

Left JOIN CAT_FaseSucecional faseSucecional ON  faseSucecional.FaseSucecionalID=sitio.FaseSucecional
Left JOIN CAT_ClaveSerieV claveSerieV ON  claveSerieV.ClaveSerieVID=sitio.ClaveSerieV

where 
claveSerieV.EsForestal=1
Group by faseSucecional.Clave
Order by faseSucecional.Clave