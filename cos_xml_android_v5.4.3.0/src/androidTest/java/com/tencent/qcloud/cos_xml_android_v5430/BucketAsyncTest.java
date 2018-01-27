package com.tencent.qcloud.cos_xml_android_v5430;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.COSACL;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.bucket.GetBucketACLRequest;
import com.tencent.cos.xml.model.bucket.GetBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.GetBucketLocationRequest;
import com.tencent.cos.xml.model.bucket.GetBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.GetBucketRequest;
import com.tencent.cos.xml.model.bucket.GetBucketVersioningRequest;
import com.tencent.cos.xml.model.bucket.HeadBucketRequest;
import com.tencent.cos.xml.model.bucket.ListMultiUploadsRequest;
import com.tencent.cos.xml.model.bucket.PutBucketACLRequest;
import com.tencent.cos.xml.model.bucket.PutBucketCORSRequest;
import com.tencent.cos.xml.model.bucket.PutBucketLifecycleRequest;
import com.tencent.cos.xml.model.bucket.PutBucketReplicationRequest;
import com.tencent.cos.xml.model.bucket.PutBucketRequest;
import com.tencent.cos.xml.model.bucket.PutBucketVersioningRequest;
import com.tencent.cos.xml.model.tag.ACLAccount;
import com.tencent.cos.xml.model.tag.CORSConfiguration;
import com.tencent.cos.xml.model.tag.LifecycleConfiguration;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by bradyxiao on 2017/12/13.
 */

public class BucketAsyncTest extends ApplicationTestCase {
    static final String TAG = "Unit_Test";

    volatile boolean isOver = false;

    String bucket;

    public BucketAsyncTest() {
        super(Application.class);
    }

    @Test
    public void test() throws CosXmlClientException, CosXmlServiceException {
        bucket = "android" + System.currentTimeMillis()/1000;
        QService.init(getContext());

        putBucketTest();
        headBucketTest();
        getBucketLocationTest();
        getBucketTest();
        listMultiUploadsTest();
        putBucketCORSTest();
        getBucketCORSTest();
        BucketCORSTest();
        putBucketLifecycleTest();
        getBucketLifecycleTest();
        putBucketLifecycleTest();
        putBucketVersioningTest();
        getBucketVersioningTest();
        putBucketReplicationTest();
        getBucketReplicationTest();
        putBucketReplicationTest();
        putBucketACLTest();
        getBucketACLTest();
        BucketTest();
    }

    private void BucketTest() {
        isOver = false;
        PutBucketRequest request = new PutBucketRequest(bucket);
        QService.cosXmlClient.putBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketACLTest() {
        isOver = false;
        GetBucketACLRequest request = new GetBucketACLRequest(bucket);
        QService.cosXmlClient.getBucketACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketACLTest() {
        isOver = false;
        PutBucketACLRequest request = new PutBucketACLRequest(bucket);
        ACLAccount aclAccount = new ACLAccount();
        aclAccount.addAccount("2832742109", "2832742109");
        request.setXCOSGrantRead(aclAccount);
        QService.cosXmlClient.putBucketACLAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketReplicationTest() {
        isOver = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GetBucketReplicationRequest request = new GetBucketReplicationRequest(bucket);
        QService.cosXmlClient.getBucketReplicationAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketReplicationTest() {
        isOver = false;
        PutBucketReplicationRequest request = new PutBucketReplicationRequest(bucket);
        PutBucketReplicationRequest.RuleStruct ruleStruct = new PutBucketReplicationRequest.RuleStruct();
        ruleStruct.id = "replication_id";
        ruleStruct.isEnable = true;
        ruleStruct.appid = "1253960454";
        ruleStruct.bucket = "replicationtest";
        ruleStruct.region = "ap-beijing";
        request.setReplicationConfigurationWithRule(ruleStruct);
        QService.cosXmlClient.putBucketReplicationAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketVersioningTest() {
        isOver = false;
        GetBucketVersioningRequest request = new GetBucketVersioningRequest(bucket);
        QService.cosXmlClient.getBucketVersioningAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketVersioningTest() {
        isOver = false;
        PutBucketVersioningRequest request = new PutBucketVersioningRequest(bucket);
        request.setEnableVersion(true);
        QService.cosXmlClient.putBucketVersionAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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



    private void getBucketLifecycleTest() {
        isOver = false;
        GetBucketLifecycleRequest request = new GetBucketLifecycleRequest(bucket);
        QService.cosXmlClient.getBucketLifecycleAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketLifecycleTest() {
        isOver = false;
        PutBucketLifecycleRequest request = new PutBucketLifecycleRequest(bucket);
        LifecycleConfiguration.Rule rule = new LifecycleConfiguration.Rule();
        rule.id = "LifeID2";
        rule.status = "Enabled";
        rule.filter = new LifecycleConfiguration.Filter();
        rule.filter.prefix = "aws";
        rule.expiration = new LifecycleConfiguration.Expiration();
        rule.expiration.days = 1;
        request.setRuleList(rule);
        QService.cosXmlClient.putBucketLifecycleAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void BucketCORSTest() {
        isOver = false;
        PutBucketCORSRequest BucketCORSRequest = new PutBucketCORSRequest(bucket);
        QService.cosXmlClient.putBucketCORSAsync(BucketCORSRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketCORSTest() {
        isOver = false;
        GetBucketCORSRequest request = new GetBucketCORSRequest(bucket);
        QService.cosXmlClient.getBucketCORSAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketCORSTest() {
        isOver = false;
        PutBucketCORSRequest request = new PutBucketCORSRequest(bucket);
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
        corsRule.id = "CORSID2";
        corsRule.maxAgeSeconds = 5000;
        request.addCORSRule(corsRule);
        QService.cosXmlClient.putBucketCORSAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void listMultiUploadsTest() {
        isOver = false;
        ListMultiUploadsRequest request = new ListMultiUploadsRequest(bucket);
        QService.cosXmlClient.listMultiUploadsAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketTest() {
        isOver = false;
        GetBucketRequest request = new GetBucketRequest(bucket);
        QService.cosXmlClient.getBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void getBucketLocationTest() {
        isOver = false;
        GetBucketLocationRequest request = new GetBucketLocationRequest(bucket);
        QService.cosXmlClient.getBucketLocationAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void headBucketTest() {
        isOver = false;
        HeadBucketRequest request = new HeadBucketRequest(bucket);
        QService.cosXmlClient.headBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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

    private void putBucketTest() {
        isOver = false;
        PutBucketRequest request = new PutBucketRequest(bucket);
//        request.setXCOSACL(COSACL.PUBLIC_READ_WRITE);
//        ACLAccount aclAccount = new ACLAccount();
//        aclAccount.addAccount("2832742109", "2832742109");
//        request.setXCOSGrantRead(aclAccount);
        QService.cosXmlClient.putBucketAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printResult());
                isOver = true;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
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
}
