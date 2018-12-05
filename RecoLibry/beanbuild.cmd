: #!/bin/sh

: # This script is automatically generated! DO NOT MODIFY! 

: # (c) 2015 JIOWA Business Solutions GmbH, Bettinastr. 30, Frankfurt am Main, Germany

: # This script is used to check the syntax of all Jiowa generator templates (*.jgt)
: # and deliver the results to the console in Eclipse (because the maven build result is usually hidden)

: # It is integrated in the builders menu of eclipse and runs 
: # directly after the maven build (which generates the template beans)
: # Whenever the version number of the jiowa-codegen dependency 
: # in pom.xml is changed this script is updated automatically 
: # in the 'initialize' phase via the 
: # exec task which calls BeanbuildCmdScriptUpdater in pom.xml

: # This script works 
: # on Linux/MacOS/Unix and Windows 

:; java -cp ~//.m2/repository/com/jiowa/jiowa-codegen/2.1.6/jiowa-codegen-2.1.6.jar com.jiowa.codegen.beangen.JiowaCodeGenTemplateBeanGenerator codegen.properties check-template-syntax-only  ; exit;

@ECHO OFF
java -cp "%USERPROFILE%\\.m2\repository\com\jiowa\jiowa-codegen\2.1.6\jiowa-codegen-2.1.6.jar" com.jiowa.codegen.beangen.JiowaCodeGenTemplateBeanGenerator codegen.properties check-template-syntax-only

: # the library path setting for windows will use windows environment variables if possible 
: # if you do not see an environment variable like %USERPROFILE% or %HOMEPATH% then your 
: # maven repository seems to be at a non-standard location.



 