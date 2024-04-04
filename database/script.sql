DROP TABLE CP1_EMPRESA CASCADE CONSTRAINTS;

DROP TABLE CP1_CONVENIO CASCADE CONSTRAINTS;

DROP TABLE CP1_UNIDADE CASCADE CONSTRAINTS;

DROP TABLE CP1_ENDERECO_UNIDADE CASCADE CONSTRAINTS;

DROP TABLE CP1_MEDICO_UNIDADE CASCADE CONSTRAINTS;

DROP TABLE CP1_MEDICO CASCADE CONSTRAINTS;

DROP TABLE CP1_CLIENTE CASCADE CONSTRAINTS;

DROP TABLE CP1_ENDERECO_CLIENTE CASCADE CONSTRAINTS;

DROP TABLE CP1_HISTORICO_SAUDE_CLIENTE CASCADE CONSTRAINTS;

DROP TABLE CP1_HISTORICO_HOSPITAL_CLIENTE CASCADE CONSTRAINTS;

DROP SEQUENCE CP1_EMPRESA_SEQ;

DROP SEQUENCE CP1_CONVENIO_SEQ;

DROP SEQUENCE CP1_UNIDADE_SEQ;

DROP SEQUENCE CP1_ENDERECO_UNIDADE_SEQ;

DROP SEQUENCE CP1_MEDICO_SEQ;

DROP SEQUENCE CP1_MEDICO_UNIDADE_SEQ;

DROP SEQUENCE CP1_CLIENTE_SEQ;

DROP SEQUENCE CP1_ENDERECO_CLIENTE_SEQ;

DROP SEQUENCE CP1_HISTORICO_SAUDE_CLIENTE_SEQ;

DROP SEQUENCE CP1_HISTORICO_HOSPITAL_CLIENTE_SEQ;

CREATE SEQUENCE CP1_EMPRESA_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_CONVENIO_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_UNIDADE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_ENDERECO_UNIDADE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_MEDICO_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_MEDICO_UNIDADE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_CLIENTE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_ENDERECO_CLIENTE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_HISTORICO_SAUDE_CLIENTE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE CP1_HISTORICO_HOSPITAL_CLIENTE_SEQ
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE CP1_EMPRESA (
    ID NUMBER DEFAULT CP1_EMPRESA_SEQ.NEXTVAL PRIMARY KEY,
    CNPJ VARCHAR(20),
    NOME VARCHAR(100),
    TIPO VARCHAR(100),
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100),
    AREA_ATUACAO VARCHAR(100)
);

CREATE TABLE CP1_CONVENIO (
    ID NUMBER DEFAULT CP1_CONVENIO_SEQ.NEXTVAL PRIMARY KEY,
    EMPRESA_ID NUMBER,
    NOME VARCHAR(100),
    VALOR FLOAT(53),
    TIPO_SERVICO VARCHAR(100),
    COBERTURA VARCHAR(100),
    CONTATO VARCHAR(100),
    VALIDADE DATE,
    FOREIGN KEY (EMPRESA_ID) REFERENCES CP1_EMPRESA(ID)
);

CREATE TABLE CP1_UNIDADE (
    ID NUMBER DEFAULT CP1_UNIDADE_SEQ.NEXTVAL PRIMARY KEY,
    EMPRESA_ID NUMBER,
    NOME VARCHAR(100),
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100),
    TIPO VARCHAR(100),
    CAPACIDADE NUMBER,
    ESPECIALIDADES VARCHAR(100),
    FOREIGN KEY (EMPRESA_ID) REFERENCES CP1_EMPRESA(ID)
);

CREATE TABLE CP1_ENDERECO_UNIDADE (
    ID NUMBER DEFAULT CP1_ENDERECO_UNIDADE_SEQ.NEXTVAL PRIMARY KEY,
    UNIDADE_ID NUMBER UNIQUE NOT NULL,
    RUA VARCHAR(100),
    NUMERO VARCHAR(10),
    CIDADE VARCHAR(100),
    ESTADO VARCHAR(100),
    CEP VARCHAR(20),
    FOREIGN KEY (UNIDADE_ID) REFERENCES CP1_UNIDADE(ID)
);

CREATE TABLE CP1_MEDICO (
    ID NUMBER DEFAULT CP1_MEDICO_SEQ.NEXTVAL PRIMARY KEY,
    NOME VARCHAR(100),
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100),
    ESPECIALIDADE VARCHAR(100),
    CRM VARCHAR(20)
);

