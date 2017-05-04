SELECT 
--Informacion de UPM
upm.UPMID,
CASE upmMalla.A WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END A,
CASE upmMalla.B WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END B,
CASE upmMalla.C WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END C,
CASE upmMalla.D WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END D,
CASE upmMalla.E WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END E,
CASE upmMalla.F WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END F,
CASE upmMalla.G WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END G,
CASE upmMalla.H WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END H,
upmMalla.Estado,
upmMalla.Municipio,
upmMalla.Region,
CASE upmMalla.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
upm.FechaInicio,
upm.FechaFin,
upm.Altitud,
upm.PendienteRepresentativa,
tipoExposicion.Descripcion AS Exposicion,
tipoFisiografia.TipoFisiografia AS Fisiografia,
upm.Predio,
upm.Paraje,
tipoTenencia.Descripcion AS TipoTenencia,
CASE upm.Accesible WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END Accesible,
upm.GradosLatitud,
upm.MinutosLatitud,
upm.SegundosLatitud,
upm.GradosLongitud,
upm.MinutosLongitud,
upm.SegundosLongitud,
upm.Datum,
upm.ErrorPresicion,
upm.Azimut,
upm.Distancia,
upm.TipoInaccesibilidadID,
upm.OtroTipoInaccesibilidad,
upm.ExplicacionInaccesibilidad,
CASE upm.InformacionContacto WHEN 1 THEN 'SI' WHEN 2 THEN 'NO' END HuboContacto,

--informacion de Sitio
tipoColocacion.Descripcion AS TipoColocacion,
sitioTransponder.Especifique,
sitioTransponder.Observaciones,
sitioObservaciones.Observaciones,
CASE upmContacto.TipoContacto WHEN 1 THEN 'Precencial' WHEN 2 THEN 'Remoto' END TipoContacto,
upmContacto.Nombre,
upmContacto.Direccion,
CASE upmContacto.TipoTelefono WHEN 0 THEN 'N/A' WHEN 1 THEN 'Fijo' WHEN 2 THEN 'Movil' END TipoTelefono,
upmContacto.NumeroTelefono,
CASE upmContacto.TieneCorreo WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END TieneCorreo,
upmContacto.DireccionCorreo,
CASE upmContacto.TieneRadio WHEN 0 THEN 'NO' WHEN 1 THEN 'SI' END TieneRadio,
upmContacto.Canal,
upmContacto.Frecuencia,
upmContacto.Observaciones


FROM

UPM_UPM upm 


LEFT JOIN UPM_Contacto upmContacto ON upmContacto.UPMID=upm.UPMID
LEFT JOIN SITIOS_Sitio sitio ON sitio.UPMID=upm.UPMID
LEFT JOIN SITIOS_Transponder sitioTransponder ON sitioTransponder.SitioID=sitio.SitioID
LEFT JOIN SITIOS_Observaciones sitioObservaciones ON sitioObservaciones.SitioID=sitio.SitioID


INNER JOIN UPM_MallaPuntos upmMalla ON upmMalla.UPMID=upm.UPMID
INNER JOIN UPM_Brigada upmBrigada ON upmBrigada.UPMID=upm.UPMID


LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID
LEFT JOIN CAT_TipoFisiografia tipoFisiografia ON tipoFisiografia.FisiografiaID=upm.FisiografiaID
LEFT JOIN CAT_TipoExposicion tipoExposicion ON tipoExposicion.ExposicionID=upm.ExposicionID
LEFT JOIN CAT_TipoTenencia tipoTenencia ON tipoTenencia. TipoTenenciaID=upm.TipoTenenciaID
LEFT JOIN CAT_TipoInaccesibilidad tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID=upm.TipoInaccesibilidadID
 JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=sitioTransponder.TipoColocacionID


GROUP BY
upm.UPMID

ORDER BY
upm.UPMID