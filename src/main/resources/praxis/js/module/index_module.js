/**
 * Created by Acer on 2016/9/19.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var readMore = $("#read_more");
    var config = require("config");

    exports.initItem = function () {
        var itemArr = $("#item_container").children(".item");
        for (var i = 0; i < itemArr.length; i++) {
            initItemHover($(itemArr[i]), i);
        }
    }

    exports.initReadMore = function () {
        readMore.bind("click", function () {
            window.location.href = config.tikuHref;
        });
    }

    var initItemHover = function (obj, i) {

        var href = config.tikuHref;
        if (i == 0) {
            href += "?grade=" + 13 + "&decipline=" + 4;
        } else if (i == 1) {
            href += "?grade=" + 14 + "&decipline=" + 5;
        } else if (i == 2) {
            href += "?grade=" + 14 + "&decipline=" + 6;
        } else if (i == 3) {
            href += "?grade=" + 15 + "&decipline=" + 7;
        } else if (i == 4) {
            href += "?grade=" + 15 + "&decipline=" + 8;
        }

        obj.click(function () {
            window.location.href = href;
        });

        obj.hover(function () {
            var this_ = $(this);
            var img_ = this_.find("img");
            var subject_ = this_.find(".item_subject");

            this_.find(".item_image_container").addClass("hover");
            subject_.addClass("subject_hover");
            var src = img_.attr("src").replace(".png", "_white.png");
            img_.attr("src", src);


        }, function () {
            var this_ = $(this);
            var img_ = this_.find("img");
            var subject_ = this_.find(".item_subject");

            this_.find(".item_image_container").removeClass("hover");
            subject_.removeClass("subject_hover");
            var src = img_.attr("src").replace("_white.png", ".png");
            img_.attr("src", src);

        });
    }

});