# Proyecto Taller Mecánico

Este repositorio sirve como el backend que da soporte al proyecto de **taller mecánico** propuesto para la cátedra de **Programación Avanzada 2023**.

## Integrantes
- PALACIOS, Lucas
- PEDRAZA, Santiago
- MARTINI, Leopoldo
- MAZA BIANCHI, Lucas
- MAIRONE, Nicolás Nahuel
- SERNIOTTI, Guido Andrés

## Configuración
1. Establezca la conexión con el JDBC de MySQL en `application.properties`. Por defecto, apunta hacia `localhost` o `127.0.0.1` y la base de datos se llama `taller`.

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/taller
spring.datasource.username=root
spring.datasource.password=test123

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

2. Ejecute el proyecto con el script `spring-boot run`.
3. La aplicación será accesible desde el puerto `8080`.

## Docker
Si no se precisa de MySQL instalado previamente, se puede correr un contenedor usando Docker. Puede tomar de referencia este comando de shell para levantar su propio contenedor.
```bash
$ docker run --name mysql -e MYSQL_ROOT_PASSWORD=test123 -d -p 3306:3306 mysql:latest
```

Luego se entra a la instancia y se crea la base de datos de la siguiente forma:
```bash
# Entrar con shell al contenedor de mysql y correr el CLI
# Va a pedir contraseña, usar "test123" si se corrió el comando de arriba como estaba
$ docker exec -it mysql mysql -u root -p
# Crear base de datos, cambiar el nombre a lo que se necesite
$ mysql> CREATE DATABASE taller;
```