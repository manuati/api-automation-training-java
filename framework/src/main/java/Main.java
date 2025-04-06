import base.ServiceBase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Why are you running me, bro?");

        ServiceBase<String> serviceBase = new ServiceBase<>("");
        try {
            serviceBase.authenticate();
            serviceBase.authenticate();
        } catch (Exception e) {
            System.out.println("Shit's fucked: "+e.getMessage());
        }
    }
}