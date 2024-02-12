FROM alpine

ADD /world.sql /root
# ADD /path/to/jar /root

RUN apk add mysql mysql-client &&
rc-service mariadb restart &&
rc-update add mariadb default &&
mysql -a /root/world.sql 
#&& java -jar target/path/to/jar