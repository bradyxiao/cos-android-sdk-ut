package com.tencent.qcloud.cos_xml_android_v130;

import android.app.Application;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.util.Log;


import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.object.AbortMultiUploadRequest;
import com.tencent.cos.xml.model.object.AbortMultiUploadResult;
import com.tencent.cos.xml.model.object.AppendObjectRequest;
import com.tencent.cos.xml.model.object.AppendObjectResult;
import com.tencent.cos.xml.model.object.CompleteMultiUploadRequest;
import com.tencent.cos.xml.model.object.CompleteMultiUploadResult;
import com.tencent.cos.xml.model.object.CopyObjectRequest;
import com.tencent.cos.xml.model.object.CopyObjectResult;
import com.tencent.cos.xml.model.object.DeleteMultiObjectRequest;
import com.tencent.cos.xml.model.object.DeleteMultiObjectResult;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.model.object.DeleteObjectResult;
import com.tencent.cos.xml.model.object.GetObjectACLRequest;
import com.tencent.cos.xml.model.object.GetObjectACLResult;
import com.tencent.cos.xml.model.object.GetObjectRequest;
import com.tencent.cos.xml.model.object.GetObjectResult;
import com.tencent.cos.xml.model.object.HeadObjectRequest;
import com.tencent.cos.xml.model.object.HeadObjectResult;
import com.tencent.cos.xml.model.object.InitMultipartUploadRequest;
import com.tencent.cos.xml.model.object.InitMultipartUploadResult;
import com.tencent.cos.xml.model.object.ListPartsRequest;
import com.tencent.cos.xml.model.object.ListPartsResult;
import com.tencent.cos.xml.model.object.OptionObjectRequest;
import com.tencent.cos.xml.model.object.OptionObjectResult;
import com.tencent.cos.xml.model.object.PutObjectACLRequest;
import com.tencent.cos.xml.model.object.PutObjectACLResult;
import com.tencent.cos.xml.model.object.PutObjectRequest;
import com.tencent.cos.xml.model.object.PutObjectResult;
import com.tencent.cos.xml.model.object.UploadPartRequest;
import com.tencent.cos.xml.model.object.UploadPartResult;
import com.tencent.cos.xml.model.tag.ACLAccount;
import com.tencent.qcloud.cos_xml_android_v130.QService;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by bradyxiao on 2017/12/13.
 */

public class ObjectAsyncTest extends AndroidTestCase {
    static final String TAG = "Unit_Test";

    volatile boolean isOver = false;

    String bucket;
    String cosPath;


