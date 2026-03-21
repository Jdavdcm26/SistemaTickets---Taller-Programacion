# Sistema de Gestión de Tickets Intermunicipales

Sistema de venta y gestión de tickets desarrollado en Java para la empresa **TransCesar S.A.S.**,
aplicando los principios de Programación Orientada a Objetos y Arquitectura en Capas.

## Arquitectura
El proyecto sigue una arquitectura en capas:
- Model → Entidades del dominio
- DAO → Persistencia en archivos `.txt`
- Service → Lógica de negocio y reglas del sistema
- View → Menú interactivo en consola

## Conceptos POO aplicados
- **Abstracción** (clases abstractas `Vehiculo` y `Persona`)
- **Herencia** (`Bus`, `Buseta` y `MicroBus` extienden `Vehiculo` / `Conductor` y `Pasajero` extienden `Persona`)
- **Polimorfismo** (`calcularDescuento()` en cada subclase de `Pasajero`)
- **Encapsulamiento** (atributos `protected/private` con getters y setters)
- **Interfaces** (`InterfazImprimible` implementada por todas las entidades / `Calculable` implementada por `Ticket`)

## Estructura del proyecto

```
SistemaTickets/
├── src/
│   └── sistematickets/
│       ├── model/
│       │   ├── Vehiculo.java
│       │   ├── Bus.java
│       │   ├── Buseta.java
│       │   ├── MicroBus.java
│       │   ├── Ruta.java
│       │   ├── Persona.java
│       │   ├── Conductor.java
│       │   ├── Pasajero.java
│       │   ├── PasajeroRegular.java
│       │   ├── PasajeroEstudiante.java
│       │   ├── PasajeroAdultoMayor.java
│       │   ├── Ticket.java
│       │   ├── Reserva.java
│       │   ├── InterfazImprimible.java
│       │   └── Calculable.java
│       ├── dao/
│       │   ├── rutaDao.java
│       │   ├── VehiculoDao.java
│       │   ├── ConductorDao.java
│       │   ├── PasajeroDao.java
│       │   ├── TicketDao.java
│       │   └── ReservaDao.java
│       ├── service/
│       │   ├── RutaService.java
│       │   ├── VehiculoService.java
│       │   ├── ConductorService.java
│       │   ├── PasajeroService.java
│       │   ├── TicketService.java
│       │   └── ReservaService.java
│       ├── view/
│       │   ├── MenuPrincipal.java
│       │   ├── RutaView.java
│       │   ├── VehiculoView.java
│       │   ├── ConductorView.java
│       │   ├── PasajeroView.java
│       │   ├── TicketView.java
│       │   └── ReservaView.java
│       └── SistemaTickets.java
├── rutas.txt
├── Buses.txt
├── Busetas.txt
├── Microbuses.txt
├── conductores.txt
├── pasajeros.txt
├── tickets.txt
├── reservas.txt
├── .gitignore
└── README.md
```

## Funcionalidades
- Gestión completa de rutas, vehículos, conductores y pasajeros
- Venta de tickets con descuento por tipo de pasajero
- Recargo automático del 20% en días festivos
- Límite de 3 tickets por pasajero por día
- Módulo de reservas con expiración automática a las 24 horas
- Reportes y estadísticas de ventas

## Archivos de persistencia
| Archivo | Contenido |
|---|---|
| `rutas.txt` | Rutas registradas |
| `Buses.txt` | Buses registrados |
| `Busetas.txt` | Busetas registradas |
| `Microbuses.txt` | Microbuses registrados |
| `conductores.txt` | Conductores registrados |
| `pasajeros.txt` | Pasajeros registrados |
| `tickets.txt` | Tickets vendidos |
| `reservas.txt` | Reservas activas e historial |

## Autores
Desarrollado por Jose David Castro y Andry Camacho
Universidad Popular del Cesar — 2026
