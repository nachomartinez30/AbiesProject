SELECT
colectaBotanica.UPMID,
colectaBotanica.ColectaBotanicaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
familiaEspecie.Nombre AS FamiliaEspecie,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
colectaBotanica.NombreComun,
colectaBotanica.Sitio,
seccionesTaxonomicas.Seccion AS SeccionesTaxonomicas ,
colectaBotanica.Consecutivo,
colectaBotanica.ClaveColecta,
colectaBotanica.ContraFuertes,
CASE colectaBotanica.Exudado  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END Exudado,
colectaBotanica.IndicarExudado,
CASE colectaBotanica.Color  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END Color,
colectaBotanica.IndicarColor,
CASE colectaBotanica.CambioColor  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END CambioColor,
CASE colectaBotanica.AceitesVolatiles  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AceitesVolatiles,
CASE colectaBotanica.ColorFlor  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ColorFlor,
colectaBotanica.IndicarColorFlor,
CASE colectaBotanica.HojasTejidoVivo  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END HojasTejidoVivo,
CASE colectaBotanica.FotoFlor  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoFlor,
CASE colectaBotanica.FotoFruto  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoFruto,
CASE colectaBotanica.FotoHojasArriba  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoHojasArriba,
CASE colectaBotanica.FotoHojasAbajo  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoHojasAbajo,
CASE colectaBotanica.FotoArbolCompleto  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoArbolCompleto,
CASE colectaBotanica.FotoCorteza  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END FotoCorteza,
CASE colectaBotanica.VirutaIncluida  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END VirutaIncluida,
CASE colectaBotanica.CortezaIncluida  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END CortezaIncluida,
colectaBotanica.MaderaIncluida,
CASE colectaBotanica.Observaciones  WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END Observaciones


FROM
TAXONOMIA_ColectaBotanica 	colectaBotanica

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=colectaBotanica.UPMID

LEFT JOIN CAT_FamiliaEspecie 	familiaEspecie ON familiaEspecie.FamiliaID = colectaBotanica.FamiliaID
LEFT JOIN CAT_Genero 	genero ON genero.GeneroID = colectaBotanica.GeneroID
LEFT JOIN CAT_Especie 	especie ON especie.EspecieID = colectaBotanica.EspecieID
LEFT JOIN CAT_Infraespecie 	infraespecie ON infraespecie.InfraespecieID = colectaBotanica.InfraespecieID
LEFT JOIN CAT_SeccionesTaxonomicas 	seccionesTaxonomicas ON seccionesTaxonomicas.SeccionTaxonomicaID = colectaBotanica.SeccionID

GROUP BY 
colectaBotanica.UPMID,
colectaBotanica.ColectaBotanicaID

ORDER BY
colectaBotanica.UPMID