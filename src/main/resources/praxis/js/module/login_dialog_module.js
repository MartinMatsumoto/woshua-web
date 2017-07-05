/**
 * Created by Acer on 2016/9/23.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var config = require("config");
    var loginModule = require("login_module");
    var toast = require("toast");
    var cookie = require("cookie");
    var utils = require("utils");
    var headerModule = require("header_module");

    var close = $("#login_dialog_close");
    var loginDialog = $("#login_dialog");
    var registerContainer = $("#register_container");
    var loginContainer = $("#login_container");
    var gotoRegister = $("#goto_register");
    var gotoLogin = $("#goto_login");
    var loginTitle = $("#login_title");
    var registerNext = $("#register_next");
    var nextContainer = $("#next_container");
    var carrerName = $("#career_name");

    var loginBtn = $("#login_button");
    var accountInput = $("#account_input");
    var passwordInput = $("#password_input");

    var registAccountInput = $("#register_account");
    var registEmailInput = $("#register_email");
    var registPasswordInput = $("#register_password");
    var registPasswordNewInput = $("#register_passwordnew");
    var readAgreement = $("#read_agreement");

    var otherUserTypeBtn = $("#other_user_type");
    var studentUserTypeBtn = $("#student_user_type");
    var teacherUserTypeBtn = $("#teacher_user_type");

    var nickNameInput = $("#nickname_input");
    var registConfirm = $("#register_confirm");

    //其他
    var otherUserType = 2;
    //教师
    var teacherUserType = 0;
    //学生
    var studentUserType = 1;
    var userType = otherUserType;

    var account;
    var password;
    var email;

    var keepLogin = $("#keep_login");
    //保持30天登录
    var keepdays = 30;

    exports.initClick = function () {
        var part = this;
        close.click(function () {
            part.closeDialog();
        });

        gotoRegister.click(function () {
            part.showRegister();
        });

        gotoLogin.click(function () {
            part.showLogin();
        });

        loginBtn.click(function () {
            part.doLogin();
        });

        registerNext.click(function () {
            gotoNext(part);
        });

        otherUserTypeBtn.click(function () {
            chooseUserType($(this), otherUserType);
            carrerName.html("其他");
        });

        studentUserTypeBtn.click(function () {
            chooseUserType($(this), studentUserType);
            carrerName.html("学生");
        });

        teacherUserTypeBtn.click(function () {
            chooseUserType($(this), teacherUserType);
            carrerName.html("教师");
        });

        registConfirm.click(function () {
            doRegist(part);
        });
    }

    var chooseUserType = function (btn, type) {
        otherUserTypeBtn.next().addClass("display_none");
        studentUserTypeBtn.next().addClass("display_none");
        teacherUserTypeBtn.next().addClass("display_none");
        btn.next().removeClass("display_none");
        userType = type;
    }

    exports.showLogin = function () {
        loginDialog.show();
        registerContainer.hide();
        loginContainer.show();
        gotoRegister.show();
        gotoLogin.hide();
        nextContainer.hide();
        loginTitle.html("登录");
        registerContainer.css("height", 285);
    }

    exports.showNext = function () {
        nextContainer.show();
        registerContainer.hide();
        loginContainer.hide();
        loginTitle.html("完善资料");
        nextContainer.css("height", 325);
    }

    exports.showRegister = function () {
        loginDialog.show();
        registerContainer.show();
        loginContainer.hide();
        gotoRegister.hide();
        gotoLogin.show();
        loginTitle.html("注册");
        registerContainer.css("height", 325);
    }

    exports.closeDialog = function () {
        registAccountInput.val("");
        registEmailInput.val("");
        registPasswordInput.val("");
        registPasswordNewInput.val("");
        readAgreement.get(0).checked = false;

        nextContainer.hide();
        loginDialog.hide();
    }

    exports.doLogin = function () {
        var part = this;
        var account = accountInput.val();
        var password = passwordInput.val();
        var iskeep = keepLogin.get(0).checked;

        if (authAccount(account, password)) {
            loginModule.login(account, password, function (data) {
                if (data && data.header && data.header.flag == 1) {
                    putCookie(iskeep, data);
                    headerModule.showUser();
                    part.closeDialog();
                    window.location.reload();
                } else {
                    changeErrorType(data.header.errorDesc);
                }
            });
        }

    }

    var gotoNext = function (part) {
        account = registAccountInput.val();
        password = registPasswordInput.val();
        var passwordNew = registPasswordNewInput.val();
        email = registEmailInput.val();
        var isRead = readAgreement.get(0).checked;

        if (authRegister(account, email, password, passwordNew, isRead)) {
            loginModule.authExist(account, email, function (data) {
                if (data && data.header && data.header.flag == 1) {
                    part.showNext();
                } else {
                    changeErrorType(data.header.errorDesc);
                }
            });
        }
    }

    var doRegist = function (part) {
        var nickName = nickNameInput.val();
        var sex = $("input[name='sex']:checked").val();
        if (authNext(nickName, sex)) {
            loginModule.register(account, password, email, nickName, sex, userType, function (data) {
                if (data && data.header && data.header.flag == 1) {
                    putCookie(false, data);
                    headerModule.showUser();
                    part.closeDialog();
                    window.location.reload();
                } else {
                    changeErrorType(data.errorDesc);
                }
            });
        }
    }

    var putCookie = function (iskeep, data) {
        if (iskeep) {
            $.cookie('woshua_key', data.content.password, {expires: keepdays, path: '/'});
            $.cookie('woshua_id', data.content.id, {expires: keepdays, path: '/'});
            $.cookie('woshua_account', data.content.account, {expires: keepdays, path: '/'});
            $.cookie('woshua_nickname', data.content.nickname, {expires: keepdays, path: '/'});
            $.cookie("woshua_sex", data.content.sex, {expires: keepdays, path: '/'});
            $.cookie('woshua_user_type', data.content.user_type, {expires: keepdays, path: '/'});
            $.cookie('woshua_favorite_num', data.content.favoriteNum, {expires: keepdays, path: '/'});
            $.cookie('woshua_icon_path', data.content.iconPath, {expires: keepdays, path: '/'});
        } else {
            $.cookie('woshua_key', data.content.password, {path: '/'});
            $.cookie('woshua_id', data.content.id, {path: '/'});
            $.cookie('woshua_account', data.content.account, {path: '/'});
            $.cookie('woshua_nickname', data.content.nickname, {path: '/'});
            $.cookie("woshua_sex", data.content.sex, {path: '/'});
            $.cookie('woshua_user_type', data.content.user_type, {path: '/'});
            $.cookie('woshua_favorite_num', data.content.favoriteNum, {path: '/'});
            $.cookie('woshua_icon_path', data.content.iconPath, {path: '/'});
        }
    }
    
    var authNext = function (nickName, sex) {
        if (!$.trim(nickName)) {
            changeErrorType("昵称不能为空");
            return false;
        }

        console.log(nickName);
        if (!utils.checkNickName(nickName, 1, 8)) {
            changeErrorType("昵称长度必须为1-8个字符，大小写中英文字符或数字");
            return false;
        }

        if (!$.trim(sex)) {
            changeErrorType("请选择性别");
            return false;
        }

        if (!$.trim(userType)) {
            changeErrorType("请选择职业类型");
            return false;
        }

        return true;
    }

    var authAccount = function (account, password) {
        /** 判断账号输入是否有值 */
        if (!$.trim(account)) {
            changeErrorType("账号不能为空");
            return false;
        }

        /** 判断账号输入是否合法*/
        if (!utils.checkStr(account, 4, 20)) {
            changeErrorType("账号长度必须为4-20个字符，大小写英文字符或数字");
            return false;
        }

        /** 判断密码输入是否有值 */
        if (!$.trim(password)) {
            changeErrorType("密码不能为空");
            return false;
        }

        /** 判断密码输入是否合法*/
        if (!utils.checkStr(password, 6, 12)) {
            changeErrorType("密码长度必须为6-12个字符，大小写英文字符或数字");
            return false;
        }
        return true;
    }

    var authRegister = function (account, email, password, passwordNew, isRead) {
        if (!isRead) {
            changeErrorType("请先同意注册条款");
            return false;
        }

        /** 判断账号输入是否有值 */
        if (!$.trim(account)) {
            changeErrorType("账号不能为空");
            return false;
        }

        /** 判断账号输入是否合法*/
        if (!utils.checkStr(account, 4, 20)) {
            changeErrorType("账号长度必须为4-20个字符，大小写英文字符或数字");
            return false;
        }

        /** 判断账号输入是否有值 */
        if (!$.trim(email)) {
            changeErrorType("邮箱不能为空");
            return false;
        }

        /** 判断账号输入是否合法*/
        if (!utils.isEmail(email)) {
            changeErrorType("邮箱格式不正确");
            return false;
        }

        /** 判断密码输入是否有值 */
        if (!$.trim(password) || !$.trim(passwordNew)) {
            changeErrorType("密码不能为空");
            return false;
        }

        /** 判断密码输入是否合法*/
        if (!utils.checkStr(password, 6, 12) || !utils.checkStr(passwordNew, 6, 12)) {
            changeErrorType("密码长度为必须6-12个字符，大小写英文字符或数字");
            return false;
        }

        /** 判断密码输入是否合法*/
        if (password !== passwordNew) {
            changeErrorType("两次密码输入不一致");
            return false;
        }
        return true;
    }

    var changeErrorType = function (msg) {
        $().toastmessage('showErrorToast', msg);
    }

});