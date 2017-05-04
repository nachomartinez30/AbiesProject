SELECT
mallaPuntos.UPMID,
mallaPuntos.ConglomeradoID,
mallaPuntos.LatDms,
mallaPuntos.LongDms,
mallaPuntos.X,
mallaPuntos.Y,
mallaPuntos.Estado,
mallaPuntos.Region,
mallaPuntos.PROYECTO,
CASE mallaPuntos.A WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloA ,
CASE mallaPuntos.B WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloB ,
CASE mallaPuntos.C WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloC ,
CASE mallaPuntos.D WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloD ,
CASE mallaPuntos.E WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloE ,
CASE mallaPuntos.F WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloF ,
CASE mallaPuntos.G WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloG ,
CASE mallaPuntos.H WHEN 1 THEN 'SI' WHEN 0 THEN 'NO'END AS ModuloH ,
mallaPuntos.SecuenciaID,
mallaPuntos.Ciclo,
CASE mallaPuntos.ProveedorID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor,
mallaPuntos.Municipio,
mallaPuntos.MunicipioID


FROM
UPM_MallaPuntos mallaPuntos
