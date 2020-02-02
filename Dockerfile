FROM openjdk:8-jdk-alpine

ADD ./build/resources/main/certs/idptruststore.jks idp_client.jks
ADD ./build/libs/saml_template-1.0.jar app.jar

ENV JAVA_OPTS="-Xss2048k -Djavax.net.ssl.trustStore=/idp_client.jks -Djavax.net.ssl.trustStoreType=JKS -Djavax.net.ssl.trustStorePassword=truststorepass"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
