import sqlite3
import os
database_dir = os.path.join(os.path.dirname(__file__),"taobao.db")
def creat_user_table():
    my_db = sqlite3.connect(database_dir)
    my_cursor = my_db.cursor()
    my_cursor.execute("CREATE TABLE user ("
                      "user_name TEXT PRIMARY KEY ,"
                      "user_password TEXT NOT NULL,"
                      "user_address TEXT)")
    my_cursor.close()
    my_db.close()


def creat_commodity_table():
    my_db = sqlite3.connect(database_dir)
    my_cursor = my_db.cursor()
    my_cursor.execute("CREATE TABLE commodity ("
                      "commodity_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                      "commodity_user_name TEXT NOT NULL,"
                      "commodity_name TEXT NOT NULL,"
                      "commodity_info TEXT NOT NULL,"
                      "commodity_price TEXT NOT NULL,"
                      "commodity_stock INTEGER NOT NULL,"
                      "commodity_resource TEXT)")
    my_cursor.close()
    my_db.close()


def creat_order_table():
    my_db = sqlite3.connect(database_dir)
    my_cursor = my_db.cursor()
    my_cursor.execute("CREATE TABLE orders ("
                      "order_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                      "order_commodity_id INTEGER NOT NULL,"
                      "order_user_name TEXT NOT NULL,"
                      "order_address TEXT,"
                      "order_num INTEGER NOT NULL,"
                      "order_status INTEGER NOT NULL)")
    my_cursor.close()
    my_db.close()

if __name__=='__main__':
    #creat_user_table()
    creat_commodity_table()
    #creat_order_table()