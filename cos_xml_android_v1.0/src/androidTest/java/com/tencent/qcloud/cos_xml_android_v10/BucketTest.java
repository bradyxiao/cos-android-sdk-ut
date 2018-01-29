package com.tencent.qcloud.cos_xml_android_v10;

import android.test.AndroidTestCase;
import android.util.Log;


import com.tencent.cos.xml.model.bucket.DeleteBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketCORSResult;
import com.tencent.cos.xml.model.bucket.DeleteBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.DeleteBucketLifecycleResult;

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

import com.tencent.cos.xml.model.bucket.GetBucketRequest;
import com.tencent.cos.xml.model.bucket.GetBucketResult;
import com.tencent.cos.xml.model.bucket.GetBucketTaggingRequest;
import com.tencent.cos.xml.model.bucket.GetBucketTaggingResult;

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

import com.tencent.cos.xml.model.bucket.PutBucketRequest;
import com.tencent.cos.xml.model.bucket.PutBucketResult;
import com.tencent.cos.xml.model.bucket.PutBucketTaggingRequest;
import com.tencent.cos.xml.model.bucket.PutBucketTaggingResult;

import com.tencent.cos.xml.model.tag.CORSRule;
import com.tencent.cos.xml.model.tag.Expiration;

import com.tencent.cos.xml.model.tag.Rule;
import com.tencent.qcloud.network.exception.QCloudException;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by bradyxiao on 2017/11/16.
 */

public class BucketTest extends AndroidTestCase {
    private static final String TAG = "Unit_Test";

    private String bucket;

    //put bucket
    public void putBucketTest() throws QCloudException {
        PutBucketRequest request = new PutBucketRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        PutBucketResult result = QBaseServe.cosXmlClient.putBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void headBucketTest() throws QCloudException {
        HeadBucketRequest request = new HeadBucketRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        HeadBucketResult result = QBaseServe.cosXmlClient.headBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketTest() throws QCloudException {
        GetBucketRequest request = new GetBucketRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        GetBucketResult result = QBaseServe.cosXmlClient.getBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

//    public void listMultiUploadsTest() throws QCloudException {
//        ListMultiUploadsRequest request = new ListMultiUploadsRequest();
//        request.setBucket(bucket);
//        request.setSign(600,null,null);
//        ListMultiUploadsResult result = QBaseServe.cosXmlClient.listMultiUploads(request);
//        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
//    }

    public void putBucketCORSTest() throws QCloudException {
        PutBucketCORSRequest request = new PutBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        CORSRule corsRule = new CORSRule();
        corsRule.id = "cors";
        corsRule.allowedOrigin = "http://cloud.tencent.com";
        corsRule.allowedMethod = new ArrayList<>();
        corsRule.allowedMethod.add("PUT");
        corsRule.allowedHeader = new ArrayList<>();
        corsRule.allowedHeader.add("Host");
        corsRule.exposeHeader = new ArrayList<>();
        corsRule.exposeHeader.add("User-Agent");
        corsRule.maxAgeSeconds = String.valueOf(5000);
        request.setCORSRuleList(corsRule);
        PutBucketCORSResult result;
        result = QBaseServe.cosXmlClient.putBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketCORSTest() throws QCloudException {
        GetBucketCORSRequest request = new GetBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        GetBucketCORSResult result = QBaseServe.cosXmlClient.getBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketCORSTest() throws QCloudException {
        DeleteBucketCORSRequest request = new DeleteBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        DeleteBucketCORSResult result = QBaseServe.cosXmlClient.deleteBucketCORS(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketLifecycleTest() throws QCloudException {
        PutBucketLifecycleRequest request = new PutBucketLifecycleRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        Rule rule = new Rule();
        rule.id = "lifeID";
        rule.status = "Enable";
        rule.expiration = new Expiration();
        rule.expiration.days = String.valueOf(1);
        request.setRuleList(rule);
        PutBucketLifecycleResult result = QBaseServe.cosXmlClient.putBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketLifecycleTest() throws QCloudException {
        GetBucketLifecycleRequest request;

        request = new GetBucketLifecycleRequest();
        request.setSign(600, null, null);
        request.setBucket(bucket);
        GetBucketLifecycleResult result = QBaseServe.cosXmlClient.getBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketLifecycleTest() throws QCloudException {
        DeleteBucketLifecycleRequest request = new DeleteBucketLifecycleRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        DeleteBucketLifecycleResult result = QBaseServe.cosXmlClient.deleteBucketLifecycle(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketTaggingTest() throws QCloudException {
        PutBucketTaggingRequest request = new PutBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        PutBucketTaggingResult result = QBaseServe.cosXmlClient.putBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketTaggingTest() throws QCloudException {
        GetBucketTaggingRequest request = new GetBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        GetBucketTaggingResult result = QBaseServe.cosXmlClient.getBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void deleteBucketTaggingTest() throws QCloudException {
        DeleteBucketTaggingRequest request = new DeleteBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        DeleteBucketTaggingResult result = QBaseServe.cosXmlClient.deleteBucketTagging(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void putBucketACLTest() throws QCloudException {
        PutBucketACLRequest request = new PutBucketACLRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        PutBucketACLResult result = QBaseServe.cosXmlClient.putBucketACL(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    public void getBucketACLTest() throws QCloudException {
        GetBucketACLRequest request;
        request = new GetBucketACLRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        GetBucketACLResult result = QBaseServe.cosXmlClient.getBucketACL(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    public void deleteBucketTest() throws QCloudException {
        DeleteBucketRequest request = new DeleteBucketRequest();
        request.setBucket(bucket);
        request.setSign(600, null, null);
        DeleteBucketResult result = QBaseServe.cosXmlClient.deleteBucket(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }


    @Test
    public void test() throws QCloudException {
        QBaseServe.init(getContext());
        try {

            bucket = "android" + System.currentTimeMillis() / 1000;

            putBucketTest();

            headBucketTest();

            getBucketTest();

//        listMultiUploadsTest();

            putBucketCORSTest();

            getBucketCORSTest();

            deleteBucketCORSTest();

            putBucketLifecycleTest();
            getBucketLifecycleTest();
            deleteBucketLifecycleTest();
//
//        putBucketACLTest();
//        getBucketACLTest();

            deleteBucketTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
