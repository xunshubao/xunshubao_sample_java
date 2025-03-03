# 循数宝 V3接口JAVA调用示例


```java
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
ZxgkSearchForm zxgkCheckCompanyForm = new ZxgkSearchForm();
zxgkCheckCompanyForm.setName(companyName);
apiService.requestZxgkCheckForCompany(zxgkCheckCompanyForm);

// 执行公开核验接口-个人
ZxgkSearchForm zxgkCheckPersonForm = new ZxgkSearchForm();
zxgkCheckPersonForm.setName(name);
zxgkCheckPersonForm.setCardNum(cardNum);
apiService.requestZxgkCheckForPerson(zxgkCheckPersonForm);
```