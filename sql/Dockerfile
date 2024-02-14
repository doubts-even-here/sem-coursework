FROM alpine
LABEL maintainer="sem-group2"

ADD sql/world.sql /root
# ADD /home/andrewh/Documents/group2-coursework/target/group2-coursework-1.0-SNAPSHOT.jar /root

RUN apk add mysql mysql-client

CMD rc-service mariadb restart
CMD rc-update add mariadb default 
CMD mysql -a /root/world.sql 
# CMD java -jar /root/group2-coursework-1.0-SNAPSHOT.jar