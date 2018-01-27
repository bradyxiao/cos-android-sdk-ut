package com.tencent.qcloud.cos_xml_android_v1211;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.object.AbortMultiUploadRequest;
import com.tencent.cos.xml.model.object.AppendObjectRequest;
import com.tencent.cos.xml.model.object.CompleteMultiUploadRequest;
import com.tencent.cos.xml.model.object.CopyObjectRequest;
import com.tencent.cos.xml.model.object.DeleteMultiObjectRequest;
import com.tencent.cos.xml.model.object.DeleteObjectRequest;
import com.tencent.cos.xml.model.object.GetObjectACLRequest;
import com.tencent.cos.xml.model.object.GetObjectRequest;
import com.tencent.cos.xml.model.object.HeadObjectRequest;
import com.tencent.cos.xml.model.object.InitMultipartUploadRequest;
import com.tencent.cos.xml.model.object.InitMultipartUploadResult;
import com.tencent.cos.xml.model.object.ListPartsRequest;
import com.tencent.cos.xml.model.object.OptionObjectRequest;
import com.tencent.cos.xml.model.object.PutObjectACLRequest;
import com.tencent.cos.xml.model.object.PutObjectRequest;
import com.tencent.cos.xml.model.object.UploadPartRequest;
import com.tencent.cos.xml.model.object.UploadPartResult;
import com.tencent.cos.xml.model.tag.ACLAccount;
import com.tencent.cos.xml.model.tag.ACLAccounts;
import com.tencent.qcloud.core.network.QCloudProgressListener;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bradyxiao on 2017/11/15.
 */

public class ObjectAsyncTest extends AndroidTestCase {
    private static final String TAG = "Unit_Test";

    private String cosPath;

    private String bucket = QBaseServe.objectBucket;
    volatile int isOver = 0;


    public void putObjectTest() throws Exception {
        isOver = 0;
        cosPath = "putobject.txt";
        String srcPath = QBaseServe.createFile(1024 * 1024);
        PutObjectRequest request = new PutObjectRequest(bucket, cosPath, srcPath);
        QBaseServe.cosXmlClient.putObjectAsync(request, new CosXmlResultListener() {

            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.delete(srcPath);
    }

    public void headObjectTest() throws InterruptedException {
        HeadObjectRequest request = new HeadObjectRequest(bucket, cosPath);
        QBaseServe.cosXmlClient.headObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }
    }


    public void optionObjectTest() throws InterruptedException {
        String origin = "http://cloud.tencent.com";
        String method = "PUT";
        OptionObjectRequest request = new OptionObjectRequest(bucket, cosPath, origin, method);
        QBaseServe.cosXmlClient.optionObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }

    }

    public void putObjectACLTest() throws InterruptedException {
        PutObjectACLRequest request = new PutObjectACLRequest(bucket, cosPath);
        request.setXCOSACL(COSACL.PUBLIC_READ_WRITE);
        ACLAccount aclAccount = new ACLAccount("1278687956", "1278687956");
        ACLAccounts aclAccounts = new ACLAccounts();
        aclAccounts.addACLAccount(aclAccount);
        request.setXCOSGrantRead(aclAccounts);
        QBaseServe.cosXmlClient.putObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }
            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getObjectACLTest() throws InterruptedException {
        GetObjectACLRequest request = new GetObjectACLRequest(bucket, cosPath);
        QBaseServe.cosXmlClient.getObjectACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getObjectTest() throws InterruptedException {
        final String localPath = Environment.getExternalStorageDirectory().getPath();
        GetObjectRequest request = new GetObjectRequest(bucket, cosPath, localPath);
        QBaseServe.cosXmlClient.getObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                int index = cosPath.lastIndexOf("/");
                String path;
                if(index < 0){
                    path = localPath + "/" + cosPath;
                }else{
                    path = localPath + cosPath.substring(index);
                }
                QBaseServe.delete(path);
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void copyObjectTest() throws Exception {
        isOver = 0;
        CopyObjectRequest.CopySourceStruct copySourceStruct = new CopyObjectRequest.CopySourceStruct(
                QBaseServe.appid, bucket, QBaseServe.region, cosPath);
        final String copyPath = "copy"+ cosPath.substring(cosPath.lastIndexOf("."));

        CopyObjectRequest request2 = new CopyObjectRequest(bucket,
                copyPath, copySourceStruct);
        QBaseServe.cosXmlClient.copyObjectAsync(request2,  new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                try {
                    QBaseServe.delete(bucket, copyPath);
                } catch (CosXmlServiceException e) {

                } catch (CosXmlClientException e) {

                }
                isOver = 1;

            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }
    }


    public void deleteObjectTest() throws InterruptedException {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, cosPath);
        QBaseServe.cosXmlClient.deleteObjectAsync(request, new CosXmlResultListener() {

            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());

                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {

                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;

            }
        });
        while (isOver == 0){
            Thread.sleep(5000);
        }

    }

