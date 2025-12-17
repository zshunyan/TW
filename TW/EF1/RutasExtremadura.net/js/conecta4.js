var nfilas = 6; // Número de filas
var ncol = 7; // Número de columnas
var turno = "rojo"; // Turno inicial
var matriz = []; // Matriz del tablero

function iniciarMatriz() {
    matriz = [];
    for (var i = 0; i < nfilas; i++) {
        matriz[i] = [];
        for (var j = 0; j < ncol; j++) {
            matriz[i][j] = 0; 
        }
    }
}

function mover(event) {
    var col = event.target.dataset.col; // Obtiene la columna de la celda clicada
    for (var i = nfilas - 1; i >= 0; i--) {
        if (matriz[i][col] === 0) { // Verifica si la celda está vacía
            matriz[i][col] = turno; // Coloca la ficha del jugador
            actualizarTablero();
            if (comprobar()) {
                setTimeout(() => {
                    alert('¡Jugador ' + (turno === "rojo" ? "rojo" : "verde") + ' ha ganado!');
                    reiniciar();
                }, 100); // Retrasa el reinicio para que se vean las celdas ganadoras
            } else {
                cambiarTurno();
            }
            break;
        }
    }
}

function cambiarTurno() {
    turno = (turno === "rojo") ? "verde" : "rojo"; // Cambia el turno entre "rojo" y "verde"
}

function crearTablero() {
    var tablero = document.getElementById('game-board');
    tablero.innerHTML = ''; 
    for (var i = 0; i < nfilas; i++) {
        var fila = document.createElement('tr');
        for (var j = 0; j < ncol; j++) {
            var celda = document.createElement('td');
            celda.dataset.col = j;
            celda.addEventListener('click', mover);
            fila.appendChild(celda);
        }
        tablero.appendChild(fila);
    }
}

function actualizarTablero() {
    var tablero = document.getElementById('game-board');
    for (var i = 0; i < nfilas; i++) {
        for (var j = 0; j < ncol; j++) {
            var celda = tablero.rows[i].cells[j];
            celda.style.backgroundColor = matriz[i][j] === "rojo" ? "red" : matriz[i][j] === "verde" ? "green" : "white";
        }
    }
}

function comprobar() {
    // Comprueba las condiciones de victoria (horizontal, vertical y diagonal)
    for (var i = 0; i < nfilas; i++) {
        for (var j = 0; j < ncol; j++) {
            if (matriz[i][j] !== 0) {
                // Horizontal
                if (j + 3 < ncol && matriz[i][j] === matriz[i][j + 1] && matriz[i][j] === matriz[i][j + 2] && matriz[i][j] === matriz[i][j + 3]) {
                    pintarGanador([[i, j], [i, j + 1], [i, j + 2], [i, j + 3]]);
                    return true;
                }
                // Vertical
                if (i + 3 < nfilas && matriz[i][j] === matriz[i + 1][j] && matriz[i][j] === matriz[i + 2][j] && matriz[i][j] === matriz[i + 3][j]) {
                    pintarGanador([[i, j], [i + 1, j], [i + 2, j], [i + 3, j]]);
                    return true;
                }
                // Diagonal \
                if (i + 3 < nfilas && j + 3 < ncol && matriz[i][j] === matriz[i + 1][j + 1] && matriz[i][j] === matriz[i + 2][j + 2] && matriz[i][j] === matriz[i + 3][j + 3]) {
                    pintarGanador([[i, j], [i + 1, j + 1], [i + 2, j + 2], [i + 3, j + 3]]);
                    return true;
                }
                // Diagonal /
                if (i + 3 < nfilas && j - 3 >= 0 && matriz[i][j] === matriz[i + 1][j - 1] && matriz[i][j] === matriz[i + 2][j - 2] && matriz[i][j] === matriz[i + 3][j - 3]) {
                    pintarGanador([[i, j], [i + 1, j - 1], [i + 2, j - 2], [i + 3, j - 3]]);
                    return true;
                }
            }
        }
    }
    return false; // No hay ganador
}

function pintarGanador(celdas) {
    var tablero = document.getElementById('game-board');
    celdas.forEach(([fila, col]) => {
        tablero.rows[fila].cells[col].style.backgroundColor = "yellow"; 
    });
}

function reiniciar() {
    iniciarMatriz(); // Reinicia la matriz del juego
    turno = "rojo"; // Reinicia el turno al jugador "rojo"
    crearTablero(); // Regenera el tablero visual
}