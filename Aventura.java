import java.util.Scanner;

public class Aventura {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        Enemigos enemigo1 = new Enemigos("Soldado de Godrick", 40, 5, 15);
        Enemigos enemigo2 = new Enemigos("Semihumano", 40, 5, 15);
        Enemigos enemigo3 = new Enemigos("Caballero desterrado", 60, 5, 15);
        Enemigos enemigo4 = new Enemigos("Margit", 80, 5, 15);
        Enemigos enemigo5 = new Enemigos("Godrick", 80, 5, 15);
        int opcion = 0;
        String nombre = "";
        int vida = 100;
        int canPociones = 3;
        Armas arma1 = new Armas("Daga", "Ligera", 8, 13);
        Armas arma2 = new Armas("Espada larga", "Mediana", 12, 17);
        Armas arma3 = new Armas("Espadón", "Pesada", 15, 20);

        while (opcion != 5) {

            System.out.println("Bienvenido a la Tierra Intermedia");
            System.out.println("¿Como te llamas?");
            nombre = sc.nextLine();
            System.out.println("Hola " + nombre + ", ¿Que deseas hacer?");
            System.out.println("1. Empezar la aventura");
            System.out.println("2. Ver inventario");
            System.out.println("3. Ayuda");
            System.out.print("4. Ver Ranking");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
        if (opcion == 1)
            {
                System.out.println("Empezando la aventura...");
                System.out.println(nombre + " tienes " + vida + " de vida y " + canPociones + " pociones.");
                System.out.println("¿Que arma deseas usar?");
                System.out.println("1. Daga");
                System.out.println("2. Espada larga");
                System.out.println("3. Espadón");
                int arma = sc.nextInt();
                if (arma == 1) {
                    System.out.println("Has elegido la " + arma1.getNombre() + ", tu daño será de " + arma1.getDañoMin() + " a " + arma1.getDañoMax() + " y nunca fallará.");
                } else if (arma == 2) {
                    System.out.println("Has elegido la " + arma2.getNombre() + ", tu daño será de " + arma2.getDañoMin() + " a " + arma2.getDañoMax() + " y tiene una probabilidad de fallar.");
                } else if (arma == 3) {
                    System.out.println("Has elegido el " + arma3.getNombre() + ", tu daño será de " + arma3.getDañoMin() + " a " + arma3.getDañoMax() + " y tiene una gran probabilidad de fallar.");
                } else {
                    System.out.println("Opción inválida, se te asignará la Daga por defecto.");
                }
                while (vida > 0 && enemigo1.getVida() > 0) {
                    System.out.println("Bienvenido a la tierras intermedias, " + nombre + ", tu aventura empieza aquí");
                    System.out.println("¿Que quieres hacer?");
                    System.out.println("1. Caminar");
                    System.out.println("2. Usar poción");
                    System.out.println("3. Sentar en la hoguera");
                    int accion = sc.nextInt();
                    
            }

        if (opcion == 2)
            {
                System.out.println("Inventario");
                System.out.println("Tu inventario contiene:");
                System.out.println("1. Daga");
                System.out.println("2. Espada larga");
                System.out.println("3. Espadón");
                System.out.println("4. Tienes " + canPociones + " pociones");
            }
        if (opcion == 3)
            {
                System.out.print("Ayuda");
                System.out.println("¿Como funciona el juego?");
                System.out.println("El juego consiste en un combate por turnos, donde tu personaje y el enemigo se atacan hasta que uno de los dos pierda toda su vida.");
                System.out.println("¿Como puedo recuperar vida?");
                System.out.println("Podrás recuperar vida usando pociones limitas, trás cada combate podrás recuperar tus pociones y tu vida descansando en una hoguera");
                System.out.println("¿Como puedo ganar el juego?");
                System.out.println("Para ganar el juego deberás derrotar a todos los enemigos y llegar al final del juego, donde te espera un gran enemigo final.");
            }
        if (opcion == 4)
            {
                System.out.println("Ranking");
                System.out.println("1. Jugador 1 - 100 puntos");
                System.out.println("2. Jugador 2 - 90 puntos");
                System.out.println("3. Jugador 3 - 80 puntos");
            }
        if (opcion == 5) 
            {
                System.out.println("Gracias por jugar, hasta la próxima!");
            }
        }
        sc.close();
    }
}