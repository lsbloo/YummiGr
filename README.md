<h3>Yummi GR </h3>

![alt text](https://www.pngkit.com/png/full/405-4055413_yummy-cute-donnut-omnomnom-yamiyami-yummy-pug-in.png)
<br>
<p>
  YummiGr is an API that has as main objective the sending of automatic messages of SMS and emails, an API works in conjunction with the umbrella, however it can be used to protect an umbrella API. The sending of messages is automatic, so you have to configure your SMS/EMAIL that will send messages to the other contacts in the list. A message can be configured and customized according to your needs. Including a database for you, information about contacts, messages, files, etc.
Once connected to api umbrella, yummigr provides the features of umbrella, being able to manage instagram profiles. Some iterations of umbrella: profile management (profile creation, uploading photos, following other profiles for a certain list of hashtags, following friends of my followers, enjoying photos and comments of my followers, following other profiles given a location).
  
  The goal of using api umbrella and api yummi is to create a unified api that has as main objective to boost digital marketing for small and medium businesses. The yummi works with the sending part of emails / sms and integration with umbrella. Already the umbrella is responsible for automated interactions in the social instagram network. So yummiGr uses the umbrella to add value to it. If you are thinking about creating an application that aims to empower the micro-entrepreneur through a technological solution specifically geared towards instagram / emails / sms. YummiGr offers a feature set.


</p>
<br>
<p> 
O YummiGr é uma API que tem como principal objetivo fornecer o envio de mensagens automáticas de  SMS e emails, a api trabalha em conjunto com o umbrellaapi, porém é possível utilizar seus recursos sem importar a API guarda-chuva. O envio de mensagens é automático, então você só precisa configurar o seu SMS/EMAIL que irá enviar mensagens para os outros contatos da lista. a mensagem pode ser configurada e personalizada de acordo com suas necessidades. incluindo api fornece um banco de dados para você armazenar informações sobre contatos, mensagens, arquivos, etc.
 Uma vez que conectada a api umbrella, o yummigr disponibiliza os recursos da umbrella, sendo possivel gerenciar perfis de instagram. Algumas iterações da umbrella: gerenciamento de perfis(criação de perfil,upload de fotos(feed),seguir outros perfis por uma determinada lista de hashtags,seguir amigos dos meus seguidores,curtir fotos e comentários dos meus seguidores, seguir outros perfis dada uma localização).
  
  O objetivo do uso da api umbrella e da api yummi é criar uma api unificada que tem como principal objetivo potencializar o marketing digital para o pequeno e médio negócio. O yummi trabalha com a parte de envio de emails/sms e integração com a umbrella. Já a umbrella é responsavel por interações automatizadas na rede social instagram. Sendo assim a yummiGr utiliza a umbrella para agregar valor a mesma. Se você pensa em criar um aplicativo que tenha como objetivo potencializar o micro-empreendor através de uma solução tecnologica voltada especificamente para o instagram/emails/sms. O yummiGr oferece um kit de funcionalidades.

<br>
Umbrella API: https://github.com/lsbloo/UmbrellaServer
<br>
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
-YUMMIGR_DB_DATABASE=yummidb \
-YUMMIGR_DB_USERNAME=postgresql \
-YUMMIGR_DB_PASSWORD=admin \
-jar YummiGr/YummiGR/target/yummigr.jar

 
 # HOW TO EXECUTE WITH DOCKER.
 
 
 
 
 # HOW TO BUILD AND EXECUTE WITH HEROKU.
 
 
 
 
 
 # How to use the urls in this api.
 
 <h4>Creation of users.</h4>
 <p>
 Create username yummi: /yummicr/api/v1/mgmnt/users/c/
 
This url is responsible for creating yummi users and admits some values. Your HTTP verb is POST

##### username(String)
##### password(String)
##### first_name(String)
##### last_name(String)
##### email(String)
##### actived(boolean)
##### identifier(String)
 </p>
 <br>
 <h4>Authentication of users.</h4>
 <p>
  Authentication of user : localhost:8080/login.
  
This url provides authentication of users in yummi, given a user created and authenticated successfully is generated a    token with validity of 10 days. 
the HTTP verb is POST
  
  ##### username(String)
  ##### password(String)
  </p>
<br>
<h4>Disable user</h4>
<p>
  Disable : /yummicr/api/v1/mgmnt/users/desative/account/user
  
  This url provides the deactivation of a user whose data still persists in api.
  the verb http is delete.

 #### identifier(String)
 
 </p>
 <br>
 <h4>View Information Users</h4>
 <p>
  View Information Users:  /yummicr/api/v1/mgmnt/users/list/all/users/
  
  Viewing all active users on the system, only the user who has access permission (ADMIN) can view. 
  the verb http is get.
  </p>
  <br>
  
<h3>Yummigr Tools.</h3>
<p>
  
The yummi has some interesting features for sending emails and sms. The user can create a connector to manage the automatic sending of messages either by sms or email. The sending of messages can contain customized messages or already loaded in the creation of a specific contact.

<br>
<h5>Create MessengerConnector </h5>

Messenger Connector is an entity responsible for creating contacts. This entity supports some values, such as account_sid, auth_token, and user identifier. Understand how account_sid and auth_token generated high hash fields for api. these fields are not the user's responsibility to fill in, and yes the system creates those values. The user identifier is part of the same premise as field 1) where the identifier is unique for each user in the system.

<h5>Creating a messenger connector bound to a user.</h5>
      
   Creating: /yummicr/api/v1/toolkit/messenger/c/
   
   #### identifier(String)
   #### account_sid(String)GeneratedValue
   #### auth_token(String)GeneratedValue.
       
   This url provides the creation of a connector for a user. It supports the user identifier and two self-generated      fields for api (account_sid, auth_token). verb is POST.
</p>
<br>
<h5>Create schedule connector.</h5>

  <p>
  Schedule Connector is an entity responsible for the time management of sending emails and sms, this entity is directly related to the messenger connector, in this way the user can customize the time of sending messages. verb is POST.
   
  The user can change the time when sending automatic messages.To update the shipping time, you can use this same url.
  
  Creating: /yummicr/api/v1/toolkit/messenger/schedule/c/
  
   #### identifier(String)
   
   #### time(String) Default is milliseconds.

</p>
<br>
<h5>Creating contacts for the messenger connector.</h5>

  <p>
  
As previously stated, the messenger connector is responsible for activating the sms and email sending capabilities. In this way a connector entity may have one or more contacts to send messages. this verb http is POST.
  
  Creating: /yummicr/api/v1/toolkit/messenger/contact/c/ (POST)
  
  #### identifier(String)
  #### message(String)
  #### phone_number(String)
  #### subject_message(String)
  #### email(String)
  
  Updating: /yummicr/api/v1/toolkit/messenger/contact/u/ (PUT)
  
   #### id_contact(String)
   
   Get ID Contact: /yummicr/api/v1/toolkit/messenger/contact/u/ (GET)
    
   #### email(String)
   #### identifier(String)
   #### phone_number(String)
   
   Delete Contact: /yummicr/api/v1/toolkit/messenger/contact/d/ (DELETE)
   
   #### email(String)
   #### identifier(String)
   #### phone_number(String)
      
  </p>
 <br>
 
 <h4>Sending Messages.</h4>
 
<p>
  
This url provides the sending of automatic messages so that the message and the title of the contact created specified, is sent without the need of a customization of sending of the user.

Broadcast pre-configured messages: /yummicr/api/v1/toolkit/messenger/s/email/all/p/ (POST).
  
   #### identifier(String)
   #### email(String) (sender)
   #### password(String) (sender)
   #### activate(String)(Default true)

  
 Broadcast messages: /yummicr/api/v1/toolkit/messenger/s/email/all/p/ (POST).
 
 BroadCast for sending emails, with customized message (message and subject).
 
 
   #### identifier(String)
   #### email(String) (sender)
   #### password(String) (sender)
   #### activate(String)(Default true)
   #### message(String)
   #### subject_message(STRING)
   
 Send pre-configured messages to selected contacts
 
 Sender Selected Contacts: /yummicr/api/v1/toolkit/messenger/s/email/select/p/ (POST)
 
   #### identifier(String)
   #### email(String) (sender)
   #### password(String) (sender)
   #### activate(String)(Default true)
   #### emails_contacts_select(String)
   
 Sender Selected Contacts for sending emails, with customized message (message and subject).
 
 Sender Selected Contacts for sending emails : /yummicr/api/v1/toolkit/messenger/s/email/select/
 
  #### identifier(String)
  #### email(String) (sender)
  #### password(String) (sender)
  #### activate(String)(Default true)
  #### message(String)
  #### subject_message(STRING)
 
  
  Stop sending custom or pre-configured messages.
  
  /yummicr/api/v1/toolkit/messenger/s/email/all/cancel/ (GET)
 
  /yummicr/api/v1/toolkit/messenger/s/sms/all/cancel/ (GET)
 
  #### identifier(String)
</p>
<br>

<h4>Sending SMS</h4>
<p>
  /yummicr/api/v1/toolkit/messenger/messenger/s/sms/all/ (POST)
  
  #### identifier(String)
  #### email(String) (sender) (Login facilitaapi).
  #### password(String) (sender) (password facilitaapi).
  #### activate(String)(Default true)
  #### message(String)
  #### subject_message(STRING)
 
 /yummicr/api/v1/toolkit/messenger/messenger/s/sms/all/p/ (POST)
 
 #### identifier(String)
 #### email(String) (sender) (Login facilitaapi).
 #### password(String) (sender) (password facilitaapi).
 #### activate(String)(Default true)
</p>
<br>
<h3>Specialized graphics.</h3>

  Regarding the creation of graphics. The yummi contains a package of information visualization related to sending emails and sms. The search filter is divided into three parts, search for the month of submission, the year of submission and the complete record (month, year, day).
  
  
The endpoints support some configurations of the type of graph to be generated. bar graph, line graph. etc; the option parameter defines this configuration and 3 configurations are currently supported. (1) - bar graph, (2), line graph (3) 3D graph;

  
  localhost:8085/yummicr/api/v1/toolkit/graphics/view/emails/year/ (POST).
  
   #### identifier(String) (identifier-user)
   #### option(Integer) (1)
   #### year(String) Example(2019)
   
   
   localhost:8085/yummicr/api/v1/toolkit/graphics/view/emails/full/ (POST).
  
   #### identifier(String) (identifier-user)
   #### option(Integer) (1)
   #### year(String) Example(2019)
   #### month(String) Example(06)
   #### day(String) Example(19)
   
   localhost:8085/yummicr/api/v1/toolkit/graphics/view/emails/month/ (POST).
   
   #### identifier(String) (identifier-user)
   #### option(Integer) (1)
   #### month(String) Example(06)
   
   
   ![Screenshot](admin_emails_year_.png)
   
   
   <br>
   
   <p>The api supports the storage of generated graphics according to each search method. the created .png files are stored in a directories tree of each yummi user. api also supports the serialization of the images and returns the response of the same serialized images so that you create your deserialization and visualization mechanism.</p>
   
 <br>
<h3>JSON images generated.</h3>

<br>

<h3>Endpoints for communing with umbrella api</h3>
