#Autor: Daniel Amaya Marín

Feature: Gestión de productos en la tienda "Your Store"

  @consultarProductosPorCategoriaElectronics
  Scenario: Consultar todos los productos de la categoría "electronics"
    When realizo una petición GET a "/products/category/electronics"
    Then debería recibir una respuesta con código 200
    And la respuesta debería contener una lista de productos
    And todos los productos en la lista deberían tener la categoría "electronics"

  @consultarProductoEspecifico
  Scenario: Consultar los datos de un producto específico
    When realizo una petición GETID a "/products/1"
    Then debería recibir una respuesta con código 200
    And la respuesta debería contener los detalles del producto con ID 1

  @crearProducto
  Scenario Outline: Crear un nuevo producto
    When realizo una petición POST a "/products" con los siguientes datos:
      | title   | price   | description   | category   | image   |
      | <title> | <price> | <description> | <category> | <image> |
    Then debería recibir una respuesta con código 200
    And la respuesta debería contener los detalles del producto creado
      | title   | price   | description   | category   | image   |
      | <title> | <price> | <description> | <category> | <image> |
    And el producto debería tener un ID asignado

    Examples: Table
      | title      | price | description        | category | image                                                   |
      | Nuevo Prod | 99.99 | Producto de prueba | test     | https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg |

  @actualizarProducto
  Scenario Outline: Actualizar la imagen de un producto creado
    When realizo una petición PATCH a "/products/21" con los siguientes datos:
      | imagen   |
      | <imagen> |
    Then debería recibir una respuesta con código 200
    And la imagen del producto debería ser "<imagen>"

    Examples: Table
      | imagen                       |
      | http://ejemplo.com/nueva-img |
      