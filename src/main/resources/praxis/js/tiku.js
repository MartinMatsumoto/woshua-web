/**
 * Created by Acer on 2016/9/18.
 */
seajs.use(['header_module','login_dialog_module','tiku_module','catalogue_module'], function (headerModule, loginDialogModule,tikuModule,catalogueModule) {
    headerModule.initHead();

    headerModule.initScroll();

    loginDialogModule.initClick();

    tikuModule.pageEnter();

    tikuModule.initData();

    tikuModule.initTips();

    catalogueModule.init();

});