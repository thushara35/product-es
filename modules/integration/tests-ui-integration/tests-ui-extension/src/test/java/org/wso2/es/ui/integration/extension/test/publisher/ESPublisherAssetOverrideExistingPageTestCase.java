/*
 *  Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.es.ui.integration.extension.test.publisher;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.extensions.selenium.BrowserManager;
import org.wso2.es.ui.integration.util.BaseUITestCase;
import org.wso2.es.ui.integration.util.ESUtil;
import org.wso2.es.ui.integration.util.ESWebDriver;

import static org.testng.Assert.assertTrue;

/**
 * Overriding an existing page under extension model
 */
public class ESPublisherAssetOverrideExistingPageTestCase extends BaseUITestCase {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.init();
        driver = new ESWebDriver(BrowserManager.getWebDriver());
        baseUrl = getWebAppURL();
        ESUtil.login(driver, baseUrl, PUBLISHER_APP, userInfo.getUserName(), userInfo.getPassword());
    }

    @Test(groups = "wso2.es.extensions", description = "Test overriding existing page in extensions")
    public void testESPublisherAssetOverrideExistingPageTestCase() throws Exception {
        driver.get(baseUrl + "/publisher/assets/gadget/list");
//        driver.findElement(By.cssSelector("button.btn.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("span.btn-asset")).click();

        driver.findElement(By.linkText("Service")).click();
        driver.get(baseUrl + "/publisher/assets/servicex/details");
        assertTrue(isElementPresent(driver, By.id("assetOveriddenDetailsPageH1")));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.get(baseUrl + PUBLISHER_LOGOUT_URL);
        driver.quit();
    }

}
