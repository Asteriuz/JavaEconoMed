CREATE OR REPLACE PROCEDURE GET_CLIENTE_CONVENIO AS
    cliente_nome CP1_CLIENTE.NOME%TYPE;
    convenio_nome CP1_CONVENIO.NOME%TYPE;
    
    CURSOR cliente_convenio_cursor IS
        SELECT
            C.NOME AS CLIENTE_NOME,
            CO.NOME AS CONVENIO_NOME
        FROM
            CP1_CLIENTE C
            JOIN CP1_CONVENIO CO ON C.CONVENIO_ID = CO.ID;
BEGIN
    OPEN cliente_convenio_cursor;
    LOOP
        FETCH cliente_convenio_cursor INTO cliente_nome, convenio_nome;

        EXIT WHEN cliente_convenio_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Cliente: ' || cliente_nome || ', Convênio: ' || convenio_nome);
    END LOOP;

    CLOSE cliente_convenio_cursor;
END;
/

SET SERVEROUTPUT ON
BEGIN
    GET_CLIENTE_CONVENIO;
END;
/
