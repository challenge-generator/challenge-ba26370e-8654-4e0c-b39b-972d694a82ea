# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/comments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/comments/application/service/CommentService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/adapter/AuthenticationServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/application/service/CommentService.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/service/CommentService.java` — `Comment.userId`: Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentJpaRepository.deleteById`: Se invoca `deleteById` sobre `CommentJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getId`: Se invoca `getId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getContent`: Se invoca `getContent` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getRating`: Se invoca `getRating` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getProductId`: Se invoca `getProductId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getUserId`: Se invoca `getUserId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getCreatedAt`: Se invoca `getCreatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setId`: Se invoca `setId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setContent`: Se invoca `setContent` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.content`: Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setRating`: Se invoca `setRating` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.rating`: Se invoca `rating` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setProductId`: Se invoca `setProductId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setUserId`: Se invoca `setUserId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.userId`: Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.createdAt`: Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.updatedAt`: Se invoca `updatedAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.id`: Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.userId`: Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.content`: Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.rating`: Se invoca `rating` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.createdAt`: Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.updatedAt`: Se invoca `updatedAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.getField`: Se invoca `getField` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`: Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.productId`: Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.userId`: Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `CommentService.getCommentsByProductId`: Se invoca `getCommentsByProductId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.size`: Se invoca `size` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.get`: Se invoca `get` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `CommentService.getCommentsByUserId`: Se invoca `getCommentsByUserId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.isEmpty`: Se invoca `isEmpty` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `CommentService.getCommentsByProductId`: Se invoca `getCommentsByProductId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `CommentService.getCommentsByUserId`: Se invoca `getCommentsByUserId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y validaciones

### Reto
- Tema: E2E prod comments 1790905766
- Seniority: senior-l2
- Tipo: practical
- Título: Manejo de errores y validaciones en sistema de comentarios de productos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Implementación de validaciones básicas — objetivo: Asegurar que los comentarios ingresados cumplen con las reglas de validación establecidas. — entregable (NO resolver): Validaciones básicas implementadas y funcionales para los campos de los comentarios.
- Fase 2: Manejo de errores de red y timeouts — objetivo: Garantizar la robustez del sistema ante errores de red y timeouts del servicio de autenticación. — entregable (NO resolver): Lógica implementada para manejar errores de red y timeouts, con documentación de las estrategias utilizadas.
- Fase 3: Idempotencia de comentarios — objetivo: Garantizar que los comentarios sean idempotentes con respecto al ID del usuario y timestamp. — entregable (NO resolver): Lógica implementada para garantizar la idempotencia de los comentarios, con documentación de la estrategia utilizada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>comments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>comments</name>
    <description>Sistema de comentarios para productos con manejo de errores y validaciones</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.5.0</springdoc-openapi.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/comments/CommentsApplication.java ===
package com.pragma.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.boot.task.TaskExecutorBuilder;
import java.util.concurrent.Executor;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
public class CommentsApplication {
    private final TaskExecutorBuilder taskExecutorBuilder;

