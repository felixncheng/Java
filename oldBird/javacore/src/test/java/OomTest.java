import java.util.LinkedList;
import java.util.List;

/**
 * @author cheng_mboy
 */
public class OomTest {

    private static List<Object> list = new LinkedList<>();
    public static void main(String[] args) {
        a();
    }
    private static void a() {
        while (true) {
            list.add(list);
            System.out.println(list.size());
        }
    }

}
