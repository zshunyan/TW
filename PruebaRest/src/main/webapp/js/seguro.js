fetch(`http://localhost:8080/PruebaRest/rest/datos/`, {
    method: 'GET',
    headers: {
        'Authorization': 'Basic ' + btoa('admin:admin')
    }
})
.then(response => {response.json();})
.then(data => {console.log(data);})
.catch(error => {console.error('Error:', error);});