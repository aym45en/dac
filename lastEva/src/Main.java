import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/*
 * group 2
 * ROUISSA RABAH
 * BRAHMIA MOHAMED HAYTHEM ABDERRAHMANE
 * BELMOKHI DIBADJ
 */

class Cal implements Callable{
    private String fileName;

    public Cal(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(",");
                for (String num : numbers) {
                    sum += Integer.parseInt(num);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future result = executor.submit(new Cal("list.txt"));
        Future result1 = executor.submit(new Cal("list1.txt"));
        int res1 = (int) result.get();
        int res2 = (int) result1.get();
        System.out.println("the rest for first file " + res1);
        System.out.println("the rest for sec file " +  res2);
        System.out.println("the total rest for all file " +  (res1+res2));
        executor.shutdown();
    }
}
