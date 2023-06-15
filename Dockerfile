FROM openjdk:11
EXPOSE 8082
ADD "target/moviebookingapp.jar" "moviebookingapp.jar"
ENTRYPOINT [ "java", "-jar", "/moviebookingapp.jar" ]