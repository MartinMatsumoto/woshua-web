/**
 * Created by martinzeng on 16/9/19.
 */
/**
 * Created by Acer on 2016/9/19.
 */
define(function (require, exports, module) {

    var $ = require("jquery");
    var config = require("config");

    exports.doEvilBackground = function (str) {
        //str = str.replaceAll(/src=/g, "src=\"" + config.imageHeader);
        return str.replace(/src=\"/g, "src=\"" + config.imageHeader).replace(/url\(\'/g,"url('" + config.imageHeader);
    };

    //厉害了我的哥
    /*exports.doEvilImg = function (container) {
        var imgArr = container.find("img");
        for (var i = 0; i < imgArr.length; i++) {
            var imgObj = $(imgArr[i]);
            var src = imgObj.attr("src");
            imgObj.attr("src", config.imageHeader + src);
        }
    }*/

    exports.doEvilWidth = function (container) {
        var divArr = container.find(".web_right_cent_b_dx");
        for (var i = 0; i < divArr.length; i++) {
            var divObj = $(divArr[i]);
            divObj.css("width", "");
            divObj.css("font-size", "");
        }
    }

});