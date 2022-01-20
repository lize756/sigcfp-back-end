package delegate;

public class BusinessDelegate {

    private BusinessService businessService;
    private BusinessLookUp businessLookUp = new BusinessLookUp();
    private String serviceType;

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask() {
        businessService = businessLookUp.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}
