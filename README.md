<h3>Yummi GR </h3>

![alt text](https://www.pngkit.com/png/full/405-4055413_yummy-cute-donnut-omnomnom-yamiyami-yummy-pug-in.png)
<br>
<p>
  YummiGr is an API that has as main objective the sending of automatic messages of SMS and emails, an API works in conjunction with the umbrella, however it can be used to protect an umbrella API. The sending of messages is automatic, so you have to configure your SMS/EMAIL that will send messages to the other contacts in the list. A message can be configured and customized according to your needs. Including a database for you, information about contacts, messages, files, etc.
</p>
<br>
<p> 
O YummiGr é uma API que tem como principal objetivo fornecer o envio de mensagens automáticas de  SMS e emails, a api trabalha em conjunto com o umbrellaapi, porém é possível utilizar seus recursos sem importar a API guarda-chuva. O envio de mensagens é automático, então você só precisa configurar o seu SMS/EMAIL que irá enviar mensagens para os outros contatos da lista. a mensagem pode ser configurada e personalizada de acordo com suas necessidades. incluindo api fornece um banco de dados para você armazenar informações sobre contatos, mensagens, arquivos, etc.
</p>
## Table of content
- [Getting Started]
- [Requirements](
- [How to Build]
- [How to Run]
- [Built With]
- [Authors](#authors)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Requirements

For building and running the application you need:

- [Java JDK 10](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)
- [PostgreSQL 9.6](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- [SpringBoot](https://spring.io/)

#### Environment Variables

In order to run the application you must need to define the following environment variables:

 **YUMMIGR_DB_POSTGRESQL**         --***localhost***--      Defines the IP or hostname of PostgreSQL instance to be connected with.                                                                                                            
 **YUMMIGR_DB_PORT**          --***5432***--          Defines the port of the PostgreSQL instance to be connected with.                                                                                                                  
 **YUMMIGR_DB_DATABASE**      --***yuumigrdb***--      Defines the database/schema on which tables and data will be stored.                                                                                                         
 **YUMMIGR_DB_USERNAME**     --***postgres***--         Defines the database username.                                                                                                                                                
 **YUMMIGR_DB_PASSWORD**   --***admin***--         Defines the database password.
 <br>
 **YUMMIGR_JAVA_MAIL**     --***custom***--        Defines the email account of gmail connector javaMail.
 <br>
 **YUMMIGR_JAVA_MAIL_PASS** --***custom***--   Defines the email password account of gmail connector javaMail.
 
 ### How To Build

From your command line:

bash
# Clone this repository
$ https://github.com/lsbloo/YummiGr.git

# Go into the repository root
$ cd YummiGR

# Install dependencies and build the project
$ ./mvnw clean package


These commands will generate the following file: **YummiGr/YummiGR/target/yummigr.jar**.

### How To Run

#### Manually using java environment variables
Note: This example assumes that you have a psql instance running on localhost.

bash
# Go into the repository root
$ cd YummiGR

# Clean and Build the project using maven
$ ./mvnw clean package

# Execute the jar with the environment variables set as shown in the example below.
$ java \
-YUMMIGR_DB_POSTGRESQL=localhost \
-YUMMIGR_DB_PORT=5432 \
-YUMMIGR_DB_DATABASE=tracytd \
-YUMMIGR_DB_USERNAME=postgresql \
-YUMMIGR_DB_PASSWORD=admin \
-jar YummiGr/YummiGR/target/yummigr.jar

 
 
 
