import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.xml.internal.ws.util.StreamUtils;

/**
 * @author cheng_mboy
 */
public class Program {


    public static void main(String[] args) throws IOException {
      /*  int[] a = {5, 2, 9, 8, 6, 7, 1};
        //quickSort(a);
        bubbleSort(a);
          //selectSort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }*/
   /*     for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(()->{
                try {
                    long id = Thread.currentThread().getId();
                    URL url = new URL("http://localhost:8080/abc?name="+ id);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    long startReturn = new Date().getTime();
                    try (InputStream inputStream = urlConnection.getInputStream()) {
                        long endReturn = new Date().getTime();
                        byte[] buffer = new byte[8192];
                        int read;
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        while ((read = inputStream.read(buffer)) > 0) {
                            out.write(buffer,0,read);
                        }
                        System.out.println(id + " return cost" + (endReturn - startReturn) + new String(out.toByteArray()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }*/

/*
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH,1);
        System.out.println(c.getActualMaximum(Calendar.DATE));*/

        /*StringBuilder builder = new StringBuilder();

        for (int i = a.length() - 1; i >= 0; i--) {
            builder.append(a.charAt(i));
        }

        System.out.println(builder);*/
       /* char[] b = a.toCharArray();
        char[] c =new char[b.length];
        for (int i = 0; i < a.length(); i ++) {
            c[i] = b[a.length() - (i + 1)];
        }
        System.out.println(new String(c));*/

        /*Process process = Runtime.getRuntime().exec(
                new String[] { "wmic", "cpu", "get", "ProcessorId" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        System.out.println(property + ": " + serial);*/
     /*   List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(3);
        a.add(2);
        List<Integer> b = new ArrayList<>();
        a.stream().sorted().peek(b::add).collect(Collectors.toList());
        b.forEach(System.out::println);*/
    }


    private static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - (i + 1); j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    private static void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp;
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[k] > a[j]) {
                    k = j;
                }
            }
            temp = a[k];
            a[k] = a[i];
            a[i] = temp;
        }
    }

    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序

    }

    public static void quickSort(int a[]) {

        sort(a, 0, a.length - 1);
    }

}
