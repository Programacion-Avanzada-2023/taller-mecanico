# Proyecto Taller Mecánico

Este repositorio sirve como el backend que da soporte al proyecto de **taller mecánico** propuesto para la cátedra de **Programación Avanzada 2023**.

## Integrantes
- IBARRA, Alvaro Fidel
- PALACIOS, Lucas
- PEDRAZA, Santiago
- MARTINI, Leopoldo
- MAZA BIANCHI, Lucas
- MAIRONE, Nicolás Nahuel
- SERNIOTTI, Guido Andrés

## Documentación
La documentación pertinente del software se encuentra disponible en [la wiki del repositorio](https://github.com/Programacion-Avanzada-2023/taller-mecanico/wiki).

Para visualizar la referencia de la API RESTful y sus recursos, dejamos [disponible en Postman la misma](https://documenter.getpostman.com/view/21463857/2s9YJgUM76).

## Configuración
1. Establezca la conexión con el JDBC de MySQL en `application.properties`. Por defecto, apunta hacia `localhost` o `127.0.0.1` y la base de datos se llama `taller`.

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/taller?useUnicode=true&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=test123

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.error.include-message: always
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

(Opcional) Para obtener datos de prueba en una primer instancia, asegurarse de importar el script de MySQL (dump) [que puede encontrarse aquí](https://gist.github.com/punteroo/6185924734a9cce5b1f8688ddc2ce78b).
```bash
# Ejemplo con el archivo de dump adentro del sistema de archivos del contenedor de MySQL.
$ docker exec mysql mysql -u root -p --database=taller < dump.2023-12-17.sql
```
