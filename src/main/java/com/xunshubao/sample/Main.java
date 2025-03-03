package com.xunshubao.sample;

import com.xunshubao.sample.form.ZxgkSearchForm;
import com.xunshubao.sample.util.SignUtils;

public class Main {
    public static void main(String[] args) {
        // 密钥，请联系销售获取
        // 用户标识
        String appKey = "";
        // 签名密钥
        String signSecretKey = "";
        // SM4密钥
        String sm4SecretKey = "";
        // AES密钥
        String aesSecretKey = "";

        // 请求样例参数
        String companyName = "企业名称";
        String name = "姓名";
        String cardNum = "身份证号";

        //初始化实例
        ZxgkApiService apiService = new ZxgkApiService(appKey, signSecretKey, sm4SecretKey, aesSecretKey);

        // 执行公开核验接口-企业
//        ZxgkSearchForm zxgkCheckCompanyForm = new ZxgkSearchForm();
//        zxgkCheckCompanyForm.setName(companyName);
//        apiService.requestZxgkCheckForCompany(zxgkCheckCompanyForm);

        // 执行公开核验接口-个人
//        ZxgkSearchForm zxgkCheckCompanyForm = new ZxgkSearchForm();
//        zxgkCheckCompanyForm.setName(name);
//        zxgkCheckCompanyForm.setCardNum(cardNum);
//        apiService.requestZxgkCheckForPerson(zxgkCheckCompanyForm);

        // 失信核验接口-企业
//        ZxgkSearchForm checkCompanyForm = new ZxgkSearchForm();
//        checkCompanyForm.setName(companyName);
//        apiService.requestShixinCheckForCompany(checkCompanyForm);

        // 失信核验接口-个人
//        ZxgkSearchForm checkPersonForm = new ZxgkSearchForm();
//        checkPersonForm.setName(name);
//        checkPersonForm.setCardNum(cardNum);
//        apiService.requestShixinCheckForPerson(checkPersonForm);

        // 限制消费核验接口-企业
//        ZxgkSearchForm checkCompanyForm = new ZxgkSearchForm();
//        checkCompanyForm.setName(companyName);
//        apiService.requestXglCheckForCompany(checkCompanyForm);

        // 限制消费核验接口-个人
//        ZxgkSearchForm checkPersonForm = new ZxgkSearchForm();
//        checkPersonForm.setName(name);
//        checkPersonForm.setCardNum(cardNum);
//        apiService.requestXglCheckForPerson(checkPersonForm);

        // 被执行人核验接口-企业
//        ZxgkSearchForm checkCompanyForm = new ZxgkSearchForm();
//        checkCompanyForm.setName(companyName);
//        apiService.requestZhixingCheckForCompany(checkCompanyForm);

        // 被执行人核验接口-个人
//        ZxgkSearchForm checkPersonForm = new ZxgkSearchForm();
//        checkPersonForm.setName(name);
//        String encodedCardNum = SignUtils.digest(ZxgkConstant.DIGEST_MD5, cardNum);
//        checkPersonForm.setCardNum(encodedCardNum);
//        checkPersonForm.setHashParam("cardNum");
//        checkPersonForm.setHashType(ZxgkConstant.DIGEST_MD5);
//        apiService.requestZhixingCheckForPerson(checkPersonForm);

        // 终本案件核验接口-企业
//        ZxgkSearchForm checkCompanyForm = new ZxgkSearchForm();
//        checkCompanyForm.setName(companyName);
//        apiService.requestZhongbenCheckForCompany(checkCompanyForm);

        // 终本案件核验接口-个人
//        ZxgkSearchForm checkPersonForm = new ZxgkSearchForm();
//        checkPersonForm.setName(name);
//        checkPersonForm.setCardNum(cardNum);
//        apiService.requestZhongbenCheckForPerson(checkPersonForm);

        // 执行公开查询接口-企业
//        ZxgkSearchForm queryCompanyForm = new ZxgkSearchForm();
//        queryCompanyForm.setName(companyName);
//        apiService.requestZxgkQueryForCompany(queryCompanyForm);

        // 执行公开查询接口-个人
//        ZxgkSearchForm queryPersonForm = new ZxgkSearchForm();
//        queryPersonForm.setName(name);
//        queryPersonForm.setCardNum(cardNum);
//        apiService.requestZxgkQueryForPerson(queryPersonForm);

        // 执行公开详情查询
//        ZxgkSearchForm dataInfoForm = new ZxgkSearchForm();
//        dataInfoForm.setDataType(ZxgkConstant.DATA_TYPE_ZHONGBEN);
//        dataInfoForm.setDataId("数据ID");
//        apiService.requestZhixingDataInfo(dataInfoForm);

    }
}