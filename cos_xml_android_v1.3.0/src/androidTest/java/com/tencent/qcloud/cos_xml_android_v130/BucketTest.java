package com.tencent.qcloud.cos_xml_android_v130;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.bucket.DeleteBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketCORSResult;
import com.tencent.cos.xml.model.bucket.DeleteBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketLifecycleResult;
import com.tencent.cos.xml.model.bucket.DeleteBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketReplicationResult;
import com.tencent.cos.xml.model.bucket.DeleteBucketRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketResult;
import com.tencent.cos.xml.model.bucket.GetBucketACLRequest;
import com.tencent.cos.xml.model.bucket.GetBucketACLResult;
import com.tencent.cos.xml.model.bucket.GetBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.GetBucketCORSResult;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleResult;
import com.tencent.cos.xml.model.bucket.GetBucketLocationRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLocationResult;
import com.tencent.cos.xml.model.bucket.GetBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.GetBucketReplicationResult;
import com.tencent.cos.xml.model.bucket.GetBucketRequest;
import com.tencent.cos.xml.model.bucket.GetBucketResult;
import com.tencent.cos.xml.model.bucket.GetBucketVersioningRequest;
import com.tencent.cos.xml.model.bucket.GetBucketVersioningResult;
import com.tencent.cos.xml.model.bucket.HeadBucketRequest;
import com.tencent.cos.xml.model.bucket.HeadBucketResult;
import com.tencent.cos.xml.model.bucket.ListMultiUploadsRequest;
import com.tencent.cos.xml.model.bucket.ListMultiUploadsResult;
import com.tencent.cos.xml.model.bucket.PutBucketACLRequest;
import com.tencent.cos.xml.model.bucket.PutBucketACLResult;
import com.tencent.cos.xml.model.bucket.PutBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.PutBucketCORSResult;
import com.tencent.cos.xml.model.bucket.PutBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.PutBucketLifecycleResult;
import com.tencent.cos.xml.model.bucket.PutBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.PutBucketReplicationResult;
import com.tencent.cos.xml.model.bucket.PutBucketRequest;
import com.tencent.cos.xml.model.bucket.PutBucketResult;
import com.tencent.cos.xml.model.bucket.PutBucketVersioningRequest;
import com.tencent.cos.xml.model.bucket.PutBucketVersioningResult;
import com.tencent.cos.xml.model.tag.ACLAccount;
import com.tencent.cos.xml.model.tag.CORSConfiguration;
import com.tencent.cos.xml.model.tag.LifecycleConfiguration;
import com.tencent.qcloud.cos_xml_android_v130.QService;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by bradyxiao on 2017/12/12.
 */

public class BucketTest extends AndroidTestCase {

    static final String TAG = "Unit_Test";

    String bucket;


