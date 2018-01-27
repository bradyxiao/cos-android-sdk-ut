package com.tencent.qcloud.cos_xml_android_v10;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;

import com.tencent.cos.xml.model.object.AbortMultiUploadRequest;
import com.tencent.cos.xml.model.object.AbortMultiUploadResult;
import com.tencent.cos.xml.model.object.AppendObjectRequest;
import com.tencent.cos.xml.model.object.AppendObjectResult;
import com.tencent.cos.xml.model.object.CompleteMultiUploadRequest;
import com.tencent.cos.xml.model.object.CompleteMultiUploadResult;

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
import com.tencent.qcloud.network.QCloudProgressListener;
import com.tencent.qcloud.network.exception.QCloudException;


import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bradyxiao on 2017/11/15.
 */

public class ObjectTest extends AndroidTestCase {

    private static final String TAG = "Unit_Test";

    private String bucket = QBaseServe.objectBucket;
    private String cosPath = "putobject.txt";

    public void putObjectTest() throws Exception {
        cosPath = "putobject.txt";
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        PutObjectRequest request = new PutObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSrcPath(srcPath);
        request.setSign(600, null, null);
        PutObjectResult result = QBaseServe.cosXmlClient.putObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        QBaseServe.deleteFile(srcPath);
    }


