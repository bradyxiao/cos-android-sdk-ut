package com.tencent.qcloud.cos_xml_android_v10;

import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.service.GetServiceRequest;
import com.tencent.cos.xml.model.service.GetServiceResult;
import com.tencent.qcloud.network.exception.QCloudException;

import org.junit.Test;

import static com.tencent.qcloud.cos_xml_android_v10.QBaseServe.TAG;

/**
 * Created by bradyxiao on 2017/11/16.
 */

public class ServiceTest extends AndroidTestCase {

    private static final String TAG = "Unit_Test";

    public void getServiceTest() throws QCloudException {
        QBaseServe.init(getContext());
        GetServiceRequest request = new GetServiceRequest();
        request.setSign(600, null, null);
        GetServiceResult result = QBaseServe.cosXmlClient.getService(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    volatile int isOver = 0;
    public void getServiceTest2() throws InterruptedException {
        QBaseServe.init(getContext());
        GetServiceRequest request = new GetServiceRequest();
        request.setSign(600, null, null);
        QBaseServe.cosXmlClient.getServiceAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlResult result) {
                String message = result.printError();
                Log.d(TAG, message);
                isOver = 2;
            }
        });
        while(isOver == 0){
            Thread.sleep(5000);
        }
    }


//    @Test
//    public void test() throws QCloudException {
//        getServiceTest();
//    }
//
//    @Test
//    public void test2() throws InterruptedException {
//        getServiceTest2();
//    }

}
