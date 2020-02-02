keytool -genkey -storetype JKS -keyalg RSA -alias service_provider_alias -keystore service_provider.jks -storepass service_provider_passwd -validity 360 -keysize 2048

keytool -export -alias service_provider_alias -keystore service_provider.jks -rfc -file service_provider_X509certificate.cer