#Gênero
INSERT INTO genero (descricao) VALUES ("Aventura");
INSERT INTO genero (descricao) VALUES ("FPS");
INSERT INTO genero (descricao) VALUES ("RPG");

#Game
INSERT INTO game (titulo, marca, plataforma, faixaetaria, fk_idgenero) VALUES ("Battlefield 4", "DICE", "PC", "18", 02);
INSERT INTO game (titulo, marca, plataforma, faixaetaria, fk_idgenero) VALUES ("Tomb Raider", "EA", "Xbox 980", "182", 01);
INSERT INTO game (titulo, marca, plataforma, faixaetaria, fk_idgenero) VALUES ("Skyrim", "Bethesta", "Pleisteichon", "18", 03);

#Usuario
INSERT INTO usuario (nome, email, senha, admin) VALUES ("Eudes", "eudes@otario.com", "12345", false);
INSERT INTO usuario (nome, email, senha, admin) VALUES ("Felipe", "felipe@bol.com", "12345", true);
INSERT INTO usuario (nome, email, senha, admin) VALUES ("Alexsandra", "alexsandra@gamil.com", "12345", false);
INSERT INTO usuario (idUsuario, nome, email, senha, admin) VALUES (123, "Alex", "alex@gamil.com", "12345", false);

#Pontuação
INSERT INTO pontuacao (fk_idusuario, fk_idgame, pontuacao, liberar) VALUES (01, 01, 1500, false);
INSERT INTO pontuacao (fk_idusuario, fk_idgame, pontuacao, liberar) VALUES (02, 02, 3500, true);
INSERT INTO pontuacao (fk_idusuario, fk_idgame, pontuacao, liberar) VALUES (03, 03, 5500, false);
INSERT INTO pontuacao (fk_idusuario, fk_idgame, pontuacao, liberar) VALUES (01, 03, 9500, true);