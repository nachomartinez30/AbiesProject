SELECT
brigada.UPMID,
brigada.BrigadaID,
brigada.BrigadistaID,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
upmMala.Estado,
upmMala.Municipio,
-- _____________________________________________________________________________________________________________________________________________________________________________________________
CASE brigada.PuestoID WHEN 1 THEN 'Jefe' WHEN 2 THEN 'Auxiliar 1' WHEN 3 THEN 'Auxiliar 2' END Puesto,
CASE brigada.EmpresaID WHEN 1 THEN 'DIAAPROY' WHEN 2 THEN 'INYDES' WHEN 3 THEN 'AMAREF' END Proveedor


FROM
UPM_Brigada brigada

JOIN UPM_MallaPuntos upmMala ON upmMala.UPMID=brigada.UPMID

GROUP BY
brigada.UPMID,
brigada.BrigadaID,
brigada.BrigadistaID
ORDER BY
brigada.UPMID,
brigada.PuestoID