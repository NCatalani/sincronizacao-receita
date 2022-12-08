#!/usr/bin/env python3

from random import randint, uniform, choice

NUMERO_DE_LINHAS = 4000000
NOME_ARQUIVO = "sicredi_clientes_receita.csv"

if __name__ == "__main__":
 
    fp = open(NOME_ARQUIVO, "w")

    header = "agencia;conta;saldo;status\n"
    fp.write(header)
    possible_status = ["A", "I", "P", "B"]

    for i in range(0, NUMERO_DE_LINHAS):

        agencia = ''.join(["{}".format(randint(0, 9)) for num in range(0, 4)])
        conta = ''.join(["{}".format(randint(0, 9)) for num in range(0, 5)]) + "-%s" % randint(1,9)
        valor = ("%s" % round(uniform(-9999999.99, +9999999.99), 2)).replace(".", ",")
        status = "%s" % choice(possible_status)

        linha = "%s;%s;%s;%s" % (agencia, conta, valor, status)
        fp.write(linha + "\n")

    fp.close()
