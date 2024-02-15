FROM openjdk:17
EXPOSE 8081
COPY target/core-module.jar core-module.jar
# Add wait-for-it script
ADD https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
CMD ["/wait-for-it.sh", "kafka:9092", "--", "java", "-jar", "/core-module.jar"]
