@SmokTest
Feature: Gestión de cuentas de usuario
  Como usuario del sitio web
  Quiero poder registrarme, iniciar sesión y restablecer mi contraseña
  Para acceder y gestionar mi cuenta de forma segura

  @Register
  Scenario Outline: Usuario completa el formulario de registro
    Given que estoy en la página de registro
    When ingreso los datos del formulario válidos
      | firstName   | lastName   | email   | telephone   | password   | confirmPassword   |
      | <firstName> | <lastName> | <email> | <telephone> | <password> | <confirmPassword> |
    Then debería ver un mensaje de confirmación de registro exitoso "Congratulations! Your new account has been successfully created!"

    Examples: Tabla de información
      | firstName | lastName | email                 | telephone  | password  | confirmPassword |
      | Daniel    | Marin    | prueba_tecnica_20253@gmail.com | 3007184558 | 123456789 | 123456789       |

  @Login
  Scenario Outline: Usuario inicia sesión correctamente
    Given que estoy en la página de login
    When ingreso las credenciales correctamente "<usuario>" "<password>"
    Then debería ver My Account

    Examples: Tabla Usuario
      | usuario               | password  |
      | prueba12732@gmail.com | 123456789 |