    public void headObjectTest() throws QCloudException {
        HeadObjectRequest request = new HeadObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        HeadObjectResult result = QBaseServe.cosXmlClient.headObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    public void optionObjectTest() throws QCloudException {
        String origin = "http://cloud.tencent.com";
        String method = "PUT";
        OptionObjectRequest request = new OptionObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setOrigin("http://www.qcloud.com");
        request.setAccessControlMethod("get");
        request.setAccessControlHeaders("host");
        request.setSign(600, null, null);
        OptionObjectResult result = QBaseServe.cosXmlClient.optionObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putObjectACLTest() throws QCloudException {
        PutObjectACLRequest request = new PutObjectACLRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setXCOSACL(COSACL.PUBLIC_READ_WRITE);
        List<String> idList = new ArrayList<>();
        idList.add("uin/1278687956:uin/1278687956");
        request.setXCOSGrantRead(idList);
        request.setSign(600, null, null);
        try {
            PutObjectACLResult result = QBaseServe.cosXmlClient.putObjectACL(request);
            Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        } catch (QCloudException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void getObjectACLTest() throws QCloudException {
        GetObjectACLRequest request = new GetObjectACLRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        try {
            GetObjectACLResult result = QBaseServe.cosXmlClient.getObjectACL(request);
            Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        } catch (QCloudException e) {
            Log.d(TAG, "QCloudException = " + e);
        }
    }


    public void getObjectTest() throws QCloudException {
        String localPath = getContext().getExternalCacheDir().getPath();
        GetObjectRequest request = new GetObjectRequest(localPath);
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        request.setRange(1);
        GetObjectResult result = QBaseServe.cosXmlClient.getObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        int index = cosPath.lastIndexOf("/");
        String path;
        if (index < 0) {
            path = localPath + "/" + cosPath;
        } else {
            path = localPath + cosPath.substring(index);
        }

        QBaseServe.deleteFile(path);
    }

    public void deleteObjectTest() throws QCloudException {
        DeleteObjectRequest request = new DeleteObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        DeleteObjectResult result = QBaseServe.cosXmlClient.deleteObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteMulitObjectTest() throws QCloudException {
        DeleteMultiObjectRequest request = new DeleteMultiObjectRequest();
        request.setBucket(bucket);
        request.setQuiet(true);
        request.setObjectList(cosPath);
        request.setSign(600, null, null);
        QBaseServe.cosXmlClient.deleteMultiObject(request);
    }

    public void appendObjectTest() throws Exception {
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        long position = 0;
        AppendObjectRequest request = new AppendObjectRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setPosition(position);
        request.setSrcPath(srcPath);
        request.setSign(600, null, null);
        AppendObjectResult result = QBaseServe.cosXmlClient.appendObject(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
        QBaseServe.deleteObject(bucket, cosPath);
        QBaseServe.deleteFile(srcPath);
    }

    public void multiUploadObjectTest() throws Exception {
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        InitMultipartUploadRequest request = new InitMultipartUploadRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        InitMultipartUploadResult result = QBaseServe.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        String uploadId = result.initMultipartUpload.uploadId;
        int partNumber = 1;
        UploadPartRequest request1 = new UploadPartRequest();
        request1.setBucket(bucket);
        request1.setCosPath(cosPath);
        request1.setUploadId(uploadId);
        request1.setSrcPath(srcPath);
        request1.setPartNumber(partNumber);
        request1.setSign(600, null, null);
        UploadPartResult result1 = QBaseServe.cosXmlClient.uploadPart(request1);
        Log.d(TAG, result1.printHeaders() + "|" + result1.printBody());

        ListPartsRequest request2 = new ListPartsRequest();
        request2.setBucket(bucket);
        request2.setCosPath(cosPath);
        request2.setUploadId(uploadId);
        request2.setSign(600, null, null);
        ListPartsResult result2 = QBaseServe.cosXmlClient.listParts(request2);
        Log.d(TAG, result2.printHeaders() + "|" + result2.printBody());

        String etag = result1.getETag();
        Map<Integer, String> partNumberAndPart = new HashMap<>();
        partNumberAndPart.put(partNumber, etag);
        CompleteMultiUploadRequest request3 = new CompleteMultiUploadRequest();
        request3.setBucket(bucket);
        request3.setCosPath(cosPath);
        request3.setUploadId(uploadId);
        request3.setSign(600, null, null);
        CompleteMultiUploadResult result3 = QBaseServe.cosXmlClient.completeMultiUpload(request3);
        Log.d(TAG, result3.printHeaders() + "|" + result3.printBody());

        QBaseServe.deleteObject(bucket, cosPath);
        QBaseServe.deleteFile(srcPath);
    }


    public void abortMultiUploadTest() throws Exception {
        String srcPath = QBaseServe.crateFile(1024 * 1024);
        InitMultipartUploadRequest request = new InitMultipartUploadRequest();
        request.setBucket(bucket);
        request.setCosPath(cosPath);
        request.setSign(600, null, null);
        InitMultipartUploadResult result = QBaseServe.cosXmlClient.initMultipartUpload(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());

        String uploadId = result.initMultipartUpload.uploadId;
        int partNumber = 1;
        UploadPartRequest request1 = new UploadPartRequest();
        request1.setBucket(bucket);
        request1.setCosPath(cosPath);
        request1.setUploadId(uploadId);
        request1.setSrcPath(srcPath);
        request1.setPartNumber(partNumber);
        request1.setSign(600, null, null);
        UploadPartResult result1 = QBaseServe.cosXmlClient.uploadPart(request1);
        Log.d(TAG, result1.printHeaders() + "|" + result1.printBody());

        ListPartsRequest request2 = new ListPartsRequest();
        request2.setBucket(bucket);
        request2.setCosPath(cosPath);
        request2.setUploadId(uploadId);
        request2.setSign(600, null, null);
        ListPartsResult result2 = QBaseServe.cosXmlClient.listParts(request2);
        Log.d(TAG, result2.printHeaders() + "|" + result2.printBody());

        AbortMultiUploadRequest request3 = new AbortMultiUploadRequest();
        request3.setBucket(bucket);
        request3.setCosPath(cosPath);
        request3.setUploadId(uploadId);
        request3.setSign(600, null, null);
        AbortMultiUploadResult result3 = QBaseServe.cosXmlClient.abortMultiUpload(request3);
        Log.d(TAG, result3.printHeaders() + "|" + result3.printBody());
        QBaseServe.deleteFile(srcPath);
    }


    @Test
    public void test() throws Exception {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiUpload() throws Exception {
        try {
            QBaseServe.init(getContext());

            multiUploadObjectTest();
            abortMultiUploadTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
