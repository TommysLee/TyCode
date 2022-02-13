<!-- VUE全局混用：实现功能复用 -->
Vue.mixin({
    i18n,
    vuetify: new Vuetify(),
    data: function() {
        return {
            fullscreenIcon: "mdi-fullscreen",
            lang: "zh_CN",
            showToast: false,
            toastText: ''
        }
    },
    watch: {
        lang(val, oldVal) {
            $cookies.set("lang", val);
            this.$i18n.locale = val;
            console.log('lang: ' + val);
        }
    },
    created() {
        this.lang = $cookies.get("lang");
    },
    methods: {
        toggleFullScreen() {
            screenfull.toggle();
            this.fullscreenIcon = "mdi-fullscreen-exit" == this.fullscreenIcon? "mdi-fullscreen" : "mdi-fullscreen-exit";
        },
        clearCache() {
            let _this = this;
            doAjax("cls", null, (response) => {
                _this.toastText = _this.$t("message.clearSuccess");
                _this.showToast = true;
            });
        }
    }
});

/**
 * 封装Axios Ajax
 */
function doAjax(url, params, callback, method) {
    method = method || "GET";
    method = method.toUpperCase();
    params = params || {};

    if (url) {
        let _params = "";
        let promise;
        switch(method) {
            case "POST":
                for (let p in params) {
                    let val = params[p];
                    if (undefined != val && null != val && '' != val) {
                        _params += (p + "=" + val + "&")
                    }
                }
                _params = _params.trim();
                params = _params.length > 0? _params.substr(0, _params.length - 1) : _params;

                promise = axios.post(url, params);
                break;
            default:
                params = {"params": params};
                promise = axios.get(url, params);
        }

        // Ajax回调
        promise.then((response) => {
            if (callback)
                callback(response);
        }).catch((err) => {
            console.log("Ajax请求异常：" + err);
        });
    }
}