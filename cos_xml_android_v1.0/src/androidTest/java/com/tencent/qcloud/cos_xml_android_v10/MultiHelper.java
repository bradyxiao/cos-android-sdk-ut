package com.tencent.qcloud.cos_xml_android_v10;


import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.model.object.DeleteObjectResult;
import com.tencent.cos.xml.model.object.MultipartUploadHelper;
import com.tencent.qcloud.network.QCloudProgressListener;

import org.junit.Test;

/**
 * Created by bradyxiao on 2017/11/16.
 */

public class MultiHelper extends AndroidTestCase {

    private static final String TAG = "Unit_Test";
//
//    private QBaseServe qBaseServe;
//
//    public void init(){
//        qBaseServe = QBaseServe.getInstance(getContext());
//    }
//
//    @Test
//    public void test() throws Exception {
//        init();
//
//        final String bucket = "xy2";
//        final String cosPath = "multiput.txt";
//        MultipartUploadHelper multipartUploadService = new MultipartUploadHelper(qBaseServe.cosXmlService);;
//
//        multiHelper(multipartUploadService, bucket, cosPath);
//
//
//        DeleteObjectRequest request4 = new DeleteObjectRequest();
//        request4.setBucket(bucket);
//        request4.setCosPath(cosPath);
//        DeleteObjectResult result4 = qBaseServe.cosXmlService.deleteObject(request4);
//        Log.d(TAG, result4.printHeaders() + "|" + result4.printBody());
//
//    }
//
//    public void multiHelper(MultipartUploadHelper multipartUploadService, String bucket, String cosPath) throws Exception {
//        String srcPath = QBaseServe.crateFile(1 * 1024 * 1024 * 1024);
//        multipartUploadService.setBucket(bucket);
//        multipartUploadService.setCosPath(cosPath);
//        multipartUploadService.setSrcPath(srcPath);
//        multipartUploadService.setSign(600);
//        multipartUploadService.setSliceSize(5 * 1024 * 1024);
//        multipartUploadService.setProgressListener(new QCloudProgressListener() {
//            @Override
//            public void onProgress(long progress, long max) {
//                Log.i(TAG, "" + progress + "/" + max);
//            }
//        });
//        CosXmlResult cosXmlResult = multipartUploadService.upload();
//        Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
//        QBaseServe.deleteFile(srcPath);
//    }
//
//    public void cancel(MultipartUploadHelper multipartUploadService){
//        multipartUploadService.cancel();
//    }
}
