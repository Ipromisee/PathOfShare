package edu.whu.controller;

/**
 * 用来返回处理信息，前期用System.out.println，后面有前端了用其他方法，直接在这里修改
 */
public class MessageController {
    /**
     * 返回正确信息，如successMessage("登陆")输出登陆成功
     * @param affair 执行事务，如登陆
     * @param message 补充信息，可有可无
     */
    public static void successMessage(String affair,String ... message){
        MessageController.hr();
        System.out.println(affair+"成功！");
        if(message.length>=1){
            System.out.println("补充信息：");
            for (String string:
                 message) {
                System.out.println("*"+string);
            }
        }
        MessageController.hr();
    }
    /**
     * 返回错误信息
     * @param affair 执行事务，如登陆
     * @param error 错误原因
     * @param message 补充信息，可有可无
     */
    public static void errorMessage(String affair,String error,String ... message){
        MessageController.hr();
        System.out.println(affair+"失败！错误原因："+error);
        if(message.length>=1){
            System.out.println("补充信息：");
            for (String string:
                    message) {
                System.out.println("*"+string);
            }
        }
        MessageController.hr();
    }
    public static void message(String message){
        System.out.println(message);
        MessageController.hr();
    }
    public static void hr(){
        System.out.println("-------------------------------------------------------------------");
    }
}
