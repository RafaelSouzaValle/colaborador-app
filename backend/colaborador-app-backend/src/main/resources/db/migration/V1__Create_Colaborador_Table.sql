CREATE TABLE Colaborador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cargo VARCHAR(255),
    senha VARCHAR(255),
    senha_score INT,
    complexidade VARCHAR(50) -- Adicionando a nova coluna "complexidade"
);
