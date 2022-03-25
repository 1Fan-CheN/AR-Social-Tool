# -*- coding: utf-8 -*-
'''
Author       : Yifan Chen
Date         : 2022-01-27 03:01:39
LastEditTime : 2022-01-27 03:06:00
Description  : 
'''
import enum


class ChoiceBase(object):
    __choices__ = ()

    def get_choices(self):
        return self.__choices__

    @classmethod
    def get_display_name(cls, value):
        _names = dict(cls.__choices__)
        return _names.get(value) or ""

    @classmethod
    def get_enum(cls, name):
        _dict = dict(cls.__choices__)
        for key, value in _dict.items():
            if value == name:
                return key

    @classmethod
    def all_elements(cls):
        _dict = dict(cls.__choices__)
        return _dict.keys()


class ReturnCode(ChoiceBase, enum.Enum):
    Success = 0
    Error = -1
    GETMethodError = 1001
    POSTMethodError = 1002

    __choices__ = (
        (Success, 'success'),
        (Error, 'fail'),
        (GETMethodError, 'not support for GET method'),
        (POSTMethodError, 'not support for POST method'),
    )
