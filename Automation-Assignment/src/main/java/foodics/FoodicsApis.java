package foodics;


import utils.ExcelUtils;

import java.io.IOException;

import static utils.ExcelUtils.readExcelFile;

public class FoodicsApis {
    ExcelUtils excelUtils = new ExcelUtils();

    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public String getMERCHANT_INFO_URL() {
        return MERCHANT_INFO_URL;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getFile() {
        return file;
    }

    private  final String LOGIN_URL = "/Login";
    private  final String MERCHANT_INFO_URL = "/GetMerchantInfo";
    private  final String baseUrl = "https://pay.foodics.dev/public-api/v1/App";
    private  final String file = "src/test/resources/LoginTestData/Foodics_Login_TestData.xlsx";
    String[] loginCredentials = readExcelFile("src/test/resources/LoginTestData/Foodics_Login_TestData.xlsx");

    private String username = loginCredentials[0];
    private String password = loginCredentials[1];

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public FoodicsApis() throws IOException {

    }


}


