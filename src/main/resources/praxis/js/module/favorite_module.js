/**
 * Created by Acer on 2016/9/29.
 */
/**
 * Created by Acer on 2016/9/19.
 */
define(function (require, exports, module) {

    var $ = require("jquery");

    var favoriteDiv = $("#favorite_ainimation");
    var jqueryTween = require("jqueryTween");
    var TWEEN = require("tween");
    var destinationImg = $(".favorite_img");
    var config = require("config");
    var headerModule = require("header_module");
    var toast = require("toast");
    var favoriteCount = $("#favorite_count");
    var spaceFavoriteCount = $("#space_favorite_count");

    var csrfParameterName = $(config.csrfParameterName).val();
    var csrfToken = $(config.csrfToken).val();

    exports.addFavorite = function (offset, praxisId, div) {
        requestFavorite(praxisId, offset, div);
    }

    exports.cancelFavorite = function (praxisId, div) {
        requestCancelFavorite(praxisId, div);
    }

    var requestFavorite = function (praxisId, offset, div) {
        div.find("#add_favorite").hide();
        div.find("#add_favorite_loading").show();
        var request = {
            praxisId: praxisId
        }
        request[csrfParameterName] = csrfToken;

        $.post(config.addFavorite, request, function (data) {
            div.find("#add_favorite_loading").hide();
            if (data.header.flag == 1) {
                showAnimate(offset);
                changeButton(div, true);
                var currCount = favoriteCount.html();
                currCount++;
                favoriteCount.html(currCount);
                if(spaceFavoriteCount){
                    spaceFavoriteCount.html(currCount);
                }
                $.cookie('woshua_favorite_num', currCount, {path: '/'});
            } else {
                $().toastmessage('showErrorToast', data.header.errorDesc);
            }
        }, "json");
    }

    var requestCancelFavorite = function (praxisId, div) {
        div.find("#cancel_favorite").hide();
        div.find("#cancel_favorite_loading").show();
        var request = {
            praxisId: praxisId
        }
        request[csrfParameterName] = csrfToken;

        $.post(config.cancelFavorite, request, function (data) {
            div.find("#cancel_favorite_loading").hide();
            if (data.header.flag == 1) {
                changeButton(div, false);
                var currCount = favoriteCount.html();
                currCount--;
                favoriteCount.html(currCount);
                if(spaceFavoriteCount){
                    spaceFavoriteCount.html(currCount);
                }
                $.cookie('woshua_favorite_num', currCount, {path: '/'});
            } else {
                $().toastmessage('showErrorToast', data.header.errorDesc);
            }
        }, "json");
    }

    var showAnimate = function (offset) {
        var currTop = offset.top;
        var currLeft = offset.left;
        var windowScrollTop = $(window).scrollTop();
        currTop -= windowScrollTop;

        var toLeft = destinationImg.offset().left;
        var toTop = destinationImg.offset().top - windowScrollTop;

        favoriteDiv.css("display", "block");
        favoriteDiv.jQueryTween({

                //options
                from: {position: {top: currTop, left: currLeft}, width: 70, height: 70, rotate: {z: 0}, opacity: 100},
                to: {position: {top: toTop, left: toLeft}, width: 14, height: 14, rotate: {z: 180}, opacity: 0},
                duration: 500,
                delay: 0,
                yoyo: false,
                easing: TWEEN.Easing.Quadratic.Out
                //easing:tween.Tween.Quad.easeIn
            },

            // callback when tween is finished
            function () {
                //some cool function
                favoriteDiv.css("display", "none");
            },

            // special callback while tweening
            function () {
                //another cool function
            }
        );
    }

    var changeButton = function (div, favorite) {
        if (favorite) {
            div.find("#add_favorite").css("display", "none");
            div.find("#cancel_favorite").css("display", "block");
        } else {
            div.find("#add_favorite").css("display", "block");
            div.find("#cancel_favorite").css("display", "none");
        }

    }
});