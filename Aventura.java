import java.util.Scanner;
import java.util.Random;

public class Aventura {
    // METODOS
    static int vida = 100;
    static int canPociones = 3;
    static int turnos = 0;
    static int danoRecibido;
    static int puntos;
    static Armas armaElegida;

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
        System.out.println("===== ELDEN RING =====");
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

    // Menu para el combate
    public static boolean empezarCombate(Scanner sc, Enemigos enemigo, Armas arma) {
        System.out.println("El enemigo te está mirando");

        while (vida > 0 && enemigo.getVida() > 0) {
            turnos++;
            System.out.println("=====COMBATE=====");
            System.out.println("Tu vida: " + vida);
            System.out.println("Vida del enemigo: " + enemigo.getVida());
            System.out.println("Tienes " + canPociones + " pociones");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Usar poción");
            System.out.println("3. Inventario");
            System.out.println("4. Intentar huir");
            System.out.println("5. Salir del juego");
            int accion = sc.nextInt();

            if (accion == 1) {
                int daño = calcularDaño(arma);
                enemigo.setVida(enemigo.getVida() - daño);
                if (daño > 0) {
                    System.out.println("Hiciste " + daño + " de daño al enemigo");
                }
                if (daño <= 0) {
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
                int[] inventario = { 25, 25, 25 };
                mostrarInventario(inventario, canPociones);
                pausa(sc);
                limpiarPantalla();
            } else if (accion == 4) {
                intentarHuir();

            } else if (accion == 5) {
                System.out.println("Gracias por jugar!!!");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("Opción inválida, el enemigo te ataca.");
            }

            if (enemigo.getVida() <= 0) {
                System.out.println("Has derrotado al enemigo " + enemigo.getNombre() + "!");
                return true;
            }

            int dañoEnemigo = calcularDañoEnemigo(enemigo);
            vida -= dañoEnemigo;
            danoRecibido += dañoEnemigo;
            System.out.println(enemigo.getNombre() + " te ha hecho " + dañoEnemigo + " de daño.");

            if (vida <= 0) {
                System.out.println("El enemigo te ha derrotado, has perdido la partida.");
                return false;
            }
        }
        return false;
    }

    public static void curarHoguera() {
        System.out.println("Has descansado en la hoguera, sientes que tus fuerzas vuelven");
        vida = 100;
        canPociones = 3;
    }

    public static void curarFueraCombate() {
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

    // Sistema de Combate

    public static boolean fase(Scanner sc, Enemigos enemigo, Armas armaElegida) {
        while (vida > 0 && enemigo.getVida() > 0) {
            mostrarMenuAventura();
            int accion = sc.nextInt();

            if (accion == 1) {
                limpiarPantalla();
                System.out.println("Has encontrado a un enemigo " + enemigo.getNombre());
                boolean victoria = empezarCombate(sc, enemigo, armaElegida);
                pausa(sc);
                limpiarPantalla();
                return victoria;
            }
            if (accion == 2) {
                limpiarPantalla();
                curarFueraCombate();
                pausa(sc);
                limpiarPantalla();
            }
            if (accion == 3) {
                limpiarPantalla();
                curarHoguera();
                pausa(sc);
                limpiarPantalla();
            }
        }
        return false;
    }

    public static void trampa() {
        System.out.println("¡Has caído en una trampa! Pierdes 25 de vida.");
        int dañoTrampa = 25;
        vida -= dañoTrampa;
        danoRecibido += 25;
    }

    // Clase
    public static void elegirClases(Scanner sc) {
        System.out.println("Elige tu clase:");
        System.out.println("1. Guerrero");
        System.out.println("2. Astrólogo");
        System.out.println("3. Profeta");
        int opcion = sc.nextInt();

        if (opcion == 2) {
            Astrologo astrologo = new Astrologo("Astrólogo", 50, 20, 16, 80);
            armaElegida = astrologo.getBaston();
            System.out.println("Eres un Astrólogo. Tu arma es: " + armaElegida.getNombre());

        } else if (opcion == 3) {
            Profeta profeta = new Profeta("Profeta", 40, 16, 10, 100);
            armaElegida = profeta.getSello();
            System.out.println("Eres un Profeta. Tu arma es: " + armaElegida.getNombre());

        } else {
            if (opcion != 1) {
                System.out.println("Opción inválida, serás Guerrero.");
            }
            Guerrero guerrero = new Guerrero("Guerrero", 0, 20, 10, 100);
            System.out.println("Eres un Guerrero. ¿Qué arma quieres?");
            System.out.println("1. Daga");
            System.out.println("2. Espada larga");
            System.out.println("3. Espadón");
            int arma = sc.nextInt();

            if (arma == 2) {
                armaElegida = guerrero.getEspadaLarga();
            } else if (arma == 3) {
                armaElegida = guerrero.getEspadon();
            } else {
                armaElegida = guerrero.getDaga();
            }
            System.out.println("Has elegido: " + armaElegida.getNombre());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String nombre = "";

        limpiarPantalla();
        System.out.println("===== ELDEN RING =====");
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
                vida = 100;
                canPociones = 3;
                turnos = 0;
                danoRecibido = 0;
                puntos = 0;

                Enemigos enemigo1 = new Enemigos("Soldado de Godrick", 40, 8, 20);
                Enemigos enemigo2 = new Enemigos("Caballero desterrado", 60, 10, 20);
                Enemigos enemigo3 = new Enemigos("Godrick", 80, 15, 20);

                System.out.println("Empezando la aventura...");
                System.out.println(nombre + " tienes " + vida + " de vida y " + canPociones + " pociones.");
                elegirClases(sc);
                
                pausa(sc);
                limpiarPantalla();

                // Primer combate
                if (fase(sc, enemigo1, armaElegida)) {
                    System.out.println("¡Enhorabuena por tu primera victoria, " + nombre + "!");
                    System.out.println("Las puertas del Castillo de Velo Tormentoso se abren. ¿Quieres entrar?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    int entrada = sc.nextInt();

                    if (entrada == 1) {
                        System.out.println("Has entrado al castillo, avanzas por los pasillos hasta que...");
                        trampa();
                        System.out.println("Tu vida actual es: " + vida);
                        if (vida <= 0) {
                            System.out.println("La trampa ha acabado contigo. Has perdido la partida.");
                        } else {
                            System.out.println(
                                    "En el pasillo principal notas una presencia enfrente de ti, es un caballero.");
                        }
                        pausa(sc);
                        limpiarPantalla();
                    } else {
                        System.out.println("Decides no entrar y rodeas el castillo. Encuentras un pasaje secreto.");
                        System.out.println("Avanzas por el pasaje y ves una silueta al fondo.");
                        pausa(sc);
                        limpiarPantalla();
                    }

                    // Segundo combate (las dos rutas llegan aquí)
                    if (vida > 0 && fase(sc, enemigo2, armaElegida)) {
                        System.out.println("¡Segunda victoria! Te espera Godrick...");
                        System.out.println(
                                "Mientras avanzas por el castillo, te encuentras con dos pasillos. Ambos llevan a la sala del trono, pero uno tiene un olor extraño. ¿Qué pasillo eliges?");
                        System.out.println("1. Pasillo izquierdo");
                        System.out.println("2. Pasillo derecho");
                        int pasillo = sc.nextInt();

                        if (pasillo == 1) {
                            System.out.println(
                                    "Avanzas con cautela hasta la sala del trono. Godrick te espera, se acerca y te ataca.");
                            if (fase(sc, enemigo3, armaElegida)) {
                                System.out.println("====Victoria====");
                                System.out.println("¡Has derrotado a Godrick! Has ganado el juego.");
                                puntos += 300;
                                puntos += Math.max(0, 500 - turnos * 10);
                                System.out.println("Tu puntuación final es " + puntos);
                            }
                        } else {
                            System.out.println(
                                    "El pasillo te lleva hacia unas escaleras, escuchas un ruido raro hasta que...");
                            System.out.println(
                                    "Una bola de piedra gigante rueda por las escaleras hacia ti y te da de lleno. Has muerto.");
                            vida = 0;
                            pausa(sc);
                        }
                    }
                }
                // Pausa final de la aventura (un solo Enter)
                System.out.println("Pulsa Enter para volver al menú...");
                sc.nextLine();
            }

            if (opcion == 2) {
                limpiarPantalla();
                int[] inventario = { 25, 25, 25 };
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
            }

            if (opcion == 5) {
                System.out.println("Gracias por jugar, hasta la próxima!");
            }
        }
        sc.close();
    }
}