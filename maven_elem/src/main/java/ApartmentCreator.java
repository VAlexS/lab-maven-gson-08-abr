import apartament.Apartment;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApartmentCreator {

    public static void main(String[] args) {

        //para que sea más generico el script, he permitido añadir tantos apartamentos como el usuario quiera leyendo los datos introducidos por
        // teclado

        var scanner = new Scanner(System.in);

        char opcion;

        boolean more = true;

        System.out.println("Este script consiste en crear apartamentos, te voy a pedir ciertas caracteristicas para crearlas");

        //ya que puede que agregue varios apartamentos, declaro las variables fuera del bucle para optimizar la memoria
        String address, description, owner;

        double price;

        int rooms;

        boolean available;

        Apartment apartament;

        List<Apartment> apartments = new ArrayList<>();

        do {
            System.out.print("Direccion: ");
            address = scanner.nextLine();

            System.out.print("Precio: ");
            price = scanner.nextDouble();
            scanner.nextLine();


            System.out.print("Rooms: ");
            rooms = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Desripcion: ");
            description = scanner.nextLine(); //aunque introduzca algo, me lo deja vacio, ayudame con esto


            System.out.print("Propietario: ");
            owner = scanner.nextLine();


            System.out.print("Available (true = si, false = no): ");
            available = scanner.nextBoolean();
            scanner.nextLine();

            apartament = new Apartment(address, price, rooms, description, owner, available);
            apartments.add(apartament);

            System.out.print("¿Desea añadir más apartamentos? s -> sí, n -> no ");
            opcion = scanner.nextLine().charAt(0);

            if (opcion == 'n')
                more = false;

        }while (more);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        final String URL = "maven_elem/src/main/resources/newApartments.json";

        try {
            FileWriter writer = new FileWriter(URL);


            //quiero añadir los apartamentos al json
           // gson.toJson(apartments, JsonArray.class, writer);

            gson.toJson(apartments, writer);



            /*for (Apartment apartmentElement : apartments) {
                json = gson.toJson(apartmentElement, Apartment.class);
                writer.write(json+"\n");
            }*/

            writer.close();

        } catch (IOException e) {
            System.out.println("Error de E/S");
            throw new RuntimeException(e);
        }


    }
}
