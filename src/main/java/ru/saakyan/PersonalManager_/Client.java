package ru.saakyan.PersonalManager_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
    private static final int port = 8989;
    private static final String localhost = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(localhost, port);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            Product product = generateProducts();
            writer.println(product.saveToJson());

            System.out.println(product);
            System.out.println(reader.readLine());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Product generateProducts() {
        String[] names = new String[]{"булка", "колбаса", "сухарики", "курица", "число"};
        String[] localDates = new String[]{"2022.09.08", "2022.11.11", "2021.02.08", "2021.10.10", "2022.02.09"};
        double[] sum = new double[]{1800., 2400., 6000., 1560, 9678.};
        Random random = new Random();
        int number1 = random.nextInt(5);
        int number2 = random.nextInt(5);
        int number3 = random.nextInt(5);
        Product product = new Product(names[number1], localDates[number2], sum[number3]);
        return product;
    }
}