/*
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/
package com.sicredi.SincronizacaoReceita;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class SincronizacaoReceita {

    public static void main(String[] args) throws CsvValidationException, IOException, RuntimeException, InterruptedException {

        String csv_file = (String) Array.get(args, 0);
        ReceitaService rs = new ReceitaService();

        System.out.println("CSV sendo processado: " + csv_file);

        FileReader reader = new FileReader(csv_file);

        CSVReader reader2 = new CSVReaderBuilder(reader)
        .withCSVParser(new CSVParserBuilder()
            .withSeparator(';')
            .build()
        ).build();

        String [] line;
        int line_count = 0;
        while ((line = reader2.readNext()) != null) {
            // nextLine[] is an array of values from the line

            line_count++;

            if (line_count == 1) {
                continue;
            }

            String agencia = line[0];
            String conta = line[1].replace("-", "");
            double saldo = Double.parseDouble(line[2].replace(",", "."));
            String status = line[3];

            System.out.println(agencia + ";" + conta + ";" + saldo + ";" + status);

            boolean res = rs.atualizarConta(agencia, conta, saldo, status);

            System.out.println("res: " + res);
            System.out.println("#####");
        }
        
        // Exemplo como chamar o "serviço" do Banco Central.
        // ReceitaService receitaService = new ReceitaService();
        // receitaService.atualizarConta("0101", "123456", 100.50, "A");        
    }
    
}
