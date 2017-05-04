SELECT
contacto.UPMID,
contacto.ContactoID,
upmMala.Estado,
upmMala.Municipio,
CASE contacto.TipoContacto WHEN 0 THEN 'Remoto' WHEN 1 THEN 'Presencial' END AS TipoContacto,
contacto.Nombre,
contacto.Direccion,
CASE contacto.TipoTelefono WHEN 1 THEN 'Fijo' WHEN 0 THEN 'Móvil' END AS TipoTelefono,
contacto.NumeroTelefono,
CASE contacto.TieneCorreo WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS TieneCorreo,
contacto.DireccionCorreo,
CASE contacto.TieneRadio WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' END AS TieneRadio,
contacto.Canal,
contacto.Frecuencia,
contacto.Observaciones

FROM
UPM_Contacto contacto

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=contacto.UPMID

GROUP BY
contacto.UPMID,
contacto.ContactoID
ORDER BY
contacto.UPMID
