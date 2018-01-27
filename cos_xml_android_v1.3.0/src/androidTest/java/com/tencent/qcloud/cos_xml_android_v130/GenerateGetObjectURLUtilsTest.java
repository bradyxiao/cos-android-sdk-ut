package com.tencent.qcloud.cos_xml_android_v130;

import android.test.AndroidTestCase;
import android.util.Log;

import com.tencent.cos.xml.common.Region;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.utils.GenerateGetObjectURLUtils;

import org.junit.Test;

/**
 * Created by bradyxiao on 2018/1/2.
 */
public class GenerateGetObjectURLUtilsTest extends AndroidTestCase {

    static final String TAG = "Unit_Test";

    @Test
    public void testGetObjectUrl() throws Exception {
        String url = GenerateGetObjectURLUtils.getObjectUrl(true, "1253960454",
                "xy2",Region.AP_Guangzhou.getRegion(), "append4.txt");
        Log.d(TAG, url);
        assertEquals("https://xy2-1253960454.cos.ap-guangzhou.myqcloud.com/append4.txt", url);
    }

    @Test
    public void testGetObjectUrlWithSign() throws CosXmlClientException {

        GenerateGetObjectURLUtils.QCloudAPI qCloudAPI = new GenerateGetObjectURLUtils.QCloudAPI() {
            @Override
            public String getSecretKey() {
                String secretKey = "rWyGVcXHpCjDOSMaheQSNGyMfstiOAqu";
                return secretKey;
            }

            @Override
            public String getSecretId() {
                String secretId = "AKIDtgHguxSsaEykZHoIfqtlT1NY0MWTn4B5";
                return secretId;
            }

            @Override
            public long getKeyDuration() {
                return 0;
            }

            @Override
            public String getSessionToken() {
                return null;
            }
        };
       String url1 = GenerateGetObjectURLUtils.getObjectUrlWithSign(true, "1253960454",
               "androidtest",Region.AP_Guangzhou.getRegion(), "append4.txt", 600, qCloudAPI);
        Log.d("Unit_Test", url1);
    }
}