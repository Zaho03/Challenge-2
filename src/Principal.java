import com.google.gson.Gson;

import javax.sound.midi.Soundbank;
import java.awt.dnd.DragSource;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        System.out.println("Digite el código de la moneda base (por ejemplo, USD): ");
        String opciones = lectura.nextLine();  // Moneda base a convertir

        String direccion = "https://v6.exchangerate-api.com/v6/f2653d162823374b5031f89a/latest/" + opciones;

        // Realizar la solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Obtener la respuesta en formato JSON
        String json = response.body();
        System.out.println("Respuesta JSON: " + json);

        // Convertir el JSON a la clase Monedas usando Gson
        Gson gson = new Gson();
        Monedas dinero = gson.fromJson(json, Monedas.class);

        // Mostrar los resultados
        System.out.println("Moneda base: " + dinero.baseCode);
        System.out.println("Tasas de conversión: " + dinero.conversionRates);

        // Ejemplo: Convertir 100 USD a EUR (o cualquier otra moneda)
        System.out.println("Ingrese el código de la moneda a convertir (por ejemplo, EUR): ");
        String monedaDestino = lectura.nextLine();

        if (dinero.conversionRates.containsKey(monedaDestino)) {
            double tasaConversion = dinero.conversionRates.get(monedaDestino);
            System.out.println("Tasa de conversión: " + tasaConversion);

            System.out.println("Ingrese el monto a convertir: ");
            double monto = lectura.nextDouble();

            double montoConvertido = monto * tasaConversion;
            System.out.println(monto + " " + dinero.baseCode + " = " + montoConvertido + " " + monedaDestino);
        } else {
            System.out.println("Moneda no encontrada.");
        }
    }
}