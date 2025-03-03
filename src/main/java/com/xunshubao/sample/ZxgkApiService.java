package com.xunshubao.sample;

import com.alibaba.fastjson.JSON;
import com.xunshubao.sample.form.PostDataForm;
import com.xunshubao.sample.form.RequestHeader;
import com.xunshubao.sample.form.ZxgkSearchForm;
import com.xunshubao.sample.util.SM4Utils;
import com.xunshubao.sample.util.SignUtils;
import com.xunshubao.sample.vo.*;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.util.Timeout;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class ZxgkApiService {
    private static final Logger logger = LoggerFactory.getLogger(ZxgkApiService.class);
    private String appKey;
    private String signSecretKey;
    private String sm4SecretKey;
    private String aesSecretKey;

    // 超时时间
    private long timeoutSeconds = 5;


    public ZxgkApiService(String appKey, String signSecretKey, String sm4SecretKey, String aesSecretKey) {
        this.appKey = appKey;
        this.signSecretKey = signSecretKey;
        this.sm4SecretKey = sm4SecretKey;
        this.aesSecretKey = aesSecretKey;
    }


    /**
     * 执行公开核验接口-企业
     *
     * @param searchForm 业务请求参数
     * @return ZxgkVerifyResultVo 核验结果
     */
    public ZxgkVerifyResultVo requestZxgkCheckForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 执行公开核验接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZXGK_CHECK_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZxgkVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZxgkVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("执行公开核验接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 执行公开核验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ZxgkVerifyResultVo 核验结果
     */
    public ZxgkVerifyResultVo requestZxgkCheckForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 执行公开核验接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZXGK_CHECK_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZxgkVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZxgkVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("执行公开核验接口-个人 失败", e);
        }
        return null;
    }


    /**
     * 失信核验接口-企业
     *
     * @param searchForm 业务请求参数
     * @return ShixinVerifyResultVo 核验结果
     */
    public ShixinVerifyResultVo requestShixinCheckForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 失信核验接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SHIXIN_CHECK_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ShixinVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ShixinVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("失信核验接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 失信核验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ShixinVerifyResultVo 核验结果
     */
    public ShixinVerifyResultVo requestShixinCheckForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 失信核验接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SHIXIN_CHECK_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ShixinVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ShixinVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("失信核验接口-个人 失败", e);
        }
        return null;
    }


    /**
     * 限制消费核验接口-企业
     *
     * @param searchForm 业务请求参数
     * @return XglVerifyResultVo 核验结果
     */
    public XglVerifyResultVo requestXglCheckForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 限制消费核验接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_XGL_CHECK_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            XglVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, XglVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("限制消费核验接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 限制消费核验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ZxgkVerifyResultVo 核验结果
     */
    public XglVerifyResultVo requestXglCheckForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 执行公开核验接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_XGL_CHECK_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            XglVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, XglVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("执行公开核验接口-个人 失败", e);
        }
        return null;
    }


    /**
     * 被执行人核验接口-企业
     *
     * @param searchForm 业务请求参数
     * @return ZhixingVerifyResultVo 核验结果
     */
    public ZhixingVerifyResultVo requestZhixingCheckForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 被执行人核验接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZHIXING_CHECK_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhixingVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZhixingVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("被执行人核验接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 被执行人核验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ZhixingVerifyResultVo 核验结果
     */
    public ZhixingVerifyResultVo requestZhixingCheckForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 被执行人核验接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZHIXING_CHECK_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhixingVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZhixingVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("被执行人核验接口-个人 失败", e);
        }
        return null;
    }


    /**
     * 终本案件核验接口-企业
     *
     * @param searchForm 业务请求参数
     * @return ZhongbenVerifyResultVo 核验结果
     */
    public ZhongbenVerifyResultVo requestZhongbenCheckForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 终本案件核验接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZHONGBEN_CHECK_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhongbenVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZhongbenVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("终本案件核验接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 终本案件核验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ZhongbenVerifyResultVo 核验结果
     */
    public ZhongbenVerifyResultVo requestZhongbenCheckForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 终本案件核验接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZHONGBEN_CHECK_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhongbenVerifyResultVo verifyResultVo = JSON.parseObject(decodedData, ZhongbenVerifyResultVo.class);
            return verifyResultVo;
        } catch (Exception e) {
            logger.error("终本案件核验接口-个人 失败", e);
        }
        return null;
    }


    /**
     * 执行公开查询接口-企业
     *
     * @param searchForm 业务请求参数
     * @return ZxgkQueryResultVo 查询结果
     */
    public ZxgkQueryResultVo requestZxgkQueryForCompany(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 执行公开查询接口-企业 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZXGK_QUERY_COMOPANY)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZxgkQueryResultVo queryResultVo = JSON.parseObject(decodedData, ZxgkQueryResultVo.class);
            return queryResultVo;
        } catch (Exception e) {
            logger.error("执行公开查询接口-企业 失败", e);
        }
        return null;
    }

    /**
     * 执行公查询验接口-个人
     *
     * @param searchForm 业务请求参数
     * @return ZxgkQueryResultVo 查询结果
     */
    public ZxgkQueryResultVo requestZxgkQueryForPerson(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 执行公开查询接口-个人 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_ZXGK_QUERY_PERSON)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZxgkQueryResultVo queryResultVo = JSON.parseObject(decodedData, ZxgkQueryResultVo.class);
            return queryResultVo;
        } catch (Exception e) {
            logger.error("执行公开查询接口-个人 失败", e);
        }
        return null;
    }

    /**
     * 失信详细信息
     *
     * @param searchForm dataType & dataId
     * @return ShixinDataInfoVo
     */
    public ShixinDataInfoVo requestShixinDataInfo(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 失信详情 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SIFA_DATA_INFO)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ShixinDataInfoVo dataInfoVo = JSON.parseObject(decodedData, ShixinDataInfoVo.class);
            return dataInfoVo;
        } catch (Exception e) {
            logger.error("失信详情 失败", e);
        }
        return null;
    }

    /**
     * 限制消费详细信息
     *
     * @param searchForm dataType & dataId
     * @return XglDataInfoVo
     */
    public XglDataInfoVo requestXglDataInfo(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 限制消费详情 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SIFA_DATA_INFO)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            XglDataInfoVo dataInfoVo = JSON.parseObject(decodedData, XglDataInfoVo.class);
            return dataInfoVo;
        } catch (Exception e) {
            logger.error("限制消费 失败", e);
        }
        return null;
    }

    /**
     * 被执行人详细信息
     *
     * @param searchForm dataType & dataId
     * @return ZhixingDataInfoVo
     */
    public ZhixingDataInfoVo requestZhixingDataInfo(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 被执详情 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SIFA_DATA_INFO)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhixingDataInfoVo dataInfoVo = JSON.parseObject(decodedData, ZhixingDataInfoVo.class);
            return dataInfoVo;
        } catch (Exception e) {
            logger.error("被执详情 失败", e);
        }
        return null;
    }

    /**
     * 终本案件详细信息
     *
     * @param searchForm dataType & dataId
     * @return ShixinDataInfoVo
     */
    public ZhongbenDataInfoVo requestZhongbenDataInfo(ZxgkSearchForm searchForm) {
        try {
            logger.debug(">> 终本案件详情 <<");
            // 获取当前时间戳
            String timestamp = String.valueOf(DateTime.now().getMillis());
            // 将业务请求参数转化为JSON字符串
            String searchFormJson = JSON.toJSONString(searchForm);
            logger.debug("业务请求参数如下：{}", searchFormJson);
            // 组装并生成token
            String tokenSrc = appKey + timestamp + signSecretKey + searchFormJson;
            String token = SignUtils.digest(ZxgkConstant.DIGEST_SM3, tokenSrc);
            // 请求头封装
            RequestHeader requestHeader = new RequestHeader(appKey, timestamp, token,
                    ZxgkConstant.DIGEST_SM3, ZxgkConstant.ENCRYPTION_SM4, searchForm.getRequestId());
            logger.debug("请求头信息如下：{}", JSON.toJSONString(requestHeader));

            // 业务请求参数使用SM4进行加密
            String requestBody = SM4Utils.encryptBase64ECB(searchFormJson, sm4SecretKey, SM4Utils.Padding.PKCS5);
            // 组装并生成请求内容
            PostDataForm postDataForm = new PostDataForm(requestHeader, requestBody);
            String bodyString = JSON.toJSONString(postDataForm);
            // 向服务器提交请求并获取返回结果
            String result = Request.post(ZxgkConstant.API_HOST + ZxgkConstant.URI_SIFA_DATA_INFO)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .connectTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .responseTimeout(Timeout.ofSeconds(timeoutSeconds))
                    .bodyString(bodyString, ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString(Charset.forName("utf-8"));
            logger.debug("原始报文如下：");
            logger.debug(result);
            // 将原始报文结果进行解析映射
            ResultVo resultVo = JSON.parseObject(result, ResultVo.class);
            logger.debug("code = {}", resultVo.getCode());
            logger.debug("msg = {}", resultVo.getMsg());
            if (!ZxgkConstant.RESULT_CODE_SUCCESS.equals(resultVo.getCode())) {
                return null;
            }
            // 将原始报文中的内容进行解密
            String decodedData = SM4Utils.decryptBase64ECB(resultVo.getData(), sm4SecretKey, SM4Utils.Padding.PKCS5);
            logger.debug("解密后的返回结果如下：");
            logger.debug(decodedData);
            logger.debug("<< ---- >>");
            ZhongbenDataInfoVo dataInfoVo = JSON.parseObject(decodedData, ZhongbenDataInfoVo.class);
            return dataInfoVo;
        } catch (Exception e) {
            logger.error("终本案件详情 失败", e);
        }
        return null;
    }


}
