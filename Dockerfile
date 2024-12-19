FROM openjdk:11
WORKDIR /sgc
EXPOSE 8055
ADD ./target/capacitacao-0.0.1-SNAPSHOT.jar capacitacao.jar
ENTRYPOINT [ "java", "-jar", "/sgc/capacitacao.jar" ]