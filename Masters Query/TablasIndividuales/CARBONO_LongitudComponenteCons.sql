SELECT
longitudComponente.UPMID,
longitudComponente.SitioID,
longitudComponente.LongitudComponenteID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
longitudComponente.Consecutivo,
longitudComponente.Transecto,
tipoComponente.Componente,
familia.Nombre AS Familia,
genero.Nombre AS Genero,
especie.Nombre AS Especie,
infraespecie.Nombre AS Infraespecie,
longitudComponente.NombreComun,
longitudComponente.EsColecta,
longitudComponente.Segmento1,
longitudComponente.Segmento2,
longitudComponente.Segmento3,
longitudComponente.Segmento4,
longitudComponente.Segmento5,
longitudComponente.Segmento6,
longitudComponente.Segmento7,
longitudComponente.Segmento8,
longitudComponente.Segmento9,
longitudComponente.Segmento10,
longitudComponente.Total,
longitudComponente.ClaveColecta



FROM
CARBONO_LongitudComponente	longitudComponente

JOIN SITIOS_Sitio sitio ON sitio.SitioID=longitudComponente.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=longitudComponente.UPMID

LEFT JOIN CAT_FamiliaEspecie familia ON longitudComponente.FamiliaID = familia.FamiliaID 
LEFT JOIN CAT_Genero genero ON longitudComponente.GeneroID = genero.GeneroID 
LEFT JOIN CAT_Especie especie ON longitudComponente.EspecieID = especie.EspecieID 
LEFT JOIN CAT_Infraespecie infraespecie ON longitudComponente.InfraespecieID = infraespecie.InfraespecieID 
LEFT JOIN CAT_TipoComponente 	tipoComponente ON tipoComponente.ComponenteID=longitudComponente.ComponenteID

GROUP BY
longitudComponente.UPMID,
longitudComponente.SitioID,
longitudComponente.LongitudComponenteID
ORDER BY
longitudComponente.UPMID