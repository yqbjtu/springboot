FROM xxxx
ADD /target/helloworld.jar helloworld.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /helloworld.jar" ]