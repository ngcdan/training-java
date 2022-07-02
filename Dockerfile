FROM openjdk:slim-buster
WORKDIR /app
COPY /app/server/config ./server/config
COPY /app/server/libs ./server/libs
COPY /app/server/server.sh ./server
ENTRYPOINT ["server.sh", "start"]