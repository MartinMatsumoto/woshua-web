/**
 * Created by Acer on 2016/9/21.
 */
define(function (require, exports, module) {

    exports.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    /**
     * 判断输入昵称是否合法
     * @param str
     * @param min
     * @param max
     * @returns {boolean}
     */
    exports.checkNickName = function (str, min, max) {
        var type = "^[a-zA-Z0-9_\u554A-\u9C52]{" + min + "," + max + "}$";
        var reg = new RegExp(type);
        if (str.match(reg) == null)
            return false;
        else
            return true;
    }

    /**
     * 判断输入是否大小写英文字符加数字，且符号长度限制
     * str 效验的字符内容
     * min 最小字符数
     * max 最大字符数
     * return boolean
     */
    exports.checkStr = function (str, min, max) {
        var type = "^[a-zA-Z0-9]{" + min + "," + max + "}$";
        var reg = new RegExp(type);
        if (str.match(reg) == null)
            return false;
        else
            return true;
    }

    /** 判断邮件地址是否正确*/
    exports.isEmail = function (mail) {
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return reg.test(mail);
    }

    /** 判断手机号码是否正确*/
    exports.isPhone = function (phone) {
        var reg = /^1[3|4|5|8][0-9]{9}$/;
        return reg.test(phone);
    }
});