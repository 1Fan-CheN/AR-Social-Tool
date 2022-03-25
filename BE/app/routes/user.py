# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:39:22
LastEditTime : 2022-03-21 00:14:10
Description  : 
'''
import hashlib

from flask import Blueprint, request
from app.models.user import User
from app import logger
from app import db
from common.basersp import BaseRsp
from common.const import ReturnCode

users = Blueprint('user', __name__)

@users.route('/user/create', endpoint='create_user')
def create_user():
    logger.info('Get create user request: %s' % str(request.json))
    if request.method == 'POST':
        username = request.json.get('username', '')
        gender = request.json.get('gender', 0)
        avator = request.json.get('avator', '')
        try:
            new_user = User(username=username, gender=gender, avator=avator)
            db.session.add(new_user)
            db.session.commit()
            return BaseRsp.success_rsp(request.json)
            
        except Exception as e:
            logger.error('Create user wrong! error: %s' % str(e))
        except:
            logger.error('Create user wrong with unexcepted wrong!')
            e = 'unexcepted error'
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, e)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/update_nickname', endpoint='update_nickname')
def update_nickname():
    pass

@users.route('/user/update_gender', endpoint='update_gender')
def update_gender():
    pass

@users.route('/user/update_avator', endpoint='update_avator')
def update_avator():
    pass

@users.route('/user/login', endpoint='login')
def user_login():
    logger.info('Get login request: %s' % str(request.json))
    if request.method == 'POST':
        username = request.json.get('username', '')
        if username != '':
            try:
                new_user = User(username=username, gender=gender, avator=avator)
                db.session.add(new_user)
                db.session.commit()
                return BaseRsp.success_rsp(request.json)
                
            except Exception as e:
                logger.error('Create user wrong! error: %s' % str(e))
            except:
                logger.error('Create user wrong with unexcepted wrong!')
                e = 'unexcepted error'
            code = ReturnCode.GETMethodError.value
            return BaseRsp.err_rsp(code, e)
        else:
            return
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/logout', endpoint='logout')
def user_logout():
    return 'logging out'