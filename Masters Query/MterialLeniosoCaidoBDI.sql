--Informacion de UPM
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,
CASE upmMalla.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upm.FechaInicio,
upmMalla.Region,
upm.FechaInicio,
upm.FechaFin,
upm.Altitud,
upm.PendienteRepresentativa,
fisiografia.TipoFisiografia AS Fisiografia,
exposicionUPM.Descripcion AS Exposicion,

--información de la tabla sitio-

sitio.Sitio,
sitio.GradosLatitud,
sitio.MinutosLatitud,
sitio.SegundosLatitud,
sitio.GradosLongitud,
sitio.MinutosLongitud,
sitio.SegundosLongitud,
sitio.ErrorPresicion,
claveSerieV.TipoVegetacion ,
faseSucecional.Clave  AS FaseSucecional,
CASE sitio.ArbolFuera WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END ArbolFuera,
sitio.CondicionEcotono,
sitio.Ecotono,
sitio.CondicionPresenteCampo,
sitio.Observaciones,
sitio.Cuadrante1,
sitio.Cuadrante2,
sitio.Cuadrante3,
sitio.Cuadrante4,



--información de la tabla material leñoso caido 100-

materialLenioso100.Transecto AS ML100_Transecto,
materialLenioso100.Pendiente AS ML100_Pendiente,
materialLenioso100.UnaHora AS ML100_UnaHora,
materialLenioso100.DiezHoras AS ML100_DiezHoras,
materialLenioso100.CienHora AS ML100_CienHora,




--información de la tabla material leñoso caido 1000-


materialLenioso1000.Transecto AS ML1000_Transecto,
materialLenioso1000.Diametro AS ML1000_Diametro,
materialLenioso1000.Gradoo AS ML1000_Gradoo,



--información de la tabla cobertura dosel-

CARBONO_CoberturaDosel	Transecto
CARBONO_CoberturaDosel	Punto1
CARBONO_CoberturaDosel	Punto2
CARBONO_CoberturaDosel	Punto3
CARBONO_CoberturaDosel	Punto4
CARBONO_CoberturaDosel	Punto5
CARBONO_CoberturaDosel	Punto6
CARBONO_CoberturaDosel	Punto7
CARBONO_CoberturaDosel	Punto8
CARBONO_CoberturaDosel	Punto9
CARBONO_CoberturaDosel	Punto10


--información cubierta vegetal-

CARBONO_CubiertaVegetal	Transecto
CARBONO_CubiertaVegetal	ComponenteID
CARBONO_CubiertaVegetal	Altura5
CARBONO_CubiertaVegetal	Altura10


--información tabla longitud por componente-

CARBONO_LongitudComponente	Consecutivo
CARBONO_LongitudComponente	Transecto
CARBONO_LongitudComponente	ComponenteID
CARBONO_LongitudComponente	FamiliaID
CARBONO_LongitudComponente	GeneroID
CARBONO_LongitudComponente	EspecieID
CARBONO_LongitudComponente	InfraespecieID
CARBONO_LongitudComponente	NombreComun
CARBONO_LongitudComponente	EsColecta
CARBONO_LongitudComponente	Segmento1
CARBONO_LongitudComponente	Segmento2
CARBONO_LongitudComponente	Segmento3
CARBONO_LongitudComponente	Segmento4
CARBONO_LongitudComponente	Segmento5
CARBONO_LongitudComponente	Segmento6
CARBONO_LongitudComponente	Segmento7
CARBONO_LongitudComponente	Segmento8
CARBONO_LongitudComponente	Segmento9
CARBONO_LongitudComponente	Segmento10
CARBONO_LongitudComponente	Total
CARBONO_LongitudComponente	ClaveColecta

FROM
SITIOS_Sitio sitio

LEFT JOIN UPM_UPM upm ON upm.UPMID=sitio.UPMID
LEFT JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID

LEFT JOIN CAT_ClaveSerieV claveSerieV ON claveSerieV.ClaveSerieVID = sitio.ClaveSerieV
LEFT JOIN CAT_FaseSucecional faseSucecional on faseSucecional.FaseSucecionalID =sitio.FaseSucecional
LEFT JOIN CAT_TipoExposicion exposicionUPM ON exposicionUPM.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoFisiografia fisiografia ON fisiografia.FisiografiaID=upm.FisiografiaID
LEFT JOIN (

			SELECT
			mateLenio100.SitioID, 
			mateLenio100.Transecto AS Transecto,
			mateLenio100.Pendiente AS Pendiente,
			mateLenio100.UnaHora AS UnaHora,
			mateLenio100.DiezHoras AS DiezHoras,
			mateLenio100.CienHoraS AS CienHoras
			FROM
			CARBONO_MaterialLenioso100 mateLenio100

) materialLenioso100 ON materialLenioso100.SitioID=sitio.SitioID
LEFT JOIN (

		SELECT
			mateLenio1000.SitioID, 
			mateLenio1000.Transecto,
			mateLenio1000.Diametro,
			mateLenio1000.Grado,
			FROM
			CARBONO_MaterialLenioso1000 mateLenio1000

) materialLenioso1000 ON materialLenioso1000.SitioID=sitio.SitioID