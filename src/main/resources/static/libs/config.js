/**
 * Created by Acer on 2016/9/19.
 */
define(function(require, exports, module) {
    exports.csrfParameterName = "#csrf_parameter_name";
    exports.csrfToken = "#csrf_token";

    /** 主页url */
    exports.mainHref = "/";
    /** 题库url */
    exports.tikuHref = "/praxis/tiku";
    /** 个人空间url */
    exports.spaceHref = "/praxis/space";
    /** 免责声明url */
    exports.mianzeHref = "/praxis/mianze";
    /** 服务条款url */
    exports.fuwuHref = "/praxis/fuwu";
    /** 联系我们url */
    exports.contactHref = "/praxis/contact";
    /** 组卷 */
    exports.zujuanHref = "/praxis/zujuan";

    /** 获取习题列表 */
    exports.listPraxis = "/praxis/list_praxis";
    /** 获取一道习题 */
    exports.getOnePraxis = "/praxis/structure/controller/get_one_praxis.php";
    /** 登录 */
    exports.login = "/user/login";
    /** 注册 */
    exports.register = "/user/register";
    /** 验证账号密码是否使用 */
    exports.authExist = "/user/auth_exist";
    /** 图片header */
    exports.imageHeader = "http://www.sosoti.com";
    /** 添加收藏 */
    exports.addFavorite = "/praxis/add_favorite";
    /** 取消收藏 */
    exports.cancelFavorite = "/praxis/cancel_favorite";
    /** 获取习题列表 */
    exports.listFavoritePraxis = "/praxis/list_favorite";
});
