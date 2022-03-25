# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-23 22:37:01
LastEditTime : 2022-03-20 22:22:09
Description  : 
'''

def init_app(app):
    from .greeting import greetings
    from .user import users
    app.register_blueprint(users)
    app.register_blueprint(greetings)
    