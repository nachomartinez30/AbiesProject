Select 
sitio.CintaClinometroBrujula,
Count(sitio.CintaClinometroBrujula) as Cuenta_CintaClinometro


FROM

SITIOS_Sitio sitio

where
sitio.CintaClinometroBrujula=1


Group by HipsometroBrujula