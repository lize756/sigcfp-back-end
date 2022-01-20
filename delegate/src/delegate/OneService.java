package delegate;

public class OneService implements BusinessService {

    @Override
    public void doProcessing() {
        System.out.println("Processed Service One");
    }


}
