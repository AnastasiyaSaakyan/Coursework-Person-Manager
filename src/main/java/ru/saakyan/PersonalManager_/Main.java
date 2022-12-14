package ru.saakyan.PersonalManager_;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int port = 8989;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            DataCategories dataCategories = new DataCategories();
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader
                             (new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted");
                    Product product = Product.loadJson(reader.readLine());

                    //обработка полученной информации
                    dataCategories.processing(product);
                    Gson gson = new Gson();
                    String result = gson.toJson(dataCategories);
                    writer.println(result);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}