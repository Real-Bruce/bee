package com.bee.common.constant;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description 常量
 */
public interface Constant {

    /**
     * 成功
     */
    int SUCCESS = 1;

    /**
     * 失败
     */
    int FAIL = 0;

    /**
     * 资源根节点
     */
    Long MENU_BOOT = 0L;

    /**
     * 部门根节点
     */
    Long DEPT_ROOT = 0L;

    /**
     * 升序
     */
    String ASC = "asc";

    /**
     * 降序
     */
    String DESC = "desc";

    /**
     * 创建时间字段
     */
    String CREATE_DATE = "create_date";

    /**
     * 数据权限过滤
     */
    String SQL_FILTER = "sqlFilter";

    /**
     * 当前页码
     */
    String PAGE = "page";

    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";

    /**
     * 排序字段
     */
    String ORDER_FIELD = "orderField";

    /**
     * 排序方式
     */
    String ORDER = "order";

    /**
     * token header
     */
    String TOKEN_HEADER = "token";

    /**
     * 云存储配置key
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    /**
     * 定时任务状态
     */
    enum SchedulesStatus {
        /**
         * 暂停
         */
        PAUSE(0),

        /**
         * 正常
         */
        NORMAL(1)
        ;

        private int value;

        SchedulesStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 阿里云
         */
        QCLOUD(3)
        ;

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
