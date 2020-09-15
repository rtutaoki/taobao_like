import sqlite3
import json
from my_sqls import db


def add_commodity(user_name,commodity_name,info,commodity_price,stock,resource):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "INSERT INTO commodity (commodity_user_name,commodity_name,commodity_info,commodity_price,commodity_stock,commodity_resource)" \
          " VALUES (?,?,?,?,?,?)"
    para = (user_name,commodity_name,info,commodity_price,stock,resource)
    my_cursor.execute(sql,para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def del_commodity(id):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "DELETE FROM commodity WHERE commodity_id = ?"
    para = (id,)
    my_cursor.execute(sql,para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_commodity_name(id,commodity_name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE commodity SET commodity_name = ? WHERE commodity_id = ?"
    para = (commodity_name,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_commodity_info(id,commodity_info):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE commodity SET commodity_info = ? WHERE commodity_id = ?"
    para = (commodity_info,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()

def updata_commodity_price(id,commodity_price):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE commodity SET commodity_price = ? WHERE commodity_id = ?"
    para = (commodity_price,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_commodity_stock(id,commodity_stock):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE commodity SET commodity_stock = ? WHERE commodity_id = ?"
    para = (commodity_stock,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_commodity_resource(id,commodity_resource):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE commodity SET commodity_resource = ? WHERE commodity_id = ?"
    para = (commodity_resource,id)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def getcommodity_id(commodity_user_name,commodity_name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT commodity_id FROM commodity WHERE commodity_user_name = ? AND commodity_name = ?"
    para = (commodity_user_name,commodity_name)
    my_cursor.execute(sql, para)
    commodity_id = None
    for row in my_cursor:
        commodity_id = row[0]
    print(commodity_id)
    my_cursor.close()
    my_db.close()
    return commodity_id


def getcommodity(commodity_id):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM commodity WHERE commodity_id = ?"
    para = (commodity_id,)
    my_cursor.execute(sql,para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()

    return data


def get_allcommodity_byuser_name(commodity_user_name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM commodity WHERE commodity_user_name = ?"
    para = (commodity_user_name,)
    my_cursor.execute(sql,para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()
    return data


def get_allcommodity_byname_like(name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM commodity WHERE commodity_name LIKE ?"
    para = ("%"+name+"%",)
    my_cursor.execute(sql, para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()
    return data


if __name__=='__main__':
    add_commodity("a","挂面","无","9.9",10,"D")
    #del_commodity(1)
    #getallcommodity()
    #getcommodity_id("x","111")
    #get_allcommodity_byuser_name("xd")
    #get_allcommodity_byname_like("好")