    public CommentsApplication(TaskExecutorBuilder taskExecutorBuilder) {
        this.taskExecutorBuilder = taskExecutorBuilder;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = taskExecutorBuilder.build();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public String applicationInfo() {
        String env = System.getProperty("spring.profiles.active", "default");
        String version = getClass().getPackage().getImplementationVersion();
        String info = String.format("Comments Application - Environment: %s, Version: %s", env, version != null ? version : "0.0.1-SNAPSHOT");
        System.out.println(info);
        return info;
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/comments

spring:
  application:
    name: comments-service
  datasource:
    url: jdbc:postgresql://localhost:5432/comments_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 10
      minimum-idle: 2
      idle-timeout: 30000
      max-lifetime: 120000
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          lob:
            non_contextual_creation: true
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true
    out-of-order: false
    schemas: public
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

resilience4j:
  circuitbreaker:
    instances:
      authenticationService:
        registerHealthIndicator: true
        failureRateThreshold: 50
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
      commentPersistence:
        registerHealthIndicator: true
        failureRateThreshold: 30
        minimumNumberOfCalls: 5
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
  retry:
    instances:
      authenticationService:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.client.ResourceAccessException
          - java.util.concurrent.TimeoutException
          - java.net.SocketTimeoutException
  timelimiter:
    instances:
      authenticationService:
        timeoutDuration: 3s
        cancelRunningFuture: true

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    retries:
      enabled: true

logging:
  level:
    root: INFO
    com.pragma.comments: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: DEBUG
    org.springframework.orm.jpa: DEBUG
    io.github.resilience4j: DEBUG

// === ARCHIVO: src/main/java/com/pragma/comments/domain/model/Comment.java ===
package com.pragma.comments.domain.model;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record Comment(
    UUID id,
    UUID productId,
    UUID userId,
    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    @Size(max = 1000, message = "El contenido del comentario no puede exceder los 1000 caracteres")
    String content,
    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    Integer rating,
    @NotNull(message = "La fecha de creación es obligatoria")
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public Comment {
        if (content != null && content.length() > 1000) {
            throw new IllegalArgumentException("El contenido del comentario no puede exceder los 1000 caracteres");
        }
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
    }

    public static Comment createNew(
            UUID productId,
            UUID userId,
            String content,
            Integer rating,
            LocalDateTime createdAt) {
        return new Comment(
                UUID.randomUUID(),
                productId,
                userId,
                content,
                rating,
                createdAt,
                null
        );
    }

    public Comment updateContent(String newContent, Integer newRating, LocalDateTime updatedAt) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        if (newRating == null || newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
        return new Comment(
                this.id,
                this.productId,
                this.userId,
                newContent,
                newRating,
                this.createdAt,
                updatedAt
        );
    }

    public boolean isSameAs(UUID userId, LocalDateTime createdAt) {
        return this.userId.equals(userId) && this.createdAt.equals(createdAt);
    }
}
"

// === ARCHIVO: src/main/java/com/pragma/comments/domain/port/CommentRepository.java ===
package com.pragma.comments.domain.port;

import com.pragma.comments.domain.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Optional<Comment> findById(UUID id);
    List<Comment> findByProductId(UUID productId);
    List<Comment> findByUserId(UUID userId);
    Optional<Comment> findByUserIdAndCreatedAt(UUID userId, java.time.LocalDateTime createdAt);
    Comment save(Comment comment);
    void delete(UUID id);
    boolean existsByUserIdAndCreatedAt(UUID userId, java.time.LocalDateTime createdAt);
}
"

// === ARCHIVO: src/main/java/com/pragma/comments/domain/port/AuthenticationServicePort.java ===
package com.pragma.comments.domain.port;

import java.util.UUID;

public interface AuthenticationServicePort {
    boolean validateUser(UUID userId);
    String getUserRole(UUID userId);
    boolean isUserActive(UUID userId);
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/InvalidCommentException.java ===
package com.pragma.comments.domain.exception;

import java.util.List;

/**
 * Excepción personalizada para manejar errores de validación de comentarios.
 * Se lanza cuando el contenido del comentario no cumple con las reglas de validación
 * establecidas en el sistema, como longitud mínima/máxima, contenido inapropiado,
 * o rating fuera de los límites permitidos.
 */
public class InvalidCommentException extends RuntimeException {

    private final String fieldName;
    private final Object rejectedValue;
    private final List<String> validationErrors;

    /**
     * Constructor con mensaje simple.
     * @param message Descripción del error de validación
     */
    public InvalidCommentException(String message) {
        super(message);
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con mensaje y causa raíz.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con información detallada del campo que falló.
     * @param fieldName Nombre del campo que no pasó la validación
     * @param rejectedValue Valor que fue rechazado
     * @param message Mensaje descriptivo del error
     */
    public InvalidCommentException(String fieldName, Object rejectedValue, String message) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con múltiples errores de validación.
     * Útil cuando se validan varios campos simultáneamente.
     * @param validationErrors Lista de mensajes de error
     */
    public InvalidCommentException(List<String> validationErrors) {
        super("Validación de comentario fallida: " + String.join("; ", validationErrors));
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = validationErrors;
    }

    /**
     * Constructor completo con toda la información de validación.
     * @param fieldName Nombre del campo que falló
     * @param rejectedValue Valor rechazado
     * @param message Mensaje de error
     * @param validationErrors Lista de errores
     */
    public InvalidCommentException(String fieldName, Object rejectedValue, 
                                    String message, List<String> validationErrors) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.validationErrors = validationErrors;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String toString() {
        return "InvalidCommentException{" +
                "fieldName='" + fieldName + '\'' +
                ", rejectedValue=" + rejectedValue +
                ", validationErrors=" + validationErrors +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/AuthenticationServiceException.java ===
package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para manejar errores del servicio de autenticación.
 * Se lanza cuando el servicio de autenticación no puede validar las credenciales del usuario,
 * cuando hay problemas de conexión, timeouts, o cuando el usuario no existe o no está activo.
 * Esta excepción ayuda a distinguir entre errores de autenticación y otros tipos de errores
 * en el sistema de comentarios.
 */
public class AuthenticationServiceException extends RuntimeException {

    private final UUID userId;
    private final String operation;
    private final ErrorType errorType;
    private final LocalDateTime timestamp;

    /**
     * Tipos de errores posibles en el servicio de autenticación.
     */
    public enum ErrorType {
        CONNECTION_ERROR,
        TIMEOUT,
        USER_NOT_FOUND,
        USER_INACTIVE,
        INVALID_TOKEN,
        SERVICE_UNAVAILABLE,
        UNKNOWN_ERROR
    }

    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error de autenticación
     */
    public AuthenticationServiceException(String message) {
        super(message);
        this.userId = null;
        this.operation = null;
        this.errorType = ErrorType.UNKNOWN_ERROR;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con causa raíz.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public AuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
        this.userId = null;
        this.operation = null;
        this.errorType = ErrorType.UNKNOWN_ERROR;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con información del usuario y tipo de error.
     * @param userId ID del usuario que no pudo ser validado
     * @param errorType Tipo específico de error de autenticación
     * @param message Mensaje descriptivo
     */
    public AuthenticationServiceException(UUID userId, ErrorType errorType, String message) {
        super(message);
        this.userId = userId;
        this.operation = null;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor completo con toda la información del error.
     * @param userId ID del usuario
     * @param operation Operación que se intentaba realizar
     * @param errorType Tipo de error
     * @param message Mensaje descriptivo
     */
    public AuthenticationServiceException(UUID userId, String operation, 
                                          ErrorType errorType, String message) {
        super(message);
        this.userId = userId;
        this.operation = operation;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con causa raíz y tipo de error.
     * @param message Mensaje de error
     * @param cause Excepción original
     * @param errorType Tipo de error
     */
    public AuthenticationServiceException(String message, Throwable cause, ErrorType errorType) {
        super(message, cause);
        this.userId = null;
        this.operation = null;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getOperation() {
        return operation;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Verifica si el error es de tipo conexión.
     * @return true si es error de conexión
     */
    public boolean isConnectionError() {
        return errorType == ErrorType.CONNECTION_ERROR;
    }

    /**
     * Verifica si el error es de timeout.
     * @return true si es error de timeout
     */
    public boolean isTimeout() {
        return errorType == ErrorType.TIMEOUT;
    }

    /**
     * Verifica si el servicio está temporalmente no disponible.
     * @return true si el servicio no está disponible
     */
    public boolean isServiceUnavailable() {
        return errorType == ErrorType.SERVICE_UNAVAILABLE;
    }

    @Override
    public String toString() {
        return "AuthenticationServiceException{" +
                "userId=" + userId +
                ", operation='" + operation + '\'' +
                ", errorType=" + errorType +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/DuplicateCommentException.java ===
package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para manejar duplicados de comentarios.
 * Se lanza cuando se intenta crear un comentario que ya existe para el mismo usuario
 * en el mismo momento (timestamp), garantizando la idempotencia del sistema.
 * El sistema usa una clave única compuesta por el ID del usuario y el timestamp de creación
 * para detectar y prevenir duplicados.
 */
public class DuplicateCommentException extends RuntimeException {

    private final UUID userId;
    private final UUID productId;
    private final LocalDateTime createdAt;
    private final String existingCommentId;

    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error de duplicado
     */
    public DuplicateCommentException(String message) {
        super(message);
        this.userId = null;
        this.productId = null;
        this.createdAt = null;
        this.existingCommentId = null;
    }

    /**
     * Constructor con causa raíz.
     * @param message Mensaje descriptivo
     * @param cause Excepción original
     */
    public DuplicateCommentException(String message, Throwable cause) {
        super(message, cause);
        this.userId = null;
        this.productId = null;
        this.createdAt = null;
        this.existingCommentId = null;
    }

    /**
     * Constructor con información del comentario duplicado.
     * @param userId ID del usuario que intentó crear el comentario
     * @param productId ID del producto
     * @param createdAt Timestamp del comentario
     */
    public DuplicateCommentException(UUID userId, UUID productId, LocalDateTime createdAt) {
        super("El comentario ya existe para el usuario " + userId + 
              " en el producto " + productId + " con fecha " + createdAt);
        this.userId = userId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.existingCommentId = null;
    }

    /**
     * Constructor completo con ID del comentario existente.
     * @param userId ID del usuario
     * @param productId ID del producto
     * @param createdAt Timestamp del comentario
     * @param existingCommentId ID del comentario existente
     */
    public DuplicateCommentException(UUID userId, UUID productId, 
                                      LocalDateTime createdAt, String existingCommentId) {
        super("El comentario ya existe para el usuario " + userId + 
              " en el producto " + productId + ". Comentario existente: " + existingCommentId);
        this.userId = userId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.existingCommentId = existingCommentId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getExistingCommentId() {
        return existingCommentId;
    }

    /**
     * Verifica si ya existe un comentario identificable.
     * @return true si se tiene el ID del comentario existente
     */
    public boolean hasExistingCommentId() {
        return existingCommentId != null && !existingCommentId.isBlank();
    }

    @Override
    public String toString() {
        return "DuplicateCommentException{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", createdAt=" + createdAt +
                ", existingCommentId='" + existingCommentId + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/application/validator/CommentValidator.java ===
package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class CommentValidator {

    private static final int MIN_CONTENT_LENGTH = 10;
    private static final int MAX_CONTENT_LENGTH = 1000;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;
    private static final String INAPPROPRIATE_CONTENT_PATTERN = "(?i)(spam|scam|phishing|malware|offensive)";

    private final Validator validator;

    public CommentValidator(Validator validator) {
        this.validator = validator;
    }

    public void validateForCreation(String content, Integer rating, UUID productId, UUID userId) {
        if (content == null || content.isBlank()) {
            throw new InvalidCommentException("El contenido del comentario no puede estar vacío");
        }

        if (content.length() < MIN_CONTENT_LENGTH) {
            throw new InvalidCommentException(
                "El contenido debe tener al menos " + MIN_CONTENT_LENGTH + " caracteres");
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new InvalidCommentException(
                "El contenido no puede exceder " + MAX_CONTENT_LENGTH + " caracteres");
        }

        if (containsInappropriateContent(content)) {
            throw new InvalidCommentException(
                "El contenido contiene palabras o frases no permitidas");
        }

        if (rating == null) {
            throw new InvalidCommentException("La calificación es obligatoria");
        }

        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new InvalidCommentException(
                "La calificación debe estar entre " + MIN_RATING + " y " + MAX_RATING);
        }

        if (productId == null) {
            throw new InvalidCommentException("El producto es obligatorio");
        }

        if (userId == null) {
            throw new InvalidCommentException("El usuario es obligatorio");
        }
    }

    public void validateForUpdate(String content, Integer rating) {
        if (content != null) {
            if (content.isBlank()) {
                throw new InvalidCommentException("El contenido no puede estar vacío");
            }

            if (content.length() < MIN_CONTENT_LENGTH) {
                throw new InvalidCommentException(
                    "El contenido debe tener al menos " + MIN_CONTENT_LENGTH + " caracteres");
            }

            if (content.length() > MAX_CONTENT_LENGTH) {
                throw new InvalidCommentException(
                    "El contenido no puede exceder " + MAX_CONTENT_LENGTH + " caracteres");
            }

            if (containsInappropriateContent(content)) {
                throw new InvalidCommentException(
                    "El contenido contiene palabras o frases no permitidas");
            }
        }

        if (rating != null && (rating < MIN_RATING || rating > MAX_RATING)) {
            throw new InvalidCommentException(
                "La calificación debe estar entre " + MIN_RATING + " y " + MAX_RATING);
        }
    }

    private boolean containsInappropriateContent(String content) {
        return content.matches(".*" + INAPPROPRIATE_CONTENT_PATTERN + ".*");
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/application/service/CommentService.java ===
package com.pragma.comments.application.service;

import com.pragma.comments.application.validator.CommentValidator;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import com.pragma.comments.domain.port.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final AuthenticationServicePort authenticationService;
    private final CommentValidator commentValidator;

    public CommentService(
            CommentRepository commentRepository,
            AuthenticationServicePort authenticationService,
            CommentValidator commentValidator) {
        this.commentRepository = commentRepository;
        this.authenticationService = authenticationService;
        this.commentValidator = commentValidator;
    }

    public Comment createComment(String content, Integer rating, UUID productId, UUID userId) {
        log.info("Creating comment for product {} by user {}", productId, userId);

        validateUserAuthentication(userId);

        commentValidator.validateForCreation(content, rating, productId, userId);

        checkIdempotency(userId);

        Comment comment = Comment.createNew(content, rating, productId, userId);
        Comment savedComment = commentRepository.save(comment);

        log.info("Comment created successfully with id {}", savedComment.id());
        return savedComment;
    }

    public Comment updateComment(UUID commentId, String newContent, Integer newRating, UUID userId) {
        log.info("Updating comment {} by user {}", commentId, userId);

        validateUserAuthentication(userId);

        Comment existingComment = commentRepository.findById(commentId)
            .orElseThrow(() -> new InvalidCommentException(
                "Comentario no encontrado con id: " + commentId));

        if (!existingComment.userId().equals(userId)) {
            throw new InvalidCommentException("No tienes permiso para modificar este comentario");
        }

        commentValidator.validateForUpdate(newContent, newRating);

        LocalDateTime updatedAt = LocalDateTime.now();
        Comment updatedComment = existingComment.updateContent(newContent, newRating, updatedAt);

        return commentRepository.save(updatedComment);
    }

    public void deleteComment(UUID commentId, UUID userId) {
        log.info("Deleting comment {} by user {}", commentId, userId);

        validateUserAuthentication(userId);

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new InvalidCommentException(
                "Comentario no encontrado con id: " + commentId));

        String userRole = authenticationService.getUserRole(userId);
        boolean isAdmin = "ADMIN".equalsIgnoreCase(userRole);

        if (!comment.userId().equals(userId) && !isAdmin) {
            throw new InvalidCommentException("No tienes permiso para eliminar este comentario");
        }

        commentRepository.delete(commentId);
        log.info("Comment {} deleted successfully", commentId);
    }

    public Optional<Comment> getCommentById(UUID commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getCommentsByProduct(UUID productId) {
        log.debug("Fetching comments for product {}", productId);
        return commentRepository.findByProductId(productId);
    }

    public List<Comment> getCommentsByUser(UUID userId) {
        log.debug("Fetching comments for user {}", userId);
        return commentRepository.findByUserId(userId);
    }

    private void validateUserAuthentication(UUID userId) {
        try {
            if (!authenticationService.validateUser(userId)) {
                throw new AuthenticationServiceException(
                    "El usuario no es válido o no existe: " + userId);
            }

            if (!authenticationService.isUserActive(userId)) {
                throw new AuthenticationServiceException(
                    "El usuario no está activo: " + userId);
            }
        } catch (Exception e) {
            if (e instanceof AuthenticationServiceException) {
                throw (AuthenticationServiceException) e;
            }
            log.error("Error al validar el usuario {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(
                "Error al validar el usuario: " + e.getMessage());
        }
    }

    private void checkIdempotency(UUID userId) {
        LocalDateTime now = LocalDateTime.now();
        boolean exists = commentRepository.existsByUserIdAndCreatedAt(userId, now);

        if (exists) {
            log.warn("Duplicate comment detected for user {} at {}", userId, now);
            throw new DuplicateCommentException(
                "Ya existe un comentario reciente del usuario. Intenta de nuevo en unos segundos.");
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java ===
package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CommentJpaAdapter implements CommentRepository {

    private final CommentJpaRepository jpaRepository;

    public CommentJpaAdapter(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Comment> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt) {
        return jpaRepository.findByUserIdAndCreatedAt(userId, createdAt).map(this::toDomain);
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = toEntity(comment);
        CommentEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt) {
        return jpaRepository.existsByUserIdAndCreatedAt(userId, createdAt);
    }

    private Comment toDomain(CommentEntity entity) {
        return new Comment(
            entity.getId(),
            entity.getContent(),
            entity.getRating(),
            entity.getProductId(),
            entity.getUserId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    private CommentEntity toEntity(Comment comment) {
        CommentEntity entity = new CommentEntity();
        if (comment.id() != null) {
            entity.setId(comment.id());
        }
        entity.setContent(comment.content());
        entity.setRating(comment.rating());
        entity.setProductId(comment.productId());
        entity.setUserId(comment.userId());
        entity.setCreatedAt(comment.createdAt());
        entity.setUpdatedAt(comment.updatedAt());
        return entity;
    }

    @Entity
    @Table(name = "comments")
    public static class CommentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false, length = 1000)
        private String content;

        @Column(nullable = false)
        private Integer rating;

        @Column(name = "product_id", nullable = false)
        private UUID productId;

        @Column(name = "user_id", nullable = false)
        private UUID userId;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    interface CommentJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<CommentEntity, UUID> {
        List<CommentEntity> findByProductId(UUID productId);
        List<CommentEntity> findByUserId(UUID userId);
        Optional<CommentEntity> findByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt);
        boolean existsByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/adapter/AuthenticationServiceAdapter.java ===
package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.UUID;

@Component
public class AuthenticationServiceAdapter implements AuthenticationServicePort {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceAdapter.class);
    private static final String SERVICE_UNAVAILABLE_MSG = "Authentication service is unavailable";
    private static final String VALIDATION_FAILED_MSG = "User validation failed";

    private final RestTemplate restTemplate;
    private final String authServiceBaseUrl;
    private final Duration connectionTimeout;
    private final Duration readTimeout;

    public AuthenticationServiceAdapter(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${app.authentication-service.base-url:http://localhost:8081}") String authServiceBaseUrl,
            @Value("${app.authentication-service.connection-timeout:2000}") int connectionTimeoutMs,
            @Value("${app.authentication-service.read-timeout:5000}") int readTimeoutMs) {
        this.restTemplate = restTemplateBuilder
                .connectTimeout(Duration.ofMillis(connectionTimeoutMs))
                .readTimeout(Duration.ofMillis(readTimeoutMs))
                .build();
        this.authServiceBaseUrl = authServiceBaseUrl;
        this.connectionTimeout = Duration.ofMillis(connectionTimeoutMs);
        this.readTimeout = Duration.ofMillis(readTimeoutMs);
    }

    @Override
    public boolean validateUser(UUID userId) {
        if (userId == null) {
            log.warn("Attempted to validate null user ID");
            return false;
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/validate";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
            log.debug("User {} validation successful", userId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found in authentication service", userId);
            return false;
        } catch (Exception e) {
            log.error("Error validating user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(VALIDATION_FAILED_MSG, e);
        }
    }

    @Override
    public String getUserRole(UUID userId) {
        if (userId == null) {
            throw new AuthenticationServiceException("Cannot get role for null user ID");
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/role";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String role = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            log.debug("Retrieved role {} for user {}", role, userId);
            return role;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found when retrieving role", userId);
            return "UNKNOWN";
        } catch (Exception e) {
            log.error("Error retrieving role for user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(SERVICE_UNAVAILABLE_MSG, e);
        }
    }

    @Override
    public boolean isUserActive(UUID userId) {
        if (userId == null) {
            log.warn("Attempted to check active status for null user ID");
            return false;
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/status";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            Boolean active = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
            log.debug("User {} active status: {}", userId, active);
            return Boolean.TRUE.equals(active);
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found when checking active status", userId);
            return false;
        } catch (Exception e) {
            log.error("Error checking active status for user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(SERVICE_UNAVAILABLE_MSG, e);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java ===
package com.pragma.comments.infrastructure.rest;

import com.pragma.comments.application.service.CommentService;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.infrastructure.rest.dto.CommentCreateRequest;
import com.pragma.comments.infrastructure.rest.dto.CommentResponse;
import com.pragma.comments.infrastructure.rest.dto.CommentUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments", description = "API for managing product comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new comment", description = "Creates a new comment for a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicate comment (idempotency conflict)", content = @Content),
            @ApiResponse(responseCode = "503", description = "Authentication service unavailable", content = @Content)
    })
    public ResponseEntity<CommentResponse> createComment(
            @Valid @RequestBody CommentCreateRequest request,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        log.info("Creating comment for product {} by user {}", request.productId(), userId);
        
        Comment comment = commentService.createComment(
                request.productId(),
                userId,
                request.content(),
                request.rating()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get comments by product", description = "Retrieves all comments for a specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID", content = @Content)
    })
    public ResponseEntity<List<CommentResponse>> getCommentsByProduct(
            @Parameter(description = "Product ID") @PathVariable UUID productId) {
        log.debug("Fetching comments for product {}", productId);
        
        List<Comment> comments = commentRepository.findByProductId(productId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get comments by user", description = "Retrieves all comments made by a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID", content = @Content)
    })
    public ResponseEntity<List<CommentResponse>> getCommentsByUser(
            @Parameter(description = "User ID") @PathVariable UUID userId) {
        log.debug("Fetching comments for user {}", userId);
        
        List<Comment> comments = commentRepository.findByUserId(userId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "Get comment by ID", description = "Retrieves a specific comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<CommentResponse> getCommentById(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId) {
        log.debug("Fetching comment {}", commentId);
        
        return commentRepository.findById(commentId)
                .map(comment -> ResponseEntity.ok(CommentResponse.fromDomain(comment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "Update a comment", description = "Updates an existing comment's content and rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<CommentResponse> updateComment(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId,
            @Valid @RequestBody CommentUpdateRequest request,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId) {
        log.info("Updating comment {} by user {}", commentId, userId);
        
        Comment updatedComment = commentService.updateComment(
                commentId,
                userId,
                request.content(),
                request.rating()
        );
        
        return ResponseEntity.ok(CommentResponse.fromDomain(updatedComment));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete a comment", description = "Deletes a comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId) {
        log.info("Deleting comment {} by user {}", commentId, userId);
        
        commentService.deleteComment(commentId, userId);
        
        return ResponseEntity.noContent().build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentCreateRequest.java ===
package com.pragma.comments.infrastructure.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CommentCreateRequest(
        @NotNull(message = "Product ID is required")
        UUID productId,
        @NotBlank(message = "Content cannot be blank")
        @Size(min = 1, max = 2000, message = "Content must be between 1 and 2000 characters")
        String content,
        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating
) {}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentUpdateRequest.java ===
package com.pragma.comments.infrastructure.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequest(
        @NotBlank(message = "Content cannot be blank")
        @Size(min = 1, max = 2000, message = "Content must be between 1 and 2000 characters")
        String content,
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating
) {}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java ===
package com.pragma.comments.infrastructure.rest.dto;

import com.pragma.comments.domain.model.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        UUID productId,
        UUID userId,
        String content,
        Integer rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CommentResponse fromDomain(Comment comment) {
        return new CommentResponse(
                comment.id(),
                comment.productId(),
                comment.userId(),
                comment.content(),
                comment.rating(),
                comment.createdAt(),
                comment.updatedAt()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java ===
package com.pragma.comments.infrastructure.config;


import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidCommentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCommentException(InvalidCommentException ex) {
        log.warn("Invalid comment exception: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_COMMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationServiceException(AuthenticationServiceException ex) {
        log.error("Authentication service exception: {}", ex.getMessage(), ex);
        ErrorResponse error = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "AUTHENTICATION_SERVICE_ERROR",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(DuplicateCommentException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCommentException(DuplicateCommentException ex) {
        log.warn("Duplicate comment exception: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "DUPLICATE_COMMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Validation exception: {}", ex.getMessage());
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));

        ValidationErrorResponse error = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Comment validation failed",
                fieldErrors,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Illegal argument exception: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_ARGUMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Please try again later.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    public record ErrorResponse(
            int status,
            String code,
            String message,
            LocalDateTime timestamp
    ) {}

    public record ValidationErrorResponse(
            int status,
            String code,
            String message,
            Map<String, String> fieldErrors,
            LocalDateTime timestamp
    ) {}
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java ===
package com.pragma.comments.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Comentarios")
                        .version("1.0.0")
                        .description("""
                                Sistema de comentarios para productos.
                                
                                Esta API permite crear, consultar y eliminar comentarios
                                asociados a productos. Los comentarios son validados
                                antes de ser almacenados y el sistema garantiza
                                idempotencia mediante claves únicas compuestas.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@pragma.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT de autenticación")));
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.comments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(3))
                .build();
        return TimeLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/resources/db/migration/V1__Create_comments_table.sql ===
-- Script de migración para crear la tabla de comentarios
-- Sistema de gestión de comentarios para productos

CREATE TABLE IF NOT EXISTS comments (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    product_id UUID NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Índice para búsquedas por producto
CREATE INDEX idx_comments_product_id ON comments(product_id);

-- Índice para búsquedas por usuario
CREATE INDEX idx_comments_user_id ON comments(user_id);

-- Índice para búsquedas por fecha de creación
CREATE INDEX idx_comments_created_at ON comments(created_at DESC);

-- Índice único para garantizar idempotencia (user_id + created_at)
CREATE UNIQUE INDEX idx_comments_user_created_unique ON comments(user_id, created_at);

-- Tabla de productos (referencia necesaria para la FK)
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de usuarios (referencia necesaria para la FK)
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Comentario en la tabla
COMMENT ON TABLE comments IS 'Almacena los comentarios de usuarios sobre productos';
COMMENT ON COLUMN comments.content IS 'Contenido del comentario';
COMMENT ON COLUMN comments.rating IS 'Puntuación del 1 al 5';
COMMENT ON COLUMN comments.product_id IS 'ID del producto comentado';
COMMENT ON COLUMN comments.user_id IS 'ID del usuario que realiza el comentario';

// === ARCHIVO: src/test/java/com/pragma/comments/application/service/CommentServiceTest.java ===
package com.pragma.comments.application.service;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import com.pragma.comments.application.validator.CommentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentService - Pruebas Unitarias")
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AuthenticationServicePort authenticationService;

    @Mock
    private CommentValidator commentValidator;

    @InjectMocks
    private CommentService commentService;

    private UUID productId;
    private UUID userId;
    private Comment sampleComment;
    private LocalDateTime fixedTimestamp;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        userId = UUID.randomUUID();
        fixedTimestamp = LocalDateTime.now();
        sampleComment = Comment.createNew(
            productId,
            userId,
            "Excelente producto, muy recomendado",
            5
        );
    }

    @Nested
    @DisplayName("Crear comentario")
    class CreateComment {

        @Test
        @DisplayName("Debe crear comentario exitosamente cuando el usuario es válido")
        void shouldCreateCommentSuccessfully_WhenUserIsValid() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            when(commentRepository.existsByUserIdAndCreatedAt(any(), any())).thenReturn(false);
            when(commentRepository.save(any(Comment.class))).thenReturn(sampleComment);

            Comment result = commentService.createComment(productId, userId, "Excelente producto", 5);

            assertNotNull(result);
            assertEquals(productId, result.productId());
            assertEquals(userId, result.userId());
            verify(commentRepository).save(any(Comment.class));
            verify(authenticationService).validateUser(userId);
            verify(authenticationService).isUserActive(userId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el usuario no existe")
        void shouldThrowException_WhenUserDoesNotExist() {
            when(authenticationService.validateUser(userId)).thenReturn(false);

            assertThrows(AuthenticationServiceException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 4)
            );

            verify(commentRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el usuario está inactivo")
        void shouldThrowException_WhenUserIsInactive() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(false);

            assertThrows(AuthenticationServiceException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 3)
            );

            verify(commentRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe lanzar excepción para contenido inválido")
        void shouldThrowException_WhenContentIsInvalid() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Contenido vacío"))
                .when(commentValidator).validateContent("");

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "", 4)
            );
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el rating está fuera de rango")
        void shouldThrowException_WhenRatingOutOfRange() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Rating debe estar entre 1 y 5"))
                .when(commentValidator).validateRating(0);

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 0)
            );
        }

        @Test
        @DisplayName("Debe lanzar excepción por comentario duplicado (idempotencia)")
        void shouldThrowException_WhenCommentIsDuplicate() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            when(commentRepository.existsByUserIdAndCreatedAt(any(), any())).thenReturn(true);

            assertThrows(DuplicateCommentException.class, () ->
                commentService.createComment(productId, userId, "Nuevo contenido", 5)
            );

            verify(commentRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Obtener comentarios")
    class GetComments {

        @Test
        @DisplayName("Debe obtener comentarios por ID de producto")
        void shouldGetCommentsByProductId() {
            List<Comment> comments = List.of(sampleComment);
            when(commentRepository.findByProductId(productId)).thenReturn(comments);

            List<Comment> result = commentService.getCommentsByProductId(productId);

            assertEquals(1, result.size());
            assertEquals(productId, result.get(0).productId());
            verify(commentRepository).findByProductId(productId);
        }

        @Test
        @DisplayName("Debe obtener comentarios por ID de usuario")
        void shouldGetCommentsByUserId() {
            List<Comment> comments = List.of(sampleComment);
            when(commentRepository.findByUserId(userId)).thenReturn(comments);

            List<Comment> result = commentService.getCommentsByUserId(userId);

            assertEquals(1, result.size());
            assertEquals(userId, result.get(0).userId());
            verify(commentRepository).findByUserId(userId);
        }

        @Test
        @DisplayName("Debe retornar lista vacía cuando no hay comentarios")
        void shouldReturnEmptyList_WhenNoCommentsExist() {
            when(commentRepository.findByProductId(productId)).thenReturn(List.of());

            List<Comment> result = commentService.getCommentsByProductId(productId);

            assertTrue(result.isEmpty());
            verify(commentRepository).findByProductId(productId);
        }
    }

    @Nested
    @DisplayName("Actualizar comentario")
    class UpdateComment {

        @Test
        @DisplayName("Debe actualizar comentario exitosamente")
        void shouldUpdateCommentSuccessfully() {
            UUID commentId = UUID.randomUUID();
            Comment updatedComment = sampleComment.updateContent("Contenido actualizado", 4, fixedTimestamp);

            when(commentRepository.findById(commentId)).thenReturn(Optional.of(sampleComment));
            when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

            Comment result = commentService.updateComment(commentId, "Contenido actualizado", 4);

            assertNotNull(result);
            verify(commentRepository).findById(commentId);
            verify(commentRepository).save(any(Comment.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el comentario no existe")
        void shouldThrowException_WhenCommentNotFound() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                commentService.updateComment(commentId, "Nuevo contenido", 5)
            );
        }
    }

    @Nested
    @DisplayName("Eliminar comentario")
    class DeleteComment {

        @Test
        @DisplayName("Debe eliminar comentario exitosamente")
        void shouldDeleteCommentSuccessfully() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.of(sampleComment));
            doNothing().when(commentRepository).delete(commentId);

            assertDoesNotThrow(() -> commentService.deleteComment(commentId));

            verify(commentRepository).findById(commentId);
            verify(commentRepository).delete(commentId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el comentario no existe para eliminar")
        void shouldThrowException_WhenCommentNotFoundToDelete() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                commentService.deleteComment(commentId)
            );
        }
    }

    @Nested
    @DisplayName("Validación de contenido inapropiado")
    class ContentValidation {

        @Test
        @DisplayName("Debe rechazar contenido con palabras prohibidas")
        void shouldRejectContentWithForbiddenWords() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Contenido inapropiado detectado"))
                .when(commentValidator).validateContent(anyString());

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "contenido inapropiado", 3)
            );
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java ===
package com.pragma.comments.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.comments.application.service.CommentService;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController - Pruebas de Integración")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    private UUID productId;
    private UUID userId;
    private UUID commentId;
    private Comment sampleComment;
    private String sampleContent;
    private Integer sampleRating;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        userId = UUID.randomUUID();
        commentId = UUID.randomUUID();
        sampleContent = "Excelente producto, muy recomendado";
        sampleRating = 5;
        sampleComment = Comment.createNew(productId, userId, sampleContent, sampleRating);
    }

    @Nested
    @DisplayName("POST /api/comments - Crear comentario")
    class CreateComment {

        @Test
        @DisplayName("Debe retornar 201 Created cuando el comentario se crea exitosamente")
        void shouldReturn201_WhenCommentCreatedSuccessfully() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenReturn(sampleComment);

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.content").value(sampleContent))
                .andExpect(jsonPath("$.rating").value(sampleRating));

            verify(commentService).createComment(eq(productId), eq(userId), eq(sampleContent), eq(sampleRating));
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el contenido está vacío")
        void shouldReturn400_WhenContentIsEmpty() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new InvalidCommentException("El contenido no puede estar vacío"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleRating)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 401 Unauthorized cuando el usuario no existe")
        void shouldReturn401_WhenUserDoesNotExist() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new AuthenticationServiceException("Usuario no encontrado"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 409 Conflict cuando el comentario es duplicado")
        void shouldReturn409_WhenCommentIsDuplicate() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new DuplicateCommentException("Ya existe un comentario para este usuario"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el rating está fuera de rango")
        void shouldReturn400_WhenRatingOutOfRange() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new InvalidCommentException("Rating debe estar entre 1 y 5"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": 10
                        }
                        """.formatted(productId, userId, sampleContent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el JSON está malformado")
        void shouldReturn400_WhenJsonIsMalformed() throws Exception {
            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/comments/product/{productId} - Obtener comentarios por producto")
    class GetCommentsByProduct {

        @Test
        @DisplayName("Debe retornar 200 OK con lista de comentarios")
        void shouldReturn200_WithCommentsList() throws Exception {
            List<Comment> comments = List.of(sampleComment);
            when(commentService.getCommentsByProductId(productId)).thenReturn(comments);

            mockMvc.perform(get("/api/comments/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].productId").value(productId.toString()));

            verify(commentService).getCommentsByProductId(productId);
        }

        @Test
        @DisplayName("Debe retornar 200 OK con lista vacía cuando no hay comentarios")
        void shouldReturn200_WithEmptyList() throws Exception {
            when(commentService.getCommentsByProductId(productId)).thenReturn(List.of());

            mockMvc.perform(get("/api/comments/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

            verify(commentService).getCommentsByProductId(productId);
        }
    }

    @Nested
    @DisplayName("GET /api/comments/user/{userId} - Obtener comentarios por usuario")
    class GetCommentsByUser {

        @Test
        @DisplayName("Debe retornar 200 OK con lista de comentarios del usuario")
        void shouldReturn200_WithUserCommentsList() throws Exception {
            List<Comment> comments = List.of(sampleComment);
            when(commentService.getCommentsByUserId(userId)).thenReturn(comments);

            mockMvc.perform(get("/api/comments/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userId").value(userId.toString()));

            verify(commentService).getCommentsByUserId(userId);
        }
    }

    @Nested
    @DisplayName("PUT /api/comments/{commentId} - Actualizar comentario")
    class UpdateComment {

        @Test
        @DisplayName("Debe retornar 200 OK cuando el comentario se actualiza exitosamente")
        void shouldReturn200_WhenCommentUpdatedSuccessfully() throws Exception {
            String updatedContent = "Contenido actualizado";
            Integer updatedRating = 4;
            Comment updatedComment = sampleComment.updateContent(updatedContent, updatedRating, LocalDateTime.now());

            when(commentService.updateComment(eq(commentId), anyString(), anyInt()))
                .thenReturn(updatedComment);

            mockMvc.perform(put("/api/comments/{commentId}", commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(updatedContent, updatedRating)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(updatedContent))
                .andExpect(jsonPath("$.rating").value(updatedRating));

            verify(commentService).updateComment(commentId, updatedContent, updatedRating);
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando el comentario no existe")
        void shouldReturn404_WhenCommentNotFound() throws Exception {
            when(commentService.updateComment(eq(commentId), anyString(), anyInt()))
                .thenThrow(new RuntimeException("Comentario no encontrado"));

            mockMvc.perform(put("/api/comments/{commentId}", commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "content": "Nuevo contenido",
                            "rating": 5
                        }
                        """))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/comments/{commentId} - Eliminar comentario")
    class DeleteComment {

        @Test
        @DisplayName("Debe retornar 204 No Content cuando el comentario se elimina exitosamente")
        void shouldReturn204_WhenCommentDeletedSuccessfully() throws Exception {
            doNothing().when(commentService).deleteComment(commentId);

            mockMvc.perform(delete("/api/comments/{commentId}", commentId))
                .andExpect(status().isNoContent());

            verify(commentService).deleteComment(commentId);
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando el comentario a eliminar no existe")
        void shouldReturn404_WhenCommentToDeleteNotFound() throws Exception {
            doThrow(new RuntimeException("Comentario no encontrado"))
                .when(commentService).deleteComment(commentId);

            mockMvc.perform(delete("/api/comments/{commentId}", commentId))
                .andExpect(status().isNotFound());
        }
    }
}
```
