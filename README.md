# SpringBootPythonTest

This is a spring boot project without an embedded tomcat.
So, to run the project, this needs to deployed over a external tomcat.

E.g. :
- Can be deployed to standalone tomcat installed in os
- Can be copied to Tomcat based docker image and run the image for bringing up the application

Resources :
api.py - A python script to expose a api using Flask
pyStart.py - python script to log data to log file and launch perm.sh
perm.sh - shell script to set the port of tomcat to 8888, assign executable permissions to pyStart.py script, run api.py using nohup to run it in background
nohup.out - Automatically generated, whenever nohup is invoked
helloApi-0.0.1-SNAPSHOT.jar - An simple spring boot application which exposes an helloWorld api

Imp points :
The Main Application extends SpringBootServletInitializer class and overrides configure method, so that this application can be deployed over external tomcat as war
Init Method in main class is to launch scripts inside docker image

