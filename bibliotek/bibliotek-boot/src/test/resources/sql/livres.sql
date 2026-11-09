INSERT INTO auteur (id, nom, prenom, nationalite) VALUES
    ('auteur-hugo', 'Hugo', 'Victor', 0)
;

INSERT INTO editeur (id, nom, pays) VALUES
    ('editeur-hachette', 'Hachette', 0)
;

INSERT INTO livre (id, nom, resume, publication, auteur_id, editeur_id, collection_id) VALUES
    ('livre-miserables', 'Les Misérables', 'Un grand classique', '1862-04-03', 'auteur-hugo', 'editeur-hachette', NULL),
    ('livre-notre-dame', 'Notre-Dame de Paris', NULL, '1831-01-14', 'auteur-hugo', 'editeur-hachette', NULL)
;
