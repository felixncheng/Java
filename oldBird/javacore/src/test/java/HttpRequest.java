import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author cheng_mboy
 */
public class HttpRequest {

    private static URLConnection sendGet(String url) {
        URLConnection connection = null;
        try {
            URL realUrl = new URL(url);
            long start2 = new Date().getTime();
            connection = realUrl.openConnection();
            System.out.println("openConnection时间: " + (new Date().getTime()-start2));
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("cookie", "token=eyJhbGciOiJIUzI1NiJ9." +
                    "eyJpYXQiOjE1MDkwODU2NjMsInN1YiI6IntcInVzZXJJZFwiOjE3MX0iLCJleHAiOjE1MDkxNzIwNjN9." +
                    "woeuJJetbtKEvfnwiWWV8Rd0PVR2XcHkjfXuauXBGvI");
            long start = new Date().getTime();
            connection.connect();
            System.out.println("connect时间: " + (new Date().getTime()-start));
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return connection;
    }


    public static void main(String[] args) {
        int j = 0;
        int sum = 0;
        int first = 0;
        boolean flag = true;
        URLConnection connection =null;
        String url = "http://localhost:9084/api/teacher/class-failed-questions/chapters?subjectId=3&classId=8&year=2017&semester=1";
        for (int i = 0; i < 100; i++) {
            Long start = new Date().getTime();
            connection = HttpRequest.sendGet(url);
            int diff = (int) (new Date().getTime() - start);
            if (flag) {
                first = diff;
                flag = false;
            }
            if (diff > 50) j++;
            sum += diff;
        }
        System.out.println("测试url ：" + url);
        System.out.println("程序共执行了100次");
        System.out.println("第一次：" + first);
        System.out.println("平均时间：" + sum / 100);
        System.out.println("超出50毫秒次数：" + j);
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}