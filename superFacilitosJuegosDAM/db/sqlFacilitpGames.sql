
create schema if not exists bd_MyGames;
/*Drop schema if exists bd_MyGames;
DROP DATABASE if exists bd_MyGames;*/

use bd_MyGames;

create table if not exists Games(
 id int Primary key,
 name varchar(40),
 description text,
 release_date date,
 rating int,
 metacritic_score int,
 background_image_url varchar(60)
);


create table if not exists Usuarios(
 Id int Primary key AUTO_INCREMENT,
 Nombre varchar(25),
 Apellidos varchar(45),
 fecha_Nacimiento varchar(80),
 Apodo varchar(25),
 Email varchar(50),
 Contrasena varchar(40)
);

create table if not exists GamesPropios(
    game_id INT,
    User_id INT,
    PRIMARY KEY (game_id, User_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (User_id) REFERENCES Usuarios(Id) ON DELETE CASCADE
);

create table if not exists GamesPendientes(
    game_id INT,
    User_id INT,
    PRIMARY KEY (game_id, User_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (User_id) REFERENCES Usuarios(Id) ON DELETE CASCADE
);

create table if not exists GamesTerminados(
    game_id INT,
    User_id INT,
    PRIMARY KEY (game_id, User_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (User_id) REFERENCES Usuarios(Id) ON DELETE CASCADE
);

create table if not exists GamesPropios(
    game_id INT,
    User_id INT,
    PRIMARY KEY (game_id, User_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (User_id) REFERENCES Usuarios(Id) ON DELETE CASCADE
);

create table if not exists Platforms(
id int primary key,
name varchar(40)
);

create table if not exists Generos(
id int primary key,
name varchar(40)
);

create table if not exists Developers(
id int primary key,
name varchar(40)
);

create table if not exists Publishers(
id int primary key,
name varchar(40)
);

create table if not exists Tags(
id int primary key,
name varchar(40)
);

create table if not exists Store(
id int primary key,
name varchar(40)
);

CREATE TABLE if not exists Game_Platforms (
    game_id INT,
    platform_id INT,
    PRIMARY KEY (game_id, platform_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (platform_id) REFERENCES Platforms(id) ON DELETE CASCADE
);

CREATE TABLE if not exists Game_Genres (
    game_id INT,
    genre_id INT,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES Generos(id) ON DELETE CASCADE
);

CREATE TABLE if not exists Game_Developers (
    game_id INT,
    developer_id INT,
    PRIMARY KEY (game_id, developer_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (developer_id) REFERENCES Developers(id) ON DELETE CASCADE
);

CREATE TABLE if not exists Game_Publishers (
    game_id INT,
    publisher_id INT,
    PRIMARY KEY (game_id, publisher_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (publisher_id) REFERENCES Publishers(id) ON DELETE CASCADE
);

CREATE TABLE if not exists Game_Tags (
    game_id INT,
    tag_id INT,
    PRIMARY KEY (game_id, tag_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tags(id) ON DELETE CASCADE
);

CREATE TABLE if not exists Game_Stores (
    game_id INT,
    store_id INT,
    PRIMARY KEY (game_id, store_id),
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES Store(id) ON DELETE CASCADE  -- Asegúrate de que 'Store' sea singular

);
CREATE TABLE `biblioteca` (
   `idUsuario` int NOT NULL,
   `idGame` int NOT NULL,
   `imagen` varchar(100) DEFAULT NULL,
   `titulos` varchar(45) DEFAULT NULL,
   `estado` varchar(1) DEFAULT NULL,
   `comentario` varchar(45) DEFAULT NULL,
   `nota` int DEFAULT NULL,
   `fechaJugado` date DEFAULT NULL,
   PRIMARY KEY (`idUsuario`,`idGame`),
   CONSTRAINT `Id` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`Id`) ON DELETE CASCADE
 ) 

CREATE TABLE IF NOT EXISTS Games_Aniadidos (
    title VARCHAR(255) primary key,
    releaseDate varchar(40),
    user varchar(40),
    description TEXT,
    imageUrl VARCHAR(255),
    plataforma VARCHAR(255)
);

INSERT INTO Usuarios (Id, Nombre, Apellidos, fecha_Nacimiento, Apodo, Email, Contrasena)
VALUES (1, 'Juan', 'Pérez', '1990-05-15', 'jperez', 'ej@gmail.com', '1234');
Select * from usuarios
