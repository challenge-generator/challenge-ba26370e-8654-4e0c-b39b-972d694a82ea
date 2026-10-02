# Manejo de errores y validaciones en sistema de comentarios de productos

El sistema de comentarios de productos en producción requiere una revisión exhaustiva del manejo de errores y validaciones para garantizar la integridad y seguridad de los datos. Los comentarios son generados por 'usuarios registrados' y deben ser validados antes de ser almacenados en el 'almacenamiento de comentarios'. El sistema debe manejar adecuadamente los errores de red, timeouts del 'servicio de autenticación' y validaciones de contenido inapropiado. Los comentarios deben ser idempotentes con respecto al 'ID del usuario' y 'timestamp' para evitar duplicados.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E prod comments 1790905766 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Implementación de validaciones básicas

**Objetivo:** Asegurar que los comentarios ingresados cumplen con las reglas de validación establecidas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Diseñar y aplicar validaciones para los campos 'contenido', 'ID del usuario' y 'timestamp' de los comentarios.
- Garantizar que los comentarios no contengan lenguaje inapropiado o caracteres especiales no permitidos.
- Implementar la lógica para manejar errores de validación y proporcionar retroalimentación al usuario.

**Entregable:** Validaciones básicas implementadas y funcionales para los campos de los comentarios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de patrones de diseño para estructurar las validaciones.
- Piensa en cómo manejarías la retroalimentación al usuario de manera efectiva.

</details>

### Fase 2: Manejo de errores de red y timeouts

**Objetivo:** Garantizar la robustez del sistema ante errores de red y timeouts del servicio de autenticación.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar lógica para manejar errores de red y timeouts del servicio de autenticación.
- Asegurar que el sistema pueda recuperarse de estos errores sin pérdida de datos.
- Documentar las estrategias de manejo de errores y timeouts utilizadas.

**Entregable:** Lógica implementada para manejar errores de red y timeouts, con documentación de las estrategias utilizadas.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga patrones de diseño comunes para el manejo de errores y timeouts.
- Considera el uso de reintentos y backoff exponencial para mejorar la robustez del sistema.

</details>

### Fase 3: Idempotencia de comentarios

**Objetivo:** Garantizar que los comentarios sean idempotentes con respecto al ID del usuario y timestamp.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar lógica para asegurar la idempotencia de los comentarios.
- Garantizar que dos solicitudes con los mismos datos no resulten en duplicados.
- Documentar la estrategia utilizada para lograr la idempotencia.

**Entregable:** Lógica implementada para garantizar la idempotencia de los comentarios, con documentación de la estrategia utilizada.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga métodos para lograr la idempotencia en sistemas distribuidos.
- Considera el uso de claves únicas para identificar comentarios duplicados.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las validaciones y por qué son importantes en el sistema de comentarios?
- **paraQueSirve**: ¿Para qué sirve el manejo de errores y timeouts en el sistema de comentarios?
- **comoSeUsa**: ¿Cómo se aplican las validaciones en los campos de los comentarios?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir en el sistema de comentarios y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica garantizar la idempotencia de los comentarios en el sistema?

## Criterios de Evaluacion

- Implementación de validaciones básicas funcionales para los campos de los comentarios.
- Lógica implementada para manejar errores de red y timeouts, con documentación de las estrategias utilizadas.
- Lógica implementada para garantizar la idempotencia de los comentarios, con documentación de la estrategia utilizada.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
