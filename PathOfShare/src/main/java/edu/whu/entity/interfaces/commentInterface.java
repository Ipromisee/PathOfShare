package edu.whu.entity.interfaces;

public interface commentInterface {
    /**
     * system.out.println内容+特殊标识
     */
    public void show();

    /**
     * 获得实际输出内容
     * @return 内容+特殊标识
     */
    public String displayContent();

    /**
     * 获得用户类型
     * @return visitor/manager/user
     */
    public String fromWho();

    /**
     * 获得权限大小
     * @return 管理者、用户、游客分别是3、2、1
     */
    public int getPriority();
}
