document.getElementById('Parametros').addEventListener('submit', function(event) { 
    event.preventDefault();     // Evitar el envío del formulario por defecto 
    // Obtener los valores del formulario 
    var valor = document.getElementById('c').value; 
     // Llamar a la función. Descomenta/comenta uno cuando quiere usar el otro
    //llamadaPath(valor);
    llamadaForm(valor);
  });
  function llamadaPath(valor) {
     // Construir la URL con el PathParam 
     var url = `http://localhost:8080/PruebaRest/rest/Conversores/M2P/${valor}`;
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

  function llamadaForm(valor) {
    // Construir la URL con el PathParam 
    var url = `http://localhost:8080/PruebaRest/rest/Conversores/M2P/`;
    // Crearmos una petición basada en formularios
    const formData = new URLSearchParams(); 
    formData.append('c', valor); 
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
