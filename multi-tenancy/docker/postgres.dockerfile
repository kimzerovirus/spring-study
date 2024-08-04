FROM postgres:15.1-alpine

#COPY ./.env ./
#RUN export $(cat ./.env | xargs)

COPY ./sql/postgres-init.sql /docker-entrypoint-initdb.d/