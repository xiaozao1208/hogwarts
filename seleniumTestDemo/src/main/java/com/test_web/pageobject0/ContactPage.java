package com.test_web.pageobject0;


import org.openqa.selenium.By;

//        /**
//         * 添加显示等待
//         */
//        // todo:为什么此处报错，原因是什么？？  Duration.ofSeconds(10).getSeconds() 没有写.getSeconds()
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10)).
//                until(ExpectedConditions.visibilityOfElementLocated(addMember));
//
//        //todo: 就算可以点击，也仍然存在一定的概率是点击不成功的；
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(addMember));

public class ContactPage {

    By addMember =By.linkText("添加成员");
    By username= By.xpath("//*[@id='username']");
    By delete = By.linkText("删除");

    /**
     *此处等待时间 坑比较多； assert
     */
    public ContactPage addMember(String username, String acctid, String mobile){

        /**
         *  点击添加成员
         *  MainPage.driver.findElements(this.username).size() ==0
         *  findElements 什么意思？为什么是复数？
         */

        /**
         * 使用username做参数
         */
//        while(MainPage.driver.findElements(this.username).size() ==0) {
//            MainPage.driver.findElement(addMember).click();
//        }

        /**
         * 当添加成员这个元素 没有消失的时候，一直点击【添加成员】按钮；
         */
        while(MainPage.driver.findElements(addMember).size() > 0){
            MainPage.driver.findElement(addMember).click();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainPage.driver.findElement(this.username).sendKeys(username);
        MainPage.driver.findElement(By.name("acctid")).sendKeys(acctid);
        MainPage.driver.findElement(By.name("mobile")).sendKeys(mobile);

        //点击保存
        MainPage.driver.findElement(By.cssSelector(".js_btn_save")).click();
        return this;

    }


    public ContactPage search(String keyword){

      MainPage.driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//      new WebDriverWait(MainPage.driver, Duration.ofSeconds(10).getSeconds())
//                .until(ExpectedConditions.elementToBeClickable(delete));

      return this;
    }

    /**
     * 删除---确认---关闭
     */
    public ContactPage delete(){

        MainPage.driver.findElement(delete).click();
        MainPage.driver.findElement(By.linkText("确认")).click();
        MainPage.driver.findElement(By.id("clearMemberSearchInput")).click();

        return  this;
    }
}
