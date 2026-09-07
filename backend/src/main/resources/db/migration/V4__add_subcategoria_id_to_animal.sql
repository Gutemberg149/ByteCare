-- Adicionar coluna subcategoria_id na tabela ANIMAL
ALTER TABLE ANIMAL ADD subcategoria_id NUMBER(19);

-- Criar índice para melhor performance
CREATE INDEX idx_animal_subcategoria ON ANIMAL(subcategoria_id);

-- Atualizar os registros existentes com um valor padrão (ex: 101 para Cães)
-- Isso é necessário porque a coluna é NOT NULL
UPDATE ANIMAL SET subcategoria_id = 101 WHERE subcategoria_id IS NULL;

-- Tornar a coluna NOT NULL após preencher os dados
ALTER TABLE ANIMAL MODIFY subcategoria_id NUMBER(19) NOT NULL;

-- Se existir uma tabela SUBCATEGORIA, adicionar FK
-- ALTER TABLE ANIMAL ADD CONSTRAINT fk_animal_subcategoria
--     FOREIGN KEY (subcategoria_id) REFERENCES SUBCATEGORIA(id);