CREATE TABLE CP1_MEDICO_UNIDADE (
    ID NUMBER DEFAULT CP1_MEDICO_UNIDADE_SEQ.NEXTVAL PRIMARY KEY,
    MEDICO_ID NUMBER NOT NULL,
    UNIDADE_ID NUMBER NOT NULL,
    HORARIO_ATENDIMENTO VARCHAR(100),
    FOREIGN KEY (MEDICO_ID) REFERENCES CP1_MEDICO(ID),
    FOREIGN KEY (UNIDADE_ID) REFERENCES CP1_UNIDADE(ID)
);

CREATE TABLE CP1_CLIENTE (
    ID NUMBER DEFAULT CP1_CLIENTE_SEQ.NEXTVAL PRIMARY KEY,
    RG VARCHAR(20),
    NOME VARCHAR(100),
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100),
    DATA_NASCIMENTO DATE,
    CPF VARCHAR(20),
    ESTADO_CIVIL VARCHAR(20) CHECK (ESTADO_CIVIL IN ('Solteiro', 'Casado', 'Divorciado', 'Viúvo')),
    CONVENIO_ID NUMBER,
    FOREIGN KEY (CONVENIO_ID) REFERENCES CP1_CONVENIO(ID)
);

CREATE TABLE CP1_ENDERECO_CLIENTE (
    ID NUMBER DEFAULT CP1_ENDERECO_CLIENTE_SEQ.NEXTVAL PRIMARY KEY,
    CLIENTE_ID NUMBER UNIQUE NOT NULL,
    RUA VARCHAR(100),
    NUMERO VARCHAR(10),
    CIDADE VARCHAR(100),
    ESTADO VARCHAR(100),
    CEP VARCHAR(20),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CP1_CLIENTE(ID)
);

CREATE TABLE CP1_HISTORICO_SAUDE_CLIENTE (
    ID NUMBER DEFAULT CP1_HISTORICO_SAUDE_CLIENTE_SEQ.NEXTVAL PRIMARY KEY,
    CLIENTE_ID NUMBER UNIQUE NOT NULL,
    DATA_REGISTRO DATE,
    FUMA NUMBER(1) CHECK (FUMA IN (0, 1)),
    DOENCA_PRINCIPAL VARCHAR(100),
    HISTORICO_FAMILIAR VARCHAR(100),
    ALERGIAS VARCHAR(100),
    OBSERVACOES VARCHAR(100),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CP1_CLIENTE(ID)
);

CREATE TABLE CP1_HISTORICO_HOSPITAL_CLIENTE (
    ID NUMBER DEFAULT CP1_HISTORICO_HOSPITAL_CLIENTE_SEQ.NEXTVAL PRIMARY KEY,
    CLIENTE_ID NUMBER UNIQUE NOT NULL,
    DATA_REGISTRO DATE,
    HISTORICO_MEDICO VARCHAR(100),
    EXAMES_REALIZADOS VARCHAR(100),
    MEDICAMENTOS_PRESCRITOS VARCHAR(100),
    OBSERVACOES VARCHAR(100),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CP1_CLIENTE(ID)
);

INSERT INTO CP1_EMPRESA (
    CNPJ,
    NOME,
    TIPO,
    TELEFONE,
    EMAIL,
    AREA_ATUACAO
) VALUES (
    '12345678901234',
    'Empresa X',
    'Saúde',
    '11223344',
    'empresa.x@email.com',
    'Atendimento médico'
);

INSERT INTO CP1_EMPRESA (
    CNPJ,
    NOME,
    TIPO,
    TELEFONE,
    EMAIL,
    AREA_ATUACAO
) VALUES (
    '98765432109876',
    'Empresa Y',
    'Saúde',
    '55443322',
    'empresa.y@email.com',
    'Atendimento médico geral'
);

INSERT INTO CP1_EMPRESA (
    CNPJ,
    NOME,
    TIPO,
    TELEFONE,
    EMAIL,
    AREA_ATUACAO
) VALUES (
    '98765432101234',
    'Empresa Z',
    'Saúde',
    '55443311',
    'empresa.y@email.com',
    'Atendimento médico pediatrico'
);

INSERT INTO CP1_EMPRESA (
    CNPJ,
    NOME,
    TIPO,
    TELEFONE,
    EMAIL,
    AREA_ATUACAO
) VALUES (
    '98765432101235',
    'Empresa G',
    'Saúde',
    '55444411',
    'empresa.y@email.com',
    'Atendimento médico'
);

