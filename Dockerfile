FROM openjdk:17
EXPOSE 8090
ADD target/MyTravelBuddyHotels-0.0.1-SNAPSHOT.war MyTravelBuddyHotels-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java", "-jar", "/MyTravelBuddyHotels-0.0.1-SNAPSHOT.war" ]