FROM alpine

ADD /world.sql /root
# ADD /path/to/jar /root

RUN apk add mysql mysql-client
RUN rc-service mariadb restart
RUN rc-update add mariadb default 
RUN mysql -a /root/world.sql 
#&& java -jar target/path/to/jar