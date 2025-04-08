import com.google.gson.*;
import apartament.Apartment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentManager {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        final String URL = "maven_elem/src/main/resources/apartments.json";

        try {

            //PROCESO: json -> generar objetos. A partir de un fichero JSON. Genero objetos con el contenido del fichero.

            FileReader reader = new FileReader(URL);

            //quiero leer el json
            JsonArray apartmentsArrayJson = gson.fromJson(reader, JsonArray.class);

            List<Apartment> apartamentList = new ArrayList<>();

            for (JsonElement apartmentElement : apartmentsArrayJson){
                Apartment apartment = gson.fromJson(apartmentElement, Apartment.class);
                apartamentList.add(apartment);
            }

            reader.close();

            System.out.println(apartamentList);





        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error de E/S");
            throw new RuntimeException(e);
        }

    }
}