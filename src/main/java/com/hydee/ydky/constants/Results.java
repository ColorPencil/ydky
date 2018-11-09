package com.hydee.ydky.constants;

/**
 * 用于描述业务接口正常或异常信息
 * 
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年4月20日
 * @version :1.0
 * @remark	:
 * ====================================
 */
public enum Results {
	/********************** 成功返回及失败[200 - 299] *****************************/
	COM_SUCCESS(200,"数据请求成功!"),
	COM_REQUEST_PARAMS_NOT_FULL(201,"请求参数[%s]不能为空!"),
	DATA_IS_NONE(202,"未查询到数据!"),

    /********************** 公共错误码 开始范围 [0 - 99] *****************************/
	SESSION_NOT_AVAILABLE(10,"SESSION已超时,请重新登录"),
	SESSION_HAS_OFFLINE(11,"您的账号已在其他地方登录,如果不是本人操作请修改您的密码"),
    TOKEN_NOT_AVAILABLE(21,"无效或已过期的token"),
    TOKEN_HAS_LOGIN(22,"当前用户已登录,无法获取token"),
    SIGN_NOT_AVAILABLE(32,"非法的签名参数"),
    SIGN_IS_TIMEOUT(33,"签名已超时"),
    AUTH_ULTRA_VIRES(34,"无权限访问该接口"),
    ORG_NOT_AVAILABLE(35,"当前机构不存在或者被禁用"),

    /********************** 用户错误码 开始范围 [100 - 199] *****************************/
    USER_NAME_NOT_EXIST(100,"用户名不存在"),
    USER_PASSWORD_WRONG(101,"用户名或密码错误"),
    USER_PHONE_VERCODE_WRONG(108,"输入手机验证码错误"),
    USER_NOT_USE(102,"用户审核未通过或被禁用"),
    USER_PHONE_NOT_EXIST(103,"该手机号码未在平台注册"),
    USER_PHONE_EXIST(104,"该手机号码已被注册，请更换注册手机号码"),
    USER_MAIL_EXIST(105,"邮箱已存在"),
    WRONG_PASSWORD(106,"密码错误"),
    USER_OLD_PWD_NEW_SAME_WRONG(107,"新旧密码不能相同"),
    USER_CANNOT_DO_THIS(109,"权限不够"),
    USER_NOT_EXIST(110,"用户不存在"),
    USER_HAS_DELETED(111,"用户已经被删除"),
    USER_HAS_LOCKED(112,"用户已经被锁住"),
    USER_SMSCODE_NOTVALID(114,"验证码错误"),
    USER_SMSCODE_GETFAIL(115,"获取验证码失败"),

    /********************** 接口参数异常错误码 开始范围 [400 - 499] *****************************/
    MISSING_REQUEST_PARAMS(400,"缺少请求参数"),
    RESOLVER_PARAMS_FAILED(401,"参数解析失败"),
    CHECKED_PARAMS_FAILED(402,"参数验证失败"),
    BIND_PARAMS_FAILED(403,"参数绑定失败"),
    NOT_FOUND_REQUEST(404,"请求接口地址不存在或已下线"),
    METHOD_NOT_ALLOWED(405,"不支持当前请求方式"),
    NO_AUTHS(406,"您无权限访问该页面！如果管理员已重新分配您的权限请重新登录。"),
    UNSUPPORTED_MEDIA_TYPE(415,"不支持的媒体类型"),
    UPLOAD_FILE_FAILED(420,"上传的文件大小或者类型不符合要求"),
    UPLOAD_EXCEPTION(421,"文件上传异常"),
    UPLOAD_TYPE_ERROR(422,"上传的文件类型不符合要求"),

    /********************** 通用模块业务类错误码 开始范围 [500 - 999] *****************************/
    COM_FAILD(500,"数据请求失败!"),
    SERVICE_EXCEPTION(501,"服务器繁忙,稍后请重试!"),

    /**********************系统业务类错误码 开始范围 [1000 - 9999] *****************************/
    NO_AUTH_SELECTED(1000,"请至少选择一个权限！"), 
    NO_UGROUP_SELECTED(1001,"未选择用户组角色或该角色已删除！"), 
    UGROUP_HAS_BE_USED(1002,"用户组角色正在被用户占用，无法删除！"),
    INTFSTATUS_IS_NOT_FOUND(1003,"当前医院或门店ID未在平台注册！"),
	PRESCID_IS_DISABLED(1004,"该批次存在处方异动数据，数据打回！"), 
	REDIS_SERVER_IS_UNSTARTED(1005,"应用缓存服务未启动！"),
	ORG_ID_NOT_VALIDE(1006, "请求方机构ID不存在！"),
	UNDISPOSED_PRESC_COUNT_TOO_MUCH(1007, "该门店未处理处方单过多，不能继续下载处方！"),
	SQL_EXCEPTION(1008, "数据库执行平台SQL脚本错误！"),
	PRESCID_IS_SOLD(1009, "该批次存在已销售的处方数据%s，请核对！"),
    //The user code already exists
	ACC_STATUS_NOT_COMPLETED(1010, "该订单存在状态不一致的处方单！"),

    /***************新增用户判断用户编号以及用户电话是否已存在*****************/
    THE_USER_CODE_ALREADY_QXISTS(601,"用户编码已被注册！"),
    THE_USER_PHONE_ALREADY_QXISTS(602,"电话已被注册！"),
    THE_USER_CODE_AND_PHONE(603,"用户编码和电话号码已被注册！"),

    /*************************新增医院时判断医院是否已存在***************************************/
    //The hospital has already existed
    THE_HOSPID_HAS_ALREADY_EXISTED(650,"该医院id已存在，请重新输入！"),
    THE_HOSPHIS_HAS_ALREADY_EXISTED(651,"该医院HIS编码已经存在，请重新输入！"),
    THE_HOSPTAL_IDANDHIS_HAS_ALREADY_EXISTED(652,"该医院id以及HIS编码已存在，请重新输入"),

    THE_BUSIID_ALREADY_EXISTED(660,"该零售门店id已存在，请重新输入！"),

    THE_HSOPITAL_STORE_AUTHORIZATION_ALREADY_EXISTS(670,"该信息已存在，若需修改请点击修改按钮进行修改！"),

    /*************************售药机模块***************************************/
    MACHINE_DCM_TYPE_NOT_fOUND(680,"传入的type不正确！"),
    MACHINE_DCM_TYPE_NOT_UPDATEED(690,"没有更新的数据！");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    Results(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
