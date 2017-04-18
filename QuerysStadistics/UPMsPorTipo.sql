SELECT /*DISTINCT*/
tipoUPM.TipoUPM,
Count(upm.UPMID)

FROM
UPM_UPM upm
LEFT JOIN CAT_TipoUPM tipoUPM ON tipoUPM.TipoUPMID=upm.TipoUPMID

GROUP BY tipoUPM.TipoUPM
