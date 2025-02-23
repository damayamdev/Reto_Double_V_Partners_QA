# Reto Double V Partners - QA Automation

## Descripción del Proyecto

Este proyecto de automatización de pruebas se divide en dos partes:

1: Pruebas de API utilizando Serenity Screenplay para validar la funcionalidad de la API de "Your Store".

2: Pruebas Web con Selenium y el patrón Page Object Model (POM) para automatizar los flujos críticos del sitio web "OpenCart".

El objetivo es asegurar la calidad y funcionalidad de la tienda en línea mediante pruebas automatizadas eficientes y repetibles.

## Tecnologías Utilizadas

### API Testing

- Serenity Screenplay
- Java
- Gradle
- RestAssured
- JUnit

### Web Testing

- Selenium WebDriver
- Java
- Gradle
- Page Object Model (POM)
- TestNG

## Instalación y Configuración

-  Clonar el Repositorio

```bash
  https://github.com/damayamdev/Reto_Double_V_Partners_QA/tree/main
```

## Requisitos Previos
Asegúrate de tener instalados los siguientes requisitos:

- Java 11+
- Gradle
- ChromeDriver o GeckoDriver (según el navegador a utilizar)
- Postman (para pruebas manuales de API, opcional)

## Configuración del Proyecto API

Dirígete a la carpeta Prueba_API y ejecuta:

```bash
 gradle clean test
```
Esto ejecutará los casos de prueba definidos en Serenity Screenplay.

##  Configuración del Proyecto Web

Dirígete a la carpeta prueba_web y ejecuta:


```bash
 gradle clean test
```
Esto ejecutará las pruebas automatizadas en Selenium siguiendo el patrón POM.

## Escenarios de Prueba Implementados

### API Testing

- Consultar todos los productos de la categoría Electronics.
- Consultar un producto específico.
- Crear un producto.
- Actualizar la imagen de un producto.
- Eliminar productos con valor menor a $100.

### Web Testing

- Registro de usuario.
- Inicio de sesión.
- Restablecimiento de contraseña.
- Navegación y selección de productos.
- Agregar y eliminar productos del carrito.
- Completar una compra.