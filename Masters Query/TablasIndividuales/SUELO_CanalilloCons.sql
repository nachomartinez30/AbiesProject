SELECT
canalillo.UPMID,
canalillo.SitioID,
canalillo.CanalilloID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
sitio.Sitio,
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
canalillo.Numero,
canalillo.Ancho,
canalillo.Profundidad


FROM
SUELO_Canalillo canalillo

JOIN SITIOS_Sitio sitio ON sitio.SitioID=canalillo.SitioID
JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=canalillo.UPMID

GROUP BY
canalillo.UPMID,
canalillo.SitioID,
canalillo.CanalilloID
ORDER BY
canalillo.UPMID