import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;


import com.SkuInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author cheng_mboy
 */

public class TestExcel {

    public static void main(String[] args) throws InterruptedException {
        int needDo;
        int count = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<SkuInfo> source = mapper.readValue(new File("skuInfo.json"), new TypeReference<List<SkuInfo>>() {
            });
            List<String> skus = source.stream().map(SkuInfo::getSku).collect(Collectors.toList());
            File file = new File("C:/Users/Administrator/Desktop/test.xlsx");
            System.out.println("正在读取excel");
            Workbook wb = new XSSFWorkbook(new FileInputStream(file));
            List<SkuInfo> list = skuInfoList(wb, skus);
            do {
                try {
                    if (count > 10) {
                        System.out.println("calculate fail! " + "total execute: " + count);
                        System.exit(0);
                    }
                    count++;
                    needDo = amazon(source, list, wb, file);
                    Thread.sleep(5000);
                } catch (Exception ignored) {
                    throw new RuntimeException("程序执行错误！");
                }
            } while (needDo > 0);
            System.out.println("calculate success! " + "total execute: " + count);
        } catch (Exception e) {
            System.out.println("请检查excel文件和json是否正确！");
        }
    }

    private static int amazon(List<SkuInfo> source, List<SkuInfo> list, Workbook wb, File file) throws Exception {
        long start = System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        List<SkuInfo> newData = new ArrayList<>();
        List<SkuInfo> newSource = new ArrayList<>();
        for (SkuInfo item : list) {
            SkuInfo data = retry(item);
            if (data != null) {
                newData.add(data);
            }
        }
        if (source != null && source.size() != 0) {
            newSource.addAll(source);
        }
        newSource.addAll(newData);
        mapper.writeValue(new File("skuInfo.json"), newSource);
        int i = 0;
        if (newData.size() > 0) {
            i = write2Excel(wb, newData);
            try {
                wb.write(new FileOutputStream(file));
            } catch (Exception e) {
                System.out.println("另一设备正在使用该文件！");
            }
        }
        int total = list.size();
        System.out.println("Total:" + total + " COST:" + (System.currentTimeMillis() - start) / 1000 + "s");
        int add = newData.size();
        System.out.println("success read:" + add + " write:" + i + " fail read:" + (total - add));
        return list.size();
    }

    private static SkuInfo retry(SkuInfo info) {
        try {
            return getData(info);
        } catch (Exception e) {
            System.err.println("获取失败：https://www.amazon.com/dp/" + info.getFbaListing());
        }
        return null;
    }

    private static int write2Excel(Workbook wb, List<SkuInfo> newData) throws Exception {
        System.out.println("开始写入");
        int rankIndex = -1;
        int reviewsIndex = -1;
        int skuIndex = -1;
        boolean isWrite = false;
        Sheet sheet = wb.getSheetAt(0);
        Row title = sheet.getRow(0);
        int sum = 0;
        for (int i = 0; i < title.getLastCellNum(); i++) {
            String value = title.getCell(i).toString();
            if (Objects.equals("Rank", value)) {
                rankIndex = i;
            }
            if (Objects.equals("Review", value)) {
                reviewsIndex = i;
            }
            if (Objects.equals("SKU", value)) {
                skuIndex = i;
            }
        }
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            String sku = row.getCell(skuIndex).toString();
            for (SkuInfo info : newData) {
                if (Objects.equals(sku, info.getSku())) {
                    Cell rank = row.getCell(rankIndex);
                    if (rank != null && rank.toString().trim().length() != 0) {
                        rank.setCellValue(info.getRank());
                        isWrite = true;
                    }
                    Cell review = row.getCell(reviewsIndex);
                    if (review != null && review.toString().trim().length() != 0) {
                        review.setCellValue(info.getReview());
                        isWrite = true;
                    }
                    if (isWrite) {
                        System.out.println("写入成功： " + info);
                        sum++;
                    }
                    break;
                }
            }
        }
        return sum;
    }

    private static SkuInfo getData(SkuInfo info) throws Exception {
        long start = System.currentTimeMillis();
        String spec = "https://www.amazon.com/dp/" + info.getFbaListing();
        URLConnection connection = new URL(spec).openConnection();
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains("out of 5 stars</span>")) {
                    int begin = s.indexOf("alt\">");
                    int end = s.indexOf("out of 5");
                    String rank = s.substring(begin + 5, end);
                    info.setRank(Float.parseFloat(rank));
                }
                if (s.contains("customer reviews</span>")) {
                    int begin = s.indexOf("base\">");
                    int end = s.indexOf("customer reviews</span>");
                    String reviews = s.substring(begin + 6, end - 1);
                    info.setReview(Integer.parseInt(reviews));
                    break;
                }
                if (s.contains("Be the first to review this item")) {
                    break;
                }
            }
        }
        System.out.println("已获取" + spec + " " + (System.currentTimeMillis() - start) / 1000 + "s");
        return info;
    }

    private static List<SkuInfo> skuInfoList(Workbook wb, List<String> already) throws Exception {
        System.out.println("正在获取excel信息");
        int skuIndex = -1;
        int linkIndex = -1;
        List<SkuInfo> data = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        Row Title = sheet.getRow(0);
        for (int j = 0; j < Title.getLastCellNum(); j++) {
            Cell cell = Title.getCell(j);
            if ("FBA Listing".equals(cell.toString())) {
                linkIndex = j;
            }
            if ("SKU".equals(cell.toString())) {
                skuIndex = j;
            }
        }
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            SkuInfo info = new SkuInfo();
            String sku = row.getCell(skuIndex).toString().trim();
            if (already != null) {
                if (!already.contains(sku) && skuIndex != -1 && linkIndex != -1) {
                    info.setSku(sku);
                    info.setFbaListing(row.getCell(linkIndex).toString().trim());
                    info.setRowNum(i);
                    data.add(info);
                }
            } else {
                if (skuIndex != -1 && linkIndex != -1) {
                    info.setSku(sku);
                    info.setFbaListing(row.getCell(linkIndex).toString().trim());
                    info.setRowNum(i);
                    data.add(info);
                }
            }

        }
        return data;
    }
}

