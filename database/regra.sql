SET SERVEROUT ON

CREATE OR REPLACE PROCEDURE CLIENTE_CONVENIO_RELATORIO AS
BEGIN
    FOR cliente_info IN (
        SELECT
            C.NOME AS NOME_CLIENTE,
            CO.NOME AS NOME_CONVENIO
        FROM
            CP1_CLIENTE C
            JOIN CP1_CONVENIO CO ON C.CONVENIO_ID = CO.ID
        ORDER BY
            C.NOME
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Nome do Cliente: ' || cliente_info.NOME_CLIENTE);
        DBMS_OUTPUT.PUT_LINE('Convênio: ' || cliente_info.NOME_CONVENIO);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(chr(10));

    FOR convenio_count IN (
        SELECT
            CO.NOME AS NOME_CONVENIO,
            COUNT(C.ID) AS NUM_CLIENTES
        FROM
            CP1_CLIENTE C
            JOIN CP1_CONVENIO CO ON C.CONVENIO_ID = CO.ID
        GROUP BY
            CO.NOME
        ORDER BY
            NUM_CLIENTES DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Convênio: ' || convenio_count.NOME_CONVENIO || ', Número de Clientes: ' || convenio_count.NUM_CLIENTES);
    END LOOP;
END;
/

BEGIN
    CLIENTE_CONVENIO_RELATORIO;
END;
/