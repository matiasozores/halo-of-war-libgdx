# Changelog

Todas las versiones siguen el formato [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/) y este proyecto usa [SemVer](https://semver.org/lang/es/).

## [Unreleased]

### Added
- Clases base de enemigos (`Enemy`, `EnemyFactory`, lógica de colisiones y matables).
- Sistema de gestión de escenas en desarrollo para permitir la transición entre diferentes mapas (todo lo que es `FlowManager`, `GameScene`, `CombatZoneScene`, `SafeZoneScene`) esta la estructura básica. De aca para adelante solo es desarrollar cada uno.  

### In Progress
- Movimiento de enemigos: pendiente implementar el algoritmo completo de desplazamiento.
- Sistema de escenas: aún se está trabajando en la navegación completa entre diferentes mapas (representados como escenas) del juego.


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
