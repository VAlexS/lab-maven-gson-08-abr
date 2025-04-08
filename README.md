# Lab Maven y Gson

En este lab vamos a trabajar con Maven y la biblioteca Gson. Aprenderemos a convertir objetos JSON a objetos Java y viceversa, así como a trabajar con listas y arrays en Java.
Es muy parecido a lo que hemos visto en clase, pero con algún extra 🤔

## Pasos a Iniciales:

1. Haz un Fork de este repo y clónalo en tu máquina local.
2. Abre el proyecto en IntelliJ.
3. Genera un nuevo proyecto Maven dentro de IntelliJ tal y como hemos visto en clase (no manualmente).
4. Añade la dependencia de Gson en el archivo `pom.xml`.
   Asegúrate de que la dependencia es la correcta:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10</version>
</dependency>
```

👁️ Hay otra dependencia que es gson-parent que nos puede confundir.

## Iteración 1: Clase Apartment

Vamos a crear una clase `Apartment` que contenga los siguientes atributos:

- `id` (long)
- `address` (String)
- `price` (double)
- `rooms` (int)
- `description` (String)
- `owner` (String)
- `available` (boolean)

_Importante:_ El id se deberá generar de forma automática en el constructor. Consulta [esta documentación](https://www.uuidgenerator.net/dev-corner/java) para ver cómo generar un UUID en Java. Puedes usar la librería `java.util.UUID` para esto.
Un UUID es un identificador único universal.

Añade los getters y setters así como el .toString() para que imprima todos los atributos de la clase. Puedes hacerlo automáticamente desde IntelliJ.

<details>
    <summary>Solución</summary>

```java
import java.util.UUID;

public class Apartment {

        private long id;
        private String address;
        private double price;
        private int rooms;
        private String description;
        private String owner;
        private boolean available;

        public Apartment(String address, double price, int rooms, String description, String owner, boolean available) {
                // Generar un UUID para el id
                this.id = UUID.randomUUID().toString();
                setAddress(address);
                setPrice(price);
                setRooms(rooms);
                setDescription(description);
                setOwner(owner);
                setAvailable(available);
        }

