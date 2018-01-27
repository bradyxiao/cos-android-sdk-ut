package com.tencent.qcloud.cos_xml_android_v10;

import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
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

public class BucketAsyncTest extends AndroidTestCase {
    private static final String TAG = "Unit_Test";

    private String bucket;

    volatile int isOver = 0;

    //put bucket
    public void putBucketTest() throws QCloudException, InterruptedException {
        isOver = 0;
        PutBucketRequest request = new PutBucketRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.putBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void headBucketTest() throws QCloudException, InterruptedException {
        isOver = 0;
        HeadBucketRequest request = new HeadBucketRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.headBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, "message = " + message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getBucketTest() throws QCloudException, InterruptedException {
        isOver = 0;
        GetBucketRequest request = new GetBucketRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

//    public void listMultiUploadsTest() throws QCloudException {
//        ListMultiUploadsRequest request = new ListMultiUploadsRequest();
//        request.setBucket(bucket);
//        request.setSign(600,null,null);
//        ListMultiUploadsResult result = QBaseServe.cosXmlClient.listMultiUploads(request);
//        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
//    }

    public void putBucketCORSTest() throws QCloudException, InterruptedException {
        isOver = 0;
        PutBucketCORSRequest request = new PutBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
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
        request.setCORSRuleList(corsRule);
        QBaseServe.cosXmlClient.putBucketCORSAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getBucketCORSTest() throws QCloudException, InterruptedException {
        isOver = 0;
        GetBucketCORSRequest request = new GetBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getBucketCORSAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void deleteBucketCORSTest() throws QCloudException, InterruptedException {
        isOver = 0;
        DeleteBucketCORSRequest request = new DeleteBucketCORSRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteBucketCORSAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void putBucketLifecycleTest() throws QCloudException, InterruptedException {
        isOver = 0;
        PutBucketLifecycleRequest request = new PutBucketLifecycleRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        Rule rule = new Rule();
        rule.id = "lifeID";
        rule.status = "Enable";
        rule.expiration = new Expiration();
        rule.expiration.days = String.valueOf(1);
        request.setRuleList(rule);
        QBaseServe.cosXmlClient.putBucketLifecycleAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getBucketLifecycleTest() throws QCloudException, InterruptedException {
        isOver = 0;
        GetBucketLifecycleRequest request;

        request = new GetBucketLifecycleRequest();
        request.setSign(600,null,null);
        request.setBucket(bucket);
        QBaseServe.cosXmlClient.getBucketLifecycleAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void deleteBucketLifecycleTest() throws QCloudException, InterruptedException {
        isOver = 0;
        DeleteBucketLifecycleRequest request = new DeleteBucketLifecycleRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteBucketLifecycleAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void putBucketTaggingTest() throws QCloudException, InterruptedException {
        isOver = 0;
        PutBucketTaggingRequest request = new PutBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.putBucketTaggingAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getBucketTaggingTest() throws QCloudException, InterruptedException {
        isOver = 0;
        GetBucketTaggingRequest request = new GetBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getBucketTaggingAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void deleteBucketTaggingTest() throws QCloudException, InterruptedException {
        isOver = 0;
        DeleteBucketTaggingRequest request = new DeleteBucketTaggingRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteBucketTaggingAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void putBucketACLTest() throws QCloudException, InterruptedException {
        isOver = 0;
        PutBucketACLRequest request = new PutBucketACLRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.putBucketACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }

    public void getBucketACLTest() throws QCloudException, InterruptedException {
        isOver = 0;
        GetBucketACLRequest request;
        request = new GetBucketACLRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.getBucketACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }


    public void deleteBucketTest() throws QCloudException, InterruptedException {
        isOver = 0;
        DeleteBucketRequest request = new DeleteBucketRequest();
        request.setBucket(bucket);
        request.setSign(600,null,null);
        QBaseServe.cosXmlClient.deleteBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                Log.d(TAG, cosXmlResult.printHeaders() + "|" + cosXmlResult.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlResult cosXmlResult) {
                String message = cosXmlResult.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });

        while (isOver == 0){
            Thread.sleep(5000);
        }
    }


    @Test
    public void test() throws QCloudException, InterruptedException {
        QBaseServe.init(getContext());

        bucket = "android" + System.currentTimeMillis()/1000;

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
    }
}

