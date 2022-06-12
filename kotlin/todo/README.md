MYSQL DOCKER
```
docker run --platform linux/amd64 -p 3306:3306 --name todo -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=todo -e MYSQL_USER=kzv -e MYSQL_PASSWORD=1234 -d mysql
```

```
docker ps
```