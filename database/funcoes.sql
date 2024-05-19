SET SERVEROUTPUT ON

CREATE OR REPLACE FUNCTION validar_cpf (cpf IN VARCHAR2) RETURN BOOLEAN IS
BEGIN
    -- Verifica se o CPF possui 11 dígitos
    IF LENGTH(cpf) != 11 THEN
        RETURN FALSE;
    END IF;

    -- Verifica se todos os caracteres do CPF são dígitos
    FOR i IN 1..LENGTH(cpf) LOOP
        IF NOT REGEXP_LIKE(SUBSTR(cpf, i, 1), '[[:digit:]]') THEN
            RETURN FALSE;
        END IF;
    END LOOP;

    -- Verifica se o CPF é válido
    DECLARE
        total     NUMBER;
        digito1   NUMBER;
        digito2   NUMBER;
        soma      NUMBER := 0;
        resto     NUMBER;
        peso      NUMBER := 10;
    BEGIN
        -- Calcula o primeiro dígito verificador
        FOR i IN 1..9 LOOP
            soma := soma + TO_NUMBER(SUBSTR(cpf, i, 1)) * peso;
            peso := peso - 1;
        END LOOP;
        resto := MOD(soma, 11);
        IF resto < 2 THEN
            digito1 := 0;
        ELSE
            digito1 := 11 - resto;
        END IF;

        -- Calcula o segundo dígito verificador
        soma  := 0;
        peso  := 11;
        FOR i IN 1..9 LOOP
            soma := soma + TO_NUMBER(SUBSTR(cpf, i, 1)) * peso;
            peso := peso - 1;
        END LOOP;
        soma := soma + digito1 * 2;
        resto := MOD(soma, 11);
        IF resto < 2 THEN
            digito2 := 0;
        ELSE
            digito2 := 11 - resto;
        END IF;

        -- Verifica se os dígitos verificadores estão corretos
        IF digito1 = TO_NUMBER(SUBSTR(cpf, 10, 1)) AND digito2 = TO_NUMBER(SUBSTR(cpf, 11, 1)) THEN
            RETURN TRUE;
        ELSE
            RETURN FALSE;
        END IF;
    END;
END validar_cpf;
/




-- Função para validar e-mail
CREATE OR REPLACE FUNCTION validar_email (email IN VARCHAR2) RETURN BOOLEAN IS
BEGIN
    -- Utilizando uma expressão regular para validar o formato do e-mail
    IF REGEXP_LIKE(email, '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}') THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END validar_email;
/

-- Exemplo verdadeiro de CPF
DECLARE
    v_cpf_valido VARCHAR2(14) := '16595164710';
BEGIN
    IF validar_cpf(v_cpf_valido) THEN
        DBMS_OUTPUT.PUT_LINE('CPF válido: ' || v_cpf_valido);
    ELSE
        DBMS_OUTPUT.PUT_LINE('CPF inválido: ' || v_cpf_valido);
    END IF;
END;
/

-- Exemplo falso de CPF
DECLARE
    v_cpf_invalido VARCHAR2(14) := '12345678900';
BEGIN
    IF validar_cpf(v_cpf_invalido) THEN
        DBMS_OUTPUT.PUT_LINE('CPF válido: ' || v_cpf_invalido);
    ELSE
        DBMS_OUTPUT.PUT_LINE('CPF inválido: ' || v_cpf_invalido);
    END IF;
END;
/

-- Exemplo verdadeiro de e-mail
DECLARE
    v_email_valido VARCHAR2(100) := 'usuario@dominio.com';
BEGIN
    IF validar_email(v_email_valido) THEN
        DBMS_OUTPUT.PUT_LINE('E-mail válido: ' || v_email_valido);
    ELSE
        DBMS_OUTPUT.PUT_LINE('E-mail inválido: ' || v_email_valido);
    END IF;
END;
/

-- Exemplo falso de e-mail
DECLARE
    v_email_invalido VARCHAR2(100) := 'usuario@dominio';
BEGIN
    IF validar_email(v_email_invalido) THEN
        DBMS_OUTPUT.PUT_LINE('E-mail válido: ' || v_email_invalido);
    ELSE
        DBMS_OUTPUT.PUT_LINE('E-mail inválido: ' || v_email_invalido);
    END IF;
END;
/