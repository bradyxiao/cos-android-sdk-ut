package com.tencent.qcloud.cos_xml_android_v130;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by bradyxiao on 2017/12/13.
 */

public class ObjectTest extends AndroidTestCase {
    static final String TAG = "Unit_Test";

    String bucket;
    String cosPath;


    public void putObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
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
        PutObjectResult result = QService.cosXmlClient.putObject(request);
        Log.d(TAG, result.printResult() + "\n" + result.accessUrl);

        QService.delete(srcPath);
    }

    public void headObjectTest() throws CosXmlServiceException, CosXmlClientException {
        HeadObjectRequest request = new HeadObjectRequest(bucket, cosPath);
        HeadObjectResult result = QService.cosXmlClient.headObject(request);
        Log.d(TAG, result.printResult());
    }

    public void getObjectTest() throws CosXmlServiceException, CosXmlClientException {

        String savePath = getContext().getCacheDir().getPath() + "/";
        GetObjectRequest request = new GetObjectRequest(bucket, cosPath, savePath);
        request.setSign(600, null, null);
        request.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, " complete：" + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        GetObjectResult result = QService.cosXmlClient.getObject(request);
        Log.d(TAG, result.printResult());

        QService.delete(request.getDownloadPath());
    }

    public void optionObjectTest() throws CosXmlServiceException, CosXmlClientException {
        String origin = "cloud.tencent.com";
        String method = "GET";
        OptionObjectRequest request = new OptionObjectRequest(bucket, cosPath, origin, method);
        OptionObjectResult result = QService.cosXmlClient.optionObject(request);
        Log.d(TAG, result.printResult());
    }

    public void copyObjectTest() throws CosXmlServiceException, CosXmlClientException {
        String destCosPath = "xml_copy.txt";
        CopyObjectRequest.CopySourceStruct copySourceStruct = new CopyObjectRequest.CopySourceStruct(
                QService.appid, bucket, QService.region, cosPath);
        CopyObjectRequest request = new CopyObjectRequest(bucket, destCosPath, copySourceStruct);
        CopyObjectResult result = QService.cosXmlClient.copyObject(request);
        Log.d(TAG, result.printResult());
        QService.delete(bucket, destCosPath);
    }

    public void putObjectACLTest() throws CosXmlServiceException, CosXmlClientException {

        PutObjectACLRequest request = new PutObjectACLRequest(bucket, cosPath);
        request.setXCOSACL(COSACL.PRIVATE);
        ACLAccount aclAccount = new ACLAccount();
        aclAccount.addAccount("1131975903", "1131975903");
        request.setXCOSGrantRead(aclAccount);
        request.setXCOSGrantWrite(aclAccount);
        PutObjectACLResult result = QService.cosXmlClient.putObjectACL(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        GetObjectACLRequest request = new GetObjectACLRequest(bucket, cosPath);
        GetObjectACLResult result = QService.cosXmlClient.getObjectACL(request);
        Log.d(TAG, result.printResult());
    }

    public void deleteObjectTest() throws CosXmlServiceException, CosXmlClientException {

        DeleteObjectRequest request = new DeleteObjectRequest(bucket, cosPath);
        DeleteObjectResult result = QService.cosXmlClient.deleteObject(request);
        Log.d(TAG, result.printResult());
    }

    public void deleteMultiObject() throws CosXmlServiceException, CosXmlClientException {

        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest(bucket, null);
        request.setQuiet(false);
        request.setObjectList(cosPath);
        DeleteMultiObjectResult result = QService.cosXmlClient.deleteMultiObject(request);
        Log.d(TAG, result.printResult());
    }


    public void appendObjectTest() throws IOException, CosXmlServiceException, CosXmlClientException {
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
        AppendObjectResult result = QService.cosXmlClient.appendObject(request);
        Log.d(TAG, result.printResult() + "\n" + result.accessUrl);

        QService.delete(bucket, cosPath);

        QService.delete(srcPath);
    }

    public void multiUploadPartObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        cosPath = "xml_multi.txt";
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        InitMultipartUploadResult result = QService.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printResult());

        int partNumber = 1;
        String uploadId = result.initMultipartUpload.uploadId;
        String srcPath = QService.createFile(1024 * 1024);
        UploadPartRequest request2 = new UploadPartRequest(bucket, cosPath,partNumber, srcPath, uploadId);
        request2.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, "complete: " + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        UploadPartResult result2 = QService.cosXmlClient.uploadPart(request2);
        Log.d(TAG, result2.printResult());

        ListPartsRequest request3 = new ListPartsRequest(bucket, cosPath, uploadId);
        ListPartsResult result3 =QService.cosXmlClient.listParts(request3);
        Log.d(TAG, result3.printResult());

        CompleteMultiUploadRequest request4 = new CompleteMultiUploadRequest(bucket, cosPath, uploadId,
                null);
        String etag = result2.eTag;
        request4.setPartNumberAndETag(partNumber, etag);
        CompleteMultiUploadResult result4 = QService.cosXmlClient.completeMultiUpload(request4);
        Log.d(TAG, result4.printResult());

        QService.delete( bucket, cosPath);
        QService.delete(srcPath);

    }

    public void abortMultiUploadPartObjectTest() throws CosXmlServiceException, CosXmlClientException {
        cosPath = "xml_test_multi2.txt";
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        InitMultipartUploadResult result = QService.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printResult());

        int partNumber = 1;
        String uploadId = result.initMultipartUpload.uploadId;
        byte[] data = new byte[1024 * 1024];
        UploadPartRequest request2 = new UploadPartRequest(bucket, cosPath,partNumber, data, uploadId);
        request2.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.d(TAG, "complete: " + complete + "| target: " + target + "|" + (long)(100.0 * complete/target));
            }
        });
        UploadPartResult result2 = QService.cosXmlClient.uploadPart(request2);
        Log.d(TAG, result2.printResult());

        ListPartsRequest request3 = new ListPartsRequest(bucket, cosPath, uploadId);
        ListPartsResult result3 = QService.cosXmlClient.listParts(request3);
        Log.d(TAG, result3.printResult());

        AbortMultiUploadRequest request4 = new AbortMultiUploadRequest(bucket, cosPath, uploadId);
        AbortMultiUploadResult result4 = QService.cosXmlClient.abortMultiUpload(request4);
        Log.d(TAG, result4.printResult());
    }



    @Test
    public void test() throws CosXmlServiceException, CosXmlClientException, IOException {
        bucket = QService.objectBucket;
        putObjectTest();
        headObjectTest();
        getObjectTest();
//        optionObjectTest();
        copyObjectTest();
//        putObjectACLTest();
        getBucketACLTest();
        deleteObjectTest();
        deleteMultiObject();
        appendObjectTest();
        multiUploadPartObjectTest();
        abortMultiUploadPartObjectTest();
    }

}
