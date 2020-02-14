This is an example of a SpringBoot SAML client, configured via gradle, currently being used for Keycloak clients.

It also simplifies the building process of a Docker container image, and can also dispatch a running container.

This example was created based on the project from https://github.com/vdenotaris/spring-boot-security-saml-sample
Beware maven central may not find all the required libraries!  Shibolet maven repository (https://build.shibboleth.net/nexus/content/repositories/releases/) is referenced in pom.xml frm this original project.


The following are usage instructions:


1 - If you do previously hold a certificate, extract a JKS for that, otherwise:

* Run keytool_cert_create.sh to create the self-signed jks and X509 certificate
* Keep the alias and password handy

2 - Create a SAML client entry under any IDP realm and map it to the app DNS

* Import the X509 certificate in Keycloak settings for the client (SAML Keys)
* Make sure Valid Redirect URLs are set, along with Logout Service Binding URLs

3 - Update src/main/resources/application.yml

* Under ssl, set the keystore attributes based on step 1
* Under key-manager, set the classpath, store-pass and alias based on the keystore above
* Set metadata URL and entity id based on the realm and client id from step 2

4 - Copy into src/main/resources/certs/ :

* the jks from step 1
* the keycloak truststore jks
* * The IDP certificate can be obtained via the following command line:
 * *  openssl s_client -connect idp_host_name:idp_port -showcert
* * *   Crop the content between ---BEGIN CERTIFICATE--- and ---END CERTIFICATE---
* * *  And paste in a new file (idp.crt)
* Further we create the jks
* *    keytool -storetype JKS -import -trustcacerts -file yourIDP.crt -alias server -keystore idptruststore.jks -storepass truststorepass
* Check your IDP settings
* *    E.g. for Keycloak, check https://www.keycloak.org/docs/latest/server_installation/#enabling-ssl-https-for-the-keycloak-server

5 - Update Dockerfile with keycloak client jks configuration

* Set the build process to include it in the new container image
* Set the Java Options attributes related to the truststore settings:
* *  Path and jks name
* *  Certificate type
* *  Jks password

6 - Run reload.sh script to trigger build and initiate a container


