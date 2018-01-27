package com.tencent.qcloud.cosv4_1436.model;

import android.test.AndroidTestCase;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.cos.common.COSAuthority;
import com.tencent.cos.model.COSRequest;
import com.tencent.cos.model.COSResult;
import com.tencent.cos.model.CopyObjectRequest;
import com.tencent.cos.model.CreateDirRequest;
import com.tencent.cos.model.CreateDirResult;
import com.tencent.cos.model.DeleteObjectRequest;
import com.tencent.cos.model.GetObjectMetadataRequest;
import com.tencent.cos.model.GetObjectMetadataResult;
import com.tencent.cos.model.GetObjectRequest;
import com.tencent.cos.model.ListDirRequest;
import com.tencent.cos.model.ListDirResult;
import com.tencent.cos.model.MoveObjectRequest;
import com.tencent.cos.model.PutObjectRequest;
import com.tencent.cos.model.PutObjectResult;
import com.tencent.cos.model.RemoveEmptyDirRequest;
import com.tencent.cos.model.UpdateObjectRequest;
import com.tencent.cos.task.listener.ICmdTaskListener;
import com.tencent.cos.task.listener.IDownloadTaskListener;
import com.tencent.cos.task.listener.IUploadTaskListener;
import com.tencent.cos.utils.SHA1Utils;
import com.tencent.qcloud.cosv4_1436.QServer;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bradyxiao on 2018/1/23.
 */

public class V4UnitTest extends AndroidTestCase {

    String dir;
    String object;

    public void createDir(){
        QServer qServer = QServer.init(getContext());
        String cosPath = "/test/";
        dir = cosPath;
        /** CreateDirRequest 请求对象 */
        CreateDirRequest createDirRequest = new CreateDirRequest();
        /** 设置Bucket */
        createDirRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        createDirRequest.setCosPath(cosPath);
        /** 设置biz_attr（一般不需要设置） :文件夹属性*/
        String biz_attr = "biz_attr";
        createDirRequest.setBizAttr(biz_attr);
        /** 设置sign: 签名，此处使用多次签名 */
        createDirRequest.setSign(qServer.getSign());
        /** 设置listener: 结果回调 */
        createDirRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                CreateDirResult createDirResult = (CreateDirResult) cosResult;
                String result = "目录创建： ret=" + createDirResult.code + "; msg=" + createDirResult.msg
                        + "ctime = " + createDirResult.ctime;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result =  "目录创建失败：ret=" + cosResult.code  + "; msg =" + cosResult.msg
                        + "; requestId =" ;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.createDir(createDirRequest);
    }

    public void removeEmptyDir(){
        QServer qServer = QServer.init(getContext());
        String cosPath = dir;

        /** RemoveEmptyDirRequest 请求对象，只能删除空文件夹，其他无效 */
        RemoveEmptyDirRequest removeEmptyDirRequest = new RemoveEmptyDirRequest();
        /** 设置Bucket */
        removeEmptyDirRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        removeEmptyDirRequest.setCosPath(cosPath);
        /** 设置sign: 签名，此处使用单次签名 */
        removeEmptyDirRequest.setSign(qServer.getSignOnce(cosPath));
        /** 设置listener: 结果回调 */
        removeEmptyDirRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除成功：code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除失败：code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.removeEmptyDir(removeEmptyDirRequest);
    }

