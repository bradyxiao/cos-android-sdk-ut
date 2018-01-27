package com.tencent.qcloud.cosv4_14311.model;

import android.test.AndroidTestCase;

import org.junit.Test;

/**
 * Created by bradyxiao on 2018/1/23.
 */
public class GetObjectMetadataRequestTest extends AndroidTestCase {
    @Test
    public void setHeaders() throws Exception {
    }

    @Test
    public void setBodys() throws Exception {
    }

    @Test
    public void test() throws Exception {
//        QServer qServer = QServer.init(getContext());
//        String cosPath = "test.txt";
//
//        /** GetObjectMetadataRequest 请求对象 */
//        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest();
//        /** 设置Bucket */
//        getObjectMetadataRequest.setBucket(qServer.bucket);
//        /** 设置cosPath :远程路径*/
//        getObjectMetadataRequest.setCosPath(cosPath);
//        /** 设置sign: 签名，此处使用多次签名 */
//        getObjectMetadataRequest.setSign(qServer.getSign());
//        /** 设置listener: 结果回调 */
//        getObjectMetadataRequest.setListener(new ICmdTaskListener() {
//            @Override
//            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
//                GetObjectMetadataResult getObjectMetadataResult = (GetObjectMetadataResult) cosResult;
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("查询结果 = ")
//                        .append(" code = ").append(getObjectMetadataResult.code)
//                        .append(", msg =").append(getObjectMetadataResult.msg)
//                        .append(", ctime =").append(getObjectMetadataResult.mtime)
//                        .append(", mtime =").append( getObjectMetadataResult.ctime).append("\n")
//                        .append(", filesize =").append(getObjectMetadataResult.filesize)
//                        .append(", filesize =").append(getObjectMetadataResult.filesize)
//                        .append(", access_url =").append(getObjectMetadataResult.access_url == null?"null":getObjectMetadataResult.access_url)
//                        .append(", sha =").append(getObjectMetadataResult.sha == null?"null":getObjectMetadataResult.sha)
//                        .append(", authority =").append(getObjectMetadataResult.authority == null?"null":getObjectMetadataResult.authority)
//                        .append(", biz_attr =").append(getObjectMetadataResult.biz_attr == null?"null":getObjectMetadataResult.biz_attr);
//                String result = stringBuilder.toString();
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
//                String result = "查询出错： ret =" + cosResult.code + "; msg =" + cosResult.msg
//                        + "; requestId =" + cosResult.requestId;
//                Log.w(QServer.TAG,result);
//            }
//        });
//        /** 发送请求：执行 */
//        qServer.cosClient.getObjectMetadata(getObjectMetadataRequest);
    }

}