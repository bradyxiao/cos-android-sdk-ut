package com.tencent.qcloud.cosv4_1435.model;

import android.test.AndroidTestCase;

import org.junit.Test;

/**
 * Created by bradyxiao on 2018/1/23.
 */
public class GetObjectRequestTest extends AndroidTestCase {
    @Test
    public void setHeaders() throws Exception {
    }

    @Test
    public void setBodys() throws Exception {
    }

    @Test
    public void setLocalFileName() throws Exception {
    }

    @Test
    public void getLocalFileName() throws Exception {
    }

    @Test
    public void test() throws Exception {
//        QServer qServer = QServer.init(getContext());
//        String url = "http://androidtest-1253653367.cosgz.myqcloud.com/test.txt";
//        String savePath = getContext().getCacheDir().getPath();
//
//        /** GetObjectRequest 请求对象 */
//        GetObjectRequest getObjectRequest = new GetObjectRequest(url,savePath);
//        //若是设置了防盗链则需要签名；否则，不需要
//        /** 设置listener: 结果回调 */
//        getObjectRequest.setListener(new IDownloadTaskListener() {
//            @Override
//            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
//                long progress = (long) ((100.00 * currentSize) / totalSize);
//                Log.w(QServer.TAG,"progress =" + progress + "%");
//            }
//
//            @Override
//            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
//                String result = "cancel =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
//                String result = "code =" + cosResult.code + "; msg =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
//                String result ="code =" + cosResult.code + "; msg =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//        });
//        qServer.cosClient.getObject(getObjectRequest);
//
//        qServer.deleteLocalFile(savePath + "/" + "test.txt");
    }

}