    public void listDir(){
        QServer qServer = QServer.init(getContext());
        String cosPath = "/";
        String prefix = "t";

        /** ListDirRequest 请求对象 */
        ListDirRequest listDirRequest = new ListDirRequest();
        /** 设置Bucket */
        listDirRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        listDirRequest.setCosPath(cosPath);
        /** 设置num :预查询的目录数*/
        listDirRequest.setNum(100);
        /** 设置content: 透传字段，首次拉取必须清空。拉取下一页，需要将前一页返回值中的context透传到参数中*/
        listDirRequest.setContent("");
        /** 设置sign: 签名，此处使用多次签名 */
        listDirRequest.setSign(qServer.getSign());
        /** 设置listener: 结果回调 */
        listDirRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                ListDirResult listObjectResult = (ListDirResult) cosResult;
                //文件夹 =》不含有  filesize 或 sha 或 access_url 或 souce_url
                StringBuilder stringBuilder = new StringBuilder("目录列表查询结果如下：");
                stringBuilder.append("code =" + listObjectResult.code + "; msg =" + listObjectResult.msg + "\n");
                stringBuilder.append("list是否结束：").append(listObjectResult.listover).append("\n");
                stringBuilder.append("content = " + listObjectResult.context + "\n");
                if(listObjectResult.infos != null && listObjectResult.infos.size() > 0){
                    int length = listObjectResult.infos.size();
                    String str;
                    JSONObject jsonObject;
                    StringBuilder fileStringBuilder = new StringBuilder();
                    StringBuilder dirStringBuilder = new StringBuilder();
                    for(int i = 0; i < length; i++){
                        str = listObjectResult.infos.get(i);
                        try {
                            jsonObject = new JSONObject(str);
                            if(jsonObject.has("sha")){
                                //是文件
                                fileStringBuilder.append("文件：" + jsonObject.optString("name")).append("\n");
                            }else{
                                //是文件夹
                                dirStringBuilder.append("文件夹： " + jsonObject.optString("name")).append("\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    stringBuilder.append(fileStringBuilder);
                    stringBuilder.append(dirStringBuilder);
                }else{
                    stringBuilder.append("该目录下无内容");
                }
                String result = stringBuilder.toString();
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "目录查询失败：ret=" + cosResult.code  + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        /** 设置 prefix: 前缀查询的字符串,开启前缀查询 */
        if(!TextUtils.isEmpty(prefix)){
            listDirRequest.setPrefix(prefix);
        }
        /** 发送请求：执行 */
        qServer.cosClient.listDir(listDirRequest);
    }

    public void uploadSample() throws IOException {
        QServer qServer = QServer.init(getContext());
        String cosPath = "sample.txt";
        object = cosPath;
        String srcPath = qServer.createFile(1024 * 64);
        /** PutObjectRequest 请求对象 */
        PutObjectRequest putObjectRequest = new PutObjectRequest();
        /** 设置Bucket */
        putObjectRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        putObjectRequest.setCosPath(cosPath);//("x-cos-meta", "meta");
        /** 设置srcPath: 本地文件的路径 */
        putObjectRequest.setSrcPath(srcPath);
        /** 设置 insertOnly: 是否上传覆盖同名文件*/
        putObjectRequest.setInsertOnly("1");
        /** 设置sign: 签名，此处使用多次签名 */
        putObjectRequest.setSign(qServer.getSign());

        /** 设置sha: 是否上传文件时带上sha，一般不需要带*/
        putObjectRequest.setSha(putObjectRequest.getsha());

        /** 设置listener: 结果回调 */
        putObjectRequest.setListener(new IUploadTaskListener() {
            @Override
            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
                long progress = ((long) ((100.00 * currentSize) / totalSize));
                Log.w(QServer.TAG,"progress =" + progress + "%");
            }

            @Override
            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                PutObjectResult putObjectResult = (PutObjectResult) cosResult;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" 上传结果： ret=" + putObjectResult.code + "; msg =" +putObjectResult.msg + "\n");
                stringBuilder.append(" access_url= " + putObjectResult.access_url == null ? "null" :putObjectResult.access_url + "\n");
                stringBuilder.append(" resource_path= " + putObjectResult.resource_path == null ? "null" :putObjectResult.resource_path + "\n");
                stringBuilder.append(" url= " + putObjectResult.url == null ? "null" :putObjectResult.url + "\n");
                String result = stringBuilder.toString();
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg
                        + "; requestId =" ;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.putObject(putObjectRequest);

        qServer.deleteLocalFile(srcPath);
    }

    public void updateObject(){
        QServer qServer = QServer.init(getContext());
        String cosPath = object;

        Map<String, String> customer = new LinkedHashMap<String, String>();
        customer.put("Cache-Control","no-cache");
        customer.put("Content-Disposition","attachment");
        customer.put("Content-Language","content=\"zh-cn\"");
        customer.put("x-cos-meta-","cos-meth");
        String biz_attr = "biz_attr";
        String authority = COSAuthority.EINVALID;
        /** UpdateObjectRequest 请求对象 */
        UpdateObjectRequest updateObjectRequest = new UpdateObjectRequest();
        /** 设置Bucket */
        updateObjectRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        updateObjectRequest.setCosPath(cosPath);
        /** 设置sign: 签名，此处使用单次签名 */
        updateObjectRequest.setSign(qServer.getSignOnce(cosPath));
        /** biz_attr: 更新属性 */
        updateObjectRequest.setBizAttr(biz_attr);
        /** biz_attr: 更新文件头部属性*/
        updateObjectRequest.setCustomHeader(customer);
        /** biz_attr: 更新文件权限属性 */
        updateObjectRequest.setAuthority(authority);
        /** 设置listener: 结果回调 */
        updateObjectRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "更新成功：" + cosResult.code+" : "+cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "更新失败:" + cosResult.code+" : "+cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.updateObject(updateObjectRequest);
    }

    public void getObjectMeta(){
        QServer qServer = QServer.init(getContext());
        String cosPath = object;

        /** GetObjectMetadataRequest 请求对象 */
        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest();
        /** 设置Bucket */
        getObjectMetadataRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        getObjectMetadataRequest.setCosPath(cosPath);
        /** 设置sign: 签名，此处使用多次签名 */
        getObjectMetadataRequest.setSign(qServer.getSign());
        /** 设置listener: 结果回调 */
        getObjectMetadataRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                GetObjectMetadataResult getObjectMetadataResult = (GetObjectMetadataResult) cosResult;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("查询结果 = ")
                        .append(" code = ").append(getObjectMetadataResult.code)
                        .append(", msg =").append(getObjectMetadataResult.msg)
                        .append(", ctime =").append(getObjectMetadataResult.mtime)
                        .append(", mtime =").append( getObjectMetadataResult.ctime).append("\n")
                        .append(", filesize =").append(getObjectMetadataResult.filesize)
                        .append(", filesize =").append(getObjectMetadataResult.filesize)
                        .append(", access_url =").append(getObjectMetadataResult.access_url == null?"null":getObjectMetadataResult.access_url)
                        .append(", sha =").append(getObjectMetadataResult.sha == null?"null":getObjectMetadataResult.sha)
                        .append(", authority =").append(getObjectMetadataResult.authority == null?"null":getObjectMetadataResult.authority)
                        .append(", biz_attr =").append(getObjectMetadataResult.biz_attr == null?"null":getObjectMetadataResult.biz_attr);
                String result = stringBuilder.toString();
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "查询出错： ret =" + cosResult.code + "; msg =" + cosResult.msg
                        + "; requestId =" ;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.getObjectMetadata(getObjectMetadataRequest);
    }

    public void moveObject(){
        QServer qServer = QServer.init(getContext());
        String cosPathSrc = object;
        String cosPathDest = "move.txt";
        object = cosPathDest;

        /** MoveObjectRequest 请求对象 */
        MoveObjectRequest moveObjectRequest = new MoveObjectRequest();
        /** 设置Bucket */
        moveObjectRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程源文件路径*/
        moveObjectRequest.setCosPath(cosPathSrc);
        /** 设置dest_fileId :远程目标文件路径*/
        moveObjectRequest.setDestFileId(cosPathDest);
        /** 设置to_Over_Write :是否覆盖 0：不覆盖 1：覆盖*/
        moveObjectRequest.setToOverWrite(1);
        /** 设置sign: 签名，此处使用单次签名 */
        moveObjectRequest.setSign(qServer.getSignOnce(cosPathSrc));
        /** 设置listener: 结果回调 */
        moveObjectRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "移动成功: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest COSRequest, COSResult cosResult) {
                String result = "移动失败: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        qServer.cosClient.moveObjcet(moveObjectRequest);
    }

    public void copyObject(){
        QServer qServer = QServer.init(getContext());
        String cosPathSrc = object;
        String cosPathDest = "copy.txt";
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest();
        copyObjectRequest.setBucket(qServer.bucket);
        copyObjectRequest.setCosPath(cosPathSrc);
        copyObjectRequest.setDestFileId(cosPathDest);
        copyObjectRequest.setToOverWrite(1);
        copyObjectRequest.setSign(qServer.getSignOnce(cosPathSrc));
        copyObjectRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "复制成功: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest COSRequest, COSResult cosResult) {
                String result = "复制失败: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        qServer.cosClient.copyObject(copyObjectRequest);

        qServer.deleteObject(qServer.bucket, cosPathDest);
    }

    public void getObject(){
        if(!object.startsWith("/")) object = "/" + object;
        String url = "http://androidtest-1253653367.cosgz.myqcloud.com" + object;
        QServer qServer = QServer.init(getContext());
        String savePath = getContext().getCacheDir().getPath();

        /** GetObjectRequest 请求对象 */
        GetObjectRequest getObjectRequest = new GetObjectRequest(url,savePath);
        //若是设置了防盗链则需要签名；否则，不需要
        /** 设置listener: 结果回调 */
        getObjectRequest.setListener(new IDownloadTaskListener() {
            @Override
            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
                long progress = (long) ((100.00 * currentSize) / totalSize);
                Log.w(QServer.TAG,"progress =" + progress + "%");
            }

            @Override
            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
                String result ="下载失败 : code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "下载成功 : code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result ="下载失败：code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        qServer.cosClient.getObject(getObjectRequest);

        qServer.deleteLocalFile(savePath + object);
    }

    public void deleteObject(){
        QServer qServer = QServer.init(getContext());
        String cosPath = object;

        /** DeleteObjectRequest 请求对象 */
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest();
        /** 设置Bucket */
        deleteObjectRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        deleteObjectRequest.setCosPath(cosPath);
        /** 设置sign: 签名，此处使用单次签名 */
        deleteObjectRequest.setSign(qServer.getSignOnce(cosPath));
        /** 设置listener: 结果回调 */
        deleteObjectRequest.setListener(new ICmdTaskListener() {
            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除成功: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "删除失败: code =" + cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.deleteObject(deleteObjectRequest);
    }

    public void uploadLarge() throws IOException {
        QServer qServer = QServer.init(getContext());
        String cosPath = "slice.txt";
        String srcPath = qServer.createFile(1024 * 1024 + 1024 * 64 + 1);


        /** PutObjectRequest 请求对象 */
        PutObjectRequest putObjectRequest = new PutObjectRequest();
        /** 设置Bucket */
        putObjectRequest.setBucket(qServer.bucket);
        /** 设置cosPath :远程路径*/
        putObjectRequest.setCosPath(cosPath);
        /** 设置srcPath: 本地文件的路径 */
        putObjectRequest.setSrcPath(srcPath);
        /** 设置 insertOnly: 是否上传覆盖同名文件*/
        putObjectRequest.setInsertOnly("1");
        /** 设置sign: 签名，此处使用多次签名 */
        putObjectRequest.setSign(qServer.getSign());

        /** 设置sliceFlag: 是否开启分片上传 */
        putObjectRequest.setSliceFlag(true);
        /** 设置slice_size: 若使用分片上传，设置分片的大小 */
        putObjectRequest.setSlice_size(1024*1024);

        /** 设置sha: 是否上传文件时带上sha，一般带上sha*/
        putObjectRequest.setSha(SHA1Utils.getFileSha1(srcPath));

        /** 设置listener: 结果回调 */
        putObjectRequest.setListener(new IUploadTaskListener() {
            @Override
            public void onProgress(COSRequest cosRequest, long currentSize, long totalSize) {
                long progress = ((long) ((100.00 * currentSize) / totalSize));
                Log.w(QServer.TAG,"progress =" + progress + "%");
            }

            @Override
            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                PutObjectResult putObjectResult = (PutObjectResult) cosResult;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" 上传结果： ret=" + putObjectResult.code + "; msg =" +putObjectResult.msg + "\n");
                stringBuilder.append(" access_url= " + putObjectResult.access_url == null ? "null" :putObjectResult.access_url + "\n");
                stringBuilder.append(" resource_path= " + putObjectResult.resource_path == null ? "null" :putObjectResult.resource_path + "\n");
                stringBuilder.append(" url= " + putObjectResult.url == null ? "null" :putObjectResult.url);
                String result = stringBuilder.toString();
                Log.w(QServer.TAG,result);
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                String result = "上传出错： ret =" +cosResult.code + "; msg =" + cosResult.msg;
                Log.w(QServer.TAG,result);
            }
        });
        /** 发送请求：执行 */
        qServer.cosClient.putObject(putObjectRequest);

        qServer.deleteLocalFile(srcPath);
        qServer.deleteObject(qServer.bucket, cosPath);
    }

    @Test
    public void test() throws Exception {
        createDir();
        removeEmptyDir();
        listDir();
        uploadSample();
        updateObject();
        getObjectMeta();
        moveObject();
        copyObject();
        getObject();
        deleteObject();
        uploadLarge();
    }
}
