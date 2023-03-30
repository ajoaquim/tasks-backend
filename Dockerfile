FROM tomcat:8.5.50-jdk11-openjdk

ARG CONTEXT
ARG WAR_FILE

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war