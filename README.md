# hibernate_3

Поднять MySql в докере 
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8 
Если установлен локальный MySql сервер его нужно остановить либо сменить порты в команде



развернуть дамп из ресурсов проекта
dump-hibernate-final.sql

Поднять redis в докере 
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest 

установить redis-insight, посмотреть на данные, которые хранятся в Redis:


Если же просмотр данных в redis не интересует, тогда достаточно:

docker run -d --name redis -p 6379:6379 redis:latest 