INSERT INTO CP1_EMPRESA (
    CNPJ,
    NOME,
    TIPO,
    TELEFONE,
    EMAIL,
    AREA_ATUACAO
) VALUES (
    '98765432102222',
    'Empresa H',
    'Saúde',
    '88443311',
    'empresa.y@email.com',
    'Atendimento médico geral'
);

INSERT INTO CP1_CONVENIO (
    EMPRESA_ID,
    NOME,
    VALOR,
    TIPO_SERVICO,
    COBERTURA,
    CONTATO,
    VALIDADE
) VALUES (
    1,
    'Convenio A',
    500.00,
    'Consulta Médica',
    'Consultas e exames básicos',
    'contato@convenioa.com',
    TO_DATE('2025-12-31', 'YYYY-MM-DD')
);

INSERT INTO CP1_CONVENIO (
    EMPRESA_ID,
    NOME,
    VALOR,
    TIPO_SERVICO,
    COBERTURA,
    CONTATO,
    VALIDADE
) VALUES (
    2,
    'Convenio B',
    800.00,
    'Consulta e Exames',
    'Consultas, exames básicos e especializados',
    'contato@conveniob.com',
    TO_DATE('2026-06-30', 'YYYY-MM-DD')
);

INSERT INTO CP1_CONVENIO (
    EMPRESA_ID,
    NOME,
    VALOR,
    TIPO_SERVICO,
    COBERTURA,
    CONTATO,
    VALIDADE
) VALUES (
    2,
    'Convenio B',
    600.00,
    'Consulta e Exames',
    'Consultas, exames básicos e especializados',
    'contato@conveniob.com',
    TO_DATE('2026-06-30', 'YYYY-MM-DD')
);

INSERT INTO CP1_CONVENIO (
    EMPRESA_ID,
    NOME,
    VALOR,
    TIPO_SERVICO,
    COBERTURA,
    CONTATO,
    VALIDADE
) VALUES (
    3,
    'Convenio C',
    700.00,
    'Cirurgia',
    'Procedimentos cirúrgicos e acompanhamento pós-operatório',
    'contato@convenioc.com',
    TO_DATE('2027-01-15', 'YYYY-MM-DD')
);

INSERT INTO CP1_CONVENIO (
    EMPRESA_ID,
    NOME,
    VALOR,
    TIPO_SERVICO,
    COBERTURA,
    CONTATO,
    VALIDADE
) VALUES (
    4,
    'Convenio D',
    800.00,
    'Fisioterapia',
    'Sessões de fisioterapia e acompanhamento especializado',
    'contato@conveniod.com',
    TO_DATE('2026-12-31', 'YYYY-MM-DD')
);

INSERT INTO CP1_UNIDADE (
    EMPRESA_ID,
    NOME,
    TELEFONE,
    EMAIL,
    TIPO,
    CAPACIDADE,
    ESPECIALIDADES
) VALUES (
    1,
    'Unidade A',
    '11223344',
    'unidade.a@email.com',
    'Hospital',
    100,
    'Cardiologia, Ortopedia'
);

INSERT INTO CP1_UNIDADE (
    EMPRESA_ID,
    NOME,
    TELEFONE,
    EMAIL,
    TIPO,
    CAPACIDADE,
    ESPECIALIDADES
) VALUES (
    2,
    'Unidade B',
    '55443322',
    'unidade.b@email.com',
    'Hospital',
    150,
    'Clínica geral'
);

INSERT INTO CP1_UNIDADE (
    EMPRESA_ID,
    NOME,
    TELEFONE,
    EMAIL,
    TIPO,
    CAPACIDADE,
    ESPECIALIDADES
) VALUES (
    2,
    'Unidade B',
    '22334455',
    'unidade.b@email.com',
    'Clínica',
    50,
    'Pediatria, Ginecologia'
);

INSERT INTO CP1_UNIDADE (
    EMPRESA_ID,
    NOME,
    TELEFONE,
    EMAIL,
    TIPO,
    CAPACIDADE,
    ESPECIALIDADES
) VALUES (
    3,
    'Unidade C',
    '33445566',
    'unidade.c@email.com',
    'Consultório',
    20,
    'Dermatologia, Oftalmologia'
);

