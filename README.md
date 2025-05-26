# Halo of War

## Integrantes del Grupo
- Matías Alejandro Ozores  
- Francisco Sosa Lanza  

## Descripción Corta del Juego
Halo of War es un videojuego de acción y aventura en 2D con vista cenital. Está pensado para ser jugado en modo cooperativo en red o de forma individual. Combina exploración, combate cuerpo a cuerpo y a distancia, misiones principales y secundarias, cinemáticas, logros y personalización de personajes, todo ambientado en un mundo visual estilo pixel art. El juego propone una experiencia dinámica con historia original y múltiples modos de juego.

## Tecnologías y plataformas objetivo
- Java 8
- LibGDX
- Herramientas adicionales: Tiled (para mapas), recursos de OpenGameArt e IA para diseño
- Plataformas objetivo: Escritorio (Windows, Linux, macOS)
- IDE utilizado: IntelliJ IDEA

## Enlace a la Wiki del Proyecto (Propuesta Detallada)
[Ver la Propuesta Completa del Proyecto aquí](https://github.com/matiasozores/halo-of-war-libgdx/wiki)

## Cómo Compilar y Ejecutar

### Windows y Linux/macOS
1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/matiasozores/halo-of-war-libgdx.git
   cd halo-of-war-libgdx
   ```

2. **Importar el proyecto en un IDE compatible con Gradle** (como IntelliJ IDEA o Eclipse).  
   - Seleccionar el proyecto como Gradle Project.  
   - Verificar que esté usando Java 8.  

3. **Ejecutar el juego:**
   - Desde el IDE, correr la clase `Lwjgl3Launcher.java` ubicada en el módulo `lwjgl3`.
   - Desde la consola (Linux/macOS y Windows con gradlew):

       ```bash
      # Linux/macOS
      ./gradlew run
      
      # Windows (CMD o PowerShell)
      gradlew.bat run
       ```

## Estado Actual del Proyecto
El proyecto se encuentra en su fase inicial de desarrollo:
- Estructura del proyecto y configuración de entorno completadas.
- Configuración del entorno de desarrollo completada (preparado para ser importado en un IDE como IntelliJ IDEA o Eclipse).
- Motor LibGDX integrado.

