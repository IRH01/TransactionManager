package com.irh.transaction.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.irh.transaction.services.QcodeService;
import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User:iritchie.ren
 * Date: 2016/2/24
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class QcodeServiceImpl implements QcodeService{

    private int width;
    private int height;
    private String format;
    private String fileLocation;
    private String imgUrlHead;

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setFormat(String format){
        this.format = format;
    }

    public void setFileLocation(String fileLocation){
        this.fileLocation = fileLocation;
    }

    public void setImgUrlHead(String imgUrlHead){
        this.imgUrlHead = imgUrlHead;
    }


    @Override
    public String gerateQcodeUrl(String content) throws WriterException, IOException{

        String uuid = UUID.randomUUID().toString();

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        LocalDate localDate = LocalDate.now();
        String urlPath = localDate.toString("yyyy/MM/dd") + "/" + uuid + "." + format;
        String filename = fileLocation + urlPath;
        File outputFile = new File(filename);
        FileUtils.touch(outputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);

        return imgUrlHead + urlPath;
    }
}
