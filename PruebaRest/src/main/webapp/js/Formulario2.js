document.getElementById('Parametros').addEventListener('submit', function (event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto
    // Obtener los valores del formulario
    var op1 = document.getElementById('op1').value;
    var op2 = document.getElementById('op2').value;
    var op = document.getElementById('op').value;
    // Llamar a la función 
    //llamadaPath(op,op1,op2); 
    llamadaForm(op,op1,op2);
});

function llamadaPath(op,op1,op2) {
    // Construir la URL con el PathParam 
    var url = `http://localhost:8080/PruebaRest/rest/Calculadora/${op}/${op1}/${op2}`;
    // Crearmos una petición basada en formularios
    var options = {
        method: 'GET',
    }
    // Realizar la petición POST al servicio REST para iniciar sesión
    fetch(url, options)
        .then(response => {
            // Verificar si la respuesta es exitosa
            if (!response.ok) {throw new Error('Error en la respuesta del servidor'); }
 return response.text();  // Usamos .text() en lugar de .json() porque la respuesta es un string
            
        })
        .then(data => {
           // Fijamos los datos como cookie para la siguientes llamadas
          document.getElementById('mensaje').innerText = data;
        })
        .catch(error => {
            document.getElementById('mensaje').innerText = 'Error: ' + error.message;
        });
}

function llamadaForm(op,op1,op2) {
    // Construir la URL con el PathParam 
    var url = `http://localhost:8080/PruebaRest/rest/Calculadora/`;
    // Crearmos una petición basada en formularios
    const formData = new URLSearchParams(); 
    formData.append('op', op); 
    formData.append('op1', op1); 
    formData.append('op2', op2); 
    var options = { 
          method: 'POST', 
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, 
          body: formData.toString() // Convierte FormData a string }
    }
    // Realizar la petición POST al servicio REST para iniciar sesión
    fetch(url, options)
        .then(response => {
            // Verificar si la respuesta es exitosa
            if (!response.ok) {throw new Error('Error en la respuesta del servidor'); }
 return response.text();  // Usamos .text() en lugar de .json() porque la respuesta es un string
            
        })
        .then(data => {
           // Fijamos los datos como cookie para la siguientes llamadas
          document.getElementById('mensaje').innerText = data;
        })
        .catch(error => {
            document.getElementById('mensaje').innerText = 'Error: ' + error.message;
        });

}
