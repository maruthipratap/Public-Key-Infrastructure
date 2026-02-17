@echo off

echo Compiling project...
javac -cp "C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar" -d out src\*.java

echo Removing old classes...
del /Q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\pki\WEB-INF\classes\*"

echo Copying new classes...
xcopy out "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\pki\WEB-INF\classes\" /E /I /Y

echo Clearing Tomcat cache...
rmdir /S /Q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\work\Catalina\localhost\pki"

echo Restarting Tomcat...
"C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\shutdown.bat"
timeout /t 5
"C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat"

echo Deployment Complete!
pause
