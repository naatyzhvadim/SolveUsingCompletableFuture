import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneratorOfQuadrEq {
    public QuadraticEquation getEquation(double radius){
        double[] a = new double[3];
        final Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            a[i] = random.nextDouble() * radius - radius / 2;
            //System.out.print(a[i] + " ");
        }
        return new QuadraticEquation(a[0], a[1], a[2]);
    }


    int i = 0;

    synchronized void add(){
        ++i;
        System.out.println(i);
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        GeneratorOfQuadrEq generator = new GeneratorOfQuadrEq();
        //double radius = 200;
        //generator.getEquation(radius);

        // Создаём CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        CompletableFuture<String> whatsYour = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });


// Добавляем колбэк к Future, используя thenApply()
        /*CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Привет," + name;
        });

        CompletableFuture<String> gr = whatsYour.thenApply(name -> {
            return "Привет," + name;
        });*/

// Блокировка и получение результата Future
        /*try {
            System.out.println(greetingFuture.get()); // Привет, Rajeev
            System.out.println(gr.get());
        } catch (Exception e){
            System.out.print("OOOPS");
        }*/
        try {
            boolean flag1 = false, flag2 = false;
            while (!flag1 || !flag2) {
                //whatsYourNameFuture.get(1, TimeUnit.SECONDS);
                //whatsYour.get(1, TimeUnit.SECONDS);
                if (whatsYour.isDone()) {
                    System.out.println("Done 1");
                    flag1 = true;
                }
                if (whatsYourNameFuture.isDone()) {
                    System.out.println("Done 2");
                    TimeUnit.SECONDS.sleep(1);
                    flag2 = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Got it");
        }
        long timeSpent = System.currentTimeMillis() - startTime;

        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }
}
