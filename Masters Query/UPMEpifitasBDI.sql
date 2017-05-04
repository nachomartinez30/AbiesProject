--información tabla malla de puntos-
upm.UPMID,
upmMalla.Estado,
upmMalla.Municipio,
upmMalla.Region,
upm.FechaInicio,
upm.FechaFin,


--información de tabla presencia de epifitas -

UPM_Epifita	ClaseTipoID
UPM_Epifita	PresenciaTroncosID
UPM_Epifita	PresenciaRamasID


--información de la tabla sitio-

SITIOS_Sitio	Sitio
SITIOS_Sitio	ClaveSerieV
SITIOS_Sitio	FaseSucecional
SITIOS_Sitio	ArbolFuera
SITIOS_Sitio	Ecotono
SITIOS_Sitio	CondicionPresenteCampo
SITIOS_Sitio	CondicionEcotono

from

INNER JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID
