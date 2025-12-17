function showSection(sectionId) {
    var sections = document.querySelectorAll('main section');
    sections.forEach(function(section) {
        section.style.display = 'none';
    });

    var targetSection = document.getElementById(sectionId);
    if (targetSection) {
        targetSection.style.display = 'block';
    } else {
        console.warn(`Section with ID "${sectionId}" not found.`);
        document.getElementById('rutas').style.display = 'block'; // Default to 'rutas'
    }
}
