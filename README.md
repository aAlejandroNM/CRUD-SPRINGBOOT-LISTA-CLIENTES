# CRUD Clientes - Aplicación Web

## Descripción

La aplicación **CRUD Clientes** es un sistema de gestión de clientes desarrollado con **Spring Boot**. Proporciona funcionalidades completas para la gestión de clientes, como la creación, edición, visualización y eliminación de registros. La aplicación está diseñada siguiendo el patrón de arquitectura **MVC (Model-View-Controller)**, lo que asegura una separación clara entre la lógica de negocio, la presentación y el acceso a datos.

Además, la aplicación cuenta con un sistema de autenticación y autorización robusto utilizando **Spring Security**, permitiendo el acceso restringido basado en roles (`USER`, `ADMIN`) para ciertas funcionalidades. También incluye opciones para exportar datos a **PDF** y **Excel**, lo que facilita la generación de reportes.

## Funcionalidades

- **Gestión de Clientes:**
  - Crear nuevos clientes.
  - Editar información existente de clientes.
  - Eliminar clientes.
  - Listar y visualizar detalles de los clientes.
  - Exportar listado de clientes a PDF y Excel.

- **Seguridad:**
  - Sistema de login y logout personalizado.
  - Autenticación basada en roles (`USER`, `ADMIN`).
  - Restricción de acceso a diferentes vistas según el rol.

- **Exportación de Datos:**
  - Exportación del listado de clientes en formato **PDF**.
  - Exportación del listado de clientes en formato **Excel**.

## Tecnologías Utilizadas

- **Backend:**
  - Java 17
  - Spring Boot
  - Spring Security
  - Spring Data JPA (Hibernate)
  - MySQL

- **Frontend:**
  - Thymeleaf
  - Bootstrap 5

- **Exportación de Documentos:**
  - Apache POI (para Excel)
  - OpenPdf (para PDF)

## Configuración

### Prerrequisitos

- JDK 17 o superior
- Maven 3.8+
- MySQL 8.0+
- Configurar la base de datos `spring_db` en MySQL.

### Configuración del Proyecto

1. Clona el repositorio:

    ```bash
    git clone https://github.com/aAlejandroNM/CRUD-SPRINGBOOT-LISTA-CLIENTES.git
    cd CRUD-SPRINGBOOT-LISTA-CLIENTES
    ```

2. Configura la base de datos en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost/spring_db?serverTimezone=America/Bogota&useSSL=false
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

3. Construye el proyecto con Maven:

    ```bash
    mvn clean install
    ```

4. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

5. Accede a la aplicación en tu navegador en `http://localhost:8080`.

## Uso

- Inicia sesión con un usuario administrador para acceder a todas las funcionalidades.
- Navega a `/views/clientes/` para gestionar clientes.
- Utiliza los botones de exportación en la página de lista de clientes para descargar los datos en PDF o Excel.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