INSERT INTO CP1_UNIDADE (
    EMPRESA_ID,
    NOME,
    TELEFONE,
    EMAIL,
    TIPO,
    CAPACIDADE,
    ESPECIALIDADES
) VALUES (
    4,
    'Unidade D',
    '44556677',
    'unidade.d@email.com',
    'Laboratório',
    30,
    'Análises Clínicas, Patologia'
);

INSERT INTO CP1_ENDERECO_UNIDADE (
    UNIDADE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    1,
    'Rua da Unidade A',
    '789',
    'Cidade Unidade',
    'Estado Unidade',
    '54321-098'
);

INSERT INTO CP1_ENDERECO_UNIDADE (
    UNIDADE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    2,
    'Rua da Unidade B',
    '101',
    'Cidade Unidade B',
    'Estado Unidade B',
    '98765-432'
);

INSERT INTO CP1_ENDERECO_UNIDADE (
    UNIDADE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    3,
    'Avenida Principal',
    '200',
    'Cidade da Unidade C',
    'Estado da Unidade C',
    '12345-678'
);

INSERT INTO CP1_ENDERECO_UNIDADE (
    UNIDADE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    4,
    'Rua da Unidade D',
    '50',
    'Cidade Unidade D',
    'Estado Unidade D',
    '54321-876'
);

INSERT INTO CP1_ENDERECO_UNIDADE (
    UNIDADE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    5,
    'Avenida Central',
    '300',
    'Cidade da Unidade E',
    'Estado da Unidade E',
    '13579-246'
);

INSERT INTO CP1_MEDICO (
    NOME,
    TELEFONE,
    EMAIL,
    ESPECIALIDADE,
    CRM
) VALUES (
    'Dr. José Santos',
    '11223344',
    'jose.santos@email.com',
    'Cardiologia',
    '12345'
);

INSERT INTO CP1_MEDICO (
    NOME,
    TELEFONE,
    EMAIL,
    ESPECIALIDADE,
    CRM
) VALUES (
    'Dra. Ana Oliveira',
    '55443322',
    'ana.oliveira@email.com',
    'Clínica Geral',
    '54321'
);

INSERT INTO CP1_MEDICO (
    NOME,
    TELEFONE,
    EMAIL,
    ESPECIALIDADE,
    CRM
) VALUES (
    'Dr. Marcos Santos',
    '99887766',
    'marcos.santos@email.com',
    'Ortopedia',
    '12345'
);

INSERT INTO CP1_MEDICO (
    NOME,
    TELEFONE,
    EMAIL,
    ESPECIALIDADE,
    CRM
) VALUES (
    'Dra. Carolina Mendes',
    '77665544',
    'carolina.mendes@email.com',
    'Pediatria',
    '54321'
);

INSERT INTO CP1_MEDICO (
    NOME,
    TELEFONE,
    EMAIL,
    ESPECIALIDADE,
    CRM
) VALUES (
    'Dr. Pedro Alves',
    '33221100',
    'pedro.alves@email.com',
    'Cardiologia',
    '67890'
);

INSERT INTO CP1_MEDICO_UNIDADE (
    MEDICO_ID,
    UNIDADE_ID,
    HORARIO_ATENDIMENTO
) VALUES (
    1,
    1,
    'Segunda a Sexta, 08:00 - 17:00'
);

INSERT INTO CP1_MEDICO_UNIDADE (
    MEDICO_ID,
    UNIDADE_ID,
    HORARIO_ATENDIMENTO
) VALUES (
    2,
    2,
    'Segunda a Sexta, 09:00 - 18:00'
);

INSERT INTO CP1_MEDICO_UNIDADE (
    MEDICO_ID,
    UNIDADE_ID,
    HORARIO_ATENDIMENTO
) VALUES (
    3,
    3,
    'Segunda a Sexta, 08:00 - 17:00'
);

INSERT INTO CP1_MEDICO_UNIDADE (
    MEDICO_ID,
    UNIDADE_ID,
    HORARIO_ATENDIMENTO
) VALUES (
    4,
    4,
    'Segunda a Sábado, 10:00 - 20:00'
);

INSERT INTO CP1_MEDICO_UNIDADE (
    MEDICO_ID,
    UNIDADE_ID,
    HORARIO_ATENDIMENTO
) VALUES (
    5,
    5,
    'Segunda a Quinta, 07:00 - 16:00; Sexta, 07:00 - 12:00'
);

