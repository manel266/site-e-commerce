FROM openjdk:11.0.14

RUN apt update && apt -y upgrade
RUN apt install -y maven
RUN apt clean
RUN rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY . /app/
RUN mvn install -DskipTests

EXPOSE 8080
ENTRYPOINT ["mvn", "spring-boot:run"]