SELECT /*DISTINCT*/
Estado,
Count(Estado)

FROM
UPM_UPM upm
LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID

GROUP BY Estado