from flask import Flask, url_for, request, render_template
from my_sqls import user, commodity, orders
import json
import my_static

app = Flask(__name__)


@app.route('/login', methods=['POST'])
def login():
    if request.method == 'POST':
        data = user.getuser(request.form['user_name'])
        if data:
            result = {}
            for row in data:
                result['user_name'] = row[0]
                result['user_password'] = row[1]
                result['user_address'] = row[2]
            if request.form['password'] ==  row[1]:
                # 传输一个json对象过去
                return json.dumps({"data": result, "ret_code": my_static.Constant.LOGIN_OK})
            else:
                return json.dumps({"data": None, "ret_code": my_static.Constant.LOGIN_PASS_ERROR})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.LOGIN_NO_USER})


@app.route('/register', methods=['POST'])
def register():
    if request.method == 'POST':
        data = user.getuser(request.form['user_name'])
        if data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.REGISTER_FAIL})
        else:
            user.add_user(request.form['user_name'], request.form['password'], request.form['user_address'])
            return json.dumps({"data": None, "ret_code": my_static.Constant.REGISTER_OK})


@app.route('/update_user', methods=['POST'])
def update_user():
    if request.method == 'POST':
        # 查询数据库中是否有这个用户名
        data = user.getuser(request.form['user_name'])
        # 获取传过来的数据
        user_password = request.form['user_password']
        user_address = request.form['user_address']
        # 根据data是否存在进行相应操作
        if data:
            if user_password is not None:
                user.updata_user_password(request.form['user_name'], user_password)
            if user_address is not None:
                user.updata_user_address(request.form['user_name'], user_address)
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_USER_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_USER_NOUSER})


@app.route('/del_user', methods=['POST'])
def del_user():
    if request.method == 'POST':
        data = user.getuser(request.form['user_name'])
        if data:
            user.del_user(request.form['user_name'])
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_USER_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_USER_NO_USER})


@app.route('/getuser', methods=['POST'])
def getuser():
    if request.method == 'POST':
        data = user.getuser(request.form['user_name'])
        if data:
            result = {}
            for row in data:
                result['user_name'] = row[0]
                result['user_password'] = row[1]
                result['user_address'] = row[2]
            # result也是个json对象
            return json.dumps({"data": result, "ret_code": my_static.Constant.GET_USER_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_USER_NOUSER})


@app.route('/add_commodity', methods=['POST'])
def add_commodity():
    if request.method == 'POST':
        commodity_user_name = request.form['commodity_user_name']
        commodity_name = request.form['commodity_name']
        commodity_info = request.form['commodity_info']
        commodity_price = request.form['commodity_price']
        commodity_stock = int(request.form['commodity_stock'])
        commodity_resource = request.form['commodity_resource']
        data = commodity.getcommodity_id(commodity_user_name, commodity_name)
        if not data:
            commodity.add_commodity(commodity_user_name, commodity_name, commodity_info, commodity_price,
                                    commodity_stock,commodity_resource)
            return json.dumps({"data": None, "ret_code": my_static.Constant.ADD_COMMODITY_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.ADD_COMMODITY_ISEXIST})


@app.route('/del_commodity', methods=['POST'])
def del_commodity():
    if request.method == 'POST':
        data = commodity.getcommodity(int(request.form['commodity_id']))
        if not data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_COMMODITY_NO_COM})
        else:
            commodity.del_commodity(id)
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_COMMODITY_OK})


# 除了id，用户名不能更改，其他均用这个函数修改
@app.route('/update_commodity', methods=['POST'])
def update_commodity():
    if request.method == 'POST':
        commodity_user_name = request.form['commodity_user_name']
        commodity_name = request.form['commodity_name']
        commodity_info = request.form['commodity_info']
        commodity_price = request.form['commodity_price']
        commodity_stock = request.form['commodity_stock']
        commodity_resource = request.form['commodity_resource']
        id = commodity.getcommodity_id(commodity_user_name, commodity_name)
        if not id:
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_COMMODITY_NO_COM})
        else:
            if commodity_name is not None:
                commodity.updata_commodity_name(id, commodity_name)
            if commodity_info is not None:
                commodity.updata_commodity_info(id, commodity_info)
            if commodity_price is not None:
                commodity.updata_commodity_price(id, commodity_price)
            if commodity_stock is not None:
                commodity.updata_commodity_stock(id, int(commodity_stock))
            if commodity_resource is not None:
                commodity.updata_commodity_resource(id, commodity_resource)
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_COMMODITY_OK})


@app.route('/get_commodity', methods=['POST'])
def getcommodity():
    if request.method == 'POST':
        data = commodity.getcommodity(int(request.form['commodity_id']))
        if not data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_COMMODITY_BY_ID_NO_COM})
        else:
            result = {}
            for row in data:
                result['commodity_id'] = row[0]
                result['commodity_user_name'] = row[1]
                result['commodity_name'] = row[2]
                result['commodity_info'] = row[3]
                result['commodity_price'] = row[4]
                result['commodity_stock'] = row[5]
                result['commodity_resource'] = row[6]
            return json.dumps({"data": result, "ret_code": my_static.Constant.GET_COMMODITY_BY_ID_OK})


