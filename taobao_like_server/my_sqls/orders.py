import sqlite3
import json
from my_sqls import db


def add_order(commodity_id,user_name,address,order_nym):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "INSERT INTO orders (order_commodity_id,order_user_name,order_address,order_num,order_status)" \
          " VALUES (?,?,?,?,?)"
    para = (commodity_id,user_name,address,order_nym,0)
    my_cursor.execute(sql,para)
    my_db.commit()
    my_cursor.close()
    my_db.close()

def del_order(id):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "DELETE FROM orders WHERE order_id = ?"
    para = (id,)
    my_cursor.execute(sql,para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_order_address(id,order_address):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE orders SET order_address = ? WHERE order_id = ?"
    para = (order_address,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_order_num(id,order_num):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE orders SET order_num = ? WHERE order_id = ?"
    para = (order_num,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_order_status(id,order_status):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE orders SET order_status = ? WHERE order_id = ?"
    para = (order_status,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def getorder(order_id):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM orders WHERE order_id = ?"
    para = (order_id,)
    my_cursor.execute(sql,para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()
    return data


def getallorders_byuser_name(order_user_name):
    my_db = sqlite3.connect('taobao.db')
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM orders WHERE order_user_name = ?"
    para = (order_user_name,)
    my_cursor.execute(sql,para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()

    jsondata=[]
    for row in data:
        result = {}
        result['order_id'] = row[0]
        result['order_ commodity_id'] = row[1]
        result['order _user_name'] = row[2]
        result['order _address'] = row[3]
        result['order_stock'] = row[4]
        result['order _status'] = row[5]
        jsondata.append(result)
    print(json.dumps(jsondata))
    return json.dumps(jsondata)

if __name__=='__main__':
    #add_order(2,"xd","湖北省",2)
    #del_order(1)
    getallorders()