    public void deleteMulitObjectTest() throws CosXmlServiceException, CosXmlClientException, InterruptedException {
        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest(bucket, null);
        request.setQuiet(true);
        request.setObjectList(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteMultiObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());

                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void appendObjectTest() throws Exception {

        isOver = 0;
        String srcPath = QBaseServe.createFile(1024 * 1024);
        cosPath = "append.txt";
        long position = 0;
        AppendObjectRequest request = new AppendObjectRequest(bucket, cosPath, srcPath, position);
        QBaseServe.cosXmlClient.appendObjectAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.delete(bucket,cosPath);
    }

    public void multiUploadObjectTest() throws Exception {
        String uploadId = initMulitupload();
        int partNumer = 1;
        String eTag = uploadPartTest(partNumer, uploadId);
        Map<Integer, String> partNumberAndEtag = new HashMap<>();
        partNumberAndEtag.put(partNumer, eTag);
        completeMultiUploadTest(uploadId, partNumberAndEtag);
    }

    public String initMulitupload() throws Exception {
        isOver = 0;
        final String[] uploadId = new String[1];
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        request.setSign(600,null,null);

        QBaseServe.cosXmlClient.initMultipartUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
                uploadId[0] = ((InitMultipartUploadResult)result).initMultipartUpload.uploadId;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0) {
            Thread.sleep(5000);
        }
        return uploadId[0];
    }

    public String uploadPartTest(int partNumer, final String uploadId) throws Exception {
        isOver = 0;
        cosPath = "xml_multi.txt";
        final String srcPath = QBaseServe.createFile(1024 * 1024);
        final String eTag[] = new String[1];
        UploadPartRequest request = new UploadPartRequest(bucket, cosPath,partNumer, srcPath, uploadId);
        request.setProgressListener(new QCloudProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, "complete: " + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.uploadPartAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
                eTag[0] = ((UploadPartResult) result).getETag();
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.getMessage());
                isOver = 2;

            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.delete(srcPath);
        return eTag[0];
    }

    public void listUploadPartTest(final String uploadId) throws InterruptedException {
        isOver = 0;
        ListPartsRequest request = new ListPartsRequest(bucket, cosPath, uploadId);
        request.setUploadId(uploadId);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.listPartsAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0) {
            Thread.sleep(5000);
        }
    }

    public void completeMultiUploadTest(String uploadId, Map<Integer, String> partNumberAndEtag) throws InterruptedException, CosXmlClientException, CosXmlServiceException {
        isOver = 0;
        CompleteMultiUploadRequest request = new CompleteMultiUploadRequest(bucket, cosPath, uploadId,
                partNumberAndEtag);
        QBaseServe.cosXmlClient.completeMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
        QBaseServe.delete(bucket, cosPath);
    }

    public void abortMultiuploadTest(String uploadId) throws InterruptedException {
        isOver=0;
        AbortMultiUploadRequest request = new AbortMultiUploadRequest(bucket, cosPath, uploadId);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.abortMultiUploadAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException) {
                Log.d(TAG, clientException == null ? serviceException.getMessage() : clientException.getMessage());
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
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
        QBaseServe.init(getContext());

        putObjectTest();
        headObjectTest();
        optionObjectTest();
        putObjectACLTest();
        getObjectACLTest();
        getObjectTest();
        deleteObjectTest();
        deleteMulitObjectTest();
        appendObjectTest();
        multiUploadObjectTest();
        abortMultiUploadPartObjectTest();
    }
}
