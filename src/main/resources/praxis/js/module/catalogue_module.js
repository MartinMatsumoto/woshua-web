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
    var byBook = $("#by_book");
    var byKnowLedge = $("#by_knowledge");

    var btnWidth = closeBtn.width();
    var containerWidth = allContainer.width();
    var totalWidth = btnWidth + containerWidth + 1;

    var prev;
    var call;

    exports.init = function (callback) {
        call = callback;
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

        var type = div.attr("type");
        var parent = div.attr("parent");

        if(type == 2 && parent == 0){
            li.hide();
        }

        div.bind("click",function () {
            if(prev){
                prev.removeClass("choose");
            }
            $(this).addClass("choose");
            prev = $(this);

            if(call){
                call($(this).attr("catalogueId"));
            }
        });

        i.bind("click",function (e) {
            try{
                e.cancelBubble();
            }catch (e){
            }
            try{
                e.stopPropagation();
            }catch (e){
            }
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

        $("#catalogue_content").children("ul").css("margin-left",0);

        byBook.bind("click",function () {
            byBook.addClass("choose");
            byKnowLedge.removeClass("choose");

            var lis = $("#catalogue_content").children("ul").children("li");
            if(lis[0]){
                $(lis[0]).show();
            }
            if(lis[1]){
                $(lis[1]).hide();
            }
        });

        byKnowLedge.bind("click",function () {
            byBook.removeClass("choose");
            byKnowLedge.addClass("choose");

            var lis = $("#catalogue_content").children("ul").children("li");
            if(lis[1]){
                $(lis[1]).show();
            }
            if(lis[0]){
                $(lis[0]).hide();
            }
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