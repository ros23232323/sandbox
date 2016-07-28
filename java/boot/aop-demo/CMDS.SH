spring boot mvn notes
-----------------------------------------
JAVA=$JAVA_HOME/bin/java
#create pom
mvn package
mvn dependency:tree # prints a tree representation of your project dependencies. 
mvn spring-boot:run
curl http://localhost:8080
curl http://localhost:8080/exception
#added in build config plugin to build an executable jar
mvn package
jar tvf target/myproject-0.0.1-SNAPSHOT.jar #view contents of executable jar
java -jar target/myproject-0.0.1-SNAPSHOT.jar #run app 

java -javaagent:/home/itowey/.m2/repository/org/springframework/springloaded/1.2.6.RELEASE/springloaded-1.2.6.RELEASE.jar -noverify -jar target/myproject-0.0.1-SNAPSHOT.jar #run app 

mvn spring-boot:run
