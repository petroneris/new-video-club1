# new-video-club1
Demonstrator project in Eclipse IDE with Spring MVC, Spring Security and Spring WebSocket

<img src="src/main/webapp/static/images/springBloss4ab.png" width="400">
<br/>

### THE SPRING MVC APPLICATION “NewVideoClubProject”
<br/>
The starting point for the application “NewVideoClubProject” was previously designed project “VideoKlubProjekat” and the bunch of technologies for further application improvements. With new technologies for application improvements we can consider this project to be a “different story” in comparison with the previous one.

“NewVideoClubProject” is the web application which demonstrates a usage of the following technologies:
- Spring MVC framework – business layer
- Spring Security framework – for logging and access activities
- Spring WebSocket and messaging – for async message-driven multi-user communication
- Hibernate (ORM)
- MySQL – RDBMS data layer
- JSP + JSTL + various TAG libraries: presentation (View) layer
- Bootstrap + JQuery: front-end framework (css + js)
- jQuery-validate Plugin - for input data validation
- AJAX in front-end via jQuery standard library AJAX support
- JSON data handling with Jackson library
- Back-end logging with Slf4j-with-Logback library

A standard Java servlet container (Tomcat) is used as a server platform.

Application is set-up to use Maven as its build system.

The code is Java8 – compliant.

A short description of coding technologies used in application design, application rules, configuration and design details are presented in [documentation:](doc/NewVideoClubProject.pdf)

Prior to starting the application you need to create and populate MySQL database as described in the document and [this sql file](sql/video_club.sql). After that please update the database access credentials with your own values in [application.properties](src/main/resources/application.properties) file.

Application is built in the standard Maven way:<br/> 
`mvn clean install`

The build artefact is a single **NewVideoClubProject.war** file, which you can deploy in a standard way on your Java server of choice: Tomcat, Glassfish, Wildfly... In project's pom file, deploy plugin is defined and it will copy the freshly built war file to your (local) destination of choice:<br/>
```
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-war-plugin</artifactId>
   <version>2.4</version>
   <configuration>
      <warSourceDirectory>src/main/webapp</warSourceDirectory>
      <warName>NewVideoClubProject</warName>
      <failOnMissingWebXml>false</failOnMissingWebXml>
      <outputDirectory>${tomcat.deploy.dir}</outputDirectory>
   </configuration>
</plugin>
```
Here, you determine the output directory for your war file through the **`tomcat.deploy.dir`** maven property.

Once the war has been deployed you can reach the application like so, if you're running your server locally and serve html at port 8080 (you've guessed it right - it's Tomcat):<br/>
`http://localhost:8080/NewVideoClubProject/`

There are no unit tests for the moment. Feel free to add your own :smiley:.

You can see the application demo video [here](video/new-video-project.mp4). You can download it as a separate file in a straightforward manner; however if you wish to clone it as part of the entire repository you will have to install [Git LFS Client](https://www.atlassian.com/git/tutorials/git-lfs) first.
