SELECT
evidenciaErosion.UPMID,
evidenciaErosion.SitioID,
evidenciaErosion.EvidenciaErosionID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
evidenciaErosion.CoberturaSueloID,
evidenciaErosion.Punto,
evidenciaErosion.Dosel,
tipoLecturaTierra.Descripcion

FROM
SUELO_EvidenciaErosion evidenciaErosion

JOIN SITIOS_Sitio sitio ON sitio.SitioID=evidenciaErosion.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=evidenciaErosion.UPMID


LEFT JOIN CAT_TipoLecturaTierra tipoLecturaTierra ON tipoLecturaTierra. LecturaTierraID=evidenciaErosion.LecturaTierraID

GROUP BY
evidenciaErosion.UPMID,
evidenciaErosion.SitioID,
evidenciaErosion.EvidenciaErosionID
ORDER BY
evidenciaErosion.UPMID
