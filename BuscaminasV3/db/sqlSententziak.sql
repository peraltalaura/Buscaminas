.open \\peral\\Documents\\Desarrollo de aplicaciones multiplataforma-1\\Programazioa\\Buscaminas\\BuscaminasV3\\db\\ranking.db
DROP TABLE Rankinga;

CREATE TABLE Rankinga(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    izena VARCHAR(25),
    denbora INTEGER(),
    laukiak INTEGER
);