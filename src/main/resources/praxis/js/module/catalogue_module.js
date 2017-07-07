/**
 * Created by Acer on 2017/7/4.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var jqueryTween = require("jqueryTween");
    var TWEEN = require("tween");

    var closeBtn = $("#catalogue_close");
    var openBtn = $("#catalogue_open");
    var allContainer = $("#catalogue_choose_container");
    var catalogueContent = $("#catalogue_content");

    var btnWidth = closeBtn.width();
    var containerWidth = allContainer.width();
    var totalWidth = btnWidth + containerWidth;

    var prev;

    exports.init = function () {
        initData();
        initBtn();
        hideOpen();
    }
    
    function initData() {
        var lis = catalogueContent.find("li");
        for (var i = 0; i < lis.length; i++) {
            bindCatalogue($(lis[i]));
        }
    }
    
    function bindCatalogue(li) {
        var children = li.children();
        var div = $(children[0]);
        var ul = $(children[1]);
        var i = div.find("i");

        div.bind("click",function () {
            if(prev){
                prev.removeClass("choose");
            }
            $(this).addClass("choose");
            prev = $(this);
        })

        i.bind("click",function () {
            var isOpen = $(this).attr("open");
            if (isOpen) {
                ul.hide();
                $(this).attr("open",false);
                $(this).removeClass("catalogue_down");
                $(this).addClass("catalogue_up");
            } else {
                ul.show();
                $(this).attr("open",true);
                $(this).removeClass("catalogue_up");
                $(this).addClass("catalogue_down");
            }
        });
    }

    function initBtn(){
        closeBtn.bind("click", function () {
            closeCatalogue();
        });

        openBtn.bind("click", function () {
            hideOpen();
        });
    }

    function closeCatalogue() {
        allContainer.jQueryTween({
                //options
                from: {position: {right: 0}},
                to: {position: {right: -totalWidth}},
                duration: 500,
                delay: 0,
                yoyo: false,
                chain:3,
                easing: TWEEN.Easing.Quadratic.Out
            }
        );

        setTimeout(showOpen,500);
    }

    function openCatalogue() {
        allContainer.jQueryTween({

                //options
                from: {position: {right: -totalWidth}},
                to: {position: {right: 0}},
                duration: 500,
                delay: 0,
                yoyo: false,
                easing: TWEEN.Easing.Quadratic.Out
            }
        );
    }

    function showOpen() {
        openBtn.jQueryTween({
                //options
                from: {position: {right: -btnWidth}},
                to: {position: {right: 0}},
                duration: 500,
                delay: 0,
                yoyo: false,
                easing: TWEEN.Easing.Quadratic.Out
            }
        );
    }

    function hideOpen() {
        openBtn.jQueryTween({
                //options
                from: {position: {right: 0}},
                to: {position: {right: -btnWidth}},
                duration: 300,
                delay: 0,
                yoyo: false,
                easing: TWEEN.Easing.Quadratic.Out
            }
        );
        setTimeout(openCatalogue,300);
    }
});