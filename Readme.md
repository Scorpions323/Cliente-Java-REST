# Práctica: Cliente REST en Java

## 1. Arquitectura y Diseño del Software
El sistema sigue los principios de diseño modular y separación de responsabilidades, organizándose bajo el paquete `es.etg.dawes.java.rest.client`:

* **`Cliente.java`**: Núcleo de comunicación de red. Implementa la API nativa `HttpClient` utilizando el patrón *Builder* para configurar cabeceras, tiempos de espera (*timeouts*) y procesar las respuestas del servidor de manera síncrona.
* **`Gui.java`**: Capa de presentación gráfica desarrollada con Java Swing. Integra la librería **FlatLaf** para ofrecer un diseño visual moderno, coherente y adaptado a entornos de escritorio profesionales.

## 2. Ampliaciones y Características Avanzadas
Se han implementado mejoras significativas tanto a nivel de backend como de interfaz:

* **Soporte Multi-Método HTTP robusto:**
  * `GET`: Recuperación y consulta de recursos remotos.
  * `POST`: Creación de nuevos registros mediante el envío de cargas útiles (*Payloads*) en formato JSON.
  * `PUT`: Actualización completa de elementos existentes.
  * `DELETE`: Eliminación lógica de recursos.
  * *Manejo de Errores:* Uso de una estructura `switch` con control explícito (`IllegalArgumentException`) para interceptar verbos HTTP no soportados.

* **Experiencia de Usuario Interactiva:**
  * **Selector de Verbos (`JComboBox`):** Permite conmutar dinámicamente entre las distintas operaciones HTTP.
  * **Endpoint Dinámico (`JTextField`):** Facilita la modificación en tiempo de ejecución de la URL de destino.
  * **Editor de JSON (`JTextArea`):** Espacio dedicado para la redacción de cuerpos estructurados en peticiones `POST` y `PUT`.
  * **Control de Limpieza:** Botón de restablecimiento rápido para vaciar la consola de salida y optimizar el flujo de pruebas.

## 3. Validación y Pruebas Realizadas
La aplicación ha sido probada contra los servicios simulados de `JSONPlaceholder`:

* **Petición GET:** Consulta de `https://jsonplaceholder.typicode.com/posts/1` -> Respuesta `200 OK` con datos estructurados.
* **Petición POST:** Inserción en `https://jsonplaceholder.typicode.com/posts` -> Respuesta `201 Created`.
* **Petición PUT:** Actualización de `https://jsonplaceholder.typicode.com/posts/1` -> Respuesta `200 OK`.
* **Petición DELETE:** Baja en `https://jsonplaceholder.typicode.com/posts/1` -> Respuesta `200 OK`.

## 4. Guía de Puesta en Marcha
1. Importar el directorio como un **Proyecto Maven** en el IDE (IntelliJ IDEA, Eclipse, NetBeans).
2. Sincronizar las dependencias del fichero `pom.xml` (librería FlatLaf).
3. Localizar la clase ejecutable `Gui.java` en `es.etg.dawes.java.rest.client`.
4. Ejecutar la aplicación mediante la opción **Run 'Gui.main()'**.