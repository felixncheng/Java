package com.chengmboy.controller.qrcode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng_mboy
 */
@RestController
public class QrcodeController {

    public static void main(String[] args) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode("hello", BarcodeFormat.QR_CODE, 300, 300, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream("test.png"));
    }

    @GetMapping("qrcode")
    public void getQrcode(HttpServletResponse response) throws IOException, WriterException {
        response.setContentType("image/png");
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode("hello", BarcodeFormat.QR_CODE, 300, 300, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

    }
}
