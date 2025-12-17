CREATE DATABASE tw;
CREATE USER 'tw'@'localhost' IDENTIFIED BY 'tw2425';
GRANT ALL PRIVILEGES ON tw.* TO 'tw'@'localhost';
FLUSH PRIVILEGES;

-- Crear tabla Usuario
use tw;
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('usuario', 'admin') DEFAULT 'usuario'
);

-- Crear tabla Rutas
CREATE TABLE Rutas (
    idRuta INT AUTO_INCREMENT PRIMARY KEY,
    pathImagen VARCHAR(255) NOT NULL,
    fechaIncorporacion DATE NOT NULL,
    maximoUsuario INT NOT NULL,
    dificultad INT CHECK(dificultad BETWEEN 1 AND 5),
    metros INT NOT NULL
);

-- Crear tabla Reservas
CREATE TABLE Reservas (
    idReserva INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    idRuta INT NOT NULL,
    fechaReserva DATE NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
    FOREIGN KEY (idRuta) REFERENCES Rutas(idRuta)
);

-- Crear tabla Valoraciones
CREATE TABLE Valoraciones (
    idValoracion INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    idRuta INT NOT NULL,
    puntuacion INT CHECK(puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id),
    FOREIGN KEY (idRuta) REFERENCES Rutas(idRuta)
);

-- Insertar datos iniciales
INSERT INTO Usuario (nombre, email, password, rol) VALUES
('admin', 'admin@example.com', 'admin123', 'admin'),
('Carlos García', 'carlos.garcia@example.com', 'password123', 'usuario'),
('María López', 'maria.lopez@example.com', 'password456', 'usuario'),
('Juan Martínez', 'juan.martinez@example.com', 'password789', 'usuario'),
('Ana Sánchez', 'ana.sanchez@example.com', 'password101', 'usuario'),
('Luis Fernández', 'luis.fernandez@example.com', 'password202', 'usuario'),
('Elena Gómez', 'elena.gomez@example.com', 'password303', 'usuario'),
('Pedro Díaz', 'pedro.diaz@example.com', 'password404', 'usuario'),
('Lucía Pérez', 'lucia.perez@example.com', 'password505', 'usuario'),
('Miguel Torres', 'miguel.torres@example.com', 'password606', 'usuario'),
('Isabel Ruiz', 'isabel.ruiz@example.com', 'password707', 'usuario');

INSERT INTO Rutas (pathImagen, fechaIncorporacion, maximoUsuario, dificultad, metros) VALUES
('RutasExtremadura.net/images/EspaciosNaturalesExtremadura.jpg', '2025-05-01', 25, 5, 7000),
('RutasExtremadura.net/images/Rutas-Patrimonio-Humanidad-de-Extremadura.jpg', '2025-05-02', 15, 4, 6000),
('RutasExtremadura.net/images/RUTAS-DE-LOS-EMBALSES.jpg', '2025-05-03', 10, 3, 3000),
('RutasExtremadura.net/images/Ruta-Valle-del-Jerte.jpg', '2025-05-04', 10, 2, 4500),
('RutasExtremadura.net/images/Ruta-de-la-Plata.jpg', '2025-05-05', 35, 5, 150000),
('RutasExtremadura.net/images/Embalses-del-rio-Tajo.jpg', '2025-05-06', 50, 4, 56000);

INSERT INTO Reservas (idUsuario, idRuta, fechaReserva) VALUES
(2, 1, '2025-05-10'),
(3, 2, '2025-05-11'),
(4, 3, '2025-05-12'),
(5, 4, '2025-05-13'),
(6, 5, '2025-05-14'),
(7, 6, '2025-05-15'),
(8, 1, '2025-05-16'),
(9, 2, '2025-05-17'),
(10, 3, '2025-05-18'),
(11, 4, '2025-05-19');


INSERT INTO Valoraciones (idUsuario, idRuta, puntuacion, comentario) VALUES
(2, 1, 5, 'Una experiencia increíble, muy recomendable.'),
(3, 2, 4, 'Muy buena ruta, aunque un poco difícil en algunas partes.'),
(4, 3, 3, 'La ruta estuvo bien, pero esperaba más vistas.'),
(5, 4, 5, 'Hermosa ruta, perfecta para una caminata relajante.'),
(6, 5, 4, 'Gran experiencia, aunque un poco larga.'),
(7, 6, 5, 'Impresionante, definitivamente volveré.'),
(8, 1, 4, 'Buena ruta, pero había mucha gente.'),
(9, 2, 3, 'No estuvo mal, pero he visto mejores.'),
(10, 3, 5, 'Excelente, me encantó cada momento.'),
(11, 4, 4, 'Muy buena, pero podría estar mejor señalizada.');

-- Verificar usuario
-- SELECT user, host FROM mysql.user WHERE user = 'tw';
-- Eliminar usuario
-- DROP USER 'tw'@'localhost';

-- DROP TABLE Reservas;
-- DROP TABLE Valoraciones;
-- DROP TABLE Usuario;
-- DROP TABLE Rutas;
-- DROP DATABASE tw;
SELECT * FROM Usuario;
-- SELECT * FROM Rutas;
-- SELECT * FROM Valoraciones;

-- DELETE FROM Rutas WHERE idRuta = 2;
-- DESCRIBE Usuario;

