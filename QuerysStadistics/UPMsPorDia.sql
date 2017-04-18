SELECT DISTINCT
julianday(upm.FechaFin)-julianday(upm.FechaInicio)+1 as DIAS,
COUNT(upm.UPMID) AS CUENTA_UPMS

FROM
UPM_UPM upm
LEFT JOIN UPM_MallaPuntos mallaPuntos ON mallaPuntos.UPMID=upm.UPMID

GROUP BY DIAS
