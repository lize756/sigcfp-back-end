package delegate;

public class TwoService implements BusinessService {

    @Override
    public void doProcessing() {
        System.out.println("Processed Service Two");
    }
}
