# Use a compatible OpenJDK image for multi-architecture support
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Expose the port the application runs on
EXPOSE 8090

# Add the application WAR file to the container
ADD target/MyTravelBuddyHotels-0.0.1-SNAPSHOT.war MyTravelBuddyHotels-0.0.1-SNAPSHOT.war

# Command to run the application
ENTRYPOINT ["java", "-jar", "MyTravelBuddyHotels-0.0.1-SNAPSHOT.war"]