INSERT INTO CP1_CLIENTE (
    RG,
    NOME,
    TELEFONE,
    EMAIL,
    DATA_NASCIMENTO,
    CPF,
    ESTADO_CIVIL,
    CONVENIO_ID
) VALUES (
    '123456789',
    'João da Silva',
    '11223344',
    'joao.silva@email.com',
    TO_DATE('1990-01-01', 'YYYY-MM-DD'),
    '123.456.789-00',
    'Solteiro',
    1
);

INSERT INTO CP1_CLIENTE (
    RG,
    NOME,
    TELEFONE,
    EMAIL,
    DATA_NASCIMENTO,
    CPF,
    ESTADO_CIVIL,
    CONVENIO_ID
) VALUES (
    '987654321',
    'Maria Souza',
    '55443322',
    'maria.souza@email.com',
    TO_DATE('1985-05-10', 'YYYY-MM-DD'),
    '987.654.321-00',
    'Casado',
    2
);

INSERT INTO CP1_CLIENTE (
    RG,
    NOME,
    TELEFONE,
    EMAIL,
    DATA_NASCIMENTO,
    CPF,
    ESTADO_CIVIL,
    CONVENIO_ID
) VALUES (
    '123456789',
    'João Silva',
    '99887766',
    'joao.silva@email.com',
    TO_DATE('1990-08-20', 'YYYY-MM-DD'),
    '123.456.789-00',
    'Solteiro',
    1
);

INSERT INTO CP1_CLIENTE (
    RG,
    NOME,
    TELEFONE,
    EMAIL,
    DATA_NASCIMENTO,
    CPF,
    ESTADO_CIVIL,
    CONVENIO_ID
) VALUES (
    '234567890',
    'Ana Oliveira',
    '77665544',
    'ana.oliveira@email.com',
    TO_DATE('1980-03-15', 'YYYY-MM-DD'),
    '234.567.890-00',
    'Casado',
    3
);

INSERT INTO CP1_CLIENTE (
    RG,
    NOME,
    TELEFONE,
    EMAIL,
    DATA_NASCIMENTO,
    CPF,
    ESTADO_CIVIL,
    CONVENIO_ID
) VALUES (
    '345678901',
    'Carlos Pereira',
    '33221100',
    'carlos.pereira@email.com',
    TO_DATE('1975-12-05', 'YYYY-MM-DD'),
    '345.678.901-00',
    'Divorciado',
    4
);

INSERT INTO CP1_ENDERECO_CLIENTE (
    CLIENTE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    1,
    'Rua dos Clientes',
    '123',
    'Cidade Cliente',
    'Estado Cliente',
    '12345-678'
);

INSERT INTO CP1_ENDERECO_CLIENTE (
    CLIENTE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    2,
    'Avenida das Flores',
    '456',
    'Cidade dos Clientes',
    'Estado dos Clientes',
    '54321-098'
);

INSERT INTO CP1_ENDERECO_CLIENTE (
    CLIENTE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    3,
    'Rua das Palmeiras',
    '789',
    'Cidade dos Clientes',
    'Estado dos Clientes',
    '12345-678'
);

INSERT INTO CP1_ENDERECO_CLIENTE (
    CLIENTE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    4,
    'Avenida Central',
    '101',
    'Cidade Nova',
    'Estado do Norte',
    '87654-321'
);

INSERT INTO CP1_ENDERECO_CLIENTE (
    CLIENTE_ID,
    RUA,
    NUMERO,
    CIDADE,
    ESTADO,
    CEP
) VALUES (
    5,
    'Rua dos Girassóis',
    '222',
    'Cidade dos Clientes',
    'Estado dos Clientes',
    '13579-246'
);

INSERT INTO CP1_HISTORICO_SAUDE_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    FUMA,
    DOENCA_PRINCIPAL,
    HISTORICO_FAMILIAR,
    ALERGIAS,
    OBSERVACOES
) VALUES (
    1,
    TO_DATE('2020-01-01', 'YYYY-MM-DD'),
    0,
    'Hipertensão',
    'Nenhum histórico relevante',
    'Nenhuma alergia conhecida',
    'Sem observações'
);

INSERT INTO CP1_HISTORICO_SAUDE_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    FUMA,
    DOENCA_PRINCIPAL,
    HISTORICO_FAMILIAR,
    ALERGIAS,
    OBSERVACOES
) VALUES (
    2,
    TO_DATE('2021-03-15', 'YYYY-MM-DD'),
    1,
    'Diabetes',
    'Histórico de hipertensão',
    'Nenhuma alergia conhecida',
    'Sem observações'
);

