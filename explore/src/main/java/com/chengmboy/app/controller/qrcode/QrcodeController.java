package com.chengmboy.app.controller.qrcode;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng_mboy
 */
@RestController
public class QrcodeController {

    public static void main(String[] args) throws WriterException, IOException, NotFoundException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode("www.baidu.com", BarcodeFormat.QR_CODE, 300, 300, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream("test.png"));

        BufferedImage image = ImageIO.read(new FileInputStream("test.png"));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Result result = new MultiFormatReader().decode(binaryBitmap);
        String text = result.getText();
        System.out.println(text);
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
