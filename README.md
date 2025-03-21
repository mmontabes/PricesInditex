Este proyecto es un microservicio desarrollado en Java (Spring Boot) que gestiona precios de productos para diferentes marcas y provee un punto de acceso REST para consultas. Implementa una arquitectura hexagonal y utiliza una base de datos en memoria H2 para pruebas y almacenamiento temporal de datos.

## Descripción
El servicio permite consultar los precios de productos según un rango de fechas, marca y producto específicos. Los precios incluyen información como la prioridad, el rango de validez y la moneda en la que se expresan.

Incluye:
- Persistencia de datos en una base de datos en memoria H2 para facilitar las pruebas.
- APIs REST diseñadas siguiendo principios de arquitectura hexagonal.

- ## Stack Tecnológico utilizado
- Lenguaje: Java 17
- Framework: Spring Boot
- Base de Datos: H2 (en memoria)
- Arquitectura: Hexagonal
- Creación API: OpenAPI
- Testing: Junit

- ## Arquitectura
El proyecto implementa una arquitectura hexagonal donde:
1. Dominio: Contiene las entidades, servicios y lógica de negocio.
2. Aplicación: Gestiona los casos de uso y conecta las peticiones con el dominio.
3. Infraestructura: Maneja la persistencia y adaptadores externos (por ejemplo, controladores REST).

4. Casos de prueba cubiertos:
Consulta a las 10:00 del día 14.
Consulta a las 16:00 del día 14.
Consulta a las 21:00 del día 14.
Consulta a las 10:00 del día 15.
Consulta a las 21:00 del día 16.
