#自定义常量，用于传输时ret_code的值，确定返回状态
class Constant:
    #登录
    LOGIN_NO_USER = 0
    LOGIN_PASS_ERROR = 1
    LOGIN_OK = 2
    #注册
    REGISTER_FAIL = 0
    REGISTER_OK = 1
    #修改用户信息
    UPDATE_USER_NOUSER = 0
    UPDATE_USER_OK = 1
    #删除用户
    DEL_USER_NO_USER = 0
    DEL_USER_OK = 1
    #获取用户数据
    GET_USER_NOUSER = 0
    GET_USER_OK = 1

    #添加商品
    ADD_COMMODITY_ISEXIST = 0
    ADD_COMMODITY_OK = 1
    #删除商品
    DEL_COMMODITY_NO_COM = 0
    DEL_COMMODITY_OK = 1
    #修改商品信息
    UPDATE_COMMODITY_NO_COM = 0
    UPDATE_COMMODITY_OK = 1
    #根据id获取商品信息
    GET_COMMODITY_BY_ID_NO_COM = 0
    GET_COMMODITY_BY_ID_OK = 1
    #根据用户名获取商品信息
    GET_COMMODITY_BY_USER_NOUSER = 0
    GET_COMMODITY_BY_USER_ONE = 1
    GET_COMMODITY_BY_USER_MORE = 2
    #根据商品名模糊查询
    GET_COMMODITY_BY_NAME_NOUSER = 0
    GET_COMMODITY_BY_NAME_ONE = 1
    GET_COMMODITY_BY_NAME_MORE = 2

    #添加订单
    ADD_ORDER_OK = 1
    #删除订单
    DEL_ORDER_NOORDER = 0
    DEL_ORDER_OK = 1
    #修改订单
    UPDATE_ORDER_NOORDER = 0
    UPDATE_ORDER_OK = 1
    #通过id获取订单信息
    GET_ORDER_BY_ID_NOORDER = 0
    GET_ORDER_BY_ID_OK = 1
    #通过用户名获取订单
    GET_ORDER_BY_USER_NAME_NOORDER = 0
    GET_ORDER_BY_USER_NAME_ONE = 1
    GET_ORDER_BY_USER_NAME_MORE = 2
