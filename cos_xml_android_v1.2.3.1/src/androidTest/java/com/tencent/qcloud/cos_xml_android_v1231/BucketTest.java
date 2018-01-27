package com.tencent.qcloud.cos_xml_android_v1231;

import android.test.AndroidTestCase;
import android.util.Log;

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
import com.tencent.cos.xml.model.bucket.DeleteBucketTaggingRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketTaggingResult;
import com.tencent.cos.xml.model.bucket.GetBucketACLRequest;
import com.tencent.cos.xml.model.bucket.GetBucketACLResult;
import com.tencent.cos.xml.model.bucket.GetBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.GetBucketCORSResult;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleResult;
import com.tencent.cos.xml.model.bucket.GetBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.GetBucketReplicationResult;
import com.tencent.cos.xml.model.bucket.GetBucketRequest;
import com.tencent.cos.xml.model.bucket.GetBucketResult;
import com.tencent.cos.xml.model.bucket.GetBucketTaggingRequest;
import com.tencent.cos.xml.model.bucket.GetBucketTaggingResult;
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
import com.tencent.cos.xml.model.bucket.PutBucketTaggingRequest;
import com.tencent.cos.xml.model.bucket.PutBucketTaggingResult;
import com.tencent.cos.xml.model.bucket.PutBucketVersioningRequest;
import com.tencent.cos.xml.model.bucket.PutBucketVersioningResult;
import com.tencent.cos.xml.model.tag.CORSRule;
import com.tencent.cos.xml.model.tag.Expiration;
import com.tencent.cos.xml.model.tag.Filter;
import com.tencent.cos.xml.model.tag.Rule;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by bradyxiao on 2017/11/16.
 */

public class BucketTest extends AndroidTestCase {
    private static final String TAG = "Unit_Test";

    private String bucket;

    //put bucket
    public void putBucketTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketRequest request = new PutBucketRequest(bucket);
        PutBucketResult result = QBaseServe.cosXmlClient.putBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void headBucketTest() throws CosXmlServiceException, CosXmlClientException {
        HeadBucketRequest request = new HeadBucketRequest(bucket);
        HeadBucketResult result = QBaseServe.cosXmlClient.headBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketRequest request = new GetBucketRequest(bucket);
        GetBucketResult result = QBaseServe.cosXmlClient.getBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void listMultiUploadsTest() throws CosXmlServiceException, CosXmlClientException {
        ListMultiUploadsRequest request = new ListMultiUploadsRequest(bucket);
        ListMultiUploadsResult result = QBaseServe.cosXmlClient.listMultiUploads(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketCORSRequest request = new PutBucketCORSRequest(bucket);
        CORSRule corsRule = new CORSRule();
        corsRule.id = "cors";
        corsRule.allowedOrigin  =  "http://cloud.tencent.com";
        corsRule.allowedMethod = new ArrayList<>();
        corsRule.allowedMethod.add("PUT");
        corsRule.allowedHeader = new ArrayList<>();
        corsRule.allowedHeader.add("Host");
        corsRule.exposeHeader = new ArrayList<>();
        corsRule.exposeHeader.add("User-Agent");
        corsRule.maxAgeSeconds = String.valueOf(5000);
        request.addCORSRule(corsRule);
        PutBucketCORSResult result;
        result = QBaseServe.cosXmlClient.putBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketCORSRequest request = new GetBucketCORSRequest(bucket);
        GetBucketCORSResult result = QBaseServe.cosXmlClient.getBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketCORSTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketCORSRequest request = new DeleteBucketCORSRequest(bucket);
        DeleteBucketCORSResult result = QBaseServe.cosXmlClient.deleteBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketLifecycleRequest request = new PutBucketLifecycleRequest(bucket);
        Rule rule = new Rule();
        rule.id = "lifeID";
        rule.status = "Enable";
        rule.filter = new Filter();
        rule.filter.prefix = "aws";
        rule.expiration = new Expiration();
        rule.expiration.days = String.valueOf(1);
        request.setRuleList(rule);
        PutBucketLifecycleResult result = QBaseServe.cosXmlClient.putBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketLifecycleRequest request;
        request = new GetBucketLifecycleRequest(bucket);
        GetBucketLifecycleResult result = QBaseServe.cosXmlClient.getBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketLifecycleTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketLifecycleRequest request = new DeleteBucketLifecycleRequest(bucket);
        DeleteBucketLifecycleResult result = QBaseServe.cosXmlClient.deleteBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketVersioningTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketVersioningRequest request = new PutBucketVersioningRequest(bucket);
        PutBucketVersioningResult result = QBaseServe.cosXmlClient.putBucketVersioning(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketVersioningTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketVersioningRequest request = new GetBucketVersioningRequest(bucket);
        GetBucketVersioningResult result = QBaseServe.cosXmlClient.getBucketVersioning(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketReplicationRequest request = new PutBucketReplicationRequest(bucket);
        PutBucketReplicationResult result = QBaseServe.cosXmlClient.putBucketReplication(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketReplicationRequest request = new GetBucketReplicationRequest(bucket);
        GetBucketReplicationResult result = QBaseServe.cosXmlClient.getBucketReplication(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketReplicationTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketReplicationRequest request = new DeleteBucketReplicationRequest(bucket);
        DeleteBucketReplicationResult result = QBaseServe.cosXmlClient.deleteBucketReplication(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketTaggingTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketTaggingRequest request = new PutBucketTaggingRequest(bucket);
        PutBucketTaggingResult result = QBaseServe.cosXmlClient.putBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketTaggingTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketTaggingRequest request = new GetBucketTaggingRequest(bucket);
        GetBucketTaggingResult result = QBaseServe.cosXmlClient.getBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketTaggingTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketTaggingRequest request = new DeleteBucketTaggingRequest(bucket);
        DeleteBucketTaggingResult result = QBaseServe.cosXmlClient.deleteBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        PutBucketACLRequest request = new PutBucketACLRequest(bucket);
        PutBucketACLResult result = QBaseServe.cosXmlClient.putBucketACL(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketACLTest() throws CosXmlServiceException, CosXmlClientException {
        GetBucketACLRequest request;
        request = new GetBucketACLRequest(bucket);
        GetBucketACLResult result = QBaseServe.cosXmlClient.getBucketACL(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    public void deleteBucketTest() throws CosXmlServiceException, CosXmlClientException {
        DeleteBucketRequest request = new DeleteBucketRequest(bucket);
        DeleteBucketResult result = QBaseServe.cosXmlClient.deleteBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    @Test
    public void test() throws CosXmlServiceException, CosXmlClientException{
        QBaseServe.init(getContext());

        bucket = "android" + System.currentTimeMillis()/1000;

        putBucketTest();

        headBucketTest();

        getBucketTest();

//        listMultiUploadsTest();

        putBucketCORSTest();

        getBucketCORSTest();

        deleteBucketCORSTest();

        deleteBucketTest();
    }
}
