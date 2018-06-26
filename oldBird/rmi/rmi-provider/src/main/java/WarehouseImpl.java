import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cheng_mboy
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {

    private Map<String, Double> prices;

    public WarehouseImpl() throws RemoteException {
        prices = new HashMap<>();
        prices.put("a", 53.63);
        prices.put("b", 64.88);
    }

    @Override
    public double getPrice(String description) throws RemoteException {

        Double price = prices.get(description);
        return price == null ? 0 : price;
    }

}
