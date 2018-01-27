package com.tencent.qcloud.cosv4_1435.utils;

import android.test.AndroidTestCase;

import com.tencent.cos.utils.FolderUtils;

import org.junit.Test;

/**
 * Created by bradyxiao on 2017/7/20.
 * author bradyxiao
 */
public class FolderUtilsTest extends AndroidTestCase {

    @Test
    public void testFolderUtils() throws Exception {
        boolean result = FolderUtils.isValidPath("/tcImag/ad3bd15f16e8514ff371a6cf1afdc97464177.jpg");
        assertEquals(true,result);
    }
}