package com.tencent.qcloud.cos_xml_android_v12;

import android.content.Context;
import android.util.Log;

import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.common.Region;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.model.object.DeleteObjectResult;
import com.tencent.qcloud.core.network.auth.LocalCredentialProvider;
import com.tencent.qcloud.core.network.auth.QCloudCredentialProvider;

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
                CosXmlServiceConfig configuration = new CosXmlServiceConfig.Builder()
                        .isHttps()
                        .setAppidAndRegion(appid,region)
                        .setDebuggable(true)
                        .build();
                QCloudCredentialProvider qCloudCredentialProvider = new LocalCredentialProvider(
                        BuildConfig.SECRET_ID,
                        BuildConfig.SECRET_KEY,
                        60 * 60);
                cosXmlClient = new CosXmlService(context, configuration, qCloudCredentialProvider);
            }
        }
    }

    public static String createFile(long length) throws IOException {
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

    public static void delete(String bucket, String cosPath) throws CosXmlServiceException, CosXmlClientException {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, cosPath);
        deleteObjectRequest.setSign(600);
        DeleteObjectResult deleteObjectResult = cosXmlClient.deleteObject(deleteObjectRequest);
        Log.d(TAG, deleteObjectResult.printHeaders());
    }

    public static void delete(String localPath){
        File file = new File(localPath);
        if(!file.exists()){
            return;
        }else {
            file.delete();
        }
    }
}
