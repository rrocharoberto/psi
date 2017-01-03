# psi - Projeto para controle de estágios de psicologia
  
#Tools of development environment:  
  
JDK 8  
Postgresql 9.x  
Maven 3.3.x  
Tomcat 8.x  
Gitbash  
Eclipse JEE  
  
#Tools of test environment:  
  
JDK 8  
Postgresql 9.x  
Maven 3.3.x  
Tomcat 8.x  
Gitbash  

#How to configure the project environment  
  
Create the database and user (aluno) according to /geps/src/main/resources/configureDatabase.txt  
Execute the script: /geps/src/main/resources/script.sql  
Execute the script: /geps/script.sql  
  
#How to execute the project  
  
Execute the following commands in prompt:  
  
git clone < git URL of this project >  
copy /geps/docs/settings.xml to $MAVEN_HOME/conf  
copy /geps/docs/tomcat-users.xml to $TOMCAT_HOME/conf  
start tomcat ($TOMCAT_HOME/bin/startup.sh)  
go to geps directory  
mvn clean install  
mvn tomcat7:deploy  
  
#Access the project in a browser:  
  
http://localhost:8080/geps  
