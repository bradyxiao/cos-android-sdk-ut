package com.tencent.qcloud.cos_xml_android_v10;


import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.object.AbortMultiUploadRequest;
import com.tencent.cos.xml.model.object.AppendObjectRequest;
import com.tencent.cos.xml.model.object.CompleteMultiUploadRequest;
import com.tencent.cos.xml.model.object.DeleteMultiObjectRequest;
import com.tencent.cos.xml.model.object.DeleteMultiObjectResult;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.model.object.GetObjectACLRequest;
import com.tencent.cos.xml.model.object.GetObjectRequest;
import com.tencent.cos.xml.model.object.HeadObjectRequest;
import com.tencent.cos.xml.model.object.InitMultipartUploadRequest;
import com.tencent.cos.xml.model.object.InitMultipartUploadResult;
import com.tencent.cos.xml.model.object.ListPartsRequest;
import com.tencent.cos.xml.model.object.ListPartsResult;
import com.tencent.cos.xml.model.object.OptionObjectRequest;
import com.tencent.cos.xml.model.object.PutObjectACLRequest;
import com.tencent.cos.xml.model.object.PutObjectRequest;
import com.tencent.cos.xml.model.object.UploadPartRequest;
import com.tencent.cos.xml.model.object.UploadPartResult;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bradyxiao on 2017/11/15.
 */

public class ObjectAsyncTest extends AndroidTestCase {

    private static final String TAG = "Unit_Test";

    volatile int isOver = 0;

    private String cosPath = "putobject2.txt";

    private String bucket = QBaseServe.objectBucket;


    public void putObjectTest() throws Exception {
        isOver = 0;
        cosPath = "putobject2.txt";
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        PutObjectRequest request = new PutObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSrcPath(srcPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.putObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.deleteFile(srcPath);
    }

    public void headObjectTest() throws InterruptedException {
        isOver = 0;
        HeadObjectRequest request = new HeadObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.headObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }



    public void optionObjectTest() throws InterruptedException {
        isOver = 0;
        OptionObjectRequest request = new OptionObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setOrigin("http://www.qcloud.com");
        request.setAccessControlMethod("get");
        request.setAccessControlHeaders("host");
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.optionObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }

    }

    public void putObjectACLTest() throws InterruptedException {
        isOver = 0;
        PutObjectACLRequest request = new PutObjectACLRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setXCOSACL(COSACL.PUBLIC_READ_WRITE);
        List<String> idList = new ArrayList<>();
        idList.add("uin/1278687956:uin/1278687956");
        request.setXCOSGrantRead(idList);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.putObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getObjectACLTest() throws InterruptedException {
        isOver = 0;
        GetObjectACLRequest request = new GetObjectACLRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }



    public void getObjectTest() throws InterruptedException {
        isOver = 0;
        String localPath = QBaseServe.context.getCacheDir().getPath();
        GetObjectRequest request = new GetObjectRequest(localPath);
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void deleteObjectTest() throws InterruptedException {
        DeleteObjectRequest request = new DeleteObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void deleteMultiObjectTest() throws InterruptedException {
        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest();
        request.setBucket(bucket);
        request.setQuiet(true);
        request.setObjectList(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteMultiObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void appendObjectTest() throws Exception {
        isOver = 0;
        cosPath = "append.txt";
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        AppendObjectRequest request = new AppendObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setPosition(0);
        request.setSrcPath(srcPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.appendObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
                QBaseServe.deleteObject(bucket,cosPath);
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.deleteFile(srcPath);
    }


    public void mulitUpload() throws Exception {
        String uploadId = initMulitupload();
        String eTag = uploadPartTest(uploadId);
        int partNumer = 1;
        Map<Integer, String> partNumberAndEtag = new HashMap<>();
        partNumberAndEtag.put(partNumer, eTag);
        completeMultiUploadTest(uploadId, partNumberAndEtag);
    }

    public String initMulitupload() throws Exception {
        isOver = 0;
        final String[] uploadId = new String[1];
        InitMultipartUploadRequest request = new InitMultipartUploadRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.initMultipartUploadAsync(request, new CosXmlResultListener() {
                @Override
                public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                    Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                    isOver = 1;
                    uploadId[0] = ((InitMultipartUploadResult)result).initMultipartUpload.uploadId;
                }

                @Override
                public void onFail(CosXmlRequest request, CosXmlResult result) {
                    String message = result.printError();
                    Log.d(TAG, "message = " + message);
                    isOver = 2;
                }
            });

        while (isOver == 0){
                Thread.sleep(5000);
        }

        return uploadId[0];

    }

    public String uploadPartTest(final String uploadId) throws Exception {
        final String srcPath = QBaseServe.crateFile(1024 * 1024);
        final String eTag[] = new String[1];
        UploadPartRequest request = new UploadPartRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setUploadId(uploadId);
        request.setSrcPath(srcPath);
        request.setPartNumber(1);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.uploadPartAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
                eTag[0] = ((UploadPartResult) result).getETag();
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.deleteFile(srcPath);
        return eTag[0];
    }

    public void completeMultiUploadTest(String uploadId, Map<Integer, String> partNumberAndEtag) throws InterruptedException {
        CompleteMultiUploadRequest request = new CompleteMultiUploadRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setUploadId(uploadId);
        request.setPartNumberAndETag(partNumberAndEtag);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.completeMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.deleteObject(bucket, cosPath);
    }

    public void abortMulti() throws Exception {
        String uploadId = initMulitupload();
        String eTag = uploadPartTest(uploadId);
        listUploadPartTest(uploadId);
        abortMultiuploadTest(uploadId);
    }

    public void listUploadPartTest(final String uploadId) throws InterruptedException {
        ListPartsRequest request = new ListPartsRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setUploadId(uploadId);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.listPartsAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void abortMultiuploadTest(String uploadId) throws InterruptedException {
        AbortMultiUploadRequest request = new AbortMultiUploadRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setUploadId(uploadId);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.abortMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    @Test
    public void test() throws Exception {
        QBaseServe.init(getContext());

        putObjectTest();
        headObjectTest();
        optionObjectTest();
        putObjectACLTest();
        getObjectACLTest();
        getObjectTest();
        deleteObjectTest();
        deleteMultiObjectTest();
        appendObjectTest();
        mulitUpload();
        abortMulti();

    }
}
