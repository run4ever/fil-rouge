#env Java runtime
FROM openjdk:8-jdk-alpine

#réperoire de travail
VOLUME /tmp

#port exposition
EXPOSE 8080

# chemin de Jar de notre application
ARG JAR_FILE=tcl-exposition/target/tcl-exposition-1.0-SNAPSHOT.jar

#ajout le JAR dans container
ADD ${JAR_FILE} mytcllist.jar

#Run Jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/mytcllist.jar"]
