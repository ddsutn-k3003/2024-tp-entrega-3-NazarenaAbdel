[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/KXg_hGCY)
# {Abdel Nour, Nazarena}

Template para TP DDS 2024 - Entrega 1

deploy entrega 3: https://dashboard.render.com/web/srv-cpl01ded3nmc73dcqkvg

deploy base de datos: https://dashboard.render.com/d/dpg-cpkvhbq0si5c73d322t0-a/logs



ENDPOINTS 


POST /rutas

Agregar una nueva ruta.

Request Body:

application/json

Example Value:

{
   
    "id": 0,
   
    "colaboradorId": 1,
    
    "heladeraIdOrigen": 0,
    
    "heladeraIdDestino": 1
}


POST /traslados

Agregar un nuevo traslado.

Request Body:

application/json

Example Value:

{
   
    "qrVianda": "abcd",
   
    "status": "CREADO",
   
    "fechaTraslado": "2024-05-15T21:10:40Z",
   
    "heladeraOrigen": 1,
   
    "heladeraDestino": 2
}




GET /traslados/search/findByColaboradorId

Obtener un traslado por id del colaborador, un mes y un año

Request:

Parameters: id (long) - ID del colaborador

Parameters: anio (integer) - anio del traslado

Parameters: mes (integer) - mes del traslado




GET /traslados/{id}

Obtener un traslado por su id

Request

Parameters: id (long) - id del traslado



PATCH /traslados/{id}

Modificar un traslado dado su id

Request

Parameters: qrVianda (string) - qr de la vianda

request body


{
    "status": "EN_VIAJE" o "ENTREGADO"
}
