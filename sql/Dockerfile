LABEL maintainer="sem-group2"
FROM alpine

ADD /world.sql /root
# ADD /path/to/jar /root

RUN apk add mysql mysql-client

CMD rc-service mariadb restart
CMD rc-update add mariadb default 
CMD mysql -a /root/world.sql 
# CMD java -jar target/path/to/jar