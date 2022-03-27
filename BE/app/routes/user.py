# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-26 19:39:22
LastEditTime : 2022-03-21 00:14:10
Description  : 
'''
from app import db, logger
from app.models.user import User
from common.basersp import BaseRsp
from common.const import ReturnCode
from flask import Blueprint, request

users = Blueprint('user', __name__)

@users.route('/user/create_user', methods=["get", "post"], endpoint='create_user')
def create_user():
    logger.info('Get create user request: %s' % str(request.json))
    if request.method == 'POST':
        try:
            username = request.json.get('username', '')
            gender = request.json.get('gender', 2)
            avator = request.json.get('avator', '')
            passwd = request.json.get('passwd', '')
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if username != '' and passwd != '': 
            try:
                if username_exist(username):
                    err_msg = 'Username exists'
                    code = ReturnCode.UserNameExist.value
                    logger.info('Create user failed, username exists, username: %s', username)
                    return BaseRsp.err_rsp(code, err_msg)
                new_user = User(username=username, gender=gender, avator=avator, passwd=passwd)
                db.session.add(new_user)
                db.session.flush()
                uid = new_user.uid
                logger.info("Create user succeed, request data: %s, uid: %s", str(request.json), str(uid))
                return BaseRsp.success_rsp({"uid": uid})
            except Exception as e:
                logger.error('Create user wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Create user wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            finally:
                code = ReturnCode.CreateUserError.value
        else:
            err_msg = 'username or passwd is empty!'
            code = ReturnCode.EmptyNameOrPwd.value
            logger.info(err_msg + 'data: %s', str(request.json))
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
    return BaseRsp.err_rsp(code, request.method)

@users.route('/user/update_username', methods=["get", "post"], endpoint='update_username')
def update_username():
    logger.info('Get update username request: %s' % str(request.json))
    if request.method == 'POST':
        try:
            old_username = request.json.get('old_username', '')
            new_username = request.json.get('new_username', '')
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if old_username != '' and new_username != '':
            try:
                user = db.session.query(User).filter(User.username==old_username).first()
                if user is None:
                    err_msg = 'Old username not exists'
                    code = ReturnCode.UserNameNotExist.value
                    logger.info('Update username failed, old username not exists, username: %s', old_username)
                    return BaseRsp.err_rsp(code, err_msg)
                if username_exist(new_username):
                    err_msg = 'New username exists'
                    code = ReturnCode.UserNameExist.value
                    logger.info('Update username failed, new username exists, username: %s', new_username)
                    return BaseRsp.err_rsp(code, err_msg)
                user.username = new_username
                logger.info("Update username succeed, request data: %s", str(request.json))
                return BaseRsp.success_rsp({})
            except Exception as e:
                logger.error('Update username wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Update username wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            finally:
                code = ReturnCode.UpdateUsernameError.value
        else:
            err_msg = 'old username or new username is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyOldnameOrNewname.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/update_gender', methods=["get", "post"], endpoint='update_gender')
def update_gender():
    logger.info('Get update gender request: %s' % str(request.json))
    if request.method == 'POST':
        try:
            username = request.json.get('username', '')
            new_gender = request.json.get('new_gender', 3)
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if username != '' and new_gender != 3:
            try:                    
                user = db.session.query(User).filter(User.username==username).first()
                if user is None:
                    err_msg = "Username doesn't exists"
                    code = ReturnCode.UserNameExist.value
                    logger.info('Update gender failed, username not exists, username: %s', username)
                    return BaseRsp.err_rsp(code, err_msg)
                user.gender = new_gender
                logger.info("Update gender succeed, request data: %s", str(request.json))
                return BaseRsp.success_rsp({})
            except Exception as e:
                logger.error('Update gender wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Update gender wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            finally:
                code = ReturnCode.UpdateGenderError.value
        else:
            err_msg = 'New gender or username is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyNewGenderOrUsername.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/update_avator', methods=["get", "post"], endpoint='update_avator')
def update_avator():
    logger.info('Get update username request: %s' % str(request.json))
    if request.method == 'POST':
        pass
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/update_passwd', methods=["get", "post"], endpoint='update_passwd')
def update_passwd():
    logger.info('Get update passwd request: %s' % str(request.json))
    if request.method == 'POST':
        try:
            username = request.json.get('username', '')
            old_passwd = request.json.get('old_passwd', '')
            new_passwd = request.json.get('new_passwd', '')
        except:
            err_msg = "Get request data error"
            logger.error(err_msg)
            code = ReturnCode.GetDataError.value
            return BaseRsp.err_rsp(code, err_msg)
        if username != '' and old_passwd != '' and new_passwd != '':
            try:
                user = db.session.query(User).filter(User.username==username).first()
                if user is None:
                    err_msg = "Username doesn't exists"
                    code = ReturnCode.UserNameExist.value
                    logger.info('Update password failed, username not exists, username: %s', username)
                    return BaseRsp.err_rsp(code, err_msg)
                if user.passwd == old_passwd:
                    user.passwd = new_passwd
                else:
                    err_msg = 'Old password incorrect'
                    code = ReturnCode.UserPwdWrong.value
                    logger.info("Update password failed, old_password: %s, username: %s", str(old_passwd), str(username))
                    return BaseRsp.err_rsp(code, err_msg)
                logger.info("Update passwd succeed, request data: %s", str(request.json))
                return BaseRsp.success_rsp({})
            except Exception as e:
                logger.error('Update gender wrong! error: %s' % str(e))
                err_msg = str(e)
            except:
                logger.error('Update gender wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            finally:
                code = ReturnCode.UpdatePasswdError.value
        else:
            err_msg = 'Passwd or username is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyData.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

@users.route('/user/login', methods=["get", "post"], endpoint='login')
def user_login():
    logger.info('Get login request: %s' % str(request.json))
    if request.method == 'POST':
        username = request.json.get('username', '')
        passwd = request.json.get('passwd', '')
        if username != '' and passwd != '':
            try:
                user = db.session.query(User).filter(User.username==username).first()
                if user.passwd == passwd:
                    return BaseRsp.success_rsp({})
                code = ReturnCode.PwdIncorrect.value
                err_msg = 'User password incorrect'
                return BaseRsp.err_rsp(code, err_msg) 
            except Exception as e:
                err_msg = str(e)
                logger.error('Login wrong! error: %s' % str(e))
            except:
                logger.error('Login wrong with unexcepted wrong!')
                err_msg = 'unexcepted error'
            code = ReturnCode.LoginError.value
            return BaseRsp.err_rsp(code, err_msg)
        else:
            err_msg = 'Passwd or username is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyUsernameOrPwd.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)


@users.route('/user/info', methods=["get", "post"], endpoint='info')
def user_login():
    logger.info('Get user info request: %s' % str(request.json))
    if request.method == 'POST':
        uid = request.json.get('uid', -1)
        if uid != '':
            try:
                user = db.session.query(User).filter(User.uid==uid).first()
                result = {}
                result['username'] = user.username
                result['gender'] = user.gender
                result['avator'] = user.avator
                return BaseRsp.success_rsp(result)
            except Exception as e:
                err_msg = str(e)
                logger.error('Get user info wrong! error: %s' % str(e))
            except:
                logger.error('Get user info wrong with unexcepted error!')
                err_msg = 'unexcepted error'
            code = ReturnCode.LoginError.value
            return BaseRsp.err_rsp(code, err_msg)
        else:
            err_msg = 'Uid is empty!'
            logger.info(err_msg + 'data: %s', str(request.json))
            code = ReturnCode.EmptyUsernameOrPwd.value
        return BaseRsp.err_rsp(code, err_msg)
    else:
        code = ReturnCode.GETMethodError.value
        return BaseRsp.err_rsp(code, request.method)

def username_exist(username):
    return False if db.session.query(User).filter(User.username==username).first() is None else True
