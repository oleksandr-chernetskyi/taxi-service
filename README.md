![taxi-service logo](readme.images/top.png)


### 🔍 Description:
___

This application service as an educational platform that demonstrates the functionality of the taxi service. A multi-level architecture is implemented here, where each level performs certain tasks and interacts with other levels. This approach improves logical separation, modularity, ease of maintenance, and scalability.
The application provides secure access through session-based authentication filters. It collects and logs authentication information and performs important database operations, including creation, update, and deletion. The output is displayed both in the console and in the ```.log``` file. In addition, it allows the registration of drivers and simplifies the management of relationships between cars, drivers and manufacturers. The application supports various CRUD operations for efficient data management.

### ⚙️ Functionalities:
___
>- authentication
>- add new manufacturer
>- add new driver
>- add new car
>- add driver to car
>- display list all cars by driver
>- display all drivers
>- display all cars
>- display all manufacturers

### 🔗 Architecture:
___
>- DAO - data access layer
>- Service - application logic layer
>- Controllers - presentation layer


### 🚕 How to run this project:
___
Please make sure that you have Tomcat and MySQL installed and properly configured on your computer. You can find official websites for these programs by following the links below:</br>
<a href="https://tomcat.apache.org/">Tomcat: Official website</a></br>
<a href="https://www.mysql.com/">MySQL: Official website</a></br>
These tools are essential for running the application successfully. Ensure that they are installed and set up correctly before proceeding.
>- Clone project from repository
>- Add a tomcat configurations:
>- * tomcat server: local
>- * deployment: war exploded
>- * application context: ``/``
>- To initialize the database, execute the SQL script located at ``src/main/resources/init.db.sql``
>- Configure ``src/main/java/taxi/util/ConnectionUtil.java`` with the following credentials:
>- * ``JDBC DRIVER``
>- * ``URL``
>- * ``USERNAME``
>- * ``PASSWORD``
>- Add required maven dependencies in ``pom.xml`` file
>- Build the project using Maven command in terminal: ``mvn clean install``
>- Once the project is deployed, access the taxi service by opening http://localhost:8080 in your web browser.


### 💻 Technologies:
___
>- [![maven](readme.images/technologies/maven.png)](https://maven.apache.org/index.html)
>- [![JDK](https://img.shields.io/badge/JDK-v.18.0.1-orange)](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
>- [![Tomcat](https://img.shields.io/badge/Tomcat-v.9.0.71-yellow)](https://tomcat.apache.org/download-90.cgi)
>- [![MySQL](https://img.shields.io/badge/MySQL-v.8.0.33-green)](https://dev.mysql.com/downloads/mysql/8.0.33.html)
>- [![Java Servlets API](https://img.shields.io/badge/Java%20Servlets%20API-v.4.0.1-blue)](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1)
>- [![JDBC](https://img.shields.io/badge/JDBC-v.4.2-darkviolet)](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.8.0)
>- [![JSP](https://img.shields.io/badge/JSP-v.2.2-lightgray)](https://mvnrepository.com/artifact/javax.servlet.jsp/jsp-api/2.2)
>- [![JSTL](https://img.shields.io/badge/JSTL-v.1.2-green)](https://mvnrepository.com/artifact/javax.servlet/jstl/1.2)
>- [![LOG4J](https://img.shields.io/badge/Log4j-v.2.20.0-yellow)](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.20.0)
>- [![HTML](https://img.shields.io/badge/HTML-v.5-orange)](https://html.spec.whatwg.org/)

<hr style="border: 2px solid lightgray;">

### 💬 Contacts:
___

```Oleksandr Chernetskiy:```<br>
- [X] [![Email](readme.images/contacts/email.png)](mailto:liero2d@gmail.com) <br>
- [X] [![Linkedin](readme.images/contacts/link.png)](https://www.linkedin.com/in/%D0%B0%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80-%D1%87%D0%B5%D1%80%D0%BD%D0%B5%D1%86%D0%BA%D0%B8%D0%B9-05106a25b/)


