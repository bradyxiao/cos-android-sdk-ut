package com.tencent.cosV4_14318;

import android.content.Context;
import android.util.Log;

import com.tencent.cos.COSClient;
import com.tencent.cos.COSConfig;
import com.tencent.cos.common.COSEndPoint;
import com.tencent.cos.model.COSRequest;
import com.tencent.cos.model.COSResult;
import com.tencent.cos.model.DeleteObjectRequest;
import com.tencent.cos.task.listener.ICmdTaskListener;
import com.tencent.cosV4_14318.utils.LocalCredentialProvider;
import com.tencent.qcloud.cosv4_14318.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by bradyxiao on 2018/1/23.
 */

public class QServer {

    public static final String TAG = "UnitTest";

    public String appid;
    public String bucket;
    public String region;
    public COSClient cosClient;
    public COSConfig config;

    public Context context;

    public static QServer instance;


    private QServer(Context context){
       if(cosClient == null){
           this.context = context;
           config = new COSConfig();
           region = COSEndPoint.COS_GZ.getCode();
           config.setEndPoint(region);
           appid = "1253653367";
           bucket = "androidtest";
           cosClient = new COSClient(context,appid,config,"xxxx");
       }
    }

    public static QServer init(Context context){
        if(instance == null){
            synchronized(QServer.class){
                instance = new QServer(context);
            }
        }
        return instance;
    }

    public String getSignOnce(String fileId){
        String secretId = BuildConfig.SECRET_ID;
        String secretKey = BuildConfig.SECRET_KEY;
        LocalCredentialProvider localCredentialProvider = new LocalCredentialProvider(secretKey);
        return localCredentialProvider.getSign(appid, bucket, secretId, fileId, 60 * 60);
    }

    public String getSign(){
        String secretId = BuildConfig.SECRET_ID;
        String secretKey = BuildConfig.SECRET_KEY;
        LocalCredentialProvider localCredentialProvider = new LocalCredentialProvider(secretKey);
        return localCredentialProvider.getSign(appid, bucket, secretId, null, 60 * 60);
    }


    public String createFile(long size) throws IOException {
        String srcPath = context.getCacheDir().getPath() + "/"
                + System.currentTimeMillis() + ".txt";
        File file = new File(srcPath);
        if(!file.exists()){
            try {
                file.createNewFile();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
                randomAccessFile.setLength(size);
                randomAccessFile.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return srcPath;
    }

    public void deleteLocalFile(String path){
        if(path == null) return;
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    public void deleteObject(String bucket, String cosPath){
        if(bucket == null || cosPath == null)return;
        /** DeleteObjectRequest 请求对象 */
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest();
        /** 设置Bucket */
        deleteObjectRequest.setBucket(bucket);
        /** 设置cosPath :远程路径*/
        deleteObjectRequest.setCosPath(cosPath);
        /** 设置sign: 签名，此处使用单次签名 */
        deleteObjectRequest.setSign(getSignOnce(cosPath));
        /** 设置listener: 结果回调 */
        deleteObjectRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除成功 : code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除失败 : code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(TAG,result);
            }
        });
        /** 发送请求：执行 */
        cosClient.deleteObject(deleteObjectRequest);
    }
}
