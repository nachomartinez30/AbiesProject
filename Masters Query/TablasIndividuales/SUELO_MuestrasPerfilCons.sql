SELECT
muestrasPerfil.UPMID,
muestrasPerfil.SitioID,
muestrasPerfil.MuestrasPerfilID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
muestrasPerfil.GradosLatitud,
muestrasPerfil.MinutosLatitud,
muestrasPerfil.SegundosLatitud,
muestrasPerfil.GradosLongitud,
muestrasPerfil.MinutosLongitud,
muestrasPerfil.SegundosLongitud,
muestrasPerfil.Elevacion,
muestrasPerfil.DiametroInterno,
muestrasPerfil.DiametroExterno,
muestrasPerfil.Altura,
muestrasPerfil.Observaciones


FROM
SUELO_MuestrasPerfil muestrasPerfil

JOIN SITIOS_Sitio sitio ON sitio.SitioID=muestrasPerfil.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=muestrasPerfil.UPMID

GROUP BY
muestrasPerfil.UPMID,
muestrasPerfil.SitioID,
muestrasPerfil.MuestrasPerfilID
ORDER BY
muestrasPerfil.UPMID