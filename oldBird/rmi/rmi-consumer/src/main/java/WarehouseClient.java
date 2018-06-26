import java.rmi.RemoteException;
import java.util.Enumeration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

/**
 * @author cheng_mboy
 */
public class WarehouseClient {

    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();

        System.out.println("RMI registry bindings :");
        Enumeration<NameClassPair> e = namingContext.list("rmi://127.0.0.1:1099");
        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        String url = "rmi://127.0.0.1:1099/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);

        String descr = "a";
        double price = centralWarehouse.getPrice(descr);
        System.out.println(descr + ": " + price);

    }
}
