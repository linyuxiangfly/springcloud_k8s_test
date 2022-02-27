FROM java:8-jdk-alpine
LABEL maintainer fly
RUN  apk add -U tzdata && \
     ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
COPY ./svr-reg-0.0.1-SNAPSHOT.jar ./
EXPOSE 80
CMD java -jar -D--spring.profiles.active=k8s /svr-reg-0.0.1-SNAPSHOT.jar
