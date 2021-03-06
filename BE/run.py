# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-23 22:46:40
LastEditTime : 2022-03-20 22:43:06
Description  : 
'''
# import sys
# import os
# sys.path.append(os.path.dirname(os.path.dirname(__file__)))
# print(sys.path)


from flask import Flask
from gevent import pywsgi
from app import create_app

app = create_app('production')

if __name__ == '__main__':
    server = pywsgi.WSGIServer(('0.0.0.0', 8110), app)
    server.serve_forever()
