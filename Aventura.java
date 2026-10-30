import java.util.Scanner;
import java.util.Random;

public class Aventura {
        
        static int vida = 100;
        static int canPociones = 3;

        public static void limpiarPantalla() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        public static void pausa(Scanner sc) {
            System.out.println("Pulsa Enter para continuar...");
            sc.nextLine();
            sc.nextLine();
        }

    public static void mostrarMenu() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Empezar la aventura");
        System.out.println("2. Ver inventario");
        System.out.println("3. Ayuda");
        System.out.println("4. Ver Ranking");
        System.out.println("5. Salir");
    }

    public static void mostrarInventario(int[] inventario, int canPociones) {
        System.out.println("===== INVENTARIO =====");

        System.out.println("Armas disponibles:");
        System.out.println("1. Daga");
        System.out.println("2. Espada larga");
        System.out.println("3. Espadón");

        System.out.println("Pociones disponibles: " + canPociones);

        for (int i = 0; i < canPociones; i++) {
            System.out.println("Poción " + (i + 1) + ": Cura " + inventario[i] + " HP");
        }
    }

    public static int calcularDaño(Armas arma) {
        Random random = new Random();
        int probabilidadFallo = arma.getProbabilidadFallo();
        int golpear = random.nextInt(100);

        if (golpear < probabilidadFallo) {
            System.out.println("¡Fallaste el ataque!");
            return 0;
        } else {
            System.out.println("Golpeaste al enemigo");
        }

        int dañoMin = arma.getDañoMin();
        int dañoMax = arma.getDañoMax();
        int dañoTotal = random.nextInt(dañoMax - dañoMin + 1) + dañoMin;
        if (dañoTotal <= 0) {
            System.out.println("El enemigo te ha esquivado");
        }
        return dañoTotal;
    }

    public static int calcularDañoEnemigo(Enemigos enemigo) {
        Random random = new Random();
        int dañoMin = enemigo.getDañoMin();
        int dañoMax = enemigo.getDañoMax();
        int dañoTotalEnemigo = random.nextInt(dañoMax - dañoMin + 1) + dañoMin;
        return dañoTotalEnemigo;
    }

    public static void intentarHuir() {
        System.out.println("Intentas correr hacia el lado contrario.");
        System.out.println("Te resbalas y caes al suelo, el enemigo se acerca a ti y te ataca.");
    }

    public static boolean empezarCombate(Scanner sc, Enemigos enemigo, Armas arma) {
        System.out.println("El enemigo te está mirando");

        while (vida > 0 && enemigo.getVida() > 0) {
            System.out.println("=====COMBATE=====");
            System.out.println("Tu vida: " + vida);
            System.out.println("Vida del enemigo: " + enemigo.getVida());
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Usar poción");
            System.out.println("3. Intentar huir");
            int accion = sc.nextInt();

            if (accion == 1) {
                int daño = calcularDaño(arma);
                enemigo.setVida(enemigo.getVida() - daño);
                if (daño > 0){
                System.out.println("Hiciste " + daño + " de daño al enemigo");
                }
                if (daño <= 0){
                    System.out.println("Has fallado el ataque");
                }
            } else if (accion == 2) {
                if (canPociones > 0) {
                    vida += 25;
                    canPociones--;
                    if (vida > 100) {
                        vida = 100;
                        System.out.println("Ya tienes vida máxima, no puedes usar más pociones.");
                    }
                    System.out.println("Has usado una poción, te has curado 25 de vida, tu vida actual es " + vida);
                } else {
                    System.out.println("No te quedan pociones.");
                }
            } else if (accion == 3) {
                intentarHuir();
            } else {
                System.out.println("Opción inválida, el enemigo te ataca.");
            }

            if (enemigo.getVida() <= 0) {
                System.out.println("Has derrotado al enemigo " + enemigo.getNombre() + "!");
                return true;
            }

            int dañoEnemigo = calcularDañoEnemigo(enemigo);
            vida -= dañoEnemigo;
            System.out.println(enemigo.getNombre() + " te ha hecho " + dañoEnemigo + " de daño.");

            if (vida <= 0) {
                System.out.println("El enemigo te ha derrotado, has perdido la partida.");
                return false;
            }
        }
        return false;
    }

    public static void curarHoguera(int vida, int canPociones) {
        System.out.println("Has descansado en la hoguera, sientes que tus fuerzas vuelven");
        vida = 100;
        canPociones = 3;
    }

    public static void curarFueraCombate(int vida, int canPociones) {
        if (canPociones > 0) {
            vida += 25;
            canPociones--;
            if (vida > 100) {
                vida = 100;
                System.out.println("Ya tienes vida máxima, no puedes usar más pociones.");
            }
            System.out.println("Has usado una poción, te has curado 25 de vida, tu vida actual es " + vida);
        } else {
            System.out.println("No te quedan pociones.");
        }
    }

    public static void mostrarMenuAventura() {
        System.out.println("¿Qué quieres hacer?");
                    System.out.println("1. Caminar");
                    System.out.println("2. Usar poción");
                    System.out.println("3. Sentarte en la hoguera");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Enemigos enemigo1 = new Enemigos("Soldado de Godrick", 40, 5, 15);
        Enemigos enemigo2 = new Enemigos("Caballero desterrado", 60, 5, 15);
        Enemigos enemigo3 = new Enemigos("Godrick", 80, 5, 15);

        int opcion = 0;
        String nombre = "";
        int vida = 100;
        int[] inventario = { 25, 25, 25 };
        int canPociones = 3;

        Armas arma1 = new Armas("Daga", "Ligera", 8, 12, 0);
        Armas arma2 = new Armas("Espada larga", "Mediana", 12, 14, 20);
        Armas arma3 = new Armas("Espadón", "Pesada", 15, 20, 40);


        limpiarPantalla();
        System.out.println("Bienvenido a la Tierra Intermedia");
        System.out.println("¿Cómo te llamas?");
        nombre = sc.nextLine();
        limpiarPantalla();

        while (opcion != 5) {
            limpiarPantalla();
            mostrarMenu();
            System.out.println("Selecciona una opción:");
            opcion = sc.nextInt();
            limpiarPantalla();

            if (opcion == 1) {
                System.out.println("Empezando la aventura...");
                System.out.println(nombre + " tienes " + vida + " de vida y " + canPociones + " pociones.");
                System.out.println("¿Qué arma deseas usar?");
                System.out.println("1. Daga");
                System.out.println("2. Espada larga");
                System.out.println("3. Espadón");
                int arma = sc.nextInt();
                Armas armaElegida = arma1;

                if (arma == 1) {
                    armaElegida = arma1;
                    System.out.println("Has elegido la " + arma1.getNombre() + ", tu daño será de " + arma1.getDañoMin()
                            + " a " + arma1.getDañoMax() + " y nunca fallará.");
                } else if (arma == 2) {
                    armaElegida = arma2;
                    System.out.println("Has elegido la " + arma2.getNombre() + ", tu daño será de " + arma2.getDañoMin()
                            + " a " + arma2.getDañoMax() + " y tiene una probabilidad de fallar.");
                } else if (arma == 3) {
                    armaElegida = arma3;
                    System.out.println("Has elegido el " + arma3.getNombre() + ", tu daño será de " + arma3.getDañoMin()
                            + " a " + arma3.getDañoMax() + " y tiene una gran probabilidad de fallar.");
                } else {
                    System.out.println("Opción inválida, se te asignará la Daga por defecto.");
                }

                pausa(sc);
                limpiarPantalla();
                
                while (vida > 0) {
                    System.out.println("Bienvenido a las tierras intermedias, " + nombre + " que quieres hacer?");
                    mostrarMenuAventura();
                    int accion = sc.nextInt();

                    if (accion == 1) {
                        limpiarPantalla();
                        System.out.println("Has encontrado un enemigo: " + enemigo1.getNombre());
                        boolean resultadoCombate = empezarCombate(sc, enemigo1, armaElegida);

                        if (resultadoCombate == true) {
                            System.out.println("Has derrotado al enemigo puedes seguir tu jornada");
                        }
                        pausa(sc);
                        limpiarPantalla();
                    }
                    if (accion == 2) {
                        curarFueraCombate(vida, canPociones);
                    }
                    if (accion == 3) {
                        curarHoguera(vida, canPociones);
                    }
                    if (enemigo1.getVida() <= 0) {
                        System.out.println("Has derrotado al enemigo " + enemigo1.getNombre() + "!");
                        pausa(sc);
                        limpiarPantalla();
                        System.out.println("Has encontrado un enemigo: " + enemigo2.getNombre());
                        boolean resultadoCombate = empezarCombate(sc, enemigo2, armaElegida);

                        if (resultadoCombate == true) {
                            System.out.println("Has derrotado al enemigo puedes seguir tu jornada");
                        }
                        pausa(sc);
                        limpiarPantalla();
                    }
                } 
                
    
                while (vida > 0); {
                    System.out.println("¿Que quieres hacer?");
                    mostrarMenuAventura();
                    limpiarPantalla();
                    int accion = sc.nextInt();
                    if (accion == 1) {
                        limpiarPantalla();
                        System.out.println ("Has encontrado un enemigo " + enemigo2.getNombre());
                        boolean resultadoCombate2 = empezarCombate(sc, enemigo2, armaElegida);
                        if (resultadoCombate2 == true) {
                                System.out.println("Has derrotado " + enemigo2.getNombre());
                        }
                        pausa(sc);
                        limpiarPantalla();
                    }
                    if (accion == 2) {
                        curarFueraCombate(vida, canPociones);
                    }
                    if (accion == 3) {
                        curarHoguera(vida, canPociones);
                    }
                }
                
                while (vida > 0) {
                    System.out.println("¿Que Quieres hacer?");
                    mostrarMenuAventura();
                    int accion = sc.nextInt();
                    if (accion == 1) {
                        limpiarPantalla();
                        System.out.println ("Has encontrado un enemigo " + enemigo3.getNombre());
                        boolean resultadoCombate3 = empezarCombate(sc, enemigo3, armaElegida);
                        if (resultadoCombate3 == true) {
                            System.out.println("Has derrotado al enemigo puedes seguir tu jornada");
                        }
                        pausa(sc);
                        limpiarPantalla();
                    }
                    if (accion == 2) {
                        curarFueraCombate(vida, canPociones);
                    }
                    if (accion == 3) {
                        curarHoguera(vida, canPociones);
                    }
                }
            }
            if (opcion == 2) {
                limpiarPantalla();
                mostrarInventario(inventario, canPociones);
                pausa(sc);
            }

            if (opcion == 3) {
                limpiarPantalla();
                System.out.println("Ayuda");
                System.out.println("¿Cómo funciona el juego?");
                System.out.println(
                        "El juego consiste en un combate por turnos, donde tu personaje y el enemigo se atacan hasta que uno de los dos pierda toda su vida.");
                System.out.println("¿Cómo puedo recuperar vida?");
                System.out.println(
                        "Podrás recuperar vida usando pociones limitadas, tras cada combate podrás recuperar tus pociones y tu vida descansando en una hoguera");
                System.out.println("¿Cómo puedo ganar el juego?");
                System.out.println(
                        "Para ganar el juego deberás derrotar a todos los enemigos y llegar al final del juego, donde te espera un gran enemigo final.");
                pausa(sc);
                }
                if (opcion == 4) {
                limpiarPantalla();
                System.out.println("Ranking");
                System.out.println("1. Jugador 1 - 100 puntos");
                System.out.println("2. Jugador 2 - 90 puntos");
                System.out.println("3. Jugador 3 - 80 puntos");
                pausa(sc);
            }if (opcion == 5) {
                System.out.println("Gracias por jugar, hasta la próxima!");
            }
        }
         sc.close();
    }
}