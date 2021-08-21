# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk8:alpine-jre

# Add maintainer
LABEL maintainer="Product Management Team at Intergamma (tlaxman88@gmail.com)"

# Create exploded jar in the image
COPY target/dependency/BOOT-INF/classes /app
COPY target/dependency/BOOT-INF/lib /app/lib
COPY target/dependency/META-INF /app/META-INF
WORKDIR /app

# Run the application on startup
ENTRYPOINT ["java","-cp",".:lib/*","nl.intergamma.GammaProductManagerApplication"]

# Only for documentation purposes
EXPOSE 8080
