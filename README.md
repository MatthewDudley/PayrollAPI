# PayrollAPI
Simple API for interacting with employee data

# Getting Started

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

**1. Clone the application**

```bash
git clone https://github.com/MatthewDudley/PayrollAPI.git
```

**2. Create Mysql database**

+ Grab the **creatdb.sql** script in the project and run in your MySql environment.

**3. Update your mysql username and password to your environemnt**

+ `src/main/resources/application.properties`
+ `spring.datasource.username`
+ `spring.datasource.password`

**4. Build and run**

```bash
mvn spring-boot:run
```

Test in your browser of usring Postman!

<http://localhost:8080/api/test>

<http://localhost:8080/api/employees> (GET)

<http://localhost:8080/api/employees> (POST + body needed)

<http://localhost:8080/api/employees/2> (GET)

<http://localhost:8080/api/employees/2> (PUT + body needed)