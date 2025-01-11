/**
 * Check how many vegetables are in the pantry
 */

 import java.util.concurrent.*;

 class HowManyVegetables implements Callable {
     public Integer call() throws Exception {
         System.out.println("Laila is counting vegetables...");
         Thread.sleep(15000);
         return 42;
     }
 }
 
 public class FutureDemo {
     public static void main(String args[]) throws ExecutionException, InterruptedException {
         System.out.println("Omar asks Laila how many vegetables are in the pantry.");
         ExecutorService executor = Executors.newSingleThreadExecutor();
         Future result = executor.submit(new HowManyVegetables());
         Future result1 = executor.submit(new HowManyVegetables());
         System.out.println("Omar can do other things while he waits for the result...");
         System.out.println("Laila responded with " + result.get());
         System.out.println("456.");
         System.out.println("Laila responded with " + result1.get());
         executor.shutdown();
     }
 }