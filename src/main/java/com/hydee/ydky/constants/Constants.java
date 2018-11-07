package com.hydee.ydky.constants;

public class Constants {
	/**
	 * model封装分页助手对象
	 */
	public static final String ATTR_NAME_PAGEINFO = "pageInfo";		// 分页对象
	public static final String ATTR_NAME_CONDITIONS = "conditions";	// 查询条件
	
	public static final String REG_PHONE = "^([1][34578])\\d{9}$";
	
    /**
     * 默认页码
     */
    public static final Integer PAGE_NUM = 1;

    /**
     * 默认页数
     */
    public static final Integer PAGE_SIZE = 10;

    /**
     * 图片上传的大小限制(MB)
     */
    public static final Integer UPLOAD_IMAGE_MAX_SIZE = 10;

    /**
     * 文件上传的大小限制(MB)
     */
    public static final Integer UPLOAD_FILE_MAX_SIZE = 10;
    
    /**
     * 超管用户对应usercode
     */
	public static final Object ADMIN_USERCODE = "admin";
	
	/**
	 * 接口状态默认值
	 */
	public static final Short INTF_STATUS_DEFAULT_VALUE = 0;
	
	
}
