package com.tencent.qcloud.cos_xml_android_v130;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.tencent.cos.xml.utils.URLEncodeUtils;

import org.junit.Test;

/**
 * Created by bradyxiao on 2018/1/2.
 */
public class URLEncodeUtilsTest extends ApplicationTestCase {


    public URLEncodeUtilsTest() {
        super(Application.class);
    }

    @Test
    public void testCosPathEncode() throws Exception {
       String url = URLEncodeUtils.cosPathEncode("/test/1.txt/");
        Log.d("Unit_Test", url);
    }

}