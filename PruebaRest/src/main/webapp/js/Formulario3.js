document.getElementById('planeta').addEventListener('submit', function(event) {
	event.preventDefault(); // Evitar el envío del formulario por defecto
	var valor = document.getElementById('valor').value;
	var valor1 = document.getElementById('valor1').value;
	var valor2 = document.getElementById('valor2').value;

	//llamadaPath(valor);
	llamadaForm(valor);
	//llamadaPostJSON(valor, valor1, valor2);
});
function llamadaPath(valor) {
	// URL del servicio REST
	var url = `http://localhost:8080/PruebaRest/rest/Planets/${valor}`;

	var options = {
		method: 'GET' // or 'POST', 'PUT', etc.
	};
	// Realizar la petición POST al servicio REST para iniciar sesión
	fetch(url, options)
		.then(response => {
			// Verificar si la respuesta es exitosa
			if (!response.ok) {
				throw new Error('Error en la respuesta del servidor');
			}
			return response.json(); // Usamos .text() en lugar de .json() porque la respuesta es un string
		})
		.then(data => {
			//-->Recuperar una planeta
			const nombre = data.name;
			document.getElementById('mensaje').textContent = nombre;
			//-->Recuperar el nombre de todo planeta
			//const planetas = Object.values(data);
			//const nombres = planetas.map(p => p.name);
			//document.getElementById('mensaje').textContent = nombres.join(', ');
		})
		.catch(error => {
			document.getElementById('mensaje').innerText = 'Error: ' + error.message;
		});
}
function llamadaForm(valor) {
	// Construir la URL con el PathParam
	var url = `http://localhost:8080/PruebaRest/rest/Planets/`;
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
			if (!response.ok) { throw new Error('Error en la respuesta del servidor'); }
			return response.text(); // Usamos .text() en lugar de .json() porque la respuesta es un string

		})
		.then(data => {
			// Fijamos los datos como cookie para la siguientes llamadas
			document.getElementById('mensaje').innerText = data;
		})
		.catch(error => {
			document.getElementById('mensaje').innerText = 'Error: ' + error.message;
		});
}
function llamadaPostJSON(valor, valor1, valor2) {
	// URL del servicio REST
	var url = `http://localhost:8080/PruebaRest/rest/Planets/`;
	var data = {
		id: valor,
		name: valor1,
		radius: valor2
	};

	var options = {
	        method: 'POST',
	        headers: { 'Content-Type': 'application/json' },
	        body: JSON.stringify(data)
	    };

	// Realizar la petición POST al servicio REST para iniciar sesión
	fetch(url, options)
		.then(response => {
			// Verificar si la respuesta es exitosa
			if (!response.ok) {
				throw new Error('Error en la respuesta del servidor');
			}
			return response.json(); // Usamos .text() en lugar de .json() porque la respuesta es un string
		})
		.then(data => {
			//-->Recuperar una planeta
			const nombre = data.name;
			document.getElementById('mensaje').textContent = nombre;
			//-->Recuperar el nombre de todo planeta
			//const planetas = Object.values(data);
			//const nombres = planetas.map(p => p.name);
			//document.getElementById('mensaje').textContent = nombres.join(', ');
		})
		.catch(error => {
			document.getElementById('mensaje').innerText = 'Error: ' + error.message;
		});
}