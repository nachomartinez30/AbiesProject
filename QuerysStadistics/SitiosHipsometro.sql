Select 
HipsometroBrujula,
Count(HipsometroBrujula) as Cuenta_Hipsometro


FROM

SITIOS_Sitio sitio

where
HipsometroBrujula=1


Group by HipsometroBrujula
