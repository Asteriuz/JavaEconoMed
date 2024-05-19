SET SERVEROUTPUT ON;

-- Bloco Anônimo 1: Consulta de Junção entre CP1_CLIENTE, CP1_ESTADO_CIVIL e CP1_ENDERECO_CLIENTE DAS CLIENTES DO SEXO FEMININO
BEGIN
    FOR CLIENTE IN (
        SELECT
            C.NOME,
            EC.NOME  AS ESTADO_CIVIL,
            CID.NOME AS CIDADE
        FROM
            CP1_CLIENTE          C
            JOIN CP1_ESTADO_CIVIL EC
            ON C.ESTADO_CIVIL_ID = EC.ID
            JOIN CP1_ENDERECO_CLIENTE EC
            ON C.ID = EC.CLIENTE_ID
            JOIN CP1_CIDADE CID
            ON EC.CIDADE_ID = CID.ID
        WHERE
            C.SEXO = 'F'
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Nome: '
                             || CLIENTE.NOME
                             || ' | Estado Civil: '
                             || CLIENTE.ESTADO_CIVIL
                             || ' | Cidade: '
                             || CLIENTE.CIDADE);
    END LOOP;
END;
/

-- blOCO ANÔNIMO 2: Consulta de Junção entre CP1_UNIDADE, CP1_ENDERECO_UNIDADE, CP1_CIDADE E CP1_ESTADO DAS UNIDADES DE SAÚDE
DECLARE
    V_ID_UNIDADE   NUMBER := &ID_UNIDADE;
    V_NOME_UNIDADE CP1_UNIDADE.NOME%TYPE;
    V_RUA          CP1_ENDERECO_UNIDADE.RUA%TYPE;
    V_NUMERO       CP1_ENDERECO_UNIDADE.NUMERO%TYPE;
    V_CIDADE       CP1_CIDADE.NOME%TYPE;
    V_ESTADO       CP1_ESTADO.NOME%TYPE;
BEGIN
    SELECT
        U.NOME,
        EU.RUA,
        EU.NUMERO,
        CID.NOME,
        E.NOME INTO V_NOME_UNIDADE,
        V_RUA,
        V_NUMERO,
        V_CIDADE,
        V_ESTADO
    FROM
        CP1_UNIDADE          U
        JOIN CP1_ENDERECO_UNIDADE EU
        ON U.ID = EU.UNIDADE_ID
        JOIN CP1_CIDADE CID
        ON EU.CIDADE_ID = CID.ID
        JOIN CP1_ESTADO E
        ON CID.ESTADO_ID = E.ID
    WHERE
        U.ID = V_ID_UNIDADE;
    DBMS_OUTPUT.PUT_LINE('Nome da Unidade: '
                         || V_NOME_UNIDADE);
    DBMS_OUTPUT.PUT_LINE('Rua: '
                         || V_RUA);
    DBMS_OUTPUT.PUT_LINE('Número: '
                         || V_NUMERO);
    DBMS_OUTPUT.PUT_LINE('Cidade: '
                         || V_CIDADE);
    DBMS_OUTPUT.PUT_LINE('Estado: '
                         || V_ESTADO);
END;
/
