import java.util.Random;

public class Tarea_11 extends Thread {
    private int hilo;
    private Tarea_11 siguienteHilo;

    public Tarea_11(int hilo) {
        super("[Hilo-" + hilo + "]");
        this.hilo = hilo;
    }

    public void run() {
        if (hilo < 5) {
            siguienteHilo = new Tarea_11(hilo + 1);
            siguienteHilo.start();
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " Iteración " + i);
            try {
                int tiempo = new Random().nextInt(501) + 100;
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (siguienteHilo != null) {
            try {
                siguienteHilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Acabó hilo " + getName());
    }

    public static void main(String[] args) {
        Tarea_11 hilo1 = new Tarea_11(1);
        hilo1.start();
    }
}


