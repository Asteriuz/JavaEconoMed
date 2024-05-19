import cx_Oracle

# ---------------------------------------------------------------------------- #
#              Configuração de conexão com o banco de dados Oracle             #
# ---------------------------------------------------------------------------- #

USERNAME = "rm98078"
PASSWORD = "261202"
DSN = "oracle.fiap.com.br:1521/ORCL"


def call_procedure(cursor, procedure_name, params):
    try:
        print(f"\nChamando a procedure {procedure_name} com parâmetros: {params}")
        result = cursor.callproc(procedure_name, params)
        print(f"Sucesso ao chamar a procedure {procedure_name}. Resultado: {result}")
    except cx_Oracle.DatabaseError as e:
        (error,) = e.args
        print(f"Erro ao chamar a procedure {procedure_name}: {error.message}")


connection = cx_Oracle.connect(USERNAME, PASSWORD, DSN)
cursor = connection.cursor()

# ---------------------------------------------------------------------------- #
#                            Chamada das procedures                            #
# ---------------------------------------------------------------------------- #

call_procedure(cursor, "INSERT_CP1_ESTADO", ["São Paulo"])

call_procedure(cursor, "UPDATE_CP1_ESTADO", [1, "Rio de Janeiro"])

call_procedure(cursor, "DELETE_CP1_ESTADO", [6])

call_procedure(cursor, "INSERT_CP1_CIDADE", ["Campinas", 1])

call_procedure(cursor, "UPDATE_CP1_CIDADE", [1, "Santos", 1])

call_procedure(cursor, "DELETE_CP1_CIDADE", [6])

call_procedure(cursor, "INSERT_CP1_ESTADO_CIVIL", ["Namorando"])

call_procedure(cursor, "UPDATE_CP1_ESTADO_CIVIL", [1, "Ficando"])

call_procedure(cursor, "DELETE_CP1_ESTADO_CIVIL", [7])

call_procedure(cursor, "INSERT_CP1_CONVENIO", ["Unimed"])

call_procedure(cursor, "UPDATE_CP1_CONVENIO", [1, "Amil"])

call_procedure(cursor, "DELETE_CP1_CONVENIO", [6])


# ------------------ Finaliza a conexão com o banco de dados ----------------- #

cursor.close()
connection.close()
