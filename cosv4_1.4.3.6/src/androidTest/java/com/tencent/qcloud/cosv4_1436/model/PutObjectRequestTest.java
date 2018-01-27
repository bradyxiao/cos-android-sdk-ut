package com.tencent.qcloud.cosv4_1436.model;

import android.test.AndroidTestCase;

import org.junit.Test;

/**
 * Created by bradyxiao on 2018/1/23.
 */
public class PutObjectRequestTest extends AndroidTestCase {
    @Test
    public void setHeaders() throws Exception {
    }

    @Test
    public void setCustomerHeaders() throws Exception {
    }

    @Test
    public void setBodys() throws Exception {
    }

    @Test
    public void setHeaders1() throws Exception {
    }

    @Test
    public void setBodys1() throws Exception {
    }

    @Test
    public void checkSha() throws Exception {
    }

    @Test
    public void setSrcPath() throws Exception {
    }

    @Test
    public void getSrcPath() throws Exception {
    }

    @Test
    public void getFilesize() throws Exception {
    }

    @Test
    public void setFilesize() throws Exception {
    }

    @Test
    public void setSliceFlag() throws Exception {
    }

    @Test
    public void isSliceFlag() throws Exception {
    }

    @Test
    public void getsha() throws Exception {
    }

    @Test
    public void setSha() throws Exception {
    }

    @Test
    public void setUploadparts() throws Exception {
    }

    @Test
    public void getUploadparts() throws Exception {
    }

    @Test
    public void setBizAttr() throws Exception {
    }

    @Test
    public void setBiz_attr() throws Exception {
    }

    @Test
    public void getBizAttr() throws Exception {
    }

    @Test
    public void getBiz_attr() throws Exception {
    }

    @Test
    public void setSliceSize() throws Exception {
    }

    @Test
    public void getInsertOnly() throws Exception {
    }

    @Test
    public void setInsertOnly() throws Exception {
    }

    @Test
    public void getDataStream() throws Exception {
    }

    @Test
    public void getDataByte() throws Exception {
    }

    @Test
    public void getDataFile() throws Exception {
    }

    @Test
    public void setDataFile() throws Exception {
    }

    @Test
    public void setDataInputStream() throws Exception {
    }

    @Test
    public void setDataByte() throws Exception {
    }

    @Test
    public void setMagicContext() throws Exception {
    }

    @Test
    public void getMagicContext() throws Exception {
    }

    @Test
    public void checkParams() throws Exception {
    }

    @Test
    public void test() throws Exception {
//        QServer qServer = QServer.init(getContext());
//        String cosPath = "sample.txt";
//        String srcPath = qServer.createFile(1024 * 64);
//        /** PutObjectRequest 请求对象 */
//        PutObjectRequest putObjectRequest = new PutObjectRequest();
//        /** 设置Bucket */
//        putObjectRequest.setBucket(qServer.bucket);
//        /** 设置cosPath :远程路径*/
//        putObjectRequest.setCosPath(cosPath);
//        /** 设置srcPath: 本地文件的路径 */
//        putObjectRequest.setSrcPath(srcPath);
//        /** 设置 insertOnly: 是否上传覆盖同名文件*/
//        putObjectRequest.setInsertOnly("1");
//        /** 设置sign: 签名，此处使用多次签名 */
//        putObjectRequest.setSign(qServer.getSign());
//
//        /** 设置sha: 是否上传文件时带上sha，一般不需要带*/
//        putObjectRequest.setSha(putObjectRequest.getsha());
//
//        /** 设置listener: 结果回调 */
//        putObjectRequest.setListener(new IUploadTaskListener() {
//            @Override
//            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
//                long progress = ((long) ((100.00 * currentSize) / totalSize));
//                Log.w(QServer.TAG,"progress =" + progress + "%");
//            }
//
//            @Override
//            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
//                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
//                PutObjectResult putObjectResult = (PutObjectResult) cosResult;
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(" 上传结果： ret=" + putObjectResult.code + "; msg =" +putObjectResult.msg + "\n");
//                stringBuilder.append(" access_url= " + putObjectResult.access_url == null ? "null" :putObjectResult.access_url + "\n");
//                stringBuilder.append(" resource_path= " + putObjectResult.resource_path == null ? "null" :putObjectResult.resource_path + "\n");
//                stringBuilder.append(" url= " + putObjectResult.url == null ? "null" :putObjectResult.url + "\n");
//                String result = stringBuilder.toString();
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
//                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg
//                        + "; requestId =" + cosResult.requestId;
//                Log.w(QServer.TAG,result);
//            }
//        });
//        /** 发送请求：执行 */
//        qServer.cosClient.putObject(putObjectRequest);
//
//        qServer.deleteLocalFile(srcPath);
//        qServer.deleteObject(qServer.bucket, cosPath);
    }

    @Test
    public void test2() throws Exception {
//        QServer qServer = QServer.init(getContext());
//        String cosPath = "slice.txt";
//        String srcPath = qServer.createFile(1024 * 1024 + 1024 * 64);
//
//
//        /** PutObjectRequest 请求对象 */
//        PutObjectRequest putObjectRequest = new PutObjectRequest();
//        /** 设置Bucket */
//        putObjectRequest.setBucket(qServer.bucket);
//        /** 设置cosPath :远程路径*/
//        putObjectRequest.setCosPath(cosPath);
//        /** 设置srcPath: 本地文件的路径 */
//        putObjectRequest.setSrcPath(srcPath);
//        /** 设置 insertOnly: 是否上传覆盖同名文件*/
//        putObjectRequest.setInsertOnly("1");
//        /** 设置sign: 签名，此处使用多次签名 */
//        putObjectRequest.setSign(qServer.getSign());
//
//        /** 设置sliceFlag: 是否开启分片上传 */
//        putObjectRequest.setSliceFlag(true);
//        /** 设置slice_size: 若使用分片上传，设置分片的大小 */
//        putObjectRequest.setSlice_size(1024*1024);
//
//        /** 设置sha: 是否上传文件时带上sha，一般带上sha*/
//        putObjectRequest.setSha(SHA1Utils.getFileSha1(cosPath));
//
//        /** 设置listener: 结果回调 */
//        putObjectRequest.setListener(new IUploadTaskListener() {
//            @Override
//            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
//                long progress = ((long) ((100.00 * currentSize) / totalSize));
//                Log.w(QServer.TAG,"progress =" + progress + "%");
//            }
//
//            @Override
//            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
//                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
//                PutObjectResult putObjectResult = (PutObjectResult) cosResult;
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(" 上传结果： ret=" + putObjectResult.code + "; msg =" +putObjectResult.msg + "\n");
//                stringBuilder.append(" access_url= " + putObjectResult.access_url == null ? "null" :putObjectResult.access_url + "\n");
//                stringBuilder.append(" resource_path= " + putObjectResult.resource_path == null ? "null" :putObjectResult.resource_path + "\n");
//                stringBuilder.append(" url= " + putObjectResult.url == null ? "null" :putObjectResult.url);
//                String result = stringBuilder.toString();
//                Log.w(QServer.TAG,result);
//            }
//
//            @Override
//            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
//                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
//                Log.w(QServer.TAG,result);
//            }
//        });
//        /** 发送请求：执行 */
//        qServer.cosClient.putObject(putObjectRequest);
//
//        qServer.deleteLocalFile(srcPath);
//        qServer.deleteObject(qServer.bucket, cosPath);
    }


}