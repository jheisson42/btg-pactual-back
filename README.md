# btg-pactual-back

Este proyecto es una API REST desarrollada en **Spring Boot** que permite gestionar la inscripción de usuarios en fondos, así como la consulta de fondos disponibles.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu máquina:

- [Java 11 o superior](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Base de datos (ej. MySQL, PostgreSQL)](https://www.mysql.com/downloads/) (si usas una base de datos diferente, actualiza la configuración en `application.properties`)

## Clonar el Repositorio

Clona este repositorio en tu máquina local usando el siguiente comando:

##bash
git clone https://github.com/tu_usuario/tu_repositorio.git
cd tu_repositorio

## Compilar
mvn clean package

## Ejecutar proyecto
java -jar target/btg-0.0.1-SNAPSHOT.jar