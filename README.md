# easylearn
### Introduction
### Development notes
You can use the IDE of your choice. Whenever submitting changes to the repository make sure that you don't include files that are specific to your operating system or IDE (e.g., files generated by Eclipse, files generated by IntelliJ, etc).

### Project structure

### Development workflow
0. The first time you should clone the project locally. This step should be skipped in future iterations.
1. Pull the latest version from github locally before adding any other code. This should be done each time.
2. Add the code to the project.
3. Build the project to make sure that your changes are successfully integrated (see the next section). If any errors are displayed you should resolve them.
4. If the build is successfull, then run the project (see the next section).
5. Test your changes manually (e.g., verify that the changes work as expected on the endpoint, UI, etc).
6. If your changes work as expected make a new commit that includes the files.
7. Pull locally any changes that might be submitted to the repository in the meantime and merge them manually if necessary (most of the times git will handle them automatically).
8. Push the changes to github.

### Building the project
In order to **build** the project, position yourself in the root directory of the project and use the following command depending on your operating system:
* Windows: **gradlew.bat build**
* Linux: **./gradlew build**

### Running the project
In order to **run** the project, position yourself in the root directory of the project and use the following command depending on your operating system:
* Windows: **gradlew.bat bootRun**
* Linux: **./gradlew bootRun**

The application will start at the port specified in the **application.properties** file, which is located in the resources folder. The current application port is set to 8100. After starting the project access it: http://localhost:8100/


### Documentation
* Angular and Spring example: https://spring.io/guides/gs/consuming-rest-angularjs/
* Gradle tutorial: http://www.vogella.com/tutorials/Gradle/article.html
* Gradle: https://gradle.org/docs/
* Spring: http://www.vogella.com/tutorials/web.html (the Spring section)
* Java: http://www.vogella.com/tutorials/java.html
* Tools: http://www.vogella.com/tutorials/technology.html
* General tutorials: http://www.vogella.com/tutorials/
