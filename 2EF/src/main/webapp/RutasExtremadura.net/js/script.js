function showSection(sectionId) {
    var sections = document.querySelectorAll('main section');
    sections.forEach(function(section) {
        section.style.display = 'none';
    });

    var targetSection = document.getElementById(sectionId);
    if (targetSection) {
        targetSection.style.display = 'block';
    } else {
        console.warn(`Sección con ID "${sectionId}" no encontrada.`);
        document.getElementById('rutas').style.display = 'block'; 
    }
}

function validateForm() {
    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var errorMessage = '';

    if (name === '') {
        errorMessage += 'El nombre es obligatorio.\n';
    }
    if (email === '') {
        errorMessage += 'El correo electrónico es obligatorio.\n';
    } else if (!validateEmail(email)) {
        errorMessage += 'El correo electrónico no es válido.\n';
    }

    if (errorMessage) {
        alert(errorMessage);
        return false;
    }
    return true;
}

function validateEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}