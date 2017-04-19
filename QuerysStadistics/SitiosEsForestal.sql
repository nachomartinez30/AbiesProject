Select 
claveSerieV.EsForestal,
Count(sitio.SitioID) as Conteo_Sitios

FROM

SITIOS_Sitio sitio

Left JOIN CAT_ClaveSerieV claveSerieV ON  claveSerieV.ClaveSerieVID=sitio.ClaveSerieV



Group by claveSerieV.EsForestal