@app.route('/get_allcommodity_byuser_name', methods=['POST'])
def get_allcommodity_byuser_name():
    if request.method == 'POST':
        data = commodity.get_allcommodity_byuser_name(request.form['commodity_user_name'])
        if not data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_COMMODITY_BY_USER_NOUSER})
        else:
            if (len(data) == 1):
                result = {}
                for row in data:
                    result['commodity_id'] = row[0]
                    result['commodity_user_name'] = row[1]
                    result['commodity_name'] = row[2]
                    result['commodity_info'] = row[3]
                    result['commodity_price'] = row[4]
                    result['commodity_stock'] = row[5]
                    result['commodity_resource'] = row[6]
                return json.dumps({"data": result, "ret_code": my_static.Constant.GET_COMMODITY_BY_USER_ONE})
            else:
                jsondata = []
                for row in data:
                    result = {}
                    result['commodity_id'] = row[0]
                    result['commodity_user_name'] = row[1]
                    result['commodity_name'] = row[2]
                    result['commodity_info'] = row[3]
                    result['commodity_price'] = row[4]
                    result['commodity_stock'] = row[5]
                    result['commodity_resource'] = row[6]
                    jsondata.append(result)
                print(json.dumps(
                    {"data": jsondata, "ret_code": my_static.Constant.GET_COMMODITY_BY_USER_MORE}))
                print(len(data))
                return json.dumps(
                    {"data": jsondata, "ret_code": my_static.Constant.GET_COMMODITY_BY_USER_MORE})


@app.route('/get_allcommodity_byname', methods=['POST'])
def get_allcommodity_byname():
    if request.method == 'POST':
        data = commodity.get_allcommodity_byname_like(request.form['name_like'])
        if not data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_COMMODITY_BY_NAME_NOUSER})
        else:
            if len(data) == 1:
                result = {}
                for row in data:
                    result['commodity_id'] = row[0]
                    result['commodity_user_name'] = row[1]
                    result['commodity_name'] = row[2]
                    result['commodity_info'] = row[3]
                    result['commodity_price'] = row[4]
                    result['commodity_stock'] = row[5]
                    result['commodity_resource'] = row[6]
                return json.dumps({"data": result, "ret_code": my_static.Constant.GET_COMMODITY_BY_NAME_ONE})
            else:
                jsondata = []
                for row in data:
                    result = {}
                    result['commodity_id'] = row[0]
                    result['commodity_user_name'] = row[1]
                    result['commodity_name'] = row[2]
                    result['commodity_info'] = row[3]
                    result['commodity_price'] = row[4]
                    result['commodity_stock'] = row[5]
                    result['commodity_resource'] = row[6]
                    jsondata.append(result)
                return json.dumps(
                    {"data": jsondata, "ret_code": my_static.Constant.GET_COMMODITY_BY_NAME_MORE})


@app.route('/add_order', methods=['POST'])
def add_order():
    if request.method == 'POST':
        order_commodity_id = int(request.form['order_commodity_id'])
        order_user_name = request.form['order_user_name']
        order_address = request.form['order_address']
        order_num = request.form['order_num']
        orders.add_order(order_commodity_id, order_user_name, order_address, order_num)
        return json.dumps({"data": None, "ret_code": my_static.Constant.ADD_ORDER_OK})


@app.route('/del_order', methods=['POST'])
def del_order():
    if request.method == 'POST':
        order_id = int(request.form['order_id'])
        data = orders.getorder(order_id)
        if data:
            orders.del_order(order_id)
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_ORDER_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.DEL_ORDER_NOORDER})


@app.route('/update_order', methods=['POST'])
def update_order():
    if request.method == 'POST':
        order_id = int(request.form['order_id'])
        order_address = request.form['order_address']
        order_num = request.form['order_num']
        order_status = request.form['order_status']
        data = orders.getorder(order_id)
        if data:
            if order_address is not None:
                orders.updata_order_address(order_id, order_address)
            if order_num is not None:
                orders.updata_order_num(order_id, int(order_num))
            if order_status is not None:
                orders.updata_order_status(order_id, int(order_status))
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_ORDER_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.UPDATE_ORDER_NOORDER})


@app.route('/getorder_by_id', methods=['POST'])
def getorder_by_id():
    if request.method == 'POST':
        order_commodity_id = int(request.form['order_commodity_id'])
        data = orders.getorder(order_commodity_id)
        if data:
            result = {}
            for row in data:
                result['order_id'] = row[0]
                result['order_commodity_id'] = row[1]
                result['order_user_name'] = row[2]
                result['order_address'] = row[3]
                result['order_stock'] = row[4]
                result['order_status'] = row[5]
            return json.dumps({"data": result, "ret_code": my_static.Constant.GET_ORDER_BY_ID_OK})
        else:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_ORDER_BY_ID_NOORDER})


@app.route('/getorder_by_user_name', methods=['POST'])
def getorder_by_user_name():
    if request.method == 'POST':
        order_user_name = request.form['order_user_name']
        data = orders.getallorders_byuser_name(order_user_name)
        if not data:
            return json.dumps({"data": None, "ret_code": my_static.Constant.GET_ORDER_BY_USER_NAME_NOORDER})
        else:
            if len(data) == 1:
                result = {}
                for row in data:
                    result['order_id'] = row[0]
                    result['order_commodity_id'] = row[1]
                    result['order_user_name'] = row[2]
                    result['order_address'] = row[3]
                    result['order_stock'] = row[4]
                    result['order_status'] = row[5]
                return json.dumps({"data": result, "ret_code": my_static.Constant.GET_ORDER_BY_USER_NAME_ONE})
            else:
                jsondata = []
                for row in data:
                    result = {}
                    result['order_id'] = row[0]
                    result['order_commodity_id'] = row[1]
                    result['order_user_name'] = row[2]
                    result['order_address'] = row[3]
                    result['order_stock'] = row[4]
                    result['order_status'] = row[5]
                    jsondata.append(result)
                return json.dumps(
                    {"data": jsondata, "ret_code": my_static.Constant.GET_ORDER_BY_USER_NAME_MORE})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)
