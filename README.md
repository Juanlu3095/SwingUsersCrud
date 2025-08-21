
# SwingUsersCrud

Una aplicación de escritorio hecha con Java Swing y MySQL, la cual permite realizar operaciones CRUD con usuarios.

## 🚀 Instalación de dependencias

Este proyecto utiliza Apache Ant para automatización de la descarga de librerías mediante targets que se indican en build.xml. Para descargarlas e incluirlas en la carpeta lib:

```bash
ant install
```

## 📂 Arquitectura

La aplicación utiliza una arquitectura MVC (Modelo-Vista-Controlador) con la que dividir el código en carpetas dependiendo de su función:

### Controlador

El controlador es el 'director de orquesta', es decir, es el intermediario entre el resto de la aplicación que se encarga de llamarlos cuando sea necesario.

### Modelo

El modelo es el que se encarga de la 'lógica de negocio', aquel que se encarga de las consultas a la base de datos.

### Vistas

Las vistas son las interfaces gráficas de la aplicación, las cuales usan Swing.

### Entidades

Las entidades representan los datos de la aplicación, como es el caso de los usuarios.

### Interfaces

Las interfaces nos permite abstraer las clases del de otros elementos de la aplicación definiendo un contrato y siguiendo las buenas prácticas.

### Base de datos (Carpeta Database)

La carpeta database incluye principalmente la configuración para la conexión con la base de datos, la cual usará el modelo para realizar las consultas. También contiene la migración de la base de datos, los seeders y las validaciones que requiere la base de datos.
Para crear la base de datos:

```bash
ant migrate
```

Para crear valores en la base de datos:

```bash
ant seed
```

### Utilidades

Esta carpeta contiene diferentes funciones para la aplicación. Aquí se encuentran las validaciones que no requieren la base de datos, por ejemplo, comprobar que un DNI es válido.

## 💉 Testing

La aplicación usa JUnit4 para la ejecución de tests, ya que JUnit5 tiene problemas de compatibilidad para Apache Ant.