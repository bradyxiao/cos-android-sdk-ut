package com.tencent.qcloud.cos_xml_android_v122;

import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.CosXmlResultListener;
import com.tencent.cos.xml.model.service.GetServiceRequest;
import com.tencent.cos.xml.model.service.GetServiceResult;

import org.junit.Test;

/**
 * Created by bradyxiao on 2017/11/16.
 */

public class ServiceTest extends AndroidTestCase {

    private static final String TAG = "Unit_Test";


    public void getServiceTest() throws CosXmlServiceException, CosXmlClientException {
        GetServiceRequest request = new GetServiceRequest();
        GetServiceResult result = QBaseServe.cosXmlClient.getService(request);
        Log.d(TAG, result.printHeaders() + "|" + result.printBody());
    }

    volatile  int isOver = 0;
    public void getServiceTest2(){
        GetServiceRequest request = new GetServiceRequest();
        QBaseServe.cosXmlClient.getServiceAsync(request, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.d(TAG, result.printHeaders() + "|" + result.printBody());
                isOver = 1;
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Log.d(TAG, exception == null ? serviceException.getMessage() : exception.toString());
                isOver = 2;
            }
        });

        while(isOver == 0){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test() throws CosXmlClientException, CosXmlServiceException {
       QBaseServe.init(getContext());
//        getServiceTest();
    }

    @Test
    public void test2() throws CosXmlClientException, CosXmlServiceException {
        QBaseServe.init(getContext());
//        getServiceTest2();
    }

}
