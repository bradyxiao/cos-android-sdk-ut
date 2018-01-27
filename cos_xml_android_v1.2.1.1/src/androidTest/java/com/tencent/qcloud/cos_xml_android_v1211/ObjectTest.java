package com.tencent.qcloud.cos_xml_android_v1211;

import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.object.AbortMultiUploadRequest;
import com.tencent.cos.xml.model.object.AbortMultiUploadResult;
import com.tencent.cos.xml.model.object.AppendObjectRequest;
import com.tencent.cos.xml.model.object.AppendObjectResult;
import com.tencent.cos.xml.model.object.CompleteMultiUploadRequest;
import com.tencent.cos.xml.model.object.CompleteMultiUploadResult;
import com.tencent.cos.xml.model.object.CopyObjectRequest;
import com.tencent.cos.xml.model.object.CopyObjectResult;
import com.tencent.cos.xml.model.object.DeleteMultiObjectRequest;
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
import com.tencent.cos.xml.model.tag.ACLAccounts;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bradyxiao on 2017/11/15.
 */

public class ObjectTest extends AndroidTestCase {

    private static final String TAG = "Unit_Test";

    private String bucket = QBaseServe.objectBucket;
    private String cosPath;

    public void putObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        cosPath = "putobject.txt";
        String srcPath = QBaseServe.createFile(1024 * 1024);
        PutObjectRequest request = new PutObjectRequest(bucket, cosPath, srcPath);
        PutObjectResult result = QBaseServe.cosXmlClient.putObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        QBaseServe.delete(srcPath);
    }

    public void headObjectTest() throws CosXmlServiceException, CosXmlClientException {
        HeadObjectRequest request = new HeadObjectRequest(bucket, cosPath);
        HeadObjectResult result = QBaseServe.cosXmlClient.headObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void optionObjectTest() throws CosXmlServiceException, CosXmlClientException {
        String origin = "http://cloud.tencent.com";
        String method = "PUT";
        OptionObjectRequest request = new OptionObjectRequest(bucket, cosPath, origin, method);
        OptionObjectResult result = QBaseServe.cosXmlClient.optionObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    public void putObjectACLTest() throws CosXmlServiceException, CosXmlClientException {
        PutObjectACLRequest request = new PutObjectACLRequest(bucket, cosPath);
        request.setXCOSACL(COSACL.PUBLIC_READ_WRITE);
        ACLAccount aclAccount = new ACLAccount("1278687956", "1278687956");
        ACLAccounts aclAccounts = new ACLAccounts();
        aclAccounts.addACLAccount(aclAccount);
        request.setXCOSGrantRead(aclAccounts);
        try {
            PutObjectACLResult result = QBaseServe.cosXmlClient.putObjectACL(request);
            Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void getObjectACLTest() throws CosXmlServiceException, CosXmlClientException {
        GetObjectACLRequest request = new GetObjectACLRequest(bucket, cosPath);
        GetObjectACLResult result = QBaseServe.cosXmlClient.getObjectACL(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getObjectTest() throws CosXmlServiceException, CosXmlClientException {
        String localPath = getContext().getExternalCacheDir().getPath();
        GetObjectRequest request = new GetObjectRequest(bucket, cosPath, localPath);
        GetObjectResult result = QBaseServe.cosXmlClient.getObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        int index = cosPath.lastIndexOf("/");
        String path;
        if(index < 0){
            path = localPath + "/" + cosPath;
        }else{
            path = localPath + cosPath.substring(index);
        }

        QBaseServe.delete(path);
    }

    public void copyObjectTest() throws CosXmlServiceException, CosXmlClientException, UnsupportedEncodingException {
        CopyObjectRequest.CopySourceStruct copySourceStruct = new CopyObjectRequest.CopySourceStruct(
                QBaseServe.appid, bucket, QBaseServe.region, cosPath);
        String copyPath = "copy_"+ cosPath;
        CopyObjectRequest request = new CopyObjectRequest(bucket,
                copyPath, copySourceStruct);
        CopyObjectResult result = QBaseServe.cosXmlClient.copyObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        QBaseServe.delete(bucket, copyPath);
    }

    public void deleteObjectTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, cosPath);
        DeleteObjectResult result = QBaseServe.cosXmlClient.deleteObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteMulitObjectTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest(bucket, null);
        request.setQuiet(true);
        request.setObjectList(cosPath);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteMultiObject(request);
    }

    public void appendObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        String srcPath = QBaseServe.createFile(1024 * 1024);
        long position = 0;
        AppendObjectRequest request = new AppendObjectRequest(bucket, cosPath, srcPath, position);
        AppendObjectResult result = QBaseServe.cosXmlClient.appendObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        QBaseServe.delete(bucket, cosPath);
        QBaseServe.delete(srcPath);
    }


    public void multiUploadObjectTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        String srcPath = QBaseServe.createFile(1024* 1024);
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        InitMultipartUploadResult result = QBaseServe.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        String uploadId = result.initMultipartUpload.uploadId;
        int partNumber = 1;
        UploadPartRequest request1 = new UploadPartRequest(bucket, cosPath, partNumber, srcPath,
                uploadId);
        UploadPartResult result1 = QBaseServe.cosXmlClient.uploadPart(request1);
        Log.d(TAG, result1.printHeaders() + "|" + result1.printBody());

        ListPartsRequest request2 = new ListPartsRequest(bucket, cosPath, uploadId);
        ListPartsResult result2 = QBaseServe.cosXmlClient.listParts(request2);
        Log.d(TAG, result2.printHeaders() + "|" + result2.printBody());

        String etag = result1.getETag();
        Map<Integer, String> partNumberAndPart = new HashMap<>();
        partNumberAndPart.put(partNumber, etag);
        CompleteMultiUploadRequest request3 = new CompleteMultiUploadRequest(bucket, cosPath, uploadId,
                partNumberAndPart);
        CompleteMultiUploadResult result3 = QBaseServe.cosXmlClient.completeMultiUpload(request3);
        Log.d(TAG, result3.printHeaders() + "|" + result3.printBody());

        QBaseServe.delete(bucket, cosPath);
        QBaseServe.delete(srcPath);
    }


    public void abortMultiUploadTest() throws CosXmlServiceException, CosXmlClientException, IOException {
        String srcPath = QBaseServe.createFile(1024* 1024);
        InitMultipartUploadRequest request = new InitMultipartUploadRequest(bucket, cosPath);
        InitMultipartUploadResult result = QBaseServe.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        String uploadId = result.initMultipartUpload.uploadId;
        int partNumber = 1;
        UploadPartRequest request1 = new UploadPartRequest(bucket, cosPath, partNumber, srcPath,
                uploadId);
        UploadPartResult result1 = QBaseServe.cosXmlClient.uploadPart(request1);
        Log.d(TAG, result1.printHeaders() + "|" + result1.printBody());

        ListPartsRequest request2 = new ListPartsRequest(bucket, cosPath, uploadId);
        ListPartsResult result2 = QBaseServe.cosXmlClient.listParts(request2);
        Log.d(TAG, result2.printHeaders() + "|" + result2.printBody());

        AbortMultiUploadRequest request3 = new AbortMultiUploadRequest(bucket, cosPath, uploadId);
        AbortMultiUploadResult result3 = QBaseServe.cosXmlClient.abortMultiUpload(request3);
        Log.d(TAG, result3.printHeaders() + "|" + result3.printBody());
    }



    @Test
    public void test() throws Exception {
        QBaseServe.init(getContext());

        putObjectTest();
        headObjectTest();
//        optionObjectTest();
        putObjectACLTest();
        getObjectACLTest();
        getObjectTest();
        deleteObjectTest();
        deleteMulitObjectTest();
        appendObjectTest();
        multiUploadObjectTest();
        abortMultiUploadTest();

    }
}
