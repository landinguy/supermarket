package com.example.supermarket.util;import okhttp3.*;import org.apache.commons.collections.MapUtils;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.web.multipart.MultipartFile;import java.io.IOException;import java.util.Map;import java.util.Optional;import java.util.concurrent.TimeUnit;import java.util.stream.Collectors;public class HttpUtil {    private final static Logger log = LoggerFactory.getLogger(HttpUtil.class);    private final static OkHttpClient okHttpClient = new OkHttpClient.Builder()            .connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES))            .readTimeout(20, TimeUnit.SECONDS)            .writeTimeout(20, TimeUnit.SECONDS)            .connectTimeout(20, TimeUnit.SECONDS).build();    public static String httpGet(String url, Map<String, String> headMap, Map<String, Object> params) {        Request.Builder builder = new Request.Builder();        Optional.ofNullable(headMap).ifPresent(map -> map.forEach(builder::addHeader));        if (MapUtils.isNotEmpty(params)) {            String join = params.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue()).collect(Collectors.joining("&"));            url = String.join("?", url, join);        }        Call call = okHttpClient.newCall(builder.url(url).build());        Response response;        try {            response = call.execute();            return response.body().string();        } catch (IOException e) {            log.error("发送 get 请求出现异常！", e);            e.printStackTrace();        }        return "";    }    public static byte[] httpGetBytes(String url) {        byte[] bytes = null;        Request request = new Request.Builder().url(url).build();        Call call = okHttpClient.newCall(request);        try {            bytes = call.execute().body().bytes();        } catch (Exception e) {            log.error("http get bytes error", e);        }        return bytes;    }    /*** post json ***/    public static String post(String url, Map<String, String> headMap, String params) {        String response = null;        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");        RequestBody body = RequestBody.create(mediaType, params);        Request.Builder builder = new Request.Builder().url(url).post(body);        if (headMap != null) headMap.forEach(builder::addHeader);        Call call = okHttpClient.newCall(builder.build());        try {            response = call.execute().body().string();            //log.info("response#{}", response);        } catch (IOException e) {            log.error("send post error", e);        }        return response;    }    /*** post Content-Type:multipart/form-data 上传文件及表单数据***/    public static String post(String url, Map<String, String> headerMap, Map<String, Object> params) {        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.ALTERNATIVE);        //添加表单数据        for (Map.Entry<String, Object> item : params.entrySet()) {            if (item.getValue() instanceof MultipartFile) {                MultipartFile file = (MultipartFile) item.getValue();                try {                    builder.addFormDataPart("file", file.getOriginalFilename(), RequestBody.create(MediaType.parse("application/octet-stream"), file.getBytes()));                } catch (IOException e) {                    e.printStackTrace();                }            } else {                builder.addFormDataPart(item.getKey(), String.valueOf(item.getValue()));            }        }        //添加文件        //for (File file : files) {        //    builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));        //}        RequestBody multipartBody = builder.build();        Request.Builder requestBuilder = new Request.Builder()                .url(url)                .header("Content-Type", "multipart/form-data")                .post(multipartBody);        for (Map.Entry<String, String> item : headerMap.entrySet()) {            requestBuilder.addHeader(item.getKey(), item.getValue());        }        String result = "";        try {            Response response = okHttpClient.newCall(requestBuilder.build()).execute();            result = response.body().string();        } catch (Exception e) {            log.error("send post error", e);        }        return result;    }}