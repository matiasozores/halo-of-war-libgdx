# Changelog

Todas las versiones siguen el formato [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/) y este proyecto usa [SemVer](https://semver.org/lang/es/).

## [Unreleased]

### Added
- Componentes de enemigos bajo el nuevo ECS (ejemplo: `EnemyComponent`) para reemplazar la antigua jerarquía de clases.
- Sistema de gestión de escenas en desarrollo para permitir la transición entre diferentes mapas (todo lo que es `FlowManager`, `GameScene`, `CombatZoneScene`, `SafeZoneScene`) — estructura básica lista, falta implementación de cada escena.

### In Progress
- Movimiento de enemigos: pendiente implementar la lógica completa de desplazamiento de entidades que no sean el jugador dentro del ECS.
- Sistema de escenas: aún se está trabajando en la navegación completa entre diferentes mapas (representados como escenas) del juego.
  
### Areas of improvement
- Animaciones: Actualmente poca flexibilidad; agregar un tipo nuevo de animación requiere modificar varias clases.
- EventBus: Problemas de escalabilidad; necesita revisión para manejar grandes cantidades de eventos y listeners.
- CollisionSystem: Bucles anidados poco eficientes con muchas entidades; se podría reemplazar por una **spatial grid**.
- BulletSystem: Actualmente se encarga de crear las balas en vez de tratarlas como entidades independientes.
- CrosshairSystem: No se trata como entidad individual; mezcla responsabilidades como renderizado y actualización del mouse de forma agresiva.
- HUDFactory: Mientras más componentes tenga, mayor complejidad; poca escalabilidad.
- SceneFactory: Uso de `switch` limita la escalabilidad al agregar nuevas escenas.
- WeaponSystem: Mal funcionamiento si hay enemigos con armas.
- Gestión de música: Debería manejarse mediante eventos para un mejor desacoplamiento.
- Convenciones de escritura: Falta cambiar a mayúscula algunas constantes que por despistado se olvido cambiar.
- Componentes de ECS: Por facilidad pusimos en public muchos atributos de los componentes ademas que es un estándar pero por argumentos de la materia se puede considerar cambiar esto si se llama la atención.

## [0.3.0] - 2025-08-17

### Changed
- Refactor completo del sistema de entidades a un **ECS (Entity Component System)**:
  - Creación de `Entity` que representa una entidad genérica del ECS que puede tener cualquier combinación de componentes.
  - Integración de `EventBus` para comunicación desacoplada.
  - Implementación de diferentes sistemas para que todo lo que funcionaba antes se adapte a lo nuevo.
  - Reemplazo de la lógica anterior de entidades heredadas por una composición flexible.
- Reestructuración de carpetas para reflejar la nueva arquitectura ECS.

### Notes
- Este refactor no agrega nuevas mecánicas jugables visibles, pero sienta la base para escalar el proyecto con sistemas desacoplados y más fáciles de mantener.

## [0.2.0] - 2025-08-03

### Added
- Escena de juego donde el jugador puede:
  - Moverse usando WASD.
  - Disparar con clic izquierdo.
  - Recoger objetos con la tecla E.
- HUD del jugador con barra de vida, arma equipada e inventario.
- Menú principal con opciones de Jugar, Configuración y Salir.
- Selector de personaje al iniciar partida (Kratos, MasterChief, Elite).
- Configuración de volumen y resolución de pantalla.
- Sistema de colisiones para entidades, ítems y balas.
- Sistema de animaciones usando spritesheets (`AnimationComponent`, `AnimationRenderer`).
- Manejo de sonido y música (`AudioManager`, `SoundManager`).
- Organización del código en carpetas lógicas:
  - Entidades (`Entity`, `LivingEntity`, `StaticEntity`).
  - Colisiones (`CollisionManager`, interfaces e implementaciones).
  - Audio.
  - Animaciones.

### Notes
- Esta versión representa la **segunda entrega del proyecto**, enfocada en mostrar las mecánicas principales, estructura del código y base de los sistemas.

### Notes
- Esta versión representa la **segunda entrega del proyecto**, enfocada en mostrar las mecánicas principales, estructura del código y base de los sistemas.

## [0.1.0] - 2025-05-21

### Added
- Inicialización del proyecto "Halo of War".
- Estructura base del repositorio y documentación inicial.
