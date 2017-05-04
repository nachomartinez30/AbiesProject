SELECT
--Informacion de UPM
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,


CASE WHEN familia.Nombre IS NULL THEN 'Desconocido' ELSE familia.Nombre END  Familia, 
CASE WHEN genero.Nombre IS NULL THEN 'Desconocido' ELSE genero.Nombre END  Genero, 
CASE WHEN especie.Nombre IS NULL THEN 'Desconocido' ELSE especie.Nombre END  Especie, 
CASE WHEN infraespecie.Nombre IS NULL THEN 'Desconocido' ELSE infraespecie.Nombre END  Infraespecie, 
CASE WHEN colectaBotanica.NombreComun IS NULL THEN '' ELSE colectaBotanica.NombreComun END  NombreComun, 
colectaBotanica.ClaveColecta, 
colectaBotanica.Sitio,
seccionTaxonomica.Seccion,
colectaBotanica.Consecutivo,
CASE colectaBotanica.ContraFuertes WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	ContraFuertes,
CASE colectaBotanica.Exudado WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Exudado, 
colectaBotanica.IndicarExudado,
CASE colectaBotanica.Color WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Color, 
colectaBotanica.IndicarColor ,
CASE colectaBotanica.CambioColor WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	CambioColor,
CASE colectaBotanica.AceitesVolatiles WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	AceitesVolatiles,
CASE colectaBotanica.ColorFlor WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	ColorFlor,
colectaBotanica.IndicarColorFlor,
CASE colectaBotanica.HojasTejidoVivo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	HojasTejidoVivo,
CASE colectaBotanica.FotoFlor WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoFlor,
CASE colectaBotanica.FotoFruto WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoFruto,
CASE colectaBotanica.FotoHojasArriba WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoHojasArriba,
CASE colectaBotanica.FotoHojasAbajo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoHojasAbajo,
CASE colectaBotanica.FotoArbolCompleto WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoArbolCompleto,
CASE colectaBotanica.FotoCorteza WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	FotoCorteza,
CASE colectaBotanica.VirutaIncluida WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	VirutaIncluida,
CASE colectaBotanica.CortezaIncluida WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	CortezaIncluida,
CASE colectaBotanica.MaderaIncluida WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS 	MaderaIncluida,
CASE WHEN colectaBotanica.Observaciones IS NULL THEN '' ELSE colectaBotanica.Observaciones END AS Observaciones 

from 
TAXONOMIA_ColectaBotanica colectaBotanica

LEFT JOIN UPM_UPM upm ON upm.UPMID=colectaBotanica.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_FamiliaEspecie familia 	ON colectaBotanica.FamiliaID = familia.FamiliaID 
LEFT JOIN CAT_Genero genero 	ON colectaBotanica.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie 	ON colectaBotanica.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecie 	ON colectaBotanica.InfraespecieID = infraespecie.InfraespecieID 
LEFT JOIN CAT_SeccionesTaxonomicas seccionTaxonomica 	ON colectaBotanica.SeccionID = seccionTaxonomica.SeccionTaxonomicaID

GROUP BY
colectaBotanica.UPMID,
colectaBotanica.SitioID,
colectaBotanica.ColectaBotanicaID

ORDER BY
colectaBotanica.UPMID