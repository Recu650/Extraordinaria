DROP DATABASE IF EXISTS datoscoches;
CREATE DATABASE datoscoches; 
USE datoscoches;
CREATE TABLE propietarios (DNI VARCHAR(10), nombre VARCHAR(40), edad INTEGER, PRIMARY KEY (DNI));
CREATE TABLE coches (matricula VARCHAR(10) , marca VARCHAR(20), precio INTEGER, DNI VARCHAR (10), PRIMARY KEY (matricula), FOREIGN KEY (DNI) References propietarios(DNI));
INSERT INTO propietarios values('1A','Pepe',30);
INSERT INTO propietarios values('1B','Ana',40);
INSERT INTO propietarios values('1C','Maria',50); 
INSERT INTO coches values('1111KBF','Opel',1000,'1A');
INSERT INTO coches values('2222KCJ','Renault',2000,'1A');
INSERT INTO coches values('3333KLM', 'Seat', 3000,'1B');