        public long getId() {
                return id;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public int getRooms() {
                return rooms;
        }

        public void setRooms(int rooms) {
                this.rooms = rooms;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getOwner() {
                return owner;
        }

        public void setOwner(String owner) {
                this.owner = owner;
        }

        public boolean isAvailable() {
                return available;
        }

        public void setAvailable(boolean available) {
                this.available = available;
        }

        @Override
        public String toString() {
                return "Apartment{" +
                                "id=" + id +
                                ", address='" + address + '\'' +
                                ", price=" + price +
                                ", rooms=" + rooms +
                                ", description='" + description + '\'' +
                                ", owner='" + owner + '\'' +
                                ", available=" + available +
                                '}';
        }
}
```

</details>

## Iteración 2: Archivo JSON

Tenemos un archivo JSON en la raíz de este proyecto llamado `apartments.json` que contiene una lista de apartamentos.
Vamos a moverlo a la carpeta `src/main/resources` para que Maven lo reconozca como un recurso del proyecto.
De momento no lo vamos a modificar.

## Iteración 3: Leer el archivo JSON y convertirlo a una lista de objetos Apartment

Vamos a crear una clase `ApartmentManager` que se encargue de leer el archivo JSON y convertirlo a una lista de objetos `Apartment` utilizando Gson. Puedes gestionarlo todo dentro del método `main` de la clase `ApartmentManager` (recuerda que puedes escribir psvm en IntelliJ para hacerlo de forma automática).

Puedes usar el ejemplo que hemos visto en clase para leer el archivo JSON y convertirlo a una lista de objetos `Apartment`. Allí hemos usado un ArrayList para almacenar los elementos.

_Tip extra:_ Para añadir todos los apartamentos a una lista, puedes usar el método estático `Collections.addAll()` de la clase `Collections`. [Consulta un tutorial](https://medium.com/@AlexanderObregon/javas-collections-addall-explained-fbed9a316bb2)

<details>
    <summary>Solución</summary>

```java
public class ApartmentManager {
    public static void main(String[] args) throws Exception {
        // crear el builder de Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Leer el archivo JSON
        Reader reader = new FileReader("src/main/resources/apartments.json");

        // Convertir el JSON a un array de objetos Apartment
        Apartment[] apartmentsArray = gson.fromJson(reader, Apartment[].class);

        // Crear un ArrayList y añadir los apartamentos
        List<Apartment> apartments = new ArrayList<>();
        Collections.addAll(apartments, apartmentsArray);

        // Ver un apartamento, para comprobar que funciona
        System.out.println(apartments.get(0).toString());

        // Cerrar el reader
        reader.close();
    }
}
```

</details>

## Iteración 4: Apartamentos nuevos

Vamos a crear la clase `ApartmentCreator` en la cual generaremos tres o cuatro variables de tipo `Apartment` y las almacenaremos en una lista. Lo puedes hacer todo en el método `main` de la clase `ApartmentCreator`.

<details>
    <summary>Solución</summary>

```java
public class ApartmentCreator {
    public static void main(String[] args) {
        // Crear una lista de apartamentos
        List<Apartment> newApartments = new ArrayList<>();

        // Crear nuevos apartamentos
        Apartment apartment1 = new Apartment("Calle Falsa 123", 1200.50, 3, "Apartamento acogedor", "Juan Pérez", true);
        Apartment apartment2 = new Apartment("Avenida Siempre Viva 742", 1500.00, 4, "Apartamento amplio", "María López", false);
        Apartment apartment3 = new Apartment("Plaza Mayor 1", 2000.00, 5, "Apartamento de lujo", "Pedro García", true);

        // Añadir los apartamentos a la lista
        newApartments.add(apartment1);
        newApartments.add(apartment2);
        newApartments.add(apartment3);

        // Comprobar que se han añadido correctamente
       System.out.println(newApartments);
    }
}

```

</details>

## Iteración 5: Generar JSON

Usando la misma clase del apartado anterior, vamos a generar un nuevo archivo JSON con los apartamentos que hemos creado. Para ello, vamos a usar el método `toJson()` de Gson. Puedes usar el mismo método `main` de la clase `ApartmentCreator`.

Utiliza `FileWriter` para crear el archivo JSON en `/src/main/resources/new_apartments.json` para escribir los objetos en el archivo. 

*Importante:* No olvides cerrar el `FileWriter` al final con el método `close()` para asegurarte de que todos los datos se escriben correctamente en el archivo.

<details>
    <summary>Solución</summary>

```java
public class ApartmentCreator {
    public static void main(String[] args) throws Exception {
        
        // ... código anterior ...
        // Crear un FileWriter para escribir el archivo JSON
        FileWriter writer = new FileWriter("src/main/resources/new_apartments.json");

        // Convertir la lista de apartamentos a JSON y escribirla en el archivo
        gson.toJson(newApartments, writer);
        
        // Cerrar el FileWriter
        writer.close();
    }
}

```

</details>

## Pull Request

Genera una pull request con tu solución y compártela en el portal.

Abre tu repo en github y ve a la pestaña de Pull Requests. Haz clic en "New Pull Request" y selecciona tu rama. Asegúrate de que la base sea la rama principal del repo original. Añade un título y una descripción y haz clic en "Create Pull Request".

Se creará una petición al autor del repositorio original para que revise tu código y lo integre en su proyecto. 

En éste caso no vamos a integrar el código, simplemente es para que veamos los cambios que has realizado y cómo lo has hecho.

El link que generará de la pull request será algo parecido a esto:
`github.com/tu_usuario/tu_repo/pull/1`
Puedes copiarlo y pegarlo en el portal.
