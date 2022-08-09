# Redis 또는 RDB 를 이용한 로그인 세션

* 웹서버가 여러대 동작중이기 때문에 서버의 세션을 공유하지 못하는 상황을 가정한다.
* 세션은 Redis 또는 RDB(MySQL) 를 이용하여 구현하였다.
* IntelliJ 를 이용하여 `TODO` 를 따라가며 학습하자. 

## Redis 를 이용한 로그인 세션

```bash
mvn spring-boot:run -Dspring-boot.run.profile=redis
```

```bash
redis-cli -h 133.186.211.156 -p 6379 -n 33 -a ${password}
```

## RDB (MySQL) 을 이용한 로그인 세션
```bash
mvn spring-boot:run -Dspring-boot.run.profile=redis
```
```bash
mysql -h 133.186.211.156 -u nhn_academy_30 -p
```
