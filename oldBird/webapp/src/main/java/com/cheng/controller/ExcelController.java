package com.cheng.controller;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cheng_mboy
 * @create 2017-11-29-0:25
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

  /*  @PostMapping
    public String excel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream input = file.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            StreamUtils.copy(input, output);
            List<SkuInfo> result = skuInfoList(
                    new ByteArrayInputStream(output.toByteArray())).stream()
                    .map(x -> retry(x.getLink(), x))
                    .collect(Collectors.toList());
            write2Excel(new ByteArrayInputStream(output.toByteArray()), result);
        } catch (IOException e) {
            e.printStackTrace();
            return "Fail!";
        }
        return "SUCCESS!";
    }*/
}
