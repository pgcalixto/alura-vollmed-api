# alura-vollmed-api
Projeto Alura de uma API que contém dados de médicos e pacientes

## Docker

```sh
docker run \
		-d \
		--name mysql-8 \
		--restart=always \
		-p 3306:3306 \
		-e MYSQL_ROOT_PASSWORD=root \
		-e MYSQL_DATABASE=vollmed_api \
		-e MYSQL_USER=vollmed_api \
		-e MYSQL_PASSWORD=vollmed_api \
		mysql:8.4.4

#		-v oracle-xe-18c-volume:/opt/oracle/oradata
```
