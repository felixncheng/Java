import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author cheng_mboy
 */
public interface Warehouse extends Remote {

    double getPrice(String description) throws RemoteException;

}
