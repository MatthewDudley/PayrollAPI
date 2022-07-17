FROM openjdk:8
COPY ./target/payroll-api-0.0.1-SNAPSHOT.jar payroll-api-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","payroll-api-0.0.1-SNAPSHOT.jar"]