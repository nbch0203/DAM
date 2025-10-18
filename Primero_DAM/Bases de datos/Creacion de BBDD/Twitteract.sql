USE twitter;

CREATE TABLE MeGusta (
    id_usuario INT NOT NULL,
    id_tweet INT NOT NULL,
    fecha_like TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario, id_tweet),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_tweet) REFERENCES Tweet(id_tweet) ON DELETE CASCADE
);