package com.tencent.qcloud.cos_xml_android_v10;

import android.content.Context;
import android.os.Environment;

import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.common.Region;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.sign.CosXmlCredentialProvider;
import com.tencent.cos.xml.sign.CosXmlLocalCredentialProvider;
import com.tencent.cos.xml.sign.CosXmlSignaturePair;
import com.tencent.qcloud.network.exception.QCloudException;
import com.tencent.qcloud.network.tools.HexUtils;
import com.tencent.qcloud.network.tools.QCloudEncryptTools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by bradyxiao on 2017/8/26.
 * author bradyxiao
 */
public class QBaseServe {
    public static final String TAG = "Unit_Test";

    public static CosXmlService cosXmlClient;
    public static String appid = "1253653367";
    public static String region = Region.AP_Guangzhou.getRegion();

    public static String objectBucket = "xmlandroidtest";

    public static Context context;

    public static void init(Context context){
        synchronized (QBaseServe.class){
            if(cosXmlClient == null){
                QBaseServe.context = context;
                CosXmlServiceConfig cosXmlServiceConfig = new CosXmlServiceConfig(appid, region);
                cosXmlClient = new CosXmlService(context,cosXmlServiceConfig,
                        new CosXmlLocalCredentialProvider(BuildConfig.SECRET_ID,
                                BuildConfig.SECRET_KEY,600));
            }
        }

    }

    public static String crateFile(long length) throws Exception {
        String srcPath = context.getCacheDir().getPath() + "/"
                + System.currentTimeMillis() + ".txt";
        File file = new File(srcPath);
        if(!file.exists()){
            try {
                file.createNewFile();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
                randomAccessFile.setLength(length);
                randomAccessFile.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return srcPath;
    }

    public static void deleteFile(String path){
        File file = new File(path);
        if(file.exists() && file.isFile()){
            file.delete();
        }
    }

    public static void deleteObject(String bucket, String cosPath){
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest();
        deleteObjectRequest.setBucket(bucket);
        deleteObjectRequest.setCosPath(cosPath);
        deleteObjectRequest.setSign(600, null, null);
        try {
            cosXmlClient.deleteObject(deleteObjectRequest);
        } catch (QCloudException e) {

        }
    }
}
