package delegate;

public class Main {
    public static void main(String[] args) {

        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("One");

        Client client = new Client(businessDelegate);
        client.doTask();

        businessDelegate.setServiceType("Two");
        client.doTask();
    }
}
