FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=giphyDb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/giphyDb
ADD ./target/giphermanager-1.0.jar /usr/src/giphermanager-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/usr/src/giphermanager-1.0.jar"]
