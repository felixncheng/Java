import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author cheng_mboy
 */
public class WarehouseServer {

    public static void main(String[] args) throws RemoteException, NamingException {
        System.out.println("Constructing server implementation");
        WarehouseImpl centralWarehouse = new WarehouseImpl();

        System.out.println("Binding server implementation to registry...");
        LocateRegistry.createRegistry(1099);
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:central_warehouse" ,centralWarehouse);
//127.0.0.1/
        System.out.println("Wait fro invocations from clients...");
    }

}
