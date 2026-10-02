# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Manejo de errores y validaciones en sistema de comentarios de productos**.

| | |
|---|---|
| Tema | E2E prod comments 1790905766 |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-aop n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.projectlombok:lombok n/a
- org.postgresql:postgresql n/a
- org.flywaydb:flyway-core n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Implementación de validaciones básicas**: Validaciones básicas implementadas y funcionales para los campos de los comentarios.
- **Fase 2 — Manejo de errores de red y timeouts**: Lógica implementada para manejar errores de red y timeouts, con documentación de las estrategias utilizadas.
- **Fase 3 — Idempotencia de comentarios**: Lógica implementada para garantizar la idempotencia de los comentarios, con documentación de la estrategia utilizada.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/comments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (49)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/comments/application/service/CommentService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/AuthenticationServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/application/service/CommentService.java` — `Comment.id`
      Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/service/CommentService.java` — `Comment.userId`
      Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentJpaRepository.deleteById`
      Se invoca `deleteById` sobre `CommentJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getId`
      Se invoca `getId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getContent`
      Se invoca `getContent` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getRating`
      Se invoca `getRating` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getProductId`
      Se invoca `getProductId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getUserId`
      Se invoca `getUserId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getCreatedAt`
      Se invoca `getCreatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.getUpdatedAt`
      Se invoca `getUpdatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.id`
      Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setId`
      Se invoca `setId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setContent`
      Se invoca `setContent` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.content`
      Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setRating`
      Se invoca `setRating` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.rating`
      Se invoca `rating` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setProductId`
      Se invoca `setProductId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.productId`
      Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setUserId`
      Se invoca `setUserId` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.userId`
      Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setCreatedAt`
      Se invoca `setCreatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.createdAt`
      Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `CommentEntity.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `CommentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java` — `Comment.updatedAt`
      Se invoca `updatedAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.id`
      Se invoca `id` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.productId`
      Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.userId`
      Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.content`
      Se invoca `content` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.rating`
      Se invoca `rating` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.createdAt`
      Se invoca `createdAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java` — `Comment.updatedAt`
      Se invoca `updatedAt` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.getField`
      Se invoca `getField` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`
      Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.productId`
      Se invoca `productId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.userId`
      Se invoca `userId` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `CommentService.getCommentsByProductId`
      Se invoca `getCommentsByProductId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.size`
      Se invoca `size` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.get`
      Se invoca `get` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `CommentService.getCommentsByUserId`
      Se invoca `getCommentsByUserId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java` — `Comment.isEmpty`
      Se invoca `isEmpty` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `CommentService.getCommentsByProductId`
      Se invoca `getCommentsByProductId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java` — `CommentService.getCommentsByUserId`
      Se invoca `getCommentsByUserId` sobre `CommentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (23)

- `pom.xml`
- `src/main/java/com/pragma/comments/CommentsApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/comments/domain/model/Comment.java`
- `src/main/java/com/pragma/comments/domain/port/CommentRepository.java`
- `src/main/java/com/pragma/comments/domain/port/AuthenticationServicePort.java`
- `src/main/java/com/pragma/comments/domain/exception/InvalidCommentException.java`
- `src/main/java/com/pragma/comments/domain/exception/AuthenticationServiceException.java`
- `src/main/java/com/pragma/comments/domain/exception/DuplicateCommentException.java`
- `src/main/java/com/pragma/comments/application/validator/CommentValidator.java`
- `src/main/java/com/pragma/comments/application/service/CommentService.java`
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java`
- `src/main/java/com/pragma/comments/infrastructure/adapter/AuthenticationServiceAdapter.java`
- `src/main/java/com/pragma/comments/infrastructure/rest/CommentController.java`
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentCreateRequest.java`
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentUpdateRequest.java`
- `src/main/java/com/pragma/comments/infrastructure/rest/dto/CommentResponse.java`
- `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/pragma/comments/infrastructure/config/Resilience4jConfig.java`
- `src/main/resources/db/migration/V1__Create_comments_table.sql`
- `src/test/java/com/pragma/comments/application/service/CommentServiceTest.java`
- `src/test/java/com/pragma/comments/infrastructure/rest/CommentControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/comments`
- `src/main/java/com/pragma/comments/domain`
- `src/main/java/com/pragma/comments/domain/model`
- `src/main/java/com/pragma/comments/domain/port`
- `src/main/java/com/pragma/comments/domain/exception`
- `src/main/java/com/pragma/comments/application`
- `src/main/java/com/pragma/comments/application/service`
- `src/main/java/com/pragma/comments/application/validator`
- `src/main/java/com/pragma/comments/infrastructure`
- `src/main/java/com/pragma/comments/infrastructure/adapter`
- `src/main/java/com/pragma/comments/infrastructure/config`
- `src/main/java/com/pragma/comments/infrastructure/rest`
- `src/main/resources`
- `src/test/java/com/pragma/comments`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2
- Brecha que el reto ataca: Comprueba manejo de errores y validaciones

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
