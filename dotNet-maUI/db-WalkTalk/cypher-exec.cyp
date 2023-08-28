GRANT CREATE CONSTRAINT ON DATABASE neo4j TO regularUsers
SHOW USER PRIVILEGES 






// Cria nova entidade ou nó chamado User (LABEL para agrupar nós similares)
// com as propriedades (DATA) no formato key: "value" em pares -[:RELATIONSHIP]
// o sinal -> cria uma relação com outro user e o segue

// Add CONSTRAINT pra enforçar regras so seu schema de dados

// add vars
CREATE (j:User {name: "@jeffmuniz"})-[r:FOLLOWS]->(n:User {name: "@neo4matrix"})
RETURN j,r,n

CREATE CONSTRAINT user_isbn IF NOT EXISTS
FOR (user:User) REQUIRE user.isbn IS UNIQUE



CREATE (j:User {name: "@alice" })-[r:SAYS]->(t:Tweet {
    text: "Hi Mom",
    created: "date (2023-06-24)"
})

MATCH (u:User {name: "@jeff"})-[FOLLOWS]->(:User)--(t:Tweet)
RETURN t.text




//DELETE STUFF
DROP CONSTRAINT book_isbn

