This is an example of a SAML client, currently being used for Keycloak clients

1 - Run keytool_cert_create.sh to create the self-signed jks and X509 certificate
  1.1 - Keep the alias and password handy

2 - Create a SAML client under any IDP realm and map it to the app DNS
  2.1 - Inport the X509 certificate in Keycloak settings for the client (SAML Keys)
  2.2 - Make sure Valid Redirect URLs are set, along with Logout Service Binding URLs

3 - Update src/main/resources/application.yml
  3.1 - Under ssl, set the keystore attributes based on step 1
  3.2 - Under key-manager, set the classpath, store-pass and alias based on the keystore above
  3.3 - Set metadata URL and entity id based on the realm and client id from step 2

4 - Copy into src/main/resources/certs/ :
  4.1 - the jks from step 1
  4.2 - the keycloak truststore jks
      4.2.1 - The IDP certificate can be obtained via the following command line:
            openssl s_client -connect idp_host_name:idp_port -showcert
            Crop content between ---BEGIN CERTIFICATE--- and ---END CERTIFICATE---
            And paste in a new file (idp.crt)
      4.2.2 - Further we create the jks
            keytool -storetype JKS -import -trustcacerts -file yourIDP.crt -alias server -keystore idptruststore.jks -storepass truststorepass
      4.2.2 Check your IDP settings
            E.g. for Keycloak, check https://www.keycloak.org/docs/latest/server_installation/#enabling-ssl-https-for-the-keycloak-server

5 - Update Dockerfile with keycloak client jks configuration
  5.1 - Set the build process to include it in the new container image
  5.2 - Set the Java Options attributes related to the truststore settings:
      5.2.1 - Path and jks name
      5.2.2 - Set the certificate type
      5.2.3 - Set the jks password

6 - Run reload.sh script to trigger build and initiate a container

* This example was created based on the project from https://github.com/vdenotaris/spring-boot-security-saml-sample
*  Beware maven central may not find all the required libraries!
 Shibolet maven repository (https://build.shibboleth.net/nexus/content/repositories/releases/) is referenced in pom.xml frm the original project.