    public void putObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        isOver = false;
        String srcPath = QService.createFile(1024 * 1024);
        cosPath = "putobject.txt";
        PutObjectRequest request = new PutObjectRequest(bucket, cosPath,
                srcPath);
        request.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, " complete：" + complete + "| target: " + target  + "|" + (long)(100.0 * complete/target));
            }
        });
       QService.cosXmlClient.putObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        QService.delete(srcPath);
    }

    public void headObjectTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        HeadObjectRequest request = new HeadObjectRequest(bucket, cosPath);
        QService.cosXmlClient.headObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void getObjectTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        String savePath = getContext().getCacheDir().getPath() + "/";
        GetObjectRequest request = new GetObjectRequest(bucket, cosPath, savePath);
        request.setSign(600, null, null);
        request.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, " complete：" + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        QService.cosXmlClient.getObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        QService.delete(request.getDownloadPath());
    }

    public void optionObjectTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        String origin = "cloud.tencent.com";
        String method = "GET";
        OptionObjectRequest request = new OptionObjectRequest(bucket, cosPath, origin, method);
        QService.cosXmlClient.optionObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyObjectTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        String destCosPath = "xml_copy.txt";
        CopyObjectRequest.CopySourceStruct copySourceStruct = new CopyObjectRequest.CopySourceStruct(
                QService.appid, bucket, QService.region, cosPath);
        CopyObjectRequest request = new CopyObjectRequest(bucket, destCosPath, copySourceStruct);
        QService.cosXmlClient.copyObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        QService.delete(bucket, destCosPath);
    }

    public void putObjectACLTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        PutObjectACLRequest request = new PutObjectACLRequest(bucket, cosPath);
        request.setXCOSACL(COSACL.PRIVATE);
        ACLAccount aclAccount = new ACLAccount();
        aclAccount.addAccount("1131975903", "1131975903");
        request.setXCOSGrantRead(aclAccount);
        request.setXCOSGrantWrite(aclAccount);
        QService.cosXmlClient.putObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void getBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        GetObjectACLRequest request = new GetObjectACLRequest(bucket, cosPath);
        QService.cosXmlClient.getObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteObjectTest() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, cosPath);
        QService.cosXmlClient.deleteObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMultiObject() throws CosXmlServiceException, CosXmlClientException {
        isOver = false;
        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest(bucket, null);
        request.setQuiet(false);
        request.setObjectList(cosPath);
        QService.cosXmlClient.deleteMultiObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void appendObjectTest() throws IOException, CosXmlServiceException, CosXmlClientException {
        isOver = false;
        String srcPath = QService.createFile(1024 * 1024);
        cosPath = "append.txt";
        AppendObjectRequest request = new AppendObjectRequest(bucket, "append.txt",
                srcPath,0);
        request.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, " complete：" + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        QService.cosXmlClient.appendObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });
        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        QService.delete(bucket, cosPath);

        QService.delete(srcPath);
    }

    public void multiUploadPartObjectTest() throws Exception {
        String uploadId = initMulitupload();
        int partNumer = 1;
        String eTag = uploadPartTest(partNumer, uploadId);
        Map<Integer, String> partNumberAndEtag = new HashMap<>();
        partNumberAndEtag.put(partNumer, eTag);
        completeMultiUploadTest(uploadId, partNumberAndEtag);
    }

    public String initMulitupload() throws Exception {
        isOver = false;
        final String[] uploadId = new String[1];
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        request.setSign(600,null,null);
        QService.cosXmlClient.initMultipartUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
                uploadId[0] = ((InitMultipartUploadResult)result).initMultipartUpload.uploadId;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });

        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return uploadId[0];
    }


    public String uploadPartTest(int partNumer, final String uploadId) throws Exception {
        isOver = false;
        cosPath = "xml_multi.txt";
        final String srcPath = QService.createFile(1024 * 1024);
        final String eTag[] = new String[1];
        UploadPartRequest request = new UploadPartRequest(bucket, cosPath,partNumer, srcPath, uploadId);
        request.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, "complete: " + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        request.setSign(600,null,null);
        QService.cosXmlClient.uploadPartAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
                eTag[0] = ((UploadPartResult) result).eTag;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });

        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        QService.delete(srcPath);
        return eTag[0];
    }

    public void listUploadPartTest(final String uploadId) throws InterruptedException {
        isOver = false;
        ListPartsRequest request = new ListPartsRequest(bucket, cosPath, uploadId);
        request.setUploadId(uploadId);
        request.setSign(600,null,null);
        QService.cosXmlClient.listPartsAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });

        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void completeMultiUploadTest(String uploadId, Map<Integer, String> partNumberAndEtag) throws InterruptedException, CosXmlClientException, CosXmlServiceException {
        isOver = false;
        CompleteMultiUploadRequest request = new CompleteMultiUploadRequest(bucket, cosPath, uploadId,
                partNumberAndEtag);
        QService.cosXmlClient.completeMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });

        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        QService.delete(bucket, cosPath);
    }

    public void abortMultiuploadTest(String uploadId) throws InterruptedException {
        isOver = false;
        AbortMultiUploadRequest request = new AbortMultiUploadRequest(bucket, cosPath, uploadId);
        request.setSign(600,null,null);
        QService.cosXmlClient.abortMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.toString());
                isOver = true;
            }
        });

        while (!isOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void abortMultiUploadPartObjectTest() throws Exception {
        String uploadId = initMulitupload();
        String eTag = uploadPartTest(1, uploadId);
        listUploadPartTest(uploadId);
        abortMultiuploadTest(uploadId);
    }



    @Test
    public void test() throws Exception {
        bucket = QService.objectBucket;
        putObjectTest();
        headObjectTest();
        getObjectTest();
        optionObjectTest();
        copyObjectTest();
        putObjectACLTest();
        getBucketACLTest();
        deleteObjectTest();
        deleteMultiObject();
        appendObjectTest();
        multiUploadPartObjectTest();
        abortMultiUploadPartObjectTest();
    }

}
