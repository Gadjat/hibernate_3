# hibernate_3

Поднять MySql в докере 
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8 
Если установлен локальный MySql сервер его нужно остановить либо сменить порты в команде

развернуть дамп из ресурсов проекта
dump-hibernate-final.sql


