FROM maven:3.6.3-openjdk-8 AS build
WORKDIR /usr/app
COPY ./ .
RUN mvn clean package

FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar ./phonebook.jar
ENTRYPOINT ["java","-jar","./phonebook.jar"]
