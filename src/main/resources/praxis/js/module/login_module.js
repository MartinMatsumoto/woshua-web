/**
 * Created by Acer on 2016/9/23.
 */
/*<![CDATA[*/
define(function (require, exports, module) {

    var $ = require("jquery");
    var cookie = require("cookie");
    var config = require("config");
    var csrfParameterName = $(config.csrfParameterName).val();
    var csrfToken = $(config.csrfToken).val();

    exports.login = function (account, password, callback) {
        var request = {
            account: account,
            password: password
        };
        request[csrfParameterName] = csrfToken;

        $.post(config.login, request, function (data) {
            callback(data);
        }, "json");
    }

    exports.authExist = function (account, email, callback) {
        var request = {
            account: account,
            email: email
        };

        request[csrfParameterName] = csrfToken;

        $.post(config.authExist, request, function (data) {
            callback(data);
        }, "json");
    }

    exports.register = function (account, password, email, nickName, sex, userType, callback) {
        var request = {
            account: account,
            password: password,
            email: email,
            nickname: nickName,
            sex: sex,
            usertype: userType
        };

        request[csrfParameterName] = csrfToken;

        $.post(config.register, request, function (data) {
            callback(data);
        }, "json");
    }

    exports.isLogin = function () {
        var account = $.cookie("woshua_account");
        //登录状态
        if (account) {
            return true;
        } else {
            return false;
        }
    }

});
/*]]>*/