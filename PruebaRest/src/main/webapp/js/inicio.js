
document.getElementById('prueba').addEventListener('click', function(event) {
    event.preventDefault(); 
    llamada();
 });
 function llamada() {
    // URL del servicio REST 
    var url = 'http://localhost:8080/PruebaRest/rest/HolaMundo/';
    var options = {
        method: 'GET' // or 'POST', 'PUT', etc.        
    };
    // Realizar la petición GET al servicio REST para iniciar sesión
    fetch(url,options)
        .then(response => {
            // Verificar si la respuesta es exitosa
            if (!response.ok) {
                throw new Error('Error en la respuesta del servidor');
            }
            // Usamos .text() en lugar de .json() porque la respuesta es un string 
        return response.text(); 
        })
        .then(data => {
 //Hay datos 
document.getElementById('mensaje').innerText = data;
        })
        .catch(error => {
            // Manejar errores
            document.getElementById('mensaje').innerText = 'Error: ' + error.message;
        });
 }