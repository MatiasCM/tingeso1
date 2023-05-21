# Técnicas de Ingeniería de Software Evaluación 1 1-2023

- Autor: Matías Cortés Manríquez
- Sección: B-2
- Profesor: Alcides Quispe

## Descripción

Este repositorio cuenta con la aplicación monolítica a ser presentada para la primera evaluación de Técnicas de Ingeníeria de Software del primer semestre del 2023.

## Herramientas y tecnologías utilizadas

* [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/#section=windows)
* [SpringBoot](https://start.spring.io/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [SonarQube Community Edition](https://www.sonarsource.com/products/sonarqube/)
* [Jenkins](https://www.jenkins.io/)
* [Nginx](https://www.nginx.com/)
* [Oracle VM VirtualBox](https://www.virtualbox.org/)
* [Terraform](https://www.terraform.io/)
* [MySQL](https://www.mysql.com/)
* [DigitalOcean](https://www.digitalocean.com/)

## Comandos importantes

### Docker

La cuenta de DockerHub utilizada es matiascm y el repositorio se llama tingeso1

* Ver contenedores:

```
docker ps
```

* Ver Imágenes:

```
docker image ls
```

* Docker Build (Crear Imágen)

```
docker build -t USUARIO/REPOSITORIO .
```

* Eliminar Imágen

```
docker rmi <nombre imagen>
```

* Eliminar Contenedor

```
docker rm -f <nombre contenedor>
```

* Subir Imágen a DockerHub

```
docker push USUARIO/REPOSITORIO
```

* Levantar Contenedor

```
docker-compose up
```

* Bajar Contenedor

```
docker-compose down
```

### SonarQube

* Iniciar SonarQube se ejecuta el siguiente comando en la carpeta \sonarqube-9.9.0.65466\bin\windows-x86-64

```
StartSonar.bat
```

### Jenkins

En la carpeta donde se encuentra el archivo "jenkins.war"
* Levantar Jenkins (localhost:8080/):

```
java -jar jenkins.war
```

### Terraform

* Levantar Máquina Virtual de Digital Ocean:

```
ssh root@165.227.108.8
```