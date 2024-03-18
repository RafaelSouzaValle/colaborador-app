CREATE TABLE IF NOT EXISTS Colaborador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    senha_score INT DEFAULT 0
);

CREATE UNIQUE INDEX idx_nome ON Colaborador (nome);
