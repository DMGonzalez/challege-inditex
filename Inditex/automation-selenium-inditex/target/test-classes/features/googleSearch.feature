# encoding: utf-8
# language: es

@Google
Característica: Google test

  @Google_001 @run
  Escenario: Búsqueda en Google - Validación de resultado
    Dado Que el usuario accede a Google
    Cuando Busca información de Automatización
    Entonces Visualiza la fecha de la primera automatización en wikipedia