SELECT /*DISTINCT*/
tipoColocacion.Descripcion,
Count(transponder.UPMID)

FROM
SITIOS_Transponder transponder
LEFT JOIN CAT_TipoColocacion tipoColocacion ON tipoColocacion.TipoColocacionID=transponder.TipoColocacionID



GROUP BY tipoColocacion.Descripcion