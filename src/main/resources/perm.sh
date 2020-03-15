#!/bin/sh

echo "perm start"
java -Dserver.port=8888 -jar /Users/sky/Desktop/tomcat/apache-tomcat-8.5.49/webapps/test1-0.0.1-SNAPSHOT/WEB-INF/classes/helloApi-0.0.1-SNAPSHOT.jar
chmod 777 /Users/sky/Desktop/tomcat/apache-tomcat-8.5.49/webapps/test1-0.0.1-SNAPSHOT/WEB-INF/classes/pyStart.py
nohup python3 /Users/sky/Desktop/tomcat/apache-tomcat-8.5.49/webapps/test1-0.0.1-SNAPSHOT/WEB-INF/classes/api.py
echo "perm end"