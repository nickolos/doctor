package com.example.doctor.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/static/output.txt");
        for (int i = 0; i < 50000; i++) {
            writer.write(UUID.randomUUID() + System.lineSeparator());
        }
        writer.close();
    }
}
