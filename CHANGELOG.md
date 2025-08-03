El formato sigue el estándar [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/).

---

## [Unreleased]

## [0.1.0] - 2025-05-21
### Agregado
- Se inicializa el proyecto "Halo of War" con su respectiva documentación.
## [0.1.0] - 2025-08-3
### Segunda entrega
En esta segunda entrega del proyecto se veran los primeros avances del mismo:
#### Experiencia y funcionalidades
- Escena de juego:
Se presenta un mapa donde el jugador aprendera las mecanicas basicas del juego: Moverse (WASD), disparar (click izquierdo) y recoger (E). El jugador posee su HUD donde vera su barra de vida (0/100), el arma que tiene, y la cantidad de objetos que lleva en su inventario.
- Menu:
En este, el usuario podra acceder a las opciones de jugar, configuracion y salir. El apartado de jugar dara a elegir al usuario con que personaje jugar y asi dar inicio al gameplay; Configuracion permite el ajuste del volumen del juego (efectos de sonido y musica) y de la resolucion de la pantalla; Salir cierra el juego.
#### Codigo
Se encuentra separado y organizado por carpetas donde encontraremos
- Personajes y entidades:
Manejo de entidades con el uso de diferentes clases, siendo la principal de todas Entity. Existen otras como son Kratos, MasterChief, Elite, que extienden de LivingEntity; Obstacle, Item, que extienden de StaticEntity.
- Colisiones:
Control de colisiones para las balas, los objetos del mapa, items y demas entidades. Esto es con el uso de clases como CollisionSystem, CollisionManager, CollisionHandler.
- Animaciones:
Sistema de animaciones usando spritesheets y texture region, aplicando las clases como: AnimationComponent, AnimationRenderer, ect.

  

