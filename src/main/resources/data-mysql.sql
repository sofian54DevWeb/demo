INSERT INTO pays (id, nom)
VALUES
(1, 'France'),
(2, 'Allemagne'),
(3, 'Belgique');

INSERT INTO utilisateur (nom,prenom,pays_id)
VALUES
('BENCHIHEUB','Sofian',1),
('TRAN','David',1),
('DOE','john',3);

INSERT INTO `competence` (`id`, `nom`) VALUES
(1, 'JAVA'),
(2, 'PHP'),
(3, 'HTML'),
(4, 'CSS');

INSERT INTO `competence_utilisateur` (`utilisateur_id`, `competence_id`) VALUES
(1, 3),
(3, 3),
(1, 4),
(2, 4);