    //put bucket
    public void putBucketTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketRequest request = new PutBucketRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        request.setSign(600, null, null);
        PutBucketResult result = QService.cosXmlClient.putBucket(request);
        Log.d(TAG, result.printResult());
    }

    public void headBucketTest() throws CosXmlServiceException, CosXmlClientException {
        HeadBucketRequest request = new HeadBucketRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        HeadBucketResult result = QService.cosXmlClient.headBucket(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketLocationTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketLocationRequest request = new GetBucketLocationRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        GetBucketLocationResult result = QService.cosXmlClient.getBucketLocation(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketRequest request = new GetBucketRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        GetBucketResult result = QService.cosXmlClient.getBucket(request);
        Log.d(TAG, result.printResult());

    }

    public void listMultiUploadsTest() throws CosXmlServiceException, CosXmlClientException {
        ListMultiUploadsRequest request = new ListMultiUploadsRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        ListMultiUploadsResult result = QService.cosXmlClient.listMultiUploads(request);
        Log.d(TAG, result.printResult());
    }

    public void putBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketCORSRequest request = new PutBucketCORSRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        CORSConfiguration.CORSRule corsRule = new CORSConfiguration.CORSRule();
        corsRule.allowedOrigin = "http://cloud.tencent.com";
        corsRule.allowedHeader = new ArrayList<>();
        corsRule.allowedHeader.add("Host");
        corsRule.allowedHeader.add("Authorization");
        corsRule.allowedMethod = new ArrayList<>();
        corsRule.allowedMethod.add("PUT");
        corsRule.allowedMethod.add("GET");
        corsRule.exposeHeader = new ArrayList<>();
        corsRule.exposeHeader.add("x-cos-meta");
        corsRule.exposeHeader.add("x-cos-meta-2");
        corsRule.id = "CORSID";
        corsRule.maxAgeSeconds = 5000;
        request.addCORSRule(corsRule);
        PutBucketCORSResult result = QService.cosXmlClient.putBucketCORS(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketCORSRequest request = new GetBucketCORSRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        try {
            GetBucketCORSResult result = QService.cosXmlClient.getBucketCORS(request);
            Log.d(TAG, result.printResult());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void deleteBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketCORSRequest request = new DeleteBucketCORSRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        DeleteBucketCORSResult getServiceResult = QService.cosXmlClient.deleteBucketCORS(request);
        Log.d(TAG, getServiceResult.printResult());
    }

    public void putBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketLifecycleRequest request = new PutBucketLifecycleRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        LifecycleConfiguration.Rule rule = new LifecycleConfiguration.Rule();
        rule.id = "LifeID";
        rule.status = "Enabled";
        rule.filter = new LifecycleConfiguration.Filter();
        rule.filter.prefix = "aws";
        rule.expiration = new LifecycleConfiguration.Expiration();
        rule.expiration.days = 1;
        // rule.expiration.date = "Mon, 11 Dec 2017 15:43:39 GMT";
        request.setRuleList(rule);
        PutBucketLifecycleResult result = QService.cosXmlClient.putBucketLifecycle(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketLifecycleRequest request = new GetBucketLifecycleRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        try {
            GetBucketLifecycleResult result = QService.cosXmlClient.getBucketLifecycle(request);
            Log.d(TAG, result.printResult());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void deleteBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketLifecycleRequest request = new DeleteBucketLifecycleRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        DeleteBucketLifecycleResult result = QService.cosXmlClient.deleteBucketLifecycle(request);
        Log.d(TAG, result.printResult());
    }

    public void putBucketVersioningTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketVersioningRequest request = new PutBucketVersioningRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        request.setEnableVersion(true);
        PutBucketVersioningResult result = QService.cosXmlClient.putBucketVersioning(request);
        Log.d(TAG, result.printResult());
    }

    public void getBucketVersioningTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketVersioningRequest request = new GetBucketVersioningRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        try {
            GetBucketVersioningResult result = QService.cosXmlClient.getBucketVersioning(request);
            Log.d(TAG, result.printResult());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void putBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {

        PutBucketReplicationRequest request = new PutBucketReplicationRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        request.setReplicationConfigurationWithRole("2832742109", "2832742109");
        PutBucketReplicationRequest.RuleStruct ruleStruct = new PutBucketReplicationRequest.RuleStruct();
        ruleStruct.id = "replication_id";
        ruleStruct.isEnable = true;
        ruleStruct.appid = "1253960454";
        ruleStruct.bucket = "replicationtest";
        ruleStruct.region = "ap-beijing";
        request.setReplicationConfigurationWithRule(ruleStruct);
        PutBucketReplicationResult result = QService.cosXmlClient.putBucketReplication(request);
        Log.d(TAG, result.printResult());

    }

    public void getBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketReplicationRequest request = new GetBucketReplicationRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        GetBucketReplicationResult result = QService.cosXmlClient.getBucketReplication(request);
        Log.d(TAG, result.printResult());
    }

    public void deleteBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketReplicationRequest request = new DeleteBucketReplicationRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        DeleteBucketReplicationResult result = QService.cosXmlClient.deleteBucketReplication(request);
        Log.d(TAG, result.printResult());
    }

    public void putBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketACLRequest request = new PutBucketACLRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        request.setXCOSACL(COSACL.PRIVATE);
        ACLAccount aclAccount = new ACLAccount();
        aclAccount.addAccount("1131975903", "1131975903");
        request.setXCOSGrantRead(aclAccount);
        request.setXCOSGrantWrite(aclAccount);

        try {
            PutBucketACLResult result = QService.cosXmlClient.putBucketACL(request);
            Log.d(TAG, result.printResult());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void getBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketACLRequest request = new GetBucketACLRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        try {
            GetBucketACLResult result = QService.cosXmlClient.getBucketACL(request);
            Log.d(TAG, result.printResult());
        } catch (CosXmlServiceException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void deleteBucketTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketRequest request = new DeleteBucketRequest(bucket);
        request.setRequestHeaders("Content-Type", "application/x-shockwave-flash");
        DeleteBucketResult result = QService.cosXmlClient.deleteBucket(request);
        Log.d(TAG, result.printResult());
    }


    @Test
    public void test() throws CosXmlClientException, CosXmlServiceException {
        bucket = "android" + System.currentTimeMillis() / 1000;
        QService.init(getContext());

        putBucketTest();
        headBucketTest();
        getBucketLocationTest();
        getBucketTest();
        listMultiUploadsTest();
        putBucketCORSTest();
        getBucketCORSTest();
        deleteBucketCORSTest();
        putBucketLifecycleTest();
        getBucketLifecycleTest();
        deleteBucketLifecycleTest();
        putBucketVersioningTest();
        getBucketVersioningTest();
//        putBucketReplicationTest();
//        getBucketReplicationTest();
        deleteBucketReplicationTest();
        putBucketACLTest();
        getBucketACLTest();
        deleteBucketTest();
    }

}
