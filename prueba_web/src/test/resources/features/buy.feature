@SmokTest
Feature: Proceso de compra en línea
  Como cliente registrado
  Quiero poder navegar por la tienda, agregar y eliminar productos del carrito
  Para realizar una compra de manera efectiva

  @Compra
  Scenario Outline: Usuario compra una tablet Samsung Galaxy eliminando un producto del carrito
    Given que Daniel inició sesión en la página Opencart "<usuario>" "<password>"
    When selecciona un portátil "MacBook Pro" de la lista
    And desde la barra de búsqueda selecciona el producto "Samsung Galaxy"
    And desde el carrito elimina el producto "MacBook Pro"
    And agrega más cantidad al producto restante finalizando la compra
    Then debería ver el número del pedido

    Examples: Tabla de compras
      | usuario               | password  |
      | prueba_tecnica_20253@gmail.com | 123456789 |
