Readme
==================

How to run tomcat using the webapps folder

1. For the tomcat server defined in eclipse, remove all projects and then click publish. This will clean out the server instance.

2. Double click on the server, this will bring up a overview window.

3. In the server locations options, select 'User Tomcat Installation'. Make sure the server path is pointint to the Tomcat local installation.

4. For deploy path set as 'webapps'

5. Save server changes by closing the window and accepting the dialog.

6. Readd the projects and start server.

This should now deploy the war files to the Tomcat installation and be able to use the webapps/ROOT folder for resources.