import sqlite3
import json
from my_sqls import db

def add_user(name, password, address):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "INSERT INTO user (user_name, user_password,user_address)" \
          " VALUES (?,?,?)"
    para = (name, password, address)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def del_user(name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "DELETE FROM user WHERE user_name = ?"
    para = (name,)
    my_cursor.execute(sql,para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def updata_user_password(name,password):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE user SET user_password = ? WHERE user_name = ?"
    para = (password,name)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()

def updata_user_address(name,address):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "UPDATE user SET user_address = ? WHERE user_name = ?"
    para = (address,name)
    my_cursor.execute(sql, para)
    my_db.commit()
    my_cursor.close()
    my_db.close()


def getuser(user_name):
    my_db = sqlite3.connect(db.database_dir)
    my_cursor = my_db.cursor()
    sql = "SELECT * FROM user WHERE user_name = ?"
    para = (user_name,)
    my_cursor.execute(sql,para)
    data = my_cursor.fetchall()
    my_cursor.close()
    my_db.close()
    return data



if __name__ == '__main__':
    add_user("xd", "123", "湖北省")
    #del_user("xd")
    #updata_user_password("xd","123")
    #updata_user_address("xd","武汉市")
    #getuser("xd")
