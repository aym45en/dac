
class Letters implements Runnable{
    char phrase;
    public Letters(char phrase){
        this.phrase=phrase;
    }

    @Override
    public void run() {
        System.out.println(phrase);
    }
}


public class HiWorld {
    public static void main(String[] args) throws InterruptedException {
        String phrase  = "Hi World!";
        for (int i = 0; i < phrase.length(); i++) {
            Thread t = new Thread(new Letters(phrase.charAt(i)));
            t.start();
        }
    }
}