INSERT INTO CP1_HISTORICO_SAUDE_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    FUMA,
    DOENCA_PRINCIPAL,
    HISTORICO_FAMILIAR,
    ALERGIAS,
    OBSERVACOES
) VALUES (
    3,
    TO_DATE('2022-01-10', 'YYYY-MM-DD'),
    0,
    'Asma',
    'Histórico de asma na família',
    'Pólen',
    'Cliente tem episódios ocasionais de asma'
);

INSERT INTO CP1_HISTORICO_SAUDE_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    FUMA,
    DOENCA_PRINCIPAL,
    HISTORICO_FAMILIAR,
    ALERGIAS,
    OBSERVACOES
) VALUES (
    4,
    TO_DATE('2022-05-20', 'YYYY-MM-DD'),
    1,
    'Hipertensão',
    'Histórico de hipertensão na família',
    'Nenhuma alergia conhecida',
    'Cliente está sob medicação para controle da pressão arterial'
);

INSERT INTO CP1_HISTORICO_SAUDE_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    FUMA,
    DOENCA_PRINCIPAL,
    HISTORICO_FAMILIAR,
    ALERGIAS,
    OBSERVACOES
) VALUES (
    5,
    TO_DATE('2023-09-05', 'YYYY-MM-DD'),
    0,
    'Depressão',
    'Histórico de depressão na família',
    'Nenhuma alergia conhecida',
    'Cliente está em acompanhamento psicológico regular'
);

INSERT INTO CP1_HISTORICO_HOSPITAL_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    HISTORICO_MEDICO,
    EXAMES_REALIZADOS,
    MEDICAMENTOS_PRESCRITOS,
    OBSERVACOES
) VALUES (
    1,
    TO_DATE('2022-01-01', 'YYYY-MM-DD'),
    'Cirurgia de apendicite em 2015',
    'Exames de rotina',
    'Paracetamol',
    'Nenhuma observação'
);

INSERT INTO CP1_HISTORICO_HOSPITAL_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    HISTORICO_MEDICO,
    EXAMES_REALIZADOS,
    MEDICAMENTOS_PRESCRITOS,
    OBSERVACOES
) VALUES (
    2,
    TO_DATE('2023-07-20', 'YYYY-MM-DD'),
    'Internação por pneumonia',
    'Exames de sangue',
    'Antibióticos',
    'Nenhuma observação'
);

INSERT INTO CP1_HISTORICO_HOSPITAL_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    HISTORICO_MEDICO,
    EXAMES_REALIZADOS,
    MEDICAMENTOS_PRESCRITOS,
    OBSERVACOES
) VALUES (
    3,
    TO_DATE('2022-05-10', 'YYYY-MM-DD'),
    'Cirurgia de emergência de apendicite',
    'Tomografia computadorizada, exames de sangue',
    'Analgésicos, antibióticos',
    'Recuperação satisfatória'
);

INSERT INTO CP1_HISTORICO_HOSPITAL_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    HISTORICO_MEDICO,
    EXAMES_REALIZADOS,
    MEDICAMENTOS_PRESCRITOS,
    OBSERVACOES
) VALUES (
    4,
    TO_DATE('2023-01-15', 'YYYY-MM-DD'),
    'Fratura de tornozelo',
    'Raio-X, ressonância magnética',
    'Analgésicos, anti-inflamatórios',
    'Cliente em reabilitação fisioterapêutica'
);

INSERT INTO CP1_HISTORICO_HOSPITAL_CLIENTE (
    CLIENTE_ID,
    DATA_REGISTRO,
    HISTORICO_MEDICO,
    EXAMES_REALIZADOS,
    MEDICAMENTOS_PRESCRITOS,
    OBSERVACOES
) VALUES (
    5,
    TO_DATE('2023-09-30', 'YYYY-MM-DD'),
    'Infarto agudo do miocárdio',
    'Eletrocardiograma, angiografia coronariana',
    'Antiagregantes plaquetários, estatinas',
    'Cliente em monitoramento cardíaco regular'
);

SELECT
    *
FROM
    CP1_CLIENTE;

SELECT
    C.NOME,
    CO.NOME
FROM
    CP1_CLIENTE  C
    JOIN CP1_CONVENIO CO
    ON C.CONVENIO_ID = CO.ID;