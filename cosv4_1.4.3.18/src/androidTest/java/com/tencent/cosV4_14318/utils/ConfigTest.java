package com.tencent.cosV4_14318.utils;

import android.support.test.runner.AndroidJUnit4;

import com.tencent.qcloud.cosv4_14318.test.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by bradyxiao on 2018/3/8.
 */
@RunWith(AndroidJUnit4.class)
public class ConfigTest {

    @Test
    public void testConfig(){
        String v4Region = BuildConfig.V4_REGION;
        String v5Region = BuildConfig.V5_REGION;
        assertEquals(v4Region, v5Region);
    }
}
