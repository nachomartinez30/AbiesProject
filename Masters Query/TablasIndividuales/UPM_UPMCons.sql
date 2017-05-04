SELECT
upm.UPMID ,
upm.FechaInicio,
upm.FechaFin,
tipoUPM.TipoUPM  AS TipoUPM,
upm.Altitud,
upm.PendienteRepresentativa,
tipoFisiografia.TipoFisiografia  AS TipoFisiografia,
tipoExposicion.Descripcion  AS TipoExposicion,
upm.Predio,
upm.Paraje,
tipoTenencia.Descripcion  AS TipoTenencia,
CASE upm.Accesible WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS Accesible ,
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
tipoInaccesibilidad.Tipo AS TipoInaccesibilidad,
upm.OtroTipoInaccesibilidad,
upm.ExplicacionInaccesibilidad,
CASE upm.InformacionContacto WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS HuboInformacionContacto

FROM
UPM_UPM upm

LEFT JOIN CAT_TipoUPM 	tipoUPM ON tipoUPM.TipoUPMID =upm.TipoUPMID
LEFT JOIN CAT_TipoFisiografia 	tipoFisiografia ON tipoFisiografia.FisiografiaID =upm.FisiografiaID
LEFT JOIN CAT_TipoExposicion 	tipoExposicion ON tipoExposicion.ExposicionID =upm.ExposicionID
LEFT JOIN CAT_TipoTenencia 	tipoTenencia ON tipoTenencia.TipoTenenciaID =upm.TipoTenenciaID
LEFT JOIN CAT_TipoInaccesibilidad 	tipoInaccesibilidad ON tipoInaccesibilidad.TipoInaccesibilidadID =upm.TipoInaccesibilidadID
