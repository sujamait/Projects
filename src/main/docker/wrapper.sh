#!/bin/bash
#To wait till MySql is connected

while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
    echo "Trying To Connect To MySQL at ${DATABASE_PORT}"
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar