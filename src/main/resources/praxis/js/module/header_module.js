/**
 * Created by Acer on 2016/9/19.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var cookie = require("cookie");
    var config = require("config");
    var loginDialogModule = require("login_dialog_module");
    var loginModule = require("login_module");
    var headerModule = require("header_module");
    var toast = require("toast");

    var mainBtn = $("#main_btn");
    var tikuBtn = $("#tiku_btn");
    var spaceBtn = $("#space_btn");
    var combinePaperBtn = $("#combine_paper_btn");
    var downloadBtn = $("#download_btn");
    var aboutBtn = $("#about_btn");
    var compressBtn = $("#compress_btn");
    var brush = $("#brush");
    var engText = $("#eng_text");
    var chnText = $("#chn_text");
    var loginBtn = $("#login_btn");
    var registerBtn = $("#register_btn");
    var loginButtonContainer = $("#login_button_container");
    var loginUserContainer = $("#login_user_container");
    var loginUserHeader = $("#login_user_header");
    var loginUserName = $("#login_user_name");
    var quitBtn = $("#quit_btn");
    var favoriteCount = $("#favorite_count");

    var maxScrollTop = 400;
    var minWidth = 88;
    var maxWidth = 166;
    var brushWidth = 70;
    var headerFavorBox = $("#header_favor_box");


    exports.initHead = function () {
        var part = this;
        brush.click(function () {
            window.location.href = config.mainHref;
        });

        mainBtn.click(function () {
            window.location.href = config.mainHref;
        });

        tikuBtn.click(function () {
            window.location.href = config.tikuHref;
        });

        loginBtn.click(function () {
            loginDialogModule.showLogin();
        });

        registerBtn.click(function () {
            loginDialogModule.showRegister();
        });

        combinePaperBtn.click(function () {
            if (!loginModule.isLogin()) {
                headerModule.showLoginDialog();
                return;
            }
            //$().toastmessage('showNoticeToast', "敬请期待");
            window.location.href = config.zujuanHref;
        });

        downloadBtn.click(function () {
            $().toastmessage('showNoticeToast', "敬请期待");
        });

        quitBtn.click(function () {
            part.quitLogin(part);
            window.location.reload();
        });

        spaceBtn.click(function () {
            if (!loginModule.isLogin()) {
                headerModule.showLoginDialog();
                return;
            }
            window.location.href = config.spaceHref;
        });

        initStyle();
        initLogin(part);
    }

    exports.quitLogin = function (part) {
        if (!part) {
            part = this;
        }
        $.cookie("woshua_key", "", {path: '/'});
        $.cookie("woshua_id", "", {path: '/'});
        $.cookie("woshua_account", "", {path: '/'});
        $.cookie("woshua_nickname", "", {path: '/'});
        $.cookie("woshua_sex", "", {path: '/'});
        $.cookie("woshua_user_type", "", {path: '/'});
        $.cookie("woshua_favorite_num", "", {path: '/'});
        $.cookie("woshua_icon_path", "", {path: '/'});
        part.showLogin();
    }

    exports.showLoginDialog = function () {
        loginDialogModule.showLogin();
    }

    var initLogin = function (part) {
        var account = $.cookie("woshua_account");
        //登录状态
        if (account) {
            part.showUser();
        } else {
            part.showLogin();
        }
    }

    var initStyle = function () {
        var href = window.location.href;
        if (href.indexOf(config.tikuHref) > 0) {
            tikuBtn.addClass("now");
            tikuBtn.unbind("click");
        } else if (href.indexOf(config.spaceHref) > 0) {
            spaceBtn.addClass("now");
            spaceBtn.unbind("click");
        } else if (href.indexOf(config.zujuanHref) > 0) {
            combinePaperBtn.addClass("now");
            combinePaperBtn.unbind("click");
        } else if (href.indexOf(config.mianzeHref) > 0 || href.indexOf(config.fuwuHref) > 0 || href.indexOf(config.contactHref) > 0) {

        } else {
            mainBtn.addClass("now");
            mainBtn.unbind("click");
        }
    }

    exports.initScroll = function () {
        $(window).scroll(function () {
            var scrollTop = $(this).scrollTop();
            var percent = scrollTop / maxScrollTop;
            if (percent > 1) {
                percent = 1;
            }
            var radius = percent * 50;
            if (radius > 50) {
                radius = 50;
            }

            var width = maxWidth - (maxWidth - minWidth) * percent;

            if (width > maxWidth) {
                width = maxWidth;
            } else if (width < minWidth) {
                width = minWidth;
            }
            headerFavorBox.css("border-radius", radius + "%");
            headerFavorBox.css("width", width);
            headerFavorBox.css("height", width);

            var marginLeft = (width - brushWidth) / 2;
            brush.css("margin-left", marginLeft);

            var opacity = 1 - percent;
            engText.css("opacity", opacity);
            chnText.css("opacity", opacity);
        });
    }

    exports.showUser = function () {
        var nickname = $.cookie("woshua_nickname");
        var favoriteNum = $.cookie("woshua_favorite_num");
        var headerPath = $.cookie("woshua_icon_path");
        loginButtonContainer.hide();
        loginUserContainer.show();
        loginUserName.html(nickname);
        favoriteCount.html(favoriteNum);
        loginUserHeader.attr("src", headerPath);
    }

    exports.showLogin = function () {
        loginButtonContainer.show();
        loginUserContainer.hide